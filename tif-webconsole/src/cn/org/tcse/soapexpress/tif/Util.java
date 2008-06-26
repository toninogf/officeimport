package cn.org.tcse.soapexpress.tif;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Util {
	private static Logger logger = Logger.getLogger(Util.class);
	
	public static String document2String(Document doc) {
		XMLOutputter outp = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		outp.setFormat(format);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			outp.output(doc, outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputStream.toString();
	}
	
	public static Document buildDocumentFromFile(File file) {
		logger.info("build document from file: " + file.getAbsolutePath());
		FileInputStream inputStream;
		Document document = null;
		try {
			inputStream = new FileInputStream(file);
			SAXBuilder sb = new SAXBuilder();
			document = sb.build(inputStream);
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
		return document;
	}
	
	public static void document2File(Document doc, File file) {
		logger.info("write document to file: " + file.getAbsolutePath());
		XMLOutputter outp = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		outp.setFormat(format);
		try {
			OutputStream outputStream = new FileOutputStream(file);
			outp.output(doc, outputStream);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
