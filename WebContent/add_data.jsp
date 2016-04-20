<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Web log Entry</title>
<link href="main.css" rel="stylesheet"/>
</head>

<body bgcolor="Ivory">
<div id="navbar">
		<a href="Show">Home</a>
		<a href="Write">Add Post</a>
	</div>
<form action="Write">
Log Entry:<br>
<%
        String data = request.getParameter("data");
%>

<textarea name="data" rows="10" cols="60">
<%= data != null ? data : "" %></textarea>
<br>
Password: <input type="text" name="password" value="" size="30">
<br>
<input type="submit" value="Submit">
</form>
<a href="Show" accesskey="1" title="">View Entries on Main blog page</a>
</body>
</html>