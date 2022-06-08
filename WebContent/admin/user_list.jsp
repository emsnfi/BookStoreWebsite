<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Users</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h1>Users Management</h1>
		<h3>
			<a href="user_form.jsp">Create new User</a>
		</h3>
	</div>


	<div align="center">
	<h4><i>${message}</i></h4>
	</div>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr> 
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>


			</tr>
			<!-- items 後面要等於 attribute 的名稱(servlet中的) -->
			<c:forEach var="user" items="${listUsers}" varStatus="status">
				<tr>
				<!-- 其 user 欄位名稱參照 User.java entity -->
					<td>${status.index+1}</td>
			<td>${user.userId}</td> 
					<td>${user.email}</td>
					<td>${user.fullname }</td>
					<td>
					<a href="">Edit</a>
					<a href = "">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>