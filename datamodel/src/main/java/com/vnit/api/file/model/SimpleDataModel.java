package com.vnit.api.file.model;

import java.util.List;
import com.vnit.api.file.utility.Utility;
import com.vnit.api.file.col_object.ColumnObject;
import com.vnit.api.file.columnobjectlist.ColumnObjectList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import main.java.com.vnit.api.file.template.EntityTemplate;
import main.java.com.vnit.api.file.utility.ProcessSubstitution;

public class SimpleDataModel {
    ColumnObjectList c2=new ColumnObjectList();
    private  String table_name=c2.getTablename();
    List <ColumnObject> tlist;
    Map<String,String> map=new HashMap<>();
    
    public SimpleDataModel(Map<String,String> map){ //creating constructor for map
         Utility u1=new Utility();
         u1.setMap(map);
         this.map=map;
      }

    
    public  List <ColumnObject> setTlist() throws SQLException{ //Returning tlist is the list of ColumnObject. 
        ColumnObjectList c1=new ColumnObjectList();
        tlist=c1.getData(this.table_name);
        return tlist;
    }
    
    public List<String> packageEntry(){ //Complete package required for entity file, they are declared in system.properties.
        List<String> list;
        list=Arrays.asList(map.get("import_package_entity").split(","));
        return list;

    }
    
    public String mainEntity(){//Basic constants and entities for entity file.
        return "@Table(name=\""+this.table_name+"\")"+"\npublic class "+ nameCase(this.table_name)+map.get("model_suffix")+"{";
    }
    
    public static String nameCase(String table_name){ //Used for example: sample->Sample(changing the name's first letter to captial letter)
            String firstLetter = table_name.substring(0, 1);
            String remainingLetters = table_name.substring(1, table_name.length());
            firstLetter = firstLetter.toUpperCase();
            String name = firstLetter + remainingLetters;
            return name;
    }
    
    
    public static String decTerm(String primarykey,String col_name,String col_datatype){ // Primary Key Method
        return "\n"+primarykey+"\n@Column(name=\""+col_name+"\")"
                         +"\nprivate "+col_datatype+" "+col_name+";";
    }
    
    public static String decTerm_1(String notprimarykey,String col_name,String col_datatype){ //Not Primary Key Method
        return "\n"+notprimarykey+ "\n@Column(name=\""+col_name+"\")"+"\nprivate "+col_datatype+" "+col_name+";";  
    }
    
    
    public static String createsetMethod(String col_name,String col_datatype){//set and get method 
        
        return "\npublic void set"+nameCase(col_name)+"("+col_datatype+" "+col_name+"){\nthis."+col_name+"= "+col_name+";\n}";
        
    }
    public static String creategetMethod(String col_name,String col_datatype){//set and get method 
        return "\npublic "+col_datatype+" get"+nameCase(col_name)+"()"+ "{\nreturn "+ col_name+";\n}";
        
    }
    
    public String  makeEntity(String name) throws SQLException{  
        //to merge all the method and get complete code for entity file
        this.table_name=name;
        String package_name="package "+map.get("package_prefix")+".entity;\n";
        String all_imports="";
        String mainEntity=this.mainEntity();
        String declaration_code="";
        System.out.println("Reach:3");
        for(int i=0;i<this.packageEntry().size();i++){
            all_imports=all_imports+this.packageEntry().get(i)+"\n";
        }

        for (int i=0;i<this.setTlist().size();i++) {
            if(tlist.get(i).getCheckvalue()==true){
                  declaration_code=declaration_code+decTerm(map.get("primarykey"),tlist.get(i).getColname(), tlist.get(i).getJavadatatype())+ createsetMethod( tlist.get(i).getColname(), tlist.get(i).getJavadatatype()) + creategetMethod( tlist.get(i).getColname(), tlist.get(i).getJavadatatype());
              }else{
                   declaration_code=declaration_code+decTerm_1(map.get("notprimarykey"),tlist.get(i).getColname(), tlist.get(i).getJavadatatype()) + createsetMethod( tlist.get(i).getColname(), tlist.get(i).getJavadatatype())+ creategetMethod( tlist.get(i).getColname(), tlist.get(i).getJavadatatype());
              }
            
        }
        
        String completecode=package_name+all_imports+mainEntity+declaration_code+"\n}";
        return completecode;                //this code is returned to makeEntity file in Utility Class.
        
    }
    
    
     public String getEntityFile(ArrayList<Object> columns) {
        EntityTemplate entityTemplate = new EntityTemplate();
        ProcessSubstitution ps = new ProcessSubstitution();

         String template = ""; 
        
        template += entityTemplate.getTemplate();

        for (int i = 0; i < columns.size(); i++) {
            String fragments = entityTemplate.getFieldFragments(columns.get(i).getColumnName());
            template += fragments;
        }

        template += entityTemplate.getClosingBracket();
        template = ps.processTemplate(template);

        return template;
    }


    
  
  
}
