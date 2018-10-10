package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.TaxDao;
import com.sg.flooringmastery.dao.TrainingDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author tylerbates
 */
public class FlooringServiceImp implements FlooringService {

    OrderDao orderDao;
    ProductDao productDao;
    TaxDao taxDao;
    TrainingDao trainDao;

    public FlooringServiceImp(OrderDao myOrderDao,
            TaxDao myTaxDao,
            ProductDao myProductDao,
            TrainingDao myTrainingDao) {
        this.orderDao = myOrderDao;
        this.productDao = myProductDao;
        this.taxDao = myTaxDao;
        this.trainDao = myTrainingDao;
    }

    //SERVICE LAYER
    @Override
    public Order calculateOrder(Order newOrder, String product, String tax) {
        newOrder.setCostPerSquareFoot((getProduct(product).getCostPerSquareFoot()));
        newOrder.setLaborCostPerSf((getProduct(product).getLaborCostPerSf()));
        newOrder.setProduct(getProduct(product).getType());
        newOrder.setState(getTax(tax).getState());
        newOrder.setTaxRate((getTax(tax).getTaxRate()));
        newOrder.setProductCost((newOrder.getCostPerSquareFoot().multiply(newOrder.getArea())));
        newOrder.setLaborCost((newOrder.getLaborCostPerSf().multiply(newOrder.getArea())));
        newOrder.setTax(((newOrder.getProductCost().add(newOrder.getLaborCost()).multiply(newOrder.getTaxRate())).setScale(2, RoundingMode.FLOOR)));
        newOrder.setTotal((newOrder.getTax().add(newOrder.getProductCost().add(newOrder.getLaborCost())).setScale(2, RoundingMode.FLOOR)));
        return newOrder;
    }

    @Override
    public String makeOrderNumber(LocalDate date) {
        List<Order> list = getOrdersByDate(date);
        String one = "1";
        if (list.size() < 1) {
            return one;
        } else {
            Order max = Collections.max(list, Comparator.comparing(num -> num.getOrderNum().substring(8)));
            int newNum = Integer.parseInt(max.getOrderNum().substring(8));
            newNum++;
            String generated = Integer.toString(newNum);
            return generated;
        }
    }

    @Override
    public Order validateOrder(LocalDate date, String orderNum) {
        String newdate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String dateOrderNum = newdate + orderNum;
        Order validation = getOrder(dateOrderNum);
        return validation;
    }

    //ORDER DAO
    @Override
    public Order getOrder(String dateOrderNum) {
        return orderDao.getOrder(dateOrderNum);
    }

    @Override
    public Order addOrder(Order newOrder) {
        return orderDao.addOrder(newOrder);
    }

    @Override
    public Order editOrder(Order editedOrder) {
        return orderDao.editOrder(editedOrder);
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        return new ArrayList<>(orderDao.getOrdersByDate(date));
    }
    
    @Override
    public Order removeOrder(Order order) {
        return orderDao.removeOrder(order);
    }

    @Override
    public void loadOrderFiles() throws FlooringPersistenceException {
        orderDao.loadAllOrders();
    }

    @Override
    public void saveOrderFiles() throws FlooringPersistenceException {
        orderDao.saveToFile();
    }

    //TAX DAO
    @Override
    public Tax getTax(String name) {
        return taxDao.getTax(name);
    }

    @Override
    public List<Tax> getAllTaxes() {
        return new ArrayList<>(taxDao.getAllTaxes());
    }
    
    @Override
    public List<String> getTaxNames(){
        List<Tax> list = new ArrayList<>(getAllTaxes());
        List<String> taxNames = new ArrayList<>();
        list.forEach((t) -> {
            taxNames.add(t.getState());
        });
        return taxNames;
    }

    @Override
    public void loadTaxes() throws FlooringPersistenceException {
        taxDao.loadTaxes();
    }

    //PRODUCT DAO
    @Override
    public Product getProduct(String name) {
        return productDao.getProduct(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDao.getAllProducts());
    }
    
    @Override
    public List<String> getProductNames(){
        List<Product> list = new ArrayList<>(getAllProducts());
        List<String> productNames = new ArrayList<>();
        list.forEach((p) -> {
            productNames.add(p.getType());
        });
        return productNames;
    }

    @Override
    public void loadProducts() throws FlooringPersistenceException {
        productDao.loadProducts();
    }

    //TRAINING DAO
    public boolean getTraining() throws FlooringPersistenceException {
        return trainDao.getIfTraining();
    }

}
