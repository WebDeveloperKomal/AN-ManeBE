package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.LoginUserDetails;
import com.example.entity.ServicesContent;
import com.example.repo.ServicesContentRepository;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class ServicesContentController {

	@Autowired
	private ServicesContentRepository servicesContentRepository;
	
	
	 /*-----------------------save-services-content-----------------------*/
    @PostMapping("/save-services-content")
    public ResponseEntity<Map<String, Object>> saveServicesContent(
            @RequestParam String title,
            @RequestParam String information
            , Authentication authentication) {
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
    	Map<String, Object> response = new HashMap<>();
        try {
            ServicesContent servicesContent = new ServicesContent();
            servicesContent.setTitle(title);
            servicesContent.setInformation(information);

            // Save the ServicesContent entity using the repository
            servicesContentRepository.save(servicesContent);

            response.put("status", true);
            response.put("data", "ServicesContent saved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error saving ServicesContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    /*-----------------------update-services-content-----------------------*/
    @PutMapping("/update-services-content/{id}")
    public ResponseEntity<Map<String, Object>> updateServicesContent(
            @PathVariable int id,
            @RequestParam String title,
            @RequestParam String information
            , Authentication authentication) {
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
    	Map<String, Object> response = new HashMap<>();
        try {
            Optional<ServicesContent> existingServicesContent = servicesContentRepository.findById(id);

            if (existingServicesContent.isPresent()) {
                ServicesContent servicesContent = existingServicesContent.get();
                servicesContent.setTitle(title);
                servicesContent.setInformation(information);

                servicesContentRepository.save(servicesContent);

                response.put("status", true);
                response.put("data", "ServicesContent updated successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", false);
                response.put("error", "ServicesContent with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error updating ServicesContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    /*-----------------------delete-services-content-----------------------*/
    @DeleteMapping("/delete-services-content/{id}")
    public ResponseEntity<Map<String, Object>> deleteServicesContent(@PathVariable int id, Authentication authentication) {
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
    	Map<String, Object> response = new HashMap<>();
        try {
            Optional<ServicesContent> servicesContent = servicesContentRepository.findById(id);
            if (servicesContent.isPresent()) {
                servicesContentRepository.deleteById(id);
                response.put("status", true);
                response.put("data", "ServicesContent deleted successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", false);
                response.put("error", "ServicesContent with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error deleting ServicesContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    /*-----------------------get-services-content-by-id-----------------------*/
    @GetMapping("/get-services-content/{id}")
    public ResponseEntity<Map<String, Object>> getServicesContentById(@PathVariable int id, Authentication authentication) {
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
    	Map<String, Object> response = new HashMap<>();
        try {
            Optional<ServicesContent> servicesContent = servicesContentRepository.findById(id);
            if (servicesContent.isPresent()) {
            ServicesContent getData = servicesContent.get();
                response.put("status", true);
                response.put("data", getData);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", false);
                response.put("error", "ServicesContent with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error fetching ServicesContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    /*-----------------------get-all-services-content-----------------------*/
    @GetMapping("/get-all-services-content")
    public ResponseEntity<Map<String, Object>> getAllServicesContent() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ServicesContent> allServicesContent = servicesContentRepository.findAll();
            response.put("status", true);
            response.put("data", allServicesContent);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "Error fetching all ServicesContent: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
    
}
