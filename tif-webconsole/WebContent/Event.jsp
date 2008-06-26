<%@ page language="java"
	import="cn.org.tcse.soapexpress.tif.*,java.util.*,cn.org.tcse.soapexpress.tif.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Event</title>
</head>
<body>
<form name="addevent" action="Event?action=add" method="post">
<h3>Add a event</h3>
<table>
<tr>
	<td>Event Match Name(*):</td>
	<td><input type="text" size="50" name="eventMatchName" /></td>
</tr>
<tr>
	<td>Event Type:</td>
	<td><input type="text" size="50" name="eventType" /></td>
</tr>
<tr>
	<td>Object Type:</td>
	<td><input type="text" size="50" name="objectType" /></td>
</tr>
<tr>
	<td>Product:</td>
	<td><input type="text" size="50" name="product" /></td>
</tr>
<tr>
	<td>Product Version:</td>
	<td><input type="text" size="50" name="productVersion" /></td>
</tr>
<tr>
	<td>Product Instance:</td>
	<td><input type="text" size="50" name="productInstance" /></td>
</tr>
</table>
<p><input type="submit" value="add event"/></p>
</form>
<hr />
<h3>Existed Events and Tools</h3>
<table border="1">
	<tr>
		<td>Event Match Name</td>
		<td>Event Type</td>
		<td>Object Type</td>
		<td>Product</td>
		<td>Product Version</td>
		<td>Product Instance</td>
		<td></td>
	<%
		Store store = Store.getInstant("");
		Map<String, Event> events = store.getEvents();
		Iterator it = events.keySet().iterator();
		while (it.hasNext()) {
			String eventId = (String) it.next();
			Event event = store.getEvent(eventId);
	%>
	<tr>
		<td><%=event.getEventMatchName()%>
		<td><%=event.getEventType()%></td>
		<td><%=event.getObjectType()%></td>
		<td><%=event.getProduct()%></td>
		<td><%=event.getProductVersion()%></td>
		<td><%=event.getProductInstance()%></td>
		<td><a href="Event?action=delete&eventId=<%=eventId%>">delete</a></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>