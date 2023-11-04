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
public class S2HtmlTemplate {
    public String getFormPart1(ArrayList<Object> columns1) {
    String temp = "";
    temp+="<div class=\"card card-default\">\n"+
            "\t<br>\n"+
            "\t<form name=\"form\" #f=\"ngForm\">\n\n"+
            "\t\t<span *ngIf=\"searchFromFilter !== true\">\n"+
            "\t\t\t<div class=\"row\">\n"+
                "\t\t\t\t<div class=\"col-md-10 offset-md-1\">\n"+
                    "\t\t\t\t\t<fieldset class=\"scheduler-border col-md-12\">\n"+
                        "\t\t\t\t\t\t<legend class=\"scheduler-border\">\n"+
                            "\t\t\t\t\t\t\t<span *ngIf=\"FORM_TYPE === '^$00$01$m:constantsMap:table_name1$^'\">^$00$01$m:constantsMap:cap_table_name1$^ Entry:</span>\n"+
                            "\t\t\t\t\t\t<span></span>\n"+
                        "\t\t\t\t\t</legend>\n"+
                        "\t\t\t\t\t<div class=\"col-md-12\">\n"+
                        "\t\t\t\t\t\t<div class=\"row\">\n";
                          
                        for(int i = 0; i < columns1.size(); i++) {
                            if(columns1.get(i).getColumnPrimaryKey()) continue;
                            temp += getFormPart8(columns1.get(i).getColumnName());
                        }
                     
                        temp += "\t\t\t\t\t</div>\n"+
                    "\t\t\t\t\t</div>\n"+
                    "\t\t\t\t\t</fieldset>\n"+
                "\t\t\t\t</div>\n"+
            "\t\t\t</div>\n\n\n";

            return temp;
    }

    public String getFormPart8(String columnName) {
        String temp = "";
        temp += "\t\t\t\t\t\t\t<div class=\"col-md-3\">\n"+
            "\t\t\t\t\t\t\t\t\t<label>\n"+
               "\t\t\t\t\t\t\t\t\t\t" + columnName + "\n"+
                "\t\t\t\t\t\t\t\t\t\t<span style=\"color: red;\">*</span>\n"+
            "\t\t\t\t\t\t\t\t\t</label>\n"+
            "\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\n"+
                "\t\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-sm\" id=\"" + columnName +"\" name=\""+ columnName + "\"\n"+
                    "\t\t\t\t\t\t\t\t\t\t\t[(ngModel)]=\"model." + columnName +"\" #" +  columnName +"=\"ngModel\" placeholder=\"" +  columnName+ "\"\n"+
                    "\t\t\t\t\t\t\t\t\t\t\tmaxlength=\"20\" pTooltip=\"{{model."+ columnName + "}}\" tooltipPosition=\"bottom\">\n"+
            "\t\t\t\t\t\t\t\t\t</div>\n"+
        "\t\t\t\t\t\t\t</div>\n\n";

        return temp;
    }

    public String getFormPart5(ArrayList<Object> columns2, ArrayList<String> PK1) {
        String temp = "";

        temp += "\t\t\t<div class=\"row\">\n"+
                "\t\t\t\t<div class=\"col-md-10 offset-md-1\">\n"+
                    "\t\t\t\t\t<fieldset class=\"scheduler-border col-md-12\">\n"+
                        "\t\t\t\t\t\t<legend class=\"scheduler-border\">" + " ^$00$01$m:constantsMap:cap_table_name2$^"+ ":</legend>\n"+
                        "\t\t\t\t\t\t\t<p-table #dt [value]=\"modelTwoArray\" scrollHeight=\"200px\" [scrollable]=\"true\"\n"+
                            "\t\t\t\t\t\t\t\t[style]=\"{width:'auto'}\"\n"+
                            "\t\t\t\t\t\t\t\tstyleClass=\"p-datatable-responsive-demo p-datatable-striped custom-class\">\n"+
                            "\t\t\t\t\t\t\t\t<ng-template pTemplate=\"caption\" *ngIf='FORM_TYPE === \"^$00$01$m:constantsMap:table_name1$^\"'>\n"+
                                "\t\t\t\t\t\t\t\t\t<div class=\"col-md-12\">\n"+
                                    "\t\t\t\t\t\t\t\t\t\t<div class=\"row\">\n"+
                                        "\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-2 offset-md-10\">\n"+
                                            "\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n"+
                                                
                                                "\t\t\t\t\t\t\t\t\t\t\t\t\t<th pResizableColumn>\n"+
                                                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"p-float-label\" style=\"margin-right: 10px;\">\n"+
                                                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\" style=\"text-align: right;\">\n"+
                                                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<button pButton pRipple type=\"button\" label=\"Add\" (click)=\"addRow()\"\n"+
                                                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"save_button p-button-success\"></button>\n"+
                                                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n"+
                                                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\n"+
                                                "\t\t\t\t\t\t\t\t\t\t\t\t\t</th>\n"+
                                            "\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n"+
                                        "\t\t\t\t\t\t\t\t\t\t\t</div>\n"+
                                    "\t\t\t\t\t\t\t\t\t\t</div>\n"+
                                "\t\t\t\t\t\t\t\t\t</div>\n"+
                            "\t\t\t\t\t\t\t\t</ng-template>\n"+
                            "\t\t\t\t\t\t\t\t<ng-template pTemplate=\"header\" let-columns>\n"+
                                "\t\t\t\t\t\t\t\t\t<tr style=\"font-size: 10px;\">\n"+
                                    "\t\t\t\t\t\t\t\t\t\t<th pResizableColumn style=\"width: 60px;\">Sr.no</th>\n";

                                    for(int i = 0; i < columns2.size(); i++) {
                                        if(columns2.get(i).getColumnPrimaryKey() && PK1.contains(columns2.get(i).getColumnName())) continue;
                                        temp += getFormPart6(columns2.get(i).getColumnName());
                                    }
                             
                                temp +=  "\t\t\t\t\t\t\t\t\t\t<th style=\"text-align: center;\" *ngIf='FORM_TYPE === \"^$00$01$m:constantsMap:table_name1$^\"'>Delete</th>\n"+
                                "\t\t\t\t\t\t\t\t\t</tr>\n"+
                            "\t\t\t\t\t\t\t\t</ng-template>\n"+
                            "\t\t\t\t\t\t\t\t<ng-template pTemplate=\"body\" let-modelTwoArray let-i=\"rowIndex\">\n"+
                                "\t\t\t\t\t\t\t\t\t<tr *ngIf=\"modelTwoArray.flag !== 'delete'\" style=\"font-size: 10px; font-weight: bold;\">\n"+
                                    "\t\t\t\t\t\t\t\t\t\t<td style=\"width: 60px;\">\n"+
                                        "\t\t\t\t\t\t\t\t\t\t\t<span style=\"margin-left: 10px;\" class=\"p-column-title\">Sr.no</span>\n"+
                                        "\t\t\t\t\t\t\t\t\t\t\t{{i+1}}\n"+
                                    "\t\t\t\t\t\t\t\t\t\t</td>\n";

                                    for(int i = 0; i < columns2.size(); i++) {
                                        if(columns2.get(i).getColumnPrimaryKey() && PK1.contains(columns2.get(i).getColumnName())) continue;
                                        temp += getFormPart7(columns2.get(i).getColumnName());
                                    }


                                    temp += "\t\t\t\t\t\t\t\t\t<td style=\"text-align: center;\" *ngIf='FORM_TYPE === \"^$00$01$m:constantsMap:table_name1$^\"'>\n"+
                                        "\t\t\t\t\t\t\t\t\t\t<button pButton pRipple icon=\"pi pi-trash\"\n"+
                                            "\t\t\t\t\t\t\t\t\t\t\tclass=\"p-button-rounded p-button-warning p-mr-2\" data-toggle=\"modal\"\n"+
                                            "\t\t\t\t\t\t\t\t\t\t\t(click)=\"deleteRowData(modelTwoArray,i)\" pTooltip=\"Delete\"\n"+
                                            "\t\t\t\t\t\t\t\t\t\ttooltipPosition=\"bottom\">\n"+
                                        "\t\t\t\t\t\t\t\t\t\t</button>\n"+
                                    "\t\t\t\t\t\t\t\t\t</td>\n"+
                                "\t\t\t\t\t\t\t\t</tr>\n"+
                            "\t\t\t\t\t\t\t\t</ng-template>\n"+
                        "\t\t\t\t\t\t\t</p-table>\n"+
                    "\t\t\t\t\t</fieldset>\n"+
                "\t\t\t\t</div>\n"+
            "\t\t\t</div>\n"+
        "\t\t</span>\n"+
    "\t</form>\n\n";

    return temp;
    }

    public String getFormPart6(String columnName) {
        String temp = "";
        temp +=  "\t\t\t\t\t\t\t\t\t\t<th pResizableColumn>" + columnName + "<span style=\"color: red;\">*</span></th>\n";
        return temp;                     
    }

    public String getFormPart7(String columnName) {
        String temp = "";
        temp +=    "\t\t\t\t\t\t\t\t\t<td>\n"+
                    "\t\t\t\t\t\t\t\t\t\t<span style=\"margin-left: 10px;\" class=\"p-column-title\">" + columnName + "</span>\n"+    
                    "\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\n"+
                        "\t\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-sm\" id=\"" + columnName + "{{i}}\" name=\"" + columnName + "{{i}}\"\n"+
                            "\t\t\t\t\t\t\t\t\t\t\t[(ngModel)]=\"modelTwoArray." + columnName + "\" #" + columnName +"=\"ngModel\" placeholder=\" " + columnName +"\"\n"+
                            "\t\t\t\t\t\t\t\t\t\t\tmaxlength=\"20\" pTooltip=\"{{modelTwoArray." + columnName + "}}\" tooltipPosition=\"bottom\"\n"+
                            "\t\t\t\t\t\t\t\t\t\t\tstyle=\"text-align: right;\">\n"+
                    "\t\t\t\t\t\t\t\t\t\t</div>\n"+
                "\t\t\t\t\t\t\t\t\t</td>\n";

        return temp;
    }

    public String getFormPart2(ArrayList<Object> columns1) {
        String temp = "";
        temp += "\t<!-- LISTING TABLE -->\n" + 
        "\t<div class=\"row\" *ngIf=\"searchFromFilter === true\">\n"+
            "\t\t<div class=\"col-md-8 offset-md-2\">\n"+
                "\t\t\t<fieldset class=\"scheduler-border col-md-12\">\n"+
                    "\t\t\t\t<legend class=\"scheduler-border\" *ngIf='FORM_TYPE ===\"^$00$01$m:constantsMap:table_name1$^\"'>^$00$01$m:constantsMap:cap_table_name1$^ Entry Details:</legend>\n"+
                    "\t\t\t\t<p-table #dt [value]=\"modelList\" scrollHeight=\"300px\" [scrollable]=\"true\" [style]=\"{width:'auto'}\"\n"+
                        "\t\t\t\t\tstyleClass=\"p-datatable-responsive-demo p-datatable-striped custom-class\">\n"+
                        "\t\t\t\t\t<ng-template pTemplate=\"caption\">\n"+
                            "\t\t\t\t\t\t<div class=\"col-md-12\">\n"+
                                "\t\t\t\t\t\t\t<div class=\"row\">\n"+
                                    "\t\t\t\t\t\t\t\t<div class=\"col-sm-8\">\n"+
                                        "\t\t\t\t\t\t\t\t<tr>\n"+
                                            "\t\t\t\t\t\t\t\t\t<th pResizableColumn>\n"+
                                                "\t\t\t\t\t\t\t\t\t\t<span class=\"p-float-label\" style=\"margin-right: 10px;\">\n"+
                                                    "\t\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\n"+
                                                        "\t\t\t\t\t\t\t\t\t\t\t\t<!-- <i class=\"pi pi-search\"></i> -->\n"+
                                                        "\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" pInputText id=\"search\"\n"+
                                                        "\t\t\t\t\t\t\t\t\t\t\t\t name=\"search\" [(ngModel)]=\"filters\" (input)=\"searchByFilter()\">\n"+
                                                        "\t\t\t\t\t\t\t\t\t\t\t\t<label style=\"margin-left: 20px;\" for=\"search\"><span\n"+
                                                                "\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"font-size: 10px;\">^$00$01$m:constantsMap:cap_table_name1$^ No</span></label>\n"+
                                                    "\t\t\t\t\t\t\t\t\t\t\t</div>\n"+
                                                "\t\t\t\t\t\t\t\t\t\t</span>\n"+
                                            "\t\t\t\t\t\t\t\t\t</th>\n"+
                                            "\t\t\t\t\t\t\t\t\t<th style=\"margin-top: 2px; margin-left: 12px; position: absolute;\"\n"+
                                                "\t\t\t\t\t\t\t\t\t pResizableColumn>\n"+
                                                "\t\t\t\t\t\t\t\t\t\t<button pButton pRipple icon=\"pi pi-refresh\"\n"+
                                                    "\t\t\t\t\t\t\t\t\t class=\"p-button-rounded p-button-primary\"\n"+
                                                    "\t\t\t\t\t\t\t\t\t(click)=\"filters = ''; searchByFilter()\" pTooltip=\"Refresh\"\n"+
                                                    "\t\t\t\t\t\t\t\t\ttooltipPosition=\"bottom\">\n"+
                                                "\t\t\t\t\t\t\t\t\t\t</button>\n"+
                                            "\t\t\t\t\t\t\t\t\t</th>\n"+
                                        "\t\t\t\t\t\t\t\t</tr>\n"+
                                    "\t\t\t\t\t\t\t\t</div>\n"+
                                "\t\t\t\t\t\t\t</div>\n"+
                            "\t\t\t\t\t\t</div>\n"+
                            "\t\t\t\t\t</ng-template>\n"+
                            "\t\t\t\t\t<ng-template pTemplate=\"header\" let-columns>\n"+
                                "\t\t\t\t\t\t<tr style=\"font-size: 10px;\">\n"+
                                "\t\t\t\t\t\t\t<th pResizableColumn>Sr.no</th>\n";

                                for(int i = 0; i < columns1.size(); i++) {
                                    temp += getFormPart3(columns1.get(i).getColumnName());
                                }
                                
                                temp += "\t\t\t\t\t\t\t<th>Edit</th>\n"+
                                "\t\t\t\t\t\t<th>Delete</th>\n"+
                            "\t\t\t\t\t\t</tr>\n"+
                            "\t\t\t\t\t</ng-template>\n"+
                            "\t\t\t\t\t<ng-template pTemplate=\"body\" let-modelTwoArray let-i=\"rowIndex\">\n"+
                            "\t\t\t\t\t\t<tr *ngIf=\"modelTwoArray.flag !== 'delete'\" style=\"font-size: 10px; font-weight: bold;\">\n"+
                                "\t\t\t\t\t\t\t<td>\n"+
                                    "\t\t\t\t\t\t\t\t<span style=\"margin-left: 10px;\" class=\"p-column-title\">Sr.no</span>\n"+
                                    "\t\t\t\t\t\t\t\t{{i+1}}\n"+
                                "\t\t\t\t\t\t\t</td>\n";

                                for(int i = 0; i < columns1.size(); i++) {
                                    temp += getFormPart4(columns1.get(i).getColumnName());
                                }

                                temp += "\t\t\t\t\t\t\t<td>\n"+
                                    "\t\t\t\t\t\t\t\t<button pButton pRipple icon=\"pi pi-user-edit\" class=\"p-button-rounded p-button-success\"\n"+
                                        "\t\t\t\t\t\t\t\t(click)=\"editRowData(modelTwoArray,i); searchFromFilter = false\" pTooltip=\"Edit\"\n"+
                                        "\t\t\t\t\t\t\t\ttooltipPosition=\"bottom\">\n"+
                                    "\t\t\t\t\t\t\t\t</button>\n"+
                                "\t\t\t\t\t\t\t</td>\n"+
                                "\t\t\t\t\t\t\t<td>\n"+
                                    "\t\t\t\t\t\t\t\t<button pButton pRipple icon=\"pi pi-trash\"\n"+
                                        "\t\t\t\t\t\t\t\t\tclass=\"p-button-rounded p-button-warning p-mr-2\" data-toggle=\"modal\"\n"+
                                        "\t\t\t\t\t\t\t\t\tdata-toggle=\"modal\" data-target=\"#confirmDeleteDialog\"\n"+
                                        "\t\t\t\t\t\t\t\t\t(click)=\"onDelete(modelTwoArray,i)\" pTooltip=\"Delete\" tooltipPosition=\"bottom\">\n"+
                                    "\t\t\t\t\t\t\t\t</button>\n"+
                                "\t\t\t\t\t\t\t</td>\n"+
                            "\t\t\t\t\t\t</tr>\n"+
                        "\t\t\t\t\t</ng-template>\n"+
                    "\t\t\t\t</p-table>\n"+
                "\t\t\t</fieldset>\n"+
            "\t\t</div>\n"+
        "\t</div>\n\n";
        return temp;
    }

    public String getFormPart3(String columnName) {
        String temp = "";
        temp += "\t\t\t\t\t\t\t<th pResizableColumn>" + columnName + "</th>\n";
        return temp;        
    }

    public String getFormPart4(String columnName) {
        String temp = "";
        temp += "\t\t\t\t\t\t\t<td>\n"+
                "\t\t\t\t\t\t\t\t<span style=\"margin-left: 10px;\" class=\"p-column-title\">" + columnName + "</span>\n"+
                "\t\t\t\t\t\t\t\t{{modelTwoArray." + columnName + "}}\n"+
                "\t\t\t\t\t\t\t</td>\n";
        return temp;
    }

    public String getSaveDelete() {
        String temp = "";
        temp = "\t<!-- SAVE CANCEL BUTTON -->\n"+
            "\t<div class=\"container-fluid  bg-white\" style=\"position: fixed;left: -1px;bottom: 32px;\">\n"+
                "\t\t<div class=\"row\" style=\"background-color: #e4f2ef;\">\n"+
                    "\t\t\t<div class=\"col-lg-12 mb-2 mt-2\" align=\"right\" style=\"background-color: #e4f2ef;\">\n"+
                        "\t\t\t\t<button style=\"margin-left: 5px;\" pButton pRipple type=\"button\" label=\"Save\" (click)=\"onSave()\"\n"+
                            "\t\t\t\tclass=\"cancel_button p-button-success p-ripple p-button p-component\"\n"+
                            "\t\t\t\t*ngIf=\"searchFromFilter !== true\"></button>\n\n"+

                        "\t\t\t\t<button style=\"margin-left: 5px;\" pButton pRipple type=\"button\"\n"+
                            "\t\t\t\t[label]=\"searchFromFilter === true ? 'Cancel':'Show All'\" (click)=\"onCancel()\"\n"+
                            "\t\t\t\tclass=\"cancel_button p-button-danger p-ripple p-button p-component\"></button>\n"+
                    "\t\t\t</div>\n"+
                "\t\t</div>\n"+
            "\t</div>\n"+
        "</div>\n" + 
        "\n\n" + 
        "<!-- Confirm Delete Dialog data-toggle=\"modal\" data-target=\"#confirmDeleteDialog\"-->\n" + 
        "<div class=\"modal fade\" id=\"confirmDeleteDialog\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"basicModal\" aria-hidden=\"true\">\n" + 
            "\t<div class=\"modal-dialog modal-sm\">\n" + 
                "\t\t<div class=\"modal-content\">\n" + 
                    "\t\t\t<div class=\"modal-body\">\n" + 
                        "\t\t\t\t<h6>Do you really want to delete!</h6>\n" + 
                    "\t\t\t</div>\n" + 
                    "\t\t\t<div class=\"modal-footer\">\n" + 
                        "\t\t\t\t<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\"\n" + 
                            "\t\t\t\t\t(click)=\"confirmDelete();\">Confirm</button>\n" + 
                        "\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Cancel</button>\n" + 
                    "\t\t\t</div>\n" + 
                "\t\t</div>\n" + 
            "\t</div>\n" + 
        "</div>\n";

        return temp;
    }

}
