package com.vnit.api.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.ScreenmappingconditionMst;

@Transactional
@Repository
public class ScreenmappingconditionRepo {

    @Autowired
    EntityManager em;

    public ScreenmappingconditionMst getScreenmappingcondition(Integer id) {
        try {
            if (id == null) {
                return null;
            }

            return em.find(ScreenmappingconditionMst.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Integer postScreenmappingcondition(ScreenmappingconditionMst screenmappingcondition) {
        try {
            ScreenmappingconditionMst data = getScreenmappingcondition(screenmappingcondition.getScreenmappingid());
            if (data == null) {
                em.persist(screenmappingcondition);
            } else {
                em.merge(screenmappingcondition);
            }

            em.flush();
            return screenmappingcondition.getScreenmappingid();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public Integer deleteScreenmappingcondition(Integer id) {
        try {
            ScreenmappingconditionMst data = getScreenmappingcondition(id);
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
