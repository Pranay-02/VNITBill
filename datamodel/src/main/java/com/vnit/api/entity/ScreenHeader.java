package com.vnit.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="screen")
public class ScreenHeader implements Serializable{
       private static final long serialVersionUID = 1L;
       
       @ApiModelProperty(required = false, value = "(Primary Key)")
       @Id
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
        public void setScreentype(Integer screentype){
            this.screentype= screentype;
        }
        public Integer getScreentype(){
            return screentype;
        }
        public void setScreendate(Date screendate){
            this.screendate= screendate;
        }
        public Date getScreendate(){
            return screendate;
        }

        @Override
	public String toString() {
		return "{screenid=" + screenid + ", screenpurpose=" + screenpurpose + ", screenname=" + screenname + ", screentype=" + screentype
				+ ", screendate=" + screendate + "}";
	}

}
