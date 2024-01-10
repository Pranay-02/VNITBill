package com.vnit.api.entity;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="people")
public class PeopleMst {

@ApiModelProperty(required = false, value = "(10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "peopleid")
private int peopleid;

public int getpeopleid() {
	return peopleid;
}

public void setpeopleid ( int peopleid) {
	this.peopleid = peopleid;
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

}