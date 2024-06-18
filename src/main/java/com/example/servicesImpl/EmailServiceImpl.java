package com.example.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.SubscribeEmail;
import com.example.repo.SubscribeEmailRepository;
import com.example.service.EmailService;
import com.example.service.EmailServiceFn;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private SubscribeEmailRepository subscribeEmailRepository;
	
	@Autowired
	private EmailServiceFn emailServiceFn;

	@Override
	public SubscribeEmail savSubscribeEmail(SubscribeEmail subscribeEmail) {
		
		SubscribeEmail savedSubscribeEmail = subscribeEmailRepository.save(subscribeEmail);

		// Construct the email content
		String emailContent = String.format(
		    "Dear Subscriber,\n\n" +
		    "Thank you for subscribe . Here are the details you provided:\n\n" +
		    "Email: %s\n\n" +
		    "Best regards,\n" +
		    "IBG Reality\n\n" +
		    "Contact Details:\n" +
		    "Contact No: 9503953330\n" +
		    "Address: M.No.307 B, Morgon Road, Baramati, Pune, Maharashtra, India: 413102\n" +
		    "Email: admin@anmane.in\n" +
		    "Website: ANMane.com",
		    savedSubscribeEmail.getEmail()
		);

		// Send thank you email
		emailServiceFn.sendThankYouEmail(
		    savedSubscribeEmail.getEmail(),
		    "Thank you for your Subscribe",
		    emailContent
		);

		return savedSubscribeEmail;
	}

}
