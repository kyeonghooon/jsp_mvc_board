<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/signup.css">
</head>
<body>
	<div class="container">
		<h2>회원가입</h2>
		<form action="${page.Context.request.contextPath}/user/signup" method="post">
			<div class="form-group">
				<label for="username">Username : </label>
				<input type="text" name="username" id="username" value="고길동" required>
			</div>
			<div class="form-group">
				<label for="password">Password : </label>
				<input type="password" name="password" id="password" value="asd1234" required>
			</div>
			<div class="form-group">
				<label for="email">Email : </label>
				<input type="email" name="email" id="email" value="a@naver.com" required>
			</div>
			<div class="form-group">
				<input class="btn btn-primary" type="submit" value="회원가입" >
			</div>
		</form>
	</div>
</body>
</html>