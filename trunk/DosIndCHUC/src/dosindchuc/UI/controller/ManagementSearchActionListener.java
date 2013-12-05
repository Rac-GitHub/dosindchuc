/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

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

    
    public ManagementFrm frmMan;
    public ManagementSearch serviceSearch;
    private ManagementWorker serviceWorker;
    public Object [][] workerList = null;
    public ActionListener Listeners;

 //   private 
    
    
    public ManagementSearchActionListener(ManagementFrm frmMan) {
     
        this.frmMan = frmMan;
        Listeners = this;
        
        serviceSearch = new ManagementSearch(this.frmMan, this);
        serviceWorker = new ManagementWorker(this.frmMan, this);
        
        addListeners();
 //       initState();
 //       inicializaTableModel();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent aevent) {
        
        
        System.out.println(aevent);
        
        String command = aevent.getActionCommand();
        
        System.out.println(command);
        
        if (command.equalsIgnoreCase("searchManagement")) {
            
            serviceSearch.searchWorkers();
          
        } else if (command.equalsIgnoreCase("cleanManagement")) {
           
            serviceSearch.cleanWorkers();
            
        } else if (command.equalsIgnoreCase("cbDoseNoteIndex")) {
           
            serviceSearch.fillDoseNotesInfo();
            
        } else if (command.equalsIgnoreCase("cbDosimeterNotesIndex")) {
           
            serviceSearch.fillDosimeterNotesInfo();
            
            
           /*   for worker */ 
            
        } else if (command.equalsIgnoreCase("btNewWorker")) {
           
            serviceWorker.newWorker();
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
        
        
     
    }

    
    
    public void searchTableMouseClicked(MouseEvent mevent) {
        
        System.out.println(mevent);
        
        if (mevent.getClickCount() == 2) {
            
            
            serviceSearch.fillAllManagement();
        }    
        
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
