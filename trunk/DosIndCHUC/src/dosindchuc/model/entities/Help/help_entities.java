/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities.Help;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ir
 */
public class help_entities {

    
     
     public String currDate () {

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        return ft.format(dNow);
              
     }
    
     public String currDateTime () {

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH-mm-ss");
        return ft.format(dNow);
              
     }
     
         
}
