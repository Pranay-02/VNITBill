package com.vnit.api.file.model;

import java.util.List;
import com.vnit.api.file.utility.Utility;
import com.vnit.api.file.col_object.ColumnObject;
import com.vnit.api.file.columnobjectlist.ColumnObjectList;
import com.vnit.api.file.dbConnection.DBConnection;
import static com.vnit.api.file.model.SimpleDataModel.nameCase;
import com.vnit.api.file.utility.TestServlet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import main.java.com.vnit.api.file.t1Template.HTMLTemplate;
import main.java.com.vnit.api.file.utility.ProcessSubstitution;
import main.java.com.vnit.api.file.col_object.Object;
import main.java.com.vnit.api.file.t2Template.S2HtmlTemplate;
import main.java.com.vnit.api.file.utility.DbUtility;

public class SimpleDataModelHtml {
    ColumnObjectList c2=new ColumnObjectList();
    private String table_name=c2.getTablename();
    Map<String,String> map=new HashMap<>();
    
    public SimpleDataModelHtml(Map<String,String> map){//creating constructor for map
        Utility u1=new Utility();
         u1.setMap(map);
         this.map=map;
    }
    public  List <ColumnObject> setTlist() throws SQLException{//creating constructor for map
        ColumnObjectList c1=new ColumnObjectList();
        List<ColumnObject> tlist = c1.getData(this.table_name);         
        return tlist;
    }
     
   public String inputhtml1(List<ColumnObject> list) throws SQLException{
       
       String start="\n<div class=\"card card-default\">\n" +
"    <br>\n" +
"    <form name=\"form\" #f=\"ngForm\">\n" +
"\n" +
"        <span *ngIf=\"searchFromFilter !== true\">\n" +
"            <div class=\"row\">\n" +
"                <div class=\"col-md-6 offset-md-3\">\n" +
"                    <fieldset class=\"scheduler-border col-md-12\">\n" +
"                        <legend class=\"scheduler-border\"> "+nameCase(this.table_name)+" Master:\n" +
"                            <p id=\"demo\"></p>\n" +
"                        </legend>\n" +
"                        <div class=\"col-md-12\">\n" +
"                            <div class=\"row\">\n";

       String input="";
        
            for(int i=0;i<list.size();i++){
             if(list.get(i).getCheckvalue()==true){
                 if(list.get(i).getJavadatatype().equals("String")){
                       input=input+"\t<div class=\"col-md-4\">\n" +
"                                    <label>\n" +
"                        "+nameCase(this.table_name)+" "+list.get(i).getColname()+" \n" +
"                                        <span style=\"color: red;\">*</span>\n" +
"                                    </label>\n" +
"                                    <div class=\"form-group\">\n" +
"                                        <input type=\"text\" class=\"form-control input-sm\" id=\""+list.get(i).getColname()+"\" name=\""+list.get(i).getColname()+"\"\n" +
"                                            [(ngModel)]=\"model."+list.get(i).getColname()+"\" #"+list.get(i).getColname()+"=\"ngModel\" placeholder=\""+nameCase(list.get(i).getColname())+" \"\n" +
"                                            maxlength=\"20\" pTooltip=\"{{model."+list.get(i).getColname()+"}}\"\n" +
"                                            tooltipPosition=\"bottom\" [disabled]=\"true\">\n" +
"                                    </div>\n" +
"                                </div>\n";
                     
                 }else{
                       input=input+"\t<div class=\"col-md-4\">\n" +
"                                    <label>\n" +
"                        "+nameCase(this.table_name)+" "+list.get(i).getColname()+" \n" +
"                                        <span style=\"color: red;\">*</span>\n" +
"                                    </label>\n" +
"                                    <div class=\"form-group\">\n" +
"                                        <input type=\"number\" class=\"form-control input-sm\" id=\""+list.get(i).getColname()+"\" name=\""+list.get(i).getColname()+"\"\n" +
"                                            [(ngModel)]=\"model."+list.get(i).getColname()+"\" #"+list.get(i).getColname()+"=\"ngModel\" placeholder=\""+nameCase(list.get(i).getColname())+" \"\n" +
"                                            maxlength=\"20\" pTooltip=\"{{model."+list.get(i).getColname()+"}}\"\n" +
"                                            tooltipPosition=\"bottom\" [disabled]=\"true\">\n" +
"                                    </div>\n" +
"                                </div>\n";
                     
                 }
               
             }else{
                 if(list.get(i).getJavadatatype().equals("Date")){
                     input=input+"<div class=\"col-md-4\">\n" +
"                                <label>\n" +
"                                    "+nameCase(list.get(i).getColname())+"\n" +
"                                    <span style=\"color: red;\">*</span>\n" +
"                                </label>\n" +
"                                <div class=\"form-group\">\n" +
"                                    <p-calendar [style]=\"{'width':'100%' ,'height':'40px'}\"\n" +
"                                        [inputStyle]=\"{'width':'100%' ,'height':'40px'}\" [showIcon]=\"true\" id=\""+list.get(i).getColname()+"\"\n" +
"                                        name=\""+list.get(i).getColname()+"\" [(ngModel)]=\"model."+list.get(i).getColname()+"\" dateFormat=\"dd-mm-yy\"\n" +
"                                        placeholder=\"dd-mm-yy\" [minDate]=\"minDate\" pTooltip=\"{{model."+list.get(i).getColname()+"}}\"\n" +
"                                        tooltipPosition=\"bottom\">\n" +
"                                    </p-calendar>\n" +
"                                </div>\n" +
"                            </div>";
                 }else{
                     if(list.get(i).getJavadatatype().equals("String")){
                          input=input+"\t<div class=\"col-md-4\">\n" +
"                                    <label>\n" +
"                        "+nameCase(this.table_name)+" "+list.get(i).getColname()+" \n" +
"                                        <span style=\"color: red;\">*</span>\n" +
"                                    </label>\n" +
"                                    <div class=\"form-group\">\n" +
"                                        <input type=\"text\" class=\"form-control input-sm\" id=\""+list.get(i).getColname()+"\" name=\""+list.get(i).getColname()+"\"\n" +
"                                            [(ngModel)]=\"model."+list.get(i).getColname()+"\" #"+list.get(i).getColname()+"=\"ngModel\" placeholder=\""+nameCase(list.get(i).getColname())+" \"\n" +
"                                            maxlength=\"20\" pTooltip=\"{{model."+list.get(i).getColname()+"}}\"\n" +
"                                            tooltipPosition=\"bottom\">\n" +
"                                    </div>\n" +
"                                </div>\n";
                         
                     }else{
                   input=input+"\t<div class=\"col-md-4\">\n" +
"                                    <label>\n" +
"                        "+nameCase(this.table_name)+" "+list.get(i).getColname()+" \n" +
"                                        <span style=\"color: red;\">*</span>\n" +
"                                    </label>\n" +
"                                    <div class=\"form-group\">\n" +
"                                        <input type=\"number\" class=\"form-control input-sm\" id=\""+list.get(i).getColname()+"\" name=\""+list.get(i).getColname()+"\"\n" +
"                                            [(ngModel)]=\"model."+list.get(i).getColname()+"\" #"+list.get(i).getColname()+"=\"ngModel\" placeholder=\""+nameCase(list.get(i).getColname())+"\"\n" +
"                                            maxlength=\"20\" pTooltip=\"{{model."+list.get(i).getColname()+"}}\"\n" +
"                                            tooltipPosition=\"bottom\">\n" +
"                                    </div>\n" +
"                                </div>\n";
                     }
                 }
             }
       
    }
   return start+input+map.get("add_button"); 
}
   
   public String inputhtml2(List<ColumnObject> list) throws SQLException{
       String start="\n<div class=\"row\" *ngIf=\"modelOneArray.length !== 0\">\n" +
"                <div class=\"col-md-6 offset-md-3\">\n" +
"                    <fieldset class=\"scheduler-border col-md-12\">\n" +
"                        <legend class=\"scheduler-border\">"+nameCase(this.table_name)+" Master Details:</legend>\n" +
"                        <p-table #dt [value]=\"modelOneArray\" scrollHeight=\"300px\" [scrollable]=\"true\"\n" +
"                            [style]=\"{width:'auto'}\"\n" +
"                            styleClass=\"p-datatable-responsive-demo p-datatable-striped custom-class\">\n" +
"                            <ng-template pTemplate=\"header\" let-columns>\n" +
"                                <tr style=\"font-size: 10px;\">\n" +
"                                    <th pResizableColumn>Sr.no</th>\n";
       
       String mid=" \n<th>Edit</th>\n" +
"                                    <th>Delete</th>\n" +
"                                </tr>\n" +
"                            </ng-template>\n" +
"                            <ng-template pTemplate=\"body\" let-modelOneArray let-i=\"rowIndex\">\n" +
"                                <tr *ngIf=\"modelOneArray.flag !== 'delete'\" style=\"font-size: 10px; font-weight: bold;\">\n" +
"                                    <td>\n" +
"                                        <span style=\"margin-left: 10px;\" class=\"p-column-title\">Sr.no</span>\n" +
"                                        {{i+1}}\n" +
"                                    </td>\n";
       
       String input="";
       for(int i=0;i<list.size();i++){
           input=input+"\n<th pResizableColumn>"+nameCase(this.table_name)+" "+nameCase(list.get(i).getColname())+"</th>\n";
       }
       
       String input1="";
       for(int i=0;i<list.size();i++){
           input1=input1+"\n<td>\n" +
"                                        <span style=\"margin-left: 10px;\" class=\"p-column-title\">"+nameCase(this.table_name)+" "+nameCase(list.get(i).getColname())+"</span>\n" +
"                                        {{modelOneArray."+list.get(i).getColname()+"}}\n" +
"                                    </td>\n";
       }
       
       String end="</tr>\n" +
"                            </ng-template>\n" +
"                        </p-table>\n" +
"                    </fieldset>\n" +
"                </div>\n" +
"            </div>\n" +
"        </span>\n" +
"    </form>\n";
       
       return start+input+mid+input1+map.get("edit_button")+map.get("delete_button")+end;
   }

   public String inputhtml3(List<ColumnObject> list) throws SQLException{
       String start="\n<div class=\"row\" *ngIf=\"searchFromFilter === true\">\n" +
"        <div class=\"col-md-6 offset-md-3\">\n" +
"            <fieldset class=\"scheduler-border col-md-12\">\n" +
"                <legend class=\"scheduler-border\">"+nameCase(this.table_name)+" Details:</legend>\n" +
"                <p-table #dt [value]=\"modelList\" scrollHeight=\"300px\" [scrollable]=\"true\"\n" +
"                    [style]=\"{width:'auto'}\"\n" +
"                    styleClass=\"p-datatable-responsive-demo p-datatable-striped custom-class\">\n" +
"                    <ng-template pTemplate=\"caption\">\n" +
"                        <div class=\"col-md-12\">\n" +
"                            <div class=\"row\">\n" +
"                                <div class=\"col-sm-8\">\n" +
"                                    <tr>";
       
       // search box one is remaining.
       String search="";
       for(int i=0;i<list.size();i++){
           if(list.get(i).getCheckvalue()==false){
               search=search+"<th pResizableColumn>\n" +
"                                            <span class=\"p-float-label\" style=\"margin-right: 10px;\">\n" +
"                                                <div class=\"form-group\">\n" +
"                                                    <!-- <i class=\"pi pi-search\"></i> -->\n" +
"                                                    <input type=\"text\" class=\"form-control\" pInputText id=\"search\"\n" +
"                                                        name=\"search\" [(ngModel)]=\"filters\"\n" +
"                                                        (input)=\"searchByFilter()\">\n" +
"                                                    <label style=\"margin-left: 20px;\" for=\"search\"><span\n" +
"                                                            style=\"font-size: 10px;\">"+nameCase(list.get(i).getColname())+"</span></label>\n" +
"                                                </div>\n" +
"                                            </span>\n" +
"                                        </th>";
           }
       }
       //Refresh Button
       String start1=" </tr>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                    </ng-template>";
       
       String mid="\n<ng-template pTemplate=\"header\" let-columns>\n" +
"                        <tr style=\"font-size: 10px;\">\n" +
"                            <th pResizableColumn>Sr.no</th>";
       
       String mid1="";
       for(int i=0;i<list.size();i++){
           mid1=mid1+"\n<th pResizableColumn>"+nameCase(this.table_name)+" "+nameCase(list.get(i).getColname())+"</th>\n";
       }
       String mid2="<th>Edit</th>\n" +
"                            <th>Delete</th>\n" +
"                        </tr>\n" +
"                    </ng-template>\n" +
"                    <ng-template pTemplate=\"body\" let-modelOneArray let-i=\"rowIndex\">\n" +
"                        <tr *ngIf=\"modelOneArray.flag !== 'delete'\" style=\"font-size: 10px; font-weight: bold;\">\n" +
"                            <td>\n" +
"                                <span style=\"margin-left: 10px;\" class=\"p-column-title\">Sr.no</span>\n" +
"                                {{i+1}}\n" +
"                            </td>\n";
       
        String mid3="";
       for(int i=0;i<list.size();i++){
           mid3=mid3+"\n<td>\n" +
"                                        <span style=\"margin-left: 10px;\" class=\"p-column-title\">"+nameCase(this.table_name)+" "+nameCase(list.get(i).getColname())+"</span>\n" +
"                                        {{modelOneArray."+list.get(i).getColname()+"}}\n" +
"                                    </td>\n";
       } 
       //Add button edit and delete
       String end="</tr>\n" +
"                    </ng-template>\n" +
"                </p-table>\n" +
"            </fieldset>\n" +
"        </div>\n" +
"    </div>\n";
       //add the save button and save cancel and all at the end.
       
       return start+search+map.get("refresh_button")+start1+mid+mid1+mid2+mid3+map.get("edit_button")+map.get("delete_button")+end+map.get("save_cancel_button")+map.get("confirm_delete_dialog_box");
   }

   public String makeHtmlFile(String tableName) throws SQLException{
//       this.table_name=name;
//       List<ColumnObject> list=this.setTlist();
//       return this.inputhtml1(list)+this.inputhtml2(list)+this.inputhtml3(list);
   
            DbUtility dbUtility = new DbUtility();
            DBConnection dbConn = new DBConnection();
  
            try {
                dbUtility.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility.getColumns(tableName, dbConn.setConnection(null));
            } catch (SQLException ex) {
                    System.out.println("****Error");
            }        

            ArrayList<Object> columns = dbUtility.getColumns();
            return getHtmlTemplate(columns);
   }
   
    public String getHtmlTemplate(ArrayList<Object> columns) {
        HTMLTemplate htmlTemplate = new HTMLTemplate();
        ProcessSubstitution ps = new ProcessSubstitution();
       
        String template = "";

        template += htmlTemplate.getFormPart1();
        for(int i = 0; i < columns.size(); i++) {
            template += htmlTemplate.getFormFragment1(columns.get(i).getColumnName());
        }
        template += htmlTemplate.getFormPart2();
     
        template += getHtmlTemplateHelper(columns);
        template += htmlTemplate.getFormPart5();

        template += htmlTemplate.getListingTablePart1();
        template += getHtmlTemplateHelper(columns);
        template += htmlTemplate.getListingTablePart2();

        template += htmlTemplate.getSaveCancelButton();
        template += htmlTemplate.getDeleteDialog();

        template = ps.processTemplate(template);
        return template;
    }

    public String getHtmlTemplateHelper(ArrayList<Object> columns) {
                HTMLTemplate htmlTemplate = new HTMLTemplate();
        String template = "";

        template += htmlTemplate.getFormPart3();
        for(int i = 0; i < columns.size(); i++) {
            template += htmlTemplate.getFormFragment2(columns.get(i).getColumnName());
        }

        template += htmlTemplate.getFormPart4();
        for(int i = 0; i < columns.size(); i++) {
            template += htmlTemplate.getFormFragment3(columns.get(i).getColumnName());
        }

        return template;

    }
    
    public String makeS2HtmlFile(ArrayList<String> tableNames) throws SQLException{//make controller file 
            DbUtility dbUtility1 = new DbUtility();
            DbUtility dbUtility2 = new DbUtility();

            DBConnection dbConn = new DBConnection();
            String tableName1 = tableNames.get(0);
            String tableName2 = tableNames.get(1);
            try {
                dbUtility1.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility1.getColumns(tableName1, dbConn.setConnection(null));
                
                dbUtility2.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility2.getColumns(tableName2, dbConn.setConnection(null));
            } catch (SQLException ex) {
                    System.out.println("****Error");
            }

            ArrayList<Object> columns1 = dbUtility1.getColumns();
            ArrayList<String> PK1 = dbUtility1.getPKColumns();
            
            ArrayList<Object> columns2 = dbUtility2.getColumns();
            ArrayList<String> PK2 = dbUtility2.getPKColumns();
            return getS2HtmlTemplate(columns1, columns2, PK1);
    }
    
    public String getS2HtmlTemplate(ArrayList<Object> columns1, ArrayList<Object> columns2, ArrayList<String> PK1) {
        ProcessSubstitution ps = new ProcessSubstitution();
        S2HtmlTemplate s2html = new S2HtmlTemplate();
        
        String template = s2html.getFormPart1(columns1);
        template += s2html.getFormPart5(columns2, PK1);
        template += s2html.getFormPart2(columns1);
        template += s2html.getSaveDelete();
        template = ps.processTemplate(template);

        return template;
    }
   

   
}
