package com.vnit.api.file.model;

import java.util.List;
import com.vnit.api.file.utility.Utility;
import com.vnit.api.file.col_object.ColumnObject;
import com.vnit.api.file.columnobjectlist.ColumnObjectList;
import com.vnit.api.file.dbConnection.DBConnection;
import com.vnit.api.file.utility.TestServlet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import main.java.com.vnit.api.file.t1Template.EntityTemplate;
import main.java.com.vnit.api.file.utility.ProcessSubstitution;
import main.java.com.vnit.api.file.col_object.Object;
import main.java.com.vnit.api.file.t2Template.S2Entity1;
import main.java.com.vnit.api.file.t2Template.S2Entity2;
import main.java.com.vnit.api.file.t2Template.S2Entity3;
import main.java.com.vnit.api.file.t2Template.S2Entity4;
import main.java.com.vnit.api.file.t2Template.S2Entity5;
import main.java.com.vnit.api.file.utility.DbUtility;

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
    
    public String  makeEntity(String tableName) throws SQLException{  
//        //to merge all the method and get complete code for entity file
//        this.table_name=name;
//        String package_name="package "+map.get("package_prefix")+".entity;\n";
//        String all_imports="";
//        String mainEntity=this.mainEntity();
//        String declaration_code="";
//        System.out.println("Reach:3");
//        for(int i=0;i<this.packageEntry().size();i++){
//            all_imports=all_imports+this.packageEntry().get(i)+"\n";
//        }
//
//        for (int i=0;i<this.setTlist().size();i++) {
//            if(tlist.get(i).getCheckvalue()==true){
//                  declaration_code=declaration_code+decTerm(map.get("primarykey"),tlist.get(i).getColname(), tlist.get(i).getJavadatatype())+ createsetMethod( tlist.get(i).getColname(), tlist.get(i).getJavadatatype()) + creategetMethod( tlist.get(i).getColname(), tlist.get(i).getJavadatatype());
//              }else{
//                   declaration_code=declaration_code+decTerm_1(map.get("notprimarykey"),tlist.get(i).getColname(), tlist.get(i).getJavadatatype()) + createsetMethod( tlist.get(i).getColname(), tlist.get(i).getJavadatatype())+ creategetMethod( tlist.get(i).getColname(), tlist.get(i).getJavadatatype());
//              }
//            
//        }
//        
//        String completecode=package_name+all_imports+mainEntity+declaration_code+"\n}";
//        return completecode;                //this code is returned to makeEntity file in Utility Class.
// 
            DbUtility dbUtility = new DbUtility();
            DBConnection dbConn = new DBConnection();
  
            try {
                dbUtility.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility.getColumns(tableName, dbConn.setConnection(null));
            } catch (SQLException ex) {
                    System.out.println("****Error");
            }
            
            ArrayList<Object> columns = dbUtility.getColumns();
            return getEntityFile(columns);
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
     
      public String makeS2Entity(ArrayList<String> tableNames, int fileNo) throws SQLException{
            DbUtility dbUtility1 = new DbUtility();
            DbUtility dbUtility2 = new DbUtility();

            DBConnection dbConn = new DBConnection();
            String tableName1 = tableNames.get(0);
            String tableName2 = tableNames.get(1);
            try {
                dbUtility1.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility1.getColumns(tableName1, dbConn.setConnection(null));
                
                dbUtility2.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility2.getColumns(tableName2, dbConn.setConnection(null));
            } catch (SQLException ex) {
                    System.out.println("****Error");
            }

            ArrayList<Object> columns1 = dbUtility1.getColumns();
            ArrayList<String> PK1 = dbUtility1.getPKColumns();
            
            ArrayList<Object> columns2 = dbUtility2.getColumns();
            ArrayList<String> PK2 = dbUtility2.getPKColumns();
            
            String ans = "";
            switch(fileNo) {
                case 1 : ans = getEntity1Template(columns1);
                break;
                
                case 2 : ans = getEntity2Template(columns2);
                break;
                
                case 3 : ans = getEntity3Template(PK2);
                break;
                
                case 4 : ans = getEntity4Template(columns1);
                break;
                
                case 5 : ans = getEntity5Template(columns2, PK1);
                break;
                
            }
            
            return ans;
    }
     
     public String getEntity1Template(ArrayList<Object> columns) {
        S2Entity1 s2e1 = new S2Entity1();
        ProcessSubstitution ps = new ProcessSubstitution();

        String template = "";
        template += s2e1.getTemplate();
        
        for(int i = 0 ; i < columns.size() ; i++) {
            String frag = s2e1.getFieldFragments(columns.get(i).getColumnName());
            template += frag;
        }

        template += s2e1.getStringMethod(columns);
        template += s2e1.getClosingBracket();
        
        template = ps.processTemplate(template);
        return template;
    }

    public String getEntity2Template(ArrayList<Object> columns) {
        S2Entity2 s2e2 = new S2Entity2();
        ProcessSubstitution ps = new ProcessSubstitution();
        
        String template = "";
        template += s2e2.getTemplate();
        
        for(int i = 0 ; i < columns.size() ; i++) {
            if(columns.get(i).getColumnPrimaryKey()) continue;            
            String frag = s2e2.getFragments(columns.get(i).getColumnName());
            template += frag;
        }

        template += s2e2.getToString(columns);
        template += s2e2.getClosingBracket();
        
        template = ps.processTemplate(template);
        return template;
    }

    public String getEntity3Template(ArrayList<String> columns) {
        S2Entity3 s2e3 = new S2Entity3();
        ProcessSubstitution ps = new ProcessSubstitution();
        
        String template = "";
        template += s2e3.getTemplate();
        
        for(int i = 0 ; i < columns.size() ; i++) {
            String frag = s2e3.getFields(columns.get(i));
            template += frag;
        }

        template += s2e3.getConstructor(columns);
      
        for(int i = 0 ; i < columns.size(); i++) {
            template += s2e3.getFragments(columns.get(i));
        }

        template += s2e3.getClosingBracket();
        
        template = ps.processTemplate(template);
        return template;
    }

    public String getEntity4Template(ArrayList<Object> columns) {
        S2Entity4 s2e4 = new S2Entity4();
        ProcessSubstitution ps = new ProcessSubstitution();

        String template = "";
        template += s2e4.getTemplate();
        
        for(int i = 0 ; i < columns.size() ; i++) {
            String frag = s2e4.getFieldFragments(columns.get(i).getColumnName());
            template += frag;
        }

        template += s2e4.getMapping();
        template += s2e4.getStringMethod(columns);
      
        template += s2e4.getClosingBracket();
        
        template = ps.processTemplate(template);
        return template;
    }

     public String getEntity5Template(ArrayList<Object> columns, ArrayList<String> primaryColumnName) {
        S2Entity5 s2e5 = new S2Entity5();
        ProcessSubstitution ps = new ProcessSubstitution();

        String template = "";
        template += s2e5.getTemplate();
        
        for(int i = 0 ; i < columns.size() ; i++) {
            if(primaryColumnName.contains(columns.get(i).getColumnName())) continue;
            String frag = s2e5.getFieldFragments(columns.get(i).getColumnName());
            template += frag;
        }

        for(int i = 0; i < columns.size(); i++) {
            if(primaryColumnName.contains(columns.get(i).getColumnName()))
                template += s2e5.getMapping(columns.get(i).getColumnName());
        }
 
        template += s2e5.getStringMethod(columns, primaryColumnName);
      
        template += s2e5.getClosingBracket();
        
        template = ps.processTemplate(template);
        return template;
    }

     
}
