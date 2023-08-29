/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.template;

/**
 *
 * @author Pranay Singhal
 */
public class RepoTemplate {
 public String getRepoFieldTemplate() {
        String temp = "";

        temp += "^00$01$m:constantsMap:repo_package_name$^" + "\n\n";
        temp += "^00$01$m:constantsMap:repo_imports$^" + "\n\n";
        temp += "@Transactional\n@Repository\n";
        temp += "public class "  + "^$00$01$m:constantsMap:cap_table_name$^" + "Repo {\n\n";
        temp += "@Autowired\n";
        temp += "EntityManager em;\n";

        return temp;
    }    

    public String getObjectTemplate() {
        String temp = "";

        temp += "\npublic ^$00$01$m:constantsMap:cap_table_name$^Mst " + "get^$00$01$m:constantsMap:cap_table_name$^" + "(Integer id) {\n";

        temp += "\ttry {\n";
        temp += "\t\tif(id == null)\n";
        temp += "\t\t\treturn null;\n";
        temp += "\t\treturn em.find(^$00$01$m:constantsMap:cap_table_name$^" + "Mst.class, id);\n";

        temp += "\t} catch (Exception ex) {\n";
        temp += "\t\tex.printStackTrace();\n";
        temp += "\t}\n\n";

        temp += "\treturn null;\n";
        temp += "}\n";

        return temp;
    }

    public String postObjectTemplate(String columnName) {
        String temp = "";

        temp += "\npublic Integer post" + "^$00$01$m:constantsMap:cap_table_name$^"; 
        temp += "(" + "^$00$01$m:constantsMap:cap_table_name$^Mst " + "^$00$01$m:constantsMap:table_name$^" + ") {\n";
        
        temp += "\ttry {\n";
        temp += "\t\t" + "^$00$01$m:constantsMap:cap_table_name$^" + "Mst data = get" + "^$00$01$m:constantsMap:cap_table_name$^";
        temp += "(" + "^$00$01$m:constantsMap:table_name$^" + ".get" + "^00$01$m2:fld:" + columnName + ":column_name$^()" + ");\n";
        temp += "\t\tif(data == null)\n";
        temp += "\t\t\tem.persist(^$00$01$m:constantsMap:table_name$^);\n";
        temp += "\t\telse\n";
        temp += "\t\t\tem.merge(^$00$01$m:constantsMap:table_name$^);\n";
        temp += "\t\tem.flush();\n";
        temp += "\t\treturn " + "^$00$01$m:constantsMap:table_name$^" + ".get" + "^00$01$m2:fld:" + columnName + ":column_name$^();\n"; 

        temp += "\t} catch (Exception ex) {\n";
        temp += "\t\tex.printStackTrace();\n";
        temp += "\t}\n\n";

        temp += "\treturn 0;\n";
        temp += "}\n";

        return temp;        
    }

    public String deleteObjectTemplate() {
        String temp = "";   

        temp += "\npublic Integer delete" + "^$00$01$m:constantsMap:cap_table_name$^" + "(Integer id) {\n";

        temp += "\ttry {\n";
        temp += "\t\t" + "^$00$01$m:constantsMap:cap_table_name$^" + "Mst data = get" + "^$00$01$m:constantsMap:cap_table_name$^" + "(id);\n";
        temp += "\t\tif(data != null) {\n";
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
