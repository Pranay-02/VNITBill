package com.vnit.api.file.model;

import java.util.List;
import com.vnit.api.file.utility.Utility;
import com.vnit.api.file.col_object.ColumnObject;
import com.vnit.api.file.columnobjectlist.ColumnObjectList;
import static com.vnit.api.file.model.SimpleDataModel.nameCase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import main.java.com.vnit.api.file.t1Template.ControllerTemplate;
import main.java.com.vnit.api.file.utility.ProcessSubstitution;
import main.java.com.vnit.api.file.col_object.Object;
import main.java.com.vnit.api.file.utility.DbUtility;

public class SimpleDataModelContoller {
    ColumnObjectList c2=new ColumnObjectList();
    private String table_name=c2.getTablename();
    List <ColumnObject> tlist;
    Map<String,String> map=new HashMap<>();
    
    public SimpleDataModelContoller(Map<String,String> map){//creating constructor for map
        Utility u1=new Utility();
         u1.setMap(map);
         this.map=map;
        
    }
    
    public  List <ColumnObject> setTlist() throws SQLException{//creating constructor for map
        ColumnObjectList c1=new ColumnObjectList();
        tlist=c1.getData(this.table_name);         
        return tlist;
    }
    
     public List<String> packageEntryController(){//packages for controller file
        List<String> list;
        list=Arrays.asList(map.get("import_package_controller").split(","));
        return list;

    }
    public String mainController(String model_suffix,String repo_suffix){//entity for the controller 
        String entityname=nameCase(this.table_name)+model_suffix;
        return "import com.vnit.api.entity."+entityname+";\n" +
        "import com.vnit.api.repo."+nameCase(this.table_name)+repo_suffix+";\n" +
        "\n" +
        "@CrossOrigin(origins = \"*\", maxAge = 3600)\n" +
        "@RestController\n" +
        "public class "+nameCase(this.table_name)+map.get("controller_suffix") +"{\n" +
        "	\n" +
        "	@Autowired\n" +
        nameCase(this.table_name)+"Repo repo;"+
                "\n Map<String, String> map = new HashMap<>();\n";  
    }
    
    
     public String createContoller(String model_suffix) throws SQLException{//create controller code 
         String entityname=nameCase(this.table_name)+model_suffix;
         String con="";
         for(int i=0;i<this.setTlist().size();i++){
             if(this.setTlist().get(i).getCheckvalue()==false){
                        con=con+"if (RestUtil.isNull(body.get"+nameCase(this.setTlist().get(i).getColname())+"())) {\n" +
"				error.addProperty(\""+this.setTlist().get(i).getColname()+"\", \""+this.setTlist().get(i).getColname()+" is required\");\n" +
"			}\n";  
             }

         }
         
         return map.get("http_status") +
            "	@PostMapping(path = \"/post_"+this.table_name+"\", produces = \"application/json\")\n" +
            "	@ApiOperation(value = \"Create or Update "+this.table_name+" entity\", httpMethod = \"POST\")\n" +
            map.get("apiresponse") +

            "	public String create"+nameCase(this.table_name)+"(@RequestBody "+ entityname +" body) {\n" +
            "		Integer status = 0;\n" +
            map.get("jsonobject")+
            "		try {\n" + con +
            "			\n" +
            "			\tif (error.entrySet().isEmpty()) {\n" +
            "				status = repo.post"+nameCase(this.table_name)+"(body);\n" +
            "			}\n"+map.get("createController")+
            "	}\n";
     }
     
     public String deleteController(String model_suffix){//delete  from database
         String entityname=nameCase(this.table_name)+model_suffix;
         
         return  map.get("http_status")+
"	@DeleteMapping(path = \"/delete_"+this.table_name+"/{"+this.table_name.toUpperCase()+"ID"+"}\", produces = \"application/json\")\n" +
"	@ApiOperation(value = \"Delete "+this.table_name+" entity\", httpMethod = \"DELETE\")\n" +
 map.get("apiresponse")+
"	public String delete"+nameCase(this.table_name)+"(@PathVariable(name = \""+this.table_name.toUpperCase()+"ID"+"\") Integer id) {\n" +
"		Integer status = 0;\n" +
map.get("jsonobject") +
"		try {\n" +
"			\tif (RestUtil.isNull(id)) {\n" +
"				error.addProperty(\"id\", \""+this.table_name.toUpperCase()+"ID"+" is required\");\n" +
"			}\n" +
"			\n" +
"			if (error.entrySet().isEmpty()) {\n" +
"				status = repo.delete"+nameCase(this.table_name)+"(id);\n" +
"			}\n" +map.get("deleteController") +
"	}\n";
     }
     
     public String getController(String model_suffix){// get from database
         
            return  map.get("http_status")+
"	@GetMapping(path = \"/get_"+this.table_name+"/{"+this.table_name.toUpperCase()+"ID"+"}\", produces = \"application/json\")\n" +
"	@ApiOperation(value = \"Get "+this.table_name+" entity\", httpMethod = \"GET\")\n" +
 map.get("apiresponse")+
"	public String get"+nameCase(this.table_name)+"(@PathVariable(name = \""+this.table_name.toUpperCase()+"ID"+"\") Integer id) {\n" +
map.get("jsonobject")+
"		try {\n" +
"			\tif (RestUtil.isNull(id)) {\n" +
"				error.addProperty(\"id\", \""+this.table_name.toUpperCase()+"ID"+" is required\");\n" +
"			}\n" +
"			\n" +
"			if (error.entrySet().isEmpty()) {\n" +
"				ObjectMapper mapper = new ObjectMapper();\n" +
"				return mapper.writeValueAsString(repo.get"+nameCase(this.table_name)+"(id));\n" +
"			}\n" +map.get("getController") +
"	}\n";
     }
     
     
     public String makeController(String name) throws SQLException{//make controller file 
//         this.table_name=name;
//         String package_name="package "+map.get("package_prefix")+".controller;\n";
//         String all_imports="";
//         String mainController=this.mainController(map.get("model_suffix"),map.get("repo_suffix"));
//         for(int i=0;i<this.packageEntryController().size();i++){
//            all_imports=all_imports+this.packageEntryController().get(i)+"\n";
//        }
//                 
//               return package_name+all_imports+mainController+this.createContoller(map.get("model_suffix"))+this.deleteController(map.get("model_suffix"))+this.getController(map.get("model_suffix"))+"\n}";
        
            ArrayList<Object> columns = DbUtility.columns;
            String primaryColumnName = DbUtility.primaryKeyColumn;
            return getControllerFile(columns, primaryColumnName);

     }
     
//     public static void main(String args[]) throws SQLException{
//        Map<String,String> map=new HashMap<>();
//        SimpleDataModelContoller s1=new SimpleDataModelContoller(map);
//        Utility u3=new Utility();
//        u3.makeControllerFile(s1.makeController());
//        
//    }
     
     public String getControllerFile(ArrayList<Object> columns, String primaryColumnName) {
       ControllerTemplate cTemplate = new ControllerTemplate();
               ProcessSubstitution ps = new ProcessSubstitution();

         String template = "";
        
        template += cTemplate.getControllerFieldTemplate();
        template += cTemplate.getControllerCreateTemplate(columns, primaryColumnName);
        template += cTemplate.getControllerDeleteTemplate(primaryColumnName);
        template += cTemplate.getControllerGetTemplate(primaryColumnName);
        template += cTemplate.getClosingBracket();
        template = ps.processTemplate(template);
        
        return template;
    }
    
    
}
