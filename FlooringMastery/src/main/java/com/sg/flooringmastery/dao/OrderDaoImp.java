package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author tylerbates
 */
public class OrderDaoImp implements OrderDao {

    public static final String DELIMITER = ",";
    public Map<String, Order> fileOrders = new HashMap<>();
    public static String HEADER;
    public static final String DATE_FORMAT = "MMddyyyy";

    @Override
    public void loadAllOrders() throws FlooringPersistenceException {
        loadAllFiles();
    }

    @Override
    public Order getOrder(String dateOrderNum) {
        Order order = fileOrders.get(dateOrderNum);
        return order;
    }

    @Override
    public Order addOrder(Order newOrder) {
        fileOrders.put(newOrder.getOrderNum(), newOrder);
        return newOrder;
    }

    @Override
    public Order editOrder(Order editedOrder) {
        fileOrders.replace(editedOrder.getOrderNum(), editedOrder);
        return editedOrder;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        return fileOrders.values()
                .stream()
                .filter(d -> d.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Order removeOrder(Order order) {
        fileOrders.remove(order.getOrderNum());
        return order;
    }

    @Override
    public void saveToFile() throws FlooringPersistenceException {
        writeToOrderFile();
    }

    public void loadAllFiles() throws FlooringPersistenceException {
        Scanner scan;

        File folder = new File("data/");
        File[] listedFiles = folder.listFiles();

        for (File currentFile : listedFiles) {

            try {
                scan = new Scanner(new BufferedReader(new FileReader(currentFile)));
            } catch (FileNotFoundException e) {
                throw new FlooringPersistenceException("Could not load orders into memory.", e);
            }

            String orderDate = currentFile.getName().substring(7, 15);
            LocalDate newDate = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MMddyyyy"));
            String currentLine;
            String[] currentTokens;
            
                HEADER = scan.nextLine();
            
            while (scan.hasNextLine()) {
                currentLine = scan.nextLine();
                currentTokens = currentLine.split(DELIMITER);

                Order currentOrder = new Order();
                currentOrder.setOrderNum(orderDate + currentTokens[0]);
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                currentOrder.setProduct(currentTokens[4]);
                currentOrder.setArea(new BigDecimal(currentTokens[5]));
                currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentOrder.setLaborCostPerSf(new BigDecimal(currentTokens[7]));
                currentOrder.setProductCost(new BigDecimal(currentTokens[8]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotal(new BigDecimal(currentTokens[11]));
                currentOrder.setDate(newDate);
                fileOrders.put(currentOrder.getOrderNum(), currentOrder);

            }
            scan.close();

        }
    }

    private void writeToOrderFile() throws FlooringPersistenceException {
        PrintWriter out = null;
        
        List<Order> orderList = new ArrayList<>(fileOrders.values());
        
        List<LocalDate> dateList = getDates(orderList);
        
        File folder = new File("data/");
        File[] listedFiles = folder.listFiles();
        
        for (File current: listedFiles){
            current.delete();
        }
        
        for (LocalDate date : dateList) {
            
            String newDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
            
            List<Order> orderListbyDate = getOrdersByDate(date);
            try {
                out = new PrintWriter(new FileWriter(new File("data/" ,"Orders_" + newDate  + ".txt")));
            } catch (IOException e) {
                throw new FlooringPersistenceException("Could not update order data.", e);
            }
            
            out.println(HEADER);
            
            for (Order currentOrder: orderListbyDate){
            out.println(currentOrder.getOrderNum().substring(8) + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getProduct() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getCostPerSquareFoot() + DELIMITER
                    + currentOrder.getLaborCostPerSf() + DELIMITER
                    + currentOrder.getProductCost() + DELIMITER
                    + currentOrder.getLaborCost() + DELIMITER
                    + currentOrder.getTax() + DELIMITER
                    + currentOrder.getTotal());
            out.flush();
            }
        }
        out.close();
    }

    private List<LocalDate> getDates(List<Order> orderList) {
        List<LocalDate> dateList = new ArrayList<>();
        
        for (Order current: orderList){
            dateList.add(current.getDate());
        }
        return dateList;
    }

    @Override
    public Map<String, Order> getAllOrdersList() {
        return fileOrders;
    }

}
