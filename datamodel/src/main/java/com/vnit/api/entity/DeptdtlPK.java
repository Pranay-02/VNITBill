package com.vnit.api.entity;

import java.io.Serializable;import javax.persistence.Basic;import javax.persistence.Column;import javax.persistence.Embeddable;

@Embeddable
public class DeptdtlPK implements Serializable {

	private static final long serialVersionUID = 1L;
	public DeptdtlPK() {
		super();
	}

	@Basic(optional = false)
	@Column(name = "deptid")
	private int deptid;

	@Basic(optional = false)
	@Column(name = "courseid")
	private int courseid;

	public DeptdtlPK(int deptid, int courseid) {
		super();
		this.deptid = deptid;
		this.courseid = courseid;
	}

	public int getdeptid() {
		return deptid;
	}

	public void setdeptid( int deptid) {
		this.deptid = deptid;
	}

	public int getcourseid() {
		return courseid;
	}

	public void setcourseid( int courseid) {
		this.courseid = courseid;
	}

}
