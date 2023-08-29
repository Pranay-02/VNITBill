package com.vnit.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vnit.api.common.RestUtil;
import com.vnit.api.entity.BillDtlMst;
import com.vnit.api.entity.BillHdrMst;
import com.vnit.api.repo.BillRepo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BillController {
	
	@Autowired
	BillRepo repo;
	
	Map<String, String> map = new HashMap<>();
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_bill", produces = "application/json")
	@ApiOperation(value = "Create or Update bill entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createBill(@RequestBody BillHdrMst body) {
		Integer status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(body.getCcode())) {
				error.addProperty("ccode", "ccode is required");
			}
			if (RestUtil.isNull(body.getBillamt())) {
				error.addProperty("billamt", "billamt is required");
			}
			if (RestUtil.isNull(body.getBilldtl())) {
				error.addProperty("billdtl", "billdtl is required");
			} else {
				List<BillDtlMst> dtlList = body.getBilldtl();
				
				for (BillDtlMst dtl : dtlList) {
					if (RestUtil.isNull(dtl.getItcode())) {
						error.addProperty("billdtl.itcode", "itcode is required");
					}
				}
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.postBill(body);
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
	@DeleteMapping(path = "/delete_bill/{BILLNO}", produces = "application/json")
	@ApiOperation(value = "Delete bill entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteBill(@PathVariable(name = "BILLNO") Integer id) {
		int status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(id)) {
				error.addProperty("id", "BILLNO is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deleteBill(id);
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
	@GetMapping(path = "/get_bill/{BILLNO}", produces = "application/json")
	@ApiOperation(value = "Get bill entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getBill(@PathVariable(name = "BILLNO") Integer id) {
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(id)) {
				error.addProperty("id", "BILLNO is required");
			}
			
			if (error.entrySet().isEmpty()) {
				BillHdrMst hdr = repo.getBill(id);
				response = JsonParser.parseString(hdr.toString()).getAsJsonObject();
				JsonArray dtlList = new JsonArray();
				for (BillDtlMst dtl : hdr.getBilldtl()) {
					dtlList.add(JsonParser.parseString(dtl.toString()));
				}
				response.add("billdtl", dtlList);
				//ObjectMapper mapper = new ObjectMapper();
				return response.toString(); //mapper.writeValueAsString(repo.getBill(id));
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
