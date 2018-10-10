package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author tylerbates
 */
public class Order {

    private String orderNum;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private BigDecimal area;

    private String product;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSf;
    
    private LocalDate date;

    private BigDecimal productCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;

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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getProductCost() {
        return productCost;
    }

    public void setProductCost(BigDecimal productCost) {
        this.productCost = productCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
