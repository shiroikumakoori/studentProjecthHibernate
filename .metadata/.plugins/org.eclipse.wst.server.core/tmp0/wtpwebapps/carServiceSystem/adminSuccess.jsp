<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="display :flex; flex-direction: column;">
		<p>hello  ${userName} </p>
		<a href="/carServiceSystem/ViewCustomerController" > view customer</a>
		<a href="/carServiceSystem/GetPendingRequestController" > view pending request</a>
		<a href="/carServiceSystem/selectCutomerToUpdateController" > update status </a>
</div>
		
</body>
</html>