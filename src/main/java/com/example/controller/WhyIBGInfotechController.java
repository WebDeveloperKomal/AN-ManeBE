//package com.example.controller;
//
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
//
//import com.example.entity.LoginUserDetails;
//import com.example.entity.WhyIBGInfotech;
//import com.example.repo.WhyIBGInfotechRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class WhyIBGInfotechController {
//
//	@Autowired
//	private WhyIBGInfotechRepository whyIBGInfotechRepository;
//
//	/*-----------------------save-why-ibg-infotech-----------------------*/
//	@PostMapping("/save-why-ibg-infotech")
//	public ResponseEntity<Map<String, Object>> saveWhyIBGInfotech(@RequestParam String name, @RequestParam String title,
//			@RequestParam String description, @RequestParam(value = "image", required = false) MultipartFile imageFile,
//			Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			WhyIBGInfotech whyIBGInfotech = new WhyIBGInfotech();
//			whyIBGInfotech.setName(name);
//			whyIBGInfotech.setTitle(title);
//			whyIBGInfotech.setDescription(description);
//
//			// Optional: Handle image field if provided
//			if (imageFile != null && !imageFile.isEmpty()) {
//				whyIBGInfotech.setImage(imageFile.getBytes());
//			}
//
//			// Save the WhyIBGInfotech entity using the repository
//			whyIBGInfotechRepository.save(whyIBGInfotech);
//
//			response.put("status", true);
//			response.put("data", "WhyIBGInfotech saved successfully.");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error saving WhyIBGInfotech: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//
//	/*-----------------------update-why-ibg-infotech-----------------------*/
//	@PutMapping("/update-why-ibg-infotech/{id}")
//	public ResponseEntity<Map<String, Object>> updateWhyIBGInfotech(@PathVariable int id, @RequestParam String name,
//			@RequestParam String title, @RequestParam String description,
//			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
//			Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<WhyIBGInfotech> existingWhyIBGInfotech = whyIBGInfotechRepository.findById(id);
//
//			if (existingWhyIBGInfotech.isPresent()) {
//				WhyIBGInfotech whyIBGInfotech = existingWhyIBGInfotech.get();
//				whyIBGInfotech.setName(name);
//				whyIBGInfotech.setTitle(title);
//				whyIBGInfotech.setDescription(description);
//
//				// Optional: Handle image field if provided
//				if (imageFile != null && !imageFile.isEmpty()) {
//					whyIBGInfotech.setImage(imageFile.getBytes());
//				}
//
//				whyIBGInfotechRepository.save(whyIBGInfotech);
//
//				response.put("status", true);
//				response.put("data", "WhyIBGInfotech updated successfully.");
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "WhyIBGInfotech with id " + id + " not found.");
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error updating WhyIBGInfotech: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//
//	/*-----------------------delete-why-ibg-infotech-----------------------*/
//	@DeleteMapping("/delete-why-ibg-infotech/{id}")
//	public ResponseEntity<Map<String, Object>> deleteWhyIBGInfotech(@PathVariable int id,
//			Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<WhyIBGInfotech> whyIBGInfotech = whyIBGInfotechRepository.findById(id);
//			if (whyIBGInfotech.isPresent()) {
//				whyIBGInfotechRepository.deleteById(id);
//				response.put("status", true);
//				response.put("data", "WhyIBGInfotech deleted successfully.");
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "WhyIBGInfotech with id " + id + " not found.");
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error deleting WhyIBGInfotech: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	/*
//	 * -----------------------get-why-ibg-infotech-by-id-----------------------
//	 * 
//	 * @GetMapping("/get-why-ibg-infotech/{id}") public ResponseEntity<Map<String,
//	 * Object>> getWhyIBGInfotechById(@PathVariable int id, Authentication
//	 * authentication) { LoginUserDetails loginUserDetails = (LoginUserDetails)
//	 * authentication.getPrincipal(); Map<String, Object> response = new
//	 * HashMap<>(); try { Optional<WhyIBGInfotech> whyIBGInfotech =
//	 * whyIBGInfotechRepository.findById(id); if (whyIBGInfotech.isPresent()) {
//	 * WhyIBGInfotech whyInfo = whyIBGInfotech.get(); response.put("status", true);
//	 * response.put("data", whyInfo); return new ResponseEntity<>(response,
//	 * HttpStatus.OK); } else { response.put("status", false); response.put("error",
//	 * "WhyIBGInfotech with id " + id + " not found."); return new
//	 * ResponseEntity<>(response, HttpStatus.NOT_FOUND); } } catch (Exception e) {
//	 * response.put("status", false); response.put("error",
//	 * "Error fetching WhyIBGInfotech: " + e.getMessage()); return new
//	 * ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); } }
//	 * 
//	 */
//	
//	
//	/*-----------------------get-why-ibg-infotech-by-id----------------------*/
//	@GetMapping("/get-why-ibg-infotech/{id}")
//	public ResponseEntity<Map<String, Object>> getWhyIBGInfotechById(@PathVariable int id,
//			Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<WhyIBGInfotech> whyIBGInfotechOptional = whyIBGInfotechRepository.findById(id);
//
//			if (whyIBGInfotechOptional.isPresent()) {
//				WhyIBGInfotech whyIBGInfotech = whyIBGInfotechOptional.get();
//
//				// Create a Map to store the whyIBGInfotech data
//				Map<String, Object> responseData = new HashMap<>();
//				responseData.put("id", whyIBGInfotech.getId());
//				responseData.put("name", whyIBGInfotech.getName());
//				responseData.put("title", whyIBGInfotech.getTitle());
//				responseData.put("description", whyIBGInfotech.getDescription());
//				// Convert byte array to Base64 encoded string for image
//				String base64EncodedImage = Base64.getEncoder().encodeToString(whyIBGInfotech.getImage());
//				responseData.put("image", base64EncodedImage);
//				// Include other fields as needed
//
//				response.put("status", true);
//				response.put("data", responseData);
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "WhyIBGInfotech not found with id: " + id);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error fetching WhyIBGInfotech: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	
//	/*-----------------------get-all-why-ibg-infotech-----------------------*/
//	@GetMapping("/get-all-why-ibg-infotech")
//	public ResponseEntity<Map<String, Object>> getAllWhyIBGInfotech() {
//		Map<String, Object> response = new HashMap<>();
//		try {
//			List<WhyIBGInfotech> allWhyIBGInfotech = whyIBGInfotechRepository.findAll();
//			response.put("status", true);
//			response.put("data", allWhyIBGInfotech);
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error fetching all WhyIBGInfotech: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//
//	/*-----------------------get-image------------------------*/
//	@GetMapping("/why-image/{id}")
//	public ResponseEntity<byte[]> getImageById(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Optional<WhyIBGInfotech> blogs = whyIBGInfotechRepository.findById(id);
//
//		if (blogs.isPresent()) {
//			WhyIBGInfotech blog = blogs.get();
//			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your
//																			// image
//					.body(blog.getImage());
//		} else {
//			// Handle not found scenario
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//}
