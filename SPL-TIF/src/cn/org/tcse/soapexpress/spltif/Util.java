package cn.org.tcse.soapexpress.spltif;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Util {
	public static Document createNewToolDocument(
			String eventMatchNameString,
			String eventTypeString,
			String objectTypeString,
			String productString,
			String productVersionString,
			String productInstanceString) {
		Document doc = new Document();
		Element root = new Element("EventMatch");
		doc.setRootElement(root);
		
		Element eventMatchName = new Element("EventMatchName");
		eventMatchName.setText(eventMatchNameString);
		root.addContent(eventMatchName);
		
		Element eventType = new Element("EventType");
		eventType.setText(eventTypeString);
		root.addContent(eventType);
		
		Element objectType = new Element("ObjectType");
		objectType.setText(objectTypeString);
		root.addContent(objectType);
		
		Element product = new Element("ProductType");
		product.setText(productString);
		root.addContent(product);
		
		Element productVersion = new Element("ProductVersion");
		productVersion.setText(productVersionString);
		root.addContent(productVersion);
		
		Element productInstance = new Element("ProductInstance");
		productInstance.setText(productInstanceString);
		root.addContent(productInstance);
		
		
		XMLOutputter outp = new XMLOutputter();
		Format format=Format.getPrettyFormat();
		outp.setFormat(format);
		try {
			outp.output(doc,System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}
	
	public static Document createNewServie(
			String eventMatchNameString,
			String eventTypeString,
			String objectTypeString,
			String productString,
			String productVersionString,
			String productInstanceString) {
		Document doc = new Document();
		Element root = new Element("EventMatch");
		doc.setRootElement(root);
		
		Element eventMatchName = new Element("EventMatchName");
		eventMatchName.setText(eventMatchNameString);
		root.addContent(eventMatchName);
		
		Element eventType = new Element("EventType");
		eventType.setText(eventTypeString);
		root.addContent(eventType);
		
		Element objectType = new Element("ObjectType");
		objectType.setText(objectTypeString);
		root.addContent(objectType);
		
		Element product = new Element("ProductType");
		product.setText(productString);
		root.addContent(product);
		
		Element productVersion = new Element("ProductVersion");
		productVersion.setText(productVersionString);
		root.addContent(productVersion);
		
		Element productInstance = new Element("ProductInstance");
		productInstance.setText(productInstanceString);
		root.addContent(productInstance);
		
		
		XMLOutputter outp = new XMLOutputter();
		Format format=Format.getPrettyFormat();
		outp.setFormat(format);
		try {
			outp.output(doc,System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Util.createNewToolDocument("a", "b", "c", "d", "e", "f");
		
	}

}
