package com.controller;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.*;

import com.service.AddService;

@Controller
public class AddController {
	
	@RequestMapping("/add")
	public ModelAndView  add(@RequestParam("t1")int i , @RequestParam("t2") int j,
			HttpServletRequest request, HttpServletResponse response )
	{
		System.out.println("im here");
		
		
//		int i = Integer.parseInt(request.getParameter("t1"));
//		int j = Integer.parseInt(request.getParameter("t2"));
		
		AddService service = new AddService();
		int k =service.add(i, j);
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add");
		mv.addObject("result",k);
		
		
		return mv;
	}

}
