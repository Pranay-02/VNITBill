package com.vnit.api.entity;

import java.io.Serializable;import java.math.BigDecimal;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.FetchType;import javax.persistence.Id;import javax.persistence.JoinColumn;import javax.persistence.ManyToOne;import javax.persistence.Table;import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="deptdtl")
public class DeptdtlMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "courseid")
	private int courseid;

	public int getcourseid() {
		return courseid;
	}

	public void setcourseid(int courseid) {
		this.courseid = courseid;
	}


		@Column(name = "students_count")
	private int students_count;

	public int getstudents_count() {
		return students_count;
	}

	public void setstudents_count(int students_count) {
		this.students_count = students_count;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptid", nullable = false, updatable = false, insertable = true)
	@JsonBackReference
	private DepartmentMst departmentMst;

	public DepartmentMst getDepartmentMst() {
		return departmentMst;
	}

	public void setDepartmentMst (DepartmentMst departmentMst) {
		this.departmentMst = departmentMst;
	}

	@Override
	public String toString() {
		return "{courseid=" + courseid + ", students_count=" + students_count + "}";
	}
}
