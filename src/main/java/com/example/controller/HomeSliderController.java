//package com.example.controller;
//
//import com.example.entity.LoginUserDetails;
//import com.example.entity.Slider;
//import com.example.repo.SliderRepository;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class HomeSliderController {
//
//    @Autowired
//    private SliderRepository sliderRepository;
//
//
//    /*-----------------------add-new-slider-----------------------*/
//    @PostMapping(value = ("/add-new-slider"))
//    private ResponseEntity<Map<String, Object>> saveSlides(@RequestParam(value = "image", required = false) MultipartFile image,
//                                                           @RequestParam String data, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Map<String, Object> response = new HashMap<>();
//
//        try {
//            JSONParser parser = new JSONParser();
//            JSONObject json = (JSONObject) parser.parse(data);
//
//            Slider slider = new Slider();
//            slider.setTitle(json.get("title").toString());
//            slider.setText(json.get("text").toString());
//            slider.setPage(json.get("page").toString());
//
//            // Check if image is provided before attempting to set it
//            if (image != null) {
//                slider.setImage(image.getBytes());
//            }
//
//            sliderRepository.save(slider);
//
//            response.put("status", true);
//            response.put("data", "Record saved");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (IOException | ParseException ex) {
//            response.put("status", false);
//            response.put("error", ex.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    
//    
//
//    /*-----------------------get-image------------------------*/
//    @GetMapping("/image/{id}")
//    public ResponseEntity<byte[]> getImageById(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<Slider> sliderOptional = sliderRepository.findById(id);
//
//        if (sliderOptional.isPresent()) {
//            Slider slider = sliderOptional.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(slider.getImage());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//    
//    
//    
//    /*-----------------------get-slider-data-----------------------*/
//    @GetMapping("/slider-data/{id}")
//    public ResponseEntity<Map<String, Object>> getSliderWithImage(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<Slider> sliderOptional = sliderRepository.findById(id);
//
//        if (sliderOptional.isPresent()) {
//            Slider slider = sliderOptional.get();
//
//            // Create a Map to store both the slider data and the image bytes
//            Map<String, Object> response = new HashMap<>();
//            response.put("id", slider.getId());
//            response.put("title", slider.getTitle());
//            response.put("text", slider.getText());
//            response.put("page", slider.getPage());
//           response.put("image", Base64.getEncoder().encodeToString(slider.getImage())); // Convert image bytes to Base64
//
//            // Return the response as a JSON object
//            return ResponseEntity.ok().body(response);
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//    
//    
//    
//    /*-----------------------update-slider-----------------------*/
//    @PutMapping(value = ("/update-slider/{id}"))
//    private ResponseEntity<Map<String, Object>> updateSlides(@PathVariable Integer id,
//                                                             @RequestParam(value = "image", required = false) MultipartFile image,
//                                                             @RequestParam String data, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Map<String, Object> response = new HashMap<>();
//
//        try {
//            Optional<Slider> optionalSlider = sliderRepository.findById(id);
//            
//            if (optionalSlider.isPresent()) {
//                Slider slider = optionalSlider.get();
//
//                JSONParser parser = new JSONParser();
//                JSONObject json = (JSONObject) parser.parse(data);
//
//                // Update fields
//                slider.setTitle(json.get("title").toString());
//                slider.setText(json.get("text").toString());
//                slider.setPage(json.get("page").toString());
//
//                // Update image if provided
//                if (image != null) {
//                    slider.setImage(image.getBytes());
//                }
//
//                sliderRepository.save(slider);
//
//                response.put("status", true);
//                response.put("data", "Record updated");
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response.put("status", false);
//                response.put("error", "Slider not found with id: " + id);
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        } catch (IOException | ParseException ex) {
//            response.put("status", false);
//            response.put("error", ex.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    
//    
//	/*-----------------------get-all-home-slider-----------------------*/
//	@GetMapping("/get-all-home-slider")
//	public ResponseEntity<Map<String, Object>> getAllHomeSlider() {
//	   Map<String, Object> response = new HashMap<>();
//	    try {
//	        List<Slider> homeSliderList = sliderRepository.findAll();
//
//	        if (!homeSliderList.isEmpty()) {
//	            response.put("status", true);
//	            response.put("data", homeSliderList);
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        } else {
//	            response.put("status", false);
//	            response.put("error", "No HomeSlider data found.");
//	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	        }
//	    } catch (Exception e) {
//	        response.put("status", false);
//	        response.put("error", "Error fetching all HomeSlider data: " + e.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//
//	
//
//    /*-----------------------delete-slider-----------------------*/
//    @DeleteMapping(value = ("/delete-slider/{id}"))
//    private ResponseEntity<Map<String, Object>> deleteSlider(@PathVariable Integer id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Map<String, Object> response = new HashMap<>();
//
//        try {
//            Optional<Slider> optionalSlider = sliderRepository.findById(id);
//
//            if (optionalSlider.isPresent()) {
//                Slider slider = optionalSlider.get();
//                
//                // Delete the slider
//                sliderRepository.delete(slider);
//
//                response.put("status", true);
//                response.put("data", "Record deleted");
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response.put("status", false);
//                response.put("error", "Slider not found with id: " + id);
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception ex) {
//            response.put("status", false);
//            response.put("error", ex.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
