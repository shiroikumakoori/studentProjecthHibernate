package com.GamMedia.userService.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PrePostAnnotationSecurityMetadataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GamMedia.payload.UserLoginInDTO;
import com.GamMedia.payload.UserSessionDTO;
import com.GamMedia.postService.entity.Post;
import com.GamMedia.userService.entity.User;
import com.GamMedia.userService.service.IUserService;



@RestController
@RequestMapping("userService/login")
public class UserLoginController {
	String[] attributeNames={ "Curr_LoginUser"};
	String getUserAttName() {return attributeNames[0];}
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ModelMapper  mMapper; 
	
	
	@PostMapping("/attempLogin")
	public User loginIn(@RequestBody UserLoginInDTO user , HttpServletRequest request)
	{	
		User u = userService.getUserByUserNamePassword(user.getUserName(), user.getPassword());
		//password passed , setting session 
		if(u != null) {		
			
			//UserSessionDTO uDTO = ConUserToUserSessionDTO(u);
			UserSessionDTO uDTO = mMapper.map(user, UserSessionDTO.class);
			uDTO.setFirstName( u.getFirstName());
			uDTO.setLastName(u.getLastName());
			uDTO.setId(u.getId());
			//uDTO.setPost(u.get)
			if(uDTO.getId() == null) {
				System.out.println("login in with null id for some reason");
			}
			request.getSession().setAttribute(getUserAttName(),uDTO);
			System.out.println("Login in succesful : " );
			//System.out.println(user.getUserName() );
		}
		else
		{
			System.out.println("Login in fail ");
		}
		//TODO put redirect here 
		return null;
		//return "redirect:userService/login/HomePage"; 
		//return "redirect:localhost:8080/userService/login/homePage";
	}
	
	@GetMapping("/homePage")
	public UserSessionDTO loginIn(HttpSession session)
	{
		Object obj =session.getAttribute(getUserAttName());
		if(obj!= null)
		{
			System.out.println("user has login");
			return (UserSessionDTO)obj;
		}

		System.out.println("please login");
		return null; 
	}
	

}
