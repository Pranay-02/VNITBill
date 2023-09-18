/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.col_object;

import java.util.Objects;

/**
 *
 * @author Pranay Singhal
 */
public class Object {

    String columnName;
    String columnType;
    boolean primaryKey;
    int size;
    boolean required;

    public Object(String columnName, String columnType, boolean primaryKey, int size, boolean required) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.primaryKey = primaryKey;
        this.size = size;
        this.required = required;
    }

    public String getColumnName() {
        return this.columnName;
    }
    
    public String getColumnType() {
        return this.columnType;
    }

    public boolean getColumnPrimaryKey() {
        return this.primaryKey;
    }

    public int getColumnSize() {
        return this.size;
    }

    public boolean getColumnRequired() {
        return this.required;
    }   
    
     @Override
    public boolean equals(java.lang.Object obj) {
        if (obj == null) {
            return false;
        }
 
        final Object other = (Object) obj;
        if (this.primaryKey != other.primaryKey) {
            return false;
        }
        if (this.size != other.size) {
            return false;
        }
        if (this.required != other.required) {
            return false;
        }
        if (!Objects.equals(this.columnName, other.columnName)) {
            return false;
        }
        if (!Objects.equals(this.columnType, other.columnType)) {
            return false;
        }
        return true;
    }
 
    
       
}
