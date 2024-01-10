package com.vnit.api.repo;

import javax.persistence.EntityManager;import javax.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;import com.vnit.api.entity.CustomerMst;
import com.vnit.api.entity.PeopleMst;

@Transactional
@Repository
public class PeopleRepo {

@Autowired
EntityManager em;

public PeopleMst getPeople(Integer id) {
	try {
		if(id == null)
			return null;
		return em.find(PeopleMst.class, id);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public Integer postPeople(PeopleMst people) {
	try {
		PeopleMst data = getPeople(people.getpeopleid());
		if(data == null)
			em.persist(people);
		else
			em.merge(people);
		em.flush();
		return people.getpeopleid();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return 0;
}

public Integer deletePeople(Integer id) {
	try {
		PeopleMst data = getPeople(id);
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