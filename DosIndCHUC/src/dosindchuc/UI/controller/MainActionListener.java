/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.MainFrm;
import java.awt.event.ActionListener;

/**
 *
 * @author ir
 */
public class MainActionListener implements ActionListener{
    
    
    private MainFrm frm;
    private LoginService service;
 
    public MainActionListener(MainFrm frm) {
        this.frm = frm;
        service = new LoginService();
        addListeners();
 //       inicializaTableModel();
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
    
    
    
}
