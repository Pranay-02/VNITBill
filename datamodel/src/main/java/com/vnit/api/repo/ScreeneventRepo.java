package com.vnit.api.repo;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.vnit.api.entity.ScreeneventMst;
@Transactional
@Repository
public class ScreeneventRepo{
@Autowired
EntityManager em;
public ScreeneventMst getScreenevent(Integer id) {
		try {
			if (id == null) 
				return null;
			
			return em.find(ScreeneventMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	public Integer postScreenevent(ScreeneventMst screenevent) {
		try {
ScreeneventMst data = getScreenevent(screenevent.getScreeneventid());
			if (data == null)
				em.persist(screenevent);
			else
				em.merge(screenevent);
			
			em.flush();
			return screenevent.getScreeneventid();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
public Integer deleteScreenevent(Integer id) {
		try {
ScreeneventMst data = getScreenevent(id);
			if (data != null) {
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