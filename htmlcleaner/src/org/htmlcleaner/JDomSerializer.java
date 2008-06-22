package org.htmlcleaner;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Comment;
import org.jdom.DefaultJDOMFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Text;

/**
 * <p>DOM serializer - creates xml DOM.</p>
 * Created by: Vladimir Nikic, Benson Margulies <br/>
 * Date: April, 2007.
 */
public class JDomSerializer {

    private DefaultJDOMFactory factory;

    public Document createJDom(TagNode rootNode) {
        this.factory = new DefaultJDOMFactory();
        Element rootElement = this.factory.element(rootNode.getName());
        Document document = this.factory.document(rootElement);
        createSubnodes(document, rootElement, rootNode.getChildren());

        return document;
    }

    private void createSubnodes(Document document, Element element, List tagChildren) {
        if (tagChildren != null) {
            Iterator it = tagChildren.iterator();
            while (it.hasNext()) {
                Object item = it.next();
                if (item instanceof CommentToken) {
                    CommentToken commentToken = (CommentToken) item;
                    Comment comment = factory.comment( commentToken.getContent() );
                    element.addContent(comment);
                } else if (item instanceof ContentToken) {
                    ContentToken contentToken = (ContentToken) item;
                    Text text = factory.text( contentToken.getContent() );
                    element.addContent(text);
                } else if (item instanceof TagNode) {
                    TagNode subTagNode = (TagNode) item;
                    Element subelement = factory.element( subTagNode.getName() );
                    Map attributes = subTagNode.getAttributes();
                    Iterator entryIterator = attributes.entrySet().iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) entryIterator.next();
                        String attrName = (String) entry.getKey();
                        String attrValue = (String) entry.getValue();
                        subelement.setAttribute(attrName, attrValue);
                    }

                    // recursively create subnodes
                    createSubnodes(document, subelement, subTagNode.getChildren());

                    element.addContent(subelement);
                } else if (item instanceof List) {
                    List sublist = (List) item;
                    createSubnodes(document, element, sublist);
                }
            }
        }
    }

}