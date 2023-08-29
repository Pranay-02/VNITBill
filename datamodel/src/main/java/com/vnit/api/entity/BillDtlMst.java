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
@Table(name="billdtl")
public class BillDtlMst implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "itcode")
	private Integer itcode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "billno", nullable = false, updatable = false, insertable = true)
	@JsonBackReference
	private BillHdrMst billHdrMst;
	
	@Column(name = "itrate")
	private BigDecimal itrate;
	
	@Column(name = "qty")
	private Integer qty;
	
	@Column(name = "value")
	private BigDecimal value;

	public Integer getItcode() {
		return itcode;
	}

	public void setItcode(Integer itcode) {
		this.itcode = itcode;
	}

	public BillHdrMst getBillHdrMst() {
		return billHdrMst;
	}

	public void setBillHdrMst(BillHdrMst billHdrMst) {
		this.billHdrMst = billHdrMst;
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
		return "{itcode=" + itcode + ", itrate=" + itrate + ", qty=" + qty
				+ ", value=" + value + "}";
	}

}
