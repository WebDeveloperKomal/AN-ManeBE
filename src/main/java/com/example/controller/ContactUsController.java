package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ContactUs;
import com.example.entity.LoginUserDetails;
import com.example.repo.ContactUsRepository;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class ContactUsController {

	@Autowired
	private ContactUsRepository contactUsRepository;

	/*-----------------------add-new-contact-----------------------*/
	@PostMapping("/save-contact-us")
	public ResponseEntity<Map<String, Object>> saveContactUs(
			@RequestBody String data/* , Authentication authentication */) {
		/*
		 * LoginUserDetails loginUserDetails = (LoginUserDetails)
		 * authentication.getPrincipal();
		 */
		Map<String, Object> response = new HashMap<>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(data);

			ContactUs contactUs = new ContactUs();
			contactUs.setName(json.get("name") == null ? "" : json.get("name").toString());
			contactUs.setMessage(json.get("message") == null ? "" : json.get("message").toString());
			contactUs.setPhone(json.get("phone") == null ? "" : json.get("phone").toString());
			contactUs.setEmail(json.get("email") == null ? "" : json.get("email").toString());

			// Save the ContactUs entity
			// ...
			contactUsRepository.save(contactUs);

			response.put("status", true);
			response.put("data", "ContactUs saved successfully.");
			response.put("data", contactUs);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error saving ContactUs: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*-----------------------update-contact-us-----------------------*/
	@PutMapping("/update-contact-us/{id}")
	public ResponseEntity<Map<String, Object>> updateContactUs(@PathVariable int id, @RequestBody String data, Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<ContactUs> optionalContactUs = Optional.ofNullable(contactUsRepository.findById(id));
	
			if (optionalContactUs.isPresent()) {
				ContactUs contactUs = optionalContactUs.get();
	
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(data);
	
				contactUs.setName(json.get("name")==null?"":json.get("name").toString());
				contactUs.setMessage(json.get("message")==null?"":json.get("message").toString());
				contactUs.setPhone(json.get("phone")==null?"":json.get("phone").toString());
				contactUs.setEmail(json.get("email")==null?"":json.get("email").toString());
	
				// Update the ContactUs entity
				contactUsRepository.save(contactUs);
	
				response.put("status", true);
				response.put("data", "ContactUs updated successfully.");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "ContactUs not found with ID: " + id);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error updating ContactUs: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	/*-----------------------delete-contact-us-----------------------*/
	@DeleteMapping("/delete-contact-us/{id}")
	public ResponseEntity<Map<String, Object>> deleteContactUs(@PathVariable int id, Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<ContactUs> optionalContactUs = Optional.ofNullable(contactUsRepository.findById(id));
	
			if (optionalContactUs.isPresent()) {
			ContactUs contactUs = optionalContactUs.get();
				contactUsRepository.delete(contactUs);
	
				response.put("status", true);
				response.put("data", "ContactUs deleted successfully.");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "ContactUs not found with ID: " + id);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error deleting ContactUs: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*-----------------------get-contact-us-by-id-----------------------*/
	@GetMapping("/get-contact-us/{id}")
	public ResponseEntity<Map<String, Object>> getContactUsById(
			@PathVariable int id/* , Authentication authentication */) {
		/*
		 * LoginUserDetails loginUserDetails = (LoginUserDetails)
		 * authentication.getPrincipal();
		 */
		Map<String, Object> response = new HashMap<>();
	    try {
	        Optional<ContactUs> optionalContactUs = Optional.ofNullable(contactUsRepository.findById(id));
	
	        if (optionalContactUs.isPresent()) {
	            ContactUs contactUs = optionalContactUs.get();
	
	            response.put("status", true);
	            response.put("data", contactUs);
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.put("status", false);
	            response.put("error", "ContactUs not found with ID: " + id);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("error", "Error fetching ContactUs: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	

	/*-----------------------get-all-contact-us-----------------------*/
	@GetMapping("/get-all-contact-us")
	public ResponseEntity<Map<String, Object>> getAllContactUs() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<ContactUs> contactUsList = contactUsRepository.getAll();

			response.put("status", true);
			response.put("data", contactUsList);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error fetching all ContactUs: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*-----------------------get-contact-member-count----------------------*/
	@GetMapping("/get-all-contact-count")
	public ResponseEntity<Map<String, Object>> getAllContactCount() {
		Map<String, Object> response = new HashMap<>();
		try {
			Long contactUsCount = contactUsRepository.getTotalContactUsCount();

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
