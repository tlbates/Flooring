/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author tylerbates
 */
public interface FlooringService {
    
    //SERVICE LAYER
    public Order calculateOrder(Order newOrder, String product, String tax);
    
    public String makeOrderNumber(LocalDate date);
    
    public Order validateOrder(LocalDate date, String orderNum);
    
    
    //ORDER DAO
    public Order getOrder(String dateOrderNum);
    
    public Order addOrder(Order newOrder);
    
    public Order editOrder(Order editedOrder);
    
    public List<Order> getOrdersByDate(LocalDate date);
    
    public Order removeOrder(Order order);
    
    public void loadOrderFiles() throws FlooringPersistenceException;
    
    public void saveOrderFiles() throws FlooringPersistenceException;
    
    
    //TAX DAO
    public Tax getTax(String name);
    
    public List<Tax> getAllTaxes();
    
    public List<String> getTaxNames();
    
    public void loadTaxes() throws FlooringPersistenceException;
    
    
    //PRODUCT DAO
    public Product getProduct(String name);
    
    public List<Product> getAllProducts();
    
    public void loadProducts() throws FlooringPersistenceException;
    
    public List<String> getProductNames();
    
    //TRAINING DAO
    public boolean getTraining() throws FlooringPersistenceException;
}
