<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>
</head>
<body>
<%
	String user = request.getParameter("username");
	String pass = request.getParameter("password");

	// needs to change to db value
	if (user.equals("admin") && pass.equals("admin")) {
		session.setAttribute("username", user);
		response.sendRedirect("add_data.jsp");
	}
	else 
		response.sendRedirect("loginError.jsp");
%>
</body>
</html>