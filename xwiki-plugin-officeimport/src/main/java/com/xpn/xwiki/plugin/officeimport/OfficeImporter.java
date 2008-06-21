package com.xpn.xwiki.plugin.officeimport;

import java.io.InputStream;
import java.io.OutputStream;

public interface OfficeImporter
{
    String convert(String attachmentFilename);
    
    void convert(InputStream inputStream, OutputStream outputStream);
}
