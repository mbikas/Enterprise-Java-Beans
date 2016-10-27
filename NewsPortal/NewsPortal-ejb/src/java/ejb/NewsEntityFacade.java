/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author bikas
 * 
 * Here a Session facade for the Entity class is created.
 * This is a Stateless Session
 * Just for the sake of simplicity no-interface is user rather than remote or local
 */
@Stateless
public class NewsEntityFacade {
   
    /**
     * Persistence object: Message Table
     */
    @PersistenceContext(unitName = "NewsPortal-ejbPU")
    private EntityManager em;

    /**
     * Creates a Message Entity
     * @param newsEntity 
     */
    public void create(NewsEntity newsEntity) {
        em.persist(newsEntity);
    }
    
    /**
     * Edits a Message Entity
     * @param newsEntity 
     */
    public void edit(NewsEntity newsEntity) {
        em.merge(newsEntity);
    }

    /**
     * Removes a Message
     * @param newsEntity 
     */
    public void remove(NewsEntity newsEntity) {
        em.remove(em.merge(newsEntity));
    }
    
    /**
     * return a Message based on the given primary key id
     * @param id
     * @return Message based on the given primary key id
     */
    public NewsEntity find(Object id) {
        return em.find(NewsEntity.class, id);
    }

    /**
     * find all the message from  the table and return  as a list
     * @return find all the message from  the table and return  as a list
     */
    public List<NewsEntity> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(NewsEntity.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     * find all the message from  the table and return  as a list with the given range
     * @param range
     * @return find all the message from  the table and return  as a list with the given range
     */
    public List<NewsEntity> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(NewsEntity.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * total number of message in the table
     * @return total number of message in the table
     */
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<NewsEntity> rt = cq.from(NewsEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
