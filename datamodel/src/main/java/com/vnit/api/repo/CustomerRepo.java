package com.vnit.api.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.CustomerMst;

@Transactional
@Repository
public class CustomerRepo {
	
	@Autowired
	EntityManager em;
	
	public CustomerMst getCustomer(Integer id) {
		try {
			if (id == null) 
				return null;
			
			return em.find(CustomerMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public Integer postCustomer(CustomerMst customer) {
		try {
			CustomerMst data = getCustomer(customer.getCcode());
			if (data == null)
				em.persist(customer);
			else
				em.merge(customer);
			
			em.flush();
			return customer.getCcode();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	public Integer deleteCustomer(Integer id) {
		try {
			CustomerMst data = getCustomer(id);
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
