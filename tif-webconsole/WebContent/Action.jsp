<%@ page language="java"
	import="cn.org.tcse.soapexpress.tif.*,java.util.*,cn.org.tcse.soapexpress.tif.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Action</title>
</head>
<body>
<form name="addaction" action="Action?action=add" method="post">
<h3>Add a action</h3>
<table>
<tr>
	<td>Service Flow Name(*):</td>
	<td><input type="text" size="50" name="serviceFlowName" /></td>
</tr>
<tr>
	<td>Endpoint:</td>
	<td><input type="text" size="50" name="endpoint" /></td>
</tr>
<tr>
	<td>Operation:</td>
	<td><input type="text" size="50" name="operation" /></td>
</tr>
</table>
<p><input type="submit" value="add action"/></p>
</form>
<hr />
<h3>Existed Actions</h3>
<table border="1">
	<tr>
		<td>Service Flow Name</td>
		<td>Endpoint</td>
		<td>Operation</td>
		<td></td>
	<%
		Store store = Store.getInstant("");
		Map<String, Action> actions = store.getActions();
		Iterator it = actions.keySet().iterator();
		while (it.hasNext()) {
			String serviceFlowName = (String) it.next();
			Action action = store.getAction(serviceFlowName);
	%>
	<tr>
		<td><%=action.getServiceFlowName() %>
		<td><%=action.getEndpoint() %></td>
		<td><%=action.getOperation() %></td>
		<td><a href="Action?action=delete&serviceFlowName=<%=serviceFlowName%>">delete</a></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>