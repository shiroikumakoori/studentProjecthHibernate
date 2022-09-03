package org.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.AdminModel;
import org.bankModels.LoanModel;

/**
 * Servlet implementation class UpdateSelectedLoanController
 */
public class UpdateSelectedLoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(true);
		List<LoanModel> listCus =(List<LoanModel>) session.getAttribute("listOfCus");

		String selectedIndex = request.getParameter("selectedPersonIndex");
		LoanModel selected = listCus.get( Integer.parseInt( selectedIndex));
		
	
		String comment =request.getParameter("selectedComment");
		
		String status =request.getParameter("Choice");
		
		
		LoanModel l = new LoanModel();
		selected.set_status(status);
		
		selected.set_comments(comment);
		selected.updateLoan();


		
		//int res = m.isDetailsCorrect();

		//session.setAttribute("userName", userName);
		//session.setAttribute("adminModelObject", m);
//		session.setAttribute("resName", name);
//		session.setAttribute("resMarks1", marks1);
//		session.setAttribute("resMarks2", marks2);
//		session.setAttribute("resMarks3", marks3);
		response.sendRedirect("/bankApplication/viewAllPendingLoans.jsp");
//		if(res ==1)
//		{
//			System.out.println("okay");
//			response.sendRedirect("/carServiceSystem/adminHomePage.jsp");
//		}
//		else if(res ==-1) {
//			System.out.println("wrong password");
//			response.sendRedirect("/carServiceSystem/wrongPassword.html");
//			//response.sendRedirect("/MvcModel/fail.html");
//		}
//		else
//		{
//			System.out.println("correct");
//		}
		
		
	}
}
