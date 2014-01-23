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
import dosindchuc.model.dao.Dosimeter_notesDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dosimeter_notes;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementDosimeter_Notes {
 
       
    private ManagementFrm frmMan;
    private Dosimeter_notesDao dsmtNotesdao;
    private DbPkIDs dbPkIDs;
 
    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementFields setFieldState;
    private JTable table;
    private ManagementActionListener Listeners;

     
    
    
    public ManagementDosimeter_Notes (ManagementFrm frmMan, ManagementActionListener Listeners) {

     
        this.frmMan = frmMan;
        this.Listeners = Listeners;
        dbPkIDs = new DbPkIDs();
        dsmtNotesdao = new Dosimeter_notesDao();
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setFieldState = new ManagementFields(this.frmMan);
        
 //       tableModel = new ManagementTablesModel(this.frmMan);
 //       setDsmtInfo = new ManagementSearch(this.frmMan, null);
        
    
    }
    
   
    
    /* ############################################### */
    /*                                                  */ 
    /*              dose Note  info                       */
    /*                                                  */ 
    /* ###############################################  */ 

    private Dosimeter_notes getDsmtNote (String newOrUpdate) {
        
        String currDateTime = dateAndTime.currDateTime();

        Dosimeter_notes dsmtNote = new Dosimeter_notes();

        table = this.frmMan.tableDosimeterInfo;

        int row = table.getSelectedRow();
        String pk_dsmt = dbPkIDs.getDsmt_id().get(row)[0].toString();
        dsmtNote.setPk_dsmt(pk_dsmt);

        dsmtNote.setNote(this.frmMan.getTxtDosimeterNote().getText());
        String cbStatus = this.frmMan.cbDosimeterNotesStatus.getSelectedItem().toString();
        String cbAlertLevel = this.frmMan.cbDosimeterNotesAlert.getSelectedItem().toString();
        System.out.println("getDose note " + pk_dsmt + " status " + cbStatus + "   level " + cbAlertLevel);
        System.out.println("getDose note " + pk_dsmt + " status " + this.frmMan.cbDosimeterNotesAlert.getSelectedItem() + "   level " + cbAlertLevel);
        
        dsmtNote.setStatus(SetEnums.note_status.valueOf(cbStatus));
        dsmtNote.setAlert_level(SetEnums.note_alertlevel.valueOf(cbAlertLevel));

        if (newOrUpdate.equalsIgnoreCase("new")) {

            dsmtNote.setTimestamp(currDateTime);
            dsmtNote.setLastchange(currDateTime);
            dsmtNote.setAlert_level_timestamp(currDateTime);
            dsmtNote.setStatus_timestamp(currDateTime);

        } else {

            dsmtNote.setTimestamp(this.frmMan.getTxtDosimeterNotesDateCreated().getText());

            System.out.println("getDsmt note antes do IF1  " + dbPkIDs.getDsmtNotes_id().get(0, 0) );
            System.out.println("getDose note antes do IF2  " + dbPkIDs.getDsmtNotes_id().get(0, 1) );
            System.out.println("getDose note antes do IF3  " + dbPkIDs.getDsmtNotes_id().get(0, 2) );
            dsmtNote.setStatus_timestamp(this.frmMan.getTxtDosimeterNoteStatusDate().getText());
            if ( ! cbStatus.matches(dbPkIDs.getDsmtNotes_id().get(0, 3).toString()) ) {
                dsmtNote.setStatus_timestamp(currDateTime);
            }

            dsmtNote.setAlert_level_timestamp(this.frmMan.getTxtDosimeterNoteAlertdate().getText());
            if ( ! cbAlertLevel.matches(dbPkIDs.getDsmtNotes_id().get(0, 2).toString()) ) {
                dsmtNote.setAlert_level_timestamp(currDateTime);
            }

        }


        System.out.println("dose note  info Note .. " + dsmtNote.getNote());


        System.out.println("dose note  info status name .. " + dsmtNote);

        return dsmtNote;
        

    }
   
   
  
    
    
    
    public void newDsmtNote () {
        
        /* tudo ok para escrever */
        
        System.out.println(" Estou no newDSMTNote ... ");
    
        setCleanState.cleanDsmtNotes();
        setFieldState.setNewDsmtNotes(true);
        setButtonsState.setNewDsmtNoteBts(true);
        
        this.frmMan.tableDosimeterInfo.setEnabled(false);
        
        this.frmMan.getTxtInfoAction().setText("Inserting a New dosimeter note");
 
        
    }
    
    
     
    // insert into the database 
    
    public void saveNewDsmtNote () {
        
        
        Dosimeter_notes dsmtNote = getDsmtNote("new");
        
        System.out.println("  Save dsmt note " + dsmtNote);
        System.out.println("  Save dsmt1  note " + dbPkIDs.getWorker_id());
        System.out.println("  Save dsmt2  note " + dbPkIDs.getDose_id());
        System.out.println("  Save dsmt3  note " + dsmtNote.getPk_dsmt());

        
        String id = dsmtNotesdao.insertDsmtNote(dsmtNote);
        
        System.out.println("  Save dsmtNote3  " + id);
        
         
        this.frmMan.getTxtInfoAction().setText("Dosimeter note to dsmt_id = "+ dsmtNote.getPk_dsmt() + " saved into database");
        
        // actualiza info
        this.frmMan.tableDosimeterInfo.setEnabled(true);
        fillDsmtNoteInfo();
        
//        setButtonsState.setNewORUpdateDoseNoteBts(true);
        
    }
    
    
    
    /**
     *
     */
    
    public void updateDsmtNote() {
        
        System.out.println(" Estou no Update DSMT ... ");
 
   
        
        setButtonsState.setUpdateDsmtNoteBts(true);
        setFieldState.setUpdateDsmtNotes(true);

        
        this.frmMan.tableDosimeterInfo.setEnabled(false);
     
        this.frmMan.getTxtInfoAction().setText("Updating Dosimeter note info");
        
    }
    
    
    public void saveUpdateDsmtNote () {
        
        Dosimeter_notes dsmtNote = getDsmtNote("update");
   
        System.out.println(" DSMT UPDATE  info " + dsmtNote);
        
        String dsmtNote_id = dbPkIDs.getDsmtNotes_id().get(0, 0).toString();
        
        System.out.println("save update DSMT   " +  dsmtNote_id);

        dsmtNotesdao.updateDsmtNote(dsmtNote, dsmtNote_id);
        
        this.frmMan.getTxtInfoAction().setText("Dosimeter note updated into database");
        
        // actualiza info
        
        System.out.println("save update dose TABELA   " +  this.frmMan.tableDosimeterInfo.isEnabled());
        
        this.frmMan.tableDosimeterInfo.setEnabled(true);
        
        fillDsmtNoteInfo();
          
    }
    
    public void cancelDsmtNote () {
        
             
         this.frmMan.getTxtInfoAction().setText("Canceling action on dosimeter note");
         setButtonsState.setAllCancelBts();
         setCleanState.cleanDsmtNotes();
         setButtonsState.setAllDsmtNoteBtsInit(false);
         this.frmMan.tableDosimeterInfo.setEnabled(true);
                
    }
    
    
    
     public void fillDsmtNoteInfo () {
        
        /* tudo ok para escrever */
        
        System.out.println(" Estou no fill DSMTNote ... ");
 
           setCleanState.cleanDsmtNotes();
           setButtonsState.setAllDsmtNoteBtsInit(false);
         
    }
    
    
  
    
    
}
