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
<%=cus.getName() %> </td>
<% car = cus.get_car(); 
if(car != null)
{
	ser =car.get_serReq();
	status = Boolean.toString( car.is_status());
}
%>
<td><%=ser%></td>
<td><%=status %> </td>
<%}%>

	<p> there ${ listOfCus } </p>
	
	<table>

    <c:forEach var="cus" items="${listOfCus}">
        <tr>
         <td>Employee blah: <c:out value="${cus._name}"/></td>
            <td>Employee Name: <c:out value="${cus._name}"/></td>
            <td>Employee service: <c:out value="${cus.get_car().get_serReq()}"/></td>  
            <td>Employee status: <c:out value="${cus.get_car().is_status()}"/></td>  
        </tr>
    </c:forEach>
</table>
	
</body>
</html>