
package com.sg.flooringmastery.dao;

/**
 *
 * @author tylerbates
 */
public class FlooringPersistenceException extends Exception {
    
    public FlooringPersistenceException(String message) {
        super(message);
    }
    
    public FlooringPersistenceException(String message, Throwable cause){
        super(message, cause);
    }
}
