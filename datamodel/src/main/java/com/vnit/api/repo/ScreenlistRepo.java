package com.vnit.api.repo;

import com.vnit.api.entity.ScreenlistDetail;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.vnit.api.entity.ScreenlistHdrMst;
import com.vnit.api.entity.ScreenlistPK;
import com.vnit.api.entity.ScreenlistdtlMst;

@Transactional
@Repository
public class ScreenlistRepo {
    
        @Autowired
	EntityManager em;
        
        public ScreenlistHdrMst getScreenlist(Integer id) {
		try {
			if (id == null)
				return null;
			
			return em.find(ScreenlistHdrMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
        
        
        public Integer postScreenlist(ScreenlistHdrMst screenlist) {
		try {
			ScreenlistHdrMst data = getScreenlist(screenlist.getScreenlistid());
                        System.out.println("Screen List:1");
                        System.out.println(screenlist.getScreenlistid());
                        System.out.println(data);
                        System.out.println("Screen List:2");
			if (data == null) {
				screenlist.setScreenlistid(null);
				em.persist(screenlist);
			} else {
				List<Integer> screenlists = new ArrayList<>();
				List<ScreenlistdtlMst> sclst = data.getScreenlistdtl();
				for (ScreenlistdtlMst sc : sclst) {
					screenlists.add(sc.getScreenfieldid());
				}
				
				List<ScreenlistdtlMst> scList = screenlist.getScreenlistdtl();
				for (ScreenlistdtlMst scl : scList) {
					ScreenlistDetail detail = em.find(ScreenlistDetail.class, new ScreenlistPK(data.getScreenlistid(),scl.getScreenfieldid() ));
					if (detail == null) {
						detail = new ScreenlistDetail();
						detail.setScreenlistPK(new ScreenlistPK(scl.getScreenfieldid(), data.getScreenlistid()));
						detail.setQuerycol(scl.getQuerycol());
						detail.setScreenid(scl.getScreenid());
						
						
						em.persist(detail);
					} else {
						detail.setQuerycol(scl.getQuerycol());
						detail.setQuerycol(scl.getQuerycol());
						detail.setScreenid(scl.getScreenid());
						
						em.merge(detail);
						screenlists.remove(scl.getScreenfieldid());
					}
				}
				
				for (Integer screenlistid : screenlists) {
					ScreenlistDetail detail = em.find(ScreenlistDetail.class, new ScreenlistPK(data.getScreenlistid(),screenlistid));
					
					em.remove(detail);
				}
				
				data.setQuery(screenlist.getQuery());
				data.setJfunction(screenlist.getJfunction());
				data.setListname(screenlist.getListname());
				
				
				em.merge(data);
			}
			
			em.flush();
			return screenlist.getScreenlistid();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
        
        
	public Integer deleteScreenlist(Integer id) {
		try {
			ScreenlistHdrMst data = getScreenlist(id);
			if (data != null) {
				List<ScreenlistdtlMst> sclList = data.getScreenlistdtl();
				for (ScreenlistdtlMst scl : sclList) {
					ScreenlistDetail detail = em.find(ScreenlistDetail.class, new ScreenlistPK(data.getScreenlistid(),scl.getScreenfieldid()));
					
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
