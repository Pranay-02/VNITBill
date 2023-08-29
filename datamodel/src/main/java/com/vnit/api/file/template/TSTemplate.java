/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.template;

import java.util.ArrayList;

/**
 *
 * @author Pranay Singhal
 */
public class TSTemplate {
    public String getTSString(ArrayList<Object> columns) {
        String temp = "";

        temp += "^00$01$m:constantsMap:entity_imports$^" + "\n\n";

        temp += "@Component({\n"+
            "\tselector: 'app-^$00$01$m:constantsMap:table_name$^-master',\n"+
            "\ttemplateUrl: './^$00$01$m:constantsMap:table_name$^-master.component.html',\n"+
            "\tstyleUrls: ['./^$00$01$m:constantsMap:table_name$^-master.component.css']\n"+
          "})\n\n"+
          "export class ^$00$01$m:constantsMap:cap_table_name$^MasterComponent implements OnInit {\n"+
            "\t@ViewChild('f', { static: false }) form: NgForm;\n"+
            "\tmodel: any = {}\n"+
            "\tmodelOneArray: any = [];\n"+
            "\tmodelList = []\n"+
            "\tsearchFromFilter: boolean = false;\n"+
            "\tfilters = \"\"\n\n"+

            "\tconstructor(private configService : ConfigService,private notificationServices: NotificationServices,private crudService: CrudService,) { }\n"+

            "\tngOnInit(): void {\n"+
                "\t\tthis.onRefresh()\n"+
            "\t}\n\n"+
            "\tonRefresh(){\n"+
                "\t\tthis.model = {\n"+
                 "\t\t\t \"ccode\": null,\n"+
                  "\t\t\t \"cname\": \"\",\n"+  
                "\t\t}\n"+
                "\t\tthis.modelOneArray = []\n\n"+

                "\t\tthis.modelList = []\n\n"+
            
                "\t\tthis.getModelList(\"\")\n\n"+
              "\t}\n\n"+
              
                "\tgetModelList(name){\n"+
                "\t\tthis.modelList = []\n"+
                "\t\tthis.crudService.commonActionPerformGet(credentials.INVENTORY + 'get_customer_list' + `${\"?\"+'name='}`+name).subscribe(response => {\n"+
                  "\t\t\tthis.modelList = response.data;\n"+
                "\t\t}, (error) => {\n"+
                  "\t\t\tconsole.log(\"getRewsRoomListError=\", JSON.stringify(error));\n"+
                "\t\t});\n"+
              "\t}\n"+

              "\tsearchByFilter(){\n"+
                "\t\tthis.getModelList(this.filters)\n"+
              "\t}\n\n"+
              
             "\tclearModelOne() {\n"+
             "\t\tthis.model = {\n"+
               "\t\t\t\"ccode\": null,\n"+  
               "\t\t\t\"cname\": \"\",\n"+  
             "\t\t}\n"+
             "\t\tdocument.getElementById(\"cname\").focus();\n"+
           "\t}\n"+
           "\taddModelOneArray() {\n"+
                "\t\tif (this.configService.isNullUndefined(this.model.cname) === false) {\n"+
                  "\t\t\tthis.notificationServices.showNotification(\'error\', \"^$00$01$m:constantsMap:cap_table_name$^ Name Required\");\n"+
                  "\t\t\tdocument.getElementById(\"cname\").focus();\n"+
                  "\t\t\treturn false;\n"+
                "\t\t}\n"+
                "\t\tvar json: any = {} = Object.assign({}, this.model);\n"+
                "\t\tif (json.index || json.index === 0) {\n"+
                "\t\t\tthis.modelOneArray[json.index] = json\n"+
                "\t\t\tconsole.log(\"old\")\n"+
                "\t\t}\n"+
                "\t\telse {\n"+
                "\t\t\tjson.index = this.modelOneArray.length;\n"+
                "\t\t\tthis.modelOneArray[json.index] = json\n"+
                "\t\t\tconsole.log(\"new\")\n"+
                "\t\t}\n"+
                "\t\tthis.clearModelOne()\n"+
            "\t}\n\n"+
            "\tasync deleteRowData(data, index) {\n"+
                "\t\tawait this.modelOneArray.splice(index, 1)\n"+
                "\t\tthis.modelOneArray.forEach((element, index) => {\n"+
                  "\t\t\telement[\"index\"] = index;\n"+
                "\t\t});\n"+
              "\t}\n\n"+
            
              "\tviewRowData(datas, index) {\n"+
                "\t\tvar tempData: any = {};\n"+
                "\t\ttempData = Object.assign({}, datas);\n"+
                "\t\tthis.model = tempData\n"+
              "\t}\n\n"+
            
              "\tonCancel(){\n"+
                "\t\tif(this.searchFromFilter === false){\n"+
                  "\t\t\tthis.searchFromFilter = true;\n"+
                "\t\t}\n"+
                "\t\telse{\n"+
                  "\t\t\tthis.searchFromFilter = false\n"+
                "\t\t}\n"+
                "\t\tthis.onRefresh()\n"+
              "\t}\n\n"+
            
              "\tasync onSave() {\n"+
              "\t\tthis.configService.enabledLoader();\n"+
              "\t\tif (this.modelOneArray.length === 0) {\n"+
                "\t\t\tthis.notificationServices.showNotification(\'error\', \"One sample detail must be added\");\n"+
                "\t\t\tthis.configService.disableLoader();\n"+
                "\t\t\treturn;\n"+
              "\t\t}\n\n"+
              "\t\tvar postJson: any = [];\n"+
             "\t\t// postJson = Object.assign({}, this.model);\n"+
              "\t\tpostJson = [... this.modelOneArray];\n\n"+
          
              "\t\tfor await (const [index, element] of postJson.entries()) {\n"+
                "\t\t\tawait this.saveElement(element)\n"+
              "\t\t}\n"+
              "\t\tthis.onRefresh()\n"+
              "\t\tthis.notificationServices.showNotification(\'success\', \"Save Successfully\");\n"+
              "\t\tthis.configService.disableLoader();\n"+
          
            "\t}\n\n"+
            "\tasync saveElement(element) {\n"+
                "\t\treturn new Promise(resolve => {\n"+
                  "\t\t\tthis.crudService.commonActionPerformPost(credentials.INVENTORY + \'post_^$00$01$m:constantsMap:table_name$^\', element).subscribe(async (response) => {\n"+
                    "\t\t\t\tif (response.status === await \"Success\") {\n"+
                      "\t\t\t\t\treturn resolve(response);\n"+
                    "\t\t\t\t}\n"+
                    "\t\t\t\telse {\n"+
                      "\t\t\t\t\tthis.notificationServices.showNotification(\'error\', response.message);\n"+
                      "\t\t\t\t\tthis.configService.disableLoader();\n"+
                      "\t\t\t\t\treturn resolve(response);\n"+
                    "\t\t\t\t}\n"+
                  "\t\t\t}, (error) => {\n"+
                    "\t\t\t\tconsole.log(\"getModelListError=\", JSON.stringify(error))\n"+
                    "\t\t\t\tthis.notificationServices.showNotification(\'error\', error);\n"+
                    "\t\t\t\tthis.configService.disableLoader();\n"+
                  "\t\t\t});\n"+
                "\t\t})\n"+
              "\t}\n\n"+

              "\tonDelete(modelOneArray,i){\n"+
                "\t\tthis.model = modelOneArray\n"+
              "\t}\n\n"+
              "\tconfirmDelete(){\n"+
              "\t\tthis.configService.enabledLoader()\n"+
                "\t\tthis.crudService.commonActionPerformDelete(credentials.INVENTORY + \'delete_^$00$01$m:constantsMap:table_name$^/\'+ this.model.ccode).subscribe(async (response) => {\n"+
                  "\t\t\tif (response.status === await \"Success\") {\n"+
                    "\t\t\t\tthis.notificationServices.showNotification(\'error\', response.message);\n"+
                    "\t\t\t\tthis.onRefresh()\n"+
                    "\t\t\t\tthis.configService.disableLoader()\n"+
                  "\t\t\t}\n"+
                  "\t\t\telse {\n"+
                    "\t\t\t\tthis.notificationServices.showNotification(\'error\', response.message);\n"+
                    "\t\t\t\tthis.onRefresh()\n"+
                    "\t\t\t\tthis.configService.disableLoader();\n"+
                  "\t\t\t}\n"+
                "\t\t}, (error) => {\n"+
                  "\t\t\tconsole.log(\"getModelListError=\", JSON.stringify(error))\n"+
                  "\t\t\tthis.notificationServices.showNotification(\'error\', error);\n"+
                  "\t\t\tthis.configService.disableLoader();\n"+
                "\t\t});\n"+
            "\t}\n\n"+
          "}\n"; 
        return temp;
    }
}
