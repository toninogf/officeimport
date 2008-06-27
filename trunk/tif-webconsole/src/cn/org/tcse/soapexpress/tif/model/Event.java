package cn.org.tcse.soapexpress.tif.model;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import cn.org.tcse.soapexpress.tif.Util;

public class Event {
	private String eventMatchName;
	private String eventType;
	private String objectType;
	private String product;
	private String productVersion;
	private String productInstance;
	
	private Document doc;
	
	private Event() {
		
	}
	
	public Event(String eventMatchName, String eventType, String objectType,
			String product, String productVersion, String productInstance) {
		this.eventMatchName = eventMatchName;
		this.eventType = eventType;
		this.objectType = objectType;
		this.product = product;
		this.productVersion = productVersion;
		this.productInstance = productInstance;
		this.doc = this.toDocument();
	}

	private Document toDocument() {
		Document doc = new Document();
		Element root = new Element("EventMatch");
		doc.setRootElement(root);

		Element eventMatchNameElement = new Element("EventMatchName");
		eventMatchNameElement.setText(eventMatchName);
		root.addContent(eventMatchNameElement);

		Element eventTypeElement = new Element("EventType");
		eventTypeElement.setText(eventType);
		root.addContent(eventTypeElement);

		Element objectTypeElement = new Element("ObjectType");
		objectTypeElement.setText(objectType);
		root.addContent(objectTypeElement);

		Element productElement = new Element("Product");
		productElement.setText(product);
		root.addContent(productElement);

		Element productVersionElement = new Element("ProductVersion");
		productVersionElement.setText(productVersion);
		root.addContent(productVersionElement);

		Element productInstanceElement = new Element("ProductInstance");
		productInstanceElement.setText(productInstance);
		root.addContent(productInstanceElement);
		return doc;
	}
	
	public String toString() {
		return Util.document2String(this.doc);
	}
	
	public static Event fromDocument(Document doc) {
		Element root = doc.getRootElement();
		Event event = new Event();
		event.doc =doc;
		List<Element> elements = root.getChildren();
		Iterator<Element> it = elements.iterator();
		while(it.hasNext()) {
			Element element = it.next();
			if(element.getName().equals("EventMatchName")) {
				String eventMatchName = element.getValue();
				event.eventMatchName = eventMatchName.trim();
			} else if(element.getName().equals("EventType")) {
				String eventType = element.getValue();
				event.eventType = eventType.trim();
			} else if(element.getName().equals("ObjectType")) {
				String objectType = element.getValue();
				event.objectType = objectType.trim();
			} else if(element.getName().equals("Product")) {
				String product = element.getValue();
				event.product = product.trim();
			} else if(element.getName().equals("ProductInstance")) {
				String productInstance = element.getValue();
				event.productInstance = productInstance.trim();
			} else if(element.getName().equals("ProductVersion")) {
				String productVersion = element.getValue();
				event.productVersion = productVersion.trim();
			} else {
				System.err.println("[Error] error when Event fromDocument");
				return null;
			}
		}
		return event;
	}
	
	public static Event fromFile(File file) {
		Document document = Util.buildDocumentFromFile(file);
		return fromDocument(document);
	}

	public void toFile(File file) {
		File parent = file.getParentFile();
		if(!parent.exists()) {
			parent.mkdirs();
		}
		Util.document2File(doc, file);
	}

	
	public String getEventMatchName() {
		return eventMatchName;
	}

	public String getEventType() {
		return eventType;
	}

	public String getObjectType() {
		return objectType;
	}
	
	public String getProduct() {
		return product;
	}
	
	public String getProductVersion() {
		return productVersion;
	}
	
	public String getProductInstance() {
		return productInstance;
	}

	public Document getDoc() {
		return doc;
	}
	
	public static void main(String[] args) {
		File file = new File("event/build-ant.xml");
		System.out.println(file.getAbsolutePath());
		Event test = Event.fromFile(file);
		System.out.println(test);
	}
	
}
