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
//import com.example.entity.HomeAbout;
//import com.example.entity.LoginUserDetails;
//import com.example.repo.HomeAboutRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class HomeAboutController {
//
//	@Autowired
//	private HomeAboutRepository homeAboutRepository;
//	
//
//	/*-----------------------save-home-about-----------------------*/
//	@PostMapping("/save-home-about")
//	public ResponseEntity<Map<String, Object>> saveHomeAbout(@RequestParam String data,
//			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			JSONParser parser = new JSONParser();
//			JSONObject json = (JSONObject) parser.parse(data);
//
//			HomeAbout homeAbout = new HomeAbout();
//			homeAbout.setAbout(getStringOrNull(json, "about"));
//			homeAbout.setAbout_title(getStringOrNull(json, "about_title"));
//			homeAbout.setAbout_text(getStringOrNull(json, "about_text"));
//
//			// Optional: Handle image field if provided
//			if (imageFile != null && !imageFile.isEmpty()) {
//				homeAbout.setImage_a(imageFile.getBytes());
//			}
//
//			// Save the HomeAbout entity using the repository
//			homeAboutRepository.save(homeAbout);
//
//			response.put("status", true);
//			response.put("data", "HomeAbout saved successfully.");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error saving HomeAbout: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	private String getStringOrNull(JSONObject json, String key) {
//		return json.containsKey(key) ? json.get(key).toString() : null;
//	}
//
//	
//	/*-----------------------update-home-about-----------------------*/
//	@PutMapping("/update-home-about/{id}")
//	public ResponseEntity<Map<String, Object>> updateHomeAbout(
//	        @PathVariable int id,
//	        @RequestParam String data,
//	        @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
//	        , Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Optional<HomeAbout> optionalHomeAbout = homeAboutRepository.findById(id);
//
//	        if (optionalHomeAbout.isPresent()) {
//	            HomeAbout homeAbout = optionalHomeAbout.get();
//
//	            JSONParser parser = new JSONParser();
//	            JSONObject json = (JSONObject) parser.parse(data);
//
//	            homeAbout.setAbout(getStringOrNull(json, "about"));
//	            homeAbout.setAbout_title(getStringOrNull(json, "about_title"));
//	            homeAbout.setAbout_text(getStringOrNull(json, "about_text"));
//
//	            // Optional: Handle image field if provided
//	            if (imageFile != null && !imageFile.isEmpty()) {
//	                homeAbout.setImage_a(imageFile.getBytes());
//	            }
//
//	            // Save the updated HomeAbout entity using the repository
//	            homeAboutRepository.save(homeAbout);
//
//	            response.put("status", true);
//	            response.put("data", "HomeAbout updated successfully.");
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "HomeAbout with ID " + id + " not found.");
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error updating HomeAbout: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//	
//	/*-----------------------get-home-about-by-id-----------------------*/
//	@GetMapping("/get-home-about/{id}")
//	public ResponseEntity<Map<String, Object>> getHomeAboutById(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Optional<HomeAbout> optionalHomeAbout = homeAboutRepository.findById(id);
//
//	        if (optionalHomeAbout.isPresent()) {
//	            HomeAbout homeAbout = optionalHomeAbout.get();
//	            response.put("status", true);
//	            response.put("data", homeAbout);
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "HomeAbout with ID " + id + " not found.");
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error fetching HomeAbout by ID: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//	
//	/*-----------------------get-all-home-about-----------------------*/
//	@GetMapping("/get-all-home-about")
//	public ResponseEntity<Map<String, Object>> getAllHomeAbout() {
//	   Map<String, Object> response = new HashMap<>();
//	    try {
//	        List<HomeAbout> homeAboutList = homeAboutRepository.findAll();
//
//	        if (!homeAboutList.isEmpty()) {
//	            response.put("status", true);
//	            response.put("data", homeAboutList);
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "No HomeAbout data found.");
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error fetching all HomeAbout data: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//	
//	/*-----------------------delete-home-about-----------------------*/
//	@DeleteMapping("/delete-home-about/{id}")
//	public ResponseEntity<Map<String, Object>> deleteHomeAbout(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Optional<HomeAbout> optionalHomeAbout = homeAboutRepository.findById(id);
//
//	        if (optionalHomeAbout.isPresent()) {
//	            homeAboutRepository.deleteById(id);
//	            response.put("status", true);
//	            response.put("data", "HomeAbout deleted successfully.");
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "HomeAbout with ID " + id + " not found.");
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error deleting HomeAbout: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//	
//	
//	/*-----------------------get-image------------------------*/
//	@GetMapping("/get-about-image/{id}")
//    public ResponseEntity<byte[]> getImageById(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<HomeAbout> homeAbout = homeAboutRepository.findById(id);
//
//        if (homeAbout.isPresent()) {
//        	HomeAbout about = homeAbout.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(about.getImage_a());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//	
//}
