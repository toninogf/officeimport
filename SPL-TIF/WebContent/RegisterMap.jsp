<%@ page language="java" import="cn.org.tcse.soapexpress.spltif.*, java.util.*, org.jdom.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="registerMap" action="RegisterMap" method="post">
<p>Application Name:<input type="text" size="50" name="applicationName" /></p>
<p>Event:
<select name="toolId">
<% 
	ToolRegister toolRegister = new ToolRegister("");
	Map<String, Document> tools = toolRegister.getTools();
	Iterator<String> it = tools.keySet().iterator();
	while(it.hasNext()) {
		String toolId = it.next();
%>
<option value="<%=toolId%>"><%=toolId%></option>
<% } %>
</select>
<p>Service Flow
<select name="serviceFlowName">
<% 
	ServiceFlowRegister serviceFlowRegister = new ServiceFlowRegister("");
	
	Map<String, Document> serviceFlows = serviceFlowRegister.getServiceFlows();
	Iterator<String> serviceFlowIt = serviceFlows.keySet().iterator();
	while(serviceFlowIt.hasNext()) {
		String serviceFlowName = serviceFlowIt.next();
%>
<option value="<%=serviceFlowName%>"><%=serviceFlowName%></option>
<% } %>
</select>
</p>
<p><input type="submit" value="register"/></p>
</form>
</body>
</html>