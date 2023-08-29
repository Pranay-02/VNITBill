package com.vnit.api.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScreenlistPK implements Serializable{
        private static final long serialVersionUID = 1L;
        
        @Basic(optional = false)
	@Column(name = "screenlistid")
	private Integer screenlistid;
	
	@Basic(optional = false)
	@Column(name = "screenfieldid")
	private Integer screenfieldid;
        
        public ScreenlistPK() {
		super();
	}
        public ScreenlistPK(Integer screenlistid, Integer screenfieldid) {
		super();
		this.screenlistid = screenlistid;
		this.screenfieldid = screenfieldid;
	}
        
        public Integer getScreenlistid() {
		return screenlistid;
	}

	public void setScreenlistid(Integer screenlistid) {
		this.screenlistid = screenlistid;
	}

	public Integer getScreenfieldid() {
		return screenfieldid;
	}

	public void setScreenfieldid(Integer screenfieldid) {
		this.screenfieldid = screenfieldid;
	}
        
}
