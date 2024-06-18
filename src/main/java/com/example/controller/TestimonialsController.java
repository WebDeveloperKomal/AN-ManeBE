package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.example.entity.Blogs;
import com.example.entity.LoginUserDetails;
import com.example.entity.Testimonials;
import com.example.repo.TestimonialsRepository;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class TestimonialsController {
	
	@Autowired
	private TestimonialsRepository testimonialsRepository;
	
	
	/*-----------------------save-testimonial-----------------------*/
	@PostMapping("/save-testimonials")
	public ResponseEntity<Map<String, Object>> saveTestimonials(@RequestBody String data, Authentication authentication) {
	
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		
		Map<String, Object> response = new HashMap<>();
	    try {
	        JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(data);

	        Testimonials testimonials = new Testimonials();
//	        testimonials.setTitle(getStringOrNull(json, "title"));
	        testimonials.setTest_name(getStringOrNull(json, "test_name"));
	        testimonials.setAddress(getStringOrNull(json, "address"));
	        testimonials.setComment(getStringOrNull(json, "comment"));
	        testimonials.setStatus(1);

	        // Optional: Handle image field if provided
//	        if (imageFile != null && !imageFile.isEmpty()) {
//	            testimonials.setImage(imageFile.getBytes());
//	        }

	        // Save the Testimonials entity using the repository
	        testimonialsRepository.save(testimonials);

	        response.put("status", true);
	        response.put("data", "Testimonials saved successfully.");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("error", "Error saving Testimonials: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	private String getStringOrNull(JSONObject json, String key) {
	    return json.containsKey(key) ? json.get(key).toString() : null;
	}

	private Integer getIntOrNull(JSONObject json, String key) {
	    return json.containsKey(key) ? Integer.parseInt(json.get(key).toString()) : null;
	}

	
	/*-----------------------update-testimonials-----------------------*/
	@PutMapping("/update-testimonials/{id}")
	public ResponseEntity<Map<String, Object>> updateTestimonials(@PathVariable int id,
	        @RequestBody String data,  Authentication authentication) {
	 
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		
		Map<String, Object> response = new HashMap<>();
	    try {
	        Optional<Testimonials> optionalTestimonials = Optional.ofNullable(testimonialsRepository.findById(id));
	        if (optionalTestimonials.isPresent()) {
	            Testimonials testimonials = optionalTestimonials.get();

	            JSONParser parser = new JSONParser();
	            JSONObject json = (JSONObject) parser.parse(data);

//	            testimonials.setTitle(getStringOrNull(json, "title"));
	            testimonials.setTest_name(getStringOrNull(json, "test_name"));
	            testimonials.setAddress(getStringOrNull(json, "address"));
	            testimonials.setComment(getStringOrNull(json, "comment"));
	            testimonials.setStatus(1);

	            // Optional: Handle image field if provided
//	            if (imageFile != null && !imageFile.isEmpty()) {
//	                testimonials.setImage(imageFile.getBytes());
//	            }

	            // Update the Testimonials entity using the repository
	            testimonialsRepository.save(testimonials);

	            response.put("status", true);
	            response.put("data", "Testimonials updated successfully.");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.put("status", false);
	            response.put("error", "Testimonials not found with id: " + id);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("error", "Error updating Testimonials: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	/*-----------------------delete-testimonials-----------------------*/
	@DeleteMapping("/delete-testimonials/{id}")
	public ResponseEntity<Map<String, Object>> deleteTestimonials(@PathVariable int id, Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();
	    try {
	        Optional<Testimonials> optionalTestimonials = Optional.ofNullable(testimonialsRepository.findById(id));
	        if (optionalTestimonials.isPresent()) {
	            testimonialsRepository.deleteById(id);
	            response.put("status", true);
	            response.put("data", "Testimonials deleted successfully.");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.put("status", false);
	            response.put("error", "Testimonials not found with id: " + id);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("error", "Error deleting Testimonials: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	/*-----------------------get-testimonials-by-id-----------------------*/
	@GetMapping("/get-testimonials/{id}")
	public ResponseEntity<Map<String, Object>> getTestimonialsById(@PathVariable int id, Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();
	    try {
	        Optional<Testimonials> optionalTestimonials = Optional.ofNullable(testimonialsRepository.findById(id));
	        if (optionalTestimonials.isPresent()) {
	            response.put("status", true);
	            response.put("data", optionalTestimonials.get());
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.put("status", false);
	            response.put("error", "Testimonials not found with id: " + id);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("error", "Error fetching Testimonials: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	/*-----------------------get-all-testimonials-----------------------*/
	@GetMapping("/get-all-testimonials")
	public ResponseEntity<Map<String, Object>> getAllTestimonials() {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        List<Testimonials> testimonialsList = testimonialsRepository.findAll();
	        response.put("status", true);
	        response.put("data", testimonialsList);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("error", "Error fetching Testimonials: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
//	/*-----------------------get-image------------------------*/
//    @GetMapping("/testimonial-image/{id}")
//    public ResponseEntity<byte[]> getImageById(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<Testimonials> testimonial = Optional.ofNullable(testimonialsRepository.findById(id));
//
//        if (testimonial.isPresent()) {
//        	Testimonials image = testimonial.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(image.getImage());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
	
}
