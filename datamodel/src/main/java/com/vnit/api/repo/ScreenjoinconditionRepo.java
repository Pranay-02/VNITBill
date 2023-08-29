package com.vnit.api.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.ScreenjoinconditionMst;

@Transactional
@Repository
public class ScreenjoinconditionRepo {

    @Autowired
    EntityManager em;

    public ScreenjoinconditionMst getScreenjoincondition(Integer id) {
        try {
            if (id == null) {
                return null;
            }

            return em.find(ScreenjoinconditionMst.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Integer postScreenjoincondition(ScreenjoinconditionMst screenjoincondition) {
        try {
            ScreenjoinconditionMst data = getScreenjoincondition(screenjoincondition.getScreenjoinid());
            if (data == null) {
                em.persist(screenjoincondition);
            } else {
                em.merge(screenjoincondition);
            }

            em.flush();
            return screenjoincondition.getScreenjoinid();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public Integer deleteScreenjoincondition(Integer id) {
        try {
            ScreenjoinconditionMst data = getScreenjoincondition(id);
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
