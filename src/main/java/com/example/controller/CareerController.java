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
//import com.example.entity.Careers;
//import com.example.entity.LoginUserDetails;
//import com.example.repo.CareersRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class CareerController {
//
//	@Autowired
//	private CareersRepository careersRepository;
//
//	/*-----------------------save-career-----------------------*/
//	@PostMapping("/save-career")
//	public ResponseEntity<Map<String, Object>> saveCareer(@RequestParam String data,
//			@RequestParam(value = "uploadImageFile", required = false) MultipartFile uploadImageFile, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			JSONParser parser = new JSONParser();
//			JSONObject json = (JSONObject) parser.parse(data);
//
//			Careers career = new Careers();
//			career.setPost(getStringOrNull(json, "post"));
//			career.setPost_content(getStringOrNull(json, "post_content"));
//
//			// Optional: Handle image field if provided
//			if (uploadImageFile != null && !uploadImageFile.isEmpty()) {
//				career.setImage3(uploadImageFile.getBytes());
//			}
//
//			// Save the Careers entity using the repository
//			careersRepository.save(career);
//
//			response.put("status", true);
//			response.put("data", "Career saved successfully.");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error saving Career: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	private String getStringOrNull(JSONObject json, String key) {
//		return json.containsKey(key) ? json.get(key).toString() : null;
//	}
//
//	/*-----------------------update-career-----------------------*/
//	@PutMapping("/update-career/{id}")
//	public ResponseEntity<Map<String, Object>> updateCareer(@PathVariable int id, @RequestParam String data,
//			@RequestParam(value = "uploadImageFile", required = false) MultipartFile uploadImageFile, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<Careers> optionalCareer = careersRepository.findById(id);
//			if (optionalCareer.isPresent()) {
//				JSONParser parser = new JSONParser();
//				JSONObject json = (JSONObject) parser.parse(data);
//
//				Careers career = optionalCareer.get();
//				career.setPost(getStringOrNull(json, "post"));
//				career.setPost_content(getStringOrNull(json, "post_content"));
//
//				// Optional: Handle image field if provided
//				if (uploadImageFile != null && !uploadImageFile.isEmpty()) {
//					career.setImage3(uploadImageFile.getBytes());
//				}
//
//				// Save the updated Careers entity using the repository
//				careersRepository.save(career);
//
//				response.put("status", true);
//				response.put("data", "Career updated successfully.");
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "Career not found with id: " + id);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error updating Career: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	/*-----------------------delete-career-----------------------*/
//	@DeleteMapping("/delete-career/{id}")
//	public ResponseEntity<Map<String, Object>> deleteCareer(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<Careers> optionalCareer = careersRepository.findById(id);
//			if (optionalCareer.isPresent()) {
//				careersRepository.deleteById(id);
//				response.put("status", true);
//				response.put("data", "Career deleted successfully.");
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "Career not found with id: " + id);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error deleting Career: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	
//	/*-----------------------get-all-careers-----------------------*/
//	@GetMapping("/get-all-careers")
//	public ResponseEntity<List<Careers>> getAllCareers() {
//		List<Careers> careersList = careersRepository.findAll();
//		return new ResponseEntity<>(careersList, HttpStatus.OK);
//	}
//
//	
//	
//	/*-----------------------get-career-by-id-----------------------*/
//	@GetMapping("/get-career/{id}")
//	public ResponseEntity<Map<String, Object>> getCareerById(@PathVariable int id, Authentication authentication) {
//		 LoginUserDetails loginUserDetails=(LoginUserDetails) authentication.getPrincipal();
//		Optional<Careers> optionalCareer = careersRepository.findById(id);
//
//		if (optionalCareer.isPresent()) {
//			Careers career = optionalCareer.get();
//
//			// Create a Map to store the career data and the image bytes
//			Map<String, Object> response = new HashMap<>();
//			response.put("id", career.getId());
//			response.put("post", career.getPost());
//			response.put("post_content", career.getPost_content());
//			response.put("image3", career.getImage3());		// response.put("image3",
//			// Base64.getEncoder().encodeToString(career.getImage3())); // Convert image
//			// bytes to Base64
//
//			// Return the response as a JSON object
//			return ResponseEntity.ok().body(response);
//		} else {
//			// Handle not found scenario
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	/*-----------------------get-image------------------------*/
//	@GetMapping("/career-image/{id}")
//	public ResponseEntity<byte[]> getImageById(@PathVariable int id) {
//		Optional<Careers> career = careersRepository.findById(id);
//
//		if (career.isPresent()) {
//			Careers blog = career.get();
//			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your
//																			// image
//					.body(blog.getImage3());
//		} else {
//			// Handle not found scenario
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//}
