package cn.org.tcse.soapexpress.spltif;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: RegisterServiceFlow
 *
 */
 public class RegisterServiceFlow extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RegisterServiceFlow() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serviceFlowNameString = request.getParameter("serviceFlowName");
		String endpointString  = request.getParameter("endpoint");
		String operationString = request.getParameter("operation");;
		ServiceFlowRegister serviceFlowRegister = new ServiceFlowRegister("");
		serviceFlowRegister.registerServiceFlow(serviceFlowNameString, endpointString, operationString);
		PrintWriter out = response.getWriter();
		out.println("<p>register service flow success!</p>");
		out.println("<p><a href=\"ServiceFlow.jsp\">service flow</a></p>");
	}   	  	    
}