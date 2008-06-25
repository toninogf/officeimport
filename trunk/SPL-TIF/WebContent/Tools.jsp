<%@ page language="java"
	import="cn.org.tcse.soapexpress.spltif.*,java.util.*,org.jdom.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tool</title>
</head>
<body>
<p><a href="RegisterTool.jsp">register tool</a></p>
<hr />
<p>Existed Tools</p>
<table>
	<%
		ToolRegister toolsRegister = new ToolRegister("");
		Map<String, Document> tools = toolsRegister.getTools();
		Iterator it = tools.keySet().iterator();
		while(it.hasNext()) {
			String toolId = (String)it.next();
	%>
	<tr>
		<td><%=toolId %></td>
		<td><a href="">delete</a></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>