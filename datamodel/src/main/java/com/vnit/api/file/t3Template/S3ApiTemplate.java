/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.t3Template;

/**
 *
 * @author Pranay Singhal
 */
public class S3ApiTemplate {
    
  public String getFunction1(String columnName) {
        String temp = "";
        temp += "\t@SuppressWarnings(\"unchecked\")\n" + 
	"\t@ResponseStatus(code = HttpStatus.OK)\n" + 
	"\t@GetMapping(path = \"/get_^$00$01$m:constantsMap:table_name1$^_list\", produces = \"application/json\")\n" + 
	"\t@ApiOperation(value = \"Get ^$00$01$m:constantsMap:cap_table_name1$^ list\", httpMethod = \"GET\")\n" + 
	"\t@ApiResponse(code = 200, message = \"Returns a 200 response code if successfull\")\n" + 
	"\tpublic String get^$00$01$m:constantsMap:cap_table_name1$^List(@RequestParam String " + columnName + ") {\n" + 
		"\t\tJsonObject response = new JsonObject();\n" + 
		
		"\t\tList<^$00$01$m:constantsMap:cap_table_name1$^> ^$00$01$m:constantsMap:table_name1$^List = new ArrayList<>();\n" + 
		"\t\tString query = \"\";\n" + 
		"\t\ttry {\n" + 
			"\t\t\tif (RestUtil.isNull(" + columnName + ")) {\n" + 
				"\t\t\t\tquery =\"select * from ^$00$01$m:constantsMap:table_name1$^ order by " + columnName + " limit 10\";\n" + 
				"\t\t\t\t^$00$01$m:constantsMap:table_name1$^List = em.createNativeQuery(query, ^$00$01$m:constantsMap:cap_table_name1$^.class).getResultList();\n" + 
			"\t\t\t}else {\n" + 
				"\t\t\t\tquery =\"select * from ^$00$01$m:constantsMap:table_name1$^ where " + columnName + " like '%\" + " + columnName + " + \"%' order by " + columnName + " desc limit 10\";\n" + 
				"\t\t\t\t^$00$01$m:constantsMap:table_name1$^List = em.createNativeQuery(query, ^$00$01$m:constantsMap:cap_table_name1$^.class).getResultList();\n" + 
			"\t\t\t}\n\n" + 
			
			"\t\t\tJsonArray ^$00$01$m:constantsMap:table_name1$^Array = new JsonArray();\n" + 
			"\t\t\tfor (^$00$01$m:constantsMap:cap_table_name1$^ ^$00$01$m:constantsMap:table_name1$^ : ^$00$01$m:constantsMap:table_name1$^List) {\n" + 
				"\t\t\t\t^$00$01$m:constantsMap:table_name1$^Array.add(JsonParser.parseString(^$00$01$m:constantsMap:table_name1$^.toString()));\n" + 
			"\t\t\t}\n" + 
			"\t\t\tresponse.add(\"data\", ^$00$01$m:constantsMap:table_name1$^Array);\n" + 
		"\t\t} catch (Exception ex) {\n" + 
			"\t\t\tex.printStackTrace();\n" + 
			"\t\t\tresponse.add(\"data\", new JsonArray());\n" + 
		"\t\t}\n\n" + 
		
		"\t\tresponse.addProperty(\"code\", 200);\n" + 
		"\t\tresponse.addProperty(\"status\", \"Success\");\n\n" + 
		
		"\t\treturn response.toString();\n" + 
	"\t}\n\n";

        return temp;
    }
  
  public String getFunction2(String col1, String col2, String givenQuery, String mappingTable) {
      String temp = "";
      
      temp += "\t@SuppressWarnings(\"unchecked\")\n" + 
	"\t@ResponseStatus(code = HttpStatus.OK)\n" + 
	"\t@GetMapping(path = \"/" + col2 +"_by_" + col1 + "\", produces = \"application/json\")\n" + 
	"\t@ApiOperation(value = \"Get " + col2 + " by " + col1 + "\", httpMethod = \"GET\")\n" + 
	"\t@ApiResponse(code = 200, message = \"Returns a 200 response code if successful\")\n" + 
	"\tpublic String get" + col2 + "By" + col1 + "(@RequestParam String type) {\n" + 
		"\t\tJsonObject response = new JsonObject();\n" + 
		"\t\tString status = \"Failed\";\n\n" + 
		
		"\t\tList<" + mappingTable + "Mst> " + mappingTable+ "List = new ArrayList<>();\n" +
		"\t\tString query = \"\";\n" +
		"\t\tObjectMapper mapper = new ObjectMapper();\n" +
		"\t\ttry {\n" +
			"\t\t\tif (RestUtil.isNull(type)) {\n" +
				"\t\t\t\tresponse.add(\"data\", new JsonArray());\n" +
				"\t\t\t\tresponse.addProperty(\"error\", \"'type' is required\");\n" +
			"\t\t\t} else {\n" +
				"\t\t\t\tquery = \"" + givenQuery + "\n" + 
				"\t\t\t\t" + mappingTable +"List = em.createNativeQuery(query," + mappingTable + "Mst.class).getResultList();\n" + 
				"\t\t\t\tresponse.add(\"data\", JsonParser.parseString(mapper.writeValueAsString(" + mappingTable + "List)));\n" +
				"\t\t\t\tstatus = \"Success\";\n" + 
			"\t\t\t}\n" +
		"\t\t} catch (Exception ex) {\n" + 
			"\t\t\tex.printStackTrace();\n" + 
			"\t\t\tresponse.add(\"data\", new JsonArray());\n" + 
		"\t\t}\n\n" + 
		
		"\t\tresponse.addProperty(\"code\", 200);\n" + 
		"\t\tresponse.addProperty(\"status\", status);\n" +
		"\t\treturn response.toString();\n" + 
	"\t}\n\n";
      
      return temp;
  }
}


