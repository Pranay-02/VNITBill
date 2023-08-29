package com.vnit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="customer")
public class CustomerMst {
	
	@ApiModelProperty(required = false, value = "(Primary Key)")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ccode")
	private Integer ccode;
	
	@ApiModelProperty(required = true, value = "(size = 100) (required)")
	@Column(name = "cname")
	private String cname;

	public Integer getCcode() {
		return ccode;
	}

	public void setCcode(Integer ccode) {
		this.ccode = ccode;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
