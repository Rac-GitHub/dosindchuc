/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.entities.DbPkIDs;
import java.util.ArrayList;

/**
 *
 * @author ir
 */
public class ManagementClean {

    private ManagementFrm frmMan;
    private MainFrm frmMain;
    private ManagementTablesModel tableModel;
    private DbPkIDs dbPkIDs;

    public ManagementClean(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        tableModel = new ManagementTablesModel(this.frmMain, this.frmMan);
        dbPkIDs = new DbPkIDs();

    }

    public void cleanAllInfo() {

        cleanWorker();
        cleanDosimeter();
        cleanDose();

    }

    public void cleanAllInfoWithSearch() {

        cleanSearch();
        cleanWorker();
        cleanDosimeter();
        cleanDose();

    }

    public void cleanSearch() {

        frmMan.getSearchWorkerName().setText("");
        frmMan.getSearchCBdepartment().setSelectedItem("NoDef");
        frmMan.getSearchCBCategory().setSelectedItem("NoDef");
        frmMan.getSearchTxtMec().setText("");
//        
        tableModel.setDefaultSearchTable();


    }

    public void cleanWorker() {

        this.frmMan.txtWorkerName.setText(null);
        this.frmMan.txtWorkerNick.setText(null);
        this.frmMan.txtWorkerBI.setText(null);
        this.frmMan.txtWorkerBirthYear.setText(null);
        this.frmMan.txtWorkerBirthMonth.setText(null);
        this.frmMan.txtWorkerBirthDay.setText(null);
        this.frmMan.txtWorkerMec.setText(null);
        this.frmMan.txtWorkerSector.setText(null);
        this.frmMan.txtWorkerNationality.setText(null);
        this.frmMan.txtWorkerCretedDate.setText(null);
        this.frmMan.txtWorkerLastModified.setText(null);
        this.frmMan.txtWorkerNIF.setText(null);
        this.frmMan.txtWorkerComments.setText(null);
        this.frmMan.cbWorkerCat.setSelectedIndex(1);
        this.frmMan.cbWorkerDept.setSelectedIndex(1);
        this.frmMan.cbWorkerSex.setSelectedIndex(0);
        this.frmMan.cbWorkerStatus.setSelectedIndex(0);

        //    frmMan.btWorkerNew.setEnabled(true);
        //    frmMan.btWorkerUpdate.setEnabled(false);
        frmMan.getTxtInfoAction().setText(null);


    }

    // dosimeter
    public void cleanDosimeter() {

        dsmt_idClear();

        cleanDsmtNotes();

        tableModel.setDefaultDsmtTable("readonly");

        if (!this.frmMan.tableDosimeterInfo.isEnabled()) {
            this.frmMan.tableDosimeterInfo.setEnabled(true);
        }

    }

    public void cleanDsmtNotes() {
        
        dsmtNotes_idClear();

        this.frmMan.txtDosimeterNote.setText(null);
        this.frmMan.txtDosimeterNote.setEditable(false);
        this.frmMan.txtDosimeterNoteAlertdate.setText(null);
        this.frmMan.txtDosimeterNoteStatusDate.setText(null);
        this.frmMan.txtDosimeterNotesDateCreated.setText(null);

        this.frmMan.cbDosimeterNotesIndex.setEnabled(false);
        this.frmMan.cbDosimeterNotesAlert.setEnabled(false);
        this.frmMan.cbDosimeterNotesStatus.setEnabled(false);


    }

    // dose
    public void cleanDose() {

        dose_idClear();
        
        cleanDoseNotes();

        tableModel.setDefaultDoseTable("readonly");

        if (!this.frmMan.tableDoseInfo.isEnabled()) {
            this.frmMan.tableDoseInfo.setEnabled(true);
        }


    }

    public void cleanDoseNotes() {

        doseNotes_idClear();
        
        this.frmMan.txtDoseNote.setText(null);
        this.frmMan.txtDoseNote.setEditable(false);
        this.frmMan.txtDoseNoteLevelDate.setText(null);
        this.frmMan.txtDoseNoteStatusDate.setText(null);
        this.frmMan.txtDoseNotesDateCreated.setText(null);

        this.frmMan.cbDoseNoteIndex.setEnabled(false);
        this.frmMan.cbDoseNoteLevel.setEnabled(false);
        this.frmMan.cbDoseNoteStatus.setEnabled(false);

    }

    /*
     *  clear all saved info
     */
    
    public void dsmt_idClear() {

        ArrayList<Object[]> dsmtIds = new ArrayList();
        dbPkIDs.setDsmt_id(dsmtIds);

    }

    public void dose_idClear() {

        ArrayList dose_id = new ArrayList();
        dbPkIDs.setDose_id(dose_id);

    }

    public void dsmtNotes_idClear() {

        ArrayList2D dsmtNotes_id = new ArrayList2D();
        dbPkIDs.setDsmtNotes_id(dsmtNotes_id);
    }
    
    public void doseNotes_idClear() {

        ArrayList2D doseNotes_id = new ArrayList2D();
        dbPkIDs.setDoseNotes_id(doseNotes_id);
    }
    
    public void alertNoteClear() {

        ArrayList<Object[]> alertNote = new ArrayList();
        dbPkIDs.setAlertNote(alertNote);
    }
    
     public void div_dsmtIDClear() {

        ArrayList<Object[]> div_dsmtID = new ArrayList();
        dbPkIDs.setAlertNote(div_dsmtID);
    }
    
}
