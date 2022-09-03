package org.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.CustomerLoginModel;

/**
 * Servlet implementation class RegNewCusController
 */
public class RegNewCusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name =request.getParameter("name");
		String email =request.getParameter("email");
		String userName =request.getParameter("userName");
		String password =request.getParameter("password");

	
		CustomerLoginModel m = new CustomerLoginModel(userName,password);
		m.set_email(email);
		m.set_name(name);

		
		int res = m.registerAccount();
		HttpSession session = request.getSession(true);
		session.setAttribute("userName", userName);
//		session.setAttribute("resName", name);
//		session.setAttribute("resMarks1", marks1);
//		session.setAttribute("resMarks2", marks2);
//		session.setAttribute("resMarks3", marks3);
		
		if(res ==1)
		{
			System.out.println("created sucess");
			response.sendRedirect("/bankApplication/customer.html");
		}
		else  {
			System.out.println("create fail");
			response.sendRedirect("/bankApplication/regNewCus.html");
			//response.sendRedirect("/MvcModel/fail.html");
		}
		
		
		
	}

}
