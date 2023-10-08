/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.t1Template;

import java.util.ArrayList;
import main.java.com.vnit.api.file.col_object.Object;

/**
 *
 * @author Pranay Singhal
 */
public class TSTemplate {
    public String getPart1() {
        String temp = "";

        temp += "^00$01$m:constantsMap:ts_imports$^" + "\n\n";

        temp += "@Component({\n"+
            "\tselector: 'app-^$00$01$m:constantsMap:table_name$^',\n"+
            "\ttemplateUrl: './^$00$01$m:constantsMap:table_name$^.component.html',\n"+
            "\tstyleUrls: ['./^$00$01$m:constantsMap:table_name$^.component.css']\n"+
          "})\n\n"+
          "export class ^$00$01$m:constantsMap:cap_table_name$^Component implements OnInit {\n"+
            "\t@ViewChild('f', { static: false }) form: NgForm;\n"+
            "\tmodel: any = {}\n"+
            "\tmodelOneArray: any = [];\n"+
            "\tmodelList = []\n"+
            "\tsearchFromFilter: boolean = false;\n"+
            "\tfilters = \"\"\n\n"+

            "\tconstructor(private configService : ConfigService,"+
            "\n\t\tprivate notificationServices: NotificationServices,"+
            "\n\t\tprivate crudService: CrudService,) { }\n\n"+

            "\tngOnInit(): void {\n"+
                "\t\tthis.onRefresh()\n"+
            "\t}\n\n";

            return temp;
    }

    public String getPart2(ArrayList<Object> columns) {
      String temp = "";
      temp += "\tonRefresh(){\n";
      
      temp += getThisModel(columns);
      
      temp += "\t\tthis.modelOneArray = []\n\n"+
              "\t\tthis.modelList = []\n\n"+
              "\t\tthis.getModelList(\"\")\n\n"+
              "\t}\n\n";

      return temp;
    }

    public String getThisModel(ArrayList<Object> columns) {
      String temp = "";
      temp += "\t\tthis.model = {\n";
      
      for(int i = 0; i < columns.size(); i++) {
          String columnName = columns.get(i).getColumnName();
          temp += "\t\t\t\"^00$01$m2:fld:" + columnName + ":column_name$^\" : null,\n";
      }

      temp += "\t\t}\n";
      return temp;
    }

    public String getPart3() {
      String temp = "";

       temp += "\tgetModelList(name){\n"+
                "\t\tthis.modelList = []\n"+
                "\t\tthis.crudService.commonActionPerformGet(credentials.INVENTORY + 'get_^$00$01$m:constantsMap:table_name$^_list' + `${\"?\"+'name='}`+name).subscribe(response => {\n"+
                  "\t\t\tthis.modelList = response.data;\n"+
                "\t\t}, (error) => {\n"+
                  "\t\t\tconsole.log(\"getRewsRoomListError=\", JSON.stringify(error));\n"+
                "\t\t});\n"+
              "\t}\n"+

              "\tsearchByFilter(){\n"+
                "\t\tthis.getModelList(this.filters)\n"+
              "\t}\n\n";

      return temp;
    }

    public String getPart4(ArrayList<Object> columns) {
      String temp = "";
      temp +=  "\tclearModelOne() {\n";
      temp += getThisModel(columns);
      temp +=   "\t\tdocument.getElementById(\"" + "^00$01$m2:fld:" + columns.get(1).getColumnName() + ":column_name$^" + "\").focus();\n"+
           "\t}\n";
           
      return temp;
    }

    public String getPart5(ArrayList<Object> columns) {
      String temp = "";
      temp += "\taddModelOneArray() {\n";

      for(int i = 0; i < columns.size(); i++) {
        String columnName = columns.get(i).getColumnName();
        if(columns.get(i).getColumnPrimaryKey()) continue;
        temp +=  "\t\tif (this.configService.isNullUndefined(this.model." + "^00$01$m2:fld:" + columnName + ":column_name$^" +  ") === false) {\n"+
                  "\t\t\tthis.notificationServices.showNotification(\'error\', \"^00$01$m2:fld:" + columnName + ":column_name$^ Required\");\n"+
                  "\t\t\tdocument.getElementById(\"^00$01$m2:fld:" + columnName + ":column_name$^\").focus();\n"+
                  "\t\t\treturn false;\n"+
                "\t\t}\n";
               
      }

      return temp;
    }

    public String getPart6() {
      String temp = ""; 
      temp +=  "\t\tvar json: any = {} = Object.assign({}, this.model);\n"+
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
            "\t}\n\n";
          return temp;

    }

    public String getRowData() {
      String temp = "";
      temp += "\tasync deleteRowData(data, index) {\n"+
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
              "\t}\n\n";
          return temp;
    }

    public String getSave() {
      String temp = "";
      temp += "\tasync onSave() {\n"+
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
              "\t}\n\n";

        return temp;
    }

    public String getDelete(String columnName) {
      String temp = "";
      temp += "\tonDelete(modelOneArray,i){\n"+
                "\t\tthis.model = modelOneArray\n"+
              "\t}\n\n"+
              
              "\tconfirmDelete(){\n"+
              "\t\tthis.configService.enabledLoader()\n"+
                "\t\tthis.crudService.commonActionPerformDelete(credentials.INVENTORY + \'delete_^$00$01$m:constantsMap:table_name$^/\'+ this.model.^00$01$m2:fld:" + columnName + ":column_name$^).subscribe(async (response) => {\n"+
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
            "\t}\n\n";

          return temp;
    }

    public String getClosingBracket() {
      return "}";
    }

}
