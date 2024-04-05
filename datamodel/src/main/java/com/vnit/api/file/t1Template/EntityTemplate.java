/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.t1Template;

/**
 *
 * @author Pranay Singhal
 */
public class EntityTemplate {

    public String getTemplate() {
        String temp = "";
        
        temp += "^00$01$m:constantsMap:entity_package_name$^" + "\n\n";

        temp += "^00$01$m:constantsMap:entity_imports$^" + "\n\n";

        temp += "@Entity\n" + 
                "@Table(name=\"" + "^$00$01$m:constantsMap:table_name$^" +  "\")\n";

        temp += "public class "  + "^$00$01$m:constantsMap:cap_table_name$^" + "Mst {\n";
        
        return temp;
    }

    public String getFieldFragments(String columnName) {
        String temp = "\n";
      //iterative start
        temp += "@ApiModelProperty(required = ^00$01$m2:fld:" + columnName + ":required$^,"; 
        temp += " value = \"(^00$01$m2:fld:" + columnName +":size$^)\")\n";

        // for primary key only
        temp += "^00$02$m2:fld:" + columnName + ":primary_key$c:@Id\n$^" ;
        temp += "^00$02$m2:fld:" + columnName + ":primary_key$c:@GeneratedValue(strategy=GenerationType.IDENTITY)\n$^";
        
        temp += "@Column(name = \"^00$01$m2:fld:" + columnName + ":column_name$^\")\n";
        temp += "private ^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^;\n\n";

        temp += "public ^00$01$m2:fld:" + columnName + ":column_type$^ get^00$01$m2:fld:" + columnName + ":column_name$^() {\n" + 
                "\treturn ^00$01$m2:fld:" + columnName + ":column_name$^;\n" + "}\n\n";

        temp += "public void set^00$01$m2:fld:" + columnName + ":column_name$^ ( ^00$01$m2:fld:" + columnName + ":column_type$^ ^00$01$m2:fld:" + columnName + ":column_name$^) {\n" +
                "\tthis.^00$01$m2:fld:" + columnName + ":column_name$^ = ^00$01$m2:fld:" + columnName + ":column_name$^;\n";
        
        temp += "}\n\n";
//iterative end
        return temp;
    }

    public String getClosingBracket() {
        return "}";
    }    
}
