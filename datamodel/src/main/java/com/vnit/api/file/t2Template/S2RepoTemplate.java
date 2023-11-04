/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.t2Template;

import java.util.ArrayList;
import main.java.com.vnit.api.file.col_object.Object;

/**
 *
 * @author Pranay Singhal
 */
public class S2RepoTemplate {
        public String getTemplate() {
        String temp = "";
        
        temp += "^00$01$m:constantsMap:s2repo_package_name$^" + "\n\n";
        temp += "^00$01$m:constantsMap:s2repo_imports$^" + "\n\n";
        temp += "import com.vnit.api.entity." +  "^$00$01$m:constantsMap:cap_table_name2$^;\n";
        temp += "import com.vnit.api.entity." +  "^$00$01$m:constantsMap:cap_table_name2$^Mst;\n"; 
        temp += "import com.vnit.api.entity." +  "^$00$01$m:constantsMap:cap_table_name2$^PK;\n"; 
        temp += "import com.vnit.api.entity." +  "^$00$01$m:constantsMap:cap_table_name1$^Mst;\n\n";

        temp += "@Transactional\n@Repository\n";
        temp += "public class "  + "^$00$01$m:constantsMap:cap_table_name1$^" + "Repo {\n\n";
        temp += "@Autowired\n";
        temp += "EntityManager em;\n";
        
        return temp;
    }

    public String getObjectTemplate() {
        String temp = "";

        temp += "\npublic ^$00$01$m:constantsMap:cap_table_name1$^Mst " + "get^$00$01$m:constantsMap:cap_table_name1$^" + "(Integer id) {\n";

        temp += "\ttry {\n";
        temp += "\t\tif(id == null)\n";
        temp += "\t\t\treturn null;\n\n";
        temp += "\t\treturn em.find(^$00$01$m:constantsMap:cap_table_name1$^" + "Mst.class, id);\n";

        temp += "\t} catch (Exception ex) {\n";
        temp += "\t\tex.printStackTrace();\n";
        temp += "\t}\n\n";

        temp += "\treturn null;\n";
        temp += "}\n";

        return temp;
    }

    public String postObjectTemplate(String primaryKey1, String primaryKey2, ArrayList<Object> columns1, ArrayList<Object> columns2) {
        String temp = "";

        temp += "\npublic Integer post" + "^$00$01$m:constantsMap:cap_table_name1$^"; 
        temp += "(" + "^$00$01$m:constantsMap:cap_table_name1$^Mst " + "^$00$01$m:constantsMap:table_name1$^" + ") {\n";
        
        temp += "\ttry {\n";
        temp += "\t\t" + "^$00$01$m:constantsMap:cap_table_name1$^" + "Mst data = get" + "^$00$01$m:constantsMap:cap_table_name1$^";
        temp += "(" + "^$00$01$m:constantsMap:table_name1$^" + ".get" + "^00$01$m2:fld:" + primaryKey1 + ":column_name$^()" + ");\n";
        temp += "\t\tif(data == null) {\n";
        temp += "\t\t\tem.persist(^$00$01$m:constantsMap:table_name1$^);\n";
        temp += "\t\t} else {\n";

        temp += "\t\t\tList<Integer> items = new ArrayList<>();\n";
        temp += "\t\t\tList<^$00$01$m:constantsMap:cap_table_name2$^Mst> dtls = data.get^$00$01$m:constantsMap:cap_table_name2$^();\n";
        temp += "\t\t\tfor (^$00$01$m:constantsMap:cap_table_name2$^Mst dt : dtls) {\n";
        temp += "\t\t\t\titems.add(dt.get" + primaryKey2 + "());\n";
        temp += "\t\t\t}\n\n";

        temp += "\t\t\tList<^$00$01$m:constantsMap:cap_table_name2$^Mst> dtlList = ^$00$01$m:constantsMap:table_name1$^.get^$00$01$m:constantsMap:cap_table_name2$^();\n";
        temp += "\t\t\tfor (^$00$01$m:constantsMap:cap_table_name2$^Mst dtl : dtlList) {\n";
        temp += "\t\t\t\t^$00$01$m:constantsMap:cap_table_name2$^ detail = em.find(^$00$01$m:constantsMap:cap_table_name2$^.class, ";
        temp += "new ^$00$01$m:constantsMap:cap_table_name2$^PK(dtl.get" + primaryKey2 +"(), data.get" + primaryKey1 + "()));\n";
        temp += "\t\t\t\tif(detail == null) {\n";
        temp += "\t\t\t\t\tdetail = new ^$00$01$m:constantsMap:cap_table_name2$^();\n";
        temp += "\t\t\t\t\tdetail.set" + "^$00$01$m:constantsMap:cap_table_name2$^PK";
        temp += "(new ^$00$01$m:constantsMap:cap_table_name2$^PK(dtl.get" + primaryKey2 +" (), data.get" + primaryKey1 +"()));\n";
        temp += getColumns2Setters(columns2);

        temp += "\t\t\t\t\tem.persist(detail);\n";
        temp += "\t\t\t\t}\n";  
        temp += "\t\t\t\telse {\n";
        temp += getColumns2Setters(columns2);

        temp += "\t\t\t\t\tem.merge(detail);\n";
        temp += "\t\t\t\t\titems.remove(dtl.get" + primaryKey2 +"());\n";
        temp += "\t\t\t\t}\n";
        temp += "\t\t\t}\n\n";

        temp += "\t\t\tfor (Integer " + primaryKey2 +" : items) {\n";
        temp += "\t\t\t\t^$00$01$m:constantsMap:cap_table_name2$^ detail = em.find(^$00$01$m:constantsMap:cap_table_name2$^.class, ";
        temp += "new ^$00$01$m:constantsMap:cap_table_name2$^PK(" + primaryKey2 + ", data.get" + primaryKey1 + "()));\n";
        temp += "\t\t\t\tem.remove(detail);\n";
        temp += "\t\t\t}\n\n";

        temp += getColumn1Setters(columns1);
        temp += "\t\t\tem.merge(data);\n";
        
        temp += "\t\t}\n\n";
        temp += "\t\tem.flush();\n";
        temp += "\t\treturn " + "^$00$01$m:constantsMap:table_name1$^" + ".get" + "^00$01$m2:fld:" + primaryKey1 + ":column_name$^();\n\n"; 

        temp += "\t} catch (Exception ex) {\n";
        temp += "\t\tex.printStackTrace();\n";
        temp += "\t}\n\n";

        temp += "\treturn 0;\n";
        temp += "}\n";

        return temp;        
    }

    public String getColumns2Setters(ArrayList<Object> columns) {
        String temp = "";

        for(int i = 0; i < columns.size(); i++) {
            if(columns.get(i).getColumnPrimaryKey()) continue;
            temp += "\t\t\t\t\tdetail.set" + columns.get(i).getColumnName() + "(dtl.get" + columns.get(i).getColumnName() +"());\n";
        }

        return temp;
    }

    public String getColumn1Setters(ArrayList<Object> columns) {
        String temp = "";
        for(int i = 0; i < columns.size(); i++) {
            if(columns.get(i).getColumnPrimaryKey()) continue;
            temp += "\t\t\tdata.set" + columns.get(i).getColumnName() + "(^$00$01$m:constantsMap:table_name1$^.get" + columns.get(i).getColumnName() + "());\n";
        }
        return temp;
    }

    public String deleteObjectTemplate(String primaryKey1, String primaryKey2) {
        String temp = "";   

        temp += "\npublic Integer delete" + "^$00$01$m:constantsMap:cap_table_name1$^" + "(Integer id) {\n";

        temp += "\ttry {\n";
        temp += "\t\t" + "^$00$01$m:constantsMap:cap_table_name1$^" + "Mst data = get" + "^$00$01$m:constantsMap:cap_table_name1$^" + "(id);\n";
        temp += "\t\tif(data != null) {\n";

        temp += "\t\t\tList<^$00$01$m:constantsMap:cap_table_name2$^Mst> list = data.get^$00$01$m:constantsMap:cap_table_name2$^();\n";
        temp += "\t\t\tfor(^$00$01$m:constantsMap:cap_table_name2$^Mst dt : list) {\n";
        temp += "\t\t\t\t^$00$01$m:constantsMap:cap_table_name2$^ detail = em.find(^$00$01$m:constantsMap:cap_table_name2$^.class, ";
        
        // to check;
        temp += "new ^$00$01$m:constantsMap:cap_table_name2$^PK(dt.get" + primaryKey2 +"(), data.get" + primaryKey1 + "()));\n";
        
        temp += "\t\t\t\tem.remove(detail);\n";
        temp += "\t\t\t}\n";

        temp += "\t\t\tem.remove(data);\n";
        temp += "\t\t\tem.flush();\n";
        temp += "\t\t\treturn 1;\n";
        temp += "\t\t}\n";

        temp += "\t} catch (Exception ex) {\n";
        temp += "\t\tex.printStackTrace();\n";
        temp += "\t}\n\n";

        temp += "\treturn 0;\n";
        temp += "}\n";

        return temp;
    }

    public String getClosingBracket() {
        return "}";
    }

}
