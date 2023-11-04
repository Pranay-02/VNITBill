package com.vnit.api.entity;

import java.io.Serializable;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.FetchType;import javax.persistence.Id;import javax.persistence.JoinColumn;import javax.persistence.Table;import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.ManyToOne;

@Entity
@Table(name="project")
public class ProjectMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "projectid")
	private int projectid;

	public int getprojectid() {
		return projectid;
	}

	public void setprojectid(int projectid) {
		this.projectid = projectid;
	}


		@Column(name = "title")
	private String title;

	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}


		@Column(name = "duration")
	private String duration;

	public String getduration() {
		return duration;
	}

	public void setduration(String duration) {
		this.duration = duration;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "scholarid", nullable = false, updatable = false, insertable = true)
	@JsonBackReference
	private ScholarMst scholarMst;

	public ScholarMst getScholarMst() {
		return scholarMst;
	}

	public void setScholarMst (ScholarMst scholarMst) {
		this.scholarMst = scholarMst;
	}

	@Override
	public String toString() {
		return "{projectid=" + projectid + ", title=" + title + ", duration=" + duration + "}";
	}
}
