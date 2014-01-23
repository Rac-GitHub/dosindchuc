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
    private ManagementTablesModel tableModel;
    
    
    
    public ManagementFields(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        tableModel = new ManagementTablesModel(this.frmMan);
        
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
 
    
    // dosimeter and dose
    
    
    
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

     
     
  // dose notes
     
    public void setNewDoseNotes (boolean doseNoteEdit) {
        
        System.out.println("Estou no set New dose note ");
 
        this.frmMan.getTxtDoseNotesDateCreated().setText("");
        this.frmMan.getTxtDoseNote().setText("");
        this.frmMan.txtDoseNote.setEditable(doseNoteEdit);
        this.frmMan.getCbDoseNoteLevel().setSelectedIndex(1);
        this.frmMan.cbDoseNoteLevel.setEnabled(doseNoteEdit);
        this.frmMan.getTxtDoseNoteLevelDate().setText("");
        this.frmMan.getCbDoseNoteStatus().setSelectedIndex(0);
        this.frmMan.cbDoseNoteStatus.setEnabled(doseNoteEdit);
        this.frmMan.getTxtDoseNoteStatusDate().setText("");
        
    }
     
   
    public void setUpdateDoseNotes (boolean doseNoteEdit) {
        
        System.out.println("Estou no set Update dose note ");
        
        this.frmMan.cbDoseNoteIndex.setEnabled(!doseNoteEdit);
        
        this.frmMan.txtDoseNote.setEditable(doseNoteEdit);
        this.frmMan.cbDoseNoteLevel.setEnabled(doseNoteEdit);
        this.frmMan.cbDoseNoteStatus.setEnabled(doseNoteEdit);

        
    }
    
    
    
    // dosimeter notes
     
    public void setNewDsmtNotes (boolean dsmtNoteEdit) {
        
        System.out.println("Estou no set New DSMT note ");
 
        this.frmMan.getTxtDosimeterNotesDateCreated().setText("");
        this.frmMan.getTxtDosimeterNote().setText("");
        this.frmMan.txtDosimeterNote.setEditable(dsmtNoteEdit);
        this.frmMan.getCbDosimeterNotesAlert().setSelectedIndex(1);
        System.out.println("  no dosimeter notes new " + this.frmMan.getCbDosimeterNotesAlert().getSelectedIndex());
        this.frmMan.cbDosimeterNotesAlert.setEnabled(dsmtNoteEdit);
        this.frmMan.getTxtDosimeterNoteAlertdate().setText("");
        this.frmMan.getCbDosimeterNotesStatus().setSelectedIndex(0);
        this.frmMan.cbDosimeterNotesStatus.setEnabled(dsmtNoteEdit);
        this.frmMan.getTxtDosimeterNoteStatusDate().setText("");

        
    }
     
   
    public void setUpdateDsmtNotes (boolean dsmtNoteEdit) {
        
        System.out.println("Estou no set Update DSMT note ");
 
        this.frmMan.cbDosimeterNotesIndex.setEnabled(!dsmtNoteEdit);
       
        this.frmMan.txtDosimeterNote.setEditable(dsmtNoteEdit);
        this.frmMan.cbDosimeterNotesAlert.setEnabled(dsmtNoteEdit);
        this.frmMan.cbDosimeterNotesStatus.setEnabled(dsmtNoteEdit);

        
    }
    
}
