<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${info}</title>
</head>
<body>
	
<!-- deprecated just because it defines the presentation instead of describing content- keep it -->
<center>
	<br>
	<br>
	<br>
	<br>	
	<br>
	<br>
	
	
	<form method="post" action="login-check">
		<br>
		<td>username</td>
		<br>
		<td><input type="text" name="text"></td>
		<br>
				
		<br>
		<td>password</td>
		<br>
		<td><input type="password" name="password"></td>
		<br>
				
		<br>
		<td><input type="submit" value="log in"></td>
		<br>
	</form>
	
	<br>
	<form method="get" action="guest">
		<input type="submit" value="log in as guest">
	</form>
</center>	

</body>
</html>