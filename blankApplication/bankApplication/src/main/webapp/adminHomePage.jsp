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
		<a href="/bankApplication/ViewAllCustomerController" > view customer</a>
		<a href="/bankApplication/ViewAllPendingLoansController" > view pending loans</a>

</div>
</body>
</html>