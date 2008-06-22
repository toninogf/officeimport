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

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.tools.ant.BuildException;

/**
 * <p>Support for ANT.</p>
 *
 * Created by: Vladimir Nikic <br/>
 * Date: December, 2006.
 */
public class HtmlCleanerForAnt extends org.apache.tools.ant.Task {

    private String text;
    private String src;
    private String dest;
    private String incharset = HtmlCleaner.DEFAULT_CHARSET;
    private String outcharset = HtmlCleaner.DEFAULT_CHARSET;
    private String outputtype = "simple";
    private boolean advancedxmlescape = true;
    private boolean usecdata = true;
    private boolean specialentities = true;
    private boolean unicodechars = true;
    private boolean omitunknowntags = false;
    private boolean treatunknowntagsascontent = false;
    private boolean omitdeprtags = false;
    private boolean treatdeprtagsascontent = false;
    private boolean omitcomments = false;
    private boolean omitxmldecl = false;
    private boolean omitdoctypedecl = true;
    private boolean omithtmlenvelope = false;
    private boolean useemptyelementtags = true;
    private boolean allowmultiwordattributes = true;
    private boolean allowhtmlinsideattributes = false;
    private boolean ignoreqe = false;
    private boolean namespacesaware = true;
    private String hyphenreplacement = "=";
    private String prunetags = "";

    public void setText(String text) {
        this.text = text;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setIncharset(String incharset) {
        this.incharset = incharset;
    }

    public void setOutcharset(String outcharset) {
        this.outcharset = outcharset;
    }

    public void setOutputtype(String outputtype) {
        this.outputtype = outputtype;
    }

    public void setAdvancedxmlescape(boolean advancedxmlescape) {
        this.advancedxmlescape = advancedxmlescape;
    }

    public void setUsecdata(boolean usecdata) {
        this.usecdata = usecdata;
    }

    public void setSpecialentities(boolean specialentities) {
        this.specialentities = specialentities;
    }

    public void setUnicodechars(boolean unicodechars) {
        this.unicodechars = unicodechars;
    }

    public void setOmitunknowntags(boolean omitunknowntags) {
        this.omitunknowntags = omitunknowntags;
    }

    public void setTreatunknowntagsascontent(boolean treatunknowntagsascontent) {
        this.treatunknowntagsascontent = treatunknowntagsascontent;
    }

    public void setOmitdeprtags(boolean omitdeprtags) {
        this.omitdeprtags = omitdeprtags;
    }


    public void setTreatdeprtagsascontent(boolean treatdeprtagsascontent) {
        this.treatdeprtagsascontent = treatdeprtagsascontent;
    }

    public void setOmitcomments(boolean omitcomments) {
        this.omitcomments = omitcomments;
    }

    public void setOmitxmldecl(boolean omitxmldecl) {
        this.omitxmldecl = omitxmldecl;
    }

    public void setOmitdoctypedecl(boolean omitdoctypedecl) {
        this.omitdoctypedecl = omitdoctypedecl;
    }

    public void setOmithtmlenvelope(boolean omithtmlenvelope) {
        this.omithtmlenvelope = omithtmlenvelope;
    }

    public void setUseemptyelementtags(boolean useemptyelementtags) {
        this.useemptyelementtags = useemptyelementtags;
    }

    public void setAllowmultiwordattributes(boolean allowmultiwordattributes) {
        this.allowmultiwordattributes = allowmultiwordattributes;
    }

    public void setAllowhtmlinsideattributes(boolean allowhtmlinsideattributes) {
        this.allowhtmlinsideattributes = allowhtmlinsideattributes;
    }

    public void setIgnoreqe(boolean ignoreqe) {
        this.ignoreqe = ignoreqe;
    }

    public void setNamespacesaware(boolean namespacesaware) {
        this.namespacesaware = namespacesaware;
    }

    public void setHyphenreplacement(String hyphenreplacement) {
        this.hyphenreplacement = hyphenreplacement;
    }

    public void setPrunetags(String prunetags) {
        this.prunetags = prunetags;
    }

    public void addText(String text) {
        this.text = text;
    }

    /**
     * Implementation of Ant task execution.
     * @throws BuildException
     */
    public void execute() throws BuildException {
        HtmlCleaner cleaner = null;

        if (text == null && src == null) {
            throw new BuildException("Eather attribute 'src' or text body containing HTML must be specified!");
        }

        try {
            if ( src != null && (src.startsWith("http://") || src.startsWith("https://")) ) {
                cleaner = new HtmlCleaner(new URL(src), incharset);
            } else if (src != null) {
                cleaner = new HtmlCleaner(new File(src), incharset);
            } else {
                cleaner = new HtmlCleaner(text);
            }
        } catch (IOException e) {
            throw new BuildException(e);
        }

        cleaner.setAdvancedXmlEscape(this.advancedxmlescape);
        cleaner.setUseCdataForScriptAndStyle(this.usecdata);
        cleaner.setTranslateSpecialEntities(this.specialentities);
        cleaner.setRecognizeUnicodeChars(this.unicodechars);
        cleaner.setOmitUnknownTags(this.omitunknowntags);
        cleaner.setTreatUnknownTagsAsContent(this.treatunknowntagsascontent);
        cleaner.setOmitDeprecatedTags(this.omitdeprtags);
        cleaner.setTreatDeprecatedTagsAsContent(this.treatdeprtagsascontent);
        cleaner.setOmitComments(this.omitcomments);
        cleaner.setOmitXmlDeclaration(this.omitxmldecl);
        cleaner.setOmitDoctypeDeclaration(this.omitdoctypedecl);
        cleaner.setOmitHtmlEnvelope(this.omithtmlenvelope);
        cleaner.setUseEmptyElementTags(this.useemptyelementtags);
        cleaner.setAllowMultiWordAttributes(this.allowmultiwordattributes);
        cleaner.setAllowHtmlInsideAttributes(this.allowhtmlinsideattributes);
        cleaner.setIgnoreQuestAndExclam(this.ignoreqe);
        cleaner.setNamespacesAware(this.namespacesaware);
        cleaner.setHyphenReplacementInComment(this.hyphenreplacement);
        cleaner.setPruneTags(this.prunetags);

        try {
            cleaner.clean();

            if ( dest == null || "".equals(dest.trim()) ) {
                if ( "compact".equals(outputtype) ) {
                    cleaner.writeCompactXmlToStream(System.out, outcharset);
                } else if ( "browser-compact".equals(outputtype) ) {
                    cleaner.writeBrowserCompactXmlToStream(System.out, outcharset);
                } else if ( "pretty".equals(outputtype) ) {
                    cleaner.writePrettyXmlToStream(System.out, outcharset);
                } else {
                    cleaner.writeXmlToStream(System.out, outcharset);
                }
            } else {
                if ( "compact".equals(outputtype) ) {
                    cleaner.writeCompactXmlToFile(dest, outcharset);
                } else if ( "browser-compact".equals(outputtype) ) {
                    cleaner.writeBrowserCompactXmlToFile(dest, outcharset);
                } else if ( "pretty".equals(outputtype) ) {
                    cleaner.writePrettyXmlToFile(dest, outcharset);
                } else {
                    cleaner.writeXmlToFile(dest, outcharset);
                }
            }
        } catch (IOException e) {
             throw new BuildException(e);
        }
    }

}