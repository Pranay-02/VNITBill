package com.vnit.api.controller;

import java.util.HashMap;import java.util.List;import java.util.Map;import javax.servlet.http.HttpServletRequest;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.DeleteMapping;import org.springframework.web.bind.annotation.ExceptionHandler;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.bind.annotation.ResponseStatus;import org.springframework.web.bind.annotation.RestController;import com.google.gson.JsonArray;import com.google.gson.JsonObject;import com.google.gson.JsonParser;import com.vnit.api.common.RestUtil;import io.swagger.annotations.ApiOperation;import io.swagger.annotations.ApiResponse;
import com.vnit.api.entity.ScholarMst;
import com.vnit.api.entity.ProjectMst;
import com.vnit.api.repo.ScholarRepo;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
public class ScholarController {

	@Autowired
	ScholarRepo repo;

	Map<String, String> map = new HashMap<>();

	@ResponseStatus (code = HttpStatus.OK)
	@PostMapping(path = "/post_scholar", produces = "application/json")
	@ApiOperation(value = "Create or Update scholar entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createScholar(@RequestBody ScholarMst body) {
                                    
		Integer status = 0; 
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(body.getname())) {
                                                                        error.addProperty("name", "name is required");
			}

			if(RestUtil.isNull(body.getage())) {
                                                                       error.addProperty("age", "age is required");
			}

			if(RestUtil.isNull(body.getProject())) {
				error.addProperty("project", "project is required");
			}

			else {
				List<ProjectMst> list = body.getProject();
				for (ProjectMst dtl : list) {
					if (RestUtil.isNull(dtl.getprojectid())) {
						error.addProperty("project.projectid", "projectid is required");
					}
				}
			}
			if (error.entrySet().isEmpty()) {
				status = repo.postScholar(body);
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
	@DeleteMapping(path = "/delete_scholar/{SCHOLARID}", produces = "application/json")
	@ApiOperation(value = "delete scholar entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteScholar(@PathVariable (name = "SCHOLARID") Integer id) {

		Integer status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(id)) {
				error.addProperty("id", "scholarid is required");
			}

			if (error.entrySet().isEmpty()) {
				status = repo.deleteScholar(id);
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
	@GetMapping(path = "/get_scholar/{SCHOLARID}", produces = "application/json")
	@ApiOperation(value = "get scholar entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScholar(@PathVariable(name = "SCHOLARID") Integer id) {

		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(id)) {
				error.addProperty("id", "scholarid is required");
			}

			if (error.entrySet().isEmpty()) {
				ScholarMst hdr = repo.getScholar(id);
				response = JsonParser.parseString(hdr.toString()).getAsJsonObject();
				JsonArray list = new JsonArray();
				for(ProjectMst dtl : hdr.getProject()) {
					list.add(JsonParser.parseString(dtl.toString()));
				}
				response.add("project", list);
				return response.toString();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		response.addProperty("code", 400);
		response.addProperty("status", "Failed");
		response.addProperty("message", "Unable to get data");
		return response.toString();
	}

@ExceptionHandler(CustomException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseBody
public Map<String, Object> handleCustomError(CustomException ex, HttpServletRequest request) {
	Map<String, Object> error = new HashMap<>();
	error.put("code", 400);
	error.put("status", "Failed");
	error.put("message", CustomException.exception);
	return error;
}

public static class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public static String exception;
	public CustomException(String ex) {
		super(ex);
		CustomException.exception = ex;
	}
}
}
