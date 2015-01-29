/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.controller.ManagementActionListener;
import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementTablesModel;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.DoseDao;
import dosindchuc.model.dao.Dose_notesDao;
import dosindchuc.model.dao.DosimeterDao;
import dosindchuc.model.dao.DsmtNotesDao;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.WorkerDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dose;
import dosindchuc.model.entities.Dose_notes;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Dsmt_notes;
import dosindchuc.model.entities.Worker;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ir
 */
public class ManagementSearch {

    private DefaultTableModel model;
    private MainFrm frmMain;
    private ManagementFrm frmMan;
    private WorkerDao workerdao;
    private DbPkIDs dbPkIDs;
    private List<Worker> workerList;
    private DoseDao doseinfodao;
    private Dose_notesDao doseNotesDao;
    private List<Dose> dose_info;
    private List<Dose_notes> dosenotes;
    private ManagementTablesModel tableModel;
    private DosimeterDao dosimeterdao;
    private DsmtNotesDao dosimeterNotesDao;
    private List<Dosimeter> dosimeter_info;
    private List<Dsmt_notes> dosimeterNotes;
    private ManagementActionListener Listeners;
    private ManagementButtons serviceBtns;
    private ManagementActiveDsmt manActiveDsmt;
    private ManagementCumulDose manCumulDose;

    public ManagementSearch(ManagementFrm frmMan, ManagementActionListener Listeners) {

        this.frmMan = frmMan;
        serviceBtns = new ManagementButtons(this.frmMan);
        tableModel = new ManagementTablesModel(this.frmMain, this.frmMan);
        dbPkIDs = new DbPkIDs();
        workerdao = new WorkerDao();
        doseinfodao = new DoseDao();
        doseNotesDao = new Dose_notesDao();
        dosimeterdao = new DosimeterDao();
        dosimeterNotesDao = new DsmtNotesDao();
        manActiveDsmt = new ManagementActiveDsmt(this.frmMan);
        manCumulDose = new ManagementCumulDose(this.frmMan);
        this.Listeners = Listeners;

    }

    /* ###############################################  */
    /*                                                  */
    /*               Search worker                      */
    /*                                                  */
    /* ###############################################  */
    public void searchWorkers() {

        String department = frmMan.getSearchCBdepartment().getSelectedItem().toString();
        String id_mec = frmMan.getSearchTxtMec().getText();
        String name = frmMan.getSearchWorkerName().getText();
        String category = frmMan.getSearchCBCategory().getSelectedItem().toString();

        workerList = workerdao.getSearchWorker(name, department, category, id_mec);

        tableModel.setDefaultSearchTable();
        model = tableModel.getSearchTable();

        for (Worker worker : workerList) {

            Object workerInfoRow[] = new Object[]{worker.getId_mec(), worker.getName(), worker.getCategory(),
                worker.getDepartment(), worker.getStatus()};

            model.addRow(workerInfoRow);

        }

    }

    /* ###############################################  */
    /*                                                  */
    /*        Fill All Management info                  */
    /*                                                  */
    /* ###############################################  */
    public void fillAllManagement() {

        String worker_id = this.workerList.get(frmMan.searchTable.getSelectedRow()).getPk_id();

        dbPkIDs.setWorker_id(worker_id);

        fillWorkerInfo(worker_id);
        fillDosimeterInfo(worker_id, "list");
        fillDoseInfo(worker_id, "list");
        manActiveDsmt.fillActiveDsmt(worker_id);
        manCumulDose.fillCumulDoseInfo(worker_id);
        manCumulDose.fillNumDsmtCumulDose(worker_id);

    }

    /* ###############################################  */
    /*                                                  */
    /*               Worker  info                       */
    /*                                                  */
    /* ###############################################  */
    public void fillWorkerInfo(String worker_id) {

        List<Worker> workerinfo = workerdao.getWorkersInfo(worker_id);

        int nResults = workerinfo.size();

        if (!(nResults > 0)) {
            return;
        }

        Worker worker = workerinfo.get(0);

        frmMan.getTxtWorkerName().setText(worker.getName());
        frmMan.getTxtWorkerNick().setText(worker.getNick());
        frmMan.getTxtWorkerMec().setText(worker.getId_mec());
        frmMan.getTxtWorkerBI().setText(worker.getBI());
        frmMan.getTxtWorkerNIF().setText(worker.getNif());
        frmMan.getTxtWorkerNationality().setText(worker.getNationality());
        frmMan.getCbWorkerSex().setSelectedItem(worker.getSex().name());
        frmMan.getCbWorkerCat().setSelectedItem(worker.getCategory().name());
        frmMan.getCbWorkerDept().setSelectedItem(worker.getDepartment().name());
// birth yyyy-mm-dd 


        if (!(worker.getBirth() == null)) {
            frmMan.getTxtWorkerBirthYear().setText(worker.getBirth().split("-")[0]);
            frmMan.getTxtWorkerBirthMonth().setText(worker.getBirth().split("-")[1]);
            frmMan.getTxtWorkerBirthDay().setText(worker.getBirth().split("-")[2]);
        } else {
            frmMan.getTxtWorkerBirthYear().setText("----");
            frmMan.getTxtWorkerBirthMonth().setText("--");
            frmMan.getTxtWorkerBirthDay().setText("--");
        }
// 
        frmMan.getTxtWorkerComments().setText(worker.getComments());
        frmMan.getTxtWorkerCretedDate().setText(worker.getTimestamp());

        frmMan.getCbWorkerStatus().setSelectedItem(worker.getStatus().name());

        frmMan.getTxtWorkerLastModified().setText(worker.getLastchange());

    }

    /* ###############################################  */
    /*                                                  */
    /*             Fill Worker dosimeter  info          */
    /*                                                  */
    /* ###############################################  */
    public List fillDosimeterInfo(String worker_id, String newORupdate) {

        dosimeter_info = dosimeterdao.getDosimetersInfo("", worker_id);

        int nResults = dosimeter_info.size();

        System.out.println("   nResults dsmt -> " + nResults);

        if (!(nResults > 0)) {
            dosimeter_info = null;
            this.frmMan.btDosimeterInfoNew.setEnabled(true);
            //          this.frmMan.btDoseInfoNew.setEnabled(false);
            //          this.frmMan.btDoseInfoUpdate.setEnabled(false);
            return dosimeter_info;
        }

        if (newORupdate.equalsIgnoreCase("update")) {
            tableModel.setDefaultDsmtTable("updatedsmt");

        } else if (newORupdate.equalsIgnoreCase("new")) {
            tableModel.setDefaultDsmtTable("readonly");

        } else if (newORupdate.equalsIgnoreCase("list")) {
            tableModel.setDefaultDsmtTable("readonly");
            this.frmMan.btDosimeterInfoNew.setEnabled(true);
            this.frmMan.btDosimeterInfoUpdate.setEnabled(true);
//            this.frmMan.btDoseInfoNew.setEnabled(true);
        } else {
            System.out.println(" fillDosimeterInfo: erro:");
        }


        model = tableModel.getDsmtTable();

        ArrayList<Object[]> dsmtIds = new ArrayList();

        for (int i = 0; i < nResults; i++) {

            Dosimeter dosimeter = dosimeter_info.get(i);
            Object newRow[] = new Object[]{dosimeter.getId(), dosimeter.getLabel(), dosimeter.getType(), dosimeter.getPeriodicity(),
                dosimeter.getSupplier(), dosimeter.getTimestamp(), dosimeter.getComments(), dosimeter.getStatus(),
                dosimeter.getLastchange()};
            model.addRow(newRow);

            // info dos dosimetros para a dose and history
//            Object dsmtInfo[] = new Object[]{dosimeter.getPk_dsmt(), dosimeter.getId(), dosimeter.getStatus(), dosimeter.getPeriodicity()};
            Object dsmtInfo[] = new Object[]{dosimeter.getPk_dsmt(), dosimeter.getId(), dosimeter.getLabel(),
                dosimeter.getType(), dosimeter.getPeriodicity(), dosimeter.getSupplier(), dosimeter.getComments(),
                dosimeter.getStatus()};

            if (dosimeter.getStatus().toString().equalsIgnoreCase("Activo")) {
                this.frmMan.btDoseInfoNew.setEnabled(true);
            }

            dsmtIds.add(i, dsmtInfo);


        }


        dbPkIDs.setDsmt_id(dsmtIds);

        return dosimeter_info;

    }

    public void fillDosimeterNotesCBIndex() {

        clearDosimeterNotesInfo();

        //     dbPkIDs.getDsmt_id().get(i)

        Object pk_dsmt = dbPkIDs.getDsmtNotes_id().get(0, 0);

        if (pk_dsmt == null) {

            System.out.println("  fill DosimeterNotesCB --- >  pk_dsmt: " + pk_dsmt);


            int row = frmMan.tableDosimeterInfo.getSelectedRow();

            System.out.println("  fill DosimeterNotesCB --- >  row: " + row);
//            System.out.println("  fill DosimeterNotesCB --- >  dosimeter_ifo size: " + dosimeter_info.size());

            //    pk_dsmt = dosimeter_info.get(row).getPk_dsmt();
            pk_dsmt = dbPkIDs.getDsmt_id().get(row)[0];

            System.out.println("  fill DosimeterNotesCB --- >  pk_dsmt2: " + pk_dsmt);


        }

        dosimeterNotes = dosimeterNotesDao.getDsmt_notes(pk_dsmt.toString());

        if (!this.frmMan.btDosimeterInfoUpdate.isEnabled()) {
            return;
        }

        int nResults = dosimeterNotes.size();

        if (!(nResults > 0)) {
            this.frmMan.btNewNoteDosimeter.setEnabled(true);
            this.frmMan.btUpdateNoteDosimeter.setEnabled(false);
            return;
        }


        frmMan.cbDosimeterNotesIndex.removeActionListener(Listeners);
        for (int i = 0; i < nResults; i++) {
            frmMan.getCbDosimeterNotesIndex().addItem(i + 1);
        }
        frmMan.cbDosimeterNotesIndex.addActionListener(Listeners);
        frmMan.getCbDosimeterNotesIndex().setSelectedIndex(0);
        frmMan.getCbDosimeterNotesIndex().setEnabled(true);
        serviceBtns.setNewORUpdateDsmtNoteBts(true);


    }

    public void fillDosimeterNotesInfo() {

        int index = frmMan.getCbDosimeterNotesIndex().getSelectedIndex();

        int row = frmMan.tableDosimeterInfo.getSelectedRow();
        //     String pk_dsmt = dosimeter_info.get(row).getPk_dsmt();
        String pk_dsmt = dbPkIDs.getDsmt_id().get(row)[0].toString();

        dosimeterNotes = dosimeterNotesDao.getDsmt_notes(pk_dsmt);

        Dsmt_notes dsmt_notes = dosimeterNotes.get(index);

        frmMan.getTxtDosimeterNote().setText(dsmt_notes.getNote());
        frmMan.getTxtDosimeterNotesDateCreated().setText(dsmt_notes.getTimestamp());
        frmMan.getCbDosimeterNotesAlert().setSelectedItem(dsmt_notes.getAlert_level().name());
        frmMan.getTxtDosimeterNoteAlertdate().setText(dsmt_notes.getAlert_level_timestamp());
        frmMan.getCbDosimeterNotesStatus().setSelectedItem(dsmt_notes.getStatus().name());
        frmMan.getTxtDosimeterNoteStatusDate().setText(dsmt_notes.getStatus_timestamp());
        frmMan.getTxtDosimeterNotesDateLastChanged().setText(dsmt_notes.getLastchange());



        ArrayList2D dsmtNoteInfo = new ArrayList2D();

        int i = 0;
        // info dose-note selected for update
        dsmtNoteInfo.Add(dsmt_notes.getPk_dsmt_notes(), i);
        dsmtNoteInfo.Add(dsmt_notes.getPk_dsmt(), i);
        dsmtNoteInfo.Add(dsmt_notes.getNote(), i);
        dsmtNoteInfo.Add(dsmt_notes.getAlert_level().name(), i);
        dsmtNoteInfo.Add(dsmt_notes.getStatus().name(), i);

        dbPkIDs.setDsmtNotes_id(dsmtNoteInfo);

    }

    public void clearDosimeterNotesInfo() {

        frmMan.cbDosimeterNotesIndex.removeActionListener(Listeners);
        frmMan.getCbDosimeterNotesIndex().removeAllItems();
        frmMan.getCbDosimeterNotesIndex().setEnabled(false);
        frmMan.cbDosimeterNotesIndex.removeActionListener(Listeners);

        frmMan.getTxtDosimeterNotesDateCreated().setText("");
        frmMan.getTxtDosimeterNote().setText("");
        frmMan.getCbDosimeterNotesAlert().setSelectedItem(1);
        //   frmMan.getTxtDoseNoteLevelDate().setText(dose_notes.get);
        frmMan.getCbDosimeterNotesStatus().setSelectedItem(1);
        frmMan.getTxtDosimeterNoteStatusDate().setText("");

    }

    /* ###############################################  */
    /*                                                  */
    /*             Fill Worker dose info                */
    /*                                                  */
    /* ###############################################  */
    public List fillDoseInfo(String worker_id, String newORupdate) {

        ArrayList doseIds = new ArrayList();

        dose_info = doseinfodao.getDoseInfo("", worker_id);

        int nResults = dose_info.size();

        System.out.println("   nResults dose -> " + nResults);

        if (!(nResults > 0)) {
            dose_info = null;
            return dose_info;
        }

        /*     this.frmMan.btDoseInfoNew.setEnabled(true); */
        this.frmMan.btDoseInfoUpdate.setEnabled(true);

        if (newORupdate.equalsIgnoreCase("update")) {
            tableModel.setDefaultDoseTable("updatedose");
        } else {
            tableModel.setDefaultDoseTable("readonly");
        }

        model = tableModel.getDoseTable();

        for (int i = 0; i < nResults; i++) {

            Dose worker_dose = dose_info.get(i);
            String dsmtID = dosimeterdao.getDosimetersInfo(worker_dose.getPk_dsmt(), worker_id).get(0).getId();

            Object newRow[] = new Object[]{dsmtID, worker_dose.getYear(), worker_dose.getTrimester(), worker_dose.getMonth(),
                worker_dose.getHp007(), worker_dose.getHp10(), worker_dose.getTimestamp(), worker_dose.getComments(),
                worker_dose.getLastchange()};
            model.addRow(newRow);

            doseIds.add(i, worker_dose.getPk_dose());

        }

        dbPkIDs.setDose_id(doseIds);

        return dose_info;

    }

    public void fillDoseNotesCBIndex() {

        clearDoseNotesInfo();

        Object pk_dose = dbPkIDs.getDoseNotes_id().get(0, 0);

        if (pk_dose == null) {
            int row = frmMan.tableDoseInfo.getSelectedRow();
            //         pk_dose = dose_info.get(row).getPk_dose();
            pk_dose = dbPkIDs.getDose_id().get(row);
        }

        /*      if (!(dose_info.size() > 0)) {
         this.frmMan.btDoseInfoNew.setEnabled(true);
         this.frmMan.btDoseInfoUpdate.setEnabled(true);
         } */

        dosenotes = doseNotesDao.getDose_notesInfo(pk_dose.toString());

        int nResults = dosenotes.size();

        if (!(nResults > 0)) {
            this.frmMan.btNewDoseNote.setEnabled(true);
            this.frmMan.btUpdateDoseNote.setEnabled(false);
            return;
        }

        frmMan.cbDoseNoteIndex.removeActionListener(Listeners);
        for (int i = 0; i < nResults; i++) {
            frmMan.getCbDoseNoteIndex().addItem(i + 1);
        }
        frmMan.cbDoseNoteIndex.addActionListener(Listeners);
        frmMan.getCbDoseNoteIndex().setSelectedIndex(0);
        frmMan.getCbDoseNoteIndex().setEnabled(true);
        serviceBtns.setNewORUpdateDoseNoteBts(true);


    }

    public void fillDoseNotesInfo() {

        int index = frmMan.getCbDoseNoteIndex().getSelectedIndex();

        int row = frmMan.tableDoseInfo.getSelectedRow();
        //    String pk_dose = dose_info.get(row).getPk_dose();
        String pk_dose = dbPkIDs.getDose_id().get(row).toString();

        dosenotes = doseNotesDao.getDose_notesInfo(pk_dose);

        Dose_notes dose_notes = dosenotes.get(index);

        frmMan.getTxtDoseNote().setText(dose_notes.getNote());
        frmMan.getTxtDoseNotesDateCreated().setText(dose_notes.getTimestamp());
        frmMan.getCbDoseNoteLevel().setSelectedItem(dose_notes.getAlert_level().name());
        frmMan.getTxtDoseNoteLevelDate().setText(dose_notes.getAlert_level_timestamp());
        frmMan.getCbDoseNoteStatus().setSelectedItem(dose_notes.getStatus().name());
        frmMan.getTxtDoseNoteStatusDate().setText(dose_notes.getStatus_timestamp());
        frmMan.getTxtDoseNotesDateLastChanged().setText(dose_notes.getLastchange());


        ArrayList2D doseNoteInfo = new ArrayList2D();

        int i = 0;
        // info dose-note selected dor update
        doseNoteInfo.Add(dose_notes.getPk_dose_notes(), i);
        doseNoteInfo.Add(dose_notes.getPk_dose(), i);
        doseNoteInfo.Add(dose_notes.getNote(), i);
        doseNoteInfo.Add(dose_notes.getAlert_level().name(), i);
        doseNoteInfo.Add(dose_notes.getStatus().name(), i);

        dbPkIDs.setDoseNotes_id(doseNoteInfo);

    }

    //  fill
    public void clearDoseNotesInfo() {

        frmMan.cbDoseNoteIndex.removeActionListener(Listeners);
        frmMan.getCbDoseNoteIndex().removeAllItems();
        frmMan.getCbDoseNoteIndex().setEnabled(false);
        frmMan.cbDoseNoteIndex.addActionListener(Listeners);

        frmMan.getTxtDoseNotesDateCreated().setText("");
        frmMan.getTxtDoseNote().setText("");
        frmMan.getCbDoseNoteLevel().setSelectedItem(1);
        frmMan.getTxtDoseNoteLevelDate().setText("");
        frmMan.getCbDoseNoteStatus().setSelectedItem(1);
        frmMan.getTxtDoseNoteStatusDate().setText("");

    }

    
    // fill 
    
    
    
}
