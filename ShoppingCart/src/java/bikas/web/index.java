/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bikas.web;

import bikas.ejb.Transactions;
import bikas.entity.Products;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author bikas
 *
 * This is a Managed Bean class
 * This class initiated each transaction and
 * retrieves all the products for each customer
 */
@ManagedBean
@RequestScoped
public class index implements Serializable {

    //Transaction is Enterprise Session bean used
    //to get the product for each customer
    @EJB
    private Transactions transactions;

    //Stateful Enterprise Session bean
    //alternately tested with both stateful and stateless
    //@EJB
    //private TransactionsStateful transactions;


    /** Creates a new instance of index */
    public index() {
    }

    /**
     * gets all the products from the entity bean
     * @return all the products from the shop
     */
    public List<Products> getProducts(){
        return transactions.retrieveProducts();
    }

}
