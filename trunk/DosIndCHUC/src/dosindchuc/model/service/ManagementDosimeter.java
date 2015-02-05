/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementTablesModel;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.globals.Tbl_dosimeters;
import dosindchuc.model.dao.DosimeterDao;
import dosindchuc.model.dao.DsmtHistDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Dsmt_hist;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import java.awt.Color;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementDosimeter {

    private MainFrm frmMain;
    private ManagementFrm frmMan;
    private DosimeterDao dsmtdao;
    private DsmtHistDao dsmtHistdao;
    private DbPkIDs dbPkIDs;
    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementTablesModel tableModel;
    private ManagementSearch setDsmtInfo;
    private JTable table;

    public ManagementDosimeter(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        dbPkIDs = new DbPkIDs();
        dsmtdao = new DosimeterDao();
        dsmtHistdao = new DsmtHistDao();
        tableModel = new ManagementTablesModel(this.frmMain, this.frmMan);
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setDsmtInfo = new ManagementSearch(this.frmMan, null);

    }

    /* ############################################### */
    /*                                                  */
    /*              dosimeter  info                       */
    /*                                                  */
    /* ###############################################  */
    private Dosimeter getDsmtInfo(String newOrUpdate) {

        Dosimeter dsmt = new Dosimeter();
        int row = 0;

        table = this.frmMan.tableDosimeterInfo;

        if (!newOrUpdate.equalsIgnoreCase("new")) {
            row = table.getSelectedRow();
            dsmt.setPk_dsmt(dbPkIDs.getDsmt_id().get(row)[0].toString());
        }

        dsmt.setId(table.getValueAt(row, 0) == null ? "" : table.getValueAt(row, 0).toString());
        dsmt.setLabel(table.getValueAt(row, 1) == null ? "" : table.getValueAt(row, 1).toString());
        dsmt.setType(SetEnums.dsmt_type.valueOf(table.getValueAt(row, 2).toString()));
        dsmt.setPeriodicity(SetEnums.dsmt_periodicity.valueOf(table.getValueAt(row, 3).toString()));
        dsmt.setSupplier(SetEnums.dsmt_supplier.valueOf(table.getValueAt(row, 4).toString()));
        dsmt.setComments(table.getValueAt(row, 6) == null ? "" : table.getValueAt(row, 6).toString());
        dsmt.setStatus(SetEnums.dsmt_status.valueOf(table.getValueAt(row, 7).toString()));

        if (newOrUpdate.equalsIgnoreCase("new")) {
//      creation date and time
            dsmt.setTimestamp(dateAndTime.currDateTime());
            dsmt.setLastchange(dateAndTime.currDateTime());
        } else {
            dsmt.setTimestamp(table.getValueAt(row, 5) == null ? "" : table.getValueAt(row, 5).toString());
        }

        return dsmt;

    }

    public void newDosimeter() {

        tableModel.setDefaultDsmtTable("newdsmt");

        setButtonsState.setDosimeterBtsNew(true);
        setCleanState.cleanDsmtNotes();
        setCleanState.cleanDsmtHist();

        this.frmMan.getTxtInfoAction().setText("Inserting a New dosimeter");

    }

    // insert into the database 
    public void saveNewDsmt() {

        Dosimeter dsmt = getDsmtInfo("new");

        String worker_id = dbPkIDs.getWorker_id();
        dsmt.setPk_id(worker_id);

        String id = dsmtdao.insertDosimeter(dsmt);

        if (id.equals("0")) {

            this.frmMan.getTxtInfoAction().setText("New dosimeter not inserted into database");

        } else {

            dsmt.setPk_dsmt(id);

            recordHist(dsmt, id, -1);

            this.frmMan.getTxtInfoAction().setText("Dosimeter with id= " + dsmt.getId() + " saved into database");
            
                    
            if (dsmt.getStatus().toString().equalsIgnoreCase("Activo")) {
                this.frmMan.btDoseInfoNew.setEnabled(true);
            }

        }
        // actualiza info
        fillWokerDsmtInfo();

    }

    /**
     *
     */
    public void updateDosimeter() {

        table = this.frmMan.tableDosimeterInfo;
        int row = table.getSelectedRow();

        dbPkIDs.setDsmtRowSelected(row);

        if (!(row > -1)) {
            return;
        }

        setButtonsState.setDosimeterBtsUpdate(true);
        setCleanState.cleanDosimeter();
        setDsmtInfo.fillDosimeterInfo(dbPkIDs.getWorker_id(), "update");

        this.frmMan.getTxtInfoAction().setText("Updating Dosimeter info");
        table.setSelectionForeground(Color.blue);


    }

    public void saveUpdateDsmt() {

        table = this.frmMan.tableDosimeterInfo;
        int row = table.getSelectedRow();

        int rowToUpdate = dbPkIDs.getDsmtRowSelected();

        if (row != rowToUpdate) {
            this.frmMan.getTxtInfoAction().setText("Nothing to be done: Selected row differs from row to be updated");
            // actualiza info
            fillWokerDsmtInfo();
            return;
        }


        Dosimeter dsmt = getDsmtInfo("update");

        String dsmt_id = dsmt.getPk_dsmt();

        dsmt.setPk_id(dbPkIDs.getWorker_id());

        dsmtdao.updateDosimeter(dsmt, dsmt_id);

        this.frmMan.getTxtInfoAction().setText("Dosimeter with id= " + dsmt.getId() + " updated into database");

        recordHist(dsmt, dsmt_id, rowToUpdate);


        // actualiza info
        fillWokerDsmtInfo();

    }

    /* 
     * history
     */
    public void fillWokerDsmtInfo() {

        // actualiza info
        setDsmtInfo.fillAllManagementAfterSave(dbPkIDs.getWorker_id());
/*        setCleanState.cleanDosimeter();
        setDsmtInfo.fillDosimeterInfo(dbPkIDs.getWorker_id(), "new");
        setCleanState.cleanDose();
        setDsmtInfo.fillDoseInfo(dbPkIDs.getWorker_id(), "new"); */


        //     setButtonsState.setDosimeterBtsSearch(true);


    }

    public void fillWokerDsmtInfoCancel() {

        // actualiza info
        setCleanState.cleanDosimeter();
        setDsmtInfo.fillDosimeterInfo(dbPkIDs.getWorker_id(), "list");

        setButtonsState.setDosimeterBtsCancel(false);


        if (!dbPkIDs.getDsmt_id().isEmpty()) {

            for (int i = 0; i < dbPkIDs.getDsmt_id().size(); i++) {
/*         Object dsmtInfo[] = new Object[]{dosimeter.getPk_dsmt(), dosimeter.getId(), dosimeter.getLabel(), 
                dosimeter.getType(), dosimeter.getPeriodicity(), dosimeter.getSupplier(), dosimeter.getComments(), 
                dosimeter.getStatus()}; */

                if (dbPkIDs.getDsmt_id().get(i)[7].toString().equalsIgnoreCase("Activo")) {
                    this.frmMan.btDoseInfoNew.setEnabled(true);
                }
            }
        }

        if (!dbPkIDs.getDose_id().isEmpty()) {
            this.frmMan.btDoseInfoUpdate.setEnabled(true);
        }

//        dbPkIDs.getDsmt_id().size();

        //     setButtonsState .setDosimeterBtsSearch(true);


    }

    
    
    /* 
     * history
     */
    private void recordHist(Dosimeter dsmt, String dsmt_id, int dsmtRow) {


        System.out.println(" .... -> dsmtRow -- > " + dsmtRow);

        String value[] = new String[]{dsmt.getId(), dsmt.getLabel(), dsmt.getType().toString(), dsmt.getPeriodicity().toString(),
            dsmt.getSupplier().toString(), dsmt.getComments(), dsmt.getStatus().toString()};

        switch (dsmtRow) {

            case -1:
                for (int i = 0; i < Tbl_dosimeters.nrHist; i++) {
                    String id_change = Tbl_dosimeters.parmHist[i];
                    saveDsmtHist(dsmt_id, id_change, value[i]);
                }
                break;

            default:

                for (int i = 0; i < Tbl_dosimeters.nrHist; i++) {

                    //System.out.println(" .... -> values old -- > " + dbPkIDs.getDsmt_id().get(1)[i + 2]);
                    System.out.println(" .... -> values old -- > " + dsmtRow + "nrHist  = " + Tbl_dosimeters.nrHist +
                            " --i  =  " + i + "  value -- " +
                            dbPkIDs.getDsmt_id().get(dsmtRow)[i + 1]);

                    if (!dbPkIDs.getDsmt_id().get(dsmtRow)[i + 1].toString().contains(value[i])) {
                        String id_change = Tbl_dosimeters.parmHist[i];
                        saveDsmtHist(dsmt_id, id_change, value[i]);
                    }

                }
                break;

        }


    }

    private void saveDsmtHist(String dsmt_id, String id_change, String value) {

        Dsmt_hist dsmtHist = new Dsmt_hist();

        dsmtHist.setPk_dsmt(dsmt_id);
        dsmtHist.setId_change(id_change);
        dsmtHist.setValue(value);

        dsmtHistdao.insertDsmtHist(dsmtHist);

    }
}
