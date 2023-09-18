package com.vnit.api.file.dbConnection;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import com.vnit.api.file.utility.Utility;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {
    
		private Connection myConn;
                
                public Connection setConnection(Connection myConn) throws SQLException{//Set Database connection
                    this.myConn=myConn;
                    Utility u2=new Utility();
                    Map<String,String> map_1=new HashMap<>();
                    u2.setMap(map_1);
                    try {
			myConn = DriverManager.getConnection(map_1.get("db_url"),map_1.get("db_username") , map_1.get("db_password"));               
			if(myConn!=null){
                           successConnection();   
                        }else{
                            System.out.print("Connection cannot be established!!!!");
                        }
                        return myConn;
                    } catch (Exception exc) {
			exc.printStackTrace();
                        myConn.close();
                        return null;
                    } 
                    
                    }
                
                public static void successConnection(){// success message for connection.*Static method*
                    
                    System.out.println("Successfully connected!!!");
                }
}
