/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.ManagementFrm;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ir
 */
public class ManagementClean {
    
    
    private DefaultTableModel model;
    private ManagementFrm frmMan;
    
    
    
    public ManagementClean (ManagementFrm frmMan) {

        this.frmMan = frmMan;
        
    }
    
    
    
    public void cleanAllInfo () {
        
        cleanWorker ();
        cleanDosimeter ();
        cleanDose ();
        
    }
    
    
    public void cleanAllInfoWithSearch () {
        
        cleanSearch();
        cleanWorker ();
        cleanDosimeter ();
        cleanDose ();
        
    }
    
      
    public void cleanSearch() {
        
        frmMan.getSearchWorkerName().setText("");
        frmMan.getSearchCBdepartment().setSelectedItem("NoDef");
        frmMan.getSearchCBCategory().setSelectedItem("NoDef");
        frmMan.getSearchTxtMec().setText("");
//        
        model = frmMan.setSettingsSearchTable();
      
    }
    
    
    
    public void cleanWorker () {
        
        this.frmMan.txtWorkerName.setText(null);
        this.frmMan.txtWorkerNick.setText(null);
        this.frmMan.txtWorkerBI.setText(null);
        this.frmMan.txtWorkerBirthYear.setText(null);
        this.frmMan.txtWorkerBirthMonth.setText(null);
        this.frmMan.txtWorkerBirthDay.setText(null);
        this.frmMan.txtWorkerMec.setText(null);
        this.frmMan.txtWorkerSector.setText(null);
        this.frmMan.txtWorkerNationality.setText(null);
        this.frmMan.txtWorkerCretedDate.setText(null);
        this.frmMan.txtWorkerLastModified.setText(null);
        this.frmMan.txtWorkerNIF.setText(null);
        this.frmMan.txtWorkerComments.setText(null);
        this.frmMan.cbWorkerCat.setSelectedIndex(1);
        this.frmMan.cbWorkerDept.setSelectedIndex(1);
        this.frmMan.cbWorkerSex.setSelectedIndex(0);
        this.frmMan.cbWorkerStatus.setSelectedIndex(0);
        
    }
    
    
    public void cleanDosimeter () {
        
        this.frmMan.txtDosimeterNote.setText(null);
        this.frmMan.txtDosimeterNoteAlertdate.setText(null);
        this.frmMan.txtDosimeterNoteStatusDate.setText(null);
        this.frmMan.txtDosimeterNotesDateCreated.setText(null);
        
        this.frmMan.cbDosimeterNotesIndex.setEnabled(false);
        this.frmMan.cbDosimeterNotesAlert.setEnabled(false);
        this.frmMan.cbDosimeterNotesStatus.setEnabled(false);
        
        this.frmMan.setSettingsDosimeterTable();
        
    }
    
    public void cleanDose () {
        
        this.frmMan.txtDoseNote.setText(null);
        this.frmMan.txtDoseNoteLevelDate.setText(null);
        this.frmMan.txtDoseNoteStatusDate.setText(null);
        this.frmMan.txtDoseNotesDateCreated.setText(null);
        
        this.frmMan.cbDoseNoteIndex.setEnabled(false);
        this.frmMan.cbDoseNoteLevel.setEnabled(false);
        this.frmMan.cbDoseNoteStatus.setEnabled(false);
        
        this.frmMan.setSettingsDoseTable();
        
    }
    
    
}
