/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
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
public class ProductDaoStubImpTest {
    
    ProductDao dao;
    
    public ProductDaoStubImpTest() {
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    dao = 
        ctx.getBean("productStub", ProductDaoStubImp.class);
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
     * Test of getProduct method, of class ProductDaoStubImp.
     */
    @Test
    public void testGetProduct() {
        assertNotNull(dao.getProduct("Tile"));
    }

    /**
     * Test of getAllProducts method, of class ProductDaoStubImp.
     */
    @Test
    public void testGetAllProducts() {
        assertEquals(2, dao.getAllProducts().size());
    }

}
