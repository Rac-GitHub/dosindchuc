/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.controller.ManagementActionListener;
import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementFields;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Dose_notesDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dose_notes;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementDose_Notes {

    
    private ManagementFrm frmMan;
    private Dose_notesDao doseNotesdao;
    private DbPkIDs dbPkIDs;

    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementFields setFieldState;
    private ManagementSearch setDoseNoteInfo;
    private JTable table;
    private ManagementActionListener Listeners;

     
    
    
    public ManagementDose_Notes (ManagementFrm frmMan, ManagementActionListener Listeners) {

     
        this.frmMan = frmMan;
        this.Listeners = Listeners;
        dbPkIDs = new DbPkIDs();
        doseNotesdao = new Dose_notesDao();
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setFieldState = new ManagementFields(this.frmMan);
        setDoseNoteInfo = new ManagementSearch(this.frmMan, Listeners);
        
    
    }
    
   
    
    /* ############################################### */
    /*                                                  */ 
    /*              dose Note  info                       */
    /*                                                  */ 
    /* ###############################################  */ 

    private Dose_notes getDoseNote (String newOrUpdate) {
        
        String currDateTime = dateAndTime.currDateTime();

        Dose_notes doseNote = new Dose_notes();

        table = this.frmMan.tableDoseInfo;

        int row = table.getSelectedRow();
        String pk_dose = dbPkIDs.getDose_id().get(row).toString();
        doseNote.setPk_dose(pk_dose);

        doseNote.setNote(this.frmMan.getTxtDoseNote().getText());
        String cbStatus = this.frmMan.cbDoseNoteStatus.getSelectedItem().toString();
        String cbAlertLevel = this.frmMan.cbDoseNoteLevel.getSelectedItem().toString();
        System.out.println("getDose note " + pk_dose + " status " + cbStatus + "   level " + cbAlertLevel);
        System.out.println("getDose note " + pk_dose + " status " + this.frmMan.cbDoseNoteLevel.getSelectedItem() + "   level " + cbAlertLevel);
        
        doseNote.setStatus(SetEnums.note_status.valueOf(cbStatus));
        doseNote.setAlert_level(SetEnums.note_alertlevel.valueOf(cbAlertLevel));

        if (newOrUpdate.equalsIgnoreCase("new")) {

            doseNote.setTimestamp(currDateTime);
            doseNote.setLastchange(currDateTime);
            doseNote.setAlert_level_timestamp(currDateTime);
            doseNote.setStatus_timestamp(currDateTime);

        } else {

            doseNote.setTimestamp(this.frmMan.getTxtDoseNotesDateCreated().getText());

            System.out.println("getDose note antes do IF  " + dbPkIDs.getDoseNotes_id().get(0, 0) );
            System.out.println("getDose note antes do IF  " + dbPkIDs.getDoseNotes_id().get(0, 1) );
            System.out.println("getDose note antes do IF  " + dbPkIDs.getDoseNotes_id().get(0, 2) );
            doseNote.setStatus_timestamp(this.frmMan.getTxtDoseNoteStatusDate().getText());
            if ( ! cbStatus.matches(dbPkIDs.getDoseNotes_id().get(0, 3).toString()) ) {
                doseNote.setStatus_timestamp(currDateTime);
            }

            doseNote.setAlert_level_timestamp(this.frmMan.getTxtDoseNoteLevelDate().getText());
            if ( ! cbAlertLevel.matches(dbPkIDs.getDoseNotes_id().get(0, 2).toString()) ) {
                doseNote.setAlert_level_timestamp(currDateTime);
            }

        }


        System.out.println("dose note  info Note .. " + doseNote.getNote());


        System.out.println("dose note  info status name .. " + doseNote);

        return doseNote;

    }
   
   
  
    
    
    
    public void newDoseNote () {
        
        /* tudo ok para escrever */
        
        System.out.println(" Estou no newDoseNote ... ");
    
        setCleanState.cleanDoseNotes();
        setFieldState.setNewDoseNotes(true);
        setButtonsState.setNewDoseNoteBts(true);
        
        this.frmMan.tableDoseInfo.setEnabled(false);
        
        this.frmMan.getTxtInfoAction().setText("Inserting a New dose note");
 
        
    }
    
    
    // insert into the database 
    
    public void saveNewDoseNote () {
        
        
        Dose_notes doseNote = getDoseNote("new");
        
        System.out.println("  Save dose note " + doseNote);
        System.out.println("  Save dose  note " + dbPkIDs.getWorker_id());
        System.out.println("  Save dose2  note " + dbPkIDs.getDose_id());
        System.out.println("  Save dose3  note " + doseNote.getPk_dose());

        
        String id = doseNotesdao.insertDoseNote(doseNote);
        
        System.out.println("  Save doseNote3  " + id);
        
         
        this.frmMan.getTxtInfoAction().setText("Dose note to dose_id = "+ doseNote.getPk_dose() + " saved into database");
        
        // actualiza info
        this.frmMan.tableDoseInfo.setEnabled(true);
        fillDoseNoteInfo();
        
//        setButtonsState.setNewORUpdateDoseNoteBts(true);
        
    }
    
    
    
    /**
     *
     */
    
    public void updateDoseNote() {
        
        System.out.println(" Estou no Update Dose Note ... ");
        
        setButtonsState.setUpdateDoseNoteBts(true);
        setFieldState.setUpdateDoseNotes(true);
        
        this.frmMan.tableDoseInfo.setEnabled(false);
   
        this.frmMan.getTxtInfoAction().setText("Updating Dose note info");
        
    }
    
    
    public void saveUpdateDoseNote () {
        
        Dose_notes doseNote = getDoseNote("update");
   
        System.out.println(" Dose UPDATE  info " + doseNote);
        
        String doseNote_id = dbPkIDs.getDoseNotes_id().get(0, 0).toString();
        
        System.out.println("save update dose   " +  doseNote_id);

        doseNotesdao.updateDoseNote(doseNote, doseNote_id);
        
        this.frmMan.getTxtInfoAction().setText("Dose note updated into database");
        
        // actualiza info
        this.frmMan.tableDoseInfo.setEnabled(true);
        fillDoseNoteInfo();
          
    }
    
    public void cancelDoseNote () {
        
             
         this.frmMan.getTxtInfoAction().setText("Canceling action on dose note");
         setButtonsState.setAllCancelBts();
         setCleanState.cleanDoseNotes();
         setButtonsState.setAllDoseNoteBtsInit(false);
         this.frmMan.tableDoseInfo.setEnabled(true);
                
    }
    
    
     public void fillDoseNoteInfo () {
        
        /* tudo ok para escrever */
        
        System.out.println(" Estou no fill DoseNote ... ");
        
        setCleanState.cleanDoseNotes();
        setButtonsState.setAllDoseNoteBtsInit(false);
 
       
        
    }
    
    
    

    
    
    
    
    
    
    
}
