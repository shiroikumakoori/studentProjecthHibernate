package org.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankModels.AdminModel;
import org.bankModels.CustomerLoginModel;

/**
 * Servlet implementation class ViewAllCustomerController
 */
public class ViewAllCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		HttpSession session = request.getSession(true);
		AdminModel m  =(AdminModel) session.getAttribute("adminModelObject");
		CustomerLoginModel t= new CustomerLoginModel("","");

		List<CustomerLoginModel> listCus = t.getAllcusModel() ;
		Iterator itr;
		for(itr = listCus.iterator(); itr.hasNext();)
		{
			CustomerLoginModel cus = ((CustomerLoginModel)itr.next());
			System.out.println(cus.get_name());
			System.out.println(cus.get_loginDetails().get_userName());
			System.out.println(cus.get_amt());
			
						
			 ;
		}
	
//		Iterator itr; 
//		CarModel car;
//		String ser ="";
//		String status="";
//		for(itr = listCus.iterator(); itr.hasNext();)
//		{
//			var cus = ((CustomerModel)itr.next());
//			
//			car = (cus).get_car(); 
//			if(car != null)
//			{
//				ser =car.get_serReq();
//				status = Boolean.toString( car.is_status());
//			}
//		}
	
		session.setAttribute("listOfCus",listCus);
		
		if(listCus != null)
		{
			System.out.println("list created ");
			session.setAttribute("listOfCus",listCus);
			response.sendRedirect("/bankApplication/viewAllCustomer.jsp");
		}		
		else  {
			System.out.println("Something went wrong ");
			response.sendRedirect("/bankApplication/viewAllCustomer.jsp");
			
		}

		
	}

}
