<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>hello  ${userName} </p>
	<div style="display :flex; flex-direction: column;">
		<a href="/carServiceSystem/regNewCar.html"> Register a new car  </a> 
		<br>
		<a href="/carServiceSystem/requestService.html"> Request for service </a>
		<a href="/carServiceSystem/StatusUpdateController"> Check Service status</a>
		
	</div>	
</body>
</html>