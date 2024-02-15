package com.vnit.api.entity;

import java.io.Serializable;import java.math.BigDecimal;import java.util.ArrayList;import java.util.Date;import java.util.List;import javax.persistence.CascadeType;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.FetchType;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.OneToMany;import javax.persistence.Table;import javax.persistence.Temporal;import javax.persistence.TemporalType;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="department")
public class DepartmentMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "deptid")
	private int deptid;

	public int getdeptid() {
		return deptid;
	}

	public void setdeptid(int deptid) {
		this.deptid = deptid;
	}


			@Column(name = "name")
	private String name;

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}


			@Column(name = "depttp")
	private int depttp;

	public int getdepttp() {
		return depttp;
	}

	public void setdepttp(int depttp) {
		this.depttp = depttp;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "departmentMst")
	@JsonManagedReference
	List<DeptdtlMst> deptdtl = new ArrayList<>();

	public List<DeptdtlMst> getDeptdtl() {
		return deptdtl;
	}

	public void setDeptdtl(List<DeptdtlMst> deptdtl) {
		this.deptdtl = deptdtl;
	}

	@Override
	public String toString() {
		return "{deptid=" + deptid + ", name=" + name + ", depttp=" + depttp + "}";
	}
}
