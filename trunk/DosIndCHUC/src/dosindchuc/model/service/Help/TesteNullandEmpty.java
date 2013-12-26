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

    public boolean TesteNullandEmpty(Object testString) {

        if (testString == null ) {
            return false;
        } else {
            return true;
        }

    }
}
