package org.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.CustomerLoginModel;

/**
 * Servlet implementation class UpdatePasswordController
 */
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		
		String oldPass =request.getParameter("oldPass");
		
		String password =request.getParameter("pass1");
		String password2 =request.getParameter("pass2");

		HttpSession session = request.getSession(true);
		CustomerLoginModel m = new CustomerLoginModel("","");
		m =(CustomerLoginModel) session.getAttribute("currCusModel");
		
		if(!password.equals(password2))
		{
			session.setAttribute("displayMsg", "Password don't match ");
			response.sendRedirect("/bankApplication/changePassword.jsp");
		}
		else if(!m.get_loginDetails().get_password().equals(oldPass))
		{
			session.setAttribute("displayMsg", " old password is wrong  ");
			response.sendRedirect("/bankApplication/changePassword.jsp");
		}

		
		
		m.get_loginDetails().set_password(password);

		try {
			m.updatePassword();
			session.setAttribute("displayMsg", "update sucessful ");
			response.sendRedirect("/bankApplication/customer.html");
			
		} catch (Exception e) {
			System.out.println("something went wrong with changing password");
			// TODO: handle exception
		}

		
		
	}

}
