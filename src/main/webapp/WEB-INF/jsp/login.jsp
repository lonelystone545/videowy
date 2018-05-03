<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/login" method="post">
		<span>账号:</span><input type="text" name="username"><br>
		<span>密码:</span><input type="password" name="password"><br>
		<input type="hidden" name="gotoUrl" value="${gotoUrl }"/>
		<input type="submit" value="登录"> ${requestScope.loginError }...
	</form>
</body>
</html>