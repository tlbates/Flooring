
package com.sg.flooringmastery.main;

import com.sg.flooringmastery.controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tylerbates
 */
public class App {
    
    public static void main(String[] args) {
        
        ApplicationContext context = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller = context.getBean("controller", FlooringController.class);
        controller.run();
        
    }
}
