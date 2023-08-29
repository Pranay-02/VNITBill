package com.vnit.api.file.model;

import java.util.List;
import com.vnit.api.file.utility.Utility;
import com.vnit.api.file.col_object.ColumnObject;
import com.vnit.api.file.columnobjectlist.ColumnObjectList;
import static com.vnit.api.file.model.SimpleDataModel.nameCase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.vnit.api.file.template.RepoTemplate;
import main.java.com.vnit.api.file.utility.ProcessSubstitution;

public class SimpleDataModelRepo {
    ColumnObjectList c2=new ColumnObjectList();
    private String table_name=c2.getTablename();
    List <ColumnObject> tlist;
    Map<String,String> map=new HashMap<>();
    
     public SimpleDataModelRepo(Map<String,String> map){//creating constructor for map
         Utility u1=new Utility();
         u1.setMap(map);
         this.map=map;
      }
     
    public  List <ColumnObject> setTlist() throws SQLException{//getting the list 
        ColumnObjectList c1=new ColumnObjectList();
         System.out.println(table_name);
        tlist=c1.getData(this.table_name);         
        return tlist;
    }
    
     public List<String> packageEntryRepo(){//get packages for file
        List<String> list;
        list=Arrays.asList(map.get("import_package_repo").split(","));
        return list;

    }
     
    public String mainEntityRepo(String model_suffix,String repo_suffix){//main Entity from the file
        return "\nimport com.vnit.api.entity."+nameCase(this.table_name)+model_suffix+";\n@Transactional\n@Repository\npublic class "+nameCase(this.table_name)+ repo_suffix+"{\n@Autowired\n" +
        "EntityManager em;\n";  
    }
    
    public String repogetMethod(String model_suffix){//create the entity from database
        String check="public "+nameCase(this.table_name)+model_suffix+" get"+nameCase(this.table_name)+"(Integer id) {\n" +
"		try {\n" +
"			if (id == null) \n" +
"				return null;\n" +
"			\n" +
"			return em.find("+nameCase(this.table_name)+model_suffix+".class, id);\n" +
"		} catch (Exception ex) {\n" +
"			ex.printStackTrace();\n" +
"		}\n" +
"		\n" +
"		return null;\n" +
"	}\n";
        
        return check;
    } 
    
    public String repopostMethod(String model_suffix) throws SQLException{//post the entity from database
        String entityname=nameCase(this.table_name)+model_suffix;
        String check="	public Integer post"+nameCase(this.table_name)+"("+entityname+" "+ this.table_name+") {\n" +
"		try {\n" +
                        entityname+" data = get"+nameCase(this.table_name)+"("+ this.table_name+".get"+nameCase(this.setTlist().get(0).getColname())+"());\n" +
"			if (data == null)\n" +
"				em.persist("+this.table_name+");\n" +
"			else\n" +
"				em.merge("+this.table_name+");\n" +
"			\n" +
"			em.flush();\n" +
"			return "+this.table_name+".get"+nameCase(this.setTlist().get(0).getColname())+"();\n" +
"		} catch (Exception ex) {\n" +
"			ex.printStackTrace();\n" +
"		}\n" +
"		\n" +
"		return 0;\n" +
"	}\n";
        return check;
    }
    
    public String repodeleteMethod(String model_suffix){//delete the entity from database
            String entityname=nameCase(this.table_name)+model_suffix;
            String check="public Integer delete"+nameCase(this.table_name)+"(Integer id) {\n" +
"		try {\n" +entityname+" data = get"+nameCase(this.table_name)+"(id);\n" +
"			if (data != null) {\n" +
"				em.remove(data);\n" +
"				em.flush();\n" +
"				return 1;\n" +
"			}\n" +
"		} catch (Exception ex) {\n" +
"			ex.printStackTrace();\n" +
"		}\n" +
"		\n" +
"		return 0;\n" +
"	}\n";
            return check;
    }
    
    public String makeRepo(String name) throws SQLException{//to merge all the method and get complete code for repo file
        this.table_name=name;
        String package_name="package "+map.get("package_prefix")+".repo;\n";
        String all_imports="";
        String mainEntity=this.mainEntityRepo(map.get("model_suffix"),map.get("repo_suffix"));
        
         for(int i=0;i<this.packageEntryRepo().size();i++){
            all_imports=all_imports+this.packageEntryRepo().get(i)+"\n";
        }
        String decalration_code=this.repogetMethod(map.get("model_suffix"))+this.repopostMethod(map.get("model_suffix"))+this.repodeleteMethod(map.get("model_suffix"));
        
        return package_name+all_imports+mainEntity+decalration_code+"\n}";//this code is returned to makeRepo file in Utility Class.
    }
    
     public String getRepoFile(String columnName) {
        RepoTemplate rTemplate = new RepoTemplate();
        ProcessSubstitution ps = new ProcessSubstitution();
        
        String template = "";
        
        template += rTemplate.getRepoFieldTemplate();
        template += rTemplate.getObjectTemplate();
        template += rTemplate.postObjectTemplate(columnName);
        template += rTemplate.deleteObjectTemplate();
        template += rTemplate.getClosingBracket();

        template = ps.processTemplate(template);

        return template;
    }
    

    
}
