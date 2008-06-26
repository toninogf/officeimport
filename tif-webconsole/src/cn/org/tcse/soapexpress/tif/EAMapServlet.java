package cn.org.tcse.soapexpress.tif;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.org.tcse.soapexpress.tif.model.EAMap;

/**
 * Servlet implementation class for Servlet: EAMap
 *
 */
 public class EAMapServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   private Store store;
   @Override
   public void init(ServletConfig config) {
	   ServletContext context = config.getServletContext();
	   String path = context.getRealPath("/WEB-INF");
	   store = Store.getInstant(path);
   }
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public EAMapServlet() {
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
		String actionString = request.getParameter("action");
		String appName = request.getParameter("appName");
		
		if(actionString.equals("deploy")) {
			System.out.println("[INFO] deploy");
			String serviceFlowName  = request.getParameter("serviceFlowName");
			String eventId = request.getParameter("eventId");
			EAMap map = new EAMap(
					appName,
					store.getEvent(eventId),
					store.getAction(serviceFlowName));
			store.addEAMap(map);
			//System.out.println(map.toString());
			AdminClient.deploy(map.toString());
		} else if (actionString.equals("undeploy")) {
			System.out.println("[INFO] undeploy");
			store.removeEAMap(appName);
			AdminClient.unDeploy(appName);
		} else if(actionString.equals("resume")) {
			System.out.println("[INFO] resume");
			AdminClient.resume(appName);
		} else if (actionString.equals("pause")) {
			System.out.println("[INFO] pause");
			AdminClient.pause(appName);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Map.jsp");
		dispatcher.forward(request, response);
	}   	  	    
}