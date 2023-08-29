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
@Table(name = "screenjoincondition")
public class ScreenjoinconditionMst {

    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screenid")
    private Integer screenid;
    
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "screengroupid")
    private Integer screengroupid;
    
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "mastergroupcol")
    private String mastergroupcol;
    
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "groupcol")
    private String groupcol;
    
    @ApiModelProperty(required = false, value = "(Primary Key)")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screenjoinid")
    private Integer screenjoinid;
    
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
   
    public void setMastergroupcol(String mastergroupcol) {
        this.mastergroupcol = mastergroupcol;
    }

    public String getMastergroupcol() {
        return mastergroupcol;
    }
    

    public void setGroupcol(String groupcol) {
        this.groupcol = groupcol;
    }

    public String getGroupcol() {
        return groupcol;
    }
    

    public void setScreenjoinid(Integer screenjoinid) {
        this.screenjoinid = screenjoinid;
    }

    public Integer getScreenjoinid() {
        return screenjoinid;
    }
}
