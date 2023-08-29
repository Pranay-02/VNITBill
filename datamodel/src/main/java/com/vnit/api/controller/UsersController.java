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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import com.vnit.api.entity.UsersMst;
import com.vnit.api.repo.UsersRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UsersController{
	
	@Autowired
 UsersRepo repo;
 Map<String, String> map = new HashMap<>();

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_users", produces = "application/json")
	@ApiOperation(value = "Create or Update users entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createUsers(@RequestBody UsersMst body) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
                        if (RestUtil.isNull(body.getName())) {
				error.addProperty("name", "name is required");
			}
                        if (RestUtil.isNull(body.getPassword())) {
				error.addProperty("password", "password is required");
			}
                        if (RestUtil.isNull(body.getEmail())) {
				error.addProperty("email", "email is required");
			}
                        if (RestUtil.isNull(body.getRole())) {
				error.addProperty("role", "role is required");
			}
                        if (RestUtil.isNull(body.getStatus())) {
				error.addProperty("status", "status is required");
			}
                        if (RestUtil.isNull(body.getMobile())) {
				error.addProperty("mobile", "mobile is required");
			}
                        if (RestUtil.isNull(body.getReg_date())) {
				error.addProperty("reg_date", "reg_date is required");
			}
			
				if (error.entrySet().isEmpty()) {
				status = repo.postUsers(body);
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
	@DeleteMapping(path = "/delete_users/{USERSID}", produces = "application/json")
	@ApiOperation(value = "Delete users entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteUsers(@PathVariable(name = "USERSID") Integer id) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (RestUtil.isNull(id)) {
				error.addProperty("id", "USERSID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deleteUsers(id);
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
	@GetMapping(path = "/get_users/{USERSID}", produces = "application/json")
	@ApiOperation(value = "Get users entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getUsers(@PathVariable(name = "USERSID") Integer id) {
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (RestUtil.isNull(id)) {
				error.addProperty("id", "USERSID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(repo.getUsers(id));
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