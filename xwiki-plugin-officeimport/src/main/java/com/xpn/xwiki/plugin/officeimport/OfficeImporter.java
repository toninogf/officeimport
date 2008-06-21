package com.xpn.xwiki.plugin.officeimport;

import java.io.InputStream;
import java.io.OutputStream;

public interface OfficeImporter
{
    /**
     * convert the attachment office document to html code
     * 
     * @param attachmentFilename
     * @return the html code or the error message
     */
    String convert(String attachmentFilename);

    /**
     * convert the inputStream which contain the office document to a outputstream which contain the result html code
     */
    void convert(InputStream inputStream, OutputStream outputStream);
    
    /*
    void convert(InputStream inputStream, OutputStream outputStream, int outputFormat);
    void convert(InputStream inputStream, OutputStream outputStream, boolean convertToXwikiSyntax);
    void convert(InputStream inputStream, OutputStream outputStream, int outputFormat);
    void convert(String filename);
    void convert(String filename, boolean convertToXwikiSyntax);
    */
}
