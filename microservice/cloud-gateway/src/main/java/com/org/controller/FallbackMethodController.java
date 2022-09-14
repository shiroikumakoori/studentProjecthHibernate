package com.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class FallbackMethodController {
	
	@HystrixCommand
	@GetMapping("/userServiceFallback")
	public String userServiceFallBackMethod() {
		return "User Service is taking longer than expected."
				+ "Please try again later";
	}
	
	@HystrixCommand
	@GetMapping("/departmentServiceFallback")
	public String departmentServiceFallBackMethod() {
		return "Department Service is taking longer than expected."
				+ "Please try again later";
	}
}
