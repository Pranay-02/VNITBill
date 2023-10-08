package com.vnit.api.entity;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="department")
public class DepartmentMst {

@ApiModelProperty(required = false, value = "(10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "deptid")
private int deptid;

public int getdeptid() {
	return deptid;
}

public void setdeptid ( int deptid) {
	this.deptid = deptid;
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

}