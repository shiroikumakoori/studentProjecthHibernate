package org.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.CustomerLoginModel;

/**
 * Servlet implementation class TransferMoneyController
 */
public class TransferMoneyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		HttpSession session = request.getSession(true);

		int amt  = Integer.parseInt(request.getParameter("amount"));
		String targetedUserName  = request.getParameter("targetUserName");

//		AdminModel m  =(AdminModel) session.getAttribute("adminModelObject");
		
		CustomerLoginModel m= ( CustomerLoginModel)(session.getAttribute("currCusModel"));
		int total = m.get_amt();

		if(!(amt > total))
		{
			m.creditMoney(amt, targetedUserName);
		
			
			CustomerLoginModel t = new CustomerLoginModel("","");
			t.get_loginDetails().set_userName(targetedUserName);
			t.retriveDetails();
			t.debitMoney(amt, targetedUserName);
			session.setAttribute("displayMsg","Sucesfully transfer "+ amt +" to "+ targetedUserName);
		}
		else
		{
			session.setAttribute("displayMsg","fail to  transfer "+ amt +" to "+ targetedUserName);
			System.out.println("not enoguh money");
		}
				

		
		response.sendRedirect("/bankApplication/TransferMoney.jsp");

	}

}
