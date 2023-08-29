package com.vnit.api.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.ScreenfieldmasterMst;

@Transactional
@Repository
public class ScreenfieldmasterRepo {

    @Autowired
    EntityManager em;

    public ScreenfieldmasterMst getScreenfieldmaster(Integer id) {
        try {
            if (id == null) {
                return null;
            }

            return em.find(ScreenfieldmasterMst.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Integer postScreenfieldmaster(ScreenfieldmasterMst screenfieldmaster) {
        try {
            System.out.println(" in repo screenfieldmaster");
            ScreenfieldmasterMst data = getScreenfieldmaster(screenfieldmaster.getScreenfieldid());
            System.out.println(data);
            if (data == null) {
                em.persist(screenfieldmaster);
            } else {
               System.out.println(em.merge(screenfieldmaster));
                em.merge(screenfieldmaster);
            }

            em.flush();
            return screenfieldmaster.getScreenfieldid();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public Integer deleteScreenfieldmaster(Integer id) {
        try {
            ScreenfieldmasterMst data = getScreenfieldmaster(id);
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
