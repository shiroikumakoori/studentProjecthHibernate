<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%@page language="java" import="java.util.*" %>
       <%@page language="java" import="carServiceSystem.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/carServiceSystem/UpdateCustomerStatusController">

<table>
<%Iterator itr; 
	CarModel car;
	String ser ="";
	String status="";
%>
<%List<CustomerModel> listCus =(List<CustomerModel>) session.getAttribute("listOfCus");
for(itr = listCus.iterator(); itr.hasNext();)
{
	CustomerModel cus = ((CustomerModel)itr.next());
	
	car = (cus).get_car(); 
%>

<tr>
<td> <input name="userName" value="<%=cus.getName() %>"> </td>
	<% car = cus.get_car(); 
	if(car != null)
	{
		ser =car.get_serReq();
		status = Boolean.toString( car.is_status());
	}
	%>
<td><%=ser%></td>
<td><%=status %> </td>

<td> <input type="submit"> Update status to done</td>

</tr>
<%}%>
</table>
</form>
</body>
</html>