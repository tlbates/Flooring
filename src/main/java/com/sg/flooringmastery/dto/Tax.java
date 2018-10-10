
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author tylerbates
 */
public class Tax {
    private String state;
    private BigDecimal taxRate;
    
    public Tax(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    
}
