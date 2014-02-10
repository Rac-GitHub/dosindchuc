/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.Help.ManagementTablesModel;
import dosindchuc.UI.swing.Help.AlertTableWithRowRendering;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.UsersDao;
import dosindchuc.model.service.AlertNotesService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ir
 */
public final class MainActionListener implements ActionListener, MouseListener {
    
    
    private MainFrm frm;
    private UsersDao service;
    private AlertNotesService alertNoteService;
    private AlertTableWithRowRendering alertNotesTable;

 
 //   private DisablePanel panel;
 
    
    public MainActionListener(MainFrm frm) {
        
        this.frm = frm;
        service = new UsersDao();
        alertNoteService = new AlertNotesService(this.frm);
        alertNotesTable = new AlertTableWithRowRendering(this.frm);
        addListeners();
        initState();
 
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

         } else if (command.equalsIgnoreCase("btNoteSave")) {
          
             alertNoteService.saveAlertNotesTable();
             
         }  else if (command.equalsIgnoreCase("btNoteCancel")) {
          
             alertNoteService.cancelAlertNotesTable();
             
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
        
         System.out.println("teste ---- > " + frm.jScrollPane1.getComponentCount()); // 
    
    }
    
    public void addNoteTableListeners() {

        alertNotesTable.setSettingsAlertTable("newToActionListeners");

        System.out.println("  Que caracas -TAble-- >>>> " + alertNotesTable.getNoteTable());
        System.out.println("  Que caracas -MODEL -- >>>> " + alertNotesTable.getModelAlertTable());
        alertNotesTable.getNoteTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                noteTableMouseClicked(evt);
            }
        });


    }

          
    public void noteTableMouseClicked(MouseEvent mevent) {

        int colSelected = alertNotesTable.getNoteTable().getSelectedColumn();

        System.out.print(" Column selected --- >   " + colSelected);

        if (!(colSelected == 8)) {

            int rowSelected = alertNotesTable.getNoteTable().getSelectedRow();
            
            System.out.print(" Row selected --- >   " + rowSelected);

            if (!((Boolean) alertNotesTable.getNoteTable().getValueAt(rowSelected, 8)) && (colSelected != -1)) {

                alertNotesTable.getNoteTable().setValueAt(Boolean.TRUE, rowSelected, 8);
//
//                this.frm.btNoteSave.setEnabled(true);
//                this.frm.btNoteCancel.setEnabled(true);
            }

        }

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
                
                
                // alert note table
                System.out.println("  ANTES  ");
                addNoteTableListeners();
                System.out.println(" LOgin --- actiolIsterner TABLE  --- > " + alertNotesTable.getNoteTable());
                
         //       alertNotetable fill.
                alertNoteService.fillAlertNotesTable();
             
  
                
            } else {   
                this.frm.txtUsername.setText(null);
                this.frm.txtPassword.setText(null);
                JOptionPane.showMessageDialog(this.frm,"Incorrect username or password ");
             }  
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
       
    
}
