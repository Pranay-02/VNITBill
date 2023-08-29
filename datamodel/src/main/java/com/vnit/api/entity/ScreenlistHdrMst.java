package com.vnit.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="screenlisthdr")

public class ScreenlistHdrMst implements Serializable{
    
            private static final long serialVersionUID = 1L;
            
            @Id
            @GeneratedValue(strategy=GenerationType.IDENTITY)
            @Column(name = "screenlistid")
            private Integer screenlistid;

            @Column(name = "query")
            private String query;

            @Column(name = "jfunction")
            private String jfunction;

            @Column(name = "listname")
            private String listname;
            
            @OneToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH}, mappedBy = "screenlistHdrMst")
            @JsonManagedReference
            List<ScreenlistdtlMst> screenlistdtl = new ArrayList<>();
            
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
        
        public List<ScreenlistdtlMst> getScreenlistdtl() {
		return screenlistdtl;
	}


	public void setScreenlistdtl(List<ScreenlistdtlMst> screenlistdtl) {
		this.screenlistdtl = screenlistdtl;
	}
        
        @Override
	public String toString() {
		return "{screenlistid=" + screenlistid + ", query=" + query + ", jfunction=" + jfunction + ", listname=" + listname
				+ "}";
	}
}
