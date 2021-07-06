<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${info}</title>
</head>
<body>
	
<center>
	<br>
	<br>
	
	<h3>enter id & values to update</h3>
	<br>
	<br>
	
	<form method="post" action="finish-updating">
		<br>
		<td>id</td>
		<br>
		<td><input type="number" name="id" value="0"></td>
		<br>
	
		<br>
		<td>name</td>
		<br>
		<td><input type="text" name="name"></td>
		<br>
		
	<!--  	<br>
			<td>id</td>
			<br>
			<td><input type="number" name="address_id" value="0"></td>
			<br>	
		-->	
		
			<br>
			<td>street</td>
			<br>
			<td><input type="text" name="street"></td>
			<br>
			
			<br>
			<td>village</td>
			<br>
			<td><input type="text" name="village"></td>
			<br>
			
			<br>
			<td>zip</td>
			<br>
			<td><input type="text" name="zip"></td>
			<br>
				
		<br>
		<td>district</td>
		<br>
		<td><input type="text" name="district"></td>
		<br>
		
		<br>
		<td>region</td>
		<br>
		<td><input type="text" name="region"></td>
		<br>
		
		<br>
		<td>footprint</td>
		<br>
		<td><input type="text" name="footprint"></td>
		<br>
						
						
		<br>
		<td><input type="submit" value="update"></td>
		<br>
	</form>
	
</center>	

</body>
</html>