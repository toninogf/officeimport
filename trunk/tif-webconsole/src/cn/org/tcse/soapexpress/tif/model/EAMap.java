package cn.org.tcse.soapexpress.tif.model;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import cn.org.tcse.soapexpress.tif.Util;

public class EAMap {
	private String applicationName;
	private Event event;
	private Action action;
	private Document doc;

	public EAMap(String applicationName, Event event, Action action) {
		this.applicationName = applicationName;
		this.event = event;
		this.action = action;
		this.doc = toDocument();
	}

	private Document toDocument() {
		Document doc = new Document();
		Element root = new Element("ALFEventActionMap");
		doc.setRootElement(root);
		Namespace namespace = Namespace.getNamespace("xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		root.addNamespaceDeclaration(namespace);

		Element applicationNameElement = new Element("ApplicationName");
		applicationNameElement.setText(applicationName);
		root.addContent(applicationNameElement);

		Element eventActions = new Element("EventActions");
		root.addContent(eventActions);

		Element eventAction = new Element("EventAction");
		eventActions.addContent(eventAction);

		Element eventMatch = (Element) event.getDoc().getRootElement().clone();
		eventMatch.detach();
		eventAction.addContent(eventMatch);

		Element actionElement = new Element("Action");
		eventAction.addContent(actionElement);

		Element serviceFlow = (Element) action.getDoc().getRootElement()
				.clone();
		serviceFlow.detach();
		actionElement.addContent(serviceFlow);
		return doc;
	}

	public String toString() {
		return Util.document2String(this.doc);
	}

	public static EAMap fromDocument(Document doc) {
		Element root = doc.getRootElement();

		String applicationName = root.getChildText("ApplicationName");

		Element eventElement = (Element) root.getChild("EventActions")
				.getChild("EventAction").getChild("EventMatch").clone();
		eventElement.detach();
		Document eventDoc = new Document().setRootElement(eventElement);
		Event event = Event.fromDocument(eventDoc);

		Element actionElement = (Element) root.getChild("EventActions")
				.getChild("EventAction").getChild("Action").getChild(
						"ServiceFlow").clone();
		actionElement.detach();
		Document actionDoc = new Document().setRootElement(actionElement);
		Action action = Action.fromDocument(actionDoc);

		EAMap eamap = new EAMap(applicationName, event, action);
		eamap.doc = doc;

		return eamap;
	}

	public static EAMap fromFile(File file) {
		Document document = Util.buildDocumentFromFile(file);
		return fromDocument(document);
	}

	public void toFile(File file) {
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		Util.document2File(doc, file);
	}

	public Event getEvent() {
		return event;
	}

	public Action getAction() {
		return action;
	}

	public Document getDoc() {
		return doc;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public static void main(String[] args) {
		File actionfile = new File("action/ServiceFlowSOAP1.xml");
		System.out.println(actionfile.getAbsolutePath());
		Action actiontest = Action.fromFile(actionfile);
		System.out.println(actiontest);

		File eventfile = new File("event/build-ant.xml");
		System.out.println(eventfile.getAbsolutePath());
		Event eventtest = Event.fromFile(eventfile);
		System.out.println(eventtest);

		EAMap eamaptest = new EAMap("myapp", eventtest, actiontest);
		System.out.println(eamaptest);

		File eamapfile = new File(
				"map/TestConfig1-event-service-ServiceFlowSOAP1.xml");
		System.out.println(actionfile.getAbsolutePath());
		EAMap eamaptestfromfile = EAMap.fromFile(eamapfile);
		System.out.println(eamaptestfromfile);
	}

}
