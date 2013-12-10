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
import dosindchuc.model.entities.Worker;
import java.util.List;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author ir
 */
public class ManagementSearch {
    
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

  
    
    
    public ManagementSearch(ManagementFrm frmMan, ManagementSearchActionListener Listeners) {

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
    /*               Search worker                      */
    /*                                                  */ 
    /* ###############################################  */ 
     
    
    public Object [][] searchWorkers() {

        int nFields = 6; // numeros de campos retirados da base de dados

        String[][][] searchWhere = {{{"name", "LIKE", frmMan.getSearchWorkerName().getText()}},
            {{"department", "null", frmMan.getSearchCBdepartment().getSelectedItem().toString()}},
            {{"category", "null", frmMan.getSearchCBCategory().getSelectedItem().toString()}},
            {{"id_mec", "LIKE", frmMan.getSearchTxtMec().getText()}}};

        workerList = daoHelper.executeSelectivePreparedQuery("worker", "pk_id, id_mec, name, category, department, status", searchWhere);
       
        int nResults = workerList.length;
          
        model = frmMan.setSettingsSearchTable();

        if (nResults > 0) {
           
           Object newRow[] = new Object[nFields - 1 ];   //  por causa do  pk_id
       
            for (int i = 0; i < nResults; i++) {
                for (int j = 1; j < nFields; j++) {    // start at 1 because pk_id
                      newRow[j - 1] = workerList[i][j];  // por causa do pk_id
                }
                model.addRow(newRow);
            }
     
        }
        
        return workerList;
        
    }
    
    
    
    /* ###############################################  */
    /*                                                  */ 
    /*        Fill All Management info                  */
    /*                                                  */ 
    /* ###############################################  */ 
     
    
    public void fillAllManagement() { 
        
        String worker_id =  this.workerList[frmMan.searchTable.getSelectedRow()][0].toString();

        fillWorkerInfo(worker_id);
        fillDoseInfo(worker_id);
        fillDosimeterInfo(worker_id);
    
    }
    
    
    
    
    /* ###############################################  */
    /*                                                  */ 
    /*               Worker  info                       */
    /*                                                  */ 
    /* ###############################################  */ 
    
    
    public void fillWorkerInfo (String worker_id) {
        
        List<Worker> workerinfo =  workerdao.getWorkersInfo(worker_id);
        
        int nResults = workerinfo.size();
        
         if ( ! (nResults > 0) ) {
             return;
         } 
       
        Worker worker = workerinfo.get(0);
        
        frmMan.getTxtWorkerName().setText(worker.getName());
        frmMan.getTxtWorkerNick().setText(worker.getNick());
        frmMan.getTxtWorkerMec().setText(worker.getId_mec());
        frmMan.getTxtWorkerBI().setText(worker.getBI());
        frmMan.getTxtWorkerNIF().setText(worker.getNif());
        frmMan.getTxtWorkerNationality().setText(worker.getNationality());
        frmMan.getCbWorkerSex().setSelectedItem(worker.getSex().name());
        frmMan.getCbWorkerCat().setSelectedItem(worker.getCategory().name());
        frmMan.getCbWorkerDept().setSelectedItem(worker.getDepartment().name());
// birth yyyy-mm-dd 
        frmMan.getTxtWorkerBirthYear().setText(worker.getBirth().split("-")[0]);
        frmMan.getTxtWorkerBirthMonth().setText(worker.getBirth().split("-")[1]);
        frmMan.getTxtWorkerBirthDay().setText(worker.getBirth().split("-")[2]);
// 
        frmMan.getTxtWorkerComments().setText(worker.getComments());
        frmMan.getTxtWorkerCreateTimeStamp().setText(worker.getTimestamp());
        
        frmMan.getCbWorkerStatus().setSelectedItem(worker.getStatus().name()); 
        
    }
    
    
    
    
    /* ###############################################  */
    /*                                                  */ 
    /*             Fill Worker dose info                */
    /*                                                  */ 
    /* ###############################################  */ 
     
    
     public List fillDoseInfo (String worker_id) {
        
         dose_info =  doseinfodao.getDoseInfo(0, worker_id);
            
         int nResults = dose_info.size();
        
         if ( ! (nResults > 0) ) {
             dose_info = null;
             return dose_info;
         } 
         
         model = frmMan.setSettingsDoseTable();
        
         for (int i = 0; i < nResults; i++) {
            
             Dose_info worker_dose = dose_info.get(i);
             Object newRow[] = new Object [] {worker_dose.getYear(), worker_dose.getTrimester(), worker_dose.getMonth()
                     , worker_dose.getHp007(), worker_dose.getHp10(), worker_dose.getComments(), worker_dose.getLastchange()};
             model.addRow(newRow);
         
         }
 
         return dose_info;
         
    }
    
  
     
     public void fillDoseNotesCBIndex () {
         
         clearDoseNotesInfo ();
          
         int row = frmMan.tableDoseInfo.getSelectedRow();
         int pk_dose = dose_info.get(row).getPk_dose();
         
         dosenotes =  doseNotesDao.getDose_notesInfo(Integer.toString(pk_dose));
         
         int nResults = dosenotes.size();
        
         if ( ! (nResults > 0) ) {
              return;
         } 
 
         for (int i = 0; i < nResults; i++) {
            frmMan.getCbDoseNoteIndex().addItem(i+1);
         }
         
           frmMan.getCbDoseNoteIndex().setSelectedIndex(0); 
           frmMan.getCbDoseNoteIndex().setEnabled(true);
          
     }
     
         
     
     public void fillDoseNotesInfo () {
         
         int index = frmMan.getCbDoseNoteIndex().getSelectedIndex();
         
         Dose_notes dose_notes = dosenotes.get(index);
         
         frmMan.getTxtDoseNotesDateCreated().setText(dose_notes.getTimestamp());
         frmMan.getTxtDoseNote().setText(dose_notes.getNote());
         frmMan.getCbDoseNoteLevel().setSelectedItem(dose_notes.getAlert_level().name());
      //   frmMan.getTxtDoseNoteLevelDate().setText(dose_notes.get);
         frmMan.getCbDoseNoteStatus().setSelectedItem(dose_notes.getStatus().name());
         frmMan.getTxtDoseNoteStatusDate().setText(dose_notes.getStatus_timestamp());
         
     }
    
     
     public void clearDoseNotesInfo () {
         
         frmMan.cbDoseNoteIndex.removeActionListener(Listeners);
         frmMan.getCbDoseNoteIndex().removeAllItems();
         frmMan.getCbDoseNoteIndex().setEnabled(false);
         frmMan.cbDoseNoteIndex.addActionListener(Listeners);
 
         frmMan.getTxtDoseNotesDateCreated().setText("");
         frmMan.getTxtDoseNote().setText("");
         frmMan.getCbDoseNoteLevel().setSelectedItem(1);
      //   frmMan.getTxtDoseNoteLevelDate().setText(dose_notes.get);
         frmMan.getCbDoseNoteStatus().setSelectedItem(1);
         frmMan.getTxtDoseNoteStatusDate().setText("");
   
     }
     
     
     
    /* ###############################################  */
    /*                                                  */ 
    /*             Fill Worker dosimeter  info          */
    /*                                                  */ 
    /* ###############################################  */ 
     
      public List fillDosimeterInfo (String worker_id) {
  
         dosimeter_info =  dosimeterdao.getDosimetersInfo(0, worker_id);
            
         int nResults = dosimeter_info.size();
        
         if ( ! (nResults > 0) ) {
             dosimeter_info = null;
             return dosimeter_info;
         } 
        
         model = frmMan.setSettingsDosimeterTable();
        
         for (int i = 0; i < nResults; i++) {
            
             Dosimeter dosimeter = dosimeter_info.get(i);
             Object newRow[] = new Object [] {dosimeter.getId(), dosimeter.getLabel(), dosimeter.getType(), dosimeter.getPeriodicity()
                     , dosimeter.getSupplier(), dosimeter.getStatus(), dosimeter.getLastchange()};
             model.addRow(newRow);
         
         }
  
         return dosimeter_info;
         
    }
    
     
     
     public void fillDosimeterNotesCBIndex () {
         
         clearDosimeterNotesInfo();
        
         int row = frmMan.tableDosimeterInfo.getSelectedRow();
         int pk_dsmt = dosimeter_info.get(row).getPk_dsmt();
         
         dosimeterNotes =  dosimeterNotesDao.getDosimetry_notes(Integer.toString(pk_dsmt));
         
         int nResults = dosimeterNotes.size();
        
         if ( ! (nResults > 0) ) {
              return;
         } 
         
         for (int i = 0; i < nResults; i++) {
            frmMan.getCbDosimeterNotesIndex().addItem(i+1);
         }
         
           frmMan.getCbDosimeterNotesIndex().setSelectedIndex(0); 
           frmMan.getCbDosimeterNotesIndex().setEnabled(true);
           
     }
     
     
     
     
     public void fillDosimeterNotesInfo () {
         
         int index = frmMan.getCbDosimeterNotesIndex().getSelectedIndex();
         
         Dosimeter_notes dsmt_notes = dosimeterNotes.get(index);
         
         frmMan.getTxtDosimeterNotesDateCreated().setText(dsmt_notes.getTimestamp());
         frmMan.getTxtDosimeterNote().setText(dsmt_notes.getNote());
         frmMan.getCbDosimeterNotesAlert().setSelectedItem(dsmt_notes.getAlert_level().name());
      //   frmMan.getTxtDoseNoteLevelDate().setText(dose_notes.get);
         frmMan.getCbDosimeterNotesStatus().setSelectedItem(dsmt_notes.getStatus().name());
         frmMan.getTxtDosimeterNoteStatusDate().setText(dsmt_notes.getStatus_timestamp());
        
         
     }
    
     
       public void clearDosimeterNotesInfo () {
         
         frmMan.cbDosimeterNotesIndex.removeActionListener(Listeners);
         frmMan.getCbDosimeterNotesIndex().removeAllItems();
         frmMan.getCbDosimeterNotesIndex().setEnabled(false);
         frmMan.cbDosimeterNotesIndex.removeActionListener(Listeners);
         
         frmMan.getTxtDosimeterNotesDateCreated().setText("");
         frmMan.getTxtDosimeterNote().setText("");
         frmMan.getCbDosimeterNotesAlert().setSelectedItem(1);
      //   frmMan.getTxtDoseNoteLevelDate().setText(dose_notes.get);
         frmMan.getCbDosimeterNotesStatus().setSelectedItem(1);
         frmMan.getTxtDosimeterNoteStatusDate().setText("");
   
     }
       
     
}
