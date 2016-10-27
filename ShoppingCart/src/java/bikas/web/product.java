/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bikas.web;

import bikas.ejb.Transactions;
import bikas.ejb.TransactionsStateful;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import bikas.entity.Products;

/**
 *
 * @author bikas
 * This is also a Managed Bean class used to manage each product
 * during a session of a customer
 */
@ManagedBean
@RequestScoped
public class product implements Serializable {

    //Transaction is Stateless Enterprise Session bean
    @EJB
    private Transactions transactions;

    //Stateful Enterprise Session bean
    //alternately tested with both stateful and stateless
    //@EJB
    //private TransactionsStateful transactions;

    //shopping cart for each user
    @ManagedProperty(value = "#{cart}")
    cart myCart;

    /**
     *
     * @return the cart for a user
     */
    public cart getMyCart() {
        return myCart;
    }

    /**
     * set a cart for a user during a session
     * @param myCart
     */
    public void setMyCart(cart myCart) {
        this.myCart = myCart;
    }

    /**
     * Creates a new instance of product
     */
    public product() {
    }


    /**
     * 
     * @return query based on product code
     */
    public String getQuery(){
        return FacesContext.getCurrentInstance().getExternalContext().
                    getRequestParameterMap().get("query");
    }

    /**
     * checks is the product code is valid or not
     * if invalid redirects to the error.xhtmp page
     * @throws IOException
     */
    public void checkIfQueryExists() throws IOException{
        if(transactions.checkIfQueryExists(getQuery()) == 0){
            FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");

        }
    }

    /**
     *
     * @return the product based on the code
     */
    public Products getProduct(){
        return transactions.returnProduct(getQuery());
    }

    /**
     * add the product into the cart based on product code
     */
    public void addToCart(){
        String query = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("query");
        myCart.add(transactions.returnProduct(query));
    }

}
