<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="tool" action="RegisterTool" method="post">
<p>Event Type: <input type="text" size="50" name="eventTypeString"/></p>
<p>Object Type: <input type="text" size="50" name="objectTypeString"/></p>
<p>Product: <input type="text" size="50" name="productString"/></p>
<p>Product Version: <input type="text" size="50" name="productVersionString"/></p>
<p>Product Instance: <input type="text" size="50" name="productInstanceString"/></p>
<p><input type="submit" value="register"/></p>
</form>
</body>
</html>