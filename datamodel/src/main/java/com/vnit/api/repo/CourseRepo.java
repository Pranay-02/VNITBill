package com.vnit.api.repo;

import javax.persistence.EntityManager;import javax.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;import com.vnit.api.entity.CustomerMst;
import com.vnit.api.entity.CourseMst;

@Transactional
@Repository
public class CourseRepo {

@Autowired
EntityManager em;

public CourseMst getCourse(Integer id) {
	try {
		if(id == null)
			return null;
		return em.find(CourseMst.class, id);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public Integer postCourse(CourseMst course) {
	try {
		CourseMst data = getCourse(course.getcourseid());
		if(data == null)
			em.persist(course);
		else
			em.merge(course);
		em.flush();
		return course.getcourseid();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return 0;
}

public Integer deleteCourse(Integer id) {
	try {
		CourseMst data = getCourse(id);
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