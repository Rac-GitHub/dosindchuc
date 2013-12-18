/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementFields;
import dosindchuc.UI.swing.Help.ManagementTablesModel;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.DosimeterDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;



/**
 *
 * @author ir
 */
public class ManagementDosimeter {
    
    
    private ManagementFrm frmMan;
    private DosimeterDao dsmtdao;
    private DbPkIDs dbPkIDs;

    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementFields setFieldsState;
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementTablesModel tableModel;
    private ManagementSearch setDsmtInfo;
    
    
    
    public ManagementDosimeter (ManagementFrm frmMan) {

     
        this.frmMan = frmMan;
        dbPkIDs = new DbPkIDs();
        dsmtdao = new DosimeterDao();
        tableModel = new ManagementTablesModel(this.frmMan);
        setFieldsState = new ManagementFields(this.frmMan);
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setDsmtInfo = new ManagementSearch(this.frmMan, null);
    
    }
    
   
    
     /* ############################################### */
    /*                                                  */ 
    /*              dosimeter  info                       */
    /*                                                  */ 
    /* ###############################################  */ 
    
 /*   
    private int getDsmtIDs () {
        
        
        
    }
    
 */   
    private Dosimeter getDsmtInfo (String newOrUpdate) {
        
        Dosimeter dsmt = new Dosimeter();
   
        dsmt.setId(this.frmMan.tableDosimeterInfo.getValueAt(0, 0).toString());
        dsmt.setLabel(this.frmMan.tableDosimeterInfo.getValueAt(0, 1).toString());
        dsmt.setType(SetEnums.dsmt_type.valueOf(this.frmMan.tableDosimeterInfo.getValueAt(0, 2).toString()));
        dsmt.setPeriodicity(SetEnums.dsmt_periodicity.valueOf(this.frmMan.tableDosimeterInfo.getValueAt(0, 3).toString()));
        dsmt.setSupplier(SetEnums.dsmt_supplier.valueOf(this.frmMan.tableDosimeterInfo.getValueAt(0, 4).toString()));
        dsmt.setComments(this.frmMan.tableDosimeterInfo.getValueAt(0, 6).toString());
        dsmt.setStatus(SetEnums.status.valueOf(this.frmMan.tableDosimeterInfo.getValueAt(0, 7).toString()));
        
        if (newOrUpdate.equalsIgnoreCase("new")) {
//      creation date and time
            dsmt.setTimestamp(dateAndTime.currDateTime());
            dsmt.setStatus_timestamp(dateAndTime.currDateTime());
            dsmt.setLastchange(dateAndTime.currDateTime());
         } else {
            dsmt.setTimestamp(this.frmMan.tableDosimeterInfo.getValueAt(0, 5).toString());
        }
        
        return dsmt;
        
    }
    
   
  
    
    
    
    public void newDosimeter () {
        
        /* tudo ok para escrever */
        
        System.out.println(" Estou no newDosimeter ... ");
        
        tableModel.setDefaultDsmtTable("newdsmt");

        setButtonsState.setDosimeterBtsNew(true);
        
        this.frmMan.getTxtInfoAction().setText("Inserting a New dosimeter");
 
        
    }
    
    
    // insert into the database 
    
    public void saveNewDsmt () {
        
        Dosimeter dsmt = getDsmtInfo ("new");
        
        System.out.println("  Save dosimeter  " + dsmt);
        System.out.println("  Save dosimeter2  " + dbPkIDs.worker_id);
        
    /*    worker.setPk_id(workerdao.insertWorker(worker));
        setFieldsState.setWorkerAllEdit(false);
        
        String id = Integer.toString(worker.getPk_id());
        this.frmMan.getTxtInfoAction().setText("Worker with id= "+ id + "saved into database");
        
        // actualiza info
        setWorkerInfo.fillWorkerInfo(id);
        setButtonsState.setAllWorkerBtsInit(true); */
        
 //       return id;
        
    }
    
    
    
    /**
     *
     */ /*
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
    

    */
    
    
}
