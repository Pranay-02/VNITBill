package com.vnit.api.file.columnobjectlist;

import com.vnit.api.file.col_object.ColumnObject;
import com.vnit.api.file.dbConnection.DBConnection;
import com.vnit.api.file.utility.Utility;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ColumnObjectList {
    private String table_name;
    private List<ColumnObject> collist;
    
    //Setting and Getting Table name.

    public void  setTablename(String table_name){
              this.table_name=table_name;         
    }
     
    public String getTablename(){  
        return this.table_name;
    }
    
    //Setting and Getting column list
    
    public void setCollist(List<ColumnObject> collist){
        this.collist=collist;
    }
    public List<ColumnObject> getCollist(){
        return collist;
    }
    
    // to get the coldata and add to list 
    public List<ColumnObject> getData(String table_name) throws SQLException{
                Connection myConn_check=null;
//                this.table_name=table_name;
                DBConnection d1=new DBConnection();
                myConn_check=d1.setConnection(myConn_check);
                List<ColumnObject> list=new ArrayList<>();
                Utility u2=new Utility();
                Map<String,String> map_1=new HashMap<>();
                u2.setMap(map_1);
                               
                    try {
                            Statement statement = myConn_check.createStatement();
                            String query= "Select * from "+table_name;
                            System.out.println(" before metadata");
                            DatabaseMetaData meta=myConn_check.getMetaData(); 
                            System.out.println(" after metadata");
                            ResultSet rs= statement.executeQuery(query);
                            ResultSetMetaData resultSetMetaData= rs.getMetaData();
                            
                            rs = meta.getPrimaryKeys(map_1.get("db_name"), null, table_name);
                          //  rs = meta.getPrimaryKeys("mydataml_test", null, table_name);
                            List aList = new ArrayList();
                                while (rs.next()) {
                                        aList.add(rs.getString(4));  
                                   }

                                for (int i = 1; i <= resultSetMetaData.getColumnCount();i++) {
                                    if(aList.contains(resultSetMetaData.getColumnName(i))){
                                    list.add(new ColumnObject(resultSetMetaData.getColumnName(i),resultSetMetaData.getColumnTypeName(i),true,map_1.get(resultSetMetaData.getColumnTypeName(i))));
                                    }else{
                                    list.add(new ColumnObject(resultSetMetaData.getColumnName(i),resultSetMetaData.getColumnTypeName(i),false,map_1.get(resultSetMetaData.getColumnTypeName(i))));
                                    }
                            }
                   
                    return list;
                                
                    } catch (SQLException ex) {
                        Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    } 
                    
    }
    
    
    

    
}
