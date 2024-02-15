package com.vnit.api.entity;

import java.io.Serializable;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.Table;import javax.persistence.Temporal;import javax.persistence.TemporalType;import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="department")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

@Id
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

	@Override
	public String toString() {
                        return "{deptid=" + deptid + ", name=" + name + ", depttp=" + depttp + "}";	
                  }
}
