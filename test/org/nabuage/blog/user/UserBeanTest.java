package org.nabuage.blog.user;

import java.sql.SQLException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.nabuage.blog.helper.TomEEContainer;
import org.nabuage.blog.persistence.UserEntity;

/**
 *
 * @author George
 */
public class UserBeanTest {
    
    private UserBean userBean;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String salt;
    private String emailAddress;
    private String displayName;
    
    public UserBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() throws NamingException, SQLException {
        DataSource dataSource = TomEEContainer.getInstance().getDataSource();
        dataSource.getConnection().createStatement().execute("DELETE FROM userprofile");
        dataSource.getConnection().createStatement().execute("UPDATE sequencetable SET sequencecount = 1");
    }
    
    @Before
    public void setUp() throws NamingException {
        userBean = (UserBean)TomEEContainer.getInstance().getEJBContainerContextLookup("UserBean");
        
        firstName = "JUnit";
        lastName = "JUnit";
        username = "junit";
        password = "junit";
        salt = "junit";
        emailAddress = "junit@nabuage.org";
        displayName = "JUnit";
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class UserBean.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        
        UserEntity result = userBean.create(emailAddress, username, password, UserEntity.Type.USER, firstName, lastName, displayName);
        
        assertEquals(true, result != null && result.getId() != null && result.getDateAdded() != null);
    }

    /**
     * Test of update method, of class UserBean.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        
        UserEntity userEntity = userBean.create(emailAddress, username, password, UserEntity.Type.USER, firstName, lastName, displayName);
        
        String updateText = "Update ";
        String firstNameUpdate = updateText + firstName;
        String lastNameUpdate = updateText + lastName;
        String usernameUpdate = updateText + username;
        String emailAddressUpdate = updateText + emailAddress;
        String displayNameUpdate = updateText + displayName;
        
        UserEntity result = userBean.update(userEntity.getId(), emailAddressUpdate, usernameUpdate, UserEntity.Type.USER, firstNameUpdate, lastNameUpdate, displayNameUpdate);
               
        assertEquals(true, result != null && 
                result.getId().equals(userEntity.getId()) &&
                result.getDateModified() != null &&
                result.getFirstName().equals(firstNameUpdate) &&
                result.getLastName().equals(lastNameUpdate) &&
                result.getUsername().equals(usernameUpdate) &&
                result.getEmailAddress().equals(emailAddressUpdate) &&
                result.getDisplayName().equals(displayNameUpdate) &&
                result.getType() == UserEntity.Type.USER);
    }

    /**
     * Test of delete method, of class UserBean.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        Long deleteId;
        
        UserEntity userEntity = userBean.create(emailAddress, username, password, UserEntity.Type.USER, firstName, lastName, displayName);
        
        deleteId = userEntity.getId();
        
        userBean.delete(deleteId);
        
        UserEntity result = userBean.findById(deleteId);
        
        assertEquals(true, result == null);
    }

    /**
     * Test of findById method, of class UserBean.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        
        UserEntity userEntity = userBean.create(emailAddress, username, password, UserEntity.Type.USER, firstName, lastName, displayName);
        
        UserEntity result = userBean.findById(userEntity.getId());
        
        assertEquals(true, result != null && result.getId().equals(userEntity.getId()));
    }

    /**
     * Test of updatePassword method, of class UserBean.
     */
    @Test
    public void testUpdatePassword() throws Exception {
        System.out.println("updatePassword");
        
        UserEntity user = userBean.create(emailAddress, username, password, UserEntity.Type.USER, firstName, lastName, displayName);
        
        UserEntity result = userBean.updatePassword(user.getId(), password, password);
        
        assertEquals(true, result != null);
    }
    
}
