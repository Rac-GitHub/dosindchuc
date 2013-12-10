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
public class ManagementFields {
    
    private ManagementFrm frmMan;
    
    
    
    public ManagementFields(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        
    }
    
    public void setAllEdit (boolean workerEdit, boolean dsmtEdit, boolean doseEdit) {
        
        setWorkerAllEdit (workerEdit);
        setAllDosimeterEdit (dsmtEdit);
        setAllDoseEdit (doseEdit);
        
    }
    
    
    public void setWorkerAllEdit (boolean workerEdit) {
        
        this.frmMan.txtWorkerName.setEditable(workerEdit);
        this.frmMan.txtWorkerBI.setEditable(workerEdit);
        this.frmMan.txtWorkerBirthYear.setEditable(workerEdit);
        this.frmMan.txtWorkerBirthMonth.setEditable(workerEdit);
        this.frmMan.txtWorkerBirthDay.setEditable(workerEdit);
        this.frmMan.txtWorkerMec.setEditable(workerEdit);
        this.frmMan.txtWorkerNIF.setEditable(workerEdit);
        this.frmMan.txtWorkerNationality.setEditable(workerEdit);
        this.frmMan.txtWorkerNick.setEditable(workerEdit);
        this.frmMan.txtWorkerSector.setEditable(workerEdit);
        this.frmMan.cbWorkerCat.setEnabled(workerEdit);
        this.frmMan.cbWorkerSex.setEnabled(workerEdit);
        this.frmMan.cbWorkerStatus.setEnabled(workerEdit);
        this.frmMan.cbWorkerDept.setEnabled(workerEdit);
        this.frmMan.txtWorkerComments.setEditable(workerEdit);
        
    }
    
     public void setAllDosimeterEdit (boolean dsmtEdit) {
        
        this.frmMan.txtDosimeterNote.setEditable(dsmtEdit);
        this.frmMan.cbDosimeterNotesIndex.setEnabled(dsmtEdit);
        this.frmMan.cbDosimeterNotesAlert.setEnabled(dsmtEdit);
        this.frmMan.cbDosimeterNotesStatus.setEnabled(dsmtEdit);
        
    }
    
     public void setAllDoseEdit (boolean doseEdit) {
        
        this.frmMan.txtDoseNote.setEditable(doseEdit);
        this.frmMan.cbDoseNoteIndex.setEnabled(doseEdit);
        this.frmMan.cbDoseNoteLevel.setEnabled(doseEdit);
        this.frmMan.cbDoseNoteStatus.setEnabled(doseEdit);
        
    }

   
     
     
}
