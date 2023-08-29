package com.vnit.api.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScreenGrpPK implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "screengroupid")
    private Integer screengroupid;
	
    @Basic(optional = false)
    @Column(name = "screenid")
    private Integer screenid;
    
    public ScreenGrpPK() {
		super();
    }
    
    public ScreenGrpPK(Integer screengroupid,Integer screenid){
        super();
        this.screengroupid=screengroupid;
        this.screenid=screenid;
    }
    
    public void setScreenid(Integer screenid){
       this.screenid= screenid;
    }
    public Integer getScreenid(){
        return screenid;
    }
    public void setScreengroupid(Integer screengroupid){  
        this.screengroupid= screengroupid;
    }

    public Integer getScreengroupid(){   
        return screengroupid;
    }
    
}
