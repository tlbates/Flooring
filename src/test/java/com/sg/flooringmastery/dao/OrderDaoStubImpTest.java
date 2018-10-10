/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
public class OrderDaoStubImpTest {
    
    OrderDao dao;
    
    public OrderDaoStubImpTest() {
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    dao = 
        ctx.getBean("orderStub", OrderDaoStubImp.class);
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
     * Test of getOrder method, of class OrderDaoStubImp.
     */
    @Test
    public void testGetOrder() {
        assertNotNull(dao.getOrder("091520181"));
    }

    /**
     * Test of addOrder method, of class OrderDaoStubImp.
     */
    @Test
    public void testAddOrder() {
        Map<String, Order> allOrders = new HashMap<>(dao.getAllOrdersList());
        assertEquals(1,allOrders.size());
        
        Order newOrder = new Order();
        newOrder.setCustomerName("Fred");
        dao.addOrder(newOrder);
        assertEquals(2, dao.getAllOrdersList().size());
    }

    /**
     * Test of editOrder method, of class OrderDaoStubImp.
     */
    @Test
    public void testEditOrder() {
        assertEquals(1, dao.getAllOrdersList().size());
        
        Order edited = new Order();
        edited.setCustomerName("Vinny");
        edited.setOrderNum("091520181");
        dao.editOrder(edited);
        
        assertEquals(1, dao.getAllOrdersList().size());
    }

    /**
     * Test of getOrdersByDate method, of class OrderDaoStubImp.
     */
    @Test
    public void testGetOrdersByDate() {
        Order diffDate = new Order();
        diffDate.setCustomerName("Hermione");
        diffDate.setDate(LocalDate.now());
        dao.addOrder(diffDate);
        
        assertEquals(2, dao.getAllOrdersList().size());
        assertEquals(1, dao.getOrdersByDate(LocalDate.now()).size());
    }

    /**
     * Test of removeOrder method, of class OrderDaoStubImp.
     */
    @Test
    public void testRemoveOrder() {
        Order yes = new Order();
        yes.setCustomerName("Hermione");
        dao.addOrder(yes);
        
        assertEquals(2, dao.getAllOrdersList().size());
        dao.removeOrder(yes);
        assertEquals(1, dao.getAllOrdersList().size());
    }

}
