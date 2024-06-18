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

import com.example.entity.AboutUsContent;
import com.example.entity.ContactUs;
import com.example.entity.LoginUserDetails;
import com.example.repo.AboutUsContentRepository;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AboutUsContentController {

	@Autowired
	private AboutUsContentRepository aboutUsContentRepository;

	
	/*-----------------------add-about-us-content-----------------------*/
	@PostMapping("/save-about-us-content")
	public ResponseEntity<Map<String, Object>> saveAboutUsContent(@RequestBody String data , Authentication authentication) {
		Map<String, Object> response = new HashMap<>();
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(data);

			AboutUsContent aboutUsContent = new AboutUsContent();

			aboutUsContent.setMember_count(getStringOrNull(json, "member_count"));
			aboutUsContent.setAwards_count(getStringOrNull(json, "awards_count"));
			aboutUsContent.setProject(getStringOrNull(json, "project"));
			aboutUsContent.setClient_review(getStringOrNull(json, "client_review"));
			aboutUsContentRepository.save(aboutUsContent);

			response.put("status", true);
			response.put("data", "AboutUsContent saved successfully.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error saving AboutUsContent: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String getStringOrNull(JSONObject json, String key) {
		return json.containsKey(key) ? json.get(key).toString() : null;
	}
	
	
	  /*-----------------------update-about-us-content-----------------------*/
    @PutMapping("/update-about-us-content/{id}")
    public ResponseEntity<Map<String, Object>> updateAboutUsContent(
            @PathVariable int id,
            @RequestBody String data, Authentication authentication) {
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<AboutUsContent> existingAboutUsContent = Optional.ofNullable(aboutUsContentRepository.findById(id));

            if (existingAboutUsContent.isPresent()) {
                AboutUsContent aboutUsContent = existingAboutUsContent.get();

                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(data);

                aboutUsContent.setMember_count(getStringOrNull(json, "member_count"));
                aboutUsContent.setAwards_count(getStringOrNull(json, "awards_count")); // Corrected field name
                aboutUsContent.setProject(getStringOrNull(json, "project"));
                aboutUsContent.setClient_review(getStringOrNull(json, "client_review")); // Corrected field name

                aboutUsContentRepository.save(aboutUsContent);

                response.put("status", true);
                response.put("data", "AboutUsContent updated successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", false);
                response.put("error", "AboutUsContent with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error updating AboutUsContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*-----------------------delete-about-us-content-----------------------*/
    @DeleteMapping("/delete-about-us-content/{id}")
    public ResponseEntity<Map<String, Object>> deleteAboutUsContent(@PathVariable int id, Authentication authentication) {
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<AboutUsContent> aboutUsContent = Optional.ofNullable(aboutUsContentRepository.findById(id));
            if (aboutUsContent.isPresent()) {

            	AboutUsContent contactUs = aboutUsContent.get();
                aboutUsContentRepository.deleteById(id);
                response.put("status", true);
                response.put("data", "AboutUsContent deleted successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", false);
                response.put("error", "AboutUsContent with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error deleting AboutUsContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*-----------------------get-about-us-content-by-id-----------------------*/
    @GetMapping("/get-about-us-content/{id}")
    public ResponseEntity<Map<String, Object>> getAboutUsContentById(@PathVariable int id, Authentication authentication) {
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<AboutUsContent> aboutUsContent = Optional.ofNullable(aboutUsContentRepository.findById(id));
            if (aboutUsContent.isPresent()) {
	
                response.put("status", true);
                response.put("data", aboutUsContent.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", false);
                response.put("error", "AboutUsContent with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error fetching AboutUsContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*-----------------------get-all-about-us-content-----------------------*/
    @GetMapping("/get-all-about-us-content")
    public ResponseEntity<Map<String, Object>> getAllAboutUsContent( Authentication authentication) {
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        try {
            List<AboutUsContent> allAboutUsContent = aboutUsContentRepository.findAll();
            response.put("status", true);
            response.put("data", allAboutUsContent);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error fetching all AboutUsContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
    
}
