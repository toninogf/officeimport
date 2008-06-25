package cn.org.tcse.soapexpress.spltif;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: RegisterMap
 *
 */
 public class RegisterMap extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   private static MapRegister mapRegister = new MapRegister("");
   @Override
   public void init() {
   }
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RegisterMap() {
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
		String applicationName = request.getParameter("applicationName");
		String serviceFlowName  = request.getParameter("serviceFlowName");
		String toolId = request.getParameter("toolId");
		mapRegister.registerMap(applicationName, toolId, serviceFlowName);
		
		PrintWriter out = response.getWriter();
		out.println("<p>register map success!</p>");
		out.println("<p><a href=\"Map.jsp\">service flow</a></p>");
	}   	  	    
}