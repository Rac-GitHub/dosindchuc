/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.ActiveDsmtDao;
import dosindchuc.model.entities.ActiveDsmt;
import java.util.List;

/**
 *
 * @author ir
 */
public class ManagementActiveDsmt {

    private ManagementFrm frmMan;
    private ActiveDsmtDao activedsmtdao;

    public ManagementActiveDsmt(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        activedsmtdao = new ActiveDsmtDao();

    }
    
    /* ###############################################  */
    /*                                                  */
    /*             Fill Worker active dsmts             */
    /*                                                  */
    /* ###############################################  */
    
    public void fillActiveDsmt(String worker_id) {

        
        List<ActiveDsmt> activedsmt = activedsmtdao.getActiveDsmtInfo(worker_id);
        
        int nResults = activedsmt.size();
        
        for (int i = 0; i < nResults; i++) {
      
        String dsmtID = activedsmt.get(i).getId();
        String dsmtSupplier = activedsmt.get(i).getSupplier();
        String dsmtType = activedsmt.get(i).getType();
        String dsmtStatus = activedsmt.get(i).getStatus();
        String dsmtPerd = activedsmt.get(i).getPeriodicity();
        String[] fullDateStatusActive = activedsmt.get(i).getLastchange().split(" ");
        String dateStatusActive = fullDateStatusActive[0];
        
        switch (i) {
            case 0:

                this.frmMan.txtWorkerActiveDsmt1.setText(dsmtID);
                this.frmMan.txtWorkerActDsmtPeriod1.setText(dsmtPerd);
                this.frmMan.txtWorkerActDsmtStatus1.setText(dsmtStatus);
                this.frmMan.txtWorkerActDsmtSupp1.setText(dsmtSupplier);
                this.frmMan.txtWorkerActDsmtType1.setText(dsmtType);
                this.frmMan.txtWorkerActDsmtLastChange1.setText(dateStatusActive);
                break;

            case 1:
                this.frmMan.txtWorkerActiveDsmt2.setText(dsmtID);
                this.frmMan.txtWorkerActDsmtPeriod2.setText(dsmtPerd);
                this.frmMan.txtWorkerActDsmtStatus2.setText(dsmtStatus);
                this.frmMan.txtWorkerActDsmtSupp2.setText(dsmtSupplier);
                this.frmMan.txtWorkerActDsmtType2.setText(dsmtType);
                this.frmMan.txtWorkerActDsmtLastChange2.setText(dateStatusActive);
                
                break;

            case 2:
                this.frmMan.txtWorkerActiveDsmt3.setText(dsmtID);
                this.frmMan.txtWorkerActDsmtPeriod3.setText(dsmtPerd);
                this.frmMan.txtWorkerActDsmtStatus3.setText(dsmtStatus);
                this.frmMan.txtWorkerActDsmtSupp3.setText(dsmtSupplier);
                this.frmMan.txtWorkerActDsmtType3.setText(dsmtType);
                this.frmMan.txtWorkerActDsmtLastChange3.setText(dateStatusActive);
                
                break; 
        }

    }
    }
}
  