<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/bankApplication/UpdatePasswordController" >
		<p>type in your details</p>
		<input type="text" name="oldPass">
		<input type="text" name="pass1">
		<input type="text" name="pass2">
		<input type="submit" >
		<p>${displayMsg}</p>
	</form>
</body>
</html>