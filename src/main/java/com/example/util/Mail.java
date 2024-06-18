package com.example.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class Mail {
	
	
	  public static void sendConfirmationEmail(String email) {
		  
	        // Setup mail server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587"); // or your SMTP port

	        // Sender's credentials
	        final String username = "ibgreality1@gmail.com";
	        final String password = "vfgw tgwf bnuy khhk";

	        // Create session with authentication
	        Session session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	        try {
	            // Create a default MimeMessage object
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header
	            message.setFrom(new InternetAddress("ibgreality1@gmail.com"));

	            // Set To: header field of the header
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

	            // Set Subject: header field
	            message.setSubject("Congratulations !!");

	            // Set the actual message
	            message.setText("A N Mane mail for subscription completion successfully!  keep in touch with us.");

	            // Send message
	            Transport.send(message);
	            System.out.println("Confirmation email sent successfully.");
	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    }

	  
	  
}
