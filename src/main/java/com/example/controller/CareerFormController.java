//package com.example.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
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
//import com.example.entity.CareerForm;
//import com.example.entity.LoginUserDetails;
//import com.example.repo.CareerFormRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class CareerFormController {
//
//	@Autowired
//	private CareerFormRepository careerFormRepository;
//
//	
//	/*-----------------------save-career-form----------------------*/
//	@PostMapping("/save-career-form")
//	public ResponseEntity<Map<String, Object>> saveCareerForm(@RequestParam String data,
//			@RequestParam(value = "uploadResume", required = false) MultipartFile uploadResume,
//	        @RequestParam(value = "uploadCovr_letter", required = false) MultipartFile uploadCovr_letter
//	       ){
//		
//		Map<String, Object> response = new HashMap<>();
//		try {
//			JSONParser parser = new JSONParser();
//			JSONObject json = (JSONObject) parser.parse(data);
//
//			CareerForm careerForm = new CareerForm();
//			careerForm.setName(getStringOrNull(json, "applicant_name"));
//			careerForm.setEmail(getStringOrNull(json, "appli_email"));
//			careerForm.setAddress(getStringOrNull(json, "address"));
//
//			// Parse and set the date of birth
//			String dobString = getStringOrNull(json, "dob");
//			if (dobString != null) {
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//				Date dob1 = dateFormat.parse(dobString);
//				careerForm.setDob(dob1);
//			}
//
//			careerForm.setEducation(getStringOrNull(json, "appli_education"));
//			careerForm.setDescription(getStringOrNull(json, "description"));
//			careerForm.setPosition(getStringOrNull(json, "applied_for"));
//			careerForm.setPhone_number(getStringOrNull(json, "phone_number"));
//
//			// Optional: Handle image field if provided
//			if (uploadResume != null && !uploadResume.isEmpty()) {
//				careerForm.setResume(uploadResume.getBytes());
//			}
//			
//			if (uploadCovr_letter != null && !uploadCovr_letter.isEmpty()) {
//				careerForm.setCovr_letter(uploadCovr_letter.getBytes());
//			}
//
//			careerFormRepository.save(careerForm);
//
//			response.put("status", true);
//			response.put("data", "CareerForm saved successfully.");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error saving CareerForm: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	private String getStringOrNull(JSONObject json, String key) {
//		return json.containsKey(key) ? json.get(key).toString() : null;
//	}
//
//	
//	/*-----------------------update-career-form----------------------*/
//	@PutMapping("/update-career-form/{id}")
//	public ResponseEntity<Map<String, Object>> updateCareerForm(@PathVariable int id, @RequestParam String data,
//			@RequestParam(value = "uploadResume", required = false) MultipartFile uploadResume,
//	        @RequestParam(value = "uploadCovr_letter", required = false) MultipartFile uploadCovr_letter
//	        , Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<CareerForm> careerFormOptional = careerFormRepository.findById(id);
//
//			if (careerFormOptional.isPresent()) {
//				CareerForm careerForm = careerFormOptional.get();
//
//				JSONParser parser = new JSONParser();
//				JSONObject json = (JSONObject) parser.parse(data);
//
//				careerForm.setName(getStringOrNull(json, "applicant_name"));
//				careerForm.setEmail(getStringOrNull(json, "appli_email"));
//				careerForm.setAddress(getStringOrNull(json, "address"));
//
//				// Parse and set the date of birth
//				String dobString = getStringOrNull(json, "dob");
//				if (dobString != null) {
//					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//					Date dob1 = dateFormat.parse(dobString);
//					careerForm.setDob(dob1);
//				}
//
//				careerForm.setEducation(getStringOrNull(json, "appli_education"));
//				careerForm.setDescription(getStringOrNull(json, "description"));
//				careerForm.setPosition(getStringOrNull(json, "applied_for"));
//				careerForm.setPhone_number(getStringOrNull(json, "phone_number"));
//
//				// Optional: Handle image field if provided
//				if (uploadResume != null && !uploadResume.isEmpty()) {
//					careerForm.setResume(uploadResume.getBytes());
//				}
//				
//				if (uploadCovr_letter != null && !uploadCovr_letter.isEmpty()) {
//					careerForm.setCovr_letter(uploadCovr_letter.getBytes());
//				}
//
//				careerFormRepository.save(careerForm);
//
//				response.put("status", true);
//				response.put("data", "CareerForm updated successfully.");
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "CareerForm not found with id: " + id);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error updating CareerForm: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	/*-----------------------delete-career-form----------------------*/
//	@DeleteMapping("/delete-career-form/{id}")
//	public ResponseEntity<Map<String, Object>> deleteCareerForm(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<CareerForm> careerFormOptional = careerFormRepository.findById(id);
//
//			if (careerFormOptional.isPresent()) {
//				CareerForm careerForm = careerFormOptional.get();
//				careerFormRepository.delete(careerForm);
//
//				response.put("status", true);
//				response.put("data", "CareerForm deleted successfully.");
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "CareerForm not found with id: " + id);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error deleting CareerForm: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	/*-----------------------get-all-career-forms----------------------*/
//	@GetMapping("/get-all-career-forms")
//	public ResponseEntity<Map<String, Object>> getAllCareerForms() {
//		
//		Map<String, Object> response = new HashMap<>();
//		try {
//			List<CareerForm> careerForms = careerFormRepository.findAll();
//
//			response.put("status", true);
//			response.put("data", careerForms);
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error fetching CareerForms: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	/*-----------------------get-career-form-by-id----------------------*/
//	@GetMapping("/get-career-form/{id}")
//	public ResponseEntity<Map<String, Object>> getCareerFormById(@PathVariable int id, Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			Optional<CareerForm> careerFormOptional = careerFormRepository.findById(id);
//
//			if (careerFormOptional.isPresent()) {
//				CareerForm careerForm = careerFormOptional.get();
//
//				// Create a Map to store the careerForm data
//				Map<String, Object> responseData = new HashMap<>();
//				responseData.put("id", careerForm.getId());
//				responseData.put("name", careerForm.getName());
//				responseData.put("email", careerForm.getEmail());
//				responseData.put("address", careerForm.getAddress());
//				responseData.put("dob", careerForm.getDob());
//				responseData.put("education", careerForm.getEducation());
//				responseData.put("description", careerForm.getDescription());
//				responseData.put("position", careerForm.getPosition());
//				responseData.put("phone_number", careerForm.getPhone_number());
//				// Include other fields as needed
//
//				response.put("status", true);
//				response.put("data", responseData);
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			} else {
//				response.put("status", false);
//				response.put("error", "CareerForm not found with id: " + id);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			response.put("status", false);
//			response.put("error", "Error fetching CareerForm: " + e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//	/*-----------------------get-resume------------------------*/
//	@GetMapping("/get-resume/{id}")
//    public ResponseEntity<byte[]> getResumeById(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<CareerForm> resumes = careerFormRepository.findById(id);
//
//        if (resumes.isPresent()) {
//        	CareerForm image = resumes.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_PDF) // Set the appropriate content type for your image
//                    .body(image.getResume());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//    
//    
//	/*-----------------------get-cover-letter------------------------*/
//	@GetMapping("/get-cover/{id}")
//    public ResponseEntity<byte[]> getDocumentById(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<CareerForm> resumes = careerFormRepository.findById(id);
//
//        if (resumes.isPresent()) {
//        	CareerForm image = resumes.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_PDF) // Set the appropriate content type for your image
//                    .body(image.getCovr_letter());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//	
//}
