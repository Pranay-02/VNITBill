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
public class S2Entity2 {
     public String getTemplate() {
        String temp = "";        
        temp += "^00$01$m:constantsMap:s2entity_package_name$^" + "\n\n";
        temp += "^00$01$m:constantsMap:s2entity2_imports$^" + "\n\n";

        temp += "@Entity\n" + 
        "@Table(name=\"" + "^$00$01$m:constantsMap:table_name2$^" +  "\")\n";

        temp += "public class "  + "^$00$01$m:constantsMap:cap_table_name2$^" + " implements Serializable {\n";
        temp += "\tprivate static final long serialVersionUID = 1L;\n\n";

        temp += "\t@EmbeddedId\n";
        temp += "\tprivate ^$00$01$m:constantsMap:cap_table_name2$^PK ^$00$01$m:constantsMap:table_name2$^PK;\n\n";
        
        temp += "\tpublic ^$00$01$m:constantsMap:cap_table_name2$^PK get^$00$01$m:constantsMap:cap_table_name2$^PK() {\n";
        temp += "\t\t return ^$00$01$m:constantsMap:table_name2$^PK;\n";
        temp += "\t}\n\n";

        temp += "\tpublic void set^$00$01$m:constantsMap:cap_table_name2$^PK(^$00$01$m:constantsMap:cap_table_name2$^PK ^$00$01$m:constantsMap:table_name2$^PK) {\n";
        temp += "\t\tthis.^$00$01$m:constantsMap:table_name2$^PK = ^$00$01$m:constantsMap:table_name2$^PK;\n";
        temp += "\t}\n\n";

        return temp;
    }

    public String getFragments(String columnName) {
        String temp = "";
        temp += "\t@Column(name = \"^00$01$m2:fld:" + columnName + ":column_name$^\")\n";
        temp += "\tprivate ^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^;\n\n";

        temp += "\tpublic ^00$01$m2:fld:" + columnName + ":column_type$^ get^00$01$m2:fld:" + columnName + ":column_name$^() {\n" + 
                "\t\treturn ^00$01$m2:fld:" + columnName + ":column_name$^;\n" + "\t}\n\n";

        temp += "\tpublic void set^00$01$m2:fld:" + columnName + ":column_name$^( ^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^) {\n" +
                "\t\tthis.^00$01$m2:fld:" + columnName + ":column_name$^ = ^00$01$m2:fld:" + columnName + ":column_name$^;\n";
        
        temp += "\t}\n\n";

        return temp;
    }

    public String getToString(ArrayList<Object> columns) {
        String temp = "";
        temp += "\t@Override\n";
        temp += "\tpublic String toString() {\n";

        temp += getFragmentsString(columns);
        temp += "\n\t}\n";
        return temp;
    }

    public String getFragmentsString(ArrayList<Object> columns) {
        ArrayList<String> parts = new ArrayList<>();

        for (Object input : columns) {
            String str = input.getColumnPrimaryKey() ? "^$00$01$m:constantsMap:table_name2$^PK.get" + input.getColumnName() + "()" : input.getColumnName();
            parts.add(input.getColumnName() + "=\" + " + str + " + \"");
        }

        return "\t\treturn \"{" + String.join(", ", parts) + "}\";";
    }

    public String getClosingBracket() {
        return "}\n";
    }
   
}
