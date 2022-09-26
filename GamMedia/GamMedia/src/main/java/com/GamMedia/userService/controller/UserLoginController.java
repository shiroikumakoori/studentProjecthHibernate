package com.GamMedia.userService.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PrePostAnnotationSecurityMetadataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.GamMedia.userService.service.SpringSecurityAuditorAware;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("userService/login")
public class UserLoginController {
	String[] attributeNames={ "Curr_LoginUser"};
	String getUserAttName() {return attributeNames[0];}
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ModelMapper  mMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
//    @PostMapping("/create")
//    public User saveUser(@RequestBody User user) {
//        //log.info("Inside saveUser of UserController");
//        System.out.println("Inside saveUser of UserController");
//        return userService.createUserAccount(user);
//    }


	
	@PostMapping("/attempLogin")
	public UserSessionDTO loginIn(@RequestBody UserLoginInDTO user , HttpServletRequest request)
	{	
		
		//User u = userService.getUserByUserNamePassword(user.getUserName(), passwordEncoder.encode(user.getPassword()));
		User u = userService.getUserByUserName(user.getUserName());
		boolean match = passwordEncoder.matches(user.getPassword(), u.getPassword());
		//org.springframework.security.core.userdetails.User uu=  new org.springframework.security.core.userdetails.User( user., user.getPassword(), null);
		//password passed , setting session 
		if(u != null && match ) {		
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
			//aware.setCurrUser(u.getUserName()); 
			 userService.setCurrLoginUser(u);
			return uDTO;
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
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//	}
	
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
	
	@GetMapping("/logout")
	public UserSessionDTO loginout()
	{

		userService.setCurrLoginUser(null);
		return null; 
	}
	

}
