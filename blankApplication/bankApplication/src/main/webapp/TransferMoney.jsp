<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="bankApplicationTransferMoneyController" >
		<p>type amount to withdraw</p> 
		<label>person to transfer to</label><input type="text" name="targetUserName">
		<label>amount to transfer </label> <input type="text" name="amount">
		<p>${displayMsg }</p>

		<input type="submit" >
	</form>
</body>
</html>