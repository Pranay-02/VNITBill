package com.vnit.api.repo;

import javax.persistence.EntityManager;import javax.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;
import com.vnit.api.entity.ExamMst;

@Transactional
@Repository
public class ExamRepo {

@Autowired
EntityManager em;

public ExamMst getExam(Integer id) {
	try {
		if(id == null)
			return null;
		return em.find(ExamMst.class, id);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public Integer postExam(ExamMst exam) {
	try {
		ExamMst data = getExam(exam.getexamid());
		if(data == null)
			em.persist(exam);
		else
			em.merge(exam);
		em.flush();
		return exam.getexamid();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return 0;
}

public Integer deleteExam(Integer id) {
	try {
		ExamMst data = getExam(id);
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