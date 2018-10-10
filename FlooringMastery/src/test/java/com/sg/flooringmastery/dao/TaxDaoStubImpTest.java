/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tylerbates
 */
public class TaxDaoStubImpTest {
    
    TaxDao dao;
    
    public TaxDaoStubImpTest() {
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    dao = 
        ctx.getBean("taxStub", TaxDaoStubImp.class);
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
     * Test of getTax method, of class TaxDaoStubImp.
     */
    @Test
    public void testGetTax() {
        assertNotNull(dao.getTax("PA"));
    }

    /**
     * Test of getAllTaxes method, of class TaxDaoStubImp.
     */
    @Test
    public void testGetAllTaxes() {
        assertEquals(2, dao.getAllTaxes().size());
    }

}
