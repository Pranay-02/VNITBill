package com.vnit.api.controller;

import java.util.HashMap;import java.util.Map;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.DeleteMapping;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.ResponseStatus;import org.springframework.web.bind.annotation.RestController;import com.fasterxml.jackson.databind.ObjectMapper;import com.google.gson.JsonObject;import com.vnit.api.common.RestUtil;import io.swagger.annotations.ApiOperation;import io.swagger.annotations.ApiResponse;
import com.vnit.api.entity.DepttypeMst;
import com.vnit.api.repo.DepttypeRepo;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
public class DepttypeController {

	@Autowired
	DepttypeRepo repo;

	Map<String, String> map = new HashMap<>();

	@ResponseStatus (code = HttpStatus.OK)
	@PostMapping(path = "/post_depttype", produces = "application/json")
	@ApiOperation(value = "Create or Update depttype entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createDepttype(@RequestBody DepttypeMst body) {

		Integer status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(body.getdeptid())) {
				error.addProperty("deptid", "deptid is required");
			}

			if(RestUtil.isNull(body.getcourseid())) {
				error.addProperty("courseid", "courseid is required");
			}

			if (error.entrySet().isEmpty()) {
				status = repo.postDepttype(body);
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

	@ResponseStatus (code = HttpStatus.OK)
	@DeleteMapping(path = "/delete_depttype/{depttypeid}", produces = "application/json")
	@ApiOperation(value = "delete depttype entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteDepttype(@PathVariable (name = "depttypeid") Integer id) {

		Integer status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(id)) {
				error.addProperty("id", "depttypeid is required");
			}

			if (error.entrySet().isEmpty()) {
				status = repo.deleteDepttype(id);
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

	@ResponseStatus (code = HttpStatus.OK)
	@GetMapping(path = "/get_depttype/{depttypeid}", produces = "application/json")
	@ApiOperation(value = "get depttype entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getDepttype(@PathVariable(name = "depttypeid") Integer id) {

		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(id)) {
				error.addProperty("id", "depttypeid is required");
			}

			if (error.entrySet().isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(repo.getDepttype(id));
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