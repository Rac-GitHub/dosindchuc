/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementTablesModel;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Dose_infoDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dose_info;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementDose {

    private ManagementFrm frmMan;
    private Dose_infoDao dosedao;
    private DbPkIDs dbPkIDs;

    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementTablesModel tableModel;
    private ManagementSearch setDoseInfo;
    private JTable table;
    
    
    
    public ManagementDose (ManagementFrm frmMan) {

     
        this.frmMan = frmMan;
        dbPkIDs = new DbPkIDs();
        dosedao = new Dose_infoDao();
        tableModel = new ManagementTablesModel(this.frmMan);
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setDoseInfo = new ManagementSearch(this.frmMan, null);
    
    }
    
   
    
    /* ############################################### */
    /*                                                  */ 
    /*              dose  info                       */
    /*                                                  */ 
    /* ###############################################  */ 

    private Dose_info getDoseInfo (String newOrUpdate) {
        
        Dose_info dose = new Dose_info();
        int row = 0;
        
        table = this.frmMan.tableDoseInfo;
        
        if ( ! newOrUpdate.equalsIgnoreCase("new") ) {
            row = table.getSelectedRow();
            System.out.println(" get dose : " + row );
            dose.setPk_dose(dbPkIDs.getDose_id().get(row).toString());
        }
           
        dose.setYear( table.getValueAt(row, 0) == null ? "" : table.getValueAt(row, 0).toString());
        dose.setYear( table.getValueAt(row, 0) == null ? "" : table.getValueAt(row, 0).toString());
        dose.setTrimester(SetEnums.Trimester.valueOf(table.getValueAt(row, 1).toString()));
        dose.setHp007(table.getValueAt(row, 2) == null ? "" : table.getValueAt(row, 2).toString());
        dose.setHp10(table.getValueAt(row, 3) == null ? "" : table.getValueAt(row, 3).toString());
        dose.setComments(table.getValueAt(row, 5) == null ? "" : table.getValueAt(row, 5).toString());
        
        if (newOrUpdate.equalsIgnoreCase("new")) {
//      creation date and time
            dose.setTimestamp(dateAndTime.currDateTime());
            dose.setLastchange(dateAndTime.currDateTime());
         } else {
            dose.setTimestamp(table.getValueAt(row, 4) == null ? "" : table.getValueAt(row, 4).toString());
        }
        
        return dose;
        
    }
    
   
  
    
    
    
    public void newDose () {
        
        /* tudo ok para escrever */
        
        System.out.println(" Estou no newDose ... ");
        
        tableModel.setDefaultDoseTable("newdose");

        setButtonsState.setDoseBtsNew(true);
        
        this.frmMan.getTxtInfoAction().setText("Inserting a New dose information");
 
        
    }
    
    
    // insert into the database 
    
    public void saveNewDose () {
        
        Dose_info dose = getDoseInfo("new");
        
        System.out.println("  Save dose  " + dose);
        System.out.println("  Save dose2  " + dbPkIDs.getWorker_id());
        
        String worker_id = dbPkIDs.getWorker_id();
        String worker_id = dbPkIDs.getWorker_id();
        dsmt.setPk_id(worker_id);
        
        String id = dsmtdao.insertDosimeter(dsmt);
        
        System.out.println("  Save dosimeter3  " + id);
        
        
        dsmt.setPk_dsmt(id);
        
        ArrayList dsmtIds = new ArrayList();
        dsmtIds.add(0, id);
        dbPkIDs.setDsmt_id(dsmtIds);
    
        
        this.frmMan.getTxtInfoAction().setText("Dosimeter with id= "+ dsmt.getId() + " saved into database");
        
        // actualiza info
        setCleanState.cleanDosimeter();
        setDsmtInfo.fillDosimeterInfo(worker_id,"new");
    
        setButtonsState.setDosimeterBtsSearch(true);
   
        
    }
    
    
    
    /**
     *
     */
    
    public void updateDosimeter () {
        
        System.out.println(" Estou no Update Dosimeter ... ");
        
        setButtonsState.setDosimeterBtsUpdate(true);
        
        setCleanState.cleanDosimeter();
        setDsmtInfo.fillDosimeterInfo(dbPkIDs.getWorker_id(),"update");
     
        this.frmMan.getTxtInfoAction().setText("Updating Dosimeter info");
        
    }
    
    
    public void saveUpdateDsmt () {
        
        Dosimeter dsmt = getDsmtInfo("update");
   
        System.out.println(" DSMT UPDATE   " + dsmt);
        
        String dsmt_id = dsmt.getPk_dsmt();
        
        System.out.println("save update    " +  dsmt_id);
        
        dsmt.setPk_id(dbPkIDs.getWorker_id());

        dsmtdao.updateDosimeter(dsmt, dsmt_id);
        
        this.frmMan.getTxtInfoAction().setText("Dosimeter with id= "+ dsmt.getId() + " updated into database");
        
        // actualiza info
        setCleanState.cleanDosimeter();
        setDsmtInfo.fillDosimeterInfo(dbPkIDs.getWorker_id(),"new");
    
        setButtonsState.setDosimeterBtsSearch(true);
  
        
    }
    
}