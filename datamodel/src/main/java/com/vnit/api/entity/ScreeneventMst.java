package com.vnit.api.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
@Entity
@Table(name="screenevent")
public class ScreeneventMst{
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="screenid")
private Integer screenid;
public void setScreenid(Integer screenid){
this.screenid= screenid;
}
public Integer getScreenid(){
return screenid;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="screencomponent")
private String screencomponent;
public void setScreencomponent(String screencomponent){
this.screencomponent= screencomponent;
}
public String getScreencomponent(){
return screencomponent;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="screencomponentid")
private Integer screencomponentid;
public void setScreencomponentid(Integer screencomponentid){
this.screencomponentid= screencomponentid;
}
public Integer getScreencomponentid(){
return screencomponentid;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="eventtype")
private Integer eventtype;
public void setEventtype(Integer eventtype){
this.eventtype= eventtype;
}
public Integer getEventtype(){
return eventtype;
}
@ApiModelProperty(required = false, value = "(Primary Key)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="screeneventid")
private Integer screeneventid;
public void setScreeneventid(Integer screeneventid){
this.screeneventid= screeneventid;
}
public Integer getScreeneventid(){
return screeneventid;
}
}