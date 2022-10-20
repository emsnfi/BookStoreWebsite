<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Administration</title>
</head>
<body>

<%-- 	<jsp:include page="header.jsp"></jsp:include> --%>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h1>Administrative Dashboard</h1>

		<!-- menu item  -->
		<div>
			<hr width="60%" />
			<h2>Quick action</h2>
			<b> <a href="create_book">New Book</a> <a href="create_user">New
					User</a> <a href="create_category">New Category</a> <a
				href="create_customer">New Customer</a>
			</b>
		</div>
	</div>


	<div align="center">
	<hr width="60%" />
		<h2>Recent Sales:</h2>
	</div>

	<div align="center">
	<hr width="60%" />
		<h2>Recent Reviews:</h2>
	</div>

	<div align="center">
	<hr width="60%" />
		<h2>Statistics:</h2>
	</div>



	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>