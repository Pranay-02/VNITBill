package com.vnit.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="screenlistdtl")
public class ScreenlistdtlMst implements Serializable {
    
    	private static final long serialVersionUID = 1L;
        
        @Id
        @Column(name = "screenfieldid")
	private Integer screenfieldid;
            
        @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screenlistid", nullable = false, updatable = false, insertable = true)
	@JsonBackReference
	private ScreenlistHdrMst screenlistHdrMst;
	
	@Column(name = "querycol")
	private String querycol;
	
	@Column(name = "screenid")
	private Integer screenid;
        
        public Integer getScreenfieldid() {
		return screenfieldid;
	}

	public void setScreenfieldid(Integer screenfieldid) {
		this.screenfieldid = screenfieldid;
	}
        
        public ScreenlistHdrMst getScreenlistHdrMst() {
		return screenlistHdrMst;
	}

	public void setScreenlistHdrMst(ScreenlistHdrMst screenlistHdrMst) {
		this.screenlistHdrMst = screenlistHdrMst;
	}
        
        public String getQuerycol() {
		return querycol;
	}

	public void setQuerycol(String querycol) {
            this.querycol=querycol;
        }
         
     
        
         public Integer getScreenid() {
		return screenid;
	}

	public void setScreenid(Integer screenid) {
		this.screenid = screenid;
	}
        
        @Override
	public String toString() {
		return "{screenfieldid=" + screenfieldid + ", querycol=" + querycol +  ", screenid=" + screenid + "}";
	}
}
