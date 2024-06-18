package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.PermissionType;
import com.example.entity.Role;
import com.example.entity.RolePermissionType;
import com.example.repo.RolePermissionTypeRepository;
import com.example.repo.RoleRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class RolePermissionTypeController {
	
	
	@Autowired
	private RolePermissionTypeRepository rolePermissionTypeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	/*-----------------------get-role-permission-type-----------------------*/
	@GetMapping(value = "role-permission-type/{roleId}", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getPermission(@PathVariable int roleId) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<JSONObject> data = null;
		JSONObject json = null;
		try {
			List<RolePermissionType> rolePermissionTypeList = rolePermissionTypeRepository.findByRoleId(roleId);
			if (rolePermissionTypeList != null) {

				data = new ArrayList<JSONObject>();
				for (RolePermissionType rolePermissionType : rolePermissionTypeList) {
					json = new JSONObject();
					json.put("rolePermissionTypeId", rolePermissionType.getPermissionType().getPermissionTypeId());
					json.put("permissionId", rolePermissionType.getPermissionType().getPermission().getPermissionId());
					json.put("typeId", rolePermissionType.getPermissionType().getType().getTypeId());
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



	/*-----------------------save-role-permission-type--------------------*/
	@PostMapping(value = "role-permission-type", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getPermission(@RequestBody String data) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(data);
	        List<RolePermissionType> list = new ArrayList<>();

	        String rule = json.get("PermissionRule").toString();
	        rule = rule.substring(1, rule.length() - 1);
	        System.out.println(rule);
	        String[] str = rule.split(",");
	        for (String s : str) {
	            RolePermissionType rolePermissionType = new RolePermissionType();
	            PermissionType permissionType = new PermissionType();
	            permissionType.setPermissionTypeId(Integer.parseInt(s));
	            rolePermissionType.setPermissionType(permissionType);
	            list.add(rolePermissionType);
	        }

	        // Save Role and RolePermissionType in a single transaction
	        Role role = new Role();
	        role.setName(json.get("roleName").toString());
	        roleRepository.save(role);

	        for (RolePermissionType r : list) {
	            r.setRole(role);
	        }
	        rolePermissionTypeRepository.saveAll(list);

	        response.put("status", true);
	        response.put("data", "Record saved");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception ex) {
	        response.put("status", false);
	        response.put("error", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	

	/*-----------------------update-role-permission-type--------------------*/
	@PutMapping(value="role-permission-type", produces="application/json")
	public ResponseEntity<Map<String, Object>> updatePermission(@RequestBody String data) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(data);
	        int roleId = Integer.parseInt(json.get("roleId").toString());
	  
	        // Custom logic for updating RolePermissionType directly within the API
	        rolePermissionTypeRepository.deleteRolePermissionTypeByRoleId(roleId);

	        // Get Role object
	        Role role = roleRepository.findById(roleId)
	                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + roleId));

	        // Create RolePermissionType objects
	        List<RolePermissionType> list = new ArrayList<>();
	        String rule = json.get("PermissionRule").toString();
	        rule = rule.substring(1, rule.length() - 1);
	        String[] str = rule.split(",");
	        for (String s : str) {
	            RolePermissionType rolePermissionType = new RolePermissionType();
	            PermissionType permissionType = new PermissionType();
	            permissionType.setPermissionTypeId(Integer.parseInt(s));
	            rolePermissionType.setPermissionType(permissionType);
	            rolePermissionType.setRole(role); // Set Role directly
	            list.add(rolePermissionType);
	        }

	        // Save RolePermissionType objects
	        rolePermissionTypeRepository.saveAll(list);
	        roleRepository.save(role);

	        response.put("status", true);
	        response.put("data", "Record updated");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception ex) {
	        response.put("status", false);
	        response.put("error", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
}
