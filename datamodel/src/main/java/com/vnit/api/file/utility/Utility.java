package com.vnit.api.file.utility;
  
import com.vnit.api.file.columnobjectlist.ColumnObjectList;
import static com.vnit.api.file.model.SimpleDataModel.nameCase;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.vnit.api.file.system.PropertyClass;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
 
public class Utility {
    
    private Map<String,String> map; 
    PropertyClass p1=new PropertyClass();
    private final String name=p1.getName();
    public Properties p= new Properties(); 
    //ServletContext ctx=ServletContext.

    public Map<String, String> getMap(){
         return map;
    }
    
    public void  setMap(Map<String,String> map){//mapping the values from properties file 
              this.map=map;
              try(FileReader fileReader = new FileReader(TestServlet.contextpath+this.name)){
                    p.load(fileReader);
                    Iterator keyIterator = p.keySet().iterator();
                    while(keyIterator.hasNext()){
                        String key   = (String) keyIterator.next();
                          map.put(key,p.getProperty(key));
                    }
                    
                } catch (IOException e) {
                e.printStackTrace();
             }
           
    }
    
    
    public void makeFile(String code){//make entity file in target folder
        ColumnObjectList c1=new ColumnObjectList();
        Map<String,String> map=new HashMap();
        this.setMap(map);
        File file = new File(map.get("target_folder")+"entity/"+nameCase(c1.getTablename())+map.get("model_suffix")+".java");
        try (FileWriter fw = new FileWriter(file,true)) {
            fw.write(code);
            fw.close();
            System.out.print("\nCheck the file at Output Location");
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     public void makeRepoFile(String code){ //make repo file in target folder
        ColumnObjectList c1=new ColumnObjectList();
        Map<String,String> map=new HashMap();
        this.setMap(map);
        File file = new File(map.get("target_folder")+"repo/"+nameCase(c1.getTablename())+map.get("repo_suffix")+".java");
        try (FileWriter fw = new FileWriter(file,true)) {
            fw.write(code);
            fw.close();
            System.out.print("\nCheck the file at Repo Output Location");
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     public void makeControllerFile(String code){//make controller file in target folder
        ColumnObjectList c1=new ColumnObjectList();
        Map<String,String> map=new HashMap();
        this.setMap(map);
        File file = new File(map.get("target_folder")+"controller/"+nameCase(c1.getTablename())+map.get("controller_suffix")+".java");
        try (FileWriter fw = new FileWriter(file,true)) {
            fw.write(code);
            fw.close();
            System.out.print("\nCheck the file at Controller Output Location");
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     
      public void makeHTMLFile(String code){//make controller file in target folder
        ColumnObjectList c1=new ColumnObjectList();
        Map<String,String> map=new HashMap();
        this.setMap(map);
        File file = new File(map.get("target_folder")+"html/"+c1.getTablename()+".component.html");
        try (FileWriter fw = new FileWriter(file,true)) {
            fw.write(code);
            fw.close();
            System.out.print("\nCheck the file at HTML Output File Location");
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
      public void makeTSFile(String code){//make controller file in target folder
        ColumnObjectList c1=new ColumnObjectList();
        Map<String,String> map=new HashMap();
        this.setMap(map);
        File file = new File(map.get("target_folder")+"ts/"+c1.getTablename()+".component.ts");
        try (FileWriter fw = new FileWriter(file,true)) {
            fw.write(code);
            fw.close();
            System.out.print("\nCheck the file at TS Output File Location");
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
  
}
