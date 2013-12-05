/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.controller.ManagementSearchActionListener;
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
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.entities.Help.SetEnums.status;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Worker;
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
    
    private DateAndTime dateAndTime;
    
    
    public ManagementWorker (ManagementFrm frmMan, ManagementSearchActionListener Listeners) {

        daoHelper = new DaoHelper();
        this.frmMan = frmMan;
        workerdao = new WorkerDao();
        doseinfodao = new Dose_infoDao();
        doseNotesDao = new Dose_notesDao();
        dosimeterdao = new DosimeterDao();
        dosimeterNotesDao = new Dosimeter_notesDao();
        this.Listeners = Listeners;
   
    
    }
    
    
     /* ###############################################  */
    /*                                                  */ 
    /*               Worker  info                       */
    /*                                                  */ 
    /* ###############################################  */ 
    
    
    private Worker getWorkerInfo () {
        
        Worker worker = null;
        
        worker.setName(frmMan.getTxtWorkerName().toString());
        worker.setNick(frmMan.getTxtWorkerNick().toString());
        worker.setStatus(frmMan.getCbWorkerStatus().getSelectedItem().toString());
        worker.setId_mec(frmMan.getTxtWorkerMec().toString());
// birth yyyy-mm-dd 
        worker.setBirth(frmMan.getTxtWorkerBirthYear().toString() + "-" + frmMan.getTxtWorkerBirthMonth().toString() + "-" + frmMan.getTxtWorkerBirthDay().toString());
//
        worker.setBI(frmMan.getTxtWorkerBI().toString());
        worker.setSex(frmMan.getCbWorkerSex().getSelectedItem().toString());
        worker.setCategory(frmMan.getCbWorkerCat().getSelectedItem().toString());
        worker.setDepartment(frmMan.getCbWorkerDept().getSelectedItem().toString());
        worker.setSector(frmMan.getTxtWorkerSector().toString());
        worker.setNif(frmMan.getTxtWorkerNIF().toString());
        worker.setNationality(frmMan.getTxtWorkerNationality().toString());
        worker.setComments(frmMan.getTxtWorkerComments().toString());
//      creation date and time
        worker.setTimestamp(dateAndTime.currDateTime());
        worker.setStatus_timestamp(dateAndTime.currDateTime());
        
        return worker;
        
    }
    
    
    public void newWorker () {
        
        Worker worker = getWorkerInfo();
        
        
        
        
    }
    
    
    
    
}
