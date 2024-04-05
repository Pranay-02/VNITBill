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
public class ScreenListTSTemplate {
    
    public String getListDetails2(String listname, String functionName) {
            String temp = "";
            
            temp += "\tget" + listname + "(name) {\n" + 
                    "\t\tthis." + listname + " = []\n" + 
                    "\t\tthis.crudService.commonActionPerformGet(credentials.INVENTORY + '" + functionName + "' + `${\"?\" + 'name='}` + name).subscribe(response => {\n" + 
                      "\t\t\tthis." + listname + " = response.data;\n" + 
                    "\t\t}, (error) => {\n" + 
                      "\t\t\tconsole.log(\"getRewsRoomListError=\", JSON.stringify(error))\n" + 
                    "\t\t});\n" + 
                  "\t}\n\n";
            
            return temp ;
    }

}
