package org.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.CustomerLoginModel;

/**
 * Servlet implementation class CustomerDepositMoneyController
 */
public class CustomerDepositMoneyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		HttpSession session = request.getSession(true);

		int amt  = Integer.parseInt(request.getParameter("amount"));

//		AdminModel m  =(AdminModel) session.getAttribute("adminModelObject");
		
		CustomerLoginModel t= ( CustomerLoginModel)(session.getAttribute("currCusModel"));
		int balance =t.get_amt();
		
		t.set_amt(balance+amt);
		t.updateMoney();
		t.addTransaction(true, "depoist from self");
		

		System.out.println("deposit ");
		session.setAttribute("displayMsg","Deposit");
		response.sendRedirect("/bankApplication/withdrawDepResult.jsp");

	}

}
