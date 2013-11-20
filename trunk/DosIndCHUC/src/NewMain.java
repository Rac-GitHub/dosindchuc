
import dosindchuc.UI.swing.MainFrm;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ir
 */
public class NewMain {
  
  
 /**
     * @param args the command line arguments
     */
      public static void main(String args[]) {
      
          /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrm().setVisible(true);
            }
        });
    }

}
