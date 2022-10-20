<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<!-- edit user 後的 message 呈現 -->

<jsp:include page="header.jsp"></jsp:include>

<%-- <jsp:directive.include file="header.jsp"></jsp:include> --%>

<div align="center">
		<h3>${eromessage}</h3>
		
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>