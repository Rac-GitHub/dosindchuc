/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.Management;
import dosindchuc.model.dao.UsersDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ir
 */
public final class MainActionListener implements ActionListener{
    
    
    private MainFrm frm;
    private Management frmMan;
    private UsersDao service;
 
    public MainActionListener(MainFrm frm) {
     
        this.frm = frm;
        service = new UsersDao();
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
                this.frmMan.setVisible(true);
                
        }
        
    }
    
    
    
//    public void inicializaTableModel() {
//        List<Paciente> pacientes = service.getPacientes();
//        tableModel = new PacienteTableModel(pacientes);
//        frm.getTbPacientes().setModel(tableModel);
//        frm.getTbPacientes()
//                .getSelectionModel()
//                .addListSelectionListener( this );
//    }
    
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
            } else {   
                this.frm.txtUsername.setText(null);
                this.frm.txtPassword.setText(null);
               JOptionPane.showMessageDialog(this.frm,"Incorrect username or password ");
             }  
        }
    
              
        
    }
    
    
}
