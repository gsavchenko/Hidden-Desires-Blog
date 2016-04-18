<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Web log Entry</title></head>

<body bgcolor="Ivory">

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

</body>
</html>