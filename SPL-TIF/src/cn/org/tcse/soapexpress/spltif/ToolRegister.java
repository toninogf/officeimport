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
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ToolRegister {
	private static Map<String, Document> tools = new HashMap<String, Document>();
	private String path;
	public ToolRegister(String filepath) {
		this.path = filepath;
		if(path==null || path.equals("")) {
			System.err.println("tool event repository is illegal. will use default repository!");
			this.path = "event-tool";
		}
		init(path);
	}

	private boolean init(String filepath) {
		File toolsFile = new File(filepath);
		if (!toolsFile.exists()) {
			System.err.println("the tools file path " + filepath
					+ " dosen't exist!");
			return false;
		}
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File f) {
				if(f.isFile() || f.getName().substring(f.getName().lastIndexOf(".")).equals("xml")) {
					return true;
				}
				return false;
			}
		};
		File[] toolsFiles = toolsFile.listFiles(fileFilter);
		for (File toolFile : toolsFiles) {
			String filename = toolFile.getName();
			registerTool(filename.substring(0, filename.indexOf(".")),
					createToolDocumentFromFile(toolFile));
		}
		return true;
	}

	private void registerTool(String toolId, Document toolDocument) {
		tools.put(toolId, toolDocument);
	}

	private Document createToolDocumentFromFile(File file) {
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

	private void writeToolDocumentToFile(Document doc, File docFile) {
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

	public void registerTool(String eventMatchNameString,
			String eventTypeString, String objectTypeString,
			String productString, String productVersionString,
			String productInstanceString) {
		Document tool = createToolDocument(eventMatchNameString,
				eventTypeString, objectTypeString, productString,
				productVersionString, productInstanceString);
		String toolId = eventTypeString + "-" + productString;
		tools.put(toolId, tool);
		File docFile = new File(this.path, toolId + ".xml");
		if(docFile.exists()) {
			System.err.println("the tool and the event is registered");
			return;
		}
		writeToolDocumentToFile(tool, docFile);
	}

	public boolean removeTool(String eventTypeString, String productString) {
		String toolId = eventTypeString + "-" + productString;
		tools.remove(toolId);
		File docFile = new File(this.path, toolId + ".xml");
		if(!docFile.exists() || !docFile.delete()) {
			System.err.println("can't delete tool file");
			return false;
		}
		return true;
	}

	private Document createToolDocument(String eventMatchNameString,
			String eventTypeString, String objectTypeString,
			String productString, String productVersionString,
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

		return doc;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		XMLOutputter outp = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		outp.setFormat(format);
		Iterator<Document> it = tools.values().iterator();
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

	public Map<String, Document> getTools() {
		return tools;
	}

	public Document getTool(String eventTypeString, String productString) {
		String toolId = eventTypeString + "-" + productString;
		return tools.get(toolId);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ToolRegister toolRegister = new ToolRegister("");
		System.out.println(toolRegister);
		toolRegister.registerTool("a", "b", "c", "d", "e", "f");
		System.out.println(toolRegister);
		toolRegister.removeTool("b", "d");
	}
}
