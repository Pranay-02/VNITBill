package com.vnit.api.repo;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.UsersMst;
@Transactional
@Repository
public class UsersRepo{
@Autowired
EntityManager em;
public UsersMst getUsers(Integer id) {
		try {
			if (id == null) 
				return null;
			
			return em.find(UsersMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
public Integer postUsers(UsersMst users) {
		try {
UsersMst data = getUsers(users.getId());
			if (data == null)
				em.persist(users);
			else
				em.merge(users);
			
			em.flush();
			return users.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
public Integer deleteUsers(Integer id) {
		try {
UsersMst data = getUsers(id);
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