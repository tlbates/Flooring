/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tylerbates
 */
public interface OrderDao {
    
    public void loadAllOrders() throws FlooringPersistenceException;
    
    public Order getOrder(String orderNum);
    
    public Order addOrder(Order newOrder);
    
    public Order editOrder(Order editedOrder);
    
    public Order removeOrder(Order order);
    
    public List<Order> getOrdersByDate(LocalDate date);
    
    public void saveToFile() throws FlooringPersistenceException;
    
    public Map<String, Order> getAllOrdersList();
       
}
