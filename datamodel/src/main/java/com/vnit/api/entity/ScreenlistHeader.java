package com.vnit.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="screenlisthdr")
public class ScreenlistHeader implements Serializable {
    private static final long serialVersionUID = 1L;
    
        @Id
	@Column(name = "screenlistid")
	private Integer screenlistid;
	
	@Column(name = "query")
	private String query;
	
	@Column(name = "jfunction")
	private String jfunction;
	
	@Column(name = "listname")
	private String listname;
    
        public Integer getScreenlistid() {
		return screenlistid;
	}


	public void setScreenlistid(Integer screenlistid) {
		this.screenlistid = screenlistid;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getJfunction() {
		return jfunction;
	}


	public void setJfunction(String jfunction) {
		this.jfunction = jfunction;
	}


	public String getListname() {
		return listname;
	}


	public void setListname(String listname) {
		this.listname = listname;
	}
        
        
        @Override
	public String toString() {
		return "{screenlistid=" + screenlistid + ", query=" + query + ", jfunction=" + jfunction + ", listname=" + listname
				+ "}";
	}
}
