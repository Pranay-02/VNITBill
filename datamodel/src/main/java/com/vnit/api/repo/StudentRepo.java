package com.vnit.api.repo;

import javax.persistence.EntityManager;import javax.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;import com.vnit.api.entity.StudentMst;

@Transactional
@Repository
public class StudentRepo {

@Autowired
EntityManager em;

public StudentMst getStudent(Integer id) {
	try {
		if(id == null)
			return null;
		return em.find(StudentMst.class, id);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public Integer postStudent(StudentMst student) {
	try {
		StudentMst data = getStudent(student.getstudentid());
		if(data == null) {
                                            em.persist(student);
                                            System.out.println("Null data");
                                    }
		else
			em.merge(student);
		em.flush();
		return student.getstudentid();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return 0;
}

public Integer deleteStudent(Integer id) {
	try {
		StudentMst data = getStudent(id);
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