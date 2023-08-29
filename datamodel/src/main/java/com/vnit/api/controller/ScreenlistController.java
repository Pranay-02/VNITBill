package com.vnit.api.controller;

import com.google.gson.Gson;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.vnit.api.common.RestUtil;
import com.vnit.api.entity.ScreenlistHdrMst;
import com.vnit.api.entity.ScreenlistdtlMst;
import com.vnit.api.repo.ScreenlistRepo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.io.StringReader;
import static javassist.CtMethod.ConstParameter.string;
import org.json.JSONObject;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ScreenlistController {
    	@Autowired
	ScreenlistRepo repo;
        
        Map<String, String> map = new HashMap<>();
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_screenlist", produces = "application/json")
	@ApiOperation(value = "Create or Update Screen entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createScreenlist(@RequestBody ScreenlistHdrMst body) {
		Integer status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
                        if (RestUtil.isNull(body.getQuery())) {
				error.addProperty("query", "query is required");
			}
			if (RestUtil.isNull(body.getJfunction())) {
				error.addProperty("jfunction", "jfunction is required");
			}
			if (RestUtil.isNull(body.getListname())) {
				error.addProperty("listname", "listname is required");
			}
			if (RestUtil.isNull(body.getScreenlistdtl())) {
				error.addProperty("screenlistdtl", "screenlistdtl is required");
			} else {
				List<ScreenlistdtlMst> scList = body.getScreenlistdtl();
				
				for (ScreenlistdtlMst sc: scList) {
					if (RestUtil.isNull(sc.getScreenfieldid())) {
						error.addProperty("screenlistdtl.screenfieldid", "screenfieldid is required");
					}
				}
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.postScreenlist(body);
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
	@DeleteMapping(path = "/delete_screenlist/{SCREENLISTNO}", produces = "application/json")
	@ApiOperation(value = "Delete screenlist entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deleteScreenlist(@PathVariable(name = "SCREENLISTNO") Integer id) {
		int status = 0;
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
		try {
			if (RestUtil.isNull(id)) {
				error.addProperty("id", "SCREENLISTNO is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deleteScreenlist(id);
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
	@GetMapping(path = "/get_screenlist/{SCREENLISTNO}", produces = "application/json")
	@ApiOperation(value = "Get screenlist entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenlist(@PathVariable(name = "SCREENLISTNO") Integer id) {
		JsonObject response = new JsonObject();
		JsonObject error = new JsonObject();
                Gson gson = new Gson();
                
//                reader.setLenient(true);
		try {
			if (RestUtil.isNull(id)) {
				error.addProperty("id", "SCREENLISTNO is required");
			}
			
			if (error.entrySet().isEmpty()) {
				ScreenlistHdrMst hdr = repo.getScreenlist(id);
                                JsonParser parser = new JsonParser();
                                String json = hdr.toString();
                                
//                                JsonReader reader = new JsonReader(new StringReader(json));
//                                reader.setLenient(true);
//                                System.out.println(response.getClass().getSimpleName());
                               
				response = JsonParser.parseString(json.trim()).getAsJsonObject();
                                
//                                System.out.println(response.getAsJsonPrimitive(json));
//                                System.out.println(response.getClass().getSimpleName());
                                
				JsonArray dtlList = new JsonArray();
                                
				for (ScreenlistdtlMst dtl : hdr.getScreenlistdtl()) {
					dtlList.add(JsonParser.parseString(dtl.toString()));
				}
				response.add("screenlistdtl", dtlList);
				
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
