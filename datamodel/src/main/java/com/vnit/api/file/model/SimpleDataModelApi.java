/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.model;

import com.vnit.api.entity.ScreenmappingconditionMst;
import com.vnit.api.file.dbConnection.DBConnection;
import com.vnit.api.file.utility.TestServlet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.java.com.vnit.api.file.t1Template.ApiTemplate;
import main.java.com.vnit.api.file.t2Template.S2ApiFunction;
import main.java.com.vnit.api.file.utility.DbUtility;
import main.java.com.vnit.api.file.utility.ProcessSubstitution;
import main.java.com.vnit.api.file.col_object.Object;
import main.java.com.vnit.api.file.t3Template.S3ApiTemplate;

/**
 *
 * @author Pranay Singhal
 */
public class SimpleDataModelApi {
    
     public String makeAPIFile(String tableName) throws SQLException{
            DbUtility dbUtility = new DbUtility();
            DBConnection dbConn = new DBConnection();
  
            try {
                dbUtility.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility.getColumns(tableName, dbConn.setConnection(null));
            } catch (SQLException ex) {
                    System.out.println("****Error");
            }       


            ArrayList<Object> columns = dbUtility.getColumns();
            ArrayList<String> columnName = dbUtility.getPKColumns();
            return getApiTemplate(columnName);
    
    }
    
    public String getApiTemplate(ArrayList<String> PK1) {
        String template = "";
        ProcessSubstitution ps = new ProcessSubstitution();
        ApiTemplate api = new ApiTemplate();
        
        template += api.getTemplate(PK1.get(0));
        
       template = ps.processTemplate(template);
       return template;

    }
    
     public String makeS2APIFile(ArrayList<String> tableNames) throws SQLException {
            DbUtility dbUtility1 = new DbUtility();
            DbUtility dbUtility2 = new DbUtility();

            DBConnection dbConn = new DBConnection();
            String tableName1 = tableNames.get(0);
            String tableName2 = tableNames.get(1);
            try {
                dbUtility1.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility1.getColumns(tableName1, dbConn.setConnection(null));
                
                dbUtility2.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility2.getColumns(tableName2, dbConn.setConnection(null));
            } catch (SQLException ex) {
                    System.out.println("****Error");
            }

            ArrayList<Object> columns1 = dbUtility1.getColumns();
            ArrayList<String> PK1 = dbUtility1.getPKColumns();
            
            ArrayList<Object> columns2 = dbUtility2.getColumns();
            ArrayList<String> PK2 = dbUtility2.getPKColumns();
            return getS2ApiTemplate(PK1);
    }
    
    public String getS2ApiTemplate(ArrayList<String> PK1) {
        String template = "";
        ProcessSubstitution ps = new ProcessSubstitution();
        S2ApiFunction s2api = new S2ApiFunction();
        
        template += s2api.getTemplate(PK1.get(0));
        
       template = ps.processTemplate(template);
       return template;

    } 
    
    public String makeS3APIFile(ArrayList<String> tableNames, ScreenmappingconditionMst mappingObj) throws SQLException {
            DbUtility dbUtility1 = new DbUtility();
            DbUtility dbUtility2 = new DbUtility();

            DBConnection dbConn = new DBConnection();
            String tableName1 = tableNames.get(0);
            String tableName2 = tableNames.get(1);
            try {
                dbUtility1.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility1.getColumns(tableName1, dbConn.setConnection(null));
                
                dbUtility2.fillMap(TestServlet.contextpath + "properties.txt");
                dbUtility2.getColumns(tableName2, dbConn.setConnection(null));
            } catch (SQLException ex) {
                    System.out.println("****Error");
            }

            ArrayList<Object> columns1 = dbUtility1.getColumns();
            ArrayList<String> PK1 = dbUtility1.getPKColumns();
            
            ArrayList<Object> columns2 = dbUtility2.getColumns();
            ArrayList<String> PK2 = dbUtility2.getPKColumns();
            return getS3ApiTemplate(PK1, mappingObj);
    }
    
    public String getS3ApiTemplate(ArrayList<String> PK1, ScreenmappingconditionMst mappingObj) {
        String template = "";
        ProcessSubstitution ps = new ProcessSubstitution();
        S3ApiTemplate s3api = new S3ApiTemplate();
        
        String col1 = mappingObj.getMasterQueryColumn();
        String col2 = mappingObj.getDetailQueryColumn();
        String query = mappingObj.getQuery();
        String mappingTable = mappingObj.getMappingtable();
        
        template += s3api.getFunction1(PK1.get(0));
        template += s3api.getFunction2(col1, col2, query, mappingTable);
        
       template = ps.processTemplate(template);
       return template;

    } 
}
