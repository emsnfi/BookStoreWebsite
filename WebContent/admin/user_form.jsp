<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 為引用 jstl  -->	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

<!-- 判斷是 create mode 還是 edit mode  -->
	<div align="center">
		<h1>
			<c:if test="${user != null }">
				Edit User
				
			</c:if>
		<c:if test="${user==null}">
			Create New User
			</c:if>
		</h1>


	</div>


	<div align="center">
		<c:if test="${user != null }">
			<form action="update_user" method="post" onsubmit ="return validateFormInput()">
			<input type="hidden" name="userId" value = "${user.userId}"> 
		
				
		</c:if>
		 
		<c:if test="${user == null }">
		
			<form action="create_user" method="post" onsubmit ="return validateFormInput()">
		
		</c:if>


			<table>

				<tr>
					<td align="right"><label for="email">Email:</label></td>
					<td align="left"><input type="email" name="email" id="email" size="20" value="${user.email}" /></td>
				</tr>
				<tr>

					<td align="right"><label for="fullName">Full Name:</label></td>
					<td align="left"><input type="text" name="fullName" id="fullName" size="20" value="${user.fullname}"/>
					</td>
				</tr>

				<tr>

					<td align="right"><label for="password">Password:</label></td>
					<td align="left"><input type="password" name="password" id="password"
						size="20" value="${user.password}"/></td>
 

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
	var fieldEmail = document.getElementById("email");
	var fieldFullName = document.getElementById("fullName");
	var password = document.getElementById("password");
	
	if(fieldEmail.value.length == 0){
		alert("Email is required!");
		fieldEmail.focus();
		return false;
	
	}
	
	if(fieldFullName .value.length == 0){
		alert("Full Name is required!");
		fieldFullName.focus();
		return false;
	
	}
	
	if(password.value.length == 0){
		alert("Password is required!");
		password.focus();
		return false;
	
	}
	return true;
	
}


</script>


</html>