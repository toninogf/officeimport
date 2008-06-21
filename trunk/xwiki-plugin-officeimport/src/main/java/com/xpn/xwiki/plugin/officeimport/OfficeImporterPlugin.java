/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.xpn.xwiki.plugin.officeimport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.api.Api;
import com.xpn.xwiki.doc.XWikiAttachment;
import com.xpn.xwiki.plugin.XWikiDefaultPlugin;
import com.xpn.xwiki.plugin.XWikiPluginInterface;

/**
 * Plugin for import office document like MS Word, Open office odt to xwiki document
 * 
 * @see OfficeImporterPluginApi
 */
public class OfficeImporterPlugin extends XWikiDefaultPlugin
{
    /**
     * {@inheritDoc}
     * 
     * @see XWikiDefaultPlugin#XWikiDefaultPlugin(String,String,com.xpn.xwiki.XWikiContext)
     */
    public OfficeImporterPlugin(String name, String className, XWikiContext context)
    {
        super(name, className, context);
        init(context);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.xpn.xwiki.plugin.XWikiDefaultPlugin#getName()
     */
    @Override
    public String getName()
    {
        return "officeimport";
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.xpn.xwiki.plugin.XWikiDefaultPlugin#getPluginApi
     */
    @Override
    public Api getPluginApi(XWikiPluginInterface plugin, XWikiContext context)
    {
        return new OfficeImporterPluginApi((OfficeImporterPlugin) plugin, context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(XWikiContext context)
    {
        super.init(context);
    }

    /**
     * convert the attachment office document to html code
     * 
     * @param the attachment name which must with a extension
     * @param xwikicontext
     * @return the html code or the error message
     */
    public String convert(String attachmentFilename, XWikiContext context)
    {
        XWikiAttachment attachment = context.getDoc().getAttachment(attachmentFilename);
        if (attachment == null) {
            return "Couldn't find the requested attachment: " + attachmentFilename;
        }
        byte[] array;
        try {
            array = attachment.getContent(context);
        } catch (XWikiException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
        InputStream inputStream = new ByteArrayInputStream(array);

        DocumentConverter converter = this.getConverter();
        DefaultDocumentFormatRegistry formatRegistry = new DefaultDocumentFormatRegistry();
        String inputfileExtension = attachmentFilename.substring(attachmentFilename.lastIndexOf("."));
        if (inputfileExtension == null || inputfileExtension.equals("")) {
            return "Error: attachment file must have a extension.";
        }
        DocumentFormat inputFormat = formatRegistry.getFormatByFileExtension(inputfileExtension);
        DocumentFormat outputFormat = formatRegistry.getFormatByFileExtension("html");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        converter.convert(inputStream, inputFormat, outputStream, outputFormat);
        return outputStream.toString();
    }

    public OutputStream convert(InputStream inputDoc, String format, boolean convertToXwikiSyntax)
    {
        throw new UnsupportedOperationException("No yet implemented.");
    }

    private DocumentConverter getConverter()
    {
        int port = SocketOpenOfficeConnection.DEFAULT_PORT;
        return getConverter(port);
    }

    private DocumentConverter getConverter(int port)
    {
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(port);
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        return converter;
    }
}
