package com.vnit.api.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="screen")
public class ScreenMst implements Serializable{
private static final long serialVersionUID = 1L;

@ApiModelProperty(required = false, value = "(Primary Key)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="screenid")
private Integer screenid;


@Column(name="screenpurpose")
private String screenpurpose;

@Column(name="screenname")
private String screenname;

@Column(name="screentype")
private Integer screentype;

@Column(name="screendate")
@JsonFormat(pattern = "yyyy-MM-dd")
@Temporal(TemporalType.DATE)
private Date screendate;


@OneToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH}, mappedBy = "screenMst")
	@JsonManagedReference
	List<ScreengroupMst> screengroup = new ArrayList<>();


        
public void setScreenid(Integer screenid){
this.screenid= screenid;
}
public Integer getScreenid(){
return screenid;
}

public void setScreenpurpose(String screenpurpose){
this.screenpurpose= screenpurpose;
}
public String getScreenpurpose(){
return screenpurpose;
}

public void setScreenname(String screenname){
this.screenname= screenname;
}
public String getScreenname(){
return screenname;
}

public void setScreendate(Date screendate){
this.screendate= screendate;
}
public Date getScreendate(){
return screendate;
}

public void setScreentype(Integer screentype){
this.screentype= screentype;
}
public Integer getScreentype(){
return screentype;
}

public List<ScreengroupMst> getScreengroup() {
		return screengroup;
}

public void setScreengroup(List<ScreengroupMst> screengroup) {
		this.screengroup = screengroup;
	}

	@Override
	public String toString() {
		return "{screenid=" + screenid + ", screenpurpose=" + screenpurpose + ", screenname=" + screenname + ", screentype=" + screentype
				+ ", screendate=" + screendate + "}";
	}
}
