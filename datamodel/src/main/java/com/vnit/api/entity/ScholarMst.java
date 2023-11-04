package com.vnit.api.entity;

import java.io.Serializable;import java.math.BigDecimal;import java.util.ArrayList;import java.util.Date;import java.util.List;import javax.persistence.CascadeType;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.FetchType;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.OneToMany;import javax.persistence.Table;import javax.persistence.Temporal;import javax.persistence.TemporalType;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="scholar")
public class ScholarMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "scholarMst")
	@JsonManagedReference
	List<ProjectMst> project = new ArrayList<>();

	public List<ProjectMst> getProject() {
		return project;
	}

	public void setProject(List<ProjectMst> project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "{scholarid=" + scholarid + ", name=" + name + ", age=" + age + "}";
	}
}
