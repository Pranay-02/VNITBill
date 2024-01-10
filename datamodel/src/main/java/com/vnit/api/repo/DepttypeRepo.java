package com.vnit.api.repo;

import javax.persistence.EntityManager;import javax.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;import com.vnit.api.entity.CustomerMst;
import com.vnit.api.entity.DepttypeMst;

@Transactional
@Repository
public class DepttypeRepo {

@Autowired
EntityManager em;

public DepttypeMst getDepttype(Integer id) {
	try {
		if(id == null)
			return null;
		return em.find(DepttypeMst.class, id);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public Integer postDepttype(DepttypeMst depttype) {
	try {
		DepttypeMst data = getDepttype(depttype.getdepttypeid());
		if(data == null)
			em.persist(depttype);
		else
			em.merge(depttype);
		em.flush();
		return depttype.getdepttypeid();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return 0;
}

public Integer deleteDepttype(Integer id) {
	try {
		DepttypeMst data = getDepttype(id);
		if(data != null) {
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