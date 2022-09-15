package org.dxcSpringSecurity.controller;

import org.dxcSpringSecurity.dto.UserRegDTO;
import org.dxcSpringSecurity.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private IUserService userService;
	
	public UserRegistrationController(IUserService userService)
	{
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegDTO userRegistrationDto()
	{
		return new  UserRegDTO();
	}
	
	@GetMapping
	public String showRegistrationForm()
	{
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegDTO regDTO)
	{
		userService.save(regDTO);
		return "redirect:/registration?success";
	}
	
}
