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
import dosindchuc.model.dao.DoseDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dose;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementDose {

    private MainFrm frmMain;
    private ManagementFrm frmMan;
    private DoseDao dosedao;
    private DbPkIDs dbPkIDs;
    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementTablesModel tableModel;
    private ManagementSearch setDoseInfo;
    private JTable table;
    private ManagementCumulDose setCumulDose;

    public ManagementDose(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        dbPkIDs = new DbPkIDs();
        dosedao = new DoseDao();
        tableModel = new ManagementTablesModel(this.frmMain, this.frmMan);
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setDoseInfo = new ManagementSearch(this.frmMan, null);
        setCumulDose = new ManagementCumulDose(frmMan);

    }

    /* ############################################### */
    /*                                                  */
    /*              dose  info                       */
    /*                                                  */
    /* ###############################################  */
    private Dose getDoseInfo(String newOrUpdate) {

        Dose dose = new Dose();
        int row = 0;

        table = this.frmMan.tableDoseInfo;

        if (!newOrUpdate.equalsIgnoreCase("new")) {
            row = table.getSelectedRow();
            dose.setPk_dose(dbPkIDs.getDose_id().get(row).toString());

        }

        String idDsmt = table.getValueAt(row, 0).toString();
        dose.setDsmt_id(idDsmt);
        
        
    /*    Object dsmtInfo[] = new Object[]{dosimeter.getPk_dsmt(), dosimeter.getId(), dosimeter.getLabel(), 
                dosimeter.getType(), dosimeter.getPeriodicity(), dosimeter.getSupplier(), dosimeter.getComments(), 
                dosimeter.getStatus()}; */

        int i = 0;
   /*     while (!(dbPkIDs.getDsmt_id().get(i)[1].toString().equalsIgnoreCase(idDsmt))) {
            i++;
        }  */
        dose.setPk_dsmt(dbPkIDs.getDsmt_id().get(i)[0].toString());

        dose.setYear(table.getValueAt(row, 1) == null ? "" : table.getValueAt(row, 1).toString());
        dose.setTrimester(SetEnums.Trimester.valueOf(table.getValueAt(row, 2).toString()));
        dose.setMonth(SetEnums.month.valueOf(table.getValueAt(row, 3).toString()));
        dose.setHp007(table.getValueAt(row, 4) == null ? "" : table.getValueAt(row, 4).toString());
        dose.setHp10(table.getValueAt(row, 5) == null ? "" : table.getValueAt(row, 5).toString());
        dose.setComments(table.getValueAt(row, 7) == null ? "" : table.getValueAt(row, 7).toString());

        if (newOrUpdate.equalsIgnoreCase("new")) {
//      creation date and time
            dose.setTimestamp(dateAndTime.currDateTime());
            dose.setLastchange(dateAndTime.currDateTime());

        } else {
            dose.setTimestamp(table.getValueAt(row, 6) == null ? "" : table.getValueAt(row, 6).toString());

        }

        return dose;

    }

    public void newDose() {

        /* tudo ok para escrever */

        // deve testar para ver se tem dosimeros associados
        
        tableModel.setDefaultDoseTable("newdose");

        setButtonsState.setDoseBtsNew(true);

        this.frmMan.getTxtInfoAction().setText("Inserting a New dose information");


    }

    // insert into the database 
    public void saveNewDose() {

        Dose dose = getDoseInfo("new");

        String worker_id = dbPkIDs.getWorker_id();
        dose.setPk_id(worker_id);

        String id = dosedao.insertDose(dose);

        dose.setPk_dose(id);

        // actualiza cumulDose
        setCumulDose.updateCumulDose(worker_id);
        
        this.frmMan.getTxtInfoAction().setText("Dose info to dsmt_id = " + dose.getDsmt_id() + " saved into database");

        // actualiza info
        fillWokerDoseInfo();
        
        setButtonsState.setDoseBtsSave(true);

    }

    /**
     *
     */
    public void updateDose() {

        table = this.frmMan.tableDoseInfo;
        int row = table.getSelectedRow();

        dbPkIDs.setDoseRowSelected(row);

        if (!(row > -1)) {
            return;
        }

        setCleanState.cleanDose();
        setDoseInfo.fillDoseInfo(dbPkIDs.getWorker_id(), "update");

        this.frmMan.getTxtInfoAction().setText("Updating Dose info");

        table.setRowSelectionInterval(row, row);
        
        setButtonsState.setDoseBtsUpdate(true);
        
    }

    public void saveUpdateDose() {

        table = this.frmMan.tableDoseInfo;
        int row = table.getSelectedRow();

        int rowToUpdate = dbPkIDs.getDoseRowSelected();

        if (row != rowToUpdate) {
            this.frmMan.getTxtInfoAction().setText("Nothing to be done: Selected row differs from row to be updated");
            // actualiza info
            fillWokerDoseInfo();
            return;
        }

        Dose dose = getDoseInfo("update");
        String dose_id = dose.getPk_dose();
        String worker_id = dbPkIDs.getWorker_id();
        dose.setPk_id(worker_id);
        dosedao.updateDose(dose, dose_id);
        
        // actualiza cumulDose
        setCumulDose.updateCumulDose(worker_id);
        
        
        this.frmMan.getTxtInfoAction().setText("Dose for dsmt = " + dose.getDsmt_id() + " updated into database");

        // actualiza info
        fillWokerDoseInfo();
        setButtonsState.setDoseBtsSave(true);

    }

    public void fillWokerDoseInfo() {

        setCleanState.cleanDose();
        setDoseInfo.fillDoseInfo(dbPkIDs.getWorker_id(), "new");
//        setButtonsState.setDoseBtsSearch(true);

    }
    
    
     public void fillWokerDoseInfoCancel() {

        // actualiza info
        setCleanState.cleanDose();
        setDoseInfo.fillDoseInfo(dbPkIDs.getWorker_id(), "list");

        setButtonsState.setDoseBtsCancel(false);

        this.frmMan.btDoseInfoNew.setEnabled(true);
  
        if (!dbPkIDs.getDose_id().isEmpty()) {
            this.frmMan.btDoseInfoUpdate.setEnabled(true);
        }



    }

    
}