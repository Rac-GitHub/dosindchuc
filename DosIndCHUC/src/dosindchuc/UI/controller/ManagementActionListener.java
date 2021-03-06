/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.service.ManagementDose;
import dosindchuc.model.service.ManagementDoseNotes;
import dosindchuc.model.service.ManagementDosimeter;
import dosindchuc.model.service.ManagementDsmtHist;
import dosindchuc.model.service.ManagementDsmtNotes;
import dosindchuc.model.service.ManagementSearch;
import dosindchuc.model.service.ManagementWorker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private ManagementDoseNotes serviceDoseNotes;
    private ManagementDsmtNotes serviceDsmtNotes;
    private ManagementDsmtHist serviceDsmtHist;
    public DbPkIDs dbPkIDs;
    public ActionListener Listeners;

    public ManagementActionListener(ManagementFrm frmMan) {

        this.frmMan = frmMan;
 //       Listeners = this;

        serviceClean = new ManagementClean(this.frmMan);
        serviceBtns = new ManagementButtons(this.frmMan);
        dbPkIDs = new DbPkIDs();
        serviceSearch = new ManagementSearch(this.frmMan, this);
        serviceWorker = new ManagementWorker(this.frmMan);
        serviceDosimeter = new ManagementDosimeter(this.frmMan);
        serviceDsmtNotes = new ManagementDsmtNotes(this.frmMan);
        serviceDose = new ManagementDose(this.frmMan);
        serviceDoseNotes = new ManagementDoseNotes(this.frmMan);
        serviceDsmtHist = new ManagementDsmtHist(this.frmMan);

        addListeners();

    }

    @Override
    public void actionPerformed(ActionEvent aevent) {

        String command = aevent.getActionCommand();

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
            case "btNewWorker":
                serviceWorker.newWorker();
                break;
            case "btSaveWorkerNew":
                serviceWorker.saveNewWorker();
                break;
            case "btUpdateWorker":
                serviceWorker.updateWorker();
                break;
            case "btSaveWorkerUpdate":
                serviceWorker.saveUpdateWorker();
                break;
            case "btWorkerCancel":
                serviceWorker.btWorkerCancel();
                break;

            // dosimeter          
            case "btDosimeterInfoNew":
                serviceDosimeter.newDosimeter();
                break;
            case "btDosimeterInfoSaveNew":
                serviceBtns.setAllCancelBts();
                serviceDosimeter.saveNewDsmt();
                break;
            case "btDosimeterInfoUpdate":
                serviceDosimeter.updateDosimeter();
                break;
            case "btDosimeterInfoSaveUpdate":
                serviceBtns.setAllCancelBts();
                serviceDosimeter.saveUpdateDsmt();
                break;
            case "btDosimeterInfoCancel":
                this.frmMan.getTxtInfoAction().setText("Action on dosimeter cancelled");
  //              serviceBtns.setDsmtCancelBts();
                serviceDosimeter.fillWokerDsmtInfoCancel();
            //    serviceDosimeter.fillWokerDsmtInfo();
                break;

            // dosimeter Notes      
            case "cbDosimeterNotesIndex":
                serviceSearch.fillDosimeterNotesInfo();
                break;
            case "btNewNoteDosimeter":
                serviceDsmtNotes.newDsmtNote();
                break;
            case "btSaveNewDsmtNote":
                serviceBtns.setAllCancelBts();
                serviceDsmtNotes.saveNewDsmtNote();
                
                break;
            case "btUpdateNoteDosimeter":
                serviceDsmtNotes.updateDsmtNote();
                break;
            case "btSaveUpdateDsmtNote":
                serviceBtns.setAllCancelBts();
                serviceDsmtNotes.saveUpdateDsmtNote();

                break;
            case "btCancelDsmtNote":
                this.frmMan.getTxtInfoAction().setText("Action on dosimeter note cancelled");
                serviceDsmtNotes.cancelDsmtNote();

                break;

            // dose          
            case "btDoseNew":
                serviceDose.newDose();
                break;
            case "btDoseInfoSaveNew":
                serviceBtns.setAllCancelBts();
                serviceDose.saveNewDose();
                break;
            case "btDoseInfoUpdate":
                serviceDose.updateDose();
                break;
            case "btDoseInfoSaveUpdate":
                serviceBtns.setAllCancelBts();
                serviceDose.saveUpdateDose();
                break;
            case "btDoseInfoCancel":
                this.frmMan.getTxtInfoAction().setText("Action on dose canceled");
         //       serviceBtns.setAllCancelBts();
         //       serviceDose.fillWokerDoseInfo();
                serviceDose.fillWokerDoseInfoCancel();
                break;

            // dose note
            case "cbDoseNoteIndex":
                serviceSearch.fillDoseNotesInfo();
                break;
            case "btNewDoseNote":
                serviceDoseNotes.newDoseNote();
                break;
            case "btSaveNewDoseNote":
                serviceBtns.setAllCancelBts();
                serviceDoseNotes.saveNewDoseNote();
                break;
            case "btUpdateDoseNote":
                serviceDoseNotes.updateDoseNote();
                break;
            case "btSaveUpdateDoseNote":
                serviceBtns.setAllCancelBts();
                serviceDoseNotes.saveUpdateDoseNote();
                break;
            case "btCancelDoseNote":
                this.frmMan.getTxtInfoAction().setText("Action on dose note canceled");
                serviceDoseNotes.cancelDoseNote();
                break;


        }
    }

    /**
     *
     */
    public void addListeners() {

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
                tableDoseInfoMouseClicked(evt);
            }
        });

        frmMan.tableDosimeterInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDosimeterInfoMouseClicked(evt);
            }
        });
        
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
   
        if (this.frmMan.btWorkerCancel.isEnabled()) {
            return;
        }
        
        if (this.frmMan.btDoseInfoCancel.isEnabled()) {
            return;
        }
        
        if (this.frmMan.btDosimeterInfoCancel.isEnabled()) {
            return;
        }
        
        if (this.frmMan.btCancelDsmtNote.isEnabled()) {
            return;
        }

        if (this.frmMan.btCancelDoseNote.isEnabled()) {
            return;
        }

        
        
        if (mevent.getClickCount() == 2) {
            serviceClean.cleanAllInfo();
            serviceBtns.setAllSearchClearBts();
            frmMan.btWorkerNew.setEnabled(true);
            frmMan.btWorkerUpdate.setEnabled(true);
 //           serviceBtns.setDosimeterBtsSearch(true);
 //           serviceBtns.setDoseBtsSearch(true);
            
            serviceSearch.fillAllManagement();
            this.frmMan.getTxtInfoAction().setText("");

        }

    }

    public void tableDoseInfoMouseClicked(MouseEvent mevent) {

        if (!this.frmMan.btDoseInfoUpdate.isEnabled()) {
            return;
        }

        if (!this.frmMan.tableDoseInfo.isEnabled()) {
            return;
        }

        ArrayList2D doseNoteInfo = new ArrayList2D();

        // info dose-note selected for update +1
        for (int i = 0; i < 5; i++) {
            doseNoteInfo.Add(null, 0);
        }

        dbPkIDs.setDoseNotes_id(doseNoteInfo);

        serviceSearch.fillDoseNotesCBIndex();

    }

    public void tableDosimeterInfoMouseClicked(MouseEvent mevent) {

        if (!this.frmMan.btDosimeterInfoUpdate.isEnabled()) {
            return;
        }

        if (!this.frmMan.tableDosimeterInfo.isEnabled()) {
            return;
        }

        ArrayList2D dsmtNoteInfo = new ArrayList2D();

        // info dose-note selected for update
        for (int i = 0; i < 5; i++) {
            dsmtNoteInfo.Add(null, 0);
        }

        dbPkIDs.setDsmtNotes_id(dsmtNoteInfo);

        serviceSearch.fillDosimeterNotesCBIndex();
 
        
        // fill dsmt hist table
        
        int row = frmMan.tableDosimeterInfo.getSelectedRow();
        serviceDsmtHist.dsmtHist(dbPkIDs.getDsmt_id().get(row)[0].toString());
        

    }

    // generic listeners
    @Override
    public void mouseClicked(MouseEvent me) {
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
