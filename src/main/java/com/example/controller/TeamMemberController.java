package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.LoginUserDetails;
//import com.example.entity.Services;
//import com.example.entity.Slider;
import com.example.entity.TeamMembers;
import com.example.repo.TeamMembersRepo;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class TeamMemberController {
	
	@Autowired
	private TeamMembersRepo teamMembersRepo;
	

	/*-----------------------add-member-----------------------*/
	@PostMapping(value = ("/add-new-member"))
	private ResponseEntity<Map<String, Object>> saveMembers(@RequestParam(value = "image", required = false) MultipartFile image,
	                                                       @RequestParam String data, Authentication authentication) {
	
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		
		Map<String, Object> response = new HashMap<>();

	    try {
	        JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(data);

	        TeamMembers team = new TeamMembers();
//	        team.setTitle(json.get("title").toString());
	        team.setName(json.get("empName").toString());
	        team.setDesignation(json.get("designation").toString());

	        // Check if image is provided before attempting to set it
	        if (image != null) {
	            team.setImage(image.getBytes());
	        }

	        teamMembersRepo.save(team);

	        response.put("status", true);
	        response.put("data", "Record saved");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (IOException | ParseException ex) {
	        response.put("status", false);
	        response.put("error", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

    
    
    /*-----------------------get-member-image-----------------------*/
	
	/* @Cacheable(value = "memberImageCache", key = "#id") */
    @GetMapping(value = "/get-member-image/{id}")
    public ResponseEntity<byte[]> getMemberImage(@PathVariable int id) {
        Optional<TeamMembers> optionalTeamMember = Optional.ofNullable(teamMembersRepo.findById(id));

        if (optionalTeamMember.isPresent()) {
            TeamMembers teamMember = optionalTeamMember.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
                    .body(teamMember.getImage());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
   
    /*-----------------------get-member-data-----------------------*/
    
	/* @Cacheable(value = "teamMembersCache", key = "#id") */
    @GetMapping("/member-data/{id}")
    public ResponseEntity<Map<String, Object>> getMemberById(@PathVariable int id, Authentication authentication) {
    	
    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
    	
    	Optional<TeamMembers> optionalTeamMember = Optional.ofNullable(teamMembersRepo.findById(id));

        if (optionalTeamMember.isPresent()) {
            TeamMembers member = optionalTeamMember.get();

            // Create a Map to store both the slider data and the image bytes
            Map<String, Object> response = new HashMap<>();
            response.put("id", member.getId());
//            response.put("title", member.getTitle());
            response.put("designation", member.getDesignation());
            response.put("name", member.getName());
            response.put("status", member.getStatus());
           response.put("image", Base64.getEncoder().encodeToString(member.getImage())); // Convert image bytes to Base64

            // Return the response as a JSON object
            return ResponseEntity.ok().body(response);
        } else {
            // Handle not found scenario
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
	/*-----------------------get-all-members-----------------------*/
	@GetMapping("/get-all-members")
	public ResponseEntity<Map<String, Object>> getAllServices() {
		Map<String, Object> response = new HashMap<>();
		try {
			// Retrieve all Services entities using the repository
			List<TeamMembers> memberList = teamMembersRepo.findAllByStatusIsZero();

			// Create a response object with relevant data
			List<Map<String, Object>> responseDataList = new ArrayList<>();
			for (TeamMembers services : memberList) {
				Map<String, Object> responseData = new HashMap<>();
				responseData.put("id", services.getId());
				responseData.put("name", services.getName());
//				responseData.put("title", services.getTitle());
				responseData.put("designation", services.getDesignation());
				responseData.put("status", services.getStatus());
				responseData.put("image", services.getImage());
				/*
				 * String base64EncodedImage =
				 * Base64.getEncoder().encodeToString(services.getImage());
				 * responseData.put("image", base64EncodedImage);
				 */
				responseDataList.add(responseData);
			}

			// Return the response
			response.put("status", true);
			response.put("data", responseDataList);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error getting all Services: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
    /*-----------------------update-member-----------------------*/
	@PutMapping(value = ("/update-member/{id}"))
	public ResponseEntity<Map<String, Object>> updateMembers(@PathVariable Integer id,
	                                                         @RequestParam(value = "image", required = false) MultipartFile image,
	                                                         @RequestParam String data, Authentication authentication) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        Optional<TeamMembers> teamMember = teamMembersRepo.findById(id);

	        if (teamMember.isPresent()) {
	            TeamMembers members = teamMember.get();

	            JSONParser parser = new JSONParser();
	            JSONObject json = (JSONObject) parser.parse(data);

	            if (json.containsKey("name") && json.containsKey("designation")) {
	                // Update fields
//	                members.setTitle(json.get("title").toString());
	                members.setName(json.get("name").toString());
	                members.setDesignation(json.get("designation").toString());
	            } else {
	                response.put("status", false);
	                response.put("error", "Required fields are missing in the data.");
	                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	            }

	            // Update image if provided
	            if (image != null) {
	                members.setImage(image.getBytes());
	            }

	            teamMembersRepo.save(members);

	            response.put("status", true);
	            response.put("data", "Record updated");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.put("status", false);
	            response.put("error", "Member not found with id: " + id);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch (IOException | ParseException ex) {
	        response.put("status", false);
	        response.put("error", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

    

    /*-----------------------delete-member-----------------------*/
    @DeleteMapping(value = ("/delete-member/{id}"))
    private ResponseEntity<Map<String, Object>> deleteMember(@PathVariable Integer id, Authentication authentication) {

    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
    	
    	Map<String, Object> response = new HashMap<>();

        try {
        	Optional<TeamMembers> teamMember = teamMembersRepo.findById(id);

            if (teamMember.isPresent()) {
                TeamMembers delete = teamMember.get();
                
                // Delete the slider
                delete.setStatus(1);
                teamMembersRepo.save(delete);

                response.put("status", true);
                response.put("data", "Record deleted");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", false);
                response.put("error", "Member not found with id: " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            response.put("status", false);
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    /*-----------------------get-contact-member-count----------------------*/
 	@GetMapping("/get-all-team-member-count")
 	public ResponseEntity<Map<String, Object>> getAllContactCount() {
 	    Map<String, Object> response = new HashMap<>();
 	    try {
 	      Long teamMemberCount = teamMembersRepo.getTotalMemberCount();

 	        response.put("status", true);
 	        response.put("data", teamMemberCount);
 	        return new ResponseEntity<>(response, HttpStatus.OK);
 	    } catch (Exception e) {
 	        response.put("status", false);
 	        response.put("error", "Error fetching all ContactUs: " + e.getMessage());
 	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 	    }
 	}
    
}
