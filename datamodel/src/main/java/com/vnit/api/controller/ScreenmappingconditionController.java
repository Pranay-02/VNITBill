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
import com.vnit.api.entity.ScreenmappingconditionMst;
import com.vnit.api.repo.ScreenmappingconditionRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ScreenmappingconditionController {

    @Autowired
    ScreenmappingconditionRepo repo;
    Map<String, String> map = new HashMap<>();

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(path = "/post_screenmappingcondition", produces = "application/json")
    @ApiOperation(value = "Create or Update screenmappingcondition entity", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String createScreenmappingcondition(@RequestBody ScreenmappingconditionMst body) {
        Integer status = 0;
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try {
            if (RestUtil.isNull(body.getScreenid())) {
                error.addProperty("screenid", "screenid is required");
            }
            if (RestUtil.isNull(body.getScreengroupid())) {
                error.addProperty("screengroupid", "screengroupid is required");
            }
            if (RestUtil.isNull(body.getScreenfieldid())) {
                error.addProperty("screenfieldid", "screenfieldid is required");
            }
            if (RestUtil.isNull(body.getQuerycolumn())) {
                error.addProperty("querycolumn", "querycolumn is required");
            }

            if (error.entrySet().isEmpty()) {
                status = repo.postScreenmappingcondition(body);
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
    @DeleteMapping(path = "/delete_screenmappingcondition/{SCREENMAPPINGCONDITIONID}", produces = "application/json")
    @ApiOperation(value = "Delete screenmappingcondition entity", httpMethod = "DELETE")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String deleteScreenmappingcondition(@PathVariable(name = "SCREENMAPPINGCONDITIONID") Integer id) {
        Integer status = 0;
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try {
            if (RestUtil.isNull(id)) {
                error.addProperty("id", "SCREENMAPPINGCONDITIONID is required");
            }

            if (error.entrySet().isEmpty()) {
                status = repo.deleteScreenmappingcondition(id);
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
    @GetMapping(path = "/get_screenmappingcondition/{SCREENMAPPINGCONDITIONID}", produces = "application/json")
    @ApiOperation(value = "Get screenmappingcondition entity", httpMethod = "GET")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String getScreenmappingcondition(@PathVariable(name = "SCREENMAPPINGCONDITIONID") Integer id) {
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try {
            if (RestUtil.isNull(id)) {
                error.addProperty("id", "SCREENMAPPINGCONDITIONID is required");
            }

            if (error.entrySet().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(repo.getScreenmappingcondition(id));
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
