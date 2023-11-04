package com.vnit.api.entity;

import java.io.Serializable;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.Table;import javax.persistence.Temporal;import javax.persistence.TemporalType;import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="scholar")
public class Scholar implements Serializable {
	private static final long serialVersionUID = 1L;

@Id
	@Column(name = "scholarid")
	private int scholarid;

	public int getscholarid() {
		return scholarid;
	}

	public void setscholarid(int scholarid) {
		this.scholarid = scholarid;
	}


	@Column(name = "name")
	private String name;

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}


	@Column(name = "age")
	private int age;

	public int getage() {
		return age;
	}

	public void setage(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "{scholarid=" + scholarid + ", name=" + name + ", age=" + age + "}";
	}
}
