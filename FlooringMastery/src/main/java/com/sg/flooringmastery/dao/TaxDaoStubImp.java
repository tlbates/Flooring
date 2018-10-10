
package com.sg.flooringmastery.dao;

import static com.sg.flooringmastery.dao.OrderDaoImp.DELIMITER;
import com.sg.flooringmastery.dto.Tax;
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
public class TaxDaoStubImp implements TaxDao {
    
    public Map<String, Tax> taxes = new HashMap<>();
    public static String HEADER;
    public static final String TAX_FILE = "Taxes.txt";

    public TaxDaoStubImp(){
        Tax current = new Tax("OH");
        current.setTaxRate(new BigDecimal("6.25"));
        
        taxes.put(current.getState(), current);
        
        Tax next = new Tax("PA");
        next.setTaxRate(new BigDecimal("4.50"));
        
        taxes.put(next.getState(), next);
    }
    
    @Override
    public Tax getTax(String name) {
        return taxes.get(name);
    }

    @Override
    public List<Tax> getAllTaxes() {
        return new ArrayList<>(taxes.values());
    }

    @Override
    public void loadTaxes() throws FlooringPersistenceException {
        loadTaxesFromFile();
    }

    private void loadTaxesFromFile() throws FlooringPersistenceException {
        Scanner scan;

        try {
            scan = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not bring products into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        
            HEADER = scan.nextLine();
        while (scan.hasNextLine()) {
            currentLine = scan.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Tax current = new Tax(currentTokens[0]);
            current.setTaxRate(new BigDecimal(currentTokens[1]));
            
            taxes.put(current.getState(), current);
        }
        
    }
}
