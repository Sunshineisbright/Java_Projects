<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html dir="ltr" lang="en-US">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Cyberspace 个人后台管理系统</title>
		<link rel="shortcut icon" href=" img/favicon.ico" />
	<link rel="stylesheet" href="css/admin_style.css" type="text/css" />

	</head>

	<body>
		<div id="container">
			
			<form action="adminLoginServlet.do" method="post">
				<div class="login">Cyberspace 后台管理系统
				 <span style="color:red">${err}</span>
				</div>
				<div class="username-text">用户名:</div>
				<div class="password-text">密码:</div>
				<div class="username-field">
					<input type="text" name="admin_name"/>
				</div>
				<div class="password-field">
					<input type="password" name="admin_password"/>
				</div>
				<input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">记住用户名</label>
	
				<div class="forgot-usr-pwd"></div>
				<input type="submit" name="submit" value="GO" />
			</form>
		</div>

	</body>
</html>
