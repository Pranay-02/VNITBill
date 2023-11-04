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
public class S2TSTemplate {
public String getPart1(ArrayList<Object> columns1, ArrayList<Object> columns2) {
        String temp = "";
        temp += "^00$01$m:constantsMap:s2ts_imports$^" + "\n\n";

        temp += "@Component({\n"+
            "\tselector: 'app-^$00$01$m:constantsMap:table_name1$^',\n"+
            "\ttemplateUrl: './^$00$01$m:constantsMap:table_name1$^.component.html',\n"+
            "\tstyleUrls: ['./^$00$01$m:constantsMap:table_name1$^.component.css']\n"+
          "})\n\n"+
          "export class ^$00$01$m:constantsMap:cap_table_name1$^Component implements OnInit {\n"+
            "\t@ViewChild('f', { static: false }) form: NgForm;\n"+
            "\tmodel: any = {}\n"+
            "\tmodel_two: any = {}\n"+
            "\tmodelTwoArray: any = [];\n"+
            "\tmodelList = []\n"+
            "\tsearchFromFilter: boolean = false;\n"+
            "\tfilters = \"\"\n\n" +
            "\tminDate = new Date()\n" + 

            "\tFORM_TYPE = \"\";\n" +
            "\tACTION_FLAG = \"\";\n\n" +
          
            "\tconstructor(private configService : ConfigService,"+
            "\n\t\tprivate notificationServices: NotificationServices,"+
            "\n\t\tprivate crudService: CrudService," + 
            "\n\t\tpublic router: Router," + 
            "\n\t\tpublic datepipe: DatePipe,) { }\n\n"+

            "\tngOnInit(): void {\n"+
                "\t\tthis.onRefresh()\n"+
            "\t}\n\n" +

            "onRefresh() {\n" + 
                "\tif (this.router.url === '/^$00$01$m:constantsMap:table_name1$^-entry') {\n" +
                  "\t\tthis.FORM_TYPE = \"BE\"\n" +  
                "\t}\n" + 
                "\tif (this.router.url === '/^$00$01$m:constantsMap:table_name1$^-entry-with-^$00$01$m:constantsMap:table_name1$^-type') {\n" + 
                  "\t\tthis.FORM_TYPE = \"BEWT\"\n" +
                "\t}\n\n" + 
            
                "\tthis.ACTION_FLAG = \"\"\n" +
                "\t\tthis.model = {\n";

                temp += getPart2(columns1);
                temp += "\t\t\t\"^$00$01$m:constantsMap:table_name2$^\" : []\n\n";

                temp += "\t}\n\n" + 
                "\tthis.model_two = {\n";

                temp += getPart2(columns2);

                temp += "\t}\n\n" + 
                "\tthis.modelTwoArray = []\n" + 
                "\tthis.modelList = []\n" + 
                "\tthis.getModelList(\"\")\n"+
                
                "}\n\n";

        return temp;
    }

    public String getPart2(ArrayList<Object> columns) {
        String temp = "";
        for(int i = 0; i  < columns.size(); i++) {
            temp += "\t\t\t\"" + columns.get(i).getColumnName() + "\": null,\n";
        }

        return temp;
    }

    public String getPart3(String columnName) {
        String temp = "";
        temp += "getModelList(type) {\n" + 
            "\t\tthis.modelList = []\n" + 
            "\t\tthis.crudService.commonActionPerformGet(credentials.INVENTORY + 'get_^$00$01$m:constantsMap:table_name1$^_list' + `${\"?\" + '" + columnName +"='}` + type).subscribe(response => {\n" + 
                "\t\t\tthis.modelList = response.data;\n" + 
            "\t\t}, (error) => {\n" + 
                "\t\t\tconsole.log(\"getRewsRoomListError=\", JSON.stringify(error))\n" + 
            "\t\t});\n" + 
            "}\n\n" + 

            "searchByFilter() {\n" + 
            "\t\tthis.getModelList(this.filters)\n" + 
            "}\n\n"+

            "addRow() {\n" +
                "\tif (this.modelTwoArray.length !== 0) {\n" +
                  "\t\tif (this.configService.isNullUndefined(this.modelTwoArray[this.modelTwoArray.length - 1]['" + columnName + "']) === false) {\n" +
                    "\t\t\tthis.notificationServices.showNotification('error', \"Row already added\");\n" +
                    "\t\t\treturn;\n" +
                  "\t\t}\n" +
                "\t}\n" +
                "\tconsole.log(JSON.stringify(this.model_two));\n" +
                
                "\tvar json: any = {} = Object.assign({}, this.model_two);\n" +
                "\tthis.modelTwoArray.push(json)\n" +
                "\tconsole.log(JSON.stringify(this.modelTwoArray));\n" +
                "\tthis.clearModelTwo();\n" +
              "}\n\n" + 

              "async deleteRowData(data, index) {\n" + 
                "\tawait this.modelTwoArray.splice(index, 1)\n" + 
                "\tthis.modelTwoArray.forEach((element, index) => {\n" + 
                  "\t\telement[\"index\"] = index;\n" + 
                "\t});\n" + 
              "}\n\n" + 

              "onCancel() {\n" + 
                "\tif (this.searchFromFilter === false) {\n" + 
                  "\t\tthis.searchFromFilter = true;\n" + 
                "\t}\n" + 
                "\telse {\n" + 
                  "\t\tthis.searchFromFilter = false;\n" + 
                "\t}\n" + 
                "\tthis.onRefresh();\n" + 
              "}\n\n" + 

              "\tgetDataby" + columnName + "(datas) {\n" + 
                "\t\treturn new Promise(resolve => {\n" + 
                  "\t\t\tthis.crudService.commonActionPerformGet(credentials.INVENTORY + 'get_^$00$01$m:constantsMap:table_name1$^/' + datas['" + columnName + "']).subscribe(response => {\n" + 
                    "\t\t\t\treturn resolve(response)\n" + 
                  "\t\t\t}, (error) => {\n" + 
                    "\t\t\t\tconsole.log(\"getRewsRoomListError=\", JSON.stringify(error))\n" + 
                  "\t\t\t});\n" + 
                "\t\t})\n" + 
              "\t}\n" ;
        
        return temp;
    } 

    public String getPart4(ArrayList<Object> columns1, ArrayList<Object> columns2) {
        String temp = "";
        temp += "\n\tclearModelOne() {\n" + 
            "\t\tthis.model = {\n" ;

        temp += getPart2(columns1);
        temp += "\t\t\t\"^$00$01$m:constantsMap:table_name2$^\" : []\n\n";


        temp += "\t\t}\n" + 
            "\t}\n\n" + 
            "\tclearModelTwo() {\n" + 
            "\t\tthis.model_two = {\n" ;
        
        temp += getPart2(columns2);
        temp += "\t\t}\n" + 
            "\t}\n\n"; 

        return temp;
    }

    public String getEditData(String columnName) {
      String temp = "";
      temp += "\tasync editRowData(datas, index) {\n" + 
        "\t\tthis.ACTION_FLAG = \"EDIT\"\n\n" +
        "\t\tlet response = await this.getDataby" + columnName + "(datas)\n" + 

        "\t\tfor await (const [index, element] of response['^$00$01$m:constantsMap:table_name2$^'].entries()) {\n" + 
          "\t\t\telement['index'] = index;\n" + 
        "\t\t}\n\n" + 
    
        "\t\tvar tempData: any = {};\n" + 
        "\t\ttempData = Object.assign({}, response);\n" + 
        "\t\tthis.model = tempData\n" + 
        "\t\tthis.modelTwoArray = tempData['^$00$01$m:constantsMap:table_name2$^']\n" + 
      "\t}\n\n" ; 
    
      return temp;
    }

    public String getPart5(ArrayList<Object> columns1, ArrayList<Object> columns2, ArrayList<String> PK1) {
      String temp = "";

      temp += "\tasync onSave() {\n\n" + 
            "\t\tthis.configService.enabledLoader();\n" ;

      for(int i = 0; i < columns1.size(); i++) {
        if(columns1.get(i).getColumnPrimaryKey()) continue;
        temp += getPart6(columns1.get(i).getColumnName()); 
      }

      temp += "\t\tif (this.modelTwoArray.length === 0) {\n" + 
        "\t\t\tthis.notificationServices.showNotification('error', \"One Entry detail must be added\");\n" + 
        "\t\t\tthis.configService.disableLoader();\n" + 
        "\t\t\treturn;\n" + 
      "\t\t}\n\n" +

      "\t\tif (this.modelTwoArray.length !== 0) {\n" + 
        "\t\t\tfor await (const [index, element] of this.modelTwoArray.entries()) {\n";
    
      for(int i = 0; i < columns2.size(); i++) {
        if(PK1.contains(columns2.get(i).getColumnName())) continue;
          temp += getPart7(columns2.get(i).getColumnName());
      }

      temp += "\t\t\t}\n" + 
        "\t\t}\n\n" + 

      "\t\tvar postJson: any = {};\n" +
      "\t\tpostJson = Object.assign({}, this.model);\n" + 
      "\t\tpostJson['^$00$01$m:constantsMap:table_name2$^'] = this.modelTwoArray;\n" ;

      temp += "\n\t\t//SECOND LEVEL JSON\n" + 
      "\t\tfor await (const [index, element] of postJson['^$00$01$m:constantsMap:table_name2$^'].entries()) {\n";

      for(int i = 0; i < columns2.size(); i++) {
        if(columns2.get(i).getColumnPrimaryKey() && !PK1.contains(columns2.get(i).getColumnName())) {
          temp += getPart8(columns2.get(i).getColumnName());
        }
      }
        
      temp += "\t\t\telement['index'] = undefined;\n" +
      "\t\t}\n\n" ; 

      return temp;
    }

    public String getPart6(String columnName) {
      String temp = "";
      temp += "\t\t\tif (this.configService.isNullUndefined(this.model." + columnName + ") === false) {\n" + 
        "\t\t\t\tthis.notificationServices.showNotification('error', \"" + columnName + " Required\");\n" + 
        "\t\t\t\tdocument.getElementById(\"" + columnName + "\").focus();\n" + 
        "\t\t\t\tthis.configService.disableLoader();\n" + 
        "\t\t\t\treturn;\n" + 
      "\t\t\t};\n\n" ;

      return temp;
    }

    public String getPart7(String columnName) {
      String temp = "";
      temp += "\t\t\t\tif (this.configService.isNullUndefined(element['" + columnName +"']) === false) {\n" + 
        "\t\t\t\t\tdocument.getElementById(\"" + columnName +"\" + index).focus();\n" + 
        "\t\t\t\t\tthis.notificationServices.showNotification('error', \"Select " + columnName + " for row \" + (index + 1));\n" + 
        "\t\t\t\t\tthis.configService.disableLoader();\n" + 
        "\t\t\t\t\treturn;\n" + 
      "\t\t\t\t}\n";

      return temp;
    }

    public String getPart8(String columnName) {
      String temp = "";
      temp += "\t\tawait (this.configService.isNullUndefined(postJson." + columnName +") === true ? postJson." + columnName + " = " + 
      "postJson." + columnName + "['" + columnName +"'] : postJson." + columnName +" = postJson." + columnName + ")\n";
      
      return temp;
    }

    public String getPostDelete(String columnName) {
        String temp = "";
        temp += "\t\tthis.crudService.commonActionPerformPost(credentials.INVENTORY + 'post_^$00$01$m:constantsMap:table_name1$^', postJson).subscribe(async (response) => {\n" + 
          "\t\t\tif (response.status === await \"Success\") {\n" + 
            "\t\t\t\tthis.notificationServices.showNotification('success', response.message + \" \" + \"Id =\" + response.id);\n" + 
            "\t\t\t\tthis.onRefresh()\n" + 
            "\t\t\t\tthis.configService.disableLoader();\n" + 
          "\t\t\t}\n" + 
          "\t\t\telse {\n" + 
            "\t\t\t\tthis.notificationServices.showNotification('error', response.message);\n" + 
            "\t\t\t\tthis.configService.disableLoader();\n" + 
          "\t\t\t}\n" + 
        "\t\t}, (error) => {\n" + 
          "\t\t\tconsole.log(\"getModelListError=\", JSON.stringify(error))\n" + 
          "\t\t\tthis.notificationServices.showNotification('error', error);\n" + 
          "\t\t\tthis.configService.disableLoader();\n" + 
        "\t\t});\n" + 
    
      "}\n\n\n" + 
    
    
      "onDelete(modelTwoArray, i) {\n" + 
        "\tconsole.log(\"onDelete =\", modelTwoArray)\n" + 
        "\tthis.model = modelTwoArray\n" + 
      "}\n\n" + 
    
      "confirmDelete() {\n" + 
        "\tthis.configService.enabledLoader()\n" + 
        "\tthis.crudService.commonActionPerformDelete(credentials.INVENTORY + 'delete_^$00$01$m:constantsMap:table_name1$^/' + this.model." + columnName + ").subscribe(async (response) => {\n" + 
          "\t\t\tif (response.status === await \"Success\") {\n" + 
            "\t\t\t\tthis.notificationServices.showNotification('error', response.message);\n" + 
            "\t\t\t\tthis.onRefresh()\n" + 
            "\t\t\t\tthis.configService.disableLoader()\n" + 
          "\t\t\t}\n" + 
          "\t\t\telse {\n" + 
            "\t\t\t\tthis.notificationServices.showNotification('error', response.message);\n" + 
            "\t\t\t\tthis.onRefresh()\n" + 
            "\t\t\t\tthis.configService.disableLoader();\n" + 
          "\t\t\t}\n" + 
        "\t\t}, (error) => {\n" + 
          "\t\t\tconsole.log(\"getModelListError=\", JSON.stringify(error))\n" + 
          "\t\t\tthis.notificationServices.showNotification('error', error);\n" + 
          "\t\t\tthis.configService.disableLoader();\n" + 
        "\t\t});\n" + 
      "\t}\n" +
        "}\n";
        return temp;
    }
    
}
