/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bikas.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bikas
 * This is the Entity bean class created from the the Derby database
 */
@Entity
@Table(name = "PRODUCTS")
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "CODE")
    private String code;

    public Products() {
    }

    /**
     *
     * @param id
     */
    public Products(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return id from the database
     */
    public Integer getId() {
        return id;
    }

    /**
     * set the id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return name from the db
     */
    public String getName() {
        return name;
    }

    /**
     * set the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the price from db
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * set the price
     * @param price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     *
     * @return the code of the product from the db
     */
    public String getCode() {
        return code;
    }

    /**
     * set the code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * checks if two entity object are equal or not
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bikas.entity.Products[id=" + id + "]";
    }

}
