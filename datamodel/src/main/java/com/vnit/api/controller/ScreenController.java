package com.vnit.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.google.gson.stream.JsonReader;
import com.vnit.api.common.RestUtil;
import com.vnit.api.entity.ScreengroupMst;
import com.vnit.api.entity.ScreenMst;
import com.vnit.api.repo.ScreenRepo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.io.StringReader;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class ScreenController {
        @Autowired
        ScreenRepo repo;
        
        Map<String, String> map = new HashMap<>();
        public static Map<String, ScreenMst> map1 = new HashMap<>();
        
        @ResponseStatus(code = HttpStatus.OK)
        @PostMapping(path = "/post_screen", produces = "application/json")
        @ApiOperation(value = "Create or Update screen entity", httpMethod = "POST")
        @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
        public String createScreen(@RequestBody ScreenMst body) {
		Integer status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(body.getScreendate())) {
				error.addProperty("screendate", "screendate is required");
			}
			if (RestUtil.isNull(body.getScreenpurpose())) {
				error.addProperty("screenpurpose", "screenpurpose is required");
			}
                                                    if (RestUtil.isNull(body.getScreenname())) {
				error.addProperty("screenname", "screenname is required");
			}
                                                    if (map1.containsKey(body.getScreenname())) {
				error.addProperty("screenname", "screenname already exist");
			}
			if (RestUtil.isNull(body.getScreengroup())) {
				error.addProperty("screengroup", "screengroup is required");
			} else {
				List<ScreengroupMst> dtlList = body.getScreengroup();
				
				for (ScreengroupMst dtl : dtlList) {
					if (RestUtil.isNull(dtl.getScreengroupid())) {
						error.addProperty("screengroup.screengroupid", "screengroupid is required");
					}
				}
			}
			
			if (error.entrySet().isEmpty()) {
                            System.out.println("Check:1");
                                                        map1.put(body.getScreenname(), body);
			    status = repo.postScreen(body);
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
	@DeleteMapping(path = "/delete_screen/{SCREENID}", produces = "application/json")
	@ApiOperation(value = "Delete screen entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteScreen(@PathVariable(name = "SCREENID") Integer id) {
		int status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(id)) {
				error.addProperty("id", "SCREENID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deleteScreen(id);
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
	@GetMapping(path = "/get_screen/{SCREENID}", produces = "application/json")
	@ApiOperation(value = "Get screen entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreen(@PathVariable(name = "SCREENID") Integer id) {
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(id)) {
				error.addProperty("id", "SCREENID is required");
			}
			
			if (error.entrySet().isEmpty()) {
                            System.out.println("Check:Do Check");
                            
				ScreenMst hdr = repo.getScreen(id);
                                                                 
                                System.out.println("Check:Do Check 1");
                                

                                JsonReader reader;
                                 reader = new JsonReader(new StringReader(hdr.toString().trim()));
                                reader.setLenient(true);
				response = JsonParser.parseReader(reader).getAsJsonObject();
                                System.out.println(response);
				JsonArray dtlList = new JsonArray();
				for (ScreengroupMst dtl : hdr.getScreengroup()) {
                                    System.out.println(dtl);
					dtlList.add(JsonParser.parseString(dtl.toString()));
				}
				response.add("screengroup", dtlList);
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