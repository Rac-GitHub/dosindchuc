/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao.Help;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ir
 */
public class DaoExceptions extends RuntimeException {

    public DaoExceptions() {
 //       super();
    }

    public DaoExceptions(String message, Class className, SQLException cause) {
        JOptionPane.showMessageDialog(null, message + "\n\nClass: " + className.getName() + "\nError:"  + cause.getErrorCode() + 
                ", State: " + cause.getSQLState() + "\nMessage: " + cause.getMessage() + "\n\n");
    }

    public DaoExceptions(String message, Class className) {
  //      super(message);
        JOptionPane.showMessageDialog(null, message + "\nClass: " + className.getName() + "\n\n");
    }

    public DaoExceptions(SQLException cause) {
  //      super(cause); 
        JOptionPane.showMessageDialog(null, "Error: "  + cause.getErrorCode() + "\nState: " + cause.getSQLState()
                + "\nMessage: " + cause.getMessage() + "\n\n");
    }
}
