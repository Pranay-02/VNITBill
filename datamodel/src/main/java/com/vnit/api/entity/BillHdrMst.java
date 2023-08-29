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
@Table(name="billhdr")
public class BillHdrMst implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH}, mappedBy = "billHdrMst")
	@JsonManagedReference
	List<BillDtlMst> billdtl = new ArrayList<>();

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


	public List<BillDtlMst> getBilldtl() {
		return billdtl;
	}


	public void setBilldtl(List<BillDtlMst> billdtl) {
		this.billdtl = billdtl;
	}


	@Override
	public String toString() {
		return "{billno=" + billno + ", billtp=" + billtp + ", ccode=" + ccode + ", billamt=" + billamt
				+ ", billdt=" + billdt + "}";
	}

}
