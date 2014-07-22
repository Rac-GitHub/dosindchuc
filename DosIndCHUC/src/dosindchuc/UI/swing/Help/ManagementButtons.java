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

        this.frmMan.btWorkerNew.setEnabled(!dsmtBts);
        this.frmMan.btWorkerUpdate.setEnabled(!dsmtBts);
        this.frmMan.btDoseInfoNew.setEnabled(!dsmtBts);
        this.frmMan.btDoseInfoUpdate.setEnabled(!dsmtBts);


        this.frmMan.btNewNoteDosimeter.setEnabled(!dsmtBts);
        this.frmMan.btUpdateNoteDosimeter.setEnabled(!dsmtBts);
        this.frmMan.btSaveNewDsmtNote.setEnabled(!dsmtBts);
        this.frmMan.btSaveUpdateDsmtNote.setEnabled(!dsmtBts);
        this.frmMan.btCancelDsmtNote.setEnabled(!dsmtBts);

    }

    public void setDosimeterBtsUpdate(boolean dsmtBts) {

        this.frmMan.btDosimeterInfoNew.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoUpdate.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoSaveNew.setEnabled(!dsmtBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoCancel.setEnabled(dsmtBts);
        this.frmMan.btDosimeterInfoSaveNew.setVisible(!dsmtBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setVisible(dsmtBts);

        this.frmMan.btWorkerNew.setEnabled(!dsmtBts);
        this.frmMan.btWorkerUpdate.setEnabled(!dsmtBts);
        this.frmMan.btDoseInfoNew.setEnabled(!dsmtBts);
        this.frmMan.btDoseInfoUpdate.setEnabled(!dsmtBts);

        this.frmMan.btNewNoteDosimeter.setEnabled(!dsmtBts);
        this.frmMan.btUpdateNoteDosimeter.setEnabled(!dsmtBts);
        this.frmMan.btSaveNewDsmtNote.setEnabled(!dsmtBts);
        this.frmMan.btSaveUpdateDsmtNote.setEnabled(!dsmtBts);
        this.frmMan.btCancelDsmtNote.setEnabled(!dsmtBts);

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

        this.frmMan.btWorkerNew.setEnabled(!doseBts);
        this.frmMan.btWorkerUpdate.setEnabled(!doseBts);
        this.frmMan.btDosimeterInfoNew.setEnabled(!doseBts);
        this.frmMan.btDosimeterInfoUpdate.setEnabled(!doseBts);

        this.frmMan.btNewDoseNote.setEnabled(!doseBts);
        this.frmMan.btUpdateDoseNote.setEnabled(!doseBts);
        this.frmMan.btSaveNewDoseNote.setEnabled(!doseBts);
        this.frmMan.btSaveUpdateDoseNote.setEnabled(!doseBts);
        this.frmMan.btCancelDoseNote.setEnabled(!doseBts);


    }

    public void setDoseBtsUpdate(boolean doseBts) {

        this.frmMan.btDoseInfoNew.setEnabled(!doseBts);
        this.frmMan.btDoseInfoUpdate.setEnabled(!doseBts);
        this.frmMan.btDoseInfoSaveNew.setEnabled(!doseBts);
        this.frmMan.btDoseInfoSaveUpdate.setEnabled(doseBts);
        this.frmMan.btDoseInfoCancel.setEnabled(doseBts);
        this.frmMan.btDoseInfoSaveNew.setVisible(!doseBts);
        this.frmMan.btDoseInfoSaveUpdate.setVisible(doseBts);

        this.frmMan.btWorkerNew.setEnabled(!doseBts);
        this.frmMan.btWorkerUpdate.setEnabled(!doseBts);
        this.frmMan.btDosimeterInfoNew.setEnabled(!doseBts);
        this.frmMan.btDosimeterInfoUpdate.setEnabled(!doseBts);

        this.frmMan.btNewDoseNote.setEnabled(!doseBts);
        this.frmMan.btUpdateDoseNote.setEnabled(!doseBts);
        this.frmMan.btSaveNewDoseNote.setEnabled(!doseBts);
        this.frmMan.btSaveUpdateDoseNote.setEnabled(!doseBts);
        this.frmMan.btCancelDoseNote.setEnabled(!doseBts);




    }

    // dose notes
    public void setAllDoseNoteBtsInit(boolean doseNoteBts) {

        this.frmMan.btNewDoseNote.setEnabled(doseNoteBts);
        this.frmMan.btUpdateDoseNote.setEnabled(doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setEnabled(doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setVisible(!doseNoteBts);
        this.frmMan.btSaveUpdateDoseNote.setVisible(doseNoteBts);
        this.frmMan.btCancelDoseNote.setEnabled(doseNoteBts);

    }

    public void setNewORUpdateDoseNoteBts(boolean doseNoteBts) {

        this.frmMan.btNewDoseNote.setEnabled(doseNoteBts);
        this.frmMan.btUpdateDoseNote.setEnabled(doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setVisible(doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setEnabled(!doseNoteBts);
        this.frmMan.btSaveUpdateDoseNote.setVisible(!doseNoteBts);
        this.frmMan.btCancelDoseNote.setEnabled(!doseNoteBts);

    }

    public void setSaveNewDoseNoteBts(boolean doseNoteBts) {

        this.frmMan.btNewDoseNote.setEnabled(!doseNoteBts);
        this.frmMan.btUpdateDoseNote.setEnabled(!doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setVisible(doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setEnabled(doseNoteBts);
        this.frmMan.btSaveUpdateDoseNote.setVisible(!doseNoteBts);
        this.frmMan.btCancelDoseNote.setEnabled(doseNoteBts);

    }

    public void setNewDoseNoteBts(boolean doseNoteBts) {

        setDoseBtsNew(doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setVisible(doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setEnabled(doseNoteBts);
        this.frmMan.btSaveUpdateDoseNote.setVisible(!doseNoteBts);
        this.frmMan.btCancelDoseNote.setEnabled(doseNoteBts);

        this.frmMan.btDoseInfoSaveNew.setEnabled(!doseNoteBts);
        this.frmMan.btDoseInfoSaveUpdate.setEnabled(!doseNoteBts);
        this.frmMan.btDoseInfoCancel.setEnabled(!doseNoteBts);

    }

    public void setUpdateDoseNoteBts(boolean doseNoteBts) {

        setDoseBtsNew(doseNoteBts);
        this.frmMan.btNewDoseNote.setEnabled(!doseNoteBts);
        this.frmMan.btUpdateDoseNote.setEnabled(!doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setVisible(!doseNoteBts);
        this.frmMan.btSaveNewDoseNote.setEnabled(!doseNoteBts);
        this.frmMan.btSaveUpdateDoseNote.setVisible(doseNoteBts);
        this.frmMan.btSaveUpdateDoseNote.setEnabled(doseNoteBts);
        this.frmMan.btCancelDoseNote.setEnabled(doseNoteBts);

        this.frmMan.btDoseInfoSaveNew.setEnabled(!doseNoteBts);
        this.frmMan.btDoseInfoSaveUpdate.setEnabled(!doseNoteBts);
        this.frmMan.btDoseInfoCancel.setEnabled(!doseNoteBts);



    }

    // Dosimeter notes
    public void setAllDsmtNoteBtsInit(boolean dsmtNoteBts) {

        this.frmMan.btNewNoteDosimeter.setEnabled(dsmtNoteBts);
        this.frmMan.btUpdateNoteDosimeter.setEnabled(dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setEnabled(dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setVisible(!dsmtNoteBts);
        this.frmMan.btSaveUpdateDsmtNote.setVisible(dsmtNoteBts);
        this.frmMan.btCancelDsmtNote.setEnabled(dsmtNoteBts);

    }

    public void setNewORUpdateDsmtNoteBts(boolean dsmtNoteBts) {

        this.frmMan.btNewNoteDosimeter.setEnabled(dsmtNoteBts);
        this.frmMan.btUpdateNoteDosimeter.setEnabled(dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setVisible(dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setEnabled(!dsmtNoteBts);
        this.frmMan.btSaveUpdateDsmtNote.setVisible(!dsmtNoteBts);
        this.frmMan.btCancelDsmtNote.setEnabled(!dsmtNoteBts);

    }

    public void setSaveNewDsmtNoteBts(boolean dsmtNoteBts) {

        this.frmMan.btNewNoteDosimeter.setEnabled(!dsmtNoteBts);
        this.frmMan.btUpdateNoteDosimeter.setEnabled(!dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setVisible(dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setEnabled(dsmtNoteBts);
        this.frmMan.btSaveUpdateDsmtNote.setVisible(!dsmtNoteBts);
        this.frmMan.btCancelDsmtNote.setEnabled(dsmtNoteBts);

    }

    public void setNewDsmtNoteBts(boolean dsmtNoteBts) {

        setDosimeterBtsNew(dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setVisible(dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setEnabled(dsmtNoteBts);
        this.frmMan.btSaveUpdateDsmtNote.setVisible(!dsmtNoteBts);
        this.frmMan.btCancelDsmtNote.setEnabled(dsmtNoteBts);

        this.frmMan.btDosimeterInfoSaveNew.setEnabled(!dsmtNoteBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setEnabled(!dsmtNoteBts);
        this.frmMan.btDosimeterInfoCancel.setEnabled(!dsmtNoteBts);

    }

    public void setUpdateDsmtNoteBts(boolean dsmtNoteBts) {

        setDosimeterBtsNew(dsmtNoteBts);
        this.frmMan.btNewNoteDosimeter.setEnabled(!dsmtNoteBts);
        this.frmMan.btUpdateNoteDosimeter.setEnabled(!dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setVisible(!dsmtNoteBts);
        this.frmMan.btSaveNewDsmtNote.setEnabled(!dsmtNoteBts);
        this.frmMan.btCancelDsmtNote.setEnabled(dsmtNoteBts);

        this.frmMan.btSaveUpdateDsmtNote.setVisible(dsmtNoteBts);
        this.frmMan.btSaveUpdateDsmtNote.setEnabled(dsmtNoteBts);

        this.frmMan.btDosimeterInfoSaveNew.setEnabled(!dsmtNoteBts);
        this.frmMan.btDosimeterInfoSaveUpdate.setEnabled(!dsmtNoteBts);
        this.frmMan.btDosimeterInfoCancel.setEnabled(!dsmtNoteBts);

    }

    // all   
    public void setAllCancelBts() {

        setDoseBtsSearch(true);
        setDosimeterBtsSearch(true);
        setAllWorkerBtsInit(true);

    }

    public void setAllSearchClearBts() {

        setAllDoseBtsInit(false);
        setAllDosimeterBtsInit(false);
        setAllDoseNoteBtsInit(false);
        setAllDsmtNoteBtsInit(false);
        setAllWorkerBtsInit(true);
        this.frmMan.btWorkerUpdate.setEnabled(false);

    }

    public void setAllSaveOrUpdateBts() {

        setDoseBtsSearch(true);
        setDosimeterBtsSearch(true);
        setAllWorkerBtsInit(true);

    }
}
