/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author tylerbates
 */
public interface TaxDao {
    
    public Tax getTax(String name);
    
    public List<Tax> getAllTaxes();
    
    public void loadTaxes() throws FlooringPersistenceException;
    
}
