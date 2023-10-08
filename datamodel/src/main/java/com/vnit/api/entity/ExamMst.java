package com.vnit.api.entity;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="exam")
public class ExamMst {

@ApiModelProperty(required = false, value = "(10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "examid")
private int examid;

public int getexamid() {
	return examid;
}

public void setexamid ( int examid) {
	this.examid = examid;
}


@ApiModelProperty(required = true, value = "(255)")
@Column(name = "subject")
private String subject;

public String getsubject() {
	return subject;
}

public void setsubject ( String subject) {
	this.subject = subject;
}

}