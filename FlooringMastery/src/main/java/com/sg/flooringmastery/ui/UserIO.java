/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author tylerbates
 */
public interface UserIO {

    void print(String message);

    double readDouble(String promptMsg);

    double readDouble(String promptMsg, double minVal, double maxVal);

    float readFloat(String promptMsg);

    float readFloat(String promptMsg, float minVal, float maxVal);

    int readInt(String promptMsg);

    int readInt(String promptMsg, int minVal, int maxVal);

    long readLong(String promptMsg);

    long readLong(String promptMsg, long minVal, long maxVal);

    String readString(String promptMsg);
    
    BigDecimal readBigDecimal(String promptMsg);
    
//    BigDecimal readBigDecimal(String promptMsg, BigDecimal minVal, BigDecimal maxVal);
    
    LocalDate readDateTime(String promptMsg);
    
}
