package com.sg.flooringmastery.dao;

import static com.sg.flooringmastery.dao.OrderDaoImp.DELIMITER;
import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author tylerbates
 */
public class ProductDaoStubImp implements ProductDao {

    public Map<String, Product> products = new HashMap<>();
    public static String HEADER;
    public static final String PRODUCT_FILE = "Products.txt";
    
    public ProductDaoStubImp(){
        Product tile = new Product("Tile");
        tile.setCostPerSquareFoot(new BigDecimal("3.50"));
        tile.setLaborCostPerSf(new BigDecimal("4.15"));
        products.put(tile.getType(), tile);
        
        Product floor = new Product("Floor");
        products.put(floor.getType(), floor);
    }

    @Override
    public Product getProduct(String name) {
        return products.get(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void loadProducts() throws FlooringPersistenceException {
        loadProductsFromFile();
    }

    private void loadProductsFromFile() throws FlooringPersistenceException {
        Scanner scan;

        try {
            scan = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not bring products into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        
            HEADER = scan.nextLine();
        while (scan.hasNextLine()) {
            currentLine = scan.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Product current = new Product(currentTokens[0]);
            current.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            current.setLaborCostPerSf(new BigDecimal(currentTokens[2]));
            
            products.put(current.getType(), current);
        }
        
    }

}
