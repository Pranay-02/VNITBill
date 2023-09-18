/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import main.java.com.vnit.api.file.col_object.Object;

/**
 *
 * @author Pranay Singhal
 */
public class DbUtility {
    
    public static ArrayList<Object> columns =  new ArrayList<>();
    public Set<String> primaryKeyColumns = new HashSet<>();
    public static String primaryKeyColumn = "";
    
    public ArrayList<Object> getColumns(String tableName, Connection connection) {        
        try {

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columnDetails = metaData.getColumns(null, null, tableName, null);

            //get primary ket details
            getPrimaryKeyColumns(metaData, tableName);
            ArrayList<String> list = new ArrayList<>(primaryKeyColumns);
            primaryKeyColumn = list.get(0);

            // Process and print the column details
            while (columnDetails.next()) {
                String columnName = columnDetails.getString("COLUMN_NAME");
                String columnType = columnDetails.getString("TYPE_NAME");
                boolean isPrimaryKey = primaryKeyColumns.contains(columnName);
                int columnSize = columnDetails.getInt("COLUMN_SIZE");
                boolean required = (columnDetails.getString("IS_NULLABLE").equals("YES")) ? true : false; 
                
                columnType = getDBVariableMapping(columnType);

                Object o = new Object(columnName, columnType, isPrimaryKey, columnSize, required);
                if(!columns.contains(o)) {
                    columns.add(o);
                    System.out.println("Added to columns");
                }

                Map<String, String> fld_map = new HashMap<>();
                fld_map.put("column_name", columnName);
                fld_map.put("column_type", columnType);
                fld_map.put("primary_key", isPrimaryKey ? "true" : "false");
                fld_map.put("size", String.valueOf(columnSize));
                fld_map.put("required", required ? "true" : "false");

                MapsUtil.fld.put(columnName, fld_map);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return columns;
    }

    public Set<String> getPrimaryKeyColumns(DatabaseMetaData metaData, String tableName) {    
        try {
            ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(null, null, tableName);

            while (primaryKeysResultSet.next()) {
                String primaryKeyColumnName = primaryKeysResultSet.getString("COLUMN_NAME");
                primaryKeyColumns.add(primaryKeyColumnName);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return primaryKeyColumns;
    }

    public String getDBVariableMapping(String dbVariable) {
        return MapsUtil.variableMap.get(dbVariable);
    }
    
    public void fillMap(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;    
            String currentMap = "";

            while ((line = reader.readLine()) != null) {
                if(line.startsWith("#")) {
                    currentMap = line.substring(1).trim();
                }
                else {
                    String []str = line.split("=");
                    if(currentMap.equals("Mysql server details")) {
                        MapsUtil.dbDetailMap.put(str[0], str[1]);
                    }
                    else 
                    if(currentMap.equals("Java data type details")) {
                        MapsUtil.variableMap.put(str[0], str[1]);
                    }
                    else 
                    if(currentMap.equals("Constants details")) {
                        MapsUtil.constantsMap.put(str[0], str[1]);
                    }
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
