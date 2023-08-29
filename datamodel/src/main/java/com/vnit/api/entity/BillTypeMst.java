package com.vnit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="billtype")
public class BillTypeMst {
	
	@ApiModelProperty(required = false, value = "(Primary Key)")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "billid")
	private Integer billid;
	
	@ApiModelProperty(required = true, value = "(size = 1) (required)")
	@Column(name = "billtp")
	private Integer billtp;
	
	@ApiModelProperty(required = true, value = "(size = 10) (required)")
	@Column(name = "itcode")
	private Integer itcode;

	public Integer getBillid() {
		return billid;
	}

	public void setBillid(Integer billid) {
		this.billid = billid;
	}

	public Integer getBilltp() {
		return billtp;
	}

	public void setBilltp(Integer billtp) {
		this.billtp = billtp;
	}

	public Integer getItcode() {
		return itcode;
	}

	public void setItcode(Integer itcode) {
		this.itcode = itcode;
	}

}
