/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bikas.ejb;

import java.io.File;
import javax.ejb.embeddable.EJBContainer;
import java.util.Map;
import java.util.HashMap;
import bikas.entity.Products;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bikas
 */
public class TransactionsTest {

    private static EJBContainer container;

    public TransactionsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("build/jar"));
        container = EJBContainer.createEJBContainer(properties);
        System.out.println("Opening the container");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();
        System.out.println("Closing the container");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    
    /**
     * Test of checkIfQueryExists method, of class Transactions.
     */
    @Test
    public void testCheckIfQueryExists() throws Exception {
        System.out.println("checkIfQueryExists");
        String query = "ITEM1";
        TransactionsStateful instance1 = (TransactionsStateful)container.getContext().lookup("java:global/classes/TransactionsStateful");
        int expResult = 1;
        int result = instance1.checkIfQueryExists(query);
        assertEquals(expResult, result);

        query = "ITEM2";
        TransactionsStateful instance2 = (TransactionsStateful)container.getContext().lookup("java:global/classes/TransactionsStateful");
        expResult = 1;
        result = instance2.checkIfQueryExists(query);
        assertEquals(expResult, result);

        query = "GARBAGE";
        TransactionsStateful instance3 = (TransactionsStateful)container.getContext().lookup("java:global/classes/TransactionsStateful");
        expResult = 0;
        result = instance3.checkIfQueryExists(query);
        assertEquals(expResult, result);
    }

   

}