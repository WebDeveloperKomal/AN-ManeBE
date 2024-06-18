package com.example.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.service.EmailServiceFn;


@Service
public class EmailServiceFnImpl implements EmailServiceFn {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendThankYouEmail(String to, String subject, String text) {
		// TODO Auto-generated method stub
		  SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("ibgreality1@gmail.com");
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        javaMailSender.send(message);
	}

}
