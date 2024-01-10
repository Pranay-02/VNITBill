/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.t1Template;

/**
 *
 * @author Pranay Singhal
 */
public class ApiTemplate {
    
    public String getTemplate(String columnName) {
        String temp = "";
        
        temp += "@SuppressWarnings(\"unchecked\")\n" + 
	"@ResponseStatus(code = HttpStatus.OK)\n" + 
	"@GetMapping(path = \"/get_^$00$01$m:constantsMap:table_name$^_list\", produces = \"application/json\")\n" + 
	"@ApiOperation(value = \"Get ^$00$01$m:constantsMap:table_name$^ list\", httpMethod = \"GET\")\n" + 
	"@ApiResponse(code = 200, message = \"Returns a 200 response code if successful\")\n" + 
	"public String get^$00$01$m:constantsMap:cap_table_name$^List(@RequestParam String name) {\n" + 
		"\t\tJsonObject response = new JsonObject();\n" + 
		
		"\t\tList<^$00$01$m:constantsMap:cap_table_name$^Mst> ^$00$01$m:constantsMap:table_name$^List = new ArrayList<>();\n" + 
		"\t\tString query = \"\";\n" + 
		"\t\tObjectMapper mapper = new ObjectMapper();\n" + 
		"\t\ttry {\n" + 
			"\t\t\tif (RestUtil.isNull(name)) {\n" + 
				"\t\t\t\tquery =\"select * from ^$00$01$m:constantsMap:table_name$^ order by " + columnName + " limit 10\";\n" + 
				"\t\t\t\t^$00$01$m:constantsMap:table_name$^List = em.createNativeQuery(query, ^$00$01$m:constantsMap:cap_table_name$^Mst.class).getResultList();\n" + 
			"\t\t\t} else {\n" + 
				"\t\t\t\tquery =\"select * from ^$00$01$m:constantsMap:table_name$^ where name like '%\" + name + \"%' order by " + columnName + " desc limit 10\";\n" + 
				"\t\t\t\t^$00$01$m:constantsMap:table_name$^List = em.createNativeQuery(query, ^$00$01$m:constantsMap:cap_table_name$^Mst.class).getResultList();\n" + 
			"\t\t\t}\n\n" + 
			
			"\t\t\tresponse.add(\"data\", JsonParser.parseString(mapper.writeValueAsString(^$00$01$m:constantsMap:table_name$^List)));\n" + 
		"\t\t} catch (Exception ex) {\n" + 
			"\t\t\tex.printStackTrace();\n" + 
			"\t\t\tresponse.add(\"data\", new JsonArray());\n" + 
		"\t\t}\n\n" + 
		
		"\t\tresponse.addProperty(\"code\", 200);\n" + 
		"\t\tresponse.addProperty(\"status\", \"Success\");\n\n" + 
		
		"\t\treturn response.toString();\n" + 
	"}\n\n";
        
        return temp;
    }
}
