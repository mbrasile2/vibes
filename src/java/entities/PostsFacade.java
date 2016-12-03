/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kATHRYN
 */
@Stateless
public class PostsFacade extends AbstractFacade<Posts> {

    @PersistenceContext(unitName = "vibePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List getUsersPosts(int user) {
        return em.createQuery("SELECT * FROM Posts p WHERE (p.Author = :name) ORDER BY postdate DESC;")
                        .setParameter("name", user)
                        .setMaxResults(10)
                        .getResultList();
    }

    public PostsFacade() {
        super(Posts.class);
    }
    
}
