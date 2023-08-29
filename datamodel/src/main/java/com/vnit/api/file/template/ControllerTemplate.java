/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.template;

import java.util.ArrayList;

/**
 *
 * @author Pranay Singhal
 */
public class ControllerTemplate {
    public String getControllerFieldTemplate() {
        String temp = "";
        temp += "^00$01$m:constantsMap:controller_package_name$^" + "\n\n";
        temp += "^00$01$m:constantsMap:controller_imports$^" + "\n";

        temp += "import com.vnit.api.entity.^$00$01$m:constantsMap:cap_table_name$^Mst;\n";
        temp += "import com.vnit.api.repo.^$00$01$m:constantsMap:cap_table_name$^Repo;\n\n";

        temp += "@CrossOrigin(origins=\"*\", maxAge = 3600)\n";
        temp += "@RestController\n";

        temp += "public class ^$00$01$m:constantsMap:cap_table_name$^Controller {\n\n";
        temp += "\t@Autowired\n";
        temp += "\t^$00$01$m:constantsMap:cap_table_name$^Repo repo;\n\n";

        temp += "\tMap<String, String> map = new HashMap<>();\n\n"; 
        return temp;
    }

    public String getControllerCreateTemplate(ArrayList<Object> columns, String primaryColumnName) {
        String temp = "";
        
        temp += "\t@ResponseStatus (code = HttpStatus.OK)\n";
        temp += "\t@PostMapping(path = \"/post_^$00$01$m:constantsMap:table_name$^\", produces = \"application/json\")\n";
        temp += "\t@ApiOperation(value = \"Create or Update ^$00$01$m:constantsMap:table_name$^ entity\", httpMethod = \"POST\")\n";
        temp += "\t@ApiResponse(code = 200, message = \"Returns a 200 response code if successful\")\n";
        
        temp += "\tpublic String create^$00$01$m:constantsMap:cap_table_name$^";
        temp += "(@RequestBody ^$00$01$m:constantsMap:cap_table_name$^Mst body) {\n\n";

        temp += "\t\tInteger status = 0;\n";
        temp += "\t\tJsonObject response = new JsonObject();\n";
        temp += "\t\tJsonObject error = new JsonObject();\n";
        temp += "\t\ttry {\n";

        for(int i = 0; i < columns.size(); i++) {
            String columnName = columns.get(i).getColumnName();
            if(!columnName.equals(primaryColumnName)){
                temp += getColumnsToCreate(columnName);
            }
        }

        temp += "\t\t\tif (error.entrySet().isEmpty()) {\n";
        temp += "\t\t\t\tstatus = repo.post^$00$01$m:constantsMap:cap_table_name$^(body);\n";
        temp += "\t\t\t}\n\n";

        temp += "\t\t} catch (Exception ex) {\n";
        temp += "\t\t\tex.printStackTrace();\n";
        temp += "\t\t}\n\n";

        temp += "\t\tif (status > 0) {\n";
        temp += "\t\t\tresponse.addProperty(\"id\", status);\n";
        temp += "\t\t\tresponse.addProperty(\"code\", 200);\n";
        temp += "\t\t\tresponse.addProperty(\"status\", \"Success\");\n";
        temp += "\t\t\tresponse.addProperty(\"message\", \"Save Successfully\");\n";

        temp += "\t\t} else {\n";
        temp += "\t\t\tresponse.addProperty(\"code\", 400);\n";
        temp += "\t\t\tresponse.addProperty(\"status\", \"Failed\");\n";
        temp += "\t\t\tresponse.addProperty(\"message\", \"Unable to save\");\n";
        temp += "\t\t}\n\n";

        temp += "\t\treturn response.toString();\n";
        temp += "\t}\n\n";

        return temp;

    }

    public String getColumnsToCreate(String columnName) {
        String temp = "";
        temp += "\t\t\tif(RestUtil.isNull(body.get" + "^00$01$m2:fld:" + columnName +":column_name$^" + "())) {\n";
        temp += "\t\t\t\terror.addProperty(\"^00$01$m2:fld:" + columnName +":column_name$^\", ";
        temp += "\"^00$01$m2:fld:" + columnName + ":column_name$^ is required\");\n";
        temp += "\t\t\t}\n\n";
        return temp;
    }

     public String getControllerDeleteTemplate(String primarycolumnName) {
        String temp = "";
        
        temp += "\t@ResponseStatus (code = HttpStatus.OK)\n";
        temp += "\t@DeleteMapping(path = \"/delete_^$00$01$m:constantsMap:table_name$^/{" + getVariableString() +"}\", produces = \"application/json\")\n";
        temp += "\t@ApiOperation(value = \"delete ^$00$01$m:constantsMap:table_name$^ entity\", httpMethod = \"DELETE\")\n";
        temp += "\t@ApiResponse(code = 200, message = \"Returns a 200 response code if successful\")\n";
        
        temp += "\tpublic String delete^$00$01$m:constantsMap:cap_table_name$^";
        temp += "(@PathVariable (name = \"" + getVariableString() + "\") Integer id) {\n\n";

        temp += "\t\tInteger status = 0;\n";
        temp += "\t\tJsonObject response = new JsonObject();\n";
        temp += "\t\tJsonObject error = new JsonObject();\n";
        temp += "\t\ttry {\n";

        temp += "\t\t\tif(RestUtil.isNull(id)) {\n";
        temp += "\t\t\t\terror.addProperty(\"id\", ";
        temp += "\"" + getVariableString() + " is required\");\n";
        temp += "\t\t\t}\n\n";

        temp += "\t\t\tif (error.entrySet().isEmpty()) {\n";
        temp += "\t\t\t\tstatus = repo.delete^$00$01$m:constantsMap:cap_table_name$^(id);\n";
        temp += "\t\t\t}\n\n";

        temp += "\t\t} catch (Exception ex) {\n";
        temp += "\t\t\tex.printStackTrace();\n";
        temp += "\t\t}\n\n";

        temp += "\t\tif (status > 0) {\n";
        temp += "\t\t\tresponse.addProperty(\"code\", 200);\n";
        temp += "\t\t\tresponse.addProperty(\"status\", \"Success\");\n";
        temp += "\t\t\tresponse.addProperty(\"message\", \"Deleted Successfully\");\n";

        temp += "\t\t} else {\n";
        temp += "\t\t\tresponse.addProperty(\"code\", 400);\n";
        temp += "\t\t\tresponse.addProperty(\"status\", \"Failed\");\n";
        temp += "\t\t\tresponse.addProperty(\"message\", \"Unable to delete\");\n";
        temp += "\t\t}\n\n";

        temp += "\t\treturn response.toString();\n";

        temp += "\t}\n\n";
        return temp;

    }

     public String getControllerGetTemplate(String primarycolumnName) {
        String temp = "";
        
        temp += "\t@ResponseStatus (code = HttpStatus.OK)\n";
        temp += "\t@GetMapping(path = \"/get_^$00$01$m:constantsMap:table_name$^/{" + getVariableString() + "}\", produces = \"application/json\")\n";
        temp += "\t@ApiOperation(value = \"get ^$00$01$m:constantsMap:table_name$^ entity\", httpMethod = \"GET\")\n";
        temp += "\t@ApiResponse(code = 200, message = \"Returns a 200 response code if successful\")\n";
        
        temp += "\tpublic String get^$00$01$m:constantsMap:cap_table_name$^";
        temp += "(@PathVariable(name = \"" + getVariableString() +"\") Integer id) {\n\n";

        temp += "\t\tJsonObject response = new JsonObject();\n";
        temp += "\t\tJsonObject error = new JsonObject();\n";
        temp += "\t\ttry {\n";

        temp += "\t\t\tif(RestUtil.isNull(id)) {\n";
        temp += "\t\t\t\terror.addProperty(\"id\", ";
        temp += "\"" + getVariableString() + " is required\");\n";
        temp += "\t\t\t}\n\n";

        temp += "\t\t\tif (error.entrySet().isEmpty()) {\n";
        temp += "\t\t\t\tObjectMapper mapper = new ObjectMapper();\n";
        temp += "\t\t\t\treturn mapper.writeValueAsString(repo.get^$00$01$m:constantsMap:cap_table_name$^(id));\n";
        temp += "\t\t\t}\n\n";

        temp += "\t\t} catch (Exception ex) {\n";
        temp += "\t\t\tex.printStackTrace();\n";
        temp += "\t\t}\n\n";

        temp += "\t\tresponse.addProperty(\"code\", 400);\n";
        temp += "\t\tresponse.addProperty(\"status\", \"Failed\");\n";
        temp += "\t\tresponse.addProperty(\"message\", \"Unable to get data\");\n";
      
        temp += "\t\treturn response.toString();\n";

        temp += "\t}\n\n";
        return temp;

    }

    public String getClosingBracket() {
        return "}";
    }
 
    public String getVariableString() {
        String str = "^$00$01$m:constantsMap:table_name$^id";
        return str;
    }    
}
