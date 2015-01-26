/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementFields;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.WorkerDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.entities.Worker;

/**
 *
 * @author ir
 */
public class ManagementWorker {

    private ManagementFrm frmMan;
    private WorkerDao workerdao;
    private DbPkIDs dbPkIDs;

    private DateAndTime dateAndTime; 
    private ManagementFields setFieldsState;
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementSearch setWorkerInfo;
    private ManagementCumulDose setCumulDose;
    
    
    public ManagementWorker (ManagementFrm frmMan) {
     
        this.frmMan = frmMan;
        dbPkIDs = new DbPkIDs();
        workerdao = new WorkerDao();
        setFieldsState = new ManagementFields(this.frmMan);
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setWorkerInfo = new ManagementSearch(this.frmMan, null);
        setCumulDose = new ManagementCumulDose(frmMan);
        dateAndTime = new DateAndTime();


    }

    /* ############################################### */
    /*                                                  */
    /*               Worker  info                       */
    /*                                                  */
    /* ###############################################  */
    private Worker getWorkerInfo(String newOrUpdate) {

        Worker worker = new Worker();

        worker.setName(this.frmMan.getTxtWorkerName().getText());
        worker.setNick(this.frmMan.getTxtWorkerNick().getText());
        worker.setStatus(SetEnums.worker_status.valueOf(this.frmMan.getCbWorkerStatus().getSelectedItem().toString()));
        worker.setId_mec(this.frmMan.getTxtWorkerMec().getText());

        // birth yyyy-mm-dd
        String birthYear = this.frmMan.getTxtWorkerBirthYear().getText();
        String birthMonth = this.frmMan.getTxtWorkerBirthMonth().getText();
        String birthDay = this.frmMan.getTxtWorkerBirthDay().getText();
        if (birthYear.isEmpty() || birthMonth.isEmpty() || birthDay.isEmpty()) {
            worker.setBirth("");
        } else {
            worker.setBirth(birthYear + "-" + birthMonth + "-" + birthDay);
        }

        worker.setBI(this.frmMan.getTxtWorkerBI().getText());
        worker.setSex(SetEnums.worker_sex.valueOf(this.frmMan.cbWorkerSex.getSelectedItem().toString()));
        worker.setCategory(SetEnums.worker_category.valueOf(this.frmMan.getCbWorkerCat().getSelectedItem().toString()));
        worker.setDepartment(SetEnums.worker_department.valueOf(this.frmMan.getCbWorkerDept().getSelectedItem().toString()));
        worker.setSector(this.frmMan.getTxtWorkerSector().getText());
        worker.setNif(this.frmMan.getTxtWorkerNIF().getText());
        worker.setNationality(this.frmMan.getTxtWorkerNationality().getText());
        worker.setComments(this.frmMan.getTxtWorkerComments().getText());

        if (newOrUpdate.equalsIgnoreCase("new")) {
//      creation date and time
            worker.setTimestamp(dateAndTime.currDateTime());
            worker.setStatus_timestamp(dateAndTime.currDateTime());
            worker.setLastchange(dateAndTime.currDateTime());
        } else {
            worker.setTimestamp(this.frmMan.getTxtWorkerCretedDate().getText());
        }

        return worker;

    }

    public void newWorker() {

        /* tudo ok para escrever */

        setFieldsState.setWorkerAllEdit(true);
        setButtonsState.setSaveWorkerNew(false);
        setCleanState.cleanAllInfo();
        setButtonsState.setAllDoseBtsInit(false);
        setButtonsState.setAllDoseNoteBtsInit(false);
        setButtonsState.setAllDosimeterBtsInit(false);
        setButtonsState.setAllDsmtNoteBtsInit(false);

        this.frmMan.getTxtInfoAction().setText("Inserting a New Worker");

    }

    // insert into the database 
    public void saveNewWorker() {

        Worker worker = getWorkerInfo("new");

        String id = workerdao.insertWorker(worker);

        if (id.equals("0")) {
           setCleanState.cleanAllInfo();
           this.frmMan.getTxtInfoAction().setText("New worker not inserted into database");
       
        } else {
            worker.setPk_id(id);
            this.frmMan.getTxtInfoAction().setText("Worker with id= " + id + " saved into database");
            // actualiza info
            setWorkerInfo.fillWorkerInfo(id);
            dbPkIDs.setWorker_id(id);
        }

        setFieldsState.setWorkerAllEdit(false);
        setButtonsState.setAllWorkerBtsInit(true);
        
        setCumulDose.newCumulDose(id);
 
    }

    /**
     *
     */
    public void updateWorker() {

        setFieldsState.setWorkerAllEdit(true);
        setButtonsState.setSaveWorkerUpdate(false);

        this.frmMan.getTxtInfoAction().setText("Updating Worker info");

    }

    public void saveUpdateWorker() {

        Worker worker = getWorkerInfo("update");
        setFieldsState.setWorkerAllEdit(false);

        String worker_id = dbPkIDs.getWorker_id();
        workerdao.updateWorker(worker, worker_id);
        this.frmMan.getTxtInfoAction().setText("Worker with id= " + worker_id + " updated into database");

        // actualiza info
        setWorkerInfo.fillWorkerInfo(worker_id);
        setButtonsState.setAllWorkerBtsInit(true);
        this.frmMan.btWorkerUpdate.setEnabled(true);

    }

    public void btWorkerCancel() {

        setButtonsState.setAllSearchClearBts();
        setCleanState.cleanAllInfo();
        this.frmMan.getTxtInfoAction().setText("Cancel");

    }
}
