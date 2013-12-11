/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.ManagementFrm;
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
public final class ManagementSearchActionListener implements ActionListener, MouseListener {

    
    private ManagementFrm frmMan;
    private ManagementClean serviceClean;
    private ManagementSearch serviceSearch;
    private ManagementWorker serviceWorker;
    public Object [][] workerList = null;
    public ActionListener Listeners;
    private String worker_id = null;

 //   private 
    
    
    public ManagementSearchActionListener(ManagementFrm frmMan) {
     
        this.frmMan = frmMan;
        Listeners = this;
        
        serviceClean = new ManagementClean(this.frmMan);
        serviceSearch = new ManagementSearch(this.frmMan, this);
        serviceWorker = new ManagementWorker(this.frmMan);
        
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
            case "cleanManagement":  serviceClean.cleanAllInfoWithSearch();
                     break;
            case "cbDoseNoteIndex":  serviceSearch.fillDoseNotesInfo();
                     break;
            case "cbDosimeterNotesIndex":  serviceSearch.fillDosimeterNotesInfo();
                     break;
            case "btNewWorker":  serviceWorker.newWorker();
                     break;
            case "btSaveWorkerNew":  worker_id = serviceWorker.saveNewWorker();
                     break;
            case "btUpdateWorker":  serviceWorker.updateWorker();
                     break;
            case "btSaveWorkerUpdate":  serviceWorker.saveUpdateWorker(worker_id);
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
                System.out.println("evento tabela::::  " + evt);
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
        
        
     
    }

    
    
    public String searchTableMouseClicked(MouseEvent mevent) {
 
        worker_id = null;
        if (mevent.getClickCount() == 2) {
            worker_id = serviceSearch.fillAllManagement();
            frmMan.btWorkerUpdate.setEnabled(true);
  
        }    
        
        return worker_id;
        
    }
    
    public void tableDoseInfoMouseClicked(MouseEvent mevent) {
 
        serviceSearch.fillDoseNotesCBIndex();
 
        }    
 
    
    public void tableDosimeterInfoMouseClicked(MouseEvent mevent) {
        
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
