<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Category</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h1>Category Management</h1>
		<h3>
			<a href="category_form.jsp">Create new Category</a>
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
				<th>Name</th>
				<th>Actions</th>


			</tr>
			<!-- items 後面要等於 attribute 的名稱(service中的) -->
			<!--  下方是從 service 傳過來的變數，唯一 list 物件 -->
			<!-- for each 將物件實例化名稱為 cat -->
			<!-- 下方使用呼叫的欄位為 在 entity 中命名的 -->
			
			<c:forEach var="cat" items="${listCategory}" varStatus="status">
				<tr>
				<!-- 其 user 欄位名稱參照 User.java entity -->
					<td>${status.index+1}</td>
					<td>${cat.categoryId}</td> 
					
					<td>${cat.name}</td>
					<td>
					<a href="edit_category?id=${cat.categoryId}">Edit</a>
					<a href = "javascript:confirmDelete(${cat.categoryId})">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>


	<jsp:include page="footer.jsp"></jsp:include>
	
	 
	<script>
	
	function confirmDelete(categoryId) {
		// 如果按下 ok 則會顯示 true
		const confirmbool = confirm("Are you sure you want to delete the category ID " + categoryId + "?");
		if(confirmbool){
			
			window.location = "delete_category?id=" + categoryId; // call java servlet call the property of the current windows to the url of java servlet
		}  
		

		
	}
	
	
	
	</script>
	
</body>
</html>