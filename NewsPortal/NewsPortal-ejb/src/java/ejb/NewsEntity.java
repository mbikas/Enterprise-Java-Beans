/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author bikas
 * @date 11.07.2014
 * 
 * 
 * This is an Entity class created to store each messages.
 * An entity class is basically a java class that represents a table (sample) in a database.
 * 
 */
@Entity
public class NewsEntity implements Serializable {
    
    //serialized id
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //one of the database column titile
    private String title;
    //another database cloun body
    private String body;

    /**
     * 
     * @return message body from the database
     */
    public String getBody() {
        return body;
    }

    /**
     * set the body of the body of the message in the table
     * @param body 
     */
    public void setBody(String body) {
        this.body = body;
    }

    
    /**
     * return the title from sample table
     * @return 
     */
    public String getTitle() {
        return title;
    }

    /**
     * set the title
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * return the primary key id
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * set the primary key
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * determined if two objects are equal
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewsEntity)) {
            return false;
        }
        NewsEntity other = (NewsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.NewsEntity[id=" + id + "]";
    }

}
