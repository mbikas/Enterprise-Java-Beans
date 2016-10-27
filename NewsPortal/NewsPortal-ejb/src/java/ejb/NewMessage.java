/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bikas
 * @date 11.07.2014
 * 
 * 
 * This the Message Bean of this EJB Module
 * The jms resources used in this class is : jms/NewMessage 
 * This jms resource we need to configure manually in the Glassfisg server before running this project
 * 
 */
@MessageDriven(mappedName = "jms/NewMessage", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class NewMessage implements MessageListener {

    //Injects the MessageDrivenContext resource into the class
    @Resource
    private MessageDrivenContext mdc;
    
    
    /**
     * here PersistenceContext (NewsPortal-ejbPU) mean the database table which defined in the persisence.xml file
     * 
     */
    @PersistenceContext(unitName = "NewsPortal-ejbPU")
    private EntityManager em;
    
    public NewMessage() {
    }
    
    /**
     * create a message object and then save a copy into the db
     * @param message 
     */
    public void onMessage(Message message) {

        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                NewsEntity e = (NewsEntity) msg.getObject();
                save(e);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }

    }

    /**
     * persisting the message
     * @param object 
     */
    public void save(Object object) {
        em.persist(object);
    }
    
}
