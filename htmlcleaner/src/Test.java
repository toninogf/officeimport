import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.htmlcleaner.HtmlCleaner;

/**
 * @author: Vladimir Nikic
 * Date: Apr 13, 2007
 */
public class Test {

    public static void main(String[] args) throws IOException, ParserConfigurationException {
        HtmlCleaner cleaner = new HtmlCleaner(new File("c:/temp/a.html"));
        cleaner.setUseCdataForScriptAndStyle(false);
//        cleaner.setTranslateSpecialEntities(false);
//        HtmlCleaner cleaner = new HtmlCleaner("<body><table><td>&hello&nbsp;&nbsp;world");
//        cleaner.setAllowHtmlInsideAttributes(false);
//        cleaner.setUseEmptyElementTags(false);
//        cleaner.setAdvancedXmlEscape(true);
//        cleaner.setRecognizeUnicodeChars(true);
//        cleaner.setPruneTags("div, p,");
//        cleaner.setNamespacesAware(false);
//        cleaner.setIgnoreQuestAndExclam(true);
//        cleaner.setRecognizeUnicodeChars(false);
        cleaner.clean();
//        System.out.println("--->" + cleaner.getPrettyXmlAsString());

//        org.w3c.dom.Document document=cleaner.createDOM(false);
//        OutputFormat format = new OutputFormat(document);
//        format.setIndenting(true);
//
//        StringWriter writer = new StringWriter(500);
//        XMLSerializer serializer = new XMLSerializer(writer, format);
//
//        try {
//            serializer.serialize(document);
//            System.out.println(writer.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        cleaner.writePrettyXmlToFile("c:/temp/out.xml", "Windows-1251");

//        cleaner.writeCompactXmlToFile("c:/temp/out.xml", "UTF-8");
//        cleaner.writeBrowserCompactXmlToFile("c:/temp/out.xml", "UTF-8");
//        cleaner.writeXmlToFile("c:/temp/out.xml", "UTF-8");
//        cleaner.writePrettyXmlToFile("c:/temp/out.xml");
//        org.jdom.Document doc = cleaner.createJDom();

//        FileOutputStream fos = new FileOutputStream("c:/temp/out1.xml");
//        OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);
//        of.setIndent(4);
//        of.setIndenting(true);
//        XMLSerializer serializer = new XMLSerializer(fos,of);
//        serializer.asDOMSerializer();
//        serializer.serialize(doc);
//        fos.close();
//
//        String src = "<div>hello[&amp;][&nbsp;][&copy;]world</div><p><script>function f() {a = a && b;}</script></p>";
//        HtmlCleaner cleaner = new HtmlCleaner(src);
//        cleaner.setAdvancedXmlEscape(true);
//        cleaner.setRecognizeUnicodeChars(true);
//        cleaner.setTranslateSpecialEntities(false);
//        cleaner.setUseCdataForScriptAndStyle(true);
//        cleaner.clean();
//        org.w3c.dom.Document dom = cleaner.createDOM(true);
//
//        XMLSerializer serializer = new XMLSerializer();
//        // Insert your PipedOutputStream here instead of System.out!
//        StringWriter stringWriter = new StringWriter();
//        serializer.setOutputCharStream(stringWriter);
//        serializer.serialize(dom);
//        System.out.println(stringWriter.toString());
    }

}
