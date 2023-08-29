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
import com.vnit.api.entity.EventmasterMst;
import com.vnit.api.repo.EventmasterRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EventmasterController{
	
	@Autowired
EventmasterRepo repo;
 Map<String, String> map = new HashMap<>();

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_eventmaster", produces = "application/json")
	@ApiOperation(value = "Create or Update eventmaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createEventmaster(@RequestBody EventmasterMst body) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
if (RestUtil.isNull(body.getEventname())) {
				error.addProperty("eventname", "eventname is required");
			}
if (RestUtil.isNull(body.getEventpurpose())) {
				error.addProperty("eventpurpose", "eventpurpose is required");
			}
if (RestUtil.isNull(body.getScreenevent())) {
				error.addProperty("screenevent", "screenevent is required");
			}
if (RestUtil.isNull(body.getScreengroupevent())) {
				error.addProperty("screengroupevent", "screengroupevent is required");
			}
if (RestUtil.isNull(body.getScreenrecordevent())) {
				error.addProperty("screenrecordevent", "screenrecordevent is required");
			}
if (RestUtil.isNull(body.getScreenfieldevent())) {
				error.addProperty("screenfieldevent", "screenfieldevent is required");
			}
			
				if (error.entrySet().isEmpty()) {
				status = repo.postEventmaster(body);
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
	@DeleteMapping(path = "/delete_eventmaster/{EVENTMASTERID}", produces = "application/json")
	@ApiOperation(value = "Delete eventmaster entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteEventmaster(@PathVariable(name = "EVENTMASTERID") Integer id) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (RestUtil.isNull(id)) {
				error.addProperty("id", "EVENTMASTERID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deleteEventmaster(id);
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
	@GetMapping(path = "/get_eventmaster/{EVENTMASTERID}", produces = "application/json")
	@ApiOperation(value = "Get eventmaster entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEventmaster(@PathVariable(name = "EVENTMASTERID") Integer id) {
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (RestUtil.isNull(id)) {
				error.addProperty("id", "EVENTMASTERID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(repo.getEventmaster(id));
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
