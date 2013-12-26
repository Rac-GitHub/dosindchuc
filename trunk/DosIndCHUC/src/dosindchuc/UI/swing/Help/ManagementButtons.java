/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.ManagementFrm;

/**
 *
 * @author ir
 */
public class ManagementButtons {

    private ManagementFrm frmMan;

    public ManagementButtons(ManagementFrm frmMan) {

        this.frmMan = frmMan;

    }

    // worker     
    public void setAllWorkerBtsInit(boolean workerBts) {

        this.frmMan.btWorkerNew.setEnabled(workerBts);
        this.frmMan.btWorkerUpdate.setEnabled(workerBts);
        this.frmMan.btSaveWorkerNew.setEnabled(!workerBts);
        this.frmMan.btSaveWorkerUpdate.setEnabled(!workerBts);
        this.frmMan.btWorkerCancel.setEnabled(!workerBts);

    }

    public void setSaveWorkerNew(boolean workerBts) {

        setAllWorkerBtsInit(workerBts);
        this.frmMan.btSaveWorkerUpdate.setEnabled(workerBts);
        this.frmMan.btSaveWorkerUpdate.setVisible(workerBts);
        this.frmMan.btSaveWorkerNew.setVisible(!workerBts);

    }

    public void setSaveWorkerUpdate(boolean workerBts) {

        setAllWorkerBtsInit(workerBts);
        this.frmMan.btSaveWorkerUpdate.setEnabled(!workerBts);
        this.frmMan.btSaveWorkerNew.setVisible(workerBts);
        this.frmMan.btSaveWorkerUpdate.setVisible(!workerBts);

    }

    
    
    /*
     * 
     * dosimeter
     * 
     */
    

    public void setAllDosimeterBtsInit(boolean dsmtBts) {

        this.frmMan.btDosimeterInfoNew.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoUpdate.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoSaveNew.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoCancel.setEnabled(dsmtBts);

    }

    public void setDosimeterBtsSearch(boolean dsmtBts) {

        this.frmMan.btDosimeterInfoNew.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoUpdate.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoSaveNew.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoCancel.setEnabled(!dsmtBts);

    }

    public void setDosimeterBtsNew(boolean dsmtBts) {

        this.frmMan.btDosimeterInfoNew.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoUpdate.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoSaveNew.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoCancel.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoSaveNew.setVisible(dsmtBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setVisible(!dsmtBts);

    }
    
    public void setDosimeterBtsUpdate(boolean dsmtBts) {

        this.frmMan.btDosimeterInfoNew.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoUpdate.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoSaveNew.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoCancel.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoSaveNew.setVisible(!dsmtBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setVisible(dsmtBts);

    }
    
    /*
     * 
     * 
     * dose info
     * 
     */

    
    public void setAllDoseBtsInit(boolean doseBts) {

        this.frmMan.btDoseInfoNew.setEnabled(doseBts);
        this.frmMan.btDoseInfoUpdate.setEnabled(doseBts);
        this.frmMan.btDoseInfoSaveNew.setEnabled(doseBts);
        this.frmMan.btDoseInfoSaveUpdate.setEnabled(doseBts);
        this.frmMan.btDoseInfoCancel.setEnabled(doseBts);

    }
    
     public void setDoseBtsSearch(boolean doseBts) {

        this.frmMan.btDoseInfoNew.setEnabled(doseBts);
        this.frmMan.btDoseInfoUpdate.setEnabled(doseBts);
        this.frmMan.btDoseInfoSaveNew.setEnabled(!doseBts);
        this.frmMan.btDoseInfoSaveUpdate.setEnabled(!doseBts);
        this.frmMan.btDoseInfoCancel.setEnabled(!doseBts);

    }

    public void setDoseBtsNew(boolean doseBts) {

        this.frmMan.btDoseInfoNew.setEnabled(!doseBts);
        this.frmMan.btDoseInfoUpdate.setEnabled(!doseBts);
        this.frmMan.btDoseInfoSaveNew.setEnabled(doseBts);
        this.frmMan.btDoseInfoSaveUpdate.setEnabled(!doseBts);
        this.frmMan.btDoseInfoCancel.setEnabled(doseBts);
        this.frmMan.btDoseInfoSaveNew.setVisible(doseBts);
        this.frmMan.btDoseInfoSaveUpdate.setVisible(!doseBts);

    }
    
    public void setDoseBtsUpdate(boolean dsmtBts) {

        this.frmMan.btDoseInfoNew.setEnabled(!dsmtBts);
        this.frmMan.btDoseInfoUpdate.setEnabled(!dsmtBts);
        this.frmMan.btDoseInfoSaveNew.setEnabled(!dsmtBts);
        this.frmMan.btDoseInfoSaveUpdate.setEnabled(dsmtBts);
        this.frmMan.btDoseInfoCancel.setEnabled(dsmtBts);
        this.frmMan.btDoseInfoSaveNew.setVisible(!dsmtBts);
        this.frmMan.btDoseInfoSaveUpdate.setVisible(dsmtBts);

    }
    
    
}
