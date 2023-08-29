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
@Table(name = "screenmappingcondition")
public class ScreenmappingconditionMst {

    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screenid")
    private Integer screenid;
    
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screengroupid")
    private Integer screengroupid;
    
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screenfieldid")
    private Integer screenfieldid;
    
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "querycolumn")
    private String querycolumn;
    
    @ApiModelProperty(required = false, value = "(Primary Key)")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screenmappingid")
    private Integer screenmappingid;
    
    
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
    

    public void setScreenfieldid(Integer screenfieldid) {
        this.screenfieldid = screenfieldid;
    }

    public Integer getScreenfieldid() {
        return screenfieldid;
    }
    

    public void setQuerycolumn(String querycolumn) {
        this.querycolumn = querycolumn;
    }

    public String getQuerycolumn() {
        return querycolumn;
    }
    

    public void setScreenmappingid(Integer screenmappingid) {
        this.screenmappingid = screenmappingid;
    }

    public Integer getScreenmappingid() {
        return screenmappingid;
    }
}
