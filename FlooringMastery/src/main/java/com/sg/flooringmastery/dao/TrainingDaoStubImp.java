
package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author tylerbates
 */
public class TrainingDaoStubImp implements TrainingDao {
    
    public static final String TRAINING_FILE = "Training.txt";
    boolean Training;
    
    public TrainingDaoStubImp(){
      
    }
    
    @Override
    public boolean getIfTraining() throws FlooringPersistenceException{
        return loadIfTraining();
    }
    
    public boolean loadIfTraining() throws FlooringPersistenceException {
        Scanner scan;
        boolean training = false;
        
        try {
            scan = new Scanner(new BufferedReader(new FileReader(TRAINING_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not load mode of application.", e);
        }

        String currentLine = scan.nextLine();
        
        if (currentLine.contains("Production")){
            training = false;
        } else {
            training = true;
        }
        
        return training;
    }
    
}
