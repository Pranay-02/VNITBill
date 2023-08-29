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
import com.vnit.api.entity.ScreenjoinconditionMst;
import com.vnit.api.repo.ScreenjoinconditionRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ScreenjoinconditionController {

    @Autowired
    ScreenjoinconditionRepo repo;
    Map<String, String> map = new HashMap<>();

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(path = "/post_screenjoincondition", produces = "application/json")
    @ApiOperation(value = "Create or Update screenjoincondition entity", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String createScreenjoincondition(@RequestBody ScreenjoinconditionMst body) {
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
            if (RestUtil.isNull(body.getMastergroupcol())) {
                error.addProperty("mastergroupcol", "mastergroupcol is required");
            }
            if (RestUtil.isNull(body.getGroupcol())) {
                error.addProperty("groupcol", "groupcol is required");
            }

            if (error.entrySet().isEmpty()) {
                status = repo.postScreenjoincondition(body);
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
    @DeleteMapping(path = "/delete_screenjoincondition/{SCREENJOINCONDITIONID}", produces = "application/json")
    @ApiOperation(value = "Delete screenjoincondition entity", httpMethod = "DELETE")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String deleteScreenjoincondition(@PathVariable(name = "SCREENJOINCONDITIONID") Integer id) {
        Integer status = 0;
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try {
            if (RestUtil.isNull(id)) {
                error.addProperty("id", "SCREENJOINCONDITIONID is required");
            }

            if (error.entrySet().isEmpty()) {
                status = repo.deleteScreenjoincondition(id);
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
    @GetMapping(path = "/get_screenjoincondition/{SCREENJOINCONDITIONID}", produces = "application/json")
    @ApiOperation(value = "Get screenjoincondition entity", httpMethod = "GET")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String getScreenjoincondition(@PathVariable(name = "SCREENJOINCONDITIONID") Integer id) {
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try {
            if (RestUtil.isNull(id)) {
                error.addProperty("id", "SCREENJOINCONDITIONID is required");
            }

            if (error.entrySet().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(repo.getScreenjoincondition(id));
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
