/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ir
 */
public class help_entities {

    
    public help_entities() {
    
    }
   
     public String currDate () {

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        return ft.format(dNow);
              
     }
    
         
}
