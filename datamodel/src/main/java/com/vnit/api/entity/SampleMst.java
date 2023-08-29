package com.vnit.api.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="sample")
public class SampleMst {
        @ApiModelProperty(required = false, value = "(Primary Key)")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name = "scode")
	private Integer scode;
	
	@ApiModelProperty(required = true, value = "(size = 100) (required)")
	@Column(name = "sname")
	private String sname;

	public Integer getScode() {
		return scode;
	}

	public void setScode(Integer scode) {
		this.scode = scode;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
        
    
}
