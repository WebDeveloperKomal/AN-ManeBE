package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import com.example.entity.Role;
import com.example.repo.RoleRepository;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	/*-----------------------save-role-----------------------*/
	@PostMapping("/save-role")
	public ResponseEntity<Map<String, Object>> saveRole(@RequestParam String data, Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(data);

			Role role = new Role();
			role.setName(getStringOrNull(json, "name"));

			// Optional: Handle date fields if provided
			if (json.containsKey("fromDate")) {
				role.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse(json.get("fromDate").toString()));
			}

			if (json.containsKey("toDate")) {
				role.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse(json.get("toDate").toString()));
			}

			// Save the Role entity using the repository
			roleRepository.save(role);

			response.put("status", true);
			response.put("data", "Role saved successfully.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error saving Role: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String getStringOrNull(JSONObject json, String key) {
		return json.containsKey(key) ? json.get(key).toString() : null;
	}

	/*-----------------------get-all-roles-----------------------*/
	@GetMapping(value = "/get-all-roles", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getRoles() {
		Map<String, Object> response = new HashMap<>();
		List<JSONObject> data = null;
		JSONObject json = null;

		try {
			List<Role> rolesList = roleRepository.findAll();

			if (rolesList != null) {
				data = new ArrayList<>();
				for (Role role : rolesList) {
					json = new JSONObject();
					json.put("roleId", role.getId());
					json.put("roleName", role.getName());
					json.put("isActive", role.isActive());
					json.put("fromDate", role.getFromDate());
					json.put("toDate", role.getToDate());

					data.add(json);
				}
			}

			response.put("status", true);
			response.put("data", data);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception ex) {
			response.put("status", false);
			response.put("error", ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*-----------------------update-role-----------------------*/
	@PutMapping("/update-role/{id}")
	public ResponseEntity<Map<String, Object>> updateRole(@PathVariable int id, @RequestParam String data,
			Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<Role> optionalRole = roleRepository.findById(id);

			if (optionalRole.isPresent()) {
				Role role = optionalRole.get();
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(data);

				role.setName(getStringOrNull(json, "name"));

				// Optional: Handle date fields if provided
				if (json.containsKey("fromDate")) {
					role.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse(json.get("fromDate").toString()));
				}

				if (json.containsKey("toDate")) {
					role.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse(json.get("toDate").toString()));
				}

				// Update the Role entity using the repository
				roleRepository.save(role);

				response.put("status", true);
				response.put("data", "Role updated successfully.");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "Role not found with id: " + id);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error updating Role: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*-----------------------delete-role-----------------------*/
	@DeleteMapping("/delete-role/{id}")
	public ResponseEntity<Map<String, Object>> deleteRole(@PathVariable int id, Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<Role> optionalRole = roleRepository.findById(id);

			if (optionalRole.isPresent()) {
				Role role = optionalRole.get();
				// Delete the Role entity using the repository
				roleRepository.delete(role);

				response.put("status", true);
				response.put("data", "Role deleted successfully.");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "Role not found with id: " + id);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error deleting Role: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * -----------------------get-all-roles-----------------------
	 * 
	 * @GetMapping("/get-all-roles") public ResponseEntity<List<Role>> getAllRoles()
	 * { List<Role> roles = roleRepository.findAll(); return new
	 * ResponseEntity<>(roles, HttpStatus.OK); }
	 */

	/*-----------------------get-role-by-id-----------------------*/
	@GetMapping("/get-role/{id}")
	public ResponseEntity<Map<String, Object>> getRoleById(@PathVariable int id, Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<Role> optionalRole = roleRepository.findById(id);

			if (optionalRole.isPresent()) {
				Role role = optionalRole.get();
				response.put("status", true);
				response.put("data", role);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "Role with ID " + id + " not found.");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("status", false);
			response.put("error", "Error fetching Role by ID: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
