package com.vnit.api.repo;

import java.util.ArrayList;import java.util.List;import javax.persistence.EntityManager;import javax.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;

import com.vnit.api.entity.Project;
import com.vnit.api.entity.ProjectMst;
import com.vnit.api.entity.ProjectPK;
import com.vnit.api.entity.ScholarMst;

@Transactional
@Repository
public class ScholarRepo {

@Autowired
EntityManager em;

public ScholarMst getScholar(Integer id) {
	try {
		if(id == null)
			return null;

		return em.find(ScholarMst.class, id);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public Integer postScholar(ScholarMst scholar) {
	try {
		ScholarMst data = getScholar(scholar.getscholarid());
		if(data == null) {
			em.persist(scholar);
		} else {
			List<Integer> items = new ArrayList<>();
			List<ProjectMst> dtls = data.getProject();
			for (ProjectMst dt : dtls) {
				items.add(dt.getprojectid());
			}

			List<ProjectMst> dtlList = scholar.getProject();
			for (ProjectMst dtl : dtlList) {
				Project detail = em.find(Project.class, new ProjectPK(dtl.getprojectid(), data.getscholarid()));
				if(detail == null) {
					detail = new Project();
					detail.setProjectPK(new ProjectPK(dtl.getprojectid (), data.getscholarid()));
					detail.settitle(dtl.gettitle());
					detail.setduration(dtl.getduration());
					em.persist(detail);
				}
				else {
					detail.settitle(dtl.gettitle());
					detail.setduration(dtl.getduration());
					em.merge(detail);
					items.remove(dtl.getprojectid());
				}
			}

			for (Integer projectid : items) {
				Project detail = em.find(Project.class, new ProjectPK(projectid, data.getscholarid()));
				em.remove(detail);
			}

			data.setname(scholar.getname());
			data.setage(scholar.getage());
			em.merge(data);
		}

		em.flush();
		return scholar.getscholarid();

	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return 0;
}

public Integer deleteScholar(Integer id) {
	try {
		ScholarMst data = getScholar(id);
		if(data != null) {
			List<ProjectMst> list = data.getProject();
			for(ProjectMst dt : list) {
				Project detail = em.find(Project.class, new ProjectPK(dt.getprojectid(), data.getscholarid()));
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