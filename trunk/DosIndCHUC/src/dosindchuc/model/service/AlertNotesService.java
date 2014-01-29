/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.controller.ManagementActionListener;
import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementFields;
import dosindchuc.UI.swing.Help.ManagementTablesModel;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.AlertNotesDao;
import dosindchuc.model.dao.Dose_notesDao;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.entities.AlertNotes;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dosimeter_notes;
import dosindchuc.model.entities.Help.DateAndTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ir
 */
public class AlertNotesService {

    
    private MainFrm frmMain;
    private ManagementFrm frmMan;
    private AlertNotesDao alertNotesDao;
    private DbPkIDs dbPkIDs;
    
    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementFields setFieldState;
    private ManagementSearch setDoseNoteInfo;
    private JTable table;
    private ManagementActionListener Listeners;
    private List<AlertNotes> alertNotes;
    private ManagementTablesModel tableModel;
    private DefaultTableModel model;

    
    
    
    
    
    
    public AlertNotesService(MainFrm frmMain) {
        
        this.frmMain = frmMain;
   //     this.Listeners = Listeners;
        dbPkIDs = new DbPkIDs();
        tableModel = new ManagementTablesModel(this.frmMain, this.frmMan);
        alertNotesDao = new AlertNotesDao();
//        setButtonsState = new ManagementButtons(this.frmMan);
//        setCleanState = new ManagementClean(this.frmMan);
//        setFieldState = new ManagementFields(this.frmMan);
//        setDoseNoteInfo = new ManagementSearch(this.frmMan, Listeners);
        
    }
    
    
    
      
     public void fillAlertNotesTable () {
          
         alertNotes =  alertNotesDao.getDoseAlerNotes();
       
         int nResults = alertNotes.size();
        
         if ( ! (nResults > 0) ) {
             alertNotes = null;
             return; // alertNotes;
         } 
        
         System.out.println(" Fill dosimeter com Update  : " + nResults);
         
   /*       if (newORupdate.equalsIgnoreCase("update")) {
              System.out.println(" Fill dosimeter com Update  inside : " + nResults);
          
              tableModel.setDefaultDsmtTable("updatedsmt");
            
              
          } else if (newORupdate.equalsIgnoreCase("new")) {
              tableModel.setDefaultDsmtTable("readonly");
 
          } else if (newORupdate.equalsIgnoreCase("list")) {
              tableModel.setDefaultDsmtTable("readonly");
              this.frmMan.btDosimeterInfoNew.setEnabled(true);
              this.frmMan.btDosimeterInfoUpdate.setEnabled(true);
              System.out.println(" erro list:");
          } else {
              System.out.println(" erro:");
          }
            
        */
        
        tableModel.setDefaultAlertTable("readonly");
         
        model = tableModel.getAlertTable();
    
        System.out.println(" Fill alert com Update model111  : " + model);
        
        
        ArrayList<Object []> dsmtIds = new ArrayList();
   
        for (int i = 0; i < nResults; i++) {
            
             AlertNotes alertNote = alertNotes.get(i);
             Object newRow[] = new Object [] {alertNote.getPk_notes(), alertNote.getNotesType(), alertNote.getNotesLevel(), alertNote.getNotesMec(), alertNote.getNotesName()
                     , alertNote.getNotesDept(), alertNote.getNotesNote(), alertNote.getNotesStatus(), alertNote.getNotesDate()};
             model.addRow(newRow);
             
             
             // info dos dosimetros para a dose
//             Object dsmtInfo [] = new Object [] {dosimeter.getPk_dsmt(), dosimeter.getId(), dosimeter.getStatus(), dosimeter.getPeriodicity()};
//             dsmtIds.add(i, dsmtInfo);
          
         }
        
        alertNotes.clear();
        
        alertNotes =  alertNotesDao.getDsmtAlerNotes();
  
         nResults = alertNotes.size();
        
         if ( ! (nResults > 0) ) {
             alertNotes = null;
             return; // alertNotes;
         } 
         
         for (int i = 0; i < nResults; i++) {
            
             AlertNotes alertNote = alertNotes.get(i);
             Object newRow[] = new Object [] {alertNote.getPk_notes(), alertNote.getNotesType(), alertNote.getNotesLevel(), alertNote.getNotesMec(), alertNote.getNotesName()
                     , alertNote.getNotesDept(), alertNote.getNotesNote(), alertNote.getNotesStatus(), alertNote.getNotesDate()};
             model.addRow(newRow);
             
             
             // info dos dosimetros para a dose
//             Object dsmtInfo [] = new Object [] {dosimeter.getPk_dsmt(), dosimeter.getId(), dosimeter.getStatus(), dosimeter.getPeriodicity()};
//             dsmtIds.add(i, dsmtInfo);
          
         }
//        
//        System.out.println(" Aqui no dsmt array list dsmtIds ja esta  " + dsmtIds.get(0)[1] );
//        System.out.println(" Aqui no dsmt array list dsmtIds  " + dsmtIds.toArray().length );
//        
//         dbPkIDs.setDsmt_id(dsmtIds);
         
//         System.out.println(" Aqui no dsmt array list dsmtIds ja esta  " + dbPkIDs.getDsmt_id().get(0)[1] );
//        
//         return dosimeter_info;
         
    }
    
     
     public void editAlertNotesTable () {
         
        
            tableModel.setDefaultAlertTable("edit");
     
     }
     
     
}