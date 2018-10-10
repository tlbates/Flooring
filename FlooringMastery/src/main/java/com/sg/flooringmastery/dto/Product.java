
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author tylerbates
 */
public class Product {
    private String type;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSf;
    
    public Product(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSf() {
        return laborCostPerSf;
    }

    public void setLaborCostPerSf(BigDecimal laborCostPerSf) {
        this.laborCostPerSf = laborCostPerSf;
    }
    
}
