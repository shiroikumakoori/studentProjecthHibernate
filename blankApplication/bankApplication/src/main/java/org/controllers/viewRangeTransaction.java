package org.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.naming.TransactionRef;
import org.bankModels.AdminModel;
import org.bankModels.CustomerLoginModel;
import org.bankModels.LoanModel;
import org.bankModels.TransactionModel;

/**
 * Servlet implementation class viewRangeTransaction
 */
public class viewRangeTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(true);
		String from  =(String) request.getParameter("from");
		String to  =(String) request.getParameter("to");
		System.out.println(from);
		CustomerLoginModel m =(CustomerLoginModel) session.getAttribute("currCusModel");
		TransactionModel t= new TransactionModel();
		
		from = "'"+from+"'";
		to = "'"+to+"'";
		
		
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<TransactionModel> listCus = m.retriveTransactionDetails( from , to) ;
		Iterator itr;
		for(int i = 0 ;i < listCus.size() ; i++)
		{
			listCus.get(i);
		}
		for(itr = listCus.iterator(); itr.hasNext();)
		{
			TransactionModel cus = ((TransactionModel)itr.next());
			System.out.println(cus.get_userName());
			System.out.println(cus.get_description());
			System.out.println(cus.get_credit());
			System.out.println(cus.get_debit());
					
			 ;
		}

	
		session.setAttribute("listOfCus",listCus);
		
		if(listCus != null)
		{
			System.out.println("list created ");
			session.setAttribute("listOfCus",listCus);
			response.sendRedirect("/bankApplication/viewTransaction.jsp");
		}		
		else  {
			System.out.println("Something went wrong ");
			response.sendRedirect("/Simple/viewAllPendingLoans.jsp");
			
		}

		
		
	}

}
