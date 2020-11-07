package org.nabuage.blog.message;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.nabuage.blog.helper.TomEEContainer;
import org.nabuage.blog.persistence.ContactMessageEntity;

/**
 *
 * @author George
 */
public class ContactMessageBeanTest {
    
    private ContactMessageBean contactMessageBean;
    private String emailAddress;
    private String name;
    private String message;
    
    public ContactMessageBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() throws NamingException, SQLException {
        DataSource dataSource = TomEEContainer.getInstance().getDataSource();
        dataSource.getConnection().createStatement().execute("DELETE FROM contactmessage");
        dataSource.getConnection().createStatement().execute("UPDATE sequencetable SET sequencecount = 1 WHERE sequencename = 'contactmessageid'");
    }
    
    @Before
    public void setUp() throws NamingException {
        contactMessageBean = (ContactMessageBean) TomEEContainer.getInstance().getEJBContainerContextLookup("ContactMessageBean");
        emailAddress = "george@nabuage.org";
        name = "George";
        message = "Contact Message";
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class ContactMessageBean.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        ContactMessageEntity result = contactMessageBean.create(emailAddress, name, message);
        assertEquals(true, result != null && result.getId() != null && result.getDateAdded() != null);
    }
    
}
