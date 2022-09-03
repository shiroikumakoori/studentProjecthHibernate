package org.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.CustomerLoginModel;

/**
 * Servlet implementation class CheckBalanceController
 */
public class CheckBalanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		HttpSession session = request.getSession(true);
		CustomerLoginModel t= ( CustomerLoginModel)(session.getAttribute("currCusModel"));
		int amt = t.get_amt();
		session.setAttribute("balance", amt);

					
		response.sendRedirect("/bankApplication/checkBalance.jsp");

		
	}

}
