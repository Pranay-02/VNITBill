package com.vnit.api.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.BillTypeMst;

@Transactional
@Repository
public class BillTypeRepo {
	
	@Autowired
	EntityManager em;
	
	public BillTypeMst getBillType(Integer id) {
		try {
			if (id == null)
				return null;
			
			return em.find(BillTypeMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public Integer postBillType(BillTypeMst bill) {
		try {
			BillTypeMst data = getBillType(bill.getBillid());
			if (data == null)
				em.persist(bill);
			else
				em.merge(bill);
			
			em.flush();
			return bill.getBillid();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	public Integer deleteBillType(Integer id) {
		try {
			BillTypeMst data = getBillType(id);
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
