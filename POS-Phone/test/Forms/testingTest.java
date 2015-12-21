/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Forms;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ARDHA
 */
public class testingTest {
    
    public testingTest() {
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
     * Test of getNamaBarang method, of class testing.
     */
    @Test
    public void testGetNamaBarang() {
       
        testing instance = new testing();
        String expResult = "Nama Barang :HP.";
        String result = instance.getNamaBarang("HP");
        assertEquals(expResult, result);
     
    }
    
}
