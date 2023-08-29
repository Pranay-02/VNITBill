package com.vnit.api.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
@Entity
@Table(name="users")
public class UsersMst{
@ApiModelProperty(required = false, value = "(Primary Key)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
private Integer id;
public void setId(Integer id){
this.id= id;
}
public Integer getId(){
return id;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="name")
private String name;
public void setName(String name){
this.name= name;
}
public String getName(){
return name;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="password")
private String password;
public void setPassword(String password){
this.password= password;
}
public String getPassword(){
return password;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="email")
private String email;
public void setEmail(String email){
this.email= email;
}
public String getEmail(){
return email;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="role")
private String role;
public void setRole(String role){
this.role= role;
}
public String getRole(){
return role;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="status")
private String status;
public void setStatus(String status){
this.status= status;
}
public String getStatus(){
return status;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="mobile")
private long mobile;
public void setMobile(long mobile){
this.mobile= mobile;
}
public long getMobile(){
return mobile;
}
@ApiModelProperty(required = true, value = "(size = 500) (required)")
@Column(name="reg_date")
private java.sql.Timestamp reg_date;//Here the java datatype is manunally added 
public void setReg_date(java.sql.Timestamp reg_date){
this.reg_date= reg_date;
}
public java.sql.Timestamp getReg_date(){
return reg_date;
}
}