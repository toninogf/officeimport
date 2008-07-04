import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ReadHtml
{
    public static String document2String(Document doc)
    {
        XMLOutputter outp = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        outp.setFormat(format);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outp.output(doc, outputStream);
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return outputStream.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        File file = new File("src/main/resources/htmltoclean.html");
        // TODO Auto-generated method stub
        SAXBuilder sb = new SAXBuilder();
        Document document = null;
        try {
            document = sb.build(file);
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        Element head = root.getChild("head");
        head.detach();
        Element body = root.getChild("body");
        body.detach();
        String result = ReadHtml.document2String(document);
        System.out.println(result);
    }

}
