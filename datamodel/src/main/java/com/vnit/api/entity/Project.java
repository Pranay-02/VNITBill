package com.vnit.api.entity;

import java.io.Serializable;import javax.persistence.Column;import javax.persistence.EmbeddedId;import javax.persistence.Entity;import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProjectPK projectPK;

	public ProjectPK getProjectPK() {
		 return projectPK;
	}

	public void setProjectPK(ProjectPK projectPK) {
		this.projectPK = projectPK;
	}

	@Column(name = "title")
	private String title;

	public String gettitle() {
		return title;
	}

	public void settitle( String title) {
		this.title = title;
	}

	@Column(name = "duration")
	private String duration;

	public String getduration() {
		return duration;
	}

	public void setduration( String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "{projectid=" + projectPK.getprojectid() + ", scholarid=" + projectPK.getscholarid() + ", title=" + title + ", duration=" + duration + "}";
	}
}
