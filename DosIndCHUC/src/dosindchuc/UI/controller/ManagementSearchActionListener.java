/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.service.ManagementSearch;
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
            
        }
        
        
    }
    
      /**
     *
     */
    public void addListeners() {
        
        frmMan.getSearchBtSeach().addActionListener(this);
        frmMan.getSearchBtClean().addActionListener(this);
        frmMan.getSearchTable().addMouseListener(this);
        
    }
      
      
      private void initState () {
    
          
 //         DisablePanel.disable();
                
    } 

    @Override
    public void mouseClicked(MouseEvent mevent) {
        
        System.out.println(mevent);
        
        if (mevent.getClickCount() == 2) {
            
            service.fillAllManagement(); //, workerList);

        }    
        
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
