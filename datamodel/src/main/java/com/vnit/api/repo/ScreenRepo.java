package com.vnit.api.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.ScreenDetail;
import com.vnit.api.entity.ScreenMst;
import com.vnit.api.entity.ScreengroupMst;
import com.vnit.api.entity.ScreenGrpPK;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Transactional
@Repository
public class ScreenRepo{
@Autowired
EntityManager em;
public ScreenMst getScreen(Integer id) {
		try {
                    System.out.println("Check:11");
                    
			if (id == null) 
				return null;
			System.out.println("Check:12");
//                        System.out.println(em.find(ScreenMst.class, id).getClass().getName());
                        
			return em.find(ScreenMst.class, id);
		} catch (Exception ex) {
                        System.out.println("Check:13");
			ex.printStackTrace();
		}
		
		return null;
	}

	public Integer postScreen(ScreenMst screen) {
           
		try {
                    ScreenMst data = getScreen(screen.getScreenid());
                     System.out.println("Check:2");
			if (data == null){
                            System.out.println("Check:3");
                            screen.setScreenid(null);
                            em.persist(screen);
                             //Code for Stored Procedure For loop for all the columns in Base Table
                        String table_name="";
                        int group_id=0;
                    //    String mysqlUrl = "jdbc:mysql://localhost/test";
                   //     Connection con = DriverManager.getConnection(mysqlUrl, "root", "shountya");
                        System.out.println("Connection established......");
                        //Creating a Statement object
                     //   Statement stmt = con.createStatement();
                         List<ScreengroupMst> screenList=screen.getScreengroup();
                        
                        for (ScreengroupMst dtk : screenList) {
                                        group_id=group_id+dtk.getScreengroupid();
                                        table_name=table_name+"select * from "+dtk.getBasetable()+" ;";
                                        System.out.println(dtk.getBasetable()+" group "+group_id+" screenid "+screen.getScreenid());
                                        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("insert_fields"); // Name of stored procedure
			// set parameters
			                  storedProcedure.registerStoredProcedureParameter("pscreenid", Integer.class, ParameterMode.IN);
			                 storedProcedure.registerStoredProcedureParameter("pscreengroupid", Integer.class, ParameterMode.IN);
			                 storedProcedure.registerStoredProcedureParameter("ptablename", String.class, ParameterMode.IN);
                                         storedProcedure.setParameter("ptablename", dtk.getBasetable());
                                         storedProcedure.setParameter("pscreenid", screen.getScreenid());
                                         storedProcedure.setParameter("pscreengroupid", group_id);
                                         
			// execute SP
			                 storedProcedure.execute();
                                        
                                        /*       ResultSet rs = stmt.executeQuery(table_name);
                                        ResultSetMetaData rsMetaData = rs.getMetaData();
                                        int count = rsMetaData.getColumnCount();
                                        CallableStatement stmt1 = con.prepareCall("{ call insert_screenfield(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                                        System.out.println(screen.getScreenid());
                                        for(int i = 1; i<=count; i++) {
                                                   System.out.println(i);
                                                   stmt1.setInt(1,screen.getScreenid());
                                                   stmt1.setInt(2,group_id);
                                                   stmt1.setString(3,rsMetaData.getColumnName(i));
                                                   stmt1.setString(4,rsMetaData.getColumnName(i));
                                                   if(dtk.getDb()==true){
                                                       stmt1.setInt(5,1);
                                                   }else{
                                                     stmt1.setInt(5,2);  
                                                   }
//                                                 stmt1.setBoolean(6,dtk.getDb());
                                                   stmt1.setString(6,rsMetaData.getColumnName(i));
                                                   stmt1.setBoolean(7,false);
                                                   stmt1.setString(8,null);
                                                   stmt1.setString(9,null);
                                                   stmt1.setInt(10,0);
                                                   stmt1.setInt(11,0);
                                                   stmt1.setDate(12,null);
                                                   stmt1.setBoolean(13,true);
                                                   stmt1.setInt(14,0);
                                                   stmt1.setInt(15,0);
                                                   stmt1.setString(16,null);
                                                   stmt1.setString(17,null);
                                                   stmt1.setString(18,null);
                                                   stmt1.addBatch();   
                                                   stmt1.execute();
                                        }*/
                                        System.out.println("Done 2");   
                                        table_name="";
                                        group_id=0;     
				}
                        em.flush();
                        }
                        else{
                             System.out.println("Check:4");
                            List<Integer> screengroups = new ArrayList<>();
                            List<ScreengroupMst> dtls = data.getScreengroup();
                            for (ScreengroupMst dt : dtls) {
                                        System.out.println(dt.getScreengroupid());
					screengroups.add(dt.getScreengroupid());
				}
                            List<ScreengroupMst> dtlList = screen.getScreengroup();
                            for (ScreengroupMst dtl : dtlList) {
					ScreenDetail detail = em.find(ScreenDetail.class, new ScreenGrpPK(dtl.getScreengroupid(), data.getScreenid()));
					
                                        System.out.println(detail);
                                        if (detail == null) {
                                                System.out.println("Check:5");
						detail = new ScreenDetail();
						detail.setScreenGrpPK(new ScreenGrpPK(dtl.getScreengroupid(),data.getScreenid() ));
						detail.setGpurpose(dtl.getGpurpose());
						detail.setDb(dtl.getDb());
						detail.setBasetable(dtl.getBasetable());
						detail.setDetailtable(dtl.getDetailtable());
                                                detail.setMastergroupname(dtl.getMastergroupname());
                                                detail.setMapping(dtl.getMapping());
                                                detail.setMappingtable(dtl.getMappingtable());
                                                detail.setRecordgroupcount(dtl.getRecordgroupcount());
                                                detail.setBasiclayout(dtl.getBasiclayout());
						em.persist(detail);
                                                System.out.println(detail);
					} else {
                                            System.out.println("Check:6");
						detail.setGpurpose(dtl.getGpurpose());
						detail.setDb(dtl.getDb());
						detail.setBasetable(dtl.getBasetable());
						detail.setDetailtable(dtl.getDetailtable());
                                                detail.setMastergroupname(dtl.getMastergroupname());
                                                detail.setMapping(dtl.getMapping());
                                                detail.setMappingtable(dtl.getMappingtable());
                                                detail.setRecordgroupcount(dtl.getRecordgroupcount());
                                                detail.setBasiclayout(dtl.getBasiclayout());
						em.merge(detail);
						screengroups.remove(dtl.getScreengroupid());
                                                System.out.println(detail);
					}
				} 
                                System.out.println("Check:7");    
                                for (Integer screengroupid : screengroups) {
                                        System.out.println(screengroupid); 
					ScreenDetail detail = em.find(ScreenDetail.class, new ScreenGrpPK(screengroupid, data.getScreenid()));
                                        em.remove(detail);
				}
                                
                                data.setScreendate(screen.getScreendate());
                                data.setScreenname(screen.getScreenname());
                                data.setScreenpurpose(screen.getScreenpurpose());
                                data.setScreentype(screen.getScreentype());
                                em.merge(data);
                                
                        }
                       
			return screen.getScreenid();
			
                        
		} catch (Exception ex) {
                        System.out.println("Check:8");
			ex.printStackTrace();
		}
		
		return 0;
	}
        
public Integer deleteScreen(Integer id) {
		try {
                    ScreenMst data = getScreen(id);
			if (data != null) {
                            List<ScreengroupMst> grpList = data.getScreengroup();
				for (ScreengroupMst grp : grpList) {
					ScreenDetail detail = em.find(ScreenDetail.class, new ScreenGrpPK(grp.getScreengroupid(), data.getScreenid()));
					
					em.remove(detail);
				}
				em.remove(data);
				em.flush();
				return 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}




}
