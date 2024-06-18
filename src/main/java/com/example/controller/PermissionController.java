package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Permission;
import com.example.repo.PermissionRepository;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class PermissionController {

	@Autowired
	private PermissionRepository permissionRepository;

	
	/* -----------------------------get-permission---------------------------------*/
    @GetMapping("/get-all-permission")
    public ResponseEntity<Map<String,Object>> getPermission()
    {
        Map<String,Object> response=new HashMap<>();
        List<JSONObject> data=null;
        JSONObject json=null;
        try
        {
            List<Permission> permissionList=permissionRepository.findAll();
            if(permissionList!=null)
            {
                data=new ArrayList<>();
                json=new JSONObject();
                for(Permission permission:permissionList)
                {
                    json=new JSONObject();
                    json.put("permissionId", permission.getPermissionId());
                    json.put("permissionName", permission.getPermissionName());
                    data.add(json);
                }
            }
            response.put("status", true);
            response.put("data", data);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("status", false);
            response.put("error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
