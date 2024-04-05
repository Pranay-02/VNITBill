/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.screenListTemplate;

/**
 *
 * @author Pranay Singhal
 */
public class ScreenListHtmlTemplate {
    
    public String getS2Template(String columnName, String listname) {
        String temp = "";
        
         temp += "\t\t\t\t\t\t\t<div class=\"col-md-3\">\n"+
            "\t\t\t\t\t\t\t\t\t<label>\n"+
               "\t\t\t\t\t\t\t\t\t\t" + columnName + "\n"+
                "\t\t\t\t\t\t\t\t\t\t<span style=\"color: red;\">*</span>\n"+
            "\t\t\t\t\t\t\t\t\t</label>\n"+
            "\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\n";
       
        temp += "\t\t\t\t\t\t\t\t\t\t<p-dropdown id=\"" + columnName +"\" name=\"" + columnName +"\" #" + columnName +"=\"ngModel\" "
                + "\t\t\t\t\t\t\t\t\t\t[options]=\""+ listname + "\"\n" + 
                        "\t\t\t\t\t\t\t\t\t\t[(ngModel)]=\"model." + columnName +"\" placeholder=\"" + columnName +"\" optionLabel=\"" + columnName +"\"\n" + 
                        "\t\t\t\t\t\t\t\t\t\tpTooltip=\"{{model." + columnName +"}}\" tooltipPosition=\"bottom\" filter=\"true\"\n" + 
                        "\t\t\t\t\t\t\t\t\t\t[showClear]=\"true\">\n" + 
                    "\t\t\t\t\t\t\t\t\t\t</p-dropdown>\n";
                                
           temp += "\t\t\t\t\t\t\t\t\t</div>\n"+
        "\t\t\t\t\t\t\t</div>\n\n";

        
        return temp;
        
    }
    
    public String getS3Template(String columnName, String col1, String listname) {
        String temp = "";
        temp += "\t\t\t\t\t\t\t<div class=\"col-md-3\">\n"+
            "\t\t\t\t\t\t\t\t\t<label>\n"+
               "\t\t\t\t\t\t\t\t\t\t" + columnName + "\n"+
                "\t\t\t\t\t\t\t\t\t\t<span style=\"color: red;\">*</span>\n"+
            "\t\t\t\t\t\t\t\t\t</label>\n"+
            "\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\n";
        
            temp += "\t\t\t\t\t\t\t\t\t\t<p-dropdown id=\"" + columnName +"\" name=\"" + columnName +"\" #" + columnName +"=\"ngModel\" "
                + "\t\t\t\t\t\t\t\t\t\t[options]=\""+ listname + "\"\n" + 
                        "\t\t\t\t\t\t\t\t\t\t[(ngModel)]=\"model." + columnName +"\" placeholder=\"" + columnName +"\" optionLabel=\"" + columnName +"\"\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + (col1.equals(columnName) ? "(onChange)=\"onSelect" + columnName + "(model." + columnName + ")\"" : "") + "\n" + 
                        "\t\t\t\t\t\t\t\t\t\tpTooltip=\"{{model." + columnName +"}}\" tooltipPosition=\"bottom\" filter=\"true\"\n" + 
                        "\t\t\t\t\t\t\t\t\t\t[showClear]=\"true\">\n" + 
                    "\t\t\t\t\t\t\t\t\t\t</p-dropdown>\n";
           
        temp +=  "\t\t\t\t\t\t\t\t\t</div>\n"+
        "\t\t\t\t\t\t\t</div>\n\n";

        return temp;
    }
    
}
