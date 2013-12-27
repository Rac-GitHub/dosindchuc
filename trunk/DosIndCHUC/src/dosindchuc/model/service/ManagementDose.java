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
//            dose.setPk_dose(dbPkIDs.getDose_id());
            System.out.println(" get dose pk_dose : " + dose.getPk_dose() );
        }
        
        
        String idDsmt = table.getValueAt(row, 0).toString();
        dose.setDsmt_id(idDsmt);
        
        int i=0;
        while (  ! (dbPkIDs.getDsmt_id().get(i, 1).toString().equalsIgnoreCase(idDsmt)) ) {   i++;  }
        dose.setPk_dsmt(dbPkIDs.getDsmt_id().get(i, 0).toString());
 
        System.out.println("while pk_dsmt--- " + dose.getPk_dsmt());
  
        dose.setYear( table.getValueAt(row, 1) == null ? "" : table.getValueAt(row, 1).toString());
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
        dose.setPk_id(worker_id);
        
        
        String id = dosedao.insertDose(dose);
        
        System.out.println("  Save dose3  " + id);
        
        
        dose.setPk_dose(id);
        
   //     dbPkIDs.setDose_id(id);
    
        
        this.frmMan.getTxtInfoAction().setText("Dose info to dsmt_id = "+ dose.getDsmt_id() + " saved into database");
        
        // actualiza info
        fillWokerDoseInfo();
        
    }
    
    
    
    /**
     *
     */
    
    public void updateDose() {
        
        System.out.println(" Estou no Update Dose ... ");
        
        setButtonsState.setDoseBtsUpdate(true);
        
        setCleanState.cleanDose();
        setDoseInfo.fillDoseInfo(dbPkIDs.getWorker_id(),"update");
     
        this.frmMan.getTxtInfoAction().setText("Updating Dose info");
        
    }
    
    
    public void saveUpdateDose () {
        
        Dose_info dose = getDoseInfo("update");
   
        System.out.println(" Dose UPDATE  info " + dose);
        
        String dose_id = dose.getPk_dose();
        
        System.out.println("save update dose   " +  dose_id);
        
        dose.setPk_id(dbPkIDs.getWorker_id());

        dosedao.updateDose(dose, dose_id);
        
        this.frmMan.getTxtInfoAction().setText("Dose for dsmt = "+ dose.getDsmt_id() + " updated into database");
        
        // actualiza info
        fillWokerDoseInfo();
          
    }
    
    
     public void fillWokerDoseInfo () {
        
        /* tudo ok para escrever */
        
        System.out.println(" Estou no fill doselDose ... ");
        
        // actualiza info
        setCleanState.cleanDose();
        setDoseInfo.fillDoseInfo(dbPkIDs.getWorker_id(),"new");
    
        setButtonsState.setDoseBtsSearch(true);
       
        
    }
    
    
    
    
    
}