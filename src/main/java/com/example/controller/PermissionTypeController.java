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

import com.example.entity.PermissionType;
import com.example.repo.PermissionTypeRepository;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class PermissionTypeController {

	@Autowired
	private PermissionTypeRepository permissionTypeRepository;
	
	
	/*-----------------------get-permission-type------------------------*/
    @GetMapping(value="permission-type",produces="application/json")
    public ResponseEntity<Map<String,Object>> getPermission()
    {
        Map<String,Object> response=new HashMap<String,Object>();
        List<JSONObject> data=null;
        JSONObject json=null;
        try
        {
            List<PermissionType> permissionTypeList=permissionTypeRepository.findAll();
            if(permissionTypeList!=null)
            {

                data=new ArrayList<JSONObject>();
                for(PermissionType permissionType:permissionTypeList)
                {
                    json=new JSONObject();
                    json.put("permissionTypeId", permissionType.getPermissionTypeId());
                    json.put("permissionTypeName",permissionType.getPermissionTypeName());
                    json.put("typeId", permissionType.getType().getTypeId());
                    json.put("typeName",permissionType.getType().getTypeName());
                    json.put("permissionId", permissionType.getPermission().getPermissionId());
                    json.put("permissionName",permissionType.getPermission().getPermissionName());
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
