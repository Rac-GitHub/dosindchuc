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

    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementFields setFieldsState;
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementSearch setWorkerInfo;
    
    
    public ManagementWorker (ManagementFrm frmMan) {

     
        this.frmMan = frmMan;
        workerdao = new WorkerDao();
        setFieldsState = new ManagementFields(this.frmMan);
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setWorkerInfo = new ManagementSearch(this.frmMan, null);
    
    }
    
    
     /* ############################################### */
    /*                                                  */ 
    /*               Worker  info                       */
    /*                                                  */ 
    /* ###############################################  */ 
    
    
    private Worker getWorkerInfo (String newOrUpdate) {
        
        Worker worker = new Worker();
    
        worker.setName(this.frmMan.getTxtWorkerName().getText());
        worker.setNick(this.frmMan.getTxtWorkerNick().getText());
        worker.setStatus(SetEnums.status.valueOf(this.frmMan.getCbWorkerStatus().getSelectedItem().toString()));
        worker.setId_mec(this.frmMan.getTxtWorkerMec().getText());
        
        System.out.println("worker info mec  " + this.frmMan.getTxtWorkerMec().getText());
        
 // birth yyyy-mm-dd
        String birthYear = this.frmMan.getTxtWorkerBirthYear().getText();
        String birthMonth = this.frmMan.getTxtWorkerBirthMonth().getText();
        String birthDay = this.frmMan.getTxtWorkerBirthDay().getText();
        if ( birthYear.isEmpty() || birthMonth.isEmpty() || birthDay.isEmpty() ) {
            worker.setBirth("");
        } else {
        worker.setBirth(birthYear + "-" + birthMonth + "-" + birthDay);
        }
//        
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
    
    
  
    
    
    
    public void newWorker () {
        
        /* tudo ok para escrever */
        
        setFieldsState.setWorkerAllEdit(true);
        setButtonsState.setSaveWorkerNew(false);
        setCleanState.cleanAllInfo();
        
        this.frmMan.getTxtInfoAction().setText("Inserting a New Worker");

        
    }
    
    
    // insert in database 
    
    public String saveNewWorker () {
        
        Worker worker = getWorkerInfo ("new");
        worker.setPk_id(workerdao.insertWorker(worker));
        setFieldsState.setWorkerAllEdit(false);
        
        String id = Integer.toString(worker.getPk_id());
        this.frmMan.getTxtInfoAction().setText("Worker with id= "+ id + "saved into database");
        
        // actualiza info
        setWorkerInfo.fillWorkerInfo(id);
        setButtonsState.setAllWorkerBtsInit(true);
        
        return id;
        
    }
    
    
    
    /**
     *
     */
    public void updateWorker () {
        
        setFieldsState.setWorkerAllEdit(true);
        setButtonsState.setSaveWorkerUpdate(false);
        
        this.frmMan.getTxtInfoAction().setText("Updating Worker info");
        
    }
    
    
    public void saveUpdateWorker (String worker_id) {
        
        Worker worker = getWorkerInfo ("update");
        setFieldsState.setWorkerAllEdit(false);
        
        System.out.println("save update    " +  worker_id);
        workerdao.updateWorker(worker, worker_id);
        
        this.frmMan.getTxtInfoAction().setText("Worker with id= "+ worker_id + " updated into database");
        
        // actualiza info
        setWorkerInfo.fillWorkerInfo(worker_id);
        setButtonsState.setAllWorkerBtsInit(true);
        this.frmMan.btWorkerUpdate.setEnabled(true);
        
    }
    
    
}
