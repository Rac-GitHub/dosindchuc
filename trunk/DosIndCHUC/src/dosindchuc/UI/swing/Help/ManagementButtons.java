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
     
     public void setAllWorkerBtsInit (boolean workerBts) {
         
         this.frmMan.btWorkerNew.setEnabled(workerBts);
         this.frmMan.btWorkerUpdate.setEnabled(workerBts);
         this.frmMan.btSaveWorkerNew.setEnabled(!workerBts);
         this.frmMan.btSaveWorkerUpdate.setEnabled(!workerBts);
         this.frmMan.btWorkerCancel.setEnabled(!workerBts);
  
         
         
     }
     
    public void setSaveWorkerNew (boolean workerBts) {
         
         setAllWorkerBtsInit(workerBts);
         this.frmMan.btSaveWorkerUpdate.setEnabled(workerBts);
         this.frmMan.btSaveWorkerUpdate.setVisible(workerBts);
         this.frmMan.btSaveWorkerNew.setVisible(!workerBts);
         
    }
         
    public void setSaveWorkerUpdate (boolean workerBts) {
         
         setAllWorkerBtsInit(workerBts);
         this.frmMan.btSaveWorkerUpdate.setEnabled(!workerBts);
         this.frmMan.btSaveWorkerNew.setVisible(workerBts);
         this.frmMan.btSaveWorkerUpdate.setVisible(!workerBts);
         
    }
    
         
}
     
    
    
    
    
    
    
    
   
