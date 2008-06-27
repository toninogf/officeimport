package cn.org.tcse.soapexpress.tif;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.org.tcse.soapexpress.tif.model.EAMap;

/**
 * Servlet implementation class for Servlet: EAMap
 *
 */
 public class EAMapServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   private Store store;
   private static Logger logger = Logger.getLogger(EAMapServlet.class);
   @Override
   public void init(ServletConfig config) {
	   logger.info("init store in EAMapServlet");
	   ServletContext context = config.getServletContext();
	   String path = context.getRealPath("/WEB-INF");
	   store = Store.getInstance(path);
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
		logger.debug("EAMapServlet doGet");
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("EAMapServlet doPost");
		String actionString = request.getParameter("action");
		String appName = request.getParameter("appName");
		
		if(actionString.equals("deploy")) {
			String serviceFlowName  = request.getParameter("serviceFlowName");
			String eventId = request.getParameter("eventId");
			EAMap map = new EAMap(
					appName,
					store.getEvent(eventId),
					store.getAction(serviceFlowName));
			logger.debug("the map content:\n" + map.toString());
			String r = AdminClient.deploy(map.toString());
			if (!r.toLowerCase().trim().equals("fail")) {
				store.addEAMap(map);
			}
		} else if (actionString.equals("undeploy")) {
			store.removeEAMap(appName);
			AdminClient.unDeploy(appName);
		} else if(actionString.equals("resume")) {
			AdminClient.resume(appName);
		} else if (actionString.equals("pause")) {
			AdminClient.pause(appName);
		}
		logger.debug("redirect to Map.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("Map.jsp");
		dispatcher.forward(request, response);
	}   	  	    
}