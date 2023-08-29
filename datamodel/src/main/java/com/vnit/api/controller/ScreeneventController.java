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
import com.vnit.api.entity.ScreeneventMst;
import com.vnit.api.repo.ScreeneventRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ScreeneventController{
	
	@Autowired
ScreeneventRepo repo;
 Map<String, String> map = new HashMap<>();

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_screenevent", produces = "application/json")
	@ApiOperation(value = "Create or Update screenevent entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createScreenevent(@RequestBody ScreeneventMst body) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
if (RestUtil.isNull(body.getScreenid())) {
				error.addProperty("screenid", "screenid is required");
			}
if (RestUtil.isNull(body.getScreencomponent())) {
				error.addProperty("screencomponent", "screencomponent is required");
			}
if (RestUtil.isNull(body.getScreencomponentid())) {
				error.addProperty("screencomponentid", "screencomponentid is required");
			}
if (RestUtil.isNull(body.getEventtype())) {
				error.addProperty("eventtype", "eventtype is required");
			}
			
				if (error.entrySet().isEmpty()) {
				status = repo.postScreenevent(body);
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
	@DeleteMapping(path = "/delete_screenevent/{SCREENEVENTID}", produces = "application/json")
	@ApiOperation(value = "Delete screenevent entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteScreenevent(@PathVariable(name = "SCREENEVENTID") Integer id) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (RestUtil.isNull(id)) {
				error.addProperty("id", "SCREENEVENTID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deleteScreenevent(id);
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
	@GetMapping(path = "/get_screenevent/{SCREENEVENTID}", produces = "application/json")
	@ApiOperation(value = "Get screenevent entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenevent(@PathVariable(name = "SCREENEVENTID") Integer id) {
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (RestUtil.isNull(id)) {
				error.addProperty("id", "SCREENEVENTID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(repo.getScreenevent(id));
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