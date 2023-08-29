package com.vnit.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.vnit.api.common.RestUtil;
import com.vnit.api.entity.ScreengroupMst;
import com.vnit.api.repo.ScreengroupRepo;
import java.util.*;

public class ScreengroupController
{

    public ScreengroupController()
    {
        map = new HashMap();
    }

    public String createScreengroup(ScreengroupMst body)
    {
        Integer status = Integer.valueOf(0);
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try
        {
            if(RestUtil.isNull(body.getGpurpose()))
                error.addProperty("grouppurpose", "grouppurpose is required");
            if(RestUtil.isNull(Boolean.valueOf(body.getDb())))
                error.addProperty("database", "database is required");
            if(RestUtil.isNull(body.getBasetable()))
                error.addProperty("basetable", "basetable is required");
            if(RestUtil.isNull(Boolean.valueOf(body.getDetailtable())))
                error.addProperty("detailtable", "detailtable is required");
            if(RestUtil.isNull(body.getMastergroupname()))
                error.addProperty("mastergroup_name", "mastergroup_name is required");
            if(RestUtil.isNull(Boolean.valueOf(body.getMapping())))
                error.addProperty("mapping", "mapping is required");
            if(RestUtil.isNull(body.getMappingtable()))
                error.addProperty("mapping_table", "mapping_table is required");
            if(RestUtil.isNull(body.getRecordgroupcount()))
                error.addProperty("record_group_count", "record_group_count is required");
            if(RestUtil.isNull(body.getBasiclayout()))
                error.addProperty("basic_layout", "basic_layout is required");
            if(error.entrySet().isEmpty())
                status = repo.postScreengroup(body);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(status.intValue() > 0)
        {
            response.addProperty("id", status);
            response.addProperty("code", Integer.valueOf(200));
            response.addProperty("status", "Success");
            response.addProperty("message", "Save Successfully");
        } else
        {
            response.addProperty("code", Integer.valueOf(400));
            response.addProperty("status", "Failed");
            response.addProperty("message", "Unable to save");
        }
        return response.toString();
    }

    public String deleteScreengroup(Integer id)
    {
        Integer status = Integer.valueOf(0);
        JsonObject response = new JsonObject();
        JsonObject error = new JsonObject();
        try
        {
            if(RestUtil.isNull(id))
                error.addProperty("id", "SCREENGROUPID is required");
            if(error.entrySet().isEmpty())
                status = repo.deleteScreengroup(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(status.intValue() > 0)
        {
            response.addProperty("code", Integer.valueOf(200));
            response.addProperty("status", "Success");
            response.addProperty("message", "Deleted Successfully");
        } else
        {
            response.addProperty("code", Integer.valueOf(400));
            response.addProperty("status", "Failed");
            response.addProperty("message", "Unable to delete");
        }
        return response.toString();
    }

    public String getScreengroup(Integer id)
    {
         JsonObject response = new JsonObject();
      JsonObject error = new JsonObject();

      try {
         if (RestUtil.isNull(id)) {
            error.addProperty("id", "SCREENGROUPID is required");
         }

         if (error.entrySet().isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this.repo.getScreengroup(id));
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      response.addProperty("code", 400);
      response.addProperty("status", "Failed");
      response.addProperty("message", "Unable to get data");
      return response.toString();

    }

    ScreengroupRepo repo;
    Map map;
}