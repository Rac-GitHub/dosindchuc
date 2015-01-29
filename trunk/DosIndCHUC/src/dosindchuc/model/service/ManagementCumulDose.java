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
import dosindchuc.model.dao.CumulDoseDao;
import dosindchuc.model.entities.Cumulative_dose;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dose;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementCumulDose {

    private MainFrm frmMain;
    private ManagementFrm frmMan;
 //   private DoseDao dosedao;
    private CumulDoseDao cumuldosedao;
    private DateAndTime dateAndTime = new DateAndTime();
    private List<Cumulative_dose> cumulDose;
 //   private DbPkIDs dbPkIDs;
 //   private DateAndTime dateAndTime = new DateAndTime();
 //   private ManagementButtons setButtonsState;
 //   private ManagementClean setCleanState;
 //   private ManagementTablesModel tableModel;
 //   private ManagementSearch setDoseInfo;
 //   private JTable table;

    public ManagementCumulDose(ManagementFrm frmMan) {

        this.frmMan = frmMan;
 //       dbPkIDs = new DbPkIDs();
 //       dosedao = new DoseDao();
        cumuldosedao = new CumulDoseDao();
 //       tableModel = new ManagementTablesModel(this.frmMain, this.frmMan);
 //       setButtonsState = new ManagementButtons(this.frmMan);
 //       setCleanState = new ManagementClean(this.frmMan);

    }

    
    
    
    public void fillCumulDoseInfo(String worker_id) {

        cumulDose = cumuldosedao.getCumulDoseInfo(worker_id);

        System.out.println(" Fill cumul -- dose --- : " + cumulDose.size() + " worker id " + worker_id);

        if ( cumulDose.size() > 0 ) {

            this.frmMan.txtDoseHp007_1year.setText(cumulDose.get(0).getHp007_1year().toString());
            this.frmMan.txtDoseHp10_1year.setText(cumulDose.get(0).getHp10_1year().toString());

            this.frmMan.txtDoseHp007_5year.setText(cumulDose.get(0).getHp007_5year().toString());
            this.frmMan.txtDoseHp10_5year.setText(cumulDose.get(0).getHp10_5year().toString());
        }

    }
    
    
    
    public void fillNumDsmtCumulDose(String worker_id) {


        String cumuldose = cumuldosedao.calcNumDsmtCumulDose("1", worker_id);

        System.out.println(" cumuldsmt --- > "  + cumuldose);

    }
    
    
    
    public void newCumulDose(String worker_id) {

        Cumulative_dose cumuldose = new Cumulative_dose();
        
        cumuldose.setPk_id(worker_id);
        cumuldose.setHp007_1year("0.0");
        cumuldose.setHp10_1year("0.0");
        cumuldose.setNdsmt_1year("0");
        cumuldose.setHp007_5year("0.0");
        cumuldose.setHp10_5year("0.0");
        cumuldose.setNdsmt_5year("0");
        cumuldose.setLastchange(dateAndTime.currDateTime());
        
        cumuldosedao.insertCumulDose(cumuldose);

    }


 
    public void updateCumulDose(String worker_id) {

        
        Cumulative_dose cumuldose = new Cumulative_dose();

        System.out.println(" Mang Update cumul -- dose --- : " +  " worker id " + worker_id);
        
        cumuldose.setPk_id(worker_id);
        cumuldose.setHp007_1year(cumuldosedao.calcCumulDose("hp007", "1", worker_id));
        cumuldose.setHp10_1year(cumuldosedao.calcCumulDose("hp10", "1", worker_id));
        cumuldose.setNdsmt_1year(cumuldosedao.calcNumDsmtCumulDose("1", worker_id));
        cumuldose.setHp007_5year(cumuldosedao.calcCumulDose("hp007", "5", worker_id));
        cumuldose.setHp10_5year(cumuldosedao.calcCumulDose("hp10", "5", worker_id));
        cumuldose.setNdsmt_5year(cumuldosedao.calcNumDsmtCumulDose("5", worker_id));
        
        
        System.out.println(" Mang Update cumul -- dose --- : " +  " hp007_1yer " + cumuldose.getHp007_1year().toString() );
        
        
        cumuldosedao.updateCumulDose(cumuldose, worker_id);
        
    }

 /*

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

    */

}