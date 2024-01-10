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
    @Column(name = "sourcegroupid")
    private Integer sourcegroupid;
    
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "destgroupid")
    private Integer destgroupid;

    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "masterfieldid")
    private Integer masterfieldid;
    
    @ApiModelProperty(required = true, value = "(size = 500) (required)")
    @Column(name = "detailfieldid")
    private Integer detailfieldid;
    
    @ApiModelProperty(required = true, value = "(size = 255)")
    @Column(name = "masterquerycolumn")
    private String masterquerycolumn;
    
    @ApiModelProperty(required = true, value = "(size = 255)")
    @Column(name = "detailquerycolumn")
    private String detailquerycolumn;

    @ApiModelProperty(required = true, value = "(size = 255) (required)")
    @Column(name = "query")
    private String query;
            
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
    

    public void setSourcegroupid(Integer sourcegroupid) {
        this.sourcegroupid = sourcegroupid;
    }

    public Integer getSourcegroupid() {
        return sourcegroupid;
    }
    
    public void setDestgroupid(Integer destgroupid) {
        this.destgroupid = destgroupid;
    }

    public Integer getDestgroupid() {
        return destgroupid;
    }
    

    public void setMasterfieldid(Integer masterfieldid) {
        this.masterfieldid = masterfieldid;
    }

    public Integer getMasterfieldid() {
        return masterfieldid;
    }
    
    public void setDetailfieldid(Integer detailfieldid) {
        this.detailfieldid = detailfieldid;
    }

    public Integer getDetailfieldid() {
        return detailfieldid;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
    
    public void setMasterQueryColumn(String masterquerycolumn) {
        this.masterquerycolumn = masterquerycolumn;
    }
    
    public String getMasterQueryColumn() {
        return masterquerycolumn;
    }
    
    public void setDetailQueryColumn(String detailquerycolumn) {
        this.detailquerycolumn = detailquerycolumn;
    }
    
    public String getDetailQueryColumn() {
        return detailquerycolumn;
    }
    
    public void setScreenmappingid(Integer screenmappingid) {
        this.screenmappingid = screenmappingid;
    }

    public Integer getScreenmappingid() {
        return screenmappingid;
    }
}
