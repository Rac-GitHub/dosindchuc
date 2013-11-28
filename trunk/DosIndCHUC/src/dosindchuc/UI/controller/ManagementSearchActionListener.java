/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.entities.Dose_info;
import dosindchuc.model.service.ManagementSearch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


/**
 *
 * @author ir
 */
public final class ManagementSearchActionListener implements ActionListener, MouseListener {

    
    public ManagementFrm frmMan;
    public ManagementSearch service;
    public Object [][] workerList = null;
 //   private 
    
    
    public ManagementSearchActionListener(ManagementFrm frmMan) {
     
        this.frmMan = frmMan;
        service = new ManagementSearch(this.frmMan);
        addListeners();
        initState();
 //       inicializaTableModel();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent aevent) {
        
        
        System.out.println(aevent);
        
        String command = aevent.getActionCommand();
        
        if (command.equalsIgnoreCase("searchManagement")) {
            
            service.searchWorkers();
          
        } else if (command.equalsIgnoreCase("cleanManagement")) {
           
            service.cleanWorkers();
            
        } else if (command.equalsIgnoreCase("cbDoseNoteIndex")) {
           
            service.fillDoseNotesInfo();
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
   //     frmMan.searchTable.addMouseListener(this);
        
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
        
        
        
    }
      
      
      private void initState () {
    
          frmMan.getCbDoseNoteIndex().setEnabled(false);
          frmMan.getCbDosimeterNotesIndex().setEnabled(false);
          
 //         DisablePanel.disable();
                
    } 


    public void searchTableMouseClicked(MouseEvent mevent) {
        
        System.out.println(mevent);
        
        if (mevent.getClickCount() == 2) {
            
            service.fillAllManagement(); //, workerList);

        }    
        
    }
    
    public void tableDoseInfoMouseClicked(MouseEvent mevent) {
        
        System.out.println(mevent);
        
     //   List<Dose_info> dose_info = null;
        
        service.fillDoseNotesCBIndex(); //, workerList);
        
        
        
        

        }    
        
    public void tableDosimeterInfoMouseClicked(MouseEvent mevent) {
        
        System.out.println(mevent);
        
     //   List<Dose_info> dose_info = null;
        
        service.fillDosimeterNotesCBIndex(); //, workerList);
        
        
        
        

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
