/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.ManagementFrm;

/**
 *
 * @author ir
 */
public class ManagementButtons {

    
    private ManagementFrm frmMan;
    
    
    
    public ManagementButtons (ManagementFrm frmMan) {

        this.frmMan = frmMan;
        
    }
    
  
   // worker     
     
     public void setAllWorkerBtsInitAndNew (boolean workerBts) {
         
         this.frmMan.btWorkerNew.setEnabled(workerBts);
         this.frmMan.btWorkerUpdate.setEnabled(workerBts);
         this.frmMan.btWorkerSave.setEnabled(!workerBts);
         this.frmMan.btWorkerCancel.setEnabled(!workerBts);
         
         
     }
     
    public void setNewWorkerBtsInit (boolean workerBts) {
         
         this.frmMan.btWorkerNew.setEnabled(workerBts);
         this.frmMan.btWorkerUpdate.setEnabled(workerBts);
         this.frmMan.btWorkerSave.setEnabled(!workerBts);
         this.frmMan.btWorkerCancel.setEnabled(!workerBts);
         
         
     }
     
    
    
    
    
    
    
    
    
}
