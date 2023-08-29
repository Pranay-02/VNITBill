package com.vnit.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "screenfieldmaster")
public class ScreenfieldmasterMst {

    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screenid")
    private Integer screenid;

    public void setScreenid(Integer screenid) {
        this.screenid = screenid;
    }

    public Integer getScreenid() {
        return screenid;
    }
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screengroupid")
    private Integer screengroupid;

    public void setScreengroupid(Integer screengroupid) {
        this.screengroupid = screengroupid;
    }

    public Integer getScreengroupid() {
        return screengroupid;
    }
    @ApiModelProperty(required = false, value = "(Primary Key)")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screenfieldid")
    private Integer screenfieldid;

    public void setScreenfieldid(Integer screenfieldid) {
        this.screenfieldid = screenfieldid;
    }

    public Integer getScreenfieldid() {
        return screenfieldid;
    }
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "field_name")
    private String field_name;

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_name() {
        return field_name;
    }
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "label")
    private String label;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "fieldtype")
    private Integer fieldtype;

    public void setFieldtype(Integer fieldtype) {
        this.fieldtype = fieldtype;
    }

    public Integer getFieldtype() {
        return fieldtype;
    }
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "tablecolumn")
    private String tablecolumn;

    public void setTablecolumn(String tablecolumn) {
        this.tablecolumn = tablecolumn;
    }

    public String getTablecolumn() {
        return tablecolumn;
    }
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "required")
    private boolean required;

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean getRequired() {
        return required;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "databasefuncname")
    private String databasefuncname;

    public void setDatabasefuncname(String databasefuncname) {
        this.databasefuncname = databasefuncname;
    }

    public String getDatabasefuncname() {
        return databasefuncname;
    }
    @ApiModelProperty(required = true, value = "(size = 500)")
    @Column(name = "jfunctionname")
    private String jfunctionname;

    public void setJfunctionname(String jfunctionname) {
        this.jfunctionname = jfunctionname;
    }

    public String getJfunctionname() {
        return jfunctionname;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "rangelow")
    private Integer rangelow;

    public void setRangelow(Integer rangelow) {
        this.rangelow = rangelow;
    }

    public Integer getRangelow() {
        return rangelow;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "rangehigh")
    private Integer rangehigh;

    public void setRangehigh(Integer rangehigh) {
        this.rangehigh = rangehigh;
    }

    public Integer getRangehigh() {
        return rangehigh;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "dateformat")
  //  @JsonFormat(pattern = "yyyy-MM-dd")
   // @Temporal(TemporalType.DATE)
    private String dateformat;

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public String getDateformat() {
        return dateformat;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "visible")
    private boolean visible;

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible() {
        return visible;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "rowno")
    private Integer rowno;

    public void setRowno(Integer rowno) {
        this.rowno = rowno;
    }

    public Integer getRowno() {
        return rowno;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "colno")
    private Integer colno;

    public void setColno(Integer colno) {
        this.colno = colno;
    }

    public Integer getColno() {
        return colno;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "inputtype")
    private String inputtype;

    public void setInputtype(String inputtype) {
        this.inputtype = inputtype;
    }

    public String getInputtype() {
        return inputtype;
    }
    @ApiModelProperty(required = true, value = "(size = 500) ")
    @Column(name = "datatype")
    private String datatype;

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getDatatype() {
        return datatype;
    }
    @ApiModelProperty(required = true, value = "(size = 500)")
    @Column(name = "defaultvalue")
    private String defaultvalue;

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }
}
