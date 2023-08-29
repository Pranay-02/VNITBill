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
@Table(name="billhdr")
public class BillHeader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "billno")
	private Integer billno;
	
	@Column(name = "billtp")
	private Integer billtp;
	
	@Column(name = "ccode")
	private Integer ccode;
	
	@Column(name = "billamt")
	private BigDecimal billamt;
	
	@Column(name = "billdt")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date billdt;

	public Integer getBillno() {
		return billno;
	}


	public void setBillno(Integer billno) {
		this.billno = billno;
	}


	public Integer getBilltp() {
		return billtp;
	}


	public void setBilltp(Integer billtp) {
		this.billtp = billtp;
	}


	public Integer getCcode() {
		return ccode;
	}


	public void setCcode(Integer ccode) {
		this.ccode = ccode;
	}


	public BigDecimal getBillamt() {
		return billamt;
	}


	public void setBillamt(BigDecimal billamt) {
		this.billamt = billamt;
	}


	public Date getBilldt() {
		return billdt;
	}


	public void setBilldt(Date billdt) {
		this.billdt = billdt;
	}
	
	@Override
	public String toString() {
		return "{billno=" + billno + ", billtp=" + billtp + ", ccode=" + ccode + ", billamt=" + billamt
				+ ", billdt=" + billdt + "}";
	}

}
