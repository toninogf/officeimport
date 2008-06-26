package cn.org.tcse.soapexpress.tif;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.org.tcse.soapexpress.tif.model.Event;

/**
 * Servlet implementation class for Servlet: Event
 * 
 */
public class EventServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	private Store store;

	@Override
	public void init(ServletConfig config) {
		ServletContext context = config.getServletContext();
		String path = context.getRealPath("/WEB-INF");
		store = Store.getInstant(path);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public EventServlet() {
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
		String actionString = request.getParameter("action");
		if (actionString.equals("add")) {
			String eventMatchName = request.getParameter("eventMatchName");
			String eventType = request.getParameter("eventType");
			String objectType = request.getParameter("objectType");
			String product = request.getParameter("product");
			String productVersion = request.getParameter("productVersion");
			String productInstance = request.getParameter("productInstance");
			Event event = new Event(eventMatchName, eventType, objectType,
					product, productVersion, productInstance);
			store.addEvent(event);
		} else if (actionString.equals("delete")) {
			String eventId = request.getParameter("eventId");
			store.removeEvent(eventId);
		} 
		RequestDispatcher dispatcher = request.getRequestDispatcher("Event.jsp");
		dispatcher.forward(request, response);
	}
}