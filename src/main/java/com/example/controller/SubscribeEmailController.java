package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.example.entity.AboutUsContent;
//import com.example.entity.Careers;
import com.example.entity.LoginUserDetails;
import com.example.entity.SubscribeEmail;
import com.example.repo.SubscribeEmailRepository;
import com.example.service.EmailService;
import com.example.util.Mail;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class SubscribeEmailController {

	@Autowired
	private SubscribeEmailRepository subscribeEmailRepository;
	
	@Autowired
	private EmailService emailService;

	/**************** Save Quote *****************/
	@CrossOrigin(origins = "http://127.0.0.1:5501")
	@PostMapping("save-subscribe")
	public ResponseEntity<SubscribeEmail> createSubscribe(@RequestBody SubscribeEmail subscribeEmail){
		SubscribeEmail saveSubscribeEmail = emailService.savSubscribeEmail(subscribeEmail);
		 return ResponseEntity.ok(saveSubscribeEmail);
	}
	
	
	/*-----------------------subscribe-user----------------------*/
	@PostMapping("/subscribe")
	public ResponseEntity<Map<String, Object>> subscribe(@RequestBody String email) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Create a new instance of SubscribeEmail
			SubscribeEmail subscribeEmail = new SubscribeEmail();
			subscribeEmail.setEmail(email);
			subscribeEmail.setIsSubscribed(1);
			// Save email to the database
			subscribeEmailRepository.save(subscribeEmail);
			// Send confirmation email
			Mail.sendConfirmationEmail(email);

			response.put("status", true);
			response.put("data", "Subscribed successfully.");
			response.put("data", subscribeEmail);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error subscribing: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	/*----------------------get-subscribe-user----------------------*/
	@GetMapping("/get-subscribed-user")
	public ResponseEntity<Map<String, Object>> getAllSubscribedMember() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<SubscribeEmail> getSubscriberdMember = subscribeEmailRepository.findAll();
			response.put("status", true);
			response.put("data", getSubscriberdMember);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error fetching all AboutUsContent: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	/*----------------------delete-subscribe-user----------------------*/
	@DeleteMapping("/delete-subscribed/{id}")
	public ResponseEntity<Map<String, Object>> deleteCareer(@PathVariable int id) {
		
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<SubscribeEmail> optionalCareer = subscribeEmailRepository.findById(id);
			if (optionalCareer.isPresent()) {
				subscribeEmailRepository.deleteById(id);
				response.put("status", true);
				response.put("data", "mail deleted successfully.");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "mail not found with id: " + id);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error deleting mail: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*-----------------------get-contact-member-count----------------------*/
	@GetMapping("/get-all-subscribed-member-count")
	public ResponseEntity<Map<String, Object>> getAllContactCount() {
	    Map<String, Object> response = new HashMap<>();
	    try {
	      Long contactUsCount = subscribeEmailRepository.getTotalSubscribedMemberCount();

	        response.put("status", true);
	        response.put("data", contactUsCount);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("error", "Error fetching all ContactUs: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	

}
