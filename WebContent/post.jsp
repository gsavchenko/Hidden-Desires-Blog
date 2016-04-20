<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blog Post</title>
<link href="main.css" rel="stylesheet" />
</head>
<body>
	<div id="navbar">
		<a href="Show">Home</a>
		<a href="Write">Add Post</a>
	</div>
	<div id="thepost">
		<h1>Post # ${blogPost.blogId} on ${blogPost.date}</h1>
		<p>${blogPost.post}</p>
	</div>
	<div id="comments">
		Total Comments: ${blogPost.comments.size()}<br>
		<center>
		<c:forEach var="comment" items="${blogPost.comments}">
			<div class="comment">
				<c:out value="${comment}" />
			</div>
		</c:forEach>
		</center>
	</div>
	<div>
		<br> <br>
		<center>
			<form action="/BlogSite/PostController" method="POST">
				<input type="hidden" name="blogId" value="${blogPost.blogId}" />
				<textarea name="comment" id="comment" cols="50" rows="10"></textarea>
				<br> <input type="submit" value="Add Comment" />
			</form>
		</center>
	</div>
</body>
</html>