package com.vnit.api.file.col_object;
import com.vnit.api.file.dbConnection.DBConnection;
import static com.vnit.api.file.dbConnection.DBConnection.successConnection;
import com.vnit.api.file.columnobjectlist.ColumnObjectList;
import com.vnit.api.file.utility.Utility;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColumnObject {
    private String col_name;
    private String col_type;
    private Boolean primary_key; //To check whether the key is primary key.
    private String java_datatype;

    public ColumnObject(String col_name,String col_type ,Boolean primary_key,String java_datatype){
        this.col_name=col_name;
        this.col_type=col_type;
        this.primary_key=primary_key;
        this.java_datatype=java_datatype;
    }
    public ColumnObject(){
        
    }
    
    //Setting and Getting column name from column object.
    public String getColname(){
        return col_name;
            }
    public void  setColname(String colname){
         this.col_name=colname;
    }   
    //Setting and Getting column datatype from column object.
    public String getColtype(){
        return col_type;
    }
    public void  setColtype(String coltype){
         this.col_type=coltype;
    } 
    //Setting and Getting java datatype from column object, we get java datatype from properties file.
    public String getJavadatatype(){
        return java_datatype;
    }
    public void  setJavadatatype(String java_datatype){
         this.java_datatype=java_datatype;
    } 
    //Setting and Getting boolean value from column object.
    public Boolean getCheckvalue(){
        return primary_key;
    }
    public void  setCheckvalue(Boolean primary_key){
         this.primary_key=primary_key;
    } 

    
  
}
