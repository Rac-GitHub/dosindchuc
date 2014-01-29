
import dosindchuc.UI.swing.MainFrm;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ir
 */
public class RunDosIndCHUC {
  
  
 /**
     * @param args the command line arguments
     */
      public static void main(String args[]) {
      
          /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                int[][][] points = {{{100,200,300}},{{122,202,402}},{{203,253,143}}};
                System.out.println(points[0][0][0]);
                System.out.println(points[0][0][1]);
                System.out.println(points[0][0][2]);
                System.out.println(points[2][0][0]);
                 System.out.println(points.length);
                new MainFrm().setVisible(true);
                
            }
        });
    }

}
