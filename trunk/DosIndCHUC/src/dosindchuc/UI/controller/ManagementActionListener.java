/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.service.ManagementDose;
import dosindchuc.model.service.ManagementDose_Notes;
import dosindchuc.model.service.ManagementDosimeter;
import dosindchuc.model.service.ManagementSearch;
import dosindchuc.model.service.ManagementWorker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.service.ManagementDosimeter_Notes;
import javax.swing.JTable;



/**
 *
 * @author ir
 */
public final class ManagementActionListener implements ActionListener, MouseListener {

    
    private ManagementFrm frmMan;
    private ManagementClean serviceClean;
    private ManagementButtons serviceBtns;
    private ManagementSearch serviceSearch;
    private ManagementWorker serviceWorker;
    private ManagementDosimeter serviceDosimeter;
    private ManagementDose serviceDose;
    private ManagementDose_Notes serviceDoseNotes;
    private ManagementDosimeter_Notes serviceDsmtNotes;
    public DbPkIDs dbPkIDs;
    private JTable table;
    
    public Object [][] workerList = null;
    public ActionListener Listeners;
    
    
    public ManagementActionListener(ManagementFrm frmMan) {
     
        this.frmMan = frmMan;
        Listeners = this;
  
        serviceClean = new ManagementClean(this.frmMan);
        serviceBtns = new ManagementButtons(this.frmMan);
        dbPkIDs = new DbPkIDs();
        serviceSearch = new ManagementSearch(this.frmMan, this);
        serviceWorker = new ManagementWorker(this.frmMan);
        serviceDosimeter = new ManagementDosimeter(this.frmMan);
        serviceDsmtNotes = new ManagementDosimeter_Notes(this.frmMan,this);
        serviceDose = new ManagementDose(this.frmMan);
        serviceDoseNotes = new ManagementDose_Notes(this.frmMan,this);
        
        addListeners();

    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent aevent) {
        
        
        System.out.println(aevent);
        
        String command = aevent.getActionCommand();
        
        System.out.println(command);
        
        switch (command) {
            case "searchManagement":  
                     serviceClean.cleanAllInfo();
                     serviceSearch.searchWorkers();
                     break;
            case "cleanManagement":  
                serviceBtns.setAllSearchClearBts();
                serviceClean.cleanAllInfoWithSearch();
                     break;
                
// worker
            case "btNewWorker":  serviceWorker.newWorker();
                     break;
            case "btSaveWorkerNew":  serviceWorker.saveNewWorker();
                     break;
            case "btUpdateWorker":  serviceWorker.updateWorker();
                     break;
            case "btSaveWorkerUpdate":  serviceWorker.saveUpdateWorker();
                     break;
            case "btWorkerCancel":  serviceWorker.btWorkerCancel();
                     break;    
                               
      // dosimeter          
            case "btDosimeterInfoNew":  serviceDosimeter.newDosimeter();
                     break;
            case "btDosimeterInfoSaveNew": 
                serviceDosimeter.saveNewDsmt();
                serviceBtns.setAllCancelBts();
                     break;
            case "btDosimeterInfoUpdate":  serviceDosimeter.updateDosimeter();
                     break;
            case "btDosimeterInfoSaveUpdate": 
               serviceDosimeter.saveUpdateDsmt();
                serviceBtns.setAllCancelBts();
                     break;
            case "btDosimeterInfoCancel":
                this.frmMan.getTxtInfoAction().setText("Canceling action on dosimeter");
                serviceBtns.setAllCancelBts();
                serviceDosimeter.fillWokerDsmtInfo();
                     break;
                
          // dosimeter Notes      
            case "cbDosimeterNotesIndex":  serviceSearch.fillDosimeterNotesInfo();
                     break; 
            case "btNewNoteDosimeter":  serviceDsmtNotes.newDsmtNote();
                     break;
            case "btSaveNewDsmtNote":  
                serviceDsmtNotes.saveNewDsmtNote();
                serviceBtns.setAllCancelBts();
                     break;
            case "btUpdateNoteDosimeter":  serviceDsmtNotes.updateDsmtNote();
                     break;
            case "btSaveUpdateDsmtNote":  
                serviceDsmtNotes.saveUpdateDsmtNote();
                serviceBtns.setAllCancelBts();
                  break;
            case "btCancelDsmtNote": 
                this.frmMan.getTxtInfoAction().setText("Canceling action on dosimeter");
                serviceDsmtNotes.cancelDsmtNote();
 
            break;
     
      // dose          
            case "btDoseNew":  serviceDose.newDose();
                     break;
            case "btDoseInfoSaveNew":  
                serviceDose.saveNewDose();
                serviceBtns.setAllCancelBts();
                     break;
            case "btDoseInfoUpdate":  serviceDose.updateDose();
                     break;
            case "btDoseInfoSaveUpdate":  
                serviceDose.saveUpdateDose();
                serviceBtns.setAllCancelBts();
                     break;
            case "btDoseInfoCancel": 
                this.frmMan.getTxtInfoAction().setText("Canceling action on dose");
                serviceBtns.setAllCancelBts();
                serviceDose.fillWokerDoseInfo();
                     break; 
                
     // dose note
            case "cbDoseNoteIndex":  serviceSearch.fillDoseNotesInfo();
                     break;
            case "btNewDoseNote":  serviceDoseNotes.newDoseNote();
                     break;
            case "btSaveNewDoseNote":  
                serviceDoseNotes.saveNewDoseNote();
                serviceBtns.setAllCancelBts();
                     break;
            case "btUpdateDoseNote":  serviceDoseNotes.updateDoseNote();
                     break;
            case "btSaveUpdateDoseNote":  
                serviceDoseNotes.saveUpdateDoseNote();
                serviceBtns.setAllCancelBts();
                     break;
            case "btCancelDoseNote": 
                this.frmMan.getTxtInfoAction().setText("Canceling action on dose note");
                serviceBtns.setAllCancelBts();
            break; 
     
                
        }
    }
    
    
    
      /**
     *
     */
    public void addListeners() {
        
        System.out.println("noa add listenesrs --- " + this);
        
        frmMan.searchBtSeach.addActionListener(this);
        frmMan.searchBtClean.addActionListener(this);
 
        frmMan.searchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchTableMouseClicked(evt);
            }
        });

        
        frmMan.tableDoseInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("evento tabela::::doseInfo  " + evt);
                tableDoseInfoMouseClicked(evt);
            }
        });
        
        
        frmMan.tableDosimeterInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDosimeterInfoMouseClicked(evt);
            }
        });
        
       System.out.println("noa add listenesrs --- " + this);
        frmMan.cbDoseNoteIndex.addActionListener(this);

        frmMan.cbDosimeterNotesIndex.addActionListener(this);
        
        
        
        // buttons Worker
        
        frmMan.btWorkerNew.addActionListener(this);
        frmMan.btSaveWorkerNew.addActionListener(this);
        frmMan.btWorkerUpdate.addActionListener(this);
        frmMan.btSaveWorkerUpdate.addActionListener(this);
        frmMan.btWorkerCancel.addActionListener(this);
        
        
        // buttons dosimeter
        
        frmMan.btDosimeterInfoNew.addActionListener(this);
        frmMan.btDosimeterInfoUpdate.addActionListener(this);
        frmMan.btDosimeterInfoSaveNew.addActionListener(this);
        frmMan.btDosimeterInfoSaveUpdate.addActionListener(this);
        frmMan.btDosimeterInfoCancel.addActionListener(this);
        
         
        frmMan.btNewNoteDosimeter.addActionListener(this);
        frmMan.btUpdateNoteDosimeter.addActionListener(this);
        frmMan.btSaveNewDsmtNote.addActionListener(this);
        frmMan.btSaveUpdateDsmtNote.addActionListener(this);
        frmMan.btCancelDsmtNote.addActionListener(this);
        
        
         // buttons for dose
        
        frmMan.btDoseInfoNew.addActionListener(this);
        frmMan.btDoseInfoUpdate.addActionListener(this);
        frmMan.btDoseInfoSaveNew.addActionListener(this);
        frmMan.btDoseInfoSaveUpdate.addActionListener(this);
        frmMan.btDoseInfoCancel.addActionListener(this);
        
        frmMan.btNewDoseNote.addActionListener(this);
        frmMan.btUpdateDoseNote.addActionListener(this);
        frmMan.btSaveNewDoseNote.addActionListener(this);
        frmMan.btSaveUpdateDoseNote.addActionListener(this);
        frmMan.btCancelDoseNote.addActionListener(this);
        
        
     
    }

  
    
    public void searchTableMouseClicked(MouseEvent mevent) {

        if (mevent.getClickCount() == 2) {
            serviceClean.cleanAllInfo();
            frmMan.btWorkerNew.setEnabled(true);
            frmMan.btWorkerUpdate.setEnabled(true);
            serviceBtns.setDosimeterBtsSearch(true);
            serviceBtns.setDoseBtsSearch(true);
            serviceSearch.fillAllManagement();
            this.frmMan.getTxtInfoAction().setText("");
  
        }    
        
    }

    
    public void tableDoseInfoMouseClicked(MouseEvent mevent) {
     
        if ( ! this.frmMan.btDoseInfoUpdate.isEnabled() ) {
            return;
        }
        
        
       if ( ! this.frmMan.tableDoseInfo.isEnabled() ) {
            System.out.println("  Aqui dentro dose ---- ");
            return;
        }  
        
        ArrayList2D doseNoteInfo = new ArrayList2D();

        // info dose-note selected for update
        for (int i = 0; i < 4; i++) {
            doseNoteInfo.Add(null, 0);
        }

        dbPkIDs.setDoseNotes_id(doseNoteInfo);

        System.out.println(" No tableDoseInfoMouseClicked pois " + dbPkIDs.getDoseNotes_id().get(0, 0));

        serviceSearch.fillDoseNotesCBIndex();
 
    }    
 
    
    public void tableDosimeterInfoMouseClicked(MouseEvent mevent) {

        if ( ! this.frmMan.btDosimeterInfoUpdate.isEnabled() ) {
            return;
        }
        
        if ( ! this.frmMan.tableDosimeterInfo.isEnabled() ) {
            System.out.println("  Aqui dentro  ---- ");
            return;
        }
        
        ArrayList2D dsmtNoteInfo = new ArrayList2D();

        // info dose-note selected for update
        for (int i = 0; i < 4; i++) {
            dsmtNoteInfo.Add(null, 0);
        }

        dbPkIDs.setDsmtNotes_id(dsmtNoteInfo);

        System.out.println(" No tableDoseInfoMouseClicked pois " + dbPkIDs.getDsmtNotes_id().get(0, 0));

        serviceSearch.fillDosimeterNotesCBIndex();

    }
      
    
    
    
    
    
    // generic listeners
    
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println(me);
       
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
        
   
    
}