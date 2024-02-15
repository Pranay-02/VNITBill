package com.vnit.api.entity;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="course")
public class CourseMst {

@ApiModelProperty(required = false, value = "(10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "courseid")
private int courseid;

public int getcourseid() {
	return courseid;
}

public void setcourseid ( int courseid) {
	this.courseid = courseid;
}


@ApiModelProperty(required = true, value = "(255)")
@Column(name = "coursename")
private String coursename;

public String getcoursename() {
	return coursename;
}

public void setcoursename ( String coursename) {
	this.coursename = coursename;
}


@ApiModelProperty(required = true, value = "(10)")
@Column(name = "credits")
private int credits;

public int getcredits() {
	return credits;
}

public void setcredits ( int credits) {
	this.credits = credits;
}

}