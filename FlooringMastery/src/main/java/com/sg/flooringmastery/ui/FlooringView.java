package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author tylerbates
 */
public class FlooringView {

    UserIO io;
    public static final String DATE_FORMAT = "MMddyyyy";

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int dispayMenuGetChoice() {
        io.print("|----------------------------|");
        io.print("|1. Display Orders For A Date|");
        io.print("|2. Add an Order-------------|");
        io.print("|3. Edit an Order------------|");
        io.print("|4. Remove an Order----------|");
        io.print("|5. Save Your Work-----------|");
        io.print("|6. EXIT PROGRAM-------------|");
        io.print("|----------------------------|");
        io.print("");
        int choice = io.readInt("Please enter your choice and hit enter.");
        return choice;
    }

    public void inTrainingBanner() {
        io.print("--You are in training. You cannot do that!--");
        io.readString("Please hit Enter to continue.");
    }

    public LocalDate getOrderDate() {
        return io.readDateTime("What date are you looking for? (" + DATE_FORMAT + ")");
    }

    public String getOrderNum() {
        return io.readString("What order number are you looking for?");
    }

    public void displayOrderSummary(Order order) {
        io.print("-------------------------------------------");
        io.print("");
        io.print("-Order Number: " + order.getOrderNum().substring(8));
        io.print("-Customer Name: " + order.getCustomerName());
        io.print("-State: " + order.getState());
        io.print("-Tax Rate: " + order.getTaxRate());
        io.print("-Product Name: " + order.getProduct());
        io.print("-Area: " + order.getArea());
        io.print("-Product Cost Per Square Foot: " + order.getCostPerSquareFoot());
        io.print("-Labor Cost Per Square Foot: " + order.getLaborCostPerSf());
        io.print("-Product Cost: " + order.getProductCost());
        io.print("-Labor Cost: " + order.getLaborCost());
        io.print("-Tax Total: " + order.getTax());
        io.print("-Total: " + order.getTotal());
        io.print("");
        io.print("-------------------------------------------");

    }

    public void displayOrdersByDate(List<Order> orders) {
        
        if (orders.size() < 1)
            io.print("No orders for this date.");
        
        for (Order currentOrder : orders) {
            io.print("-------------------------------------------");
            io.print("");

            io.print("-Order Number: " + currentOrder.getOrderNum().substring(8));
            io.print("-Customer Name: " + currentOrder.getCustomerName());
            io.print("-State: " + currentOrder.getState());
            io.print("-Tax Rate: " + currentOrder.getTaxRate());
            io.print("-Product Name: " + currentOrder.getProduct());
            io.print("-Area: " + currentOrder.getArea());
            io.print("-Product Cost Per Square Foot: " + currentOrder.getCostPerSquareFoot());
            io.print("-Labor Cost Per Square Foot: " + currentOrder.getLaborCostPerSf());
            io.print("-Product Cost: " + currentOrder.getProductCost());
            io.print("-Labor Cost: " + currentOrder.getLaborCost());
            io.print("-Tax Total: " + currentOrder.getTax());
            io.print("-Total: " + currentOrder.getTotal());
            io.print("");
            io.print("-------------------------------------------");
            io.print("");
        }

        io.readString("Hit enter to continue.");
    }

    public Order getOrderInfo(String orderNum) {
        Order newOrder = new Order();
        LocalDate date = LocalDate.now();
        io.print("-------------------------------------------");
        io.print("--------------Create an Order--------------");
        io.print("-------------------------------------------");
        io.print("");
        newOrder.setOrderNum(orderNum);
        newOrder.setCustomerName(io.readString("What is the name of the Customer?"));
        newOrder.setArea(io.readBigDecimal("What is the area?"));
        newOrder.setDate(date);
        newOrder.setOrderNum(newOrder.getDate().format(DateTimeFormatter.ofPattern("MMddyyyy")) + newOrder.getOrderNum());

        return newOrder;
    }

    public Order getEditedInfo(Order order) {
        String name = io.readString("What is the correct name of the Customer? (Currently: " + order.getCustomerName() + ")");
        if (name.equals("")) {
            order.setCustomerName(order.getCustomerName());
        } else {
            order.setCustomerName(name);
        }
        

        BigDecimal area;
        do {
            area = new BigDecimal(io.readString("What is the correct area? (Currently: " + order.getArea() + ")"));
        } while (area.doubleValue() < 1);
        order.setArea(area);

        return order;
    }

    public boolean yesOrNo() {
        boolean keepGoing;
        boolean returnValue = false;

        do {
            String choice = io.readString("Would you like to commit to these changes/additions? (Yes/No)");
            if (choice.toLowerCase().startsWith("y")) {
                keepGoing = false;
                returnValue = true;
            } else if (choice.toLowerCase().startsWith("n")) {
                keepGoing = false;
                returnValue = false;
            } else {

                keepGoing = true;
            }

        } while (keepGoing);

        return returnValue;
    }

    public String getProductInfo(List<String> listedProducts) {
        String product = "";
        do {
            io.print("What product would you like?");
            listedProducts.stream().forEach(p -> io.print(p));
            String choice = io.readString("Please enter one of the above choices.");
            for (String p : listedProducts) {
                if (p.equalsIgnoreCase(choice)) {
                    return p;
                } else {
                    product = "";
                }
            }
        } while (product.equals(""));
        return null;
    }

    public String getStateInfo(List<String> listedTax) {
        String state = "";
        do {
            io.print("What state will this order go to?");
            listedTax.stream().forEach(t -> io.print(t));
            String choice = io.readString("Please enter one of the above states and hit enter.");
            for (String current : listedTax) {
                if (current.equalsIgnoreCase(choice)) {
                    return current;
                } else {
                    state = "";
                }
            }
        } while (state.equals(""));

        return null;
    }

    public String editProductInfo(List<String> listedProducts, Order order) {
        String product = "";
        String original = order.getProduct();
        String picked;
        do {
            io.print("What product would you like?");
            listedProducts.stream().forEach(p -> io.print(p));
            String choice = io.readString("Please enter one of the above choices.");
            for (String p : listedProducts) {
                if (p.equalsIgnoreCase(choice)) {
                    picked = p;
                    return picked;
                } else if (choice.equals("")) {
                    return original;
                } else {
                    product = "";
                }
            }
        } while (product.equals(""));
        return null;
    }

    public String editStateInfo(List<String> listedTax, Order order) {
        String product = "";
        String original = order.getState();
        String picked;
        do {
            io.print("What product would you like?");
            listedTax.stream().forEach(p -> io.print(p));
            String choice = io.readString("Please enter one of the above choices.");
            for (String p : listedTax) {
                if (p.equalsIgnoreCase(choice)) {
                    picked = p;
                    return picked;
                } else if (choice.equals("")) {
                    return original;
                } else {
                    product = "";
                }
            }
        } while (product.equals(""));
        return null;
    }

    public void displayUnknownCommand() {
        io.print("Unknown Menu Choice.");
        io.readString("Please hit Enter to continue.");
    }

    public void thankYou() {
        io.print("Thank you for using Flooring Mastery!");
    }

    public void displayErrorMessage(String message) {
        io.print("-----ERROR-----");
        io.print(message);
        io.readString("Please hit Enter to continue.");
    }

    public void createdOrder() {
        io.print("--------ORDER CREATED----------");
        io.readString("Please hit Enter to continue.");
        io.print("");
    }

    public void removeComplete() {
        io.print("--------REMOVE COMPLETE---------");
        io.readString("Please hit Enter to continue.");
    }

    public void wrongOrderInfo() {
        io.print("--------ERROR INVALID INFO--------");
        io.readString("Please hit Enter to continue");
    }

    public void editedOrderComplete() {
        io.print("--------EDIT COMPLETE--------");
        io.readString("Please hit Enter to continue.");
    }

    public void discardedChanges() {
        io.print("-------DISCARDED CHANGES---------");
        io.readString("Please hit Enter to continue.");
    }

    public void savedFiles() {
        io.print("--------SAVED CHANGES--------");
        io.readString("Please hit Enter to continue.");
    }

}
