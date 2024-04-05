/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.screenListTemplate;

/**
 *
 * @author Pranay Singhal
 */
public class ScreenListAPITemplate {
        
        public String getTemplate(String query, String functionName, String listName) {
            String temp = "";
            
            temp += "\n@SuppressWarnings(\"unchecked\")\n" + 
	"@ResponseStatus(code = HttpStatus.OK)\n" + 
	"@GetMapping(path = \"/"+ functionName + "\", produces = \"application/json\")\n" + 
	"@ApiOperation(value = \""+ functionName + "\", httpMethod = \"GET\")\n" + 
	"@ApiResponse(code = 200, message = \"Returns a 200 response code if successful\")\n" + 
	"public String " + functionName + "(@RequestParam String name) {\n" + 
		"\tJsonObject response = new JsonObject();\n" +
		
		"\tList " + listName + " = new ArrayList<>();\n" +
		"\tString query = \"\";\n" +
		"\tObjectMapper mapper = new ObjectMapper();\n" +
		"\ttry {\n" +
                                                    "\t\tquery = " + query + ";\n" + 
                                                    "\t\t" + listName + " = em.createNativeQuery(query).getResultList();\n" + 
                                                    "\t\tresponse.add(\"data\", JsonParser.parseString(mapper.writeValueAsString(" + listName + ")));\n" + 
		"\t} catch (Exception ex) {\n" + 
			"\t\tex.printStackTrace();\n" + 
			"\t\tresponse.add(\"data\", new JsonArray());\n" + 
		"\t}\n\n" + 
		
		"\t\tresponse.addProperty(\"code\", 200);\n" + 
		"\t\tresponse.addProperty(\"status\", \"Success\");\n\n" + 
		
		"\t\treturn response.toString();\n" + 
	"}\n\n";
        
            return temp; 
        }
}
