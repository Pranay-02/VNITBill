package com.vnit.api.file.system;

import java.util.Properties;
import java.io.*;

public class PropertyClass { //Getting properties file which consists of all the data required for connection and all
    Properties p=new Properties() ;
    private String file_name="system.properties";

    public Properties getFile(){//getting the properties file
        return p;
    }
    
    public void  setName(String file_name){//Setting the properties file name
            this.file_name=file_name;
      }
      
   public String getName(){//getting  the properties file name
       return file_name;
      }

}
