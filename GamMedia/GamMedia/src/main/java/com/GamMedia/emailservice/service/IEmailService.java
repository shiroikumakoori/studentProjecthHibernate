package com.GamMedia.emailservice.service;

import com.GamMedia.emailservice.entity.EmailDetails;

public interface IEmailService  {
	   // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
 
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
