package com.GamMedia.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
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
	  
	   
	    // Sending a simple Email
	    @PostMapping("/send/resetPassword")
	    public EmailDetails resetPasswordByMail(@RequestBody EmailDetails email)
	    {
	    	 System.out.println("reset password");
	    	User user= userService.getUserByEmail(email.getRecipient());
	    	if(user!= null)
	    	{	
	    		//details.setRecipient(email);
	    		email.setSubject("reset your password");
	    		email.setMsgBody(
	    				"we have reset your password to 'Password123'. "
	    				);
	    		user.setPassword("Password123");
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
}
