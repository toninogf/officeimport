package cn.org.tcse.soapexpress.tif;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.org.tcse.soapexpress.tif.model.Action;

/**
 * Servlet implementation class for Servlet: Action
 *
 */
 public class ActionServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   private static Logger logger = Logger.getLogger(ActionServlet.class);
   private Store store;
   @Override
   public void init(ServletConfig config) {
	   logger.info("init store in Action Servlet");
	   ServletContext context = config.getServletContext();
	   String path = context.getRealPath("/WEB-INF");
	   store = Store.getInstance(path);
   }
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ActionServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("actionServlet doGet");
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("actionServlet doPost");
		String actionString = request.getParameter("action");
		String serviceFlowName = request.getParameter("serviceFlowName");
		if(actionString.equals("add")) {
			String endpoint  = request.getParameter("endpoint");
			String operation = request.getParameter("operation");
			Action action = new Action(serviceFlowName, endpoint, operation);
			store.addAction(action);
		} else if (actionString.equals("delete")) {
			store.removeAction(serviceFlowName);
		}
		logger.info("redirect to Action.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("Action.jsp");
		dispatcher.forward(request, response);
	}   	  	    
}