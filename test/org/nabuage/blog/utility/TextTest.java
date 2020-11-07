/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TextTest {
    
    public TextTest() {
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
     * Test of getInstance method, of class Text.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Text result = Text.getInstance();
        assertEquals(true, result != null);
    }

    /**
     * Test of incrementLastNumber method, of class Text.
     */
    @Test
    public void testIncrementLastNumber() {
        System.out.println("incrementLastNumber");
        String text = "junit-name-1";
        String separator = "-";
        String result = Text.getInstance().incrementLastNumber(text, separator);
        assertEquals(true, result.equals("junit-name-2"));
    }
    
}
