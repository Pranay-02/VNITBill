package com.vnit.api.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillDtlPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
	@Column(name = "itcode")
	private Integer itcode;
	
	@Basic(optional = false)
	@Column(name = "billno")
	private Integer billno;

	public BillDtlPK() {
		super();
	}

	public BillDtlPK(Integer itcode, Integer billno) {
		super();
		this.itcode = itcode;
		this.billno = billno;
	}

	public Integer getItcode() {
		return itcode;
	}

	public void setItcode(Integer itcode) {
		this.itcode = itcode;
	}

	public Integer getBillno() {
		return billno;
	}

	public void setBillno(Integer billno) {
		this.billno = billno;
	}

}
