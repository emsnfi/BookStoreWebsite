<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h1>Create New User</h1>


	</div>


	<div align="center">


		<form action="create_user" method="post" onsubmit ="return validateFormInput()">



			<table>

				<tr>
					<td align="right"><label for="email">Email:</label></td>
					<td align="left"><input type="email" name="email" id="email" size="20" /></td>
				</tr>
				<tr>

					<td align="right"><label for="fullName">Full Name:</label></td>
					<td align="left"><input type="text" name="fullName" id="fullName" size="20" />
					</td>
				</tr>

				<tr>

					<td align="right"><label for="password">Password:</label></td>
					<td align="left"><input type="password" name="password" id="password"
						size="20" /></td>
 

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