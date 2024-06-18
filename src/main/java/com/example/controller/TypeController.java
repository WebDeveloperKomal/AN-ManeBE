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

import com.example.entity.Type;
import com.example.repo.TypeRepository;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class TypeController {

	
	@Autowired
	private TypeRepository typeRepository;

	
	/*---------------------------get-type-------------------------------*/
	@GetMapping("/get-all-type")
	public ResponseEntity<Map<String, Object>> getType() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<JSONObject> data = null;
		JSONObject json = null;

		try {
			List<Type> typeList = typeRepository.findAll();
			if (typeList != null) {
				data = new ArrayList<>();
				for (Type type : typeList) {
					json = new JSONObject();
					json.put("typeId", type.getTypeId());
					json.put("typeName", type.getTypeName());
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
	
}
