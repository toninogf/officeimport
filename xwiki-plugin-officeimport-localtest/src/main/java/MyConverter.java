import java.io.BufferedWriter;
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

public class MyConverter
{
    OpenOfficeConnection connection;
    DocumentConverter converter;
    public MyConverter() {
        connect();
    }
    
    public MyConverter(int port) {
        connect(port);
    }
    
    private void connect() {
        int port = SocketOpenOfficeConnection.DEFAULT_PORT;
        connect(port);
    }
    
    private void connect(int port) {
        connection = new SocketOpenOfficeConnection(port);
        try {
            connection.connect();
        } catch (ConnectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        converter = new OpenOfficeDocumentConverter(connection);
    }
    
    private void disconnect() {
        connection.disconnect();
        converter = null;
    }
    
    public void convert(String inputFilename)
    {
        File inputFile = new File(inputFilename);
        String outputFilename = inputFilename.substring(0, inputFilename.lastIndexOf(".")) + ".html";
        File outputFile = new File(outputFilename);
        converter.convert(inputFile, outputFile);
    }
    
    public void convert(InputStream inputStream, String extension) 
    {
        DefaultDocumentFormatRegistry formatRegistry = new DefaultDocumentFormatRegistry();
        DocumentFormat inputFormat = formatRegistry.getFormatByFileExtension(extension);
        DocumentFormat outputFormat = formatRegistry.getFormatByFileExtension("html");
        OutputStream outputStream = null;
        converter.convert(inputStream, inputFormat, outputStream, outputFormat);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        //TODO how to read html from outputstream
    }
}
