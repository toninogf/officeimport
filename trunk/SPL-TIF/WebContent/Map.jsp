<%@ page language="java"
	import="cn.org.tcse.soapexpress.spltif.*,java.util.*,org.jdom.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>map</title>
</head>
<body>
<p>
<a href="RegisterMap.jsp">register map</a>
</p>
<hr />
<p>Existed Maps</p>
<table>
	<%
		MapRegister mapRegister = new MapRegister("");
		Map<String, Document> maps = mapRegister.getMaps();
		Iterator it = maps.keySet().iterator();
		while(it.hasNext()) {
			String mapId = (String)it.next();
	%>
	<tr>
		<td><%=mapId %></td>
		<td><a href="">delete</a></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>