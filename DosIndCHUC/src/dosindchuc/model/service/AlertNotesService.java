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
import dosindchuc.UI.swing.Help.AlertTableInMainFrm;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.AlertNotesDao;
import dosindchuc.model.dao.Dose_notesDao;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.entities.AlertNotes;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dosimeter_notes;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
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

    private ManagementActionListener Listeners;
    private List<AlertNotes> alertNotes;
 //   private AlertTableInMainFrm tableModel;
    private DefaultTableModel model;
    private AlertTableInMainFrm tableNote;
  //  private Comparator<AlertNotes> alertNotesSort = comp;
    
   

    
    
    
    
    
    
    public AlertNotesService(MainFrm frmMain) {
        
        this.frmMain = frmMain;
   //     this.Listeners = Listeners;
        dbPkIDs = new DbPkIDs();
  //      tableModel = new ManagementTablesModel(this.frmMain, this.frmMan);
        tableNote = new AlertTableInMainFrm(this.frmMain);
        
        alertNotesDao = new AlertNotesDao();
       
//        setButtonsState = new ManagementButtons(this.frmMan);
//        setCleanState = new ManagementClean(this.frmMan);
//        setFieldState = new ManagementFields(this.frmMan);
//        setDoseNoteInfo = new ManagementSearch(this.frmMan, Listeners);
        
    }
    
    
    
      
     public void fillAlertNotesTable () {
  

        ArrayList<Object[]> alertNoteInfo = new ArrayList();

        List<AlertNotes> alertNoteList = new ArrayList<>();

        alertNotes = alertNotesDao.getDoseAlerNotes();

        int nResultsDose = alertNotes.size();


        if (nResultsDose > 0) {

            for (int i = 0; i < nResultsDose; i++) {

                AlertNotes alertNote = alertNotes.get(i);
                alertNoteList.add(alertNote);

            }

        }


        alertNotes.clear();

        alertNotes = alertNotesDao.getDsmtAlerNotes();

        int nResultsDsmt = alertNotes.size();

        if (nResultsDsmt > 0) {

            for (int i = 0; i < nResultsDsmt; i++) {
              AlertNotes alertNote = alertNotes.get(i);
                alertNoteList.add(alertNote);
            }


            if (!(nResultsDose > 0) && !(nResultsDsmt > 0)) {

                return;
            }

            
            // sort Collection
            
            Collections.sort(alertNoteList, Collections.reverseOrder(new sortArrayList()));
            
            
            
            // display sorted info on table 
            
            model = setTableModel("toedit");
    //        model = AlertTableInMainFrm.modelAlertTable; // Mode setTableModel("toedit");

            for (AlertNotes alN : alertNoteList) {
                Object newAlInfo[] = new Object[]{alN.getPk_notes(), alN.getNotesType(), alN.getNotesLevel(),
                    alN.getNotesMec(), alN.getNotesName(), alN.getNotesDept(), alN.getNotesNote(),
                    alN.getNotesStatus(), alN.getNotesDate(), Boolean.FALSE};
                model.addRow(newAlInfo);
                alertNoteInfo.add(newAlInfo);
                System.out.println(" estamos aqui ----- > type " + alN.getNotesType() + "   status " + alN.getNotesStatus() + "  level  ->> " + alN.getNotesLevel());
            }

            // save to use later 

            dbPkIDs.setAlertNote(alertNoteInfo);

 


        }
    }
    
     
     
     public void saveAlertNotesTable () {
   
        ArrayList<Object[]> alertNoteInfo;
        alertNoteInfo = dbPkIDs.getAlertNote();


        ArrayList<Integer> removedRows = new ArrayList<>();

        JTable table = tableNote.getNoteTable();
        DefaultTableModel tmodel = tableNote.getModelAlertTable();

        System.out.println(" Numero de rows " + table.getRowCount());

        int nAlertNotes = table.getRowCount();


        for (int i = 0; i < nAlertNotes; i++) {

            System.out.println(" Is to save ? __> " + table.getValueAt(i, 8) + "nAlertNotes   ->  " + nAlertNotes);
            boolean isToSave = table.getValueAt(i, 8).equals(true);

            System.out.println(" Is to save ? __> " + isToSave + " iiii  ->  " + i);

            if (isToSave) {

           //     alertNote = alertNoteInfo.get(i);
                System.out.println(" Is to save ? __> " + isToSave + " iiii  ->  " + i);
                alertNotesDao.updateAlertNote(i, table);

                table.setValueAt(dateAndTime.currDateTime().split("\\s+")[0], i, 7);
                table.setValueAt(Boolean.FALSE, i, 8);

                if (table.getValueAt(i, 6).toString().matches("C")) {
                    
                    removedRows.add(i);
                    System.out.println(" Is to save ? __> " + isToSave + " iidentro do tteste C ii  ->  " + i);


                }
  
            }

        }
        
        
        
        System.out.println(" To be removed ? __> " + removedRows.size());
        
        int nR = 0;
        for (int i = 0; i < removedRows.size(); i++) {

            int rowR = removedRows.get(i);
            
            System.out.println(" To be removed 333 ? __> " + rowR);
            
            tmodel.removeRow(rowR - nR);
            alertNoteInfo.remove(rowR - nR);

            System.out.println(" Dentro do IF  size  ->  " + alertNoteInfo.size());
            
            nR++;
            
        }

  
          dbPkIDs.setAlertNote(alertNoteInfo);
          System.out.println(" Dentro do IF  size  ->  " + alertNoteInfo.size());
          
        
    }
     
     
     
     
     public void cancelAlertNotesTable () {
         
       
        DefaultTableModel tmodel = tableNote.getModelAlertTable();
         
        
        System.out.println(tmodel.getRowCount());
        
        int nRow = tmodel.getRowCount();
        
         for (int i = 0; i < nRow; i++) {
             System.out.println("  iiii ----> " + i);
                 tmodel.removeRow(0);

             }
         
         displayAlertNotesTable (tmodel);
        
     }
     
     
     
      
     private DefaultTableModel setTableModel ( String type ) {
         
             tableNote.setSettingsAlertTable(type);
             model = tableNote.getModelAlertTable();
             
             System.out.println("  --- Get MOdel SET TABLE ---- > " + model);
             return model;
         
     }
     
 
     
     
     
     private void displayAlertNotesTable (DefaultTableModel model) {
         
            ArrayList<Object[]> alertNoteInfo;
            
            alertNoteInfo= dbPkIDs.getAlertNote();
            
            for (int i = 0; i < alertNoteInfo.size(); i++) {
                
                

                 Object alertNote[] = alertNoteInfo.get(i);
                 
                 System.out.println("  --- Type ---- > " + alertNote[1] + " alert level " + alertNote[2]);
                 
                 model.addRow(alertNote);

             }
                
            
     }
     
     
}




    // class to sort ana ArrayList --- AlertNotes based on a column value

    class sortArrayList implements Comparator<AlertNotes> {
     
    
     //Sorting using Anonymous inner class type
   
             @Override
             public int compare(AlertNotes alN, AlertNotes alN1) {
                 return  alN.getNotesLevel().compareTo(alN1.getNotesLevel());
	}
     
     
     }

