package com.vnit.api.repo;

import javax.persistence.EntityManager;import javax.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;
import com.vnit.api.entity.DepartmentMst;

@Transactional
@Repository
public class DepartmentRepo {

@Autowired
EntityManager em;

public DepartmentMst getDepartment(Integer id) {
	try {
		if(id == null)
			return null;
		return em.find(DepartmentMst.class, id);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public Integer postDepartment(DepartmentMst department) {
	try {
		DepartmentMst data = getDepartment(department.getdeptid());
		if(data == null)
			em.persist(department);
		else
			em.merge(department);
		em.flush();
		return department.getdeptid();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return 0;
}

public Integer deleteDepartment(Integer id) {
	try {
		DepartmentMst data = getDepartment(id);
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