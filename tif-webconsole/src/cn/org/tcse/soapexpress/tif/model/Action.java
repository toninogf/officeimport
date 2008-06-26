package cn.org.tcse.soapexpress.tif.model;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import cn.org.tcse.soapexpress.tif.Util;

public class Action {
	private String serviceFlowName;
	private String endpoint;
	private String operation;
	private Document doc;
	
	private Action() {
		
	}
	
	public Action(String serviceFlowName, String endpoint, String operation) {
		this.serviceFlowName = serviceFlowName;
		this.endpoint = endpoint;
		this.operation = operation;
		this.doc = this.toDocument();
	}

	private Document toDocument() {
		Document doc = new Document();
		Element root = new Element("ServiceFlow");
		doc.setRootElement(root);

		Element serviceFlowNameElement = new Element("ServiceFlowName");
		serviceFlowNameElement.setText(serviceFlowName);
		root.addContent(serviceFlowNameElement);

		Element endpointElement = new Element("Endpoint");
		endpointElement.setText(endpoint + "?" + operation);
		root.addContent(endpointElement);
		
		return doc;
	}
	
	public String toString() {
		return Util.document2String(this.doc);
	}
	
	public static Action fromDocument(Document doc) {
		Element root = doc.getRootElement();
		Action action = new Action();
		action.setDoc(doc);
		List<Element> elements = root.getChildren();
		Iterator<Element> it = elements.iterator();
		while(it.hasNext()) {
			Element element = it.next();
			if(element.getName().equals("ServiceFlowName")) {
				String serviceFlowName = element.getValue();
				action.setServiceFlowName(serviceFlowName.trim());
			} else if(element.getName().equals("Endpoint")) {
				String[] temp = element.getValue().split("\\?");
				String endpoint = temp[0].trim();
				action.setEndpoint(endpoint);
				String operation = temp[1].trim();
				action.setOperation(operation);
			} else {
				System.err.println("error when Action fromDocument");
				return null;
			}
		}
		return action;
	}
	
	public static Action fromFile(File file) {
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

	public String getServiceFlowName() {
		return serviceFlowName;
	}

	public void setServiceFlowName(String serviceFlowName) {
		this.serviceFlowName = serviceFlowName;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
	public static void main(String[] args) {
		File file = new File("action/ServiceFlowSOAP1.xml");
		System.out.println(file.getAbsolutePath());
		Action test = Action.fromFile(file);
		System.out.println(test);
	}
}
