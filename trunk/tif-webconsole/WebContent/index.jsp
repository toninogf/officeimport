<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TIF Web Console</title>
</head>

<frameset rows="80,*">
	<frame name="top" src="top.jsp">
	<frameset cols="180,*">
		<frame name="lower_left" src="dir.jsp">
		<frame name="lower_right" src="welcome.jsp">
	</frameset>
</frameset>
<noframes>
<body>
很抱谦，您使用的浏览器不支持框架功能，请采用新版本的浏览器。
</body> 
</noframes>
</html>