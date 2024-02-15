package com.vnit.api.entity;

import java.io.Serializable;import javax.persistence.Column;import javax.persistence.EmbeddedId;import javax.persistence.Entity;import javax.persistence.Table;

@Entity
@Table(name="deptdtl")
public class Deptdtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DeptdtlPK deptdtlPK;

	public DeptdtlPK getDeptdtlPK() {
		 return deptdtlPK;
	}

	public void setDeptdtlPK(DeptdtlPK deptdtlPK) {
		this.deptdtlPK = deptdtlPK;
	}

	@Column(name = "students_count")
	private int students_count;

	public int getstudents_count() {
		return students_count;
	}

	public void setstudents_count( int students_count) {
		this.students_count = students_count;
	}

	@Override
	public String toString() {
		return "{deptid=" + deptdtlPK.getdeptid() + ", courseid=" + deptdtlPK.getcourseid() + ", students_count=" + students_count + "}";
	}
}
