package com.vnit.api.controller;

import java.util.HashMap;import java.util.List;import java.util.Map;import javax.servlet.http.HttpServletRequest;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.DeleteMapping;import org.springframework.web.bind.annotation.ExceptionHandler;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.bind.annotation.ResponseStatus;import org.springframework.web.bind.annotation.RestController;import com.google.gson.JsonArray;import com.google.gson.JsonObject;import com.google.gson.JsonParser;import com.vnit.api.common.RestUtil;import io.swagger.annotations.ApiOperation;import io.swagger.annotations.ApiResponse;
import com.vnit.api.entity.DepartmentMst;
import com.vnit.api.entity.DeptdtlMst;
import com.vnit.api.repo.DepartmentRepo;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
public class DepartmentController {

	@Autowired
	DepartmentRepo repo;

	Map<String, String> map = new HashMap<>();

	@ResponseStatus (code = HttpStatus.OK)
	@PostMapping(path = "/post_department", produces = "application/json")
	@ApiOperation(value = "Create or Update department entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createDepartment(@RequestBody DepartmentMst body) {

		Integer status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(body.getname())) {
				error.addProperty("name", "name is required");
			}

			if(RestUtil.isNull(body.getdepttp())) {
				error.addProperty("depttp", "depttp is required");
			}

			if(RestUtil.isNull(body.getDeptdtl())) {
				error.addProperty("deptdtl", "deptdtl is required");
			}

			else {
				List<DeptdtlMst> list = body.getDeptdtl();
				for (DeptdtlMst dtl : list) {
					if (RestUtil.isNull(dtl.getcourseid())) {
						error.addProperty("deptdtl.courseid", "courseid is required");
					}
				}
			}
			if (error.entrySet().isEmpty()) {
				status = repo.postDepartment(body);
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
	@DeleteMapping(path = "/delete_department/{DEPTID}", produces = "application/json")
	@ApiOperation(value = "delete department entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteDepartment(@PathVariable (name = "DEPTID") Integer id) {

		Integer status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(id)) {
				error.addProperty("id", "deptid is required");
			}

			if (error.entrySet().isEmpty()) {
				status = repo.deleteDepartment(id);
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
	@GetMapping(path = "/get_department/{DEPTID}", produces = "application/json")
	@ApiOperation(value = "get department entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getDepartment(@PathVariable(name = "DEPTID") Integer id) {

		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if(RestUtil.isNull(id)) {
				error.addProperty("id", "deptid is required");
			}

			if (error.entrySet().isEmpty()) {
				DepartmentMst hdr = repo.getDepartment(id);
				response = JsonParser.parseString(hdr.toString()).getAsJsonObject();
				JsonArray list = new JsonArray();
				for(DeptdtlMst dtl : hdr.getDeptdtl()) {
					list.add(JsonParser.parseString(dtl.toString()));
				}
				response.add("deptdtl", list);
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
