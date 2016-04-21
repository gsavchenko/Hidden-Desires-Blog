<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Web log Entry</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<% try {
	String user = (String)session.getAttribute("username");
	if (!user.equals(null) && !user.equals("")) {
		// no need to redirect, cuz they are logged in
	} else {
		//send them packin...
		response.sendRedirect("login.jsp");
	}
} catch(Exception ex) { response.sendRedirect("login.jsp"); }
%>
<div id="navbar">
		<a class="boxme" href="Show">Home</a>
		<a class="boxme" href="Write">Add Post</a>
		<a class="boxme" href="Logout.jsp">Logout</a>
	</div>
<h1>Log Entry:</h1>
<form action="Write" method="POST">
<br>
<%
        String data = request.getParameter("data");
%>

<textarea name="data" rows="10" cols="60">
<%= data != null ? data : "" %></textarea>
<br>
Password: <input type="text" name="password" value="" size="30">
<br>
<input type="submit" value="Submit">
</form> <br>
<a href="Show" accesskey="1" title=""><img alt="Link to main page" src="http://www.montgomerycountymd.gov/OCP/Resources/Images/hoa.png"/></a>
</body>
</html>