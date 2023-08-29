package com.vnit.api.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="item")
public class ItemMst {
	
	@ApiModelProperty(required = false, value = "(Primary Key)")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "itcode")
	private Integer itcode;
	
	@ApiModelProperty(required = true, value = "(size = 100) (required)")
	@Column(name = "itname")
	private String itname;
	
	@ApiModelProperty(required = true, value = "(size = 5,2) (required)")
	@Column(name = "itrate")
	private BigDecimal itrate;

	public Integer getItcode() {
		return itcode;
	}

	public void setItcode(Integer itcode) {
		this.itcode = itcode;
	}

	public String getItname() {
		return itname;
	}

	public void setItname(String itname) {
		this.itname = itname;
	}

	public BigDecimal getItrate() {
		return itrate;
	}

	public void setItrate(BigDecimal itrate) {
		this.itrate = itrate;
	}

}
