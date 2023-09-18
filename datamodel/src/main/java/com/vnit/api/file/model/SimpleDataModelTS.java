package com.vnit.api.file.model;
import java.util.List;
import com.vnit.api.file.utility.Utility;
import com.vnit.api.file.col_object.ColumnObject;
import com.vnit.api.file.columnobjectlist.ColumnObjectList;
import static com.vnit.api.file.model.SimpleDataModel.nameCase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import main.java.com.vnit.api.file.t1Template.TSTemplate;
import main.java.com.vnit.api.file.utility.ProcessSubstitution;
import main.java.com.vnit.api.file.col_object.Object;
import main.java.com.vnit.api.file.utility.DbUtility;

public class SimpleDataModelTS {
    ColumnObjectList c2=new ColumnObjectList();
    private String table_name=c2.getTablename();
    Map<String,String> map=new HashMap<>();
    
    public SimpleDataModelTS(Map<String,String> map){//creating constructor for map
        Utility u1=new Utility();
         u1.setMap(map);
         this.map=map;
    }   
    
    public  List <ColumnObject> setTlist() throws SQLException{//creating constructor for map
        ColumnObjectList c1=new ColumnObjectList();
        List<ColumnObject> tlist = c1.getData(this.table_name);         
        return tlist;
    }
    
    
    public String componentString(){
        return "\n@Component({\n" +
"  selector: 'app-"+this.table_name+"',\n" +
"  templateUrl: './"+this.table_name+".component.html',\n" +
"  styleUrls: ['./"+this.table_name+".component.css']\n" +
"})";
    }
    
    public String ts1(List<ColumnObject> list) throws SQLException{
        String input1="export class "+nameCase(this.table_name)+"Component implements OnInit {\n" +
"  @ViewChild('f', { static: false }) form: NgForm;\n" +
"  model: any = {}\n" +
"  modelOneArray: any = [];\n" +
"  modelList = []\n" +
"  searchFromFilter: boolean = false;\n" +
"  filters = \"\"\n" +
"\n" +
"  constructor(private configService : ConfigService,\n" +
"    private notificationServices: NotificationServices,\n" +
"    private crudService: CrudService,) { }\n" +
"\n" +
"  ngOnInit(): void {\n" +
"    this.onRefresh()\n" +
"  }\n" +
"\n" +
"  onRefresh(){\n" +
"    this.model = {\n";
        
        String input2="";
        for(int i=0;i<list.size();i++){
//            if(list.get(i).getJavadatatype().equals("String")){
//                System.out.print(list.get(i).getJavadatatype());
//            }
            
            if(list.get(i).getJavadatatype().equals("String")){
                input2=input2+"\""+list.get(i).getColname()+"\": \"\",\n";
                
            }else{
                input2=input2+"\""+list.get(i).getColname()+"\": null,\n";
            }
        }
        
        String input3="\n}\nthis.modelOneArray = []\n" +
"\n" +
"    this.modelList = []\n" +
"\n" +
"    this.getModelList(\"\")\n" +
"  }\n";
        
        return input1+input2+input3;
    }
    
    public String ts2(List<ColumnObject> list){
         
        return "getModelList(name){\n" +
"    this.modelList = []\n" +
"    // let param = { \"param1\": param1, \"param10\": \"\", \"param2\": param2, \"param3\": \"\", \"param4\": \"\", \"param5\": \"\", \"param6\": \"\", \"param7\": \"\", \"param8\": \"\", \"param9\": \"\" }\n" +
"    // let filter = [{ \"property\": \"hcmCountryName\", \"operator\": \"like\", \"value\": `${countryName.toString()}` }]\n" +
"    // let encodedParamter = \"filter=\" + `${encodeURI(JSON.stringify(filter))}` + \"&limit=\" + `${encodeURI(\"25\")}` + \"&sort=\" + `${encodeURI(\"[]\")}` + \"&start=\" + `${encodeURI(\"0\")}`\n" +
"    this.crudService.commonActionPerformGet(credentials.INVENTORY + 'get_"+this.table_name+"_list' + `${\"?\"+'name='}`+name).subscribe(response => {\n" +
"      this.modelList = response.data;\n" +
"    }, (error) => {\n" +
"      console.log(\"getRewsRoomListError=\", JSON.stringify(error))\n" +
"    });\n" +
"  }\n" +
"  \n" +
"  searchByFilter(){\n" +
"    this.getModelList(this.filters)\n" +
"  }\n";
    }
    
    public String ts3(List<ColumnObject> list) throws SQLException{
        String input1="clearModelOne() {\n" +
"    this.model = {";
        String input2="";
        for(int i=0;i<list.size();i++){
            if(list.get(i).getJavadatatype().equals("String")){
                input2=input2+"\""+list.get(i).getColname()+"\": \"\",\n";
                
            }else{
                input2=input2+"\""+list.get(i).getColname()+"\": null,\n";
            }
        }
        String input3="\n}\n";
        for(int i=0;i<list.size();i++){
            if(list.get(i).getJavadatatype().equals("String")){
                input3=input3+"\ndocument.getElementById(\""+list.get(i).getColname()+"\").focus();";
                
            }
        }
        
        String input4="\n}\n";
        
        
        return input1+input2+input3+input4;
    }
    
    public String ts4(List<ColumnObject> list) throws SQLException{
        String input1="\n\naddModelOneArray() {\n";
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCheckvalue()==false){
                input1=input1+"\n if (this.configService.isNullUndefined(this.model."+list.get(i).getColname()+") === false) {\n" +
"      this.notificationServices.showNotification('error', \""+nameCase(this.table_name)+" Required\");\n" +
"      document.getElementById(\""+list.get(i).getColname()+"\").focus();\n" +
"      return false;\n" +
"    }";
            }
        }
        String input2="\n var json: any = {} = Object.assign({}, this.model);\n" +
"    if (json.index || json.index === 0) {\n" +
"      this.modelOneArray[json.index] = json\n" +
"      console.log(\"old\")\n" +
"    }\n" +
"    else {\n" +
"      json.index = this.modelOneArray.length;\n" +
"      this.modelOneArray[json.index] = json\n" +
"      console.log(\"new\")\n" +
"    }\n" +
"    this.clearModelOne()\n" +
"  }\n" +
"\n" +
"  async deleteRowData(data, index) {\n" +
"    await this.modelOneArray.splice(index, 1)\n" +
"    this.modelOneArray.forEach((element, index) => {\n" +
"      element[\"index\"] = index;\n" +
"    });\n" +
"  }\n" +
"\n" +
"  viewRowData(datas, index) {\n" +
"    var tempData: any = {};\n" +
"    tempData = Object.assign({}, datas);\n" +
"    this.model = tempData\n" +
"  }\n" +
"\n" +
"  onCancel(){\n" +
"    if(this.searchFromFilter === false){\n" +
"      this.searchFromFilter = true;\n" +
"    }\n" +
"    else{\n" +
"      this.searchFromFilter = false\n" +
"    }\n" +
"    this.onRefresh()\n" +
"  }\n" +
"\n" +
"\n" +
"  async onSave() {\n" +
"    this.configService.enabledLoader();\n" +
"    if (this.modelOneArray.length === 0) {\n" +
"      this.notificationServices.showNotification('error', \"One customer detail must be added\");\n" +
"      this.configService.disableLoader();\n" +
"      return;\n" +
"    }\n" +
"\n" +
"    var postJson: any = [];\n" +
"    // postJson = Object.assign({}, this.model);\n" +
"    postJson = [... this.modelOneArray];\n" +
"\n" +
"    for await (const [index, element] of postJson.entries()) {\n" +
"      await this.saveElement(element)\n" +
"    }\n" +
"    this.onRefresh()\n" +
"    this.notificationServices.showNotification('success', \"Save Successfully\");\n" +
"    this.configService.disableLoader();\n" +
"\n" +
"  }";           
        return input1+input2;
    }
    
    public String ts5(List<ColumnObject> list){
        
        return  " \nasync saveElement(element) {\n" +
"    return new Promise(resolve => {\n" +
"      this.crudService.commonActionPerformPost(credentials.INVENTORY + 'post_"+this.table_name+"', element).subscribe(async (response) => {\n" +
"        if (response.status === await \"Success\") {\n" +
"          return resolve(response);\n" +
"        }\n" +
"        else {\n" +
"          this.notificationServices.showNotification('error', response.message);\n" +
"          this.configService.disableLoader();\n" +
"          return resolve(response);\n" +
"        }\n" +
"      }, (error) => {\n" +
"        console.log(\"getModelListError=\", JSON.stringify(error))\n" +
"        this.notificationServices.showNotification('error', error);\n" +
"        this.configService.disableLoader();\n" +
"      });\n" +
"    })\n" +
"  }";
    }
    
    public String ts6(List<ColumnObject> list) throws SQLException{
        
        String input1= "";
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCheckvalue()==true){
                input1=input1+ "\nonDelete(modelOneArray,i){\n" +
"    this.model = modelOneArray\n" +
"  }\n" +
"\n" +
"  confirmDelete(){\n" +
"    this.configService.enabledLoader()\n" +
"      this.crudService.commonActionPerformDelete(credentials.INVENTORY + 'delete_"+this.table_name+"/'+ this.model."+list.get(i).getColname()+").subscribe(async (response) => {\n" +
"        if (response.status === await \"Success\") {\n" +
"          this.notificationServices.showNotification('error', response.message);\n" +
"          this.onRefresh()\n" +
"          this.configService.disableLoader()\n" +
"        }\n" +
"        else {\n" +
"          this.notificationServices.showNotification('error', response.message);\n" +
"          this.onRefresh()\n" +
"          this.configService.disableLoader();\n" +
"        }\n" +
"      }, (error) => {\n" +
"        console.log(\"getModelListError=\", JSON.stringify(error))\n" +
"        this.notificationServices.showNotification('error', error);\n" +
"        this.configService.disableLoader();\n" +
"      });\n" +
"  }\n" +
"}";
            }
        }
                
        return input1;
    }
 
    public String makeTSFile(String name) throws SQLException{
//         this.table_name=name;
//         List<ColumnObject> list=this.setTlist();
//       return map.get("tspackage")+this.componentString()+this.ts1(list)+this.ts2(list)+this.ts3(list)+this.ts4(list)+this.ts5(list)+this.ts6(list);
  
            ArrayList<Object> columns = DbUtility.columns;
            String columnName = DbUtility.primaryKeyColumn;
            return generateTSFile(columns, columnName);
    
    }
     
     public String generateTSFile(ArrayList<Object> columns, String columnName) {
        TSTemplate tsTemplate = new TSTemplate();
        ProcessSubstitution ps = new ProcessSubstitution();
         
         String template = "";
        template += tsTemplate.getPart1();
        template += tsTemplate.getPart2(columns);
        template += tsTemplate.getPart3();
        template += tsTemplate.getPart4(columns);
        template += tsTemplate.getPart5(columns);
        template += tsTemplate.getPart6();
        template += tsTemplate.getRowData();
        template += tsTemplate.getSave();
        template += tsTemplate.getDelete(columnName);
        template += tsTemplate.getClosingBracket();
        template = ps.processTemplate(template);

        return template;
    }
        
}
