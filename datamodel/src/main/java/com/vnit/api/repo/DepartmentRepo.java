package com.vnit.api.repo;

import java.util.ArrayList;import java.util.List;import javax.persistence.EntityManager;import javax.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;

import com.vnit.api.entity.Deptdtl;
import com.vnit.api.entity.DeptdtlMst;
import com.vnit.api.entity.DeptdtlPK;
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
		if(data == null) {
			em.persist(department);
		} else {
			List<Integer> items = new ArrayList<>();
			List<DeptdtlMst> dtls = data.getDeptdtl();
			for (DeptdtlMst dt : dtls) {
				items.add(dt.getcourseid());
			}

			List<DeptdtlMst> dtlList = department.getDeptdtl();
			for (DeptdtlMst dtl : dtlList) {
				Deptdtl detail = em.find(Deptdtl.class, new DeptdtlPK(dtl.getcourseid(), data.getdeptid()));
				if(detail == null) {
					detail = new Deptdtl();
					detail.setDeptdtlPK(new DeptdtlPK(dtl.getcourseid (), data.getdeptid()));
					detail.setstudents_count(dtl.getstudents_count());
					em.persist(detail);
				}
				else {
					detail.setstudents_count(dtl.getstudents_count());
					em.merge(detail);
					items.remove(dtl.getcourseid());
				}
			}

			for (Integer courseid : items) {
				Deptdtl detail = em.find(Deptdtl.class, new DeptdtlPK(courseid, data.getdeptid()));
				em.remove(detail);
			}

			data.setname(department.getname());
			data.setdepttp(department.getdepttp());
			em.merge(data);
		}

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
			List<DeptdtlMst> list = data.getDeptdtl();
			for(DeptdtlMst dt : list) {
				Deptdtl detail = em.find(Deptdtl.class, new DeptdtlPK(dt.getcourseid(), data.getdeptid()));
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