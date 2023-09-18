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
public class HTMLTemplate {
public String getFormPart1() {
        String temp = "";

        temp += "<div class=\"card card-default\">\n\t<br>\n" + 
        "\t<form name=\"form\" #f=\"ngForm\">\n\n" + 
        "\t\t<span *ngIf=\"searchFromFilter !== true\">\n" + 
        "\t\t\t<div class=\"row\">\n" + 
        "\t\t\t\t<div class=\"col-md-6 offset-md-3\">\n" + 
        "\t\t\t\t\t<fieldset class=\"scheduler-border col-md-12\">\n" + 
        "\t\t\t\t\t\t<legend class=\"scheduler-border\">" +  "^$00$01$m:constantsMap:cap_table_name$^:\n" + 
            "\t\t\t\t\t\t\t<p id=\"demo\"></p>\n" + 
        "\t\t\t\t\t\t</legend>\n" + 
        "\t\t\t\t\t\t<div class=\"col-md-12\">\n" + 
            "\t\t\t\t\t\t\t<div class=\"row\">\n";

        return temp;
    }

    public String getTabs() {
        return "\t\t\t\t\t\t\t";
    }

    public String getFormFragment1(String columnName) {
        String temp = "";

        temp += getTabs() + "\t<div class=\"col-md-4\">\n" + 
        getTabs() +"\t\t<label>\n" + 
        getTabs() +"\t\t\t ^$00$01$m:constantsMap:cap_table_name$^ ^00$01$m2:fld:" + columnName + ":column_name$^\n" +     
        getTabs() +"\t\t\t<span style=\"color: red;\">*</span>\n" + 
        getTabs() + "\t\t</label>\n" + 
        getTabs() +"\t\t<div class=\"form-group\">\n" + 
        getTabs() + "\t\t\t<input type=\"number\" class=\"form-control input-sm\" " +  
        "id=\"^00$01$m2:fld:" + columnName + ":column_name$^\" name=\"^00$01$m2:fld:" + columnName + ":column_name$^\"\n" +  
        getTabs() + "\t\t\t[(ngModel)]=\"model.^00$01$m2:fld:" + columnName + ":column_name$^\" #^00$01$m2:fld:" + columnName + ":column_name$^=\"ngModel\" " + 
                    "placeholder=\"^$00$01$m:constantsMap:cap_table_name$^ ^00$01$m2:fld:" + columnName + ":column_name$^\"\n" +
        getTabs() + "\t\t\tmaxlength=\"^00$01$m2:fld:" + columnName + ":size$^\" pTooltip=\"{{model.^00$01$m2:fld:" + columnName + ":column_name$^}}\"\n" + 
        getTabs() + "\t\t\ttooltipPosition=\"bottom\" ^00$02$m2:fld:" + columnName + ":primary_key$c:[disabled]=\"true\"$^>\n" +  
        getTabs() +"\t\t</div>\n" + 
        getTabs() +"\t</div>\n";

        return temp;
    }

    public String getFormPart2() {
        String temp = "";

        temp += getTabs() + "\t<div class=\"col-md-4\">\n" + 
        getTabs() + "\t\t<label style=\"margin-top:18px;\"></label>\n" + 
        getTabs() + "\t\t<div class=\"form-group\" style=\"text-align: center;\">\n" +
        getTabs() + "\t\t\t<button pButton pRipple type=\"button\"\n" + 
        getTabs() + "\t\t\t\tlabel=\"Add\" (click)=\"addModelOneArray()\"\n" + 
        getTabs() + "\t\t\t\tclass=\"save_button p-button-success\"></button>\n" + 
        getTabs() + "\t\t</div>\n" + 
        getTabs() + "\t</div>\n" + 
        getTabs() + "</div>\n";

        temp += "\t\t\t\t\t\t</div>\n" + 
        "\t\t\t\t\t</fieldset>\n" + 
        "\t\t\t\t</div>\n" + 
        "\t\t\t</div>\n\n";

        temp += "\t\t\t<div class=\"row\" *ngIf=\"modelOneArray.length !== 0\">\n" + 
        "\t\t\t\t<div class=\"col-md-6 offset-md-3\">\n" + 
        "\t\t\t\t\t<fieldset class=\"scheduler-border col-md-12\">\n" + 
        "\t\t\t\t\t\t<legend class=\"scheduler-border\">^$00$01$m:constantsMap:cap_table_name$^ Details:</legend>\n" + 
        "\t\t\t\t\t\t<p-table #dt [value]=\"modelOneArray\" scrollHeight=\"300px\" [scrollable]=\"true\"\n" +
        getTabs() + "[style]=\"{width:'auto'}\"\n" + 
        getTabs() + "styleClass=\"p-datatable-responsive-demo p-datatable-striped custom-class\">\n"; 

        return temp; 
    }

    public String getFormPart3() {
        String temp = "";
        temp += getTabs() + "<ng-template pTemplate=\"header\" let-columns>\n" + 
        getTabs() + "\t<tr style=\"font-size: 10px;\">\n" + 
        getTabs() + "\t\t<th pResizableColumn>Sr.no</th>\n";

        return temp;
    }

    public String getFormFragment2(String columnName) {
        String temp = "";
        temp += getTabs() + "\t\t<th pResizableColumn>^$00$01$m:constantsMap:cap_table_name$^ ^00$01$m2:fld:" + columnName + ":column_name$^</th>\n";
        return temp;
    }

    public String getFormPart4() {
        String temp = "";
   
        temp += getTabs() + "\t\t<th>Edit</th>\n" + 
        getTabs() + "\t\t<th>Delete</th>\n" + 
        getTabs() + "\t</tr>\n" + 
        getTabs() + "</ng-template>\n" + 
        getTabs() + "<ng-template pTemplate=\"body\" let-modelOneArray let-i=\"rowIndex\">\n" + 
        getTabs() + "\t<tr *ngIf=\"modelOneArray.flag !== 'delete'\" style=\"font-size: 10px; font-weight: bold;\">\n" + 
        getTabs() + "\t\t<td>\n" + 
        getTabs() + "\t\t\t<span style=\"margin-left: 10px;\" class=\"p-column-title\">Sr.no</span>\n" + 
        getTabs() + "\t\t\t{{i+1}}\n" + 
        getTabs() + "\t\t</td>\n";

        return temp;
    }

    public String getFormFragment3(String columnName) {
        String temp = "";
        
        temp += getTabs() + "\t\t<td>\n" + 
        getTabs() + "\t\t\t<span style=\"margin-left: 10px;\" class=\"p-column-title\">" + 
        "^$00$01$m:constantsMap:cap_table_name$^ ^00$01$m2:fld:" + columnName + ":column_name$^</span>\n" + 
        getTabs() + "\t\t\t{{modelOneArray.^00$01$m2:fld:" + columnName + ":column_name$^}}\n" + 
        getTabs() + "\t\t</td>\n";


        return temp;
    }

    public String getFormPart5() {
        String temp = "";

        temp += getTabs() + "\t\t<td>\n" + 
        getTabs() + "\t\t\t<button pButton pRipple icon=\"pi pi-user-edit\"\n" + 
        getTabs() + "\t\t\t\tclass=\"p-button-rounded p-button-success\"\n" +
        getTabs() + "\t\t\t\t(click)=\"viewRowData(modelOneArray,i);\" pTooltip=\"Edit\"\n" + 
        getTabs() + "\t\t\t\ttooltipPosition=\"bottom\">\n" +
        getTabs() + "\t\t\t</button>\n" +
        getTabs() + "\t\t</td>\n";

        temp += getTabs() + "\t\t<td>\n" + 
        getTabs() + "\t\t\t<button pButton pRipple icon=\"pi pi-trash\"\n" + 
        getTabs() + "\t\t\t\tclass=\"p-button-rounded p-button-warning p-mr-2\" data-toggle=\"modal\"\n" +
        getTabs() + "\t\t\t\t(click)=\"deleteRowData(modelOneArray,i)\" pTooltip=\"Delete\"\n" +
        getTabs() + "\t\t\t\ttooltipPosition=\"bottom\">\n" +
        getTabs() + "\t\t\t</button>\n" + 
        getTabs() + "\t\t</td>\n";

        temp +=  getTabs() + "\t</tr>\n" + 
        getTabs() + "</ng-template>\n" + 
        "\t\t\t\t\t\t</p-table>\n" + 
        "\t\t\t\t\t</fieldset>\n" + 
        "\t\t\t\t</div>\n" + 
        "\t\t\t</div>\n" + 
        "\t\t</span>\n" + 
        "\t</form>\n\n\n";
        
        return temp;
    }

    public String getListingTablePart1() {
        String temp = "";
        
        temp += "\t<!-- LISTING TABLE -->\n" + 
        "\t<div class=\"row\" *ngIf=\"searchFromFilter === true\">\n" + 
        "\t\t<div class=\"col-md-6 offset-md-3\">\n" + 
        "\t\t\t<fieldset class=\"scheduler-border col-md-12\">\n" + 
        "\t\t\t\t<legend class=\"scheduler-border\">^$00$01$m:constantsMap:cap_table_name$^ Details:</legend>\n" + 
        "\t\t\t\t<p-table #dt [value]=\"modelList\" scrollHeight=\"300px\" [scrollable]=\"true\"\n" + 
        "\t\t\t\t\t[style]=\"{width:'auto'}\"\n" + 
        "\t\t\t\t\tstyleClass=\"p-datatable-responsive-demo p-datatable-striped custom-class\">\n" + 
        "\t\t\t\t\t<ng-template pTemplate=\"caption\">\n" + 
        "\t\t\t\t\t\t<div class=\"col-md-12\">\n" + 
        getTabs() + "<div class=\"row\">\n" + 
        getTabs() + "\t<div class=\"col-sm-8\">\n" + 
        getTabs() + "\t\t<tr>\n" + 
        getTabs() + "\t\t\t<th pResizableColumn>\n" + 
        getTabs() + "\t\t\t\t<span class=\"p-float-label\" style=\"margin-right: 10px;\">\n" + 
        getTabs() + "\t\t\t\t\t<div class=\"form-group\">\n" + 
        getTabs() + "\t\t\t\t\t\t<!-- <i class=\"pi pi-search\"></i> -->\n" + 
        getTabs() + "\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" pInputText id=\"search\"\n" + 
        getTabs() + getTabs() + "name=\"search\" [(ngModel)]=\"filters\"\n" + 
        getTabs() + getTabs() + "(input)=\"searchByFilter()\">\n" + 
        getTabs() + "\t\t\t\t\t\t<label style=\"margin-left: 20px;\" for=\"search\"><span\n" + 
        getTabs() + getTabs() + "style=\"font-size: 10px;\">^$00$01$m:constantsMap:cap_table_name$^ Name</span></label>\n" + 
        getTabs() + "\t\t\t\t\t</div>\n" + 
        getTabs() + "\t\t\t\t</span>\n" + 
        getTabs() + "\t\t\t</th>\n" + 
        getTabs() + "\t\t\t<th style=\"margin-top: 2px; margin-left: 12px; position: absolute;\" pResizableColumn>\n" + 
        getTabs() + "\t\t\t\t<button pButton pRipple icon=\"pi pi-refresh\"\n" + 
        getTabs() + "\t\t\t\t\tclass=\"p-button-rounded p-button-primary\" (click)=\"filters = ''; searchByFilter()\"\n" + 
        getTabs() + "\t\t\t\t\tpTooltip=\"Refresh\"\n" + 
        getTabs() + "\t\t\t\t\ttooltipPosition=\"bottom\">\n" + 
        getTabs() + "\t\t\t\t</button>\n" + 
        getTabs() + "\t\t\t</th>\n" + 
        getTabs() + "\t\t</tr>\n" + 
        getTabs() + "\t</div>\n" + 
        getTabs() + "</div>\n" + 
        "\t\t\t\t\t\t</div>\n" + 
        "\t\t\t\t\t</ng-template>\n";

        return temp;
    }

    public String getListingTablePart2() {
        String temp = "";

        temp += getTabs() + "\t\t<td>\n" + 
        getTabs() + "\t\t\t<button pButton pRipple icon=\"pi pi-user-edit\"\n" + 
        getTabs() + "\t\t\t\tclass=\"p-button-rounded p-button-success\"\n" +
        getTabs() + "\t\t\t\t(click)=\"viewRowData(modelOneArray,i); searchFromFilter = false\" pTooltip=\"Edit\"\n" + 
        getTabs() + "\t\t\t\ttooltipPosition=\"bottom\">\n" +
        getTabs() + "\t\t\t</button>\n" +
        getTabs() + "\t\t</td>\n";

        temp += getTabs() + "\t\t<td>\n" + 
        getTabs() + "\t\t\t<button pButton pRipple icon=\"pi pi-trash\"\n" + 
        getTabs() + "\t\t\t\tclass=\"p-button-rounded p-button-warning p-mr-2\" data-toggle=\"modal\"\n" +
        getTabs() + "\t\t\t\tdata-target=\"#confirmDeleteDialog\" (click)=\"onDelete(modelOneArray,i)\" pTooltip=\"Delete\"\n" +
        getTabs() + "\t\t\t\ttooltipPosition=\"bottom\">\n" +
        getTabs() + "\t\t\t</button>\n" + 
        getTabs() + "\t\t</td>\n";

        temp +=  getTabs() + "\t</tr>\n" + 
        getTabs() + "</ng-template>\n" + 
        "\t\t\t\t</p-table>\n" + 
        "\t\t\t</fieldset>\n" + 
        "\t\t</div>\n" + 
        "\t</div>\n";

        return temp;
    }
    
    
    public String getSaveCancelButton() {
        String temp = "";

        temp += "\n\n\t<!-- SAVE CANCEL BUTTON -->\n" + 
        "\t<div class=\"container-fluid  bg-white\" style=\"position: fixed;left: -1px;bottom: 32px;\">\n" +
            "\t\t<div class=\"row\" style=\"background-color: #e4f2ef;\">\n" +
            "\t\t\t<div class=\"col-lg-12 mb-2 mt-2\" align=\"right\" style=\"background-color: #e4f2ef;\">\n" +
            "\t\t\t\t<button style=\"margin-left: 5px;\" pButton pRipple type=\"button\" label=\"Save\" (click)=\"onSave()\"\n" + 
                "\t\t\t\t\tclass=\"cancel_button p-button-success p-ripple p-button p-component\" *ngIf=\"searchFromFilter !== true\"></button>\n" +
            "\t\t\t\t<button style=\"margin-left: 5px;\" pButton pRipple type=\"button\" [label]=\"searchFromFilter === true ? 'Cancel':'Show All'\"  (click)=\"onCancel()\"\n" +
                "\t\t\t\t\tclass=\"cancel_button p-button-danger p-ripple p-button p-component\"></button>\n" +
            "\t\t\t</div>\n" + 
            "\t\t</div>\n" +
        "\t</div>\n\n" + 
        "</div>\n\n";

        return temp;
    }
    
    public String getDeleteDialog() {
        String temp = "";
        
        temp += "<!-- Confirm Delete Dialog    data-toggle=\"modal\" data-target=\"#confirmDeleteDialog\"-->\n" + 
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
        "</div>\n\n";

        return temp;
    }    
}
