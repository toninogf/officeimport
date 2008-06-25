package cn.org.tcse.soapexpress.spltif;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class MapRegister {
	private static Map<String, Document> maps = new HashMap<String, Document>();
	private static ToolRegister toolRegister = new ToolRegister("");
	private static ServiceFlowRegister serviceFlowRegister = new ServiceFlowRegister(
			"");
	private String path;

	public MapRegister(String filepath) {
		this.path = filepath;
		if (path == null || path.equals("")) {
			System.err
					.println("map repository is illegal. will use default repository!");
			this.path = "map";
		}
		init(path);
		System.out.println(toolRegister);
		System.out.println(serviceFlowRegister);
	}

	private boolean init(String filepath) {
		File toolsFile = new File(filepath);
		if (!toolsFile.exists()) {
			System.err.println("the maps file path " + filepath
					+ " dosen't exist!");
			return false;
		}
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File f) {
				if (f.isFile()
						|| f.getName().substring(f.getName().lastIndexOf("."))
								.equals("xml")) {
					return true;
				}
				return false;
			}
		};
		File[] toolsFiles = toolsFile.listFiles(fileFilter);
		for (File toolFile : toolsFiles) {
			String filename = toolFile.getName();
			registerMap(filename.substring(0, filename.indexOf(".")),
					createMapDocumentFromFile(toolFile));
		}
		return true;
	}

	private void registerMap(String mapId, Document mapDocument) {
		maps.put(mapId, mapDocument);
	}

	private Document createMapDocumentFromFile(File file) {
		FileInputStream inputStream;
		Document doc = null;
		try {
			inputStream = new FileInputStream(file);
			SAXBuilder sb = new SAXBuilder();
			doc = sb.build(inputStream);
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	private void writeMapDocumentToFile(Document doc, File docFile) {
		XMLOutputter outp = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		outp.setFormat(format);
		try {
			OutputStream outputStream = new FileOutputStream(docFile);
			outp.output(doc, outputStream);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registerMap(String applicationName, String toolId,
			String serviceFlowName) {
		Document _map = createMapDocument(applicationName, toolId,
				serviceFlowName);
		String mapId = applicationName + "-" + toolId + "-" + serviceFlowName;
		maps.put(mapId, _map);
		File docFile = new File(this.path, mapId + ".xml");
		if (docFile.exists()) {
			System.err.println("the map is registered");
			return;
		}
		writeMapDocumentToFile(_map, docFile);
	}

	public boolean removeMap(String applicationName, String toolId,
			String serviceFlowName) {
		String mapId = applicationName + "-" + toolId + "-" + serviceFlowName;
		maps.remove(mapId);
		File docFile = new File(this.path, mapId + ".xml");
		if (!docFile.exists() || !docFile.delete()) {
			System.err.println("can't delete map file");
			return false;
		}
		return true;
	}

	private Document createMapDocument(String applicationNameString,
			String toolId, String serviceFlowName) {
		Document doc = new Document();
		Element root = new Element("ALFEventActionMap");
		doc.setRootElement(root);
		Namespace namespace = Namespace.getNamespace("xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		root.setNamespace(namespace);

		Element applicationName = new Element("ApplicationName");
		applicationName.setText(applicationNameString);
		root.addContent(applicationName);

		Element eventActions = new Element("EventActions");
		root.addContent(eventActions);

		Element eventAction = new Element("EventAction");
		eventActions.addContent(eventAction);

		Element eventMatch = (Element) toolRegister.getTool(toolId)
				.getRootElement().clone();
		eventMatch.detach();
		eventAction.addContent(eventMatch);

		Element action = new Element("Action");
		eventAction.addContent(action);

		Element serviceFlow = (Element) serviceFlowRegister.getServiceFlow(
				serviceFlowName).getRootElement().clone();
		serviceFlow.detach();
		action.addContent(serviceFlow);

		// doc.setRootElement(root);
		return doc;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		XMLOutputter outp = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		outp.setFormat(format);
		Iterator<Document> it = maps.values().iterator();
		while (it.hasNext()) {
			Document doc = it.next();
			try {
				outp.output(doc, System.out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "please see the console";
	}

	public Map<String, Document> getMaps() {
		return maps;
	}

	public Document getMap(String applicationName, String toolId,
			String serviceFlowName) {
		String mapId = applicationName + "-" + toolId + "-" + serviceFlowName;
		return maps.get(mapId);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MapRegister mapRegister = new MapRegister("");
		System.out.println(mapRegister);
		mapRegister.registerMap("TestConfig2", "build-ant", "ServiceFlowSOAP1");
		System.out.println(mapRegister);
		//mapRegister.removeMap("TestConfig2", "build-ant", "ServiceFlowSOAP1");
		System.out.println(toolRegister);
	}
}
