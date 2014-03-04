/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service.Help;

/**
 *
 * @author ir
 */
public class TesteNullandEmpty {

    public TesteNullandEmpty() {
    }

    public boolean TesteNull(Object testString) {

        if ( testString == null ) {
            System.out.println(" string null --- > ");
            return false;
        } else {
            System.out.println(" string not null --- > ");
            return true;
        }

    }
    
    public boolean TesteEmpty(Object testString) {

        if (testString.toString().isEmpty() ) {
            System.out.println(" string empty --- > ");
            return false;
        } else {
            System.out.println(" string not empty --- > ");
            return true;
        }

    }
    
  
    
    
    
    
}
