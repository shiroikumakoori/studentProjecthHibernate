<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                           <%@page language="java" import="java.util.*" %>
       <%@page language="java" import="org.bankModels.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/bankApplication/viewRangeTransaction" >
 <label>from</label> <input type="text" name="hi" >
 <label>from</label> <input type="datetime-local" name="from" >
  <label>to</label><input type="datetime-local" name="to" >
 	 <input type="submit">
 </form>
 
 <table>
		<%Iterator itr; 
		
			String name ="";
			String userName="";
			int amt = 0;
		%>
		
		<%
		  
		if( session.getAttribute("listOfCus") != null )
		{
			List<TransactionModel> listCus =(List<TransactionModel>) session.getAttribute("listOfCus");
			for(itr = listCus.iterator(); itr.hasNext();)
			{
				TransactionModel cus = ((TransactionModel)itr.next());
			
			%>
				
			
			<tr>
			
			<td><%=cus.get_dateTime()%>> </td>
			<td><%=cus.get_description() %> </td>
			<td><%=cus.get_credit()%> </td>
			<td><%=cus.get_debit() %> </td>
			
			<td>
				 
			
			</td>
			</tr>
		
			<%}%>
		<%}%>
	</table>
</body>
</html>