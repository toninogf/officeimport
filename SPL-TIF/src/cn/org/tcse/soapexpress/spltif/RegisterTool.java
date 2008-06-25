package cn.org.tcse.soapexpress.spltif;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 * Servlet implementation class for Servlet: RegisterTool
 * 
 */
public class RegisterTool extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	static HashMap tools = new HashMap();

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RegisterTool() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String eventMatchNameString = "";
		String eventTypeString = request.getParameter("eventTypeString");
		String objectTypeString = request.getParameter("objectTypeString");
		String productString = request.getParameter("productString");
		String productVersionString = request.getParameter("productVersionString");
		String productInstanceString = request.getParameter("productInstanceString");

		ToolRegister toolRegister = new ToolRegister("");
		toolRegister.registerTool(eventMatchNameString, eventTypeString,
				objectTypeString, productString, productVersionString,
				productInstanceString);
		PrintWriter out = response.getWriter();
		out.println("<p>register tool success!</p>");
		out.println("<p><a href=\"Tools.jsp\">tools</a></p>");
	}
}