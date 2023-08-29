/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnit.api.file.utility;
    import java.io.*;  
    import javax.servlet.*;  
    import javax.servlet.http.*;  
      
      
    public class TestServlet extends HttpServlet{ 
    public static String contextpath="";    
    public void doGet(HttpServletRequest req,HttpServletResponse res)  
    throws ServletException,IOException  
    {  
    res.setContentType("text/html");  
    
      
    //creating ServletContext object  
    ServletContext context=getServletContext();  
    contextpath=context.getContextPath();
    System.out.println(" context path "+contextpath);
    String DPATH =context.getRealPath("")+"\\WEB-INF\\";  //30-JAN-2007
    String filepath=DPATH;
    contextpath=DPATH;
    System.out.println(" context path 2 "+contextpath);
      
    //Getting the value of the initialization parameter and printing it  
    
      
    }}  