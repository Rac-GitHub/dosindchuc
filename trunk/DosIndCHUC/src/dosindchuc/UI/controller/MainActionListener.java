/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.UI.swing.Help.AlertTableInMainFrm;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.UsersDao;
import dosindchuc.model.entities.Users;
import dosindchuc.model.service.AlertNotesService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ir
 */
public final class MainActionListener implements ActionListener, MouseListener {

    private MainFrm frm;
    private UsersDao service;
    private AlertNotesService alertNoteService;
    private AlertTableInMainFrm alertNotesTable;

    public MainActionListener(MainFrm frm) {

        this.frm = frm;

        service = new UsersDao();
        alertNoteService = new AlertNotesService(this.frm);
        alertNotesTable = new AlertTableInMainFrm(this.frm);

        addListeners();
        initState();

    }

//
    @Override
    public void actionPerformed(ActionEvent aevent) {

        String command = aevent.getActionCommand();

        if (command.equalsIgnoreCase("LoginOK")) {
            login();

        } else if (command.equalsIgnoreCase("LoginCancel")) {
            
            this.frm.getBtLoginOk().setEnabled(true);
            this.frm.txtUsername.setEditable(true);
            this.frm.txtUsername.setText(null);
            this.frm.txtPassword.setEditable(true);
            this.frm.txtPassword.setText(null);
            initState();
            alertNotesTable.setSettingsAlertTable("newToActionListeners");
            

        } else if (command.equalsIgnoreCase("Management")) {

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ManagementFrm frmMan = new ManagementFrm();
                    frmMan.setVisible(true);
                }
            });

            MainFrm.btProfInfo.setEnabled(false);

        } else if (command.equalsIgnoreCase("btNoteSave")) {

            alertNoteService.saveAlertNotesTable();

        } else if (command.equalsIgnoreCase("btNoteCancel")) {

            alertNoteService.cancelAlertNotesTable();

        } else if (command.equalsIgnoreCase("InsertNewDIV")) {

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    DIVFrm frmDIV = new DIVFrm();
                    frmDIV.setVisible(true);

                }
            });

            MainFrm.btInsertIndDosimetry.setEnabled(false);

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

    }

    public void addNoteTableListeners() {

        alertNotesTable.setSettingsAlertTable("newToActionListeners");
        alertNotesTable.getNoteTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                noteTableMouseClicked(evt);
            }
        });


    }

    public void noteTableMouseClicked(MouseEvent mevent) {

        int colSelected = alertNotesTable.getNoteTable().getSelectedColumn();

        if (!(colSelected == 8)) {

            int rowSelected = alertNotesTable.getNoteTable().getSelectedRow();

            if (!((Boolean) alertNotesTable.getNoteTable().getValueAt(rowSelected, 8)) && (colSelected != -1)) {
                alertNotesTable.getNoteTable().setValueAt(Boolean.TRUE, rowSelected, 8);
            }

        }

    }

    private void initState() {

        this.frm.getBtProfInfo().setEnabled(false);
        this.frm.getBtInsertIndDosimetry().setEnabled(false);
        this.frm.getBtAdministration().setEnabled(false);
        this.frm.txtNameOfUser.setEditable(false);
        this.frm.getBtNoteSave().setEnabled(false);
        this.frm.getBtNoteCancel().setEnabled(false);

    }

    public void login() {

        List<Users> users = this.service.loginUsers(this.frm.getTxtUsername().getText(), this.frm.getTxtPassword().getText());

        if (users.size() > 0) {

            String NameOfUser = users.get(0).getName();

            this.frm.getBtProfInfo().setEnabled(true);
            this.frm.getBtInsertIndDosimetry().setEnabled(true);

            if (users.get(0).getAdmin().equals("Y")) {
                this.frm.getBtAdministration().setEnabled(true);
            }

            this.frm.txtNameOfUser.setText(NameOfUser);
            this.frm.txtNameOfUser.setEditable(false);
            this.frm.txtUsername.setText(null);
            this.frm.txtPassword.setText(null);

            // alert note table
            addNoteTableListeners();
            // alertNotetable fill.
            alertNoteService.fillAlertNotesTable();

            // disable buttons and input fields for new re-login before cancel
            this.frm.getBtLoginOk().setEnabled(false);
            this.frm.txtUsername.setEditable(false);
            this.frm.txtPassword.setEditable(false);


        } else {
            this.frm.txtUsername.setText(null);
            this.frm.txtPassword.setText(null);
            JOptionPane.showMessageDialog(this.frm, "Incorrect username or password ");
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
