package org.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.CustomerLoginModel;

/**
 * Servlet implementation class CustomerLoginController
 */
public class CustomerLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		

		String userName =request.getParameter("userName");
		String password =request.getParameter("password");

	
		CustomerLoginModel m = new CustomerLoginModel("","");
		m.get_loginDetails().set_userName(userName);
		m.get_loginDetails().set_password(password);

		
		int res = m.isDetailsCorrect();
		HttpSession session = request.getSession(true);

//		session.setAttribute("resName", name);
//		session.setAttribute("resMarks1", marks1);
//		session.setAttribute("resMarks2", marks2);
//		session.setAttribute("resMarks3", marks3);
		
		if(res ==1)
		{
			System.out.println("okay");
			m.retriveDetails();
			session.setAttribute("userName", userName);
			session.setAttribute("currCusModel", m);
			response.sendRedirect("/bankApplication/cusHomePage.jsp");
		}
		else if(res ==-1) {
			System.out.println("wrong password");
			response.sendRedirect("/bankApplication/wrongPassword.html");
			//response.sendRedirect("/MvcModel/fail.html");
		}
		else
		{
			System.out.println("not found ");
			response.sendRedirect("/bankApplication/customer.html");
		}
		
	}

}
