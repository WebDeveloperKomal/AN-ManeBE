//package com.example.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.entity.HomeContent;
//import com.example.entity.LoginUserDetails;
//import com.example.repo.HomeContentRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class HomeContentController {
//
//	
//	@Autowired
//	private HomeContentRepository homeContentRepository;
//	
//	
//	/*-----------------------add-home-content-----------------------*/
//	@PostMapping("/save-home-content")
//	public ResponseEntity<Map<String, Object>> saveHomeContent(@RequestBody String data, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        JSONParser parser = new JSONParser();
//	        JSONObject json = (JSONObject) parser.parse(data);
//
//	        HomeContent homeContent = new HomeContent();
//	        homeContent.setText(getStringOrNull(json, "text"));
//	        homeContent.setText1(getStringOrNull(json, "text1"));
//	        homeContent.setText2(getStringOrNull(json, "text2"));
//	        homeContent.setFacts(getStringOrNull(json, "facts"));
//	        homeContent.setFacts_text(getStringOrNull(json, "facts_text"));
//	        homeContent.setNations(getStringOrNull(json, "nations"));
//	        homeContent.setMembers(getStringOrNull(json, "members"));
//	        homeContent.setAwards(getStringOrNull(json, "awards"));
//	        homeContent.setSatisfied_customer(getStringOrNull(json, "satisfied_customer"));
//	        homeContent.setMiddle_text(getStringOrNull(json, "middle_text"));
//	        homeContent.setMiddle_text1(getStringOrNull(json, "middle_text1"));
//	        homeContent.setCus_review(getStringOrNull(json, "cus_review"));
//	        homeContent.setCus_review_text(getStringOrNull(json, "cus_review_text"));
//
//	        // Save the HomeContent entity using the repository
//	        homeContentRepository.save(homeContent);
//
//	        response.put("status", true);
//	        response.put("data", "HomeContent saved successfully.");
//	        return new ResponseEntity<>(response, HttpStatus.OK);
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error saving HomeContent: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//	
//
//	private String getStringOrNull(JSONObject json, String key) {
//	    return json.containsKey(key) ? json.get(key).toString() : null;
//	}
//	
//	
//	/*-----------------------update-home-content-----------------------*/
//	@PutMapping("/update-home-content/{id}")
//	public ResponseEntity<Map<String, Object>> updateHomeContent(@PathVariable int id, @RequestBody String data, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Optional<HomeContent> optionalHomeContent = homeContentRepository.findById(id);
//
//	        if (optionalHomeContent.isPresent()) {
//	            HomeContent homeContent = optionalHomeContent.get();
//	            JSONObject json = (JSONObject) new JSONParser().parse(data);
//
//	            // Update fields similar to the save API
//	            homeContent.setText(getStringOrNull(json, "text"));
//		        homeContent.setText1(getStringOrNull(json, "text1"));
//		        homeContent.setText2(getStringOrNull(json, "text2"));
//		        homeContent.setFacts(getStringOrNull(json, "facts"));
//		        homeContent.setFacts_text(getStringOrNull(json, "facts_text"));
//		        homeContent.setNations(getStringOrNull(json, "nations"));
//		        homeContent.setMembers(getStringOrNull(json, "members"));
//		        homeContent.setAwards(getStringOrNull(json, "awards"));
//		        homeContent.setSatisfied_customer(getStringOrNull(json, "satisfied_customer"));
//		        homeContent.setMiddle_text(getStringOrNull(json, "middle_text"));
//		        homeContent.setMiddle_text1(getStringOrNull(json, "middle_text1"));
//		        homeContent.setCus_review(getStringOrNull(json, "cus_review"));
//		        homeContent.setCus_review_text(getStringOrNull(json, "cus_review_text"));
//	            // Save the updated HomeContent entity using the repository
//	            homeContentRepository.save(homeContent);
//
//	            response.put("status", true);
//	            response.put("data", "HomeContent updated successfully.");
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "HomeContent not found with ID: " + id);
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error updating HomeContent: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//	
//	/*-----------------------delete-home-content-----------------------*/
//	@DeleteMapping("/delete-home-content/{id}")
//	public ResponseEntity<Map<String, Object>> deleteHomeContent(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Optional<HomeContent> optionalHomeContent = homeContentRepository.findById(id);
//
//	        if (optionalHomeContent.isPresent()) {
//	            homeContentRepository.deleteById(id);
//	            response.put("status", true);
//	            response.put("data", "HomeContent deleted successfully.");
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "HomeContent not found with ID: " + id);
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error deleting HomeContent: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//	
//	/*-----------------------get-home-content-by-id-----------------------*/
//	@GetMapping("/get-home-content/{id}")
//	public ResponseEntity<Map<String, Object>> getHomeContentById(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Optional<HomeContent> optionalHomeContent = homeContentRepository.findById(id);
//
//	        if (optionalHomeContent.isPresent()) {
//	            HomeContent homeContent = optionalHomeContent.get();
//	            response.put("status", true);
//	            response.put("data", homeContent);
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "HomeContent not found with ID: " + id);
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error fetching HomeContent: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//	
//	
//	/*-----------------------get-all-home-content-----------------------*/
//	@GetMapping("/get-all-home-content")
//	public ResponseEntity<Map<String, Object>> getAllHomeContent() {
//	    Map<String, Object> response = new HashMap<>();
//	    try {
//	        List<HomeContent> homeContentList = homeContentRepository.findAll();
//
//	        if (!homeContentList.isEmpty()) {
//	            response.put("status", true);
//	            response.put("data", homeContentList);
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "No HomeContent data found.");
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error fetching HomeContent data: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//
//
//}
