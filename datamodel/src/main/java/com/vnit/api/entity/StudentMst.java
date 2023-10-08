package com.vnit.api.entity;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="student")
public class StudentMst {

@ApiModelProperty(required = false, value = "(10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "studentid")
private int studentid;

public int getstudentid() {
	return studentid;
}

public void setstudentid ( int studentid) {
	this.studentid = studentid;
}


@ApiModelProperty(required = true, value = "(255)")
@Column(name = "name")
private String name;

public String getname() {
	return name;
}

public void setname ( String name) {
	this.name = name;
}


@ApiModelProperty(required = true, value = "(255)")
@Column(name = "city")
private String city;

public String getcity() {
	return city;
}

public void setcity ( String city) {
	this.city = city;
}


@ApiModelProperty(required = true, value = "(255)")
@Column(name = "dob")
private String dob;

public String getdob() {
	return dob;
}

public void setDOB ( String dob) {
	this.dob = dob;
}

}