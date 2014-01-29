/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.UsersDao;
import dosindchuc.model.service.AlertNotesService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author ir
 */
public final class MainActionListener implements ActionListener {
    
    
    private MainFrm frm;
    private UsersDao service;
    private AlertNotesService alertNoteService;
 
 //   private DisablePanel panel;
 
    
    public MainActionListener(MainFrm frm) {
        
        this.frm = frm;
        service = new UsersDao();
        alertNoteService = new AlertNotesService(this.frm);
        addListeners();
        initState();
        
 //       inicializaTableModel();
    }
 
    
    
//
    @Override
    public void actionPerformed(ActionEvent aevent) {
      
        System.out.println(aevent);

        String command = aevent.getActionCommand();

        if (command.equalsIgnoreCase("LoginOK")) {
            login();

        } else if (command.equalsIgnoreCase("LoginCancel")) {
            this.frm.txtUsername.setText(null);
            this.frm.txtPassword.setText(null);

        } else if (command.equalsIgnoreCase("LoginCancel")) {
            this.frm.txtUsername.setText(null);
            this.frm.txtPassword.setText(null);

        } else if (command.equalsIgnoreCase("Management")) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ManagementFrm frmMan = new ManagementFrm();
                frmMan.setVisible(true);
                    //             panel.disable(frmMan.panelWorkerInfo);   // nao funciona
            }
         });

         }
        
    }
    
    

    public void addListeners() {
        frm.getBtLoginOk().addActionListener(this);
        frm.getBtLoginCancel().addActionListener(this);
        frm.getBtProfInfo().addActionListener(this);
        frm.getBtInsertIndDosimetry().addActionListener(this);
        frm.getBtAdministration().addActionListener(this);
        frm.getBtNoteSave().addActionListener(this);
        frm.getBtNoteCancel().addActionListener(this);
        frm.getTxtUsername().addActionListener(this);
        frm.getTxtPassword().addActionListener(this);
        
        frm.NoteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noteTableInfoMouseClicked(evt);
            }
        });
        
    }

    
    
    private void initState () {
        
        this.frm.getBtProfInfo().setEnabled(false);
        this.frm.getBtInsertIndDosimetry().setEnabled(false);
        this.frm.getBtAdministration().setEnabled(false);
        this.frm.txtNameOfUser.setEditable(false);
        this.frm.getBtNoteSave().setEnabled(false);
        this.frm.getBtNoteCancel().setEnabled(false);
                
    } 
 
        
    public void login() { 
    /* Acciones formulario login */
         
         String NameOfUser = this.service.loginUsers(this.frm.getTxtUsername().getText(),this.frm.getTxtPassword().getText());
        
         
        {            
            if( NameOfUser != null ) {
 
                this.frm.getBtProfInfo().setEnabled(true);
                this.frm.getBtInsertIndDosimetry().setEnabled(true);
                this.frm.getBtAdministration().setEnabled(true);
               
                this.frm.txtNameOfUser.setText(NameOfUser);
                this.frm.txtNameOfUser.setEditable(false);
                this.frm.txtUsername.setText(null);
                this.frm.txtPassword.setText(null);
                
                alertNoteService.fillAlertNotesTable();
                
            } else {   
                this.frm.txtUsername.setText(null);
                this.frm.txtPassword.setText(null);
                JOptionPane.showMessageDialog(this.frm,"Incorrect username or password ");
             }  
        }
        
    }
    
    
     public void noteTableInfoMouseClicked(MouseEvent mevent) {
     
   /*     if ( ! this.frmMan.btDoseInfoUpdate.isEnabled() ) {
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

        serviceSearch.fillDoseNotesCBIndex(); */
 
         alertNoteService.editAlertNotesTable();
         
        
    }    
 
    
    
}
