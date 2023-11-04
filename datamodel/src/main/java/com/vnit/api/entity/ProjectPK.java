package com.vnit.api.entity;

import java.io.Serializable;import javax.persistence.Basic;import javax.persistence.Column;import javax.persistence.Embeddable;

@Embeddable
public class ProjectPK implements Serializable {

	private static final long serialVersionUID = 1L;
	public ProjectPK() {
		super();
	}

	@Basic(optional = false)
	@Column(name = "projectid")
	private int projectid;

	@Basic(optional = false)
	@Column(name = "scholarid")
	private int scholarid;

	public ProjectPK(int projectid, int scholarid) {
		super();
		this.projectid = projectid;
		this.scholarid = scholarid;
	}

	public int getprojectid() {
		return projectid;
	}

	public void setprojectid( int projectid) {
		this.projectid = projectid;
	}

	public int getscholarid() {
		return scholarid;
	}

	public void setscholarid( int scholarid) {
		this.scholarid = scholarid;
	}

}
