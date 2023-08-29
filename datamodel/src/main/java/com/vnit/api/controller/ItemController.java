package com.vnit.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.vnit.api.common.RestUtil;
import com.vnit.api.entity.ItemMst;
import com.vnit.api.repo.ItemRepo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ItemController {
	
	@Autowired
	ItemRepo repo;
	
Map<String, String> map = new HashMap<>();
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_item", produces = "application/json")
	@ApiOperation(value = "Create or Update item entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createItem(@RequestBody ItemMst body) {
		int status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(body.getItname())) {
				error.addProperty("itname", "itname is required");
			}
			if (RestUtil.isNull(body.getItrate())) {
				error.addProperty("itrate", "itrate is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.postItem(body);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if (status > 0) {
			response.addProperty("id", status);
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("message", "Save Successfully");
		} else {
			response.addProperty("code", 400);
			response.addProperty("status", "Failed");
			response.addProperty("message", "Unable to save");
		}
		
		return response.toString();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping(path = "/delete_item/{ITEMCODE}", produces = "application/json")
	@ApiOperation(value = "Delete item entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteItem(@PathVariable(name = "ITEMCODE") Integer id) {
		int status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(id)) {
				error.addProperty("id", "ITEMCODE is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deleteItem(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if (status > 0) {
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("message", "Deleted Successfully");
		} else {
			response.addProperty("code", 400);
			response.addProperty("status", "Failed");
			response.addProperty("message", "Unable to delete");
		}
		
		return response.toString();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_item/{ITEMCODE}", produces = "application/json")
	@ApiOperation(value = "Get item entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getItem(@PathVariable(name = "ITEMCODE") Integer id) {
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(id)) {
				error.addProperty("id", "ITEMCODE is required");
			}
			
			if (error.entrySet().isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(repo.getItem(id));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		response.addProperty("code", 400);
		response.addProperty("status", "Failed");
		response.addProperty("message", "Unable to get data");
		
		return response.toString();
	}

}
