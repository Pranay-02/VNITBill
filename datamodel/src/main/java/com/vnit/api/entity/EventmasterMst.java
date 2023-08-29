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
@Table(name="eventmaster")
public class EventmasterMst{
@ApiModelProperty(required = false, value = "(Primary Key)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="eventid")
private Integer eventid;
public void setEventid(Integer eventid){
this.eventid= eventid;
}
public Integer getEventid(){
return eventid;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="eventname")
private String eventname;
public void setEventname(String eventname){
this.eventname= eventname;
}
public String getEventname(){
return eventname;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="eventpurpose")
private String eventpurpose;
public void setEventpurpose(String eventpurpose){
this.eventpurpose= eventpurpose;
}
public String getEventpurpose(){
return eventpurpose;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="screenevent")
private boolean screenevent;
public void setScreenevent(boolean screenevent){
this.screenevent= screenevent;
}
public boolean getScreenevent(){
return screenevent;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="screengroupevent")
private boolean screengroupevent;
public void setScreengroupevent(boolean screengroupevent){
this.screengroupevent= screengroupevent;
}
public boolean getScreengroupevent(){
return screengroupevent;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="screenrecordevent")
private boolean screenrecordevent;
public void setScreenrecordevent(boolean screenrecordevent){
this.screenrecordevent= screenrecordevent;
}
public boolean getScreenrecordevent(){
return screenrecordevent;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="screenfieldevent")
private boolean screenfieldevent;
public void setScreenfieldevent(boolean screenfieldevent){
this.screenfieldevent= screenfieldevent;
}
public boolean getScreenfieldevent(){
return screenfieldevent;
}
}