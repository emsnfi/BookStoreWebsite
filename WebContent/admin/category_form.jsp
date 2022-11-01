<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 為引用 jstl  -->	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="" rel="stylesheet">
<title>Create New Category</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

<!-- 判斷是 create mode 還是 edit mode  -->
	<div align="center">
		<h1>
			<c:if test="${category != null }">
				Edit Category
				
			</c:if>
		<c:if test="${category==null}">
			Create New Category
			</c:if>
		</h1>


	</div>


	<div align="center">
		<c:if test="${category != null }">
			<form action="update_category" method="post" onsubmit ="return validateFormInput()">
			<input type="hidden" name="categoryId" value = "${category.categoryId}"> 
		
				
		</c:if>
		 
		<c:if test="${category == null }">
		
			<form action="create_category" method="post" onsubmit ="return validateFormInput()">
		
		</c:if>


			<table>

				<tr>

					<td align="right"><label for="name">Name:</label></td>
					<td align="left"><input type="text" name="name" id="name" size="20" value="${category.name}"/>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="Save" />
					<input type="button" value="Cancel" onclick="javascript:history.go(-1);"/>
					<!-- cancel 的話 就回到前一頁 -->
					</td>

				</tr>

			</table>
		</form>

	</div>



	<jsp:include page="footer.jsp"></jsp:include>


</body>

<script type="text/javascript">
/* validate input field */

function validateFormInput(){
	/* var fieldEmail = document.getElementById("email"); */
	var fieldName = document.getElementById("name");
	/* var password = document.getElementById("password"); */

	
	if(fieldName.value.length == 0){
		alert("Category name is required!");
		fieldName.focus(); //
		return false;
	
	}
	
	/* return true;/*  */
	
}


</script>


</html>