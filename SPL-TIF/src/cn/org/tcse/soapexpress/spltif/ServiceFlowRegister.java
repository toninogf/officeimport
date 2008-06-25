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

public class ServiceFlowRegister {
	private static Map<String, Document> serviceFlows = new HashMap<String, Document>();
	private String path;
	public ServiceFlowRegister(String filepath) {
		this.path = filepath;
		if(path==null || path.equals("")) {
			System.err.println("service flow repository is illegal. will use default repository!");
			this.path = "serviceflow";
		}
		init(path);
	}

	private boolean init(String filepath) {
		File serviceflowFile = new File(filepath);
		if (!serviceflowFile.exists()) {
			System.err.println("the service flow file path " + filepath
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
		File[] serviceflowFiles = serviceflowFile.listFiles(fileFilter);
		for (File flowFile : serviceflowFiles) {
			String filename = flowFile.getName();
			registerServiceFlow(filename.substring(0, filename.indexOf(".")),
					createServiceFlowDocumentFromFile(flowFile));
		}
		return true;
	}

	private void registerServiceFlow(String serviceFlowName, Document toolDocument) {
		serviceFlows.put(serviceFlowName, toolDocument);
	}

	private Document createServiceFlowDocumentFromFile(File file) {
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

	private void writeServiceFlowDocumentToFile(Document doc, File docFile) {
		try {
			System.out.println(docFile.getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

	public void registerServiceFlow(String serviceFlowNameString,
			String endpointString, String operationString) {
		Document serviceFlow = createServiceFlowDocument(serviceFlowNameString,
				endpointString, operationString);
		serviceFlows.put(serviceFlowNameString, serviceFlow);
		File docFile = new File(this.path, serviceFlowNameString + ".xml");
		if(docFile.exists()) {
			System.err.println("the service flow is registered");
			return;
		}
		writeServiceFlowDocumentToFile(serviceFlow, docFile);
	}

	public boolean removeServiceFlow(String serviceFlowName) {
		serviceFlows.remove(serviceFlowName);
		File docFile = new File(this.path, serviceFlowName + ".xml");
		if(!docFile.exists() || !docFile.delete()) {
			System.err.println("can't delete Service Flow file");
			return false;
		}
		return true;
	}

	private Document createServiceFlowDocument(String serviceFlowNameString,
			String endpointString, String operationString) {
		Document doc = new Document();
		Element root = new Element("ServiceFlow");
		doc.setRootElement(root);

		Element serviceFlowName = new Element("ServiceFlowName");
		serviceFlowName.setText(serviceFlowNameString);
		root.addContent(serviceFlowName);

		Element endpoint = new Element("Endpoint");
		endpoint.setText(endpointString+"?"+operationString);
		root.addContent(endpoint);
		
		return doc;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		XMLOutputter outp = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		outp.setFormat(format);
		Iterator<Document> it = serviceFlows.values().iterator();
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

	public Map<String, Document> getServiceFlows() {
		return serviceFlows;
	}

	public Document getServiceFlow(String serviceFlowName) {
		return serviceFlows.get(serviceFlowName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceFlowRegister serviceFlowRegister = new ServiceFlowRegister("");
		System.out.println(serviceFlowRegister);
		serviceFlowRegister.registerServiceFlow("a", "http://b", "c");
		System.out.println(serviceFlowRegister);
		serviceFlowRegister.removeServiceFlow("a");
	}
}
