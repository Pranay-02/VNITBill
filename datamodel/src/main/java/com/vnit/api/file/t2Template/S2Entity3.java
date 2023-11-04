/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.t2Template;

import java.util.ArrayList;

/**
 *
 * @author Pranay Singhal
 */
public class S2Entity3 {
    
        public String getTemplate() {
        String temp = "";
        temp += "^00$01$m:constantsMap:s2entity_package_name$^" + "\n\n";
        temp += "^00$01$m:constantsMap:s2entity3_imports$^" + "\n\n";

        temp += "@Embeddable\n";
        temp += "public class "  + "^$00$01$m:constantsMap:cap_table_name2$^" + "PK implements Serializable {\n\n";

        temp += "\tprivate static final long serialVersionUID = 1L;\n";

        temp += "\tpublic ^$00$01$m:constantsMap:cap_table_name2$^PK() {\n";
        temp += "\t\tsuper();\n";
        temp += "\t}\n\n";

        return temp;
    }

    public String getFields(String columnName) {
        String temp = "";

        temp += "\t@Basic(optional = false)\n";
        temp += "\t@Column(name = \"^00$01$m2:fld:" + columnName + ":column_name$^\")\n";
	    temp += "\tprivate ^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^;\n\n";
        return temp;
    }

    public String getConstructor(ArrayList<String> columns) {
        String temp = "";
        temp += "\tpublic ^$00$01$m:constantsMap:cap_table_name2$^PK(";
        for(int i = 0; i < columns.size(); i++) {
            temp += getVariables1(columns.get(i));
            if(i < columns.size() - 1)
                temp += ", ";
        }

        temp += ") {\n";
        temp += "\t\tsuper();\n";

        for(int i = 0; i < columns.size(); i++)
           temp += getVariables2(columns.get(i));

        temp += "\t}\n\n";
        return temp;
    }

    public String getVariables1(String columnName) {
        String temp = "";
        temp += "^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^";
        return temp;
    }

    public String getVariables2(String columnName) {
        String temp = "";
        temp += "\t\tthis.^00$01$m2:fld:" + columnName + ":column_name$^ = ^00$01$m2:fld:" + columnName + ":column_name$^;\n";
        return temp;
    }

    public String getFragments(String columnName) {
        String temp = "";

        temp += "\tpublic ^00$01$m2:fld:" + columnName + ":column_type$^ get^00$01$m2:fld:" + columnName + ":column_name$^() {\n" + 
                "\t\treturn ^00$01$m2:fld:" + columnName + ":column_name$^;\n" + "\t}\n\n";

        temp += "\tpublic void set^00$01$m2:fld:" + columnName + ":column_name$^( ^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^) {\n" +
                "\t\tthis.^00$01$m2:fld:" + columnName + ":column_name$^ = ^00$01$m2:fld:" + columnName + ":column_name$^;\n" + "\t}\n\n";

        return temp;
    }

    public String getClosingBracket() {
        return "}\n";
    }
    
}
