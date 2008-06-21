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

import java.io.InputStream;
import java.io.OutputStream;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.api.Api;

public class OfficeImporterPluginApi extends Api implements OfficeImporter
{
    /**
     * the plugin instance
     */
    private OfficeImporterPlugin plugin;

    public OfficeImporterPluginApi(OfficeImporterPlugin plugin, XWikiContext context)
    {
        super(context);
        setPlugin(plugin);
    }

    public OfficeImporterPlugin getPlugin()
    {
        if (hasProgrammingRights()) {
            return plugin;
        }
        return null;
    }

    public void setPlugin(OfficeImporterPlugin plugin)
    {
        this.plugin = plugin;
    }

    public OutputStream convert(InputStream inputDoc, String format, boolean convertToXwikiSyntax)
    {
        throw new UnsupportedOperationException("No yet implemented.");
    }

    public String convert(String attachmentFilename)
    {
        return getPlugin().convert(attachmentFilename, context);
    }

    public void convert(InputStream inputStream, OutputStream outputStream)
    {
        throw new UnsupportedOperationException("No yet implemented.");
    }
    
}
