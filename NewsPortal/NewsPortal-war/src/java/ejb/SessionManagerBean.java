/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author bikas
 * 
 * A very simple session bean used just to count number of active users
 * This a singleton session bean
 */
@Singleton
@LocalBean
@WebListener
public class SessionManagerBean implements HttpSessionListener {

    //variable used to track number of active users
    private static int counter = 0;

    /**
     * when a session is created counter is increased
     * @param se 
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        counter++;
    }

    /**
     * counter decreased when session destroyed
     * @param se 
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        counter--;
    }

    /**
     * 
     * @return number of active users
     */
    public int getActiveSessionsCount() {
        return counter;
    }
    
 
}
