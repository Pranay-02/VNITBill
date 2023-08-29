package com.vnit.api.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.BillDetail;
import com.vnit.api.entity.BillDtlMst;
import com.vnit.api.entity.BillDtlPK;
import com.vnit.api.entity.BillHdrMst;

@Transactional
@Repository
public class BillRepo {
	
	@Autowired
	EntityManager em;
	
	public BillHdrMst getBill(Integer id) {
		try {
			if (id == null)
				return null;
			
			return em.find(BillHdrMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public Integer postBill(BillHdrMst bill) {
		try {
			BillHdrMst data = getBill(bill.getBillno());
			if (data == null) {
				bill.setBillno(null);
				em.persist(bill);
			} else {
				List<Integer> items = new ArrayList<>();
				List<BillDtlMst> dtls = data.getBilldtl();
				for (BillDtlMst dt : dtls) {
					items.add(dt.getItcode());
				}
				
				List<BillDtlMst> dtlList = bill.getBilldtl();
				for (BillDtlMst dtl : dtlList) {
					BillDetail detail = em.find(BillDetail.class, new BillDtlPK(dtl.getItcode(), data.getBillno()));
					if (detail == null) {
						detail = new BillDetail();
						detail.setBillDtlPK(new BillDtlPK(dtl.getItcode(), data.getBillno()));
						detail.setItrate(dtl.getItrate());
						detail.setQty(dtl.getQty());
						detail.setValue(dtl.getValue());
						
						em.persist(detail);
					} else {
						detail.setItrate(dtl.getItrate());
						detail.setQty(dtl.getQty());
						detail.setValue(dtl.getValue());
						
						em.merge(detail);
						items.remove(dtl.getItcode());
					}
				}
				
				for (Integer itcode : items) {
					BillDetail detail = em.find(BillDetail.class, new BillDtlPK(itcode, data.getBillno()));
					
					em.remove(detail);
				}
				
				data.setBilltp(bill.getBilltp());
				data.setBilldt(bill.getBilldt());
				data.setBillamt(bill.getBillamt());
				data.setCcode(bill.getCcode());
				
				em.merge(data);
			}
			
			em.flush();
			return bill.getBillno();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	public Integer deleteBill(Integer id) {
		try {
			BillHdrMst data = getBill(id);
			if (data != null) {
				List<BillDtlMst> dtlList = data.getBilldtl();
				for (BillDtlMst dtl : dtlList) {
					BillDetail detail = em.find(BillDetail.class, new BillDtlPK(dtl.getItcode(), data.getBillno()));
					
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
