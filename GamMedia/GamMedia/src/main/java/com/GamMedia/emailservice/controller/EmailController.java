package com.GamMedia.emailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GamMedia.emailservice.entity.EmailDetails;
import com.GamMedia.emailservice.service.EmailServiceImpl;
import com.GamMedia.emailservice.service.IEmailService;
import com.GamMedia.userService.entity.User;
import com.GamMedia.userService.service.IUserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("emailService")
public class EmailController {
	  @Autowired 
	  private IEmailService emailService;
	  @Autowired
	  private IUserService userService;
	  
	  @Autowired
	  private BCryptPasswordEncoder passwordEncoder;
	   
	    // Sending a simple Email
	    @PostMapping("/send/resetPassword")
	    public EmailDetails resetPasswordByMail(@RequestBody EmailDetails email)
	    {
	    	 System.out.println("reset password");
	    	User user= userService.getUserByEmail(email.getRecipient());
	    	if(user!= null)
	    	{	
	    		String pw = makeid(5);
	    		//details.setRecipient(email);
	    		email.setSubject("reset your password");
	    		email.setMsgBody(
	    				"we have reset your password to" + pw
	    				);
	    		user.setPassword( passwordEncoder.encode(pw ));
	    		userService.updateUserAccount(user);
		        String status
		            = emailService.sendSimpleMail(email);
		        System.out.println("password reseted");
		        return email;
	    	}
	    	else
	    	{
	    		   System.out.println(email+" : email doesnt exist");
	    		return null;
	    	}
	    }
	 
	    // Sending email with attachment
	    @PostMapping("/sendMailWithAttachment")
	    public String sendMailWithAttachment(
	        @RequestBody EmailDetails details)
	    {
	        String status
	            = emailService.sendMailWithAttachment(details);
	 
	        return status;
	    }
	    private String makeid( int length) {
	        String result           = "";
	        String characters       = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        int charactersLength = characters.length();
	        for ( var i = 0; i < length; i++ ) {
	          result += characters.charAt((int)Math.floor(Math.random() * 
	     charactersLength));
	       }
	       return result;
	    }
}
