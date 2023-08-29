
package com.vnit.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="screenlistdtl")

public class ScreenlistDetail implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ScreenlistPK screenlistPK;
    
    @Column(name = "querycol")
    private String querycol;
	
    @Column(name = "screenid")
    private Integer screenid;
    
    public ScreenlistPK getScreenlistPK() {
		return screenlistPK;
	}

    public void setScreenlistPK(ScreenlistPK screenlistPK) {
		this.screenlistPK = screenlistPK;
	}
    
    public String getQuerycol() {
		return querycol;
	}

    public void setQuerycol(String querycol) {
		this.querycol = querycol;
	}

    public Integer getScreenid() {
		return screenid;
	}

    public void setScreenid(Integer screenid) {
		this.screenid = screenid;
	}
    
    @Override
	public String toString() {
		return "{screenlistid=" + screenlistPK.getScreenlistid() + ", screenfieldid=" + screenlistPK.getScreenfieldid() + ", querycol=" + querycol 
				+ ", screenid=" + screenid + "}";
	}
}
