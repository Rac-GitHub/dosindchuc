/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.controller.ManagementSearchActionListener;
import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementFields;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Dose_infoDao;
import dosindchuc.model.dao.Dose_notesDao;
import dosindchuc.model.dao.DosimeterDao;
import dosindchuc.model.dao.Dosimeter_notesDao;
import dosindchuc.model.dao.Help.DaoHelper;
import dosindchuc.model.dao.WorkerDao;
import dosindchuc.model.entities.Dose_info;
import dosindchuc.model.entities.Dose_notes;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Dosimeter_notes;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.entities.Worker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ir
 */
public class ManagementWorker {

    private DaoHelper daoHelper;
    private DefaultTableModel model;
    private Object [][] workerList;
    private ManagementFrm frmMan;
    private WorkerDao workerdao;
    
    private Dose_infoDao doseinfodao;
    private Dose_notesDao doseNotesDao;
    private List<Dose_info> dose_info;
    private List<Dose_notes> dosenotes; 
    
    private DosimeterDao dosimeterdao;
    private Dosimeter_notesDao dosimeterNotesDao;
    private List<Dosimeter> dosimeter_info;
    private List<Dosimeter_notes> dosimeterNotes;
    
    private ManagementSearchActionListener Listeners;
    
    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementFields setFieldsState;
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    
    
    public ManagementWorker (ManagementFrm frmMan, ManagementSearchActionListener Listeners) {

        daoHelper = new DaoHelper();
        this.frmMan = frmMan;
        workerdao = new WorkerDao();
        doseinfodao = new Dose_infoDao();
        doseNotesDao = new Dose_notesDao();
        dosimeterdao = new DosimeterDao();
        dosimeterNotesDao = new Dosimeter_notesDao();
        setFieldsState = new ManagementFields(this.frmMan);
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        this.Listeners = Listeners;
        
   
    
    }
    
    
     /* ###############################################  */
    /*                                                  */ 
    /*               Worker  info                       */
    /*                                                  */ 
    /* ###############################################  */ 
    
    
    private Worker getWorkerInfo () {
        
        Worker worker = new Worker();
    
        worker.setName(this.frmMan.getTxtWorkerName().getText());
        worker.setNick(this.frmMan.getTxtWorkerNick().getText());
        worker.setStatus(SetEnums.status.valueOf(this.frmMan.getCbWorkerStatus().getSelectedItem().toString()));
        worker.setId_mec(this.frmMan.getTxtWorkerMec().getText());
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
//      creation date and time
        worker.setTimestamp(dateAndTime.currDateTime());
        worker.setStatus_timestamp(dateAndTime.currDateTime());
        
        return worker;
        
    }
    
    
  
    
    
    
    public void newWorker () {
        
        /* tudo ok para escrever */
        
        setFieldsState.setWorkerAllEdit(true);
        setButtonsState.setAllWorkerBtsInitAndNew(false);
        setCleanState.cleanAllInfo();
        
        this.frmMan.txtInfoAction.setText("Inserting a New Worker");

        
    }
    
    
    // insert in database 
    
    public void saveWorker () {
        
        Worker worker = getWorkerInfo ();
        
  //      ArrayList<ArrayList<Object>> hhh1 = hhhh(worker);
        
        
 //       System.out.println("saveW -- " + hhh1.get(6).get(2));
        
        worker.setPk_id(workerdao.prepareToInsertWorker(worker));
        
        
        
        setFieldsState.setWorkerAllEdit(false);
        
        this.frmMan.txtInfoAction.setText("Worker saved into database");
        
        // actualiza info
        
        
    }
    
    
}
