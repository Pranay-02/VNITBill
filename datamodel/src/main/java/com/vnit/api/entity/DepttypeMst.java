package com.vnit.api.entity;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="depttype")
public class DepttypeMst {

@ApiModelProperty(required = false, value = "(10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "depttypeid")
private int depttypeid;

public int getdepttypeid() {
	return depttypeid;
}

public void setdepttypeid ( int depttypeid) {
	this.depttypeid = depttypeid;
}


@ApiModelProperty(required = true, value = "(10)")
@Column(name = "depttp")
private int depttp;

public int getdepttp() {
	return depttp;
}

public void setdepttp ( int depttp) {
	this.depttp = depttp;
}


@ApiModelProperty(required = true, value = "(10)")
@Column(name = "courseid")
private int courseid;

public int getcourseid() {
	return courseid;
}

public void setcourseid ( int courseid) {
	this.courseid = courseid;
}

}