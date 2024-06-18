//package com.example.controller;
//
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.entity.HomeFooter;
//import com.example.entity.LoginUserDetails;
//import com.example.repo.HomeFooterRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class HomeFooterController {
//
//	@Autowired
//	private HomeFooterRepository homeFooterRepository;
//
//	/*-----------------------save-footer------------------------*/
//	@PostMapping("/save-footer")
//	public ResponseEntity<Map<String, Object>> saveHomeFooter(@RequestParam String pingNow, @RequestParam String text,
//			@RequestParam(required = false) MultipartFile image, @RequestParam String openHours,
//			@RequestParam String footer_title, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			HomeFooter homeFooter = new HomeFooter();
//			homeFooter.setPing_now(pingNow);
//			homeFooter.setText(text);
//			homeFooter.setOpen_hours(openHours);
//			homeFooter.setFooter_title(footer_title);
//
//			if (image != null) {
//				homeFooter.setImage(image.getBytes());
//			}
//
//			homeFooterRepository.save(homeFooter);
//
//			response.put("status", true);
//			response.put("data", "HomeFooter saved successfully.");
//			return ResponseEntity.ok(response);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error saving HomeFooter: " + e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//		}
//	}
//
//	
//	/*-----------------------update-home-footer-----------------------*/
//	@PutMapping("/update-home-footer/{id}")
//	public ResponseEntity<Map<String, Object>> updateHomeFooter(@PathVariable int id,
//	        @RequestParam String ping_now, @RequestParam String text, @RequestParam String open_hours,
//	        @RequestParam String footer_title,
//	        @RequestParam(value = "image", required = false) MultipartFile image,
//	        Authentication authentication) {
//	    LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//	    Map<String, Object> response = new HashMap<>();
//	    try {
//	        Optional<HomeFooter> existingHomeFooter = homeFooterRepository.findById(id);
//
//	        if (existingHomeFooter.isPresent()) {
//	            HomeFooter homeFooter = existingHomeFooter.get();
//	            homeFooter.setPing_now(ping_now);
//	            homeFooter.setText(text);
//	            homeFooter.setOpen_hours(open_hours);
//	            homeFooter.setFooter_title(footer_title);
//
//	            // Optional: Handle image field if provided
//	            if (image != null && !image.isEmpty()) {
//	                homeFooter.setImage(image.getBytes());
//	            }
//
//	            homeFooterRepository.save(homeFooter);
//
//	            response.put("status", true);
//	            response.put("data", "HomeFooter updated successfully.");
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "HomeFooter with id " + id + " not found.");
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error updating HomeFooter: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//	
//	/*-----------------------delete-footer------------------------*/
//	@DeleteMapping("/delete-footer/{id}")
//	public ResponseEntity<Map<String, Object>> deleteHomeFooter(@PathVariable int id, Authentication authentication) {
//		// Assuming you have a service for managing HomeFooter entities
//		Map<String, Object> response = new HashMap<>();
//
//		try {
//			// Delete the HomeFooter entity by ID
//			Optional<HomeFooter> homeFooterOptional = homeFooterRepository.findById(id);
//
//			
//			if (homeFooterOptional.isPresent()) {
//				HomeFooter homeFooter = homeFooterOptional.get();
//
//				// Delete the HomeFooter
//				homeFooterRepository.delete(homeFooter);
//
//				response.put("status", true);
//				response.put("data", "HomeFooter deleted successfully");
//				return ResponseEntity.ok().body(response);
//			} else {
//				response.put("status", false);
//				response.put("error", "HomeFooter not found with id: " + id);
//				return ResponseEntity.notFound().build();
//			}
//		} catch (Exception ex) {
//			response.put("status", false);
//			response.put("error", "Failed to delete HomeFooter: " + ex.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//		}
//	}
//
//	/*-----------------------get-image------------------------*/
//	@GetMapping("/get-footer-image/{id}")
//	public ResponseEntity<byte[]> getImageById(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Optional<HomeFooter> homeFooter = homeFooterRepository.findById(id);
//
//		if (homeFooter.isPresent()) {
//			HomeFooter footer = homeFooter.get();
//			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your
//																			// image
//					.body(footer.getImage());
//		} else {
//			// Handle not found scenario
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	/*-----------------------get-home-footer-by-id------------------------*/
//	@GetMapping("/get-home-footer/{id}")
//	public ResponseEntity<Map<String, Object>> getHomeFooter(@PathVariable int id, Authentication authentication) {
//		// Assuming you have a service for managing HomeFooter entities
//		Optional<HomeFooter> homeFooterOptional = homeFooterRepository.findById(id);
//
//		if (homeFooterOptional.isPresent()) {
//			HomeFooter homeFooter = homeFooterOptional.get();
//
//			// Create a Map to store HomeFooter data
//			Map<String, Object> response = new HashMap<>();
//			response.put("id", homeFooter.getId());
//			response.put("ping_now", homeFooter.getPing_now());
//			response.put("text", homeFooter.getText());
//			// Assuming you want to encode the image as Base64
//			response.put("image", Base64.getEncoder().encodeToString(homeFooter.getImage()));
//			response.put("open_hours", homeFooter.getOpen_hours());
//			response.put("footer_title", homeFooter.getFooter_title());
//
//			// Return the response as a JSON object
//			return ResponseEntity.ok().body(response);
//		} else {
//			// Handle not found scenario
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	/*-----------------------get-all-footer-----------------------*/
//	@GetMapping("/get-all-footer")
//	public ResponseEntity<Map<String, Object>> getAllFooter() {
//		Map<String, Object> response = new HashMap<>();
//		try {
//			// Retrieve all Services entities using the repository
//			List<HomeFooter> footerList = homeFooterRepository.findAll();
//
//			// Create a response object with relevant data
//			List<Map<String, Object>> responseDataList = new ArrayList<>();
//			for (HomeFooter services : footerList) {
//				Map<String, Object> responseData = new HashMap<>();
//				responseData.put("id", services.getId());
//				responseData.put("ping_now", services.getPing_now());
//				responseData.put("text", services.getText());
//				responseData.put("openHours", services.getOpen_hours());
//				responseData.put("footer_title", services.getFooter_title());
//				responseData.put("image", Base64.getEncoder().encodeToString(services.getImage())); // Convert image
//																									// bytes to Base64
//				responseDataList.add(responseData);
//			}
//
//			// Return the response
//			response.put("status", true);
//			response.put("data", responseDataList);
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error getting all Services: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//}
