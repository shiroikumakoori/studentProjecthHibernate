package org.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.CustomerLoginModel;

/**
 * Servlet implementation class CustomerWithdrawMoneyController
 */
public class CustomerWithdrawMoneyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		HttpSession session = request.getSession(true);
		CustomerLoginModel t= ( CustomerLoginModel)(session.getAttribute("currCusModel"));
		int amt  = Integer.parseInt(request.getParameter("amount"));


		int balance =t.get_amt();
		
		if(amt > balance)
		{
			System.out.println("unable to withdraw ");

		}
		else
		{
			System.out.println("withdrawn ");
			//this part is wrong 
			t.set_amt(amt);
			t.addTransaction(false, "Withdraw money from self");
			t.set_amt(balance-amt);
			t.updateMoney();
			session.setAttribute("displayMsg","withdrawn");
			response.sendRedirect("/bankApplication/withdrawDepResult.jsp");
		}
	}

}
