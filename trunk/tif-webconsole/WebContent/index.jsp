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
<h3>Sorry, but your web brower doesn't support frame. Please use a
brower supporting frame. <a	href="http://www.firefox.com">Firefox 3</a>
 recommended.</h3>
</body> 
</noframes>
</html>