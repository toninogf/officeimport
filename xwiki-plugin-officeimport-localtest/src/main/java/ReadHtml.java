import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
    public static void removeAllTagsAndChildren(Element element, String tag) {
        List children = element.getChildren();
        for(int i=0; i<children.size(); i++) {
            Element child = (Element)children.get(i);
            if(child.getName().equals(tag)) {
                //System.out.println("parent: " + child.getParentElement().getName());
                //System.out.println(child.getName());
                child.detach();
                //the children.size is decreased after invoke child.detach(), so i--
                i--;
            }
            else {
                //System.out.println("parent: " + child.getParentElement().getName());
                //System.out.println(child.getName());
                removeAllTagsAndChildren(child, tag);
            }
        }
    }
    
    public static void removeAllTagsRetainChildren(Element element, String tag) {
        List children = element.getChildren();
        for(int i=0; i<children.size(); i++) {
            Element child = (Element)children.get(i);
            if(child.getName().equals(tag)) {
                //System.out.println("parent: " + child.getParentElement().getName());
                //System.out.println(child.getName());
                child.detach();
                i += child.getChildren().size();
                for(int j=0; j<child.getChildren().size(); j++)
                {
                    Element t = (Element)child.getChildren().get(j);
                    t.detach();
                    j--;
                    element.addContent(t);
                }
                //the children.size is decreased after invoke child.detach(), so i--
                i--;
            }
            else {
                //System.out.println("parent: " + child.getParentElement().getName());
                //System.out.println(child.getName());
                removeAllTagsRetainChildren(child, tag);
            }
        }
    }
    
    public static void removePinLi(Element element) {
        List children = element.getChildren();
        for(int i=0; i<children.size(); i++) {
            Element child = (Element)children.get(i);
            if(child.getName().equals("li")) {
                List tempChildren = child.getChildren();
                if(tempChildren.size() == 0) 
                    continue;
                Element first = (Element)tempChildren.get(0);
                if(first.getName().equals("p")) {
                    first.detach();
                    child.addContent(0, first.removeContent());
                }
                tempChildren = child.getChildren();
                for(int j=0; j<tempChildren.size(); j++) {
                    Element childchild = (Element)tempChildren.get(j);
                    removePinLi(child);
                }
            } else {
                removePinLi(child);
            }
        }
    }
    
    public static void updateImageTag(Element element) {
        List children = element.getChildren();
        for(int i=0; i<children.size(); i++) {
            Element child = (Element)children.get(i);
            if(child.getName().equals("img")) {
                System.out.println("found a img ");
                StringBuffer sb = new StringBuffer();
                sb.append("{image:");
               
                if (child.getAttribute("src")!=null) {
                    String src = child.getAttribute("src").getValue();
                    sb.append(src);
                }

                if (child.getAttribute("width")!=null) {
                    String width = child.getAttribute("width").getValue();
                    sb.append("|width="+width);
                }
                
                if (child.getAttribute("height")!=null) {
                    String height = child.getAttribute("height").getValue();
                    sb.append("|height="+height);
                }
                
                if (child.getAttribute("alt")!=null) {
                    String alt = child.getAttribute("alt").getValue();
                    sb.append("|alt="+alt);
                }
                sb.append("}");
                String str = sb.toString();
                System.out.println(str);
                child.getParentElement().addContent(str);
                child.detach();
            } else {
                updateImageTag(child);
            }
            
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        File file = new File("src/main/resources/test.html");
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
        ReadHtml.removeAllTagsAndChildren(root, "head");
        ReadHtml.removePinLi(root);
        ReadHtml.updateImageTag(root);
        String result = ReadHtml.document2String(document);
        System.out.println(result);
    }

}
