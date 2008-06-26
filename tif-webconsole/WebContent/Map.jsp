<%@ page language="java"
	import="cn.org.tcse.soapexpress.tif.*,java.util.*,cn.org.tcse.soapexpress.tif.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Event-Actoin Map</title>
</head>
<body>
<h3>Add a Event-Action Map</h3>
<form name="deployMap" action="Map?action=deploy" method="post">
<table>
<tr>
	<td>Application Name:</td>
	<td><input type="text" size="50" name="appName" /></td>
</tr>
<tr>
	<td>Event-Tool:</td>
	<td>
		<select name="eventId">
		<%
			Store store = Store.getInstant("");
			Map<String, Event> events = store.getEvents();
			Iterator<String> it = events.keySet().iterator();
			while (it.hasNext()) {
				String eventId = it.next();
		%>
		<option value="<%=eventId%>"><%=eventId%></option>
		<%
			}
		%>
		</select>
	</td>
</tr>
<tr>
	<td>Action:</td>
	<td>
		<select name="serviceFlowName">
		<%
			Map<String, Action> actions = store.getActions();
			it = actions.keySet().iterator();
			while (it.hasNext()) {
				String serviceFlowName = it.next();
		%>
		<option value="<%=serviceFlowName%>"><%=serviceFlowName%></option>
		<%
			}
		%>
		</select>
	</td>
</tr>
</table>
<p><input type="submit" value="deploy" /></p>
</form>
<hr />
<h3>Existed Maps</h3>
<table border="1">
	<tr>
		<td>Application Name</td>
		<td>Event-Tool</td>
		<td>Action Service Flow Name</td>
		<td>Statu</td>
		<td>pause/resume</td>
		<td>undeploy</td>
	</tr>
	<%
		Map<String, EAMap> maps = store.getEamapMap();
		it = maps.keySet().iterator();
		while(it.hasNext()) {
			String appName = (String)it.next();
			EAMap map = store.getEAMaps(appName);
	%>
	<tr>
		<td><%=map.getApplicationName() %>
		<td><%=map.getEvent().getEventMatchName() %></td>
		<td><%=map.getAction().getServiceFlowName() %></td>
		<td><%=AdminClient.getStatus(appName)%></td>
	<%
		if(AdminClient.getStatus(appName).equals("Running")){
	%>
		<td><a href="Map?action=pause&appName=<%=appName %>">pause</a></td>
	<%  
		} else if(AdminClient.getStatus(appName).equals("Paused")) {
	%>	
		<td><a href="Map?action=resume&appName=<%=appName %>">resume</a></td>
	<%  
		} else {
	%>
		<td>error</td>
	<%
		}
	%>
		<td><a href="Map?action=undeploy&appName=<%=appName %>">undeploy</a></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>