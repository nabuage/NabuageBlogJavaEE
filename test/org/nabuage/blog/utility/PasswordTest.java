package org.nabuage.blog.utility;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author George
 */
public class PasswordTest {
    
    public PasswordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generateSalt method, of class Password.
     */
    @Test
    public void testGenerateSalt() throws Exception {
        System.out.println("generateSalt");
        
        Password instance = new Password();
        
        String result = instance.generateSalt();
        
        System.out.println(result);
        System.out.println(instance.saltPassword("george", result));
        
        assertEquals(true, !result.equals(""));
    }

    /**
     * Test of saltPassword method, of class Password.
     */
    @Test
    public void testSaltPassword() throws Exception {
        System.out.println("saltPassword");
        String password = "junit";
        String salt = "";
        Password instance = new Password();
        salt = instance.generateSalt();
        
        String result = instance.saltPassword(password, salt);
        
        assertEquals(true, !result.equals(""));
    }

    /**
     * Test of valid method, of class Password.
     */
    @Test
    public void testValid() throws Exception {
        System.out.println("valid");
        
        String password = "junit";
        String salt = "";
        String saltedPassword = "";
        Password instance = new Password();
        salt = instance.generateSalt();
        
        saltedPassword = instance.saltPassword(password, salt);
        
        boolean result = instance.valid(password, saltedPassword, salt);
        
        assertEquals(true, result);
    }
    
    /**
     * Test of valid method, of class Password.
     */
    @Test
    public void testValidWithConstant() throws Exception {
        System.out.println("validWithConstant");
        
        String password = "junit";
        String salt = "BcNdsMVhf7+FQUyEPJruvye//WHekuivCvz6Z4b5iNlfXOdRlitLEps/7iNoEde+";
        String saltedPassword = "uKxK9eTN6X6XqMEk61ME9EDZTe1XHZI372sH1zu1+aWYj/R0bB1D3NdWRYNKh/csDy2XuiML+9VhNtrPtz3Gdw==";
        
        Password instance = new Password();
        
        boolean result = instance.valid(password, saltedPassword, salt);
        
        assertEquals(true, result);
    }
    
    /**
     * Test of valid method, of class Password.
     */
    @Test
    public void testValidWithWrongPassword() throws Exception {
        System.out.println("validWithWrongPassword");
        
        String password = "junitWRONG";
        String salt = "BcNdsMVhf7+FQUyEPJruvye//WHekuivCvz6Z4b5iNlfXOdRlitLEps/7iNoEde+";
        String saltedPassword = "uKxK9eTN6X6XqMEk61ME9EDZTe1XHZI372sH1zu1+aWYj/R0bB1D3NdWRYNKh/csDy2XuiML+9VhNtrPtz3Gdw==";
        
        Password instance = new Password();
        
        boolean result = instance.valid(password, saltedPassword, salt);
        
        assertEquals(false, result);
    }
    
    /**
     * Test of valid method, of class Password.
     */
    @Test
    public void testValidWithWrongSaltedPassword() throws Exception {
        System.out.println("validWithWrongSaltedPassword");
        
        String password = "junit";
        String salt = "BcNdsMVhf7+FQUyEPJruvye//WHekuivCvz6Z4b5iNlfXOdRlitLEps/7iNoEde+";
        String saltedPassword = "WRONGuKxK9eTN6X6XqMEk61ME9EDZTe1XHZI372sH1zu1+aWYj/R0bB1D3NdWRYNKh/csDy2XuiML+9VhNtrPtz3Gdw==";
        
        Password instance = new Password();
        
        boolean result = instance.valid(password, saltedPassword, salt);
        
        assertEquals(false, result);
    }
    
}
