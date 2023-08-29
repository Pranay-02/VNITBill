package com.vnit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@Entity
@Table(name = "screenmappingquery")
public class ScreenmappingqueryMst {

    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screenid")
    private Integer screenid;
     @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screengroupid")
    private Integer screengroupid;
     
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "querytext")
    private String querytext;
    
    @ApiModelProperty(required = false, value = "(Primary Key)")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screenmappingqueryid")
    private Integer screenmappingqueryid;
     
    public void setScreenid(Integer screenid) {
        this.screenid = screenid;
    }

    public Integer getScreenid() {
        return screenid;
    }
   

    public void setScreengroupid(Integer screengroupid) {
        this.screengroupid = screengroupid;
    }

    public Integer getScreengroupid() {
        return screengroupid;
    }
   

    public void setQuerytext(String querytext) {
        this.querytext = querytext;
    }

    public String getQuerytext() {
        return querytext;
    }
   

    public void setScreenmappingqueryid(Integer screenmappingqueryid) {
        this.screenmappingqueryid = screenmappingqueryid;
    }

    public Integer getScreenmappingqueryid() {
        return screenmappingqueryid;
    }
}
