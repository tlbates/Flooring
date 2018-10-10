/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tylerbates
 */
public class FlooringServiceImpTest {
    
    FlooringService service;
    
    public FlooringServiceImpTest() {
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    service = 
        ctx.getBean("service", FlooringServiceImp.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FlooringPersistenceException {
        service.loadOrderFiles();
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateOrder method, of class FlooringServiceStubImp.
     */
    @Test
    public void testCalculateOrder() {
        Order testOrder = service.getOrder("091520181");
        
        testOrder = service.calculateOrder(testOrder, "Tile", "OH");
        
        assertEquals(new BigDecimal("3.50"),testOrder.getCostPerSquareFoot());
        assertEquals(new BigDecimal("4.15"),testOrder.getLaborCostPerSf());
        assertEquals("Tile",testOrder.getProduct());
        assertEquals("OH",testOrder.getState());
        assertEquals(new BigDecimal("6.25"),testOrder.getTaxRate());
        assertEquals(new BigDecimal("17.50"),testOrder.getProductCost());
        assertEquals(new BigDecimal("20.75"),testOrder.getLaborCost());
        assertEquals(new BigDecimal("239.06"),testOrder.getTax());
        assertEquals(new BigDecimal("277.31"),testOrder.getTotal());
    }

    /**
     * Test of makeOrderNumber method, of class FlooringServiceStubImp.
     */
    @Test
    public void testMakeOrderNumber() {
        LocalDate date = LocalDate.of(2018, Month.SEPTEMBER, 15);
        String test = service.makeOrderNumber(date);
        
        LocalDate date2 = LocalDate.of(2234, Month.SEPTEMBER, 28);
        String test2 = service.makeOrderNumber(date2);
        
        
        assertEquals("2", test);
        assertEquals("1", test2);
    }

    /**
     * Test of validateOrder method, of class FlooringServiceStubImp.
     */
    @Test
    public void testValidateOrder() {
        LocalDate date = LocalDate.of(2018, Month.SEPTEMBER, 15);
        String orderNum = "1";
        assertNotNull(service.validateOrder(date, orderNum));
        
        LocalDate date2 = LocalDate.of(2345, Month.OCTOBER, 4);
        assertNull(service.validateOrder(date2, orderNum));
    }
    
    @Test
    public void testGetProductNames() {
        List<String> list = service.getProductNames();
        assertEquals(2,list.size());
    }
    
    @Test
    public void testGetTaxNames() {
        List<String> list = service.getTaxNames();
        assertEquals(2,list.size());
    }
}
