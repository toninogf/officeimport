import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.xwiki.rendering.block.XDOM;
import org.xwiki.rendering.parser.ParseException;
import org.xwiki.rendering.parser.Parser;
import org.xwiki.rendering.renderer.XWikiSyntaxRenderer;
import org.xwiki.rendering.wikimodel.parser.WikiModelXHTMLParser;


public class TestParser
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
    	String content = 
    			"<html>" +
    			"<body>" +
    			"<h1>abc</h1>" +
    			"<h2>title2</h2>" +
    			"<p>abcd</p>" +
    			"<p>adasdf</p>" +
    			"/n" +
    			"<p>seda</p>\n" +
    			"</body>\n" +
    			"</html>";
        Reader sourceReader = new StringReader(content);
        Parser parser = new WikiModelXHTMLParser();
        XDOM document = null;
        try {
            document = parser.parse(sourceReader);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        StringWriter sw = new StringWriter();
        XWikiSyntaxRenderer render = new XWikiSyntaxRenderer(sw);
        document.traverse(render);
        System.out.println(sw.toString());
    }

}