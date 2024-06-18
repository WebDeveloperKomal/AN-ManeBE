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
//import com.example.entity.Gallery;
//import com.example.entity.LoginUserDetails;
//import com.example.repo.GalleryRepository;
//
//import jakarta.persistence.EntityNotFoundException;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class GalleryController {
//
//	
//	@Autowired
//	private GalleryRepository galleryRepository;
//	
//	
//	/*-----------------------save-gallery-----------------------*/
//	@PostMapping("/save-gallery")
//	public ResponseEntity<Map<String, Object>> saveGallery(@RequestParam String data,
//	        @RequestParam(value = "uploadImageFile", required = false) MultipartFile uploadImageFile
//	        , Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        JSONParser parser = new JSONParser();
//	        JSONObject json = (JSONObject) parser.parse(data);
//
//	        Gallery gallery = new Gallery();
//	        gallery.setTitle(getStringOrNull(json, "title"));
//	        gallery.setTitle2(getStringOrNull(json, "title2"));
//	        gallery.setStatus(getStringOrNull(json, "status"));
//
//	        // Optional: Handle image field if provided
//	        if (uploadImageFile != null && !uploadImageFile.isEmpty()) {
//	            gallery.setUpload_image(uploadImageFile.getBytes());
//	        }
//
//	        // Save the Gallery entity using the repository
//	        galleryRepository.save(gallery);
//
//	        response.put("status", true);
//	        response.put("data", "Gallery saved successfully.");
//	        return new ResponseEntity<>(response, HttpStatus.OK);
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error saving Gallery: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//	private String getStringOrNull(JSONObject json, String key) {
//	    return json.containsKey(key) ? json.get(key).toString() : null;
//	}
//	
//	
//	/*-----------------------update-gallery-----------------------*/
//	@PutMapping("/update-gallery/{id}")
//	public ResponseEntity<Map<String, Object>> updateGallery(@PathVariable int id,
//	        @RequestParam String data, @RequestParam(value = "uploadImageFile", required = false) MultipartFile uploadImageFile
//	        , Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Gallery existingGallery = galleryRepository.findById(id)
//	                .orElseThrow(() -> new EntityNotFoundException("Gallery not found"));
//
//	        JSONParser parser = new JSONParser();
//	        JSONObject json = (JSONObject) parser.parse(data);
//
//	        existingGallery.setTitle(getStringOrNull(json, "title"));
//	        existingGallery.setTitle2(getStringOrNull(json, "title2"));
//	        existingGallery.setStatus(getStringOrNull(json, "status"));
//
//	        // Optional: Handle image field if provided
//	        if (uploadImageFile != null && !uploadImageFile.isEmpty()) {
//	            existingGallery.setUpload_image(uploadImageFile.getBytes());
//	        }
//
//	        // Save the updated Gallery entity
//	        galleryRepository.save(existingGallery);
//
//	        response.put("status", true);
//	        response.put("data", "Gallery updated successfully.");
//	        return new ResponseEntity<>(response, HttpStatus.OK);
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error updating Gallery: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//
//	/*-----------------------delete-gallery-----------------------*/
//	@DeleteMapping("/delete-gallery/{id}")
//	public ResponseEntity<Map<String, Object>> deleteGallery(@PathVariable int id
//			, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Gallery existingGallery = galleryRepository.findById(id)
//	                .orElseThrow(() -> new EntityNotFoundException("Gallery not found"));
//
//	        galleryRepository.delete(existingGallery);
//
//	        response.put("status", true);
//	        response.put("data", "Gallery deleted successfully.");
//	        return new ResponseEntity<>(response, HttpStatus.OK);
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error deleting Gallery: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//
//	/*-----------------------get-gallery-by-id-----------------------*/
//	@GetMapping("/get-gallery/{id}")
//	public ResponseEntity<Map<String, Object>> getGalleryById(@PathVariable int id
//			, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//	    try {
//	        Gallery gallery = galleryRepository.findById(id)
//	                .orElseThrow(() -> new EntityNotFoundException("Gallery not found"));
//
//	        response.put("status", true);
//	        response.put("data", gallery);
//	        return new ResponseEntity<>(response, HttpStatus.OK);
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error getting Gallery by ID: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//
//	/*-----------------------get-all-galleries-----------------------*/
//	@GetMapping("/get-all-galleries")
//	public ResponseEntity<Map<String, Object>> getAllGalleries() {
//	    Map<String, Object> response = new HashMap<>();
//	    try {
//	        List<Gallery> galleries = galleryRepository.findAll();
//
//	        response.put("status", true);
//	        response.put("data", galleries);
//	        return new ResponseEntity<>(response, HttpStatus.OK);
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error getting all Galleries: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//
//	/*-----------------------get-image------------------------*/
//	@GetMapping("/get-gallary-image/{id}")
//    public ResponseEntity<byte[]> getImageById(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<Gallery> gallery = galleryRepository.findById(id);
//
//        if (gallery.isPresent()) {
//        	Gallery image = gallery.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(image.getUpload_image());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//    
//    
//}
