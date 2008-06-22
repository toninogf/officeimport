/*  Copyright (c) 2006-2007, Vladimir Nikic
    All rights reserved.
	
    Redistribution and use of this software in source and binary forms, 
    with or without modification, are permitted provided that the following 
    conditions are met:
	
    * Redistributions of source code must retain the above
      copyright notice, this list of conditions and the
      following disclaimer.
	
    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the
      following disclaimer in the documentation and/or other
      materials provided with the distribution.
	
    * The name of HtmlCleaner may not be used to endorse or promote 
      products derived from this software without specific prior
      written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
    ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
    LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
    CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
    SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
    INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
    CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
    POSSIBILITY OF SUCH DAMAGE.
	
    You can contact Vladimir Nikic by sending e-mail to
    nikic_vladimir@yahoo.com. Please include the word "HtmlCleaner" in the
    subject line.
*/

package org.htmlcleaner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

/**
 * Main HtmlCleaner class.
 *
 * <p>It represents public interface to the user. It's task is to call tokenizer with
 * specified source HTML, traverse list of produced token list and create internal
 * object model. It also offers a set of methods to write resulting XML to string,
 * file or any output stream.</p>
 * <p>Typical usage is the following:</p>
 *
 * <xmp>
 *      HtmlCleaner cleaner = new HtmlCleaner(...);     // one of few constructors
 *      cleaner.setXXX(...)                             // optionally, set cleaner's behaviour
 *      clener.clean();                                 // calls cleaning process
 *      cleaner.writeXmlXXX(...);                       // writes resulting XML to string, file or any output stream
 *      // cleaner.createDOM();                         // creates DOM of resulting xml
 *      // cleaner.createJDom();                        // creates JDom of resulting xml 
 * </xmp>
 *
 * Created by: Vladimir Nikic <br/>
 * Date: November, 2006
 */
public class HtmlCleaner {

    public static final String DEFAULT_CHARSET = System.getProperty("file.encoding");
    
    private static final int WRITE_METHOD_SIMPLE = 0;  
    private static final int WRITE_METHOD_COMPACT = 1;
    private static final int WRITE_METHOD_PRETTY = 2;
    private static final int WRITE_METHOD_BROWSERCOMPACT = 3;

    /**
     * Contains information about single open tag
     */
    private class TagPos {
		private int position;
		private String name;
		private TagInfo info;

		TagPos(int position, String name) {
			this.position = position;
			this.name = name;
            this.info = tagInfoProvider.getTagInfo(name);
        }
	}

    /**
     * Class that contains information and mathods for managing list of open,
     * but unhandled tags.
     */
    private class OpenTags {
        private List list = new ArrayList();
        private TagPos last = null;
        private Set set = new HashSet();

        private boolean isEmpty() {
            return list.isEmpty();
        }

        private void addTag(String tagName, int position) {
            last = new TagPos(position, tagName);
            list.add(last);
            set.add(tagName);
        }

        private void removeTag(String tagName) {
            ListIterator it = list.listIterator( list.size() );
            while ( it.hasPrevious() ) {
                TagPos currTagPos = (TagPos) it.previous();
                if (tagName.equals(currTagPos.name)) {
                    it.remove();
                    break;
                }
            }

            last =  list.isEmpty() ? null : (TagPos) list.get( list.size() - 1 );
        }

        private TagPos findFirstTagPos() {
            return list.isEmpty() ? null : (TagPos) list.get(0);
        }

        private TagPos getLastTagPos() {
            return last;
        }

        private TagPos findTag(String tagName) {
            if (tagName != null) {
                ListIterator it = list.listIterator(list.size());
                String fatalTag = null;
                TagInfo fatalInfo = tagInfoProvider.getTagInfo(tagName);
                if (fatalInfo != null) {
                    fatalTag = fatalInfo.getFatalTag();
                }

                while (it.hasPrevious()) {
                    TagPos currTagPos = (TagPos) it.previous();
                    if (tagName.equals(currTagPos.name)) {
                        return currTagPos;
                    } else if (fatalTag != null && fatalTag.equals(currTagPos.name)) {
                        // do not search past a fatal tag for this tag
                        return null;
                    }
                }
            }

            return null;
        }

        private boolean tagExists(String tagName) {
            TagPos tagPos = findTag(tagName);
            return tagPos != null;
        }

        private TagPos findTagToPlaceRubbish() {
            TagPos result = null, prev = null;

            if ( !isEmpty() ) {
                ListIterator it = list.listIterator( list.size() );
                while ( it.hasPrevious() ) {
                    result = (TagPos) it.previous();
                    if ( result.info == null || result.info.allowsAnything() ) {
                    	if (prev != null) {
                            return prev;
                        }
                    }
                    prev = result;
                }
            }

            return result;
        }
        
        private boolean tagEncountered(String tagName) {
        	return set.contains(tagName);
        }
        
        /**
         * Checks if any of tags specified in the set are already open.
         * @param tags
         */
        private boolean someAlreadyOpen(Set tags) {
        	Iterator it = list.iterator();
            while ( it.hasNext() ) {
            	TagPos curr = (TagPos) it.next();
            	if ( tags.contains(curr.name) ) {
            		return true;
            	}
            }
            
            
            return false;
        }
    }

    private ITagInfoProvider tagInfoProvider;

    private Reader reader;
    private transient OpenTags _openTags;
    private transient DoctypeToken _docType = null;
    private Set allTags = new TreeSet(); 

    private boolean advancedXmlEscape = true;
    private boolean useCdataForScriptAndStyle = true;
    private boolean translateSpecialEntities = true;
    private boolean recognizeUnicodeChars = true;
    private boolean omitUnknownTags = false;
    private boolean treatUnknownTagsAsContent = false;
    private boolean omitDeprecatedTags = false;
    private boolean treatDeprecatedTagsAsContent = false;
    private boolean omitComments = false;
    private boolean omitXmlDeclaration = false;
    private boolean omitDoctypeDeclaration = true;
    private boolean omitHtmlEnvelope = false;
    private boolean useEmptyElementTags = true;
    private boolean allowMultiWordAttributes = true;
    private boolean allowHtmlInsideAttributes = false;
    private boolean ignoreQuestAndExclam = false;
    private boolean namespacesAware = true;
    private String hyphenReplacementInComment = "=";
    private String pruneTags = null;

    private TagNode htmlNode;
    private TagNode bodyNode;
    private TagNode headNode;
    private TagNode rootNode;

    private Set pruneTagSet = new HashSet();
    private Set pruneNodeSet = new HashSet();

    /**
	 * Constructor - creates the instance with specified html 
	 * content as String.
	 * @param htmlContent
	 */
	public HtmlCleaner(String htmlContent, ITagInfoProvider tagInfoProvider) {
		this.reader = new StringReader(htmlContent);
        this.tagInfoProvider = tagInfoProvider == null ? HtmlTagProvider.getInstance() : tagInfoProvider;
    }
	/**
	 * Constructor - creates the instance with specified html
	 * content as String.
	 * @param htmlContent
	 */
	public HtmlCleaner(String htmlContent) {
		this(htmlContent, HtmlTagProvider.getInstance());
	}

	/**
	 * Constructor - creates the instance for specified file.
	 * @param file
	 * @param charset
	 * @throws IOException
	 */
	public HtmlCleaner(File file, String charset, ITagInfoProvider tagInfoProvider) throws IOException {
		FileInputStream in = new FileInputStream(file);
		this.reader = new InputStreamReader(in, charset);
        this.tagInfoProvider = tagInfoProvider == null ? HtmlTagProvider.getInstance() : tagInfoProvider;
    }

	/**
	 * Constructor - creates the instance for specified file.
	 * @param file
	 * @param charset
	 * @throws IOException
	 */
	public HtmlCleaner(File file, String charset) throws IOException {
		this(file, charset, HtmlTagProvider.getInstance());
    }

    /**
	 * Constructor - creates the instance for specified file and charset.
	 * @param file
	 * @throws IOException
	 */
	public HtmlCleaner(File file, ITagInfoProvider tagInfoProvider) throws IOException {
        this(file, DEFAULT_CHARSET, tagInfoProvider);
    }

	/**
	 * Constructor - creates the instance for specified file and charset.
	 * @param file
	 * @throws IOException
	 */
	public HtmlCleaner(File file) throws IOException {
		this(file, DEFAULT_CHARSET, HtmlTagProvider.getInstance());
	}

	/**
	 * Constructor - creates the instance for specified URL and charset.
	 * @param url
	 * @param charset
	 * @throws IOException 
	 */
	public HtmlCleaner(URL url, String charset, ITagInfoProvider tagInfoProvider) throws IOException {
		StringBuffer content = Utils.readUrl(url, charset);
		this.reader = new StringReader( content.toString() );
        this.tagInfoProvider = tagInfoProvider == null ? HtmlTagProvider.getInstance() : tagInfoProvider;
    }

	/**
	 * Constructor - creates the instance for specified URL and charset.
	 * @param url
	 * @param tagInfoProvider
	 * @throws IOException
	 */
	public HtmlCleaner(URL url, ITagInfoProvider tagInfoProvider) throws IOException {
		this(url, DEFAULT_CHARSET, tagInfoProvider);
    }

	/**
	 * Constructor - creates the instance for specified URL and charset.
	 * @param url
	 * @param charset
	 * @throws IOException
	 */
	public HtmlCleaner(URL url, String charset) throws IOException {
		this(url, charset, HtmlTagProvider.getInstance());
    }

	/**
	 * Constructor - creates the instance for specified URL and charset.
	 * @param url
	 * @throws IOException
	 */
	public HtmlCleaner(URL url) throws IOException {
		this(url, DEFAULT_CHARSET, HtmlTagProvider.getInstance());
    }

    /**
     * Constructor - creates the instance for the specified input stream
     * @param in
     * @param tagInfoProvider
     */
    public HtmlCleaner(InputStream in, ITagInfoProvider tagInfoProvider, String charset) throws IOException {
    	this.reader = new InputStreamReader(in, charset);
        this.tagInfoProvider = tagInfoProvider == null ? HtmlTagProvider.getInstance() : tagInfoProvider;
    }

    /**
     * Constructor - creates the instance for the specified input stream
     * @param in
     * @param tagInfoProvider
     */
    public HtmlCleaner(InputStream in, ITagInfoProvider tagInfoProvider) {
    	this.reader = new InputStreamReader(in);
        this.tagInfoProvider = tagInfoProvider == null ? HtmlTagProvider.getInstance() : tagInfoProvider;
    }

	/**
     * Constructor - creates the instance for the specified input stream
     * and the charset
     * @param in
     * @param charset
     * @throws IOException
     */
    public HtmlCleaner(InputStream in, String charset) throws IOException {
    	this.reader = new InputStreamReader(in, charset);
        this.tagInfoProvider = HtmlTagProvider.getInstance();
    }

    /**
     * Constructor - creates the instance for the specified reader
     * @param reader
     * @param tagInfoProvider
     */
    public HtmlCleaner(Reader reader, ITagInfoProvider tagInfoProvider) {
    	this.reader = reader;
        this.tagInfoProvider = tagInfoProvider == null ? HtmlTagProvider.getInstance() : tagInfoProvider;
    }

    /**
     * Constructor - creates the instance for the specified reader
     * @param reader
     */
    public HtmlCleaner(Reader reader) {
        this(reader, null);
    }

    /**
     * Constructor - creates the instance for the specified inpout stream
     * @param in
     */
    public HtmlCleaner(InputStream in) {
    	this(in, HtmlTagProvider.getInstance());
    }

    DoctypeToken getDoctype() {
		return _docType;
	}

	void setDoctype(DoctypeToken type) {
		_docType = type;
	}

    public void clean() throws IOException {
        _openTags = new OpenTags();
        _docType = null;
        allTags.clear();

        htmlNode = new TagNode("html", this);
        bodyNode = new TagNode("body", this);
        headNode = new TagNode("head", this);
        rootNode = null;
        htmlNode.addChild(headNode);
        htmlNode.addChild(bodyNode);

        HtmlTokenizer htmlTokenizer = new HtmlTokenizer(this);

		htmlTokenizer.start();

        List nodeList = htmlTokenizer.getTokenList();
        closeAll(nodeList);
        createDocumentNodes(nodeList);

        calculateRootNode( htmlTokenizer.getNamespacePrefixes() );

        // if there are some nodes to prune from tree
        if ( pruneNodeSet != null && !pruneNodeSet.isEmpty() ) {
            Iterator iterator = pruneNodeSet.iterator();
            while (iterator.hasNext()) {
                TagNode tagNode = (TagNode) iterator.next();
                TagNode parent = tagNode.getParent();
                if (parent != null) {
                    parent.removeChild(tagNode);
                }
            }
        }
    }

    /**
     * Assigns root node to internal variable and adds neccessery xmlns
     * attributes if cleaner if namespaces aware.
     * Root node of the result depends on parameter "omitHtmlEnvelope".
     * If it is set, then first child of the body will be root node,
     * or html will be root node otherwise.
     * 
     * @param namespacePrefixes
     */
    private void calculateRootNode(Set namespacePrefixes) {
        this.rootNode =  this.htmlNode;

        if (this.omitHtmlEnvelope) {
            List bodyChildren = this.bodyNode.getChildren();
            if (bodyChildren != null) {
                Iterator iterator = bodyChildren.iterator();
                while (iterator.hasNext()) {
                    Object currChild = iterator.next();
                    // if found child that is tag itself, then return it
                    if (currChild instanceof TagNode) {
                        this.rootNode = (TagNode)currChild;
                    }
                }
            }
        }

        Map atts = this.rootNode.getAttributes();

        if (namespacesAware && namespacePrefixes != null) {
            Iterator iterator = namespacePrefixes.iterator();
            while (iterator.hasNext()) {
                String prefix = (String) iterator.next();
                String xmlnsAtt = "xmlns:" + prefix;
                if ( !atts.containsKey(xmlnsAtt) ) {
                    this.rootNode.addAttribute(xmlnsAtt, prefix);
                }
            }
        }
    }
    
    Reader getReader() {
    	return reader;
    }

    /**
     * Add attributes from specified map to the specified tag.
     * If some attribute already exist it is preserved.
     * @param tag
     * @param attributes
     */
	private void addAttributesToTag(TagNode tag, Map attributes) {
		if (attributes != null) {
			Map tagAttributes = tag.getAttributes();
			Iterator it = attributes.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry currEntry = (Map.Entry) it.next();
				String attName = (String) currEntry.getKey();
				if ( !tagAttributes.containsKey(attName) ) {
					String attValue = (String) currEntry.getValue();
					tag.addAttribute(attName, attValue);
				}
			}
		}
	}

    /**
     * Checks if open fatal tag is missing if there is a fatal tag for
     * the specified tag.
     * @param tag
     */
    private boolean isFatalTagSatisfied(TagInfo tag) {
    	if (tag != null) {
            String fatalTagName = tag.getFatalTag();
            return fatalTagName == null ? true : _openTags.tagExists(fatalTagName);
    	}

    	return true;
    }

    /**
     * Check if specified tag requires parent tag, but that parent
     * tag is missing in the appropriate context.
     * @param tag
     */
    private boolean mustAddRequiredParent(TagInfo tag) {
    	if (tag != null) {
    		String requiredParent = tag.getRequiredParent();
    		if (requiredParent != null) {
	    		String fatalTag = tag.getFatalTag();
                int fatalTagPositon = -1;
                if (fatalTag != null) {
                    TagPos tagPos = _openTags.findTag(fatalTag);
                    if (tagPos != null) {
                        fatalTagPositon = tagPos.position;
                    }
                }

	    		// iterates through the list of open tags from the end and check if there is some higher
	    		ListIterator it = _openTags.list.listIterator( _openTags.list.size() );
	            while ( it.hasPrevious() ) {
	            	TagPos currTagPos = (TagPos) it.previous();
	            	if (tag.isHigher(currTagPos.name)) {
	            		return currTagPos.position <= fatalTagPositon;
	            	}
	            }

	            return true;
    		}
    	}

    	return false;
    }

    private TagNode createTagNode(TagNode startTagToken) {
    	startTagToken.setFormed();
    	return startTagToken;
    }

    private boolean isAllowedInLastOpenTag(BaseToken token) {
        TagPos last = _openTags.getLastTagPos();
        if (last != null) {
			 if (last.info != null) {
                 return last.info.allowsItem(token);
			 }
		}

		return true;
    }

    private void saveToLastOpenTag(List nodeList, Object tokenToAdd) {
        TagPos last = _openTags.getLastTagPos();
        if ( last != null && last.info != null && last.info.isIgnorePermitted() ) {
            return;
        }

        TagPos rubbishPos = _openTags.findTagToPlaceRubbish();
        if (rubbishPos != null) {
    		TagNode startTagToken = (TagNode) nodeList.get(rubbishPos.position);
            startTagToken.addItemForMoving(tokenToAdd);
        }
    }
    
    private boolean isStartToken(Object o) {
    	return (o instanceof TagNode) && !((TagNode)o).isFormed(); 
    }

	void makeTree(List nodeList, ListIterator nodeIterator) {
		// process while not reach the end of the list
		while ( nodeIterator.hasNext() ) {
			BaseToken token = (BaseToken) nodeIterator.next();

            if (token instanceof EndTagToken) {
				EndTagToken endTagToken = (EndTagToken) token;
				String tagName = endTagToken.getName();
				TagInfo tag = tagInfoProvider.getTagInfo(tagName);

				if ( (tag == null && omitUnknownTags) || (tag != null && tag.isDeprecated() && omitDeprecatedTags) ) {
					nodeIterator.set(null);
				} else if ( tag != null && !tag.allowsBody() ) {
					nodeIterator.set(null);
				} else {
					TagPos matchingPosition = _openTags.findTag(tagName);

					if (matchingPosition != null) {
                        closeSnippet(nodeList, matchingPosition, endTagToken);
					} else if ( !isAllowedInLastOpenTag(token) ) {
                        saveToLastOpenTag(nodeList, token);
                    }

                    nodeIterator.set(null);
                }
			} else if ( isStartToken(token) ) {
                TagNode startTagToken = (TagNode) token;
				String tagName = startTagToken.getName();
				TagInfo tag = tagInfoProvider.getTagInfo(tagName);

                TagPos lastTagPos = _openTags.isEmpty() ? null : _openTags.getLastTagPos();
                TagInfo lastTagInfo = lastTagPos == null ? null : tagInfoProvider.getTagInfo(lastTagPos.name);

                // add tag to set of all tags
				allTags.add(tagName);

                // HTML open tag
                if ( "html".equals(tagName) ) {
					addAttributesToTag(htmlNode, startTagToken.getAttributes());
					nodeIterator.set(null);
                // BODY open tag
                } else if ( "body".equals(tagName) ) {
					addAttributesToTag(bodyNode, startTagToken.getAttributes());
					nodeIterator.set(null);
                // HEAD open tag
                } else if ( "head".equals(tagName) ) {
					addAttributesToTag(headNode, startTagToken.getAttributes());
					nodeIterator.set(null);
                // unknows HTML tag and unknown tags are not allowed
                } else if ( (tag == null && omitUnknownTags) || (tag != null && tag.isDeprecated() && omitDeprecatedTags) ) {
                    nodeIterator.set(null);
                // if current tag is unknown and last open tag doesn't allow any other tags in in its body
                } else if ( tag == null && lastTagInfo != null && !lastTagInfo.allowsAnything() ) {
                    closeSnippet(nodeList, lastTagPos, startTagToken);
                    nodeIterator.previous();
                } else if ( tag != null && tag.hasPermittedTags() && _openTags.someAlreadyOpen(tag.getPermittedTags()) ) {
                	nodeIterator.set(null);
                // if tag that must be unique, ignore this occurence
                } else if ( tag != null && tag.isUnique() && _openTags.tagEncountered(tagName) ) {
                	nodeIterator.set(null);
                // if there is no required outer tag without that this open tag is ignored
                } else if ( !isFatalTagSatisfied(tag) ) {
					nodeIterator.set(null);
                // if there is no required parent tag - it must be added before this open tag
                } else if ( mustAddRequiredParent(tag) ) {
					String requiredParent = tag.getRequiredParent();
					TagNode requiredParentStartToken = new TagNode(requiredParent, this);
					nodeIterator.previous();
					nodeIterator.add(requiredParentStartToken);
					nodeIterator.previous();
                // if last open tag has lower presidence then this, it must be closed
                } else if ( tag != null && lastTagPos != null && tag.isMustCloseTag(lastTagInfo) ) {
					List closed = closeSnippet(nodeList, lastTagPos, startTagToken);
					int closedCount = closed.size();

					// it is needed to copy some tags again in front of current, if there are any
					if ( tag.hasCopyTags() && closedCount > 0 ) {
						// first iterates over list from the back and collects all start tokens
						// in sequence that must be copied
						ListIterator closedIt = closed.listIterator(closedCount);
						List toBeCopied = new ArrayList();
						while (closedIt.hasPrevious()) {
							TagNode currStartToken = (TagNode) closedIt.previous();
							if ( tag.isCopy(currStartToken.getName()) ) {
								toBeCopied.add(0, currStartToken);
							} else {
								break;
							}
						}

						if (toBeCopied.size() > 0) {
							Iterator copyIt = toBeCopied.iterator();
							while (copyIt.hasNext()) {
								TagNode currStartToken = (TagNode) copyIt.next();
								nodeIterator.add( currStartToken.makeCopy() );
							}
                            
                            // back to the previous place, before adding new start tokens
							for (int i = 0; i < toBeCopied.size(); i++) {
								nodeIterator.previous();
							}
                        }
					}

                    nodeIterator.previous();
				// if this open tag is not allowed inside last open tag, then it must be moved to the place where it can be
                } else if ( !isAllowedInLastOpenTag(token) ) {
                    saveToLastOpenTag(nodeList, token);
                    nodeIterator.set(null);
				// if it is known HTML tag but doesn't allow body, it is immidiately closed
                } else if ( tag != null && !tag.allowsBody() ) {
					TagNode newTagNode = createTagNode(startTagToken);
					if ( tag.isHeadTag() ) {
						headNode.addChild(newTagNode);
						nodeIterator.set(null);
					} else {
						nodeIterator.set(newTagNode);
					}
				// default case - just remember this open tag and go further
                } else {
                    _openTags.addTag( tagName, nodeIterator.previousIndex() );
				}
			} else {
				if ( !isAllowedInLastOpenTag(token) ) {
                    saveToLastOpenTag(nodeList, token);
                    nodeIterator.set(null);
				}
			}
		}
    }

	private void createDocumentNodes(List listNodes) {
		Iterator it = listNodes.iterator();
        while (it.hasNext()) {
            Object child = it.next();

            if (child == null) {
            	continue;
            }

			TagNode parent = bodyNode;
			boolean toAdd = true;

            if (child instanceof TagNode) {
				TagInfo tag = tagInfoProvider.getTagInfo( ((TagNode)child).getName() );
				if (tag != null) {
					if ( tag.isHeadTag() || (tag.isHeadAndBodyTag() && bodyNode.getChildren().isEmpty()) ) {
						parent = headNode;
					}
				}
			} else {
				if (child instanceof ContentToken) {
					toAdd = !"".equals(child.toString());
				}
			}

			if (toAdd) {
				parent.addChild(child);
			}
        }
	}

	private List closeSnippet(List nodeList, TagPos tagPos, Object toNode) {
		List closed = new ArrayList();
		ListIterator it = nodeList.listIterator(tagPos.position);

		TagNode tagNode = null;
		Object item = it.next();
		boolean isListEnd = false;

		while ( (toNode == null && !isListEnd) || (toNode != null && item != toNode) ) {
			if ( isStartToken(item) ) {
                TagNode startTagToken = (TagNode) item;
                closed.add(startTagToken);
                List itemsToMove = startTagToken.getItemsToMove();
                if (itemsToMove != null) {
            		OpenTags prevOpenTags = _openTags;
            		_openTags = new OpenTags();
            		makeTree(itemsToMove, itemsToMove.listIterator(0));
                    closeAll(itemsToMove);
                    startTagToken.setItemsToMove(null);
                    _openTags = prevOpenTags;
                }
                
                TagNode newTagNode = createTagNode(startTagToken);

                TagInfo tag = tagInfoProvider.getTagInfo( newTagNode.getName() );
                if ( tag != null && tag.isHeadTag() ) {
					headNode.addChild(newTagNode);
					it.set(null);
				} else if (tagNode != null) {
					tagNode.addChildren(itemsToMove);
                    tagNode.addChild(newTagNode);
                    it.set(null);
                } else {
                	if (itemsToMove != null) {
                		itemsToMove.add(newTagNode);
                		it.set(itemsToMove);
                	} else {
                		it.set(newTagNode);
                	}
                }

                _openTags.removeTag( newTagNode.getName() );
                tagNode = newTagNode;
            } else {
            	if (tagNode != null) {
            		it.set(null);
            		if (item != null) {
            			tagNode.addChild(item);
                    }
                }
            }
			
			if ( it.hasNext() ) {
				item = it.next();
			} else {
				isListEnd = true;
			}
		}
		
		return closed;
    }

    /**
     * Close all unclosed tags if there are any.
     */
    private void closeAll(List nodeList) {
        TagPos firstTagPos = _openTags.findFirstTagPos();
        if (firstTagPos != null) {
            closeSnippet(nodeList, firstTagPos, null);
        }
    }

    // setters and getters

    public boolean isOmitUnknownTags() {
        return omitUnknownTags;
    }

    public void setOmitUnknownTags(boolean omitUnknownTags) {
        this.omitUnknownTags = omitUnknownTags;
    }

    public boolean isTreatUnknownTagsAsContent() {
        return treatUnknownTagsAsContent;
    }

    public void setTreatUnknownTagsAsContent(boolean treatUnknownTagsAsContent) {
        this.treatUnknownTagsAsContent = treatUnknownTagsAsContent;
    }

    public boolean isOmitDeprecatedTags() {
    	return omitDeprecatedTags;
    }
    
    public void setOmitDeprecatedTags(boolean omitDeprecatedTags) {
    	this.omitDeprecatedTags = omitDeprecatedTags;
    }

    public boolean isTreatDeprecatedTagsAsContent() {
        return treatDeprecatedTagsAsContent;
    }

    public void setTreatDeprecatedTagsAsContent(boolean treatDeprecatedTagsAsContent) {
        this.treatDeprecatedTagsAsContent = treatDeprecatedTagsAsContent;
    }

    public boolean isAdvancedXmlEscape() {
        return advancedXmlEscape;
    }

    public void setAdvancedXmlEscape(boolean advancedXmlEscape) {
        this.advancedXmlEscape = advancedXmlEscape;
    }

    public boolean isUseCdataForScriptAndStyle() {
        return useCdataForScriptAndStyle;
    }

    public void setUseCdataForScriptAndStyle(boolean useCdataForScriptAndStyle) {
        this.useCdataForScriptAndStyle = useCdataForScriptAndStyle;
    }

    public boolean isTranslateSpecialEntities() {
        return translateSpecialEntities;
    }

    public void setTranslateSpecialEntities(boolean translateSpecialEntities) {
        this.translateSpecialEntities = translateSpecialEntities;
    }

    public boolean isRecognizeUnicodeChars() {
        return recognizeUnicodeChars;
    }

    public void setRecognizeUnicodeChars(boolean recognizeUnicodeChars) {
        this.recognizeUnicodeChars = recognizeUnicodeChars;
    }

    public boolean isOmitComments() {
        return omitComments;
    }

    public void setOmitComments(boolean omitComments) {
        this.omitComments = omitComments;
    }

    public boolean isOmitXmlDeclaration() {
        return omitXmlDeclaration;
    }

    public void setOmitXmlDeclaration(boolean omitXmlDeclaration) {
        this.omitXmlDeclaration = omitXmlDeclaration;
    }
    
    public boolean isOmitDoctypeDeclaration() {
		return omitDoctypeDeclaration;
	}

	public void setOmitDoctypeDeclaration(boolean omitDoctypeDeclaration) {
		this.omitDoctypeDeclaration = omitDoctypeDeclaration;
	}

    /**
     * @deprecated Use isNamespacesAware() insted. This getter is preserved for backword
     * compatibility and returns negation of namespacesAware option. 
     */
    public boolean isOmitXmlnsAttributes() {
		return !namespacesAware;
	}

    /**
     * @deprecated Use setNamespacesAware() insted. This setter is preserved for backword
     * compatibility and sets negation of namespacesAware option.
     *
     * @param omitXmlnsAttributes
     */
    public void setOmitXmlnsAttributes(boolean omitXmlnsAttributes) {
		this.namespacesAware = !omitXmlnsAttributes;
	}

    public boolean isOmitHtmlEnvelope() {
        return omitHtmlEnvelope;
    }

    public void setOmitHtmlEnvelope(boolean omitHtmlEnvelope) {
        this.omitHtmlEnvelope = omitHtmlEnvelope;
    }

    public boolean isUseEmptyElementTags() {
        return useEmptyElementTags;
    }

    public void setUseEmptyElementTags(boolean useEmptyElementTags) {
        this.useEmptyElementTags = useEmptyElementTags;
    }

    public boolean isAllowMultiWordAttributes() {
        return allowMultiWordAttributes;
    }

    public void setAllowMultiWordAttributes(boolean allowMultiWordAttributes) {
        this.allowMultiWordAttributes = allowMultiWordAttributes;
    }

    public boolean isAllowHtmlInsideAttributes() {
        return allowHtmlInsideAttributes;
    }

    public void setAllowHtmlInsideAttributes(boolean allowHtmlInsideAttributes) {
        this.allowHtmlInsideAttributes = allowHtmlInsideAttributes;
    }

    public boolean isIgnoreQuestAndExclam() {
        return ignoreQuestAndExclam;
    }

    public void setIgnoreQuestAndExclam(boolean ignoreQuestAndExclam) {
        this.ignoreQuestAndExclam = ignoreQuestAndExclam;
    }

    public boolean isNamespacesAware() {
        return namespacesAware;
    }

    public void setNamespacesAware(boolean namespacesAware) {
        this.namespacesAware = namespacesAware;
    }

    public String getHyphenReplacementInComment() {
        return hyphenReplacementInComment;
    }

    public void setHyphenReplacementInComment(String hyphenReplacementInComment) {
        this.hyphenReplacementInComment = hyphenReplacementInComment;
    }

    public String getPruneTags() {
        return pruneTags;
    }

    public Set getPruneTagSet() {
        return pruneTagSet;
    }

    public void setPruneTags(String pruneTags) {
        pruneTagSet.clear();
        this.pruneTags = pruneTags;
        if (pruneTags != null) {
            StringTokenizer tokenizer = new StringTokenizer(pruneTags, ",");
            while ( tokenizer.hasMoreTokens() ) {
                pruneTagSet.add( tokenizer.nextToken().trim().toLowerCase() );
            }
        }
    }

    void addPruneNode(TagNode node) {
        this.pruneNodeSet.add(node);
    }

    public Set getAllTags() {
		return allTags;
	}

    /**
     * @return ITagInfoProvider instance for this HtmlCleaner
     */
    public ITagInfoProvider getTagInfoProvider() {
        return tagInfoProvider;
    }

    // methods for getting the result

    /**
     * @return Root node of the cleaned document. This value depends on
     *         omitHtmlEnvelope flag - if it is set to true, then first
     *         TagNode inside the body is retrieved if exists, or outer
     *         HTML node is returned otherwise. 
     */
    public TagNode getRootNode() {
        return this.rootNode;
    }

    /**
     * Creates XML JDOM document object.
     * @return Instance of org.jdom.Document
     */
    public org.jdom.Document createJDom() throws ParserConfigurationException {
        JDomSerializer jdomSerializer = new JDomSerializer();
        return jdomSerializer.createJDom( getRootNode() );
    }

    /**
     * Creates XML DOM document object without escaping XML.
     * @return Instance of org.w3c.dom.Document
     */
    public Document createDOM() throws ParserConfigurationException {
        return createDOM(false);
    }

    /**
     * Creates XML DOM document object.
     * @param escapeXml Tells whether to escape XML nodes before storing them into DOM tree.
     * @return Instance of org.w3c.dom.Document
     */
    public Document createDOM(boolean escapeXml) throws ParserConfigurationException {
        DomSerializer domSerializer = new DomSerializer(this, escapeXml);
        return domSerializer.createDOM( getRootNode() );
    }

    /**
     * The most general way to serialize resulting XML.
     * @param xmlSerializer
     * @throws IOException
     */
    public void writeXml(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.createXml( getRootNode() );
    }
    
    private void writeXml(Writer writer, int method, String charset) throws IOException {
        XmlSerializer xmlSerializer = null;
        
        if (WRITE_METHOD_COMPACT == method) {
        	xmlSerializer = new CompactXmlSerializer(writer, this, charset);
        } else if (WRITE_METHOD_BROWSERCOMPACT == method) {
        	xmlSerializer = new BrowserCompactXmlSerializer(writer, this, charset);
        } else if (WRITE_METHOD_PRETTY == method) {
        	xmlSerializer = new PrettyXmlSerializer(writer, this, charset);
        } else {
        	xmlSerializer = new SimpleXmlSerializer(writer, this, charset);
        }

        xmlSerializer.createXml( getRootNode() );
    }

	private void writeToStream(OutputStream out, String charset, int method) throws IOException {
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(out, charset) );
		writeXml(writer, method, charset);
    }

	private void writeToStream(OutputStream out, int method) throws IOException {
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(out) );
		writeXml(writer, method, null);
    }

    public void writeXmlToStream(OutputStream out) throws IOException {
        writeToStream(out, WRITE_METHOD_SIMPLE);
    }

	public void writeXmlToStream(OutputStream out, String charset) throws IOException {
		writeToStream(out, charset, WRITE_METHOD_SIMPLE);
	}

    public void writeCompactXmlToStream(OutputStream out) throws IOException {
    	writeToStream(out, WRITE_METHOD_COMPACT);
    }
    
    public void writeCompactXmlToStream(OutputStream out, String charset) throws IOException {
    	writeToStream(out, charset, WRITE_METHOD_COMPACT);
    }

    public void writeBrowserCompactXmlToStream(OutputStream out) throws IOException {
    	writeToStream(out, WRITE_METHOD_BROWSERCOMPACT);
    }

    public void writeBrowserCompactXmlToStream(OutputStream out, String charset) throws IOException {
    	writeToStream(out, charset, WRITE_METHOD_BROWSERCOMPACT);
    }

    public void writePrettyXmlToStream(OutputStream out) throws IOException {
    	writeToStream(out, WRITE_METHOD_PRETTY);
    }

    public void writePrettyXmlToStream(OutputStream out, String charset) throws IOException {
    	writeToStream(out, charset, WRITE_METHOD_PRETTY);
    }

    private void writeToFile(String fileName, String charset, int method) throws IOException {
        writeToStream(new FileOutputStream(fileName), charset, method );
    }

	private void writeToFile(String fileName, int method) throws IOException {
        writeToStream( new FileOutputStream(fileName), method );
    }

    public void writeXmlToFile(String fileName) throws IOException {
        writeToFile(fileName, WRITE_METHOD_SIMPLE);
    }

	public void writeXmlToFile(String fileName, String charset) throws IOException {
		writeToFile(fileName, charset, WRITE_METHOD_SIMPLE);
	}
    
    public void writeCompactXmlToFile(String fileName) throws IOException {
    	writeToFile(fileName, WRITE_METHOD_COMPACT);
    }
    
    public void writeCompactXmlToFile(String fileName, String charset) throws IOException {
    	writeToFile(fileName, charset, WRITE_METHOD_COMPACT);
    }

    public void writeBrowserCompactXmlToFile(String fileName) throws IOException {
    	writeToFile(fileName, WRITE_METHOD_BROWSERCOMPACT);
    }
    
    public void writeBrowserCompactXmlToFile(String fileName, String charset) throws IOException {
    	writeToFile(fileName, charset, WRITE_METHOD_BROWSERCOMPACT);
    }

    public void writePrettyXmlToFile(String fileName) throws IOException {
    	writeToFile(fileName, WRITE_METHOD_PRETTY);
    }

    public void writePrettyXmlToFile(String fileName, String charset) throws IOException {
    	writeToFile(fileName, charset, WRITE_METHOD_PRETTY);
    }

    public String getXmlAsString() throws IOException {
        StringWriter writer = new StringWriter();
        writeXml(writer, WRITE_METHOD_SIMPLE, null);

        return writer.getBuffer().toString();
    }

    public String getCompactXmlAsString() throws IOException {
        StringWriter writer = new StringWriter();
        writeXml(writer, WRITE_METHOD_COMPACT, null);

        return writer.getBuffer().toString();
    }

    public String getBrowserCompactXmlAsString() throws IOException {
        StringWriter writer = new StringWriter();
        writeXml(writer, WRITE_METHOD_BROWSERCOMPACT, null);

        return writer.getBuffer().toString();
    }

    public String getPrettyXmlAsString() throws IOException {
    	StringWriter writer = new StringWriter();
    	writeXml(writer, WRITE_METHOD_PRETTY, null);
    	
    	return writer.getBuffer().toString();
    }

}