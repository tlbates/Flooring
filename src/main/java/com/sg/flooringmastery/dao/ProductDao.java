/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author tylerbates
 */
public interface ProductDao {
    
    public Product getProduct(String name);
    
    public List<Product> getAllProducts();
    
    public void loadProducts() throws FlooringPersistenceException ;
}
