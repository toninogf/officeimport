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
		event.setDoc(doc);
		List<Element> elements = root.getChildren();
		Iterator<Element> it = elements.iterator();
		while(it.hasNext()) {
			Element element = it.next();
			if(element.getName().equals("EventMatchName")) {
				String eventMatchName = element.getValue();
				event.setEventMatchName(eventMatchName.trim());
			} else if(element.getName().equals("EventType")) {
				String eventType = element.getValue();
				event.setEventType(eventType.trim());
			} else if(element.getName().equals("ObjectType")) {
				String objectType = element.getValue();
				event.setObjectType(objectType.trim());
			} else if(element.getName().equals("Product")) {
				String product = element.getValue();
				event.setProduct(product.trim());
			} else if(element.getName().equals("ProductInstance")) {
				String productInstance = element.getValue();
				event.setProductInstance(productInstance.trim());
			} else if(element.getName().equals("ProductVersion")) {
				String productVersion = element.getValue();
				event.setProductVersion(productVersion.trim());
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

	public void setEventMatchName(String eventMatchName) {
		this.eventMatchName = eventMatchName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public String getProductInstance() {
		return productInstance;
	}

	public void setProductInstance(String productInstance) {
		this.productInstance = productInstance;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
	public static void main(String[] args) {
		File file = new File("event/build-ant.xml");
		System.out.println(file.getAbsolutePath());
		Event test = Event.fromFile(file);
		System.out.println(test);
	}
	
}
