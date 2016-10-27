/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bikas.web;

import bikas.entity.Products;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author bikas
 * This is also Managed Bean class used to represent a shopping cart
 * for each user during a session
 *
 */
@ManagedBean
@SessionScoped
public class cart implements Serializable{

    /** Creates a new instance of cart */
    public cart() {
    }

    //keeps track of the products for each user in the cart
    private List<Products> products = new ArrayList();


    
    /**
     * adds the product p in the cart
     * @param p
     */
    public void add(Products p){
        products.add(p);
    }

    /**
     * removes the products form the cart
     * @param p
     */
    public void remove(Products p){
        products.remove(p);
    }

    /**
     * return the number of product in the cart
     * @return
     */
    public int getCartCount(){
        return products.size();
    }

    /**
     *
     * @return returns a HashMap with product as a key and corresponding # as value
     */
    public Map<Products, Integer> getCartContents(){
        Map<Products, Integer> cartContents = new HashMap<Products, Integer>();
        for (Products obj: products){
            if(cartContents.containsKey(obj)){
                cartContents.put(obj, cartContents.get(obj) +1);
            } else{
                cartContents.put(obj, 1);
            }
        }
        return cartContents;
    }

}
