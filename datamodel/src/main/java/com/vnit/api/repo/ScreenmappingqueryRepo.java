package com.vnit.api.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.ScreenmappingqueryMst;

@Transactional
@Repository
public class ScreenmappingqueryRepo {

    @Autowired
    EntityManager em;

    public ScreenmappingqueryMst getScreenmappingquery(Integer id) {
        try {
            if (id == null) {
                return null;
            }

            return em.find(ScreenmappingqueryMst.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Integer postScreenmappingquery(ScreenmappingqueryMst screenmappingquery) {
        try {
            ScreenmappingqueryMst data = getScreenmappingquery(screenmappingquery.getScreenmappingqueryid());
            if (data == null) {
                em.persist(screenmappingquery);
            } else {
                em.merge(screenmappingquery);
            }

            em.flush();
            return screenmappingquery.getScreenmappingqueryid();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public Integer deleteScreenmappingquery(Integer id) {
        try {
            ScreenmappingqueryMst data = getScreenmappingquery(id);
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
