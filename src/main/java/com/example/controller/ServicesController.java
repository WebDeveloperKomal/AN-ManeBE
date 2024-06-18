//package com.example.controller;
//
//import java.util.ArrayList;
//import java.util.Base64;
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
//import com.example.entity.Services;
//import com.example.repo.ServicesRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class ServicesController {
//
//	@Autowired
//	private ServicesRepository servicesRepository;
//
//	/*-----------------------save-services-----------------------*/
//	@PostMapping("/save-services")
//	public ResponseEntity<Map<String, Object>> saveServices(@RequestParam String data,
//			@RequestParam(value = "image1File", required = false) MultipartFile image1File,
//			@RequestParam(value = "image2File", required = false) MultipartFile image2File
//			, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			JSONParser parser = new JSONParser();
//			JSONObject json = (JSONObject) parser.parse(data);
//
//			Services services = new Services();
//			services.setName(getStringOrNull(json, "name"));
//			services.setText(getStringOrNull(json, "text"));
//			services.setHome_text(getStringOrNull(json, "home_text"));
//
//			// Optional: Handle image fields if provided
//			if (image1File != null && !image1File.isEmpty()) {
//				services.setImage1(image1File.getBytes());
//			}
//
//			if (image2File != null && !image2File.isEmpty()) {
//				services.setImage2(image2File.getBytes());
//			}
//
//			// Save the Services entity using the repository
//			servicesRepository.save(services);
//
//			response.put("status", true);
//			response.put("data", "Services saved successfully.");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error saving Services: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	private String getStringOrNull(JSONObject json, String key) {
//		return json.containsKey(key) ? json.get(key).toString() : null;
//	}
//	
//
//	
//	/*-----------------------update-services-----------------------*/
//	@PutMapping("/update-services/{id}")
//	public ResponseEntity<Map<String, Object>> updateServices(@PathVariable int id, @RequestParam String data,
//			@RequestParam(value = "image1File", required = false) MultipartFile image1File,
//			@RequestParam(value = "image2File", required = false) MultipartFile image2File
//			, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<Services> optionalServices = servicesRepository.findById(id);
//			if (optionalServices.isPresent()) {
//				Services services = optionalServices.get();
//				JSONParser parser = new JSONParser();
//				JSONObject json = (JSONObject) parser.parse(data);
//
//				services.setName(getStringOrNull(json, "name"));
//				services.setText(getStringOrNull(json, "text"));
//				services.setHome_text(getStringOrNull(json, "home_text"));
//
//				// Optional: Handle image fields if provided
//				if (image1File != null && !image1File.isEmpty()) {
//					services.setImage1(image1File.getBytes());
//				}
//
//				if (image2File != null && !image2File.isEmpty()) {
//					services.setImage2(image2File.getBytes());
//				}
//
//				// Update the Services entity using the repository
//				servicesRepository.save(services);
//
//				response.put("status", true);
//				response.put("data", "Services updated successfully.");
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "Services with ID " + id + " not found.");
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error updating Services: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	/*-----------------------delete-services-----------------------*/
//	@DeleteMapping("/delete-services/{id}")
//	public ResponseEntity<Map<String, Object>> deleteServices(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<Services> optionalServices = servicesRepository.findById(id);
//			if (optionalServices.isPresent()) {
//				// Delete the Services entity using the repository
//				servicesRepository.deleteById(id);
//
//				response.put("status", true);
//				response.put("data", "Services deleted successfully.");
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "Services with ID " + id + " not found.");
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error deleting Services: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	/*-----------------------get-services-by-id-----------------------*/
//	@GetMapping("/get-services/{id}")
//	public ResponseEntity<Map<String, Object>> getServicesById(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<Services> optionalServices = servicesRepository.findById(id);
//			if (optionalServices.isPresent()) {
//				Services services = optionalServices.get();
//
//				// Create a response object with relevant data
//				Map<String, Object> responseData = new HashMap<>();
//				responseData.put("id", services.getId());
//				responseData.put("name", services.getName());
//				responseData.put("text", services.getText());
//				responseData.put("home_text", services.getHome_text());
//				responseData.put("image1", services.getImage1());
//				responseData.put("image2", services.getImage2());
//
//				// Return the response
//				response.put("status", true);
//				response.put("data", responseData);
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "Services with ID " + id + " not found.");
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error getting Services by ID: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//
//	/*-----------------------get-all-services-----------------------*/
//	@GetMapping("/get-all-services")
//	public ResponseEntity<Map<String, Object>> getAllServices() {
//		Map<String, Object> response = new HashMap<>();
//		try {
//			// Retrieve all Services entities using the repository
//			List<Services> servicesList = servicesRepository.findAll();
//
//			// Create a response object with relevant data
//			List<Map<String, Object>> responseDataList = new ArrayList<>();
//			for (Services services : servicesList) {
//				Map<String, Object> responseData = new HashMap<>();
//				responseData.put("id", services.getId());
//				responseData.put("name", services.getName());
//				responseData.put("text", services.getText());
//				responseData.put("home_text", services.getHome_text());
//				responseData.put("image1", services.getImage1());
//				responseData.put("image2", services.getImage2());
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
//	
//	/*-----------------------get-image------------------------*/
//	@GetMapping("/get-service-image1/{id}")
//    public ResponseEntity<byte[]> getImageById(@PathVariable int id) {
//        Optional<Services> services = servicesRepository.findById(id);
//
//        if (services.isPresent()) {
//        	Services image = services.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(image.getImage1());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//    
//    
//	/*-----------------------get-image------------------------*/
//	@GetMapping("/get-service-image2/{id}")
//    public ResponseEntity<byte[]> getImageByI(@PathVariable int id) {
//        Optional<Services> services = servicesRepository.findById(id);
//
//        if (services.isPresent()) {
//        	Services image = services.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(image.getImage2());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//    
//    
//
//}
