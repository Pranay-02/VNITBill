package com.vnit.api.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.ItemMst;

@Transactional
@Repository
public class ItemRepo {
	
	@Autowired
	EntityManager em;
	
	public ItemMst getItem(Integer id) {
		try {
			if (id == null) 
				return null;
			
			return em.find(ItemMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public Integer postItem(ItemMst item) {
		try {
			ItemMst data = getItem(item.getItcode());
			if (data == null)
				em.persist(item);
			else
				em.merge(item);
			
			em.flush();
			return item.getItcode();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	public Integer deleteItem(Integer id) {
		try {
			ItemMst data = getItem(id);
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
