/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.UI.swing.Help.DIVButtons;
import dosindchuc.UI.swing.Help.DIVTablesModel;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.service.DIVService;
import dosindchuc.model.service.Help.YearMonthAndTrimester;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener1;

/**
 *
 * @author ir
 */
public final class DIVActionListener implements ActionListener, MouseListener {

    private DIVFrm frmDIV;
    private DIVService serviceDIV;
    private DIVButtons divBtns;
    public DbPkIDs dbPkIDs;
    private YearMonthAndTrimester yearMonthTrimester;
    public Object[][] workerList = null;
    /**
     *
     */
  //  public ActionListener Listeners;
    private DIVTablesModel infoDIVTables;

    public DIVActionListener(DIVFrm frmDIV) {

        this.frmDIV = frmDIV;

//        Listeners = this;

        divBtns = new DIVButtons(this.frmDIV);
        dbPkIDs = new DbPkIDs();
        serviceDIV = new DIVService(this.frmDIV);

        infoDIVTables = new DIVTablesModel(this.frmDIV);

        addListeners();

    }

    @Override
    public void actionPerformed(ActionEvent aevent) {

        String command = aevent.getActionCommand();

        switch (command) {

            case "btDIV_Search":
                serviceDIV.searchDIVInfo();
                break;
            case "btDIV_Clean":
                serviceDIV.removeDIVTables();
                frmDIV.txtWorkerNameDIV.setText("");
                divBtns.setAllDIVBtsSaveCancel(false);
                break;

            case "btDIV_Save":
                serviceDIV.saveDIVs();
                break;
            case "btDIV_Cancel":
                serviceDIV.removeDIVTables();
                frmDIV.txtWorkerNameDIV.setText("");
                serviceDIV.searchDIVInfo();
                break;

        }

    }

    /**
     *
     */
    public void addListeners() {

        frmDIV.btDIV_Search.addActionListener(this);
        frmDIV.btDIV_Clean.addActionListener(this);

        frmDIV.btDIV_Save.addActionListener(this);
        frmDIV.btDIV_Cancel.addActionListener(this);

        frmDIV.comboDsmtID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {

                    int selectedRow = infoDIVTables.getTableNewDIVinfo().getSelectedRow();

                    if (!(selectedRow == -1)) {

                        int colSetPerd = 8;
                        int colPerd = 2;

                        yearMonthTrimester = new YearMonthAndTrimester();

                        SetEnums.month Month[] = SetEnums.month.values();
                        SetEnums.Trimester Trimester[] = SetEnums.Trimester.values();

                        int indexSelectedItem = frmDIV.comboDsmtID.getSelectedIndex();

                        String newPerd = DbPkIDs.getAllDsmtPeriod().get(selectedRow).get(indexSelectedItem);

                        infoDIVTables.getModelTableNewDIVinfo().setValueAt(newPerd, selectedRow, colPerd);

                        switch (newPerd) {
                            case "Mensal":
                                infoDIVTables.getModelTableNewDIVinfo().setValueAt(Month[yearMonthTrimester.Month()], selectedRow, colSetPerd);
                                infoDIVTables.getModelTableNewDIVinfo().setValueAt(yearMonthTrimester.Year(), selectedRow, colSetPerd + 1);
                                break;
                            case "Trimestral":
                                infoDIVTables.getModelTableNewDIVinfo().setValueAt(Trimester[yearMonthTrimester.Trimester()], selectedRow, colSetPerd);
                                infoDIVTables.getModelTableNewDIVinfo().setValueAt(yearMonthTrimester.Year(), selectedRow, colSetPerd + 1);
                                break;

                        }
                    }
                }
            }
        });


    }

    public void addNoteTableListeners() {

        infoDIVTables.setSettingsNewDIVinfoTable("newTable");
        infoDIVTables.getTableNewDIVinfo().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noteTableMouseClicked(evt);
            }
        });


    }

    public void noteTableMouseClicked(MouseEvent mevent) {
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
