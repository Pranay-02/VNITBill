package com.vnit.api.entity;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="screengroup")
public class ScreengroupMst implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "screengroupid")
    private Integer screengroupid;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screenid", nullable = false, updatable = false, insertable = true)
    @JsonBackReference
    private ScreenMst screenMst;
    
@Column(name="gpurpose")
private String gpurpose;

@Column(name="db")
private boolean db;

@Column(name="basetable")
private String basetable;

@Column(name="detailtable")
private boolean detailtable;

@Column(name="mastergroupname")
private String mastergroupname;

@Column(name="mapping")
private boolean mapping;

@Column(name="mappingtable")
private String mappingtable;

@Column(name="recordgroupcount")
private Integer recordgroupcount;

@Column(name="basiclayout")
private Integer basiclayout;

public void setScreengroupid(Integer screengroupid){  
this.screengroupid= screengroupid;
}

public Integer getScreengroupid(){   
return screengroupid;
}

public ScreenMst getScreenMst() {
	return screenMst;
}

public void setScreenMst(ScreenMst screenMst) {
	this.screenMst = screenMst;
}

public void setGpurpose(String gpurpose){  
this.gpurpose= gpurpose;
}
public String getGpurpose(){   
return gpurpose;
}

public void setDb(boolean db){   
this.db= db;
}

public boolean getDb(){ 
return db;
}


public void setBasetable(String basetable){
this.basetable= basetable;
}
public String getBasetable(){
return basetable;
}

public void setDetailtable(boolean detailtable){
this.detailtable= detailtable;
}
public boolean getDetailtable(){
return detailtable;
}

public void setMastergroupname(String mastergroupname){
this.mastergroupname= mastergroupname;
}
public String getMastergroupname(){
return mastergroupname;
}

public void setMapping(boolean mapping){
this.mapping= mapping;
}
public boolean getMapping(){
return mapping;
}

public void setMappingtable(String mappingtable){
this.mappingtable= mappingtable;
}
public String getMappingtable(){
return mappingtable;
}

public void setRecordgroupcount(Integer recordgroupcount){
this.recordgroupcount= recordgroupcount;
}
public Integer getRecordgroupcount(){
return recordgroupcount;
}

public void setBasiclayout(Integer basiclayout){
this.basiclayout= basiclayout;
}
public Integer getBasiclayout(){
return basiclayout;
}
    
@Override
	public String toString() {
		return "{screengroupid=" + screengroupid + ", gpurpose=" + gpurpose 
				+ ", db=" + db + ", basetable=" + basetable +", detailtable="+detailtable +", mastergroupname="
                        +mastergroupname+", mapping="+mapping+", mappingtable="+mappingtable+", recordgroupcount="+recordgroupcount+", basiclayout="+basiclayout+"}";
	}

    
}
