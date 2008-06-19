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

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

public class OfficeImportPluginApi extends Api
{
    /**
     * the plugin instance
     */
    private OfficeImportPlugin plugin;

    public OfficeImportPluginApi(OfficeImportPlugin plugin, XWikiContext context)
    {
        super(context);
        setPlugin(plugin);
    }

    public OfficeImportPlugin getPlugin()
    {
        if (hasProgrammingRights()) {
            return plugin;
        }
        return null;
    }

    public void setPlugin(OfficeImportPlugin plugin)
    {
        this.plugin = plugin;
    }

    public OutputStream convert(InputStream inputDoc, String format, boolean convertToXwikiSyntax)
    {
        File inputFile = new File("document.doc");
        File outputFile = new File("document.pdf");
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // convert
        // TODO use different converter for remote and local oo server
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);

        // close the connection
        connection.disconnect();

        return null;
    }

    public String convert(String attachmentFilename)
    {
        XWikiContext context = getXWikiContext();
        XWikiAttachment attachment = context.getDoc().getAttachment(attachmentFilename);
        if (attachment == null) {           
            return "Couldn't find the requested attachment: " + attachmentFilename;
        }               
        byte[] array;
        try {
            array = attachment.getContent(context);
        } catch (XWikiException e2) {
            e2.printStackTrace();
            return "Error: " + e2.getMessage();
        }
        InputStream inputStream = new ByteArrayInputStream(array);
        
        int port = SocketOpenOfficeConnection.DEFAULT_PORT;
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(port);
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        DefaultDocumentFormatRegistry formatRegistry = new DefaultDocumentFormatRegistry();
        DocumentFormat inputFormat = formatRegistry.getFormatByFileExtension(attachmentFilename.substring(attachmentFilename.lastIndexOf(".")));
        DocumentFormat outputFormat = formatRegistry.getFormatByFileExtension("html");
        OutputStream outputStream = null;
        converter.convert(inputStream, inputFormat, outputStream, outputFormat);
        BufferedWriter reader = new BufferedWriter(new OutputStreamWriter(outputStream));
        reader.
        return null;
    }

    public void convert(File input, File output)
    {
        
    }
    
}
