//package com.example.controller;
//
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
//import com.example.entity.Achievement;
//import com.example.entity.Blogs;
//import com.example.entity.LoginUserDetails;
//import com.example.repo.AchievementRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class AchievementController {
//
//	@Autowired
//	private AchievementRepository achievementRepository;
//	
//	
//	/*-----------------------add-achievement-----------------------*/
//	@PostMapping("/save-achievement")
//	public ResponseEntity<Map<String, Object>> saveAchievement(@RequestParam String year, @RequestParam String heading,
//			@RequestParam String text, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Achievement achievement = new Achievement();
//			achievement.setYear(year);
//			achievement.setHeading(heading);
//			achievement.setText(text);
//
//			// Optional: Handle image field if provided
//			if (imageFile != null && !imageFile.isEmpty()) {
//				achievement.setImage(imageFile.getBytes());
//			}
//
//			// Save the Achievement entity using the repository
//			achievementRepository.save(achievement);
//
//			response.put("status", true);
//			response.put("data", "Achievement saved successfully.");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error saving Achievement: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	  /*-----------------------update-achievement-----------------------*/
//    @PutMapping("/update-achievement/{id}")
//    public ResponseEntity<Map<String, Object>> updateAchievement(
//            @PathVariable int id,
//            @RequestParam String year,
//            @RequestParam String heading,
//            @RequestParam String text,
//            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile, Authentication authentication) {
//        Map<String, Object> response = new HashMap<>();
//        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//        try {
//            Optional<Achievement> existingAchievement = achievementRepository.findById(id);
//
//            if (existingAchievement.isPresent()) {
//                Achievement achievement = existingAchievement.get();
//                achievement.setYear(year);
//                achievement.setHeading(heading);
//                achievement.setText(text);
//
//                // Optional: Handle image field if provided
//                if (imageFile != null && !imageFile.isEmpty()) {
//                    achievement.setImage(imageFile.getBytes());
//                }
//
//                achievementRepository.save(achievement);
//
//                response.put("status", true);
//                response.put("data", "Achievement updated successfully.");
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response.put("status", false);
//                response.put("error", "Achievement with id " + id + " not found.");
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            response.put("status", false);
//            response.put("error", "Error updating Achievement: " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    
//    /*-----------------------delete-achievement-----------------------*/
//    @DeleteMapping("/delete-achievement/{id}")
//    public ResponseEntity<Map<String, Object>> deleteAchievement(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Map<String, Object> response = new HashMap<>();
//        try {
//            Optional<Achievement> achievement = achievementRepository.findById(id);
//            if (achievement.isPresent()) {
//                achievementRepository.deleteById(id);
//                response.put("status", true);
//                response.put("data", "Achievement deleted successfully.");
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response.put("status", false);
//                response.put("error", "Achievement with id " + id + " not found.");
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            response.put("status", false);
//            response.put("error", "Error deleting Achievement: " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    
//
//    /*-----------------------get-achievement-by-id-----------------------*/
//    @GetMapping("/get-achievement/{id}")
//    public ResponseEntity<Map<String, Object>> getAchievementById(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Map<String, Object> response = new HashMap<>();
//        try {
//            Optional<Achievement> achievement = achievementRepository.findById(id);
//            if (achievement.isPresent()) {
//                response.put("status", true);
//                response.put("data", achievement.get());
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response.put("status", false);
//                response.put("error", "Achievement with id " + id + " not found.");
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            response.put("status", false);
//            response.put("error", "Error fetching Achievement: " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    
//    /*-----------------------get-all-achievements-----------------------*/
//    @GetMapping("/get-all-achievements")
//    public ResponseEntity<Map<String, Object>> getAllAchievements() {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            List<Achievement> allAchievements = achievementRepository.findAll();
//            response.put("status", true);
//            response.put("data", allAchievements);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.put("status", false);
//            response.put("error", "Error fetching all Achievements: " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    
//    
//    /*-----------------------get-image------------------------*/
//    @GetMapping("/achievement-image/{id}")
//    public ResponseEntity<byte[]> getImageById(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<Achievement> image = achievementRepository.findById(id);
//        if (image.isPresent()) {
//        	Achievement blog = image.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(blog.getImage());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
