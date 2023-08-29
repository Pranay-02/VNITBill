package com.vnit.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="billdtl")
public class BillDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BillDtlPK billDtlPK;
	
	@Column(name = "itrate")
	private BigDecimal itrate;
	
	@Column(name = "qty")
	private Integer qty;
	
	@Column(name = "value")
	private BigDecimal value;

	public BillDtlPK getBillDtlPK() {
		return billDtlPK;
	}

	public void setBillDtlPK(BillDtlPK billDtlPK) {
		this.billDtlPK = billDtlPK;
	}

	public BigDecimal getItrate() {
		return itrate;
	}

	public void setItrate(BigDecimal itrate) {
		this.itrate = itrate;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "{itcode=" + billDtlPK.getItcode() + ", billno=" + billDtlPK.getBillno() + ", itrate=" + itrate 
				+ ", qty=" + qty + ", value=" + value + "}";
	}

}
