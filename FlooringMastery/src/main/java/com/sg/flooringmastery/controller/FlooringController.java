package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FlooringService;
import com.sg.flooringmastery.ui.FlooringView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author tylerbates
 */
public class FlooringController {

    FlooringService service;
    FlooringView view;

    public FlooringController(FlooringService service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean restart = true;
        int menuSelect = 0;

        try {
            loadFiles();
            boolean training = service.getTraining();

            while (restart) {

                menuSelect = view.dispayMenuGetChoice();

                switch (menuSelect) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addAnOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        if (training) {
                            view.inTrainingBanner();
                            break;
                        } else {
                            saveToFile();
                            break;
                        }
                    case 6:
                        if (training) {
                            restart = false;
                            break;
                        } else {
                            saveToFile();
                            restart = false;
                            break;
                        }
                    default:
                        unknownCommand();
                }
            }
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }

    private void displayOrders() {
        LocalDate date = view.getOrderDate();
        date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        List<Order> list = service.getOrdersByDate(date);
        view.displayOrdersByDate(list);
    }

    private void addAnOrder() {
        String num = service.makeOrderNumber(LocalDate.now());
        Order newOrder = view.getOrderInfo(num);
        String product = view.getProductInfo(service.getProductNames());
        String tax = view.getStateInfo(service.getTaxNames());
        newOrder = service.calculateOrder(newOrder, product, tax);
        service.addOrder(newOrder);
        view.displayOrderSummary(newOrder);
        view.createdOrder();
    }

    private void editOrder() {
        Order orderToEdit = null;
        Order original = new Order();

        LocalDate date = view.getOrderDate();
        date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        List<Order> list = service.getOrdersByDate(date);
        view.displayOrdersByDate(list);
        if (list.size() > 0) {
            String orderNum = view.getOrderNum();
            orderToEdit = service.validateOrder(date, orderNum);
            if (orderToEdit == null) {
                view.wrongOrderInfo();
            } else {
                original = orderToEdit;
                orderToEdit = view.getEditedInfo(orderToEdit);
                orderToEdit = service.calculateOrder(orderToEdit,
                        view.editProductInfo(service.getProductNames(), orderToEdit),
                        view.editStateInfo(service.getTaxNames(), orderToEdit));
                
                boolean yes = view.yesOrNo();
                
                if (yes) {
                    service.editOrder(orderToEdit);
                    view.editedOrderComplete();
                } else {
                    service.editOrder(original);
                    view.discardedChanges();
                }
            }
        }
    }

    private void removeOrder() {
        Order orderToEdit = null;

        LocalDate date = view.getOrderDate();
        date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        List<Order> list = service.getOrdersByDate(date);
        view.displayOrdersByDate(list);
        if (list.size() > 0) {
            String orderNum = view.getOrderNum();
            orderToEdit = service.validateOrder(date, orderNum);
            if (orderToEdit == null) {
                view.wrongOrderInfo();
            } else {
                service.removeOrder(orderToEdit);
                view.removeComplete();
            }
        }

    }

    private void saveToFile() throws FlooringPersistenceException {
        service.saveOrderFiles();
        view.savedFiles();
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }

    private void exitMessage() {
        view.thankYou();
    }

    private void loadFiles() throws FlooringPersistenceException {
        service.loadOrderFiles();
        service.loadProducts();
        service.loadTaxes();
    }
}
