/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service.Help;

import java.util.Calendar;


    
    
    
    
/**
 *
 * @author ir
 */
public class YearMonthAndTrimester {
    
    Calendar c = Calendar.getInstance();

    public YearMonthAndTrimester() {
    }

    public int Year() {

        return c.get(Calendar.YEAR);

    }

    public int Month() {

        c.add(Calendar.MONTH, -1);

        return c.get(Calendar.MONTH);

    }

    public int Trimester() {

        c.add(Calendar.MONTH, -4);
        
        System.out.println(" Dentro do trimester ---> " + c.get(Calendar.MONTH));
        

        //return (c.get(Calendar.MONTH) / 3 );
        return ( c.get(Calendar.MONTH) - 1 );

    }

    
    
    
}
