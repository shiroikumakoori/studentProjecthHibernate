<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/bankApplication/ApplyLoanController" >
		<p>loan to apply and amount to loan</p> 
		<select name=selectedLoan>
		  <option value="P">Personal loan</option>
		  <option value="H">House loan</option>
		  <option value="V">Vechile loan </option>
		</select>
		<input type="text" name="amount">

		<input type="submit" >
		<p>${displayMsg} </p>
	</form>
</body>
</html>