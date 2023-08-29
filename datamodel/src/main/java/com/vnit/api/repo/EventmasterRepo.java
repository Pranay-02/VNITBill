package com.vnit.api.repo;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.EventmasterMst;
@Transactional
@Repository
public class EventmasterRepo{
@Autowired
EntityManager em;
public EventmasterMst getEventmaster(Integer id) {
		try {
			if (id == null) 
				return null;
			
			return em.find(EventmasterMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	public Integer postEventmaster(EventmasterMst eventmaster) {
		try {
                EventmasterMst data = getEventmaster(eventmaster.getEventid());
			if (data == null)
				em.persist(eventmaster);
			else
				em.merge(eventmaster);
			
			em.flush();
			return eventmaster.getEventid();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
public Integer deleteEventmaster(Integer id) {
		try {
EventmasterMst data = getEventmaster(id);
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