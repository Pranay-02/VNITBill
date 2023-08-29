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
import com.vnit.api.entity.ScreenfieldmasterMst;
import com.vnit.api.repo.ScreenfieldmasterRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ScreenfieldmasterController {

    @Autowired
    ScreenfieldmasterRepo repo;
    Map<String, String> map = new HashMap<>();

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(path = "/post_screenfieldmaster", produces = "application/json")
    @ApiOperation(value = "Create or Update screenfieldmaster entity", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String createScreenfieldmaster(@RequestBody ScreenfieldmasterMst body) {
        Integer status = 0;
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        System.out.println(" in create fieldmaster");
        try {
            if (RestUtil.isNull(body.getScreenid())) {
                error.addProperty("screenid", "screenid is required");
            }
            if (RestUtil.isNull(body.getScreengroupid())) {
                error.addProperty("screengroupid", "screengroupid is required");
            }
            if (RestUtil.isNull(body.getField_name())) {
                error.addProperty("field_name", "field_name is required");
            }
            if (RestUtil.isNull(body.getLabel())) {
                error.addProperty("label", "label is required");
            }
            if (RestUtil.isNull(body.getFieldtype())) {
                error.addProperty("fieldtype", "fieldtype is required");
            }
            if (RestUtil.isNull(body.getTablecolumn())) {
                error.addProperty("tablecolumn", "tablecolumn is required");
            }
            if (RestUtil.isNull(body.getRequired())) {
                error.addProperty("required", "required is required");
            }
//            if (RestUtil.isNull(body.getDatabasefuncname())) {
//                error.addProperty("databasefuncname", "databasefuncname is required");
//            }
//            if (RestUtil.isNull(body.getJfunctionname())) {
//                error.addProperty("jfunctionname", "jfunctionname is required");
//            }
//            if (RestUtil.isNull(body.getRangelow())) {
//                error.addProperty("rangelow", "rangelow is required");
//            }
//            if (RestUtil.isNull(body.getRangehigh())) {
//                error.addProperty("rangehigh", "rangehigh is required");
//            }
//            if (RestUtil.isNull(body.getDateformat())) {
//                error.addProperty("dateformat", "dateformat is required");
//            }
//            if (RestUtil.isNull(body.getVisible())) {
//                error.addProperty("visible", "visible is required");
//            }
//            viso
//            if (RestUtil.isNull(body.getInputtype())) {
//                error.addProperty("inputtype", "inputtype is required");
//            }
//            if (RestUtil.isNull(body.getDatatype())) {
//                error.addProperty("datatype", "datatype is required");
//            }
//            if (RestUtil.isNull(body.getDefaultvalue())) {
//                error.addProperty("defaultvalue", "defaultvalue is required");
//            }

            if (error.entrySet().isEmpty()) {
                status = repo.postScreenfieldmaster(body);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("field master save status"+status);
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
    @DeleteMapping(path = "/delete_screenfieldmaster/{SCREENFIELDMASTERID}", produces = "application/json")
    @ApiOperation(value = "Delete screenfieldmaster entity", httpMethod = "DELETE")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String deleteScreenfieldmaster(@PathVariable(name = "SCREENFIELDMASTERID") Integer id) {
        Integer status = 0;
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try {
            if (RestUtil.isNull(id)) {
                error.addProperty("id", "SCREENFIELDMASTERID is required");
            }

            if (error.entrySet().isEmpty()) {
                status = repo.deleteScreenfieldmaster(id);
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
    @GetMapping(path = "/get_screenfieldmaster/{SCREENFIELDMASTERID}", produces = "application/json")
    @ApiOperation(value = "Get screenfieldmaster entity", httpMethod = "GET")
    @ApiResponse(code = 200, message = "Returns a 200 response code if successful")
    public String getScreenfieldmaster(@PathVariable(name = "SCREENFIELDMASTERID") Integer id) {
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try {
            if (RestUtil.isNull(id)) {
                error.addProperty("id", "SCREENFIELDMASTERID is required");
            }

            if (error.entrySet().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(repo.getScreenfieldmaster(id));
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
