/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bikas.ejb;

import bikas.entity.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bikas
 *
 * This is a stateless session bean
 * Some of the Business logic is written in this class
 *
 */
@Stateless
public class Transactions {

    //Entity bean Class ( Shopping cart database)
    @PersistenceContext(unitName = "ShoppingCartPU")
    private EntityManager em;
    

    /**
     *
     * @return all the products from the database
     */
    public List<Products> retrieveProducts(){
        return em.createQuery("SELECT p FROM Products p").getResultList();
    }

    /**
     * Checks is the given query is valid or not
     * @param query a database query
     * @return 0 if invalid query otherwise total no of product
     */
    public int checkIfQueryExists(String query){
         List<Products> items = em.createQuery("SELECT p FROM Products p WHERE p.code = :code").
                setParameter("code", query).getResultList();
         return items.size();
    }

    /**
     * return all the products based on the code
     * @param query
     * @return return all the products based on the code
     */
    public Products returnProduct(String query){
        Products item = (Products)
                   em.createQuery("SELECT p FROM Products p WHERE p.code = :code").setParameter("code", query).getSingleResult();
       return item;
    }

 
}
