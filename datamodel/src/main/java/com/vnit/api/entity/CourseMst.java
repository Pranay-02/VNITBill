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


@ApiModelProperty(required = true, value = "(50)")
@Column(name = "name")
private String name;

public String getname() {
	return name;
}

public void setname ( String name) {
	this.name = name;
}


@ApiModelProperty(required = true, value = "(10)")
@Column(name = "duration")
private int duration;

public int getduration() {
	return duration;
}

public void setduration ( int duration) {
	this.duration = duration;
}

}