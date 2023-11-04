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
public class S2Entity4 {
    public String getTemplate() {
        String temp = "";
        temp += "^00$01$m:constantsMap:s2entity_package_name$^" + "\n\n";
        temp += "^00$01$m:constantsMap:s2entity4_imports$^" + "\n\n";

        temp += "@Entity\n" + 
        "@Table(name=\"" + "^$00$01$m:constantsMap:table_name1$^" +  "\")\n";

        temp += "public class "  + "^$00$01$m:constantsMap:cap_table_name1$^Mst" + " implements Serializable {\n";

        temp += "\tprivate static final long serialVersionUID = 1L;\n";

        return temp;
    }

    public String getFieldFragments(String columnName) {
        String temp = "\n";

         // for primary key only
        temp += "\t^00$02$m2:fld:" + columnName + ":primary_key$c:@Id\n$^";
        temp += "\t^00$02$m2:fld:" + columnName + ":primary_key$c:@GeneratedValue(strategy=GenerationType.IDENTITY)\n$^";

        temp += "\t@Column(name = \"^00$01$m2:fld:" + columnName + ":column_name$^\")\n";
        temp += "\tprivate ^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^;\n\n";

        temp += "\tpublic ^00$01$m2:fld:" + columnName + ":column_type$^ get^00$01$m2:fld:" + columnName + ":column_name$^() {\n" + 
                "\t\treturn ^00$01$m2:fld:" + columnName + ":column_name$^;\n" + "\t}\n\n";

        temp += "\tpublic void set^00$01$m2:fld:" + columnName + ":column_name$^(^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^) {\n" +
                "\t\tthis.^00$01$m2:fld:" + columnName + ":column_name$^ = ^00$01$m2:fld:" + columnName + ":column_name$^;\n";
        
        temp += "\t}\n\n";

       
        return temp;
    }

    public String getMapping() {
        String temp = "";
        temp += "\t@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH},";
        temp += "mappedBy = \"^$00$01$m:constantsMap:table_name1$^Mst\")\n";
    	temp += "\t@JsonManagedReference\n";
        temp += "\tList<^$00$01$m:constantsMap:cap_table_name2$^Mst> ^$00$01$m:constantsMap:table_name2$^ = new ArrayList<>();\n\n";
	
        temp += "\tpublic List<^$00$01$m:constantsMap:cap_table_name2$^Mst> get^$00$01$m:constantsMap:cap_table_name2$^() {\n";
        temp += "\t\treturn ^$00$01$m:constantsMap:table_name2$^;\n";
        temp += "\t}\n\n";

        temp += "\tpublic void set^$00$01$m:constantsMap:cap_table_name2$^(List<^$00$01$m:constantsMap:cap_table_name2$^Mst> ^$00$01$m:constantsMap:table_name2$^) {\n";
        temp += "\t\tthis.^$00$01$m:constantsMap:table_name2$^ = ^$00$01$m:constantsMap:table_name2$^" + ";\n";
        temp += "\t}\n\n";
        
        return temp;
    }

    public String getStringMethod(ArrayList<Object> columns) {
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
            parts.add(input.getColumnName() + "=\" + " + input.getColumnName() + " + \"");
        }

        return "\t\treturn \"{" + String.join(", ", parts) + "}\";";
    }

    public String getClosingBracket() {
        return "}\n";
    }

}
