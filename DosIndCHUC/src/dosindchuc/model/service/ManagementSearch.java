/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.controller.ManagementActionListener;
import dosindchuc.UI.swing.Help.ManagementTablesModel;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Dose_infoDao;
import dosindchuc.model.dao.Dose_notesDao;
import dosindchuc.model.dao.DosimeterDao;
import dosindchuc.model.dao.Dosimeter_notesDao;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.WorkerDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dose_info;
import dosindchuc.model.entities.Dose_notes;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Dosimeter_notes;
import dosindchuc.model.entities.Worker;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author ir
 */
public class ManagementSearch {
    
    private DaoConnections daoHelper;
    private DefaultTableModel model;
    private Object [][] workerList;
    private ManagementFrm frmMan;
    private WorkerDao workerdao;
    private DbPkIDs dbPkIDs;
    
    private Dose_infoDao doseinfodao;
    private Dose_notesDao doseNotesDao;
    private List<Dose_info> dose_info;
    private List<Dose_notes> dosenotes; 
    
    private ManagementTablesModel tableModel;
    
    private DosimeterDao dosimeterdao;
    private Dosimeter_notesDao dosimeterNotesDao;
    private List<Dosimeter> dosimeter_info;
    private List<Dosimeter_notes> dosimeterNotes;
    
    private ManagementActionListener Listeners;

  
    
    
    public ManagementSearch(ManagementFrm frmMan, ManagementActionListener Listeners) {

        daoHelper = new DaoConnections();
        this.frmMan = frmMan;
        tableModel = new ManagementTablesModel(this.frmMan);
        dbPkIDs = new DbPkIDs();
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
        
        tableModel.setDefaultSearchTable();
        model = tableModel.getSearchTable();
        
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
        
        System.out.println("fill all man  worker_id  " + worker_id);
        
        dbPkIDs.setWorker_id(worker_id);
        
        System.out.println("fill all man  worker_id  " + dbPkIDs.getWorker_id());

        fillWorkerInfo(worker_id);
        fillDoseInfo(worker_id,"list");
        fillDosimeterInfo(worker_id,"list");
    
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
        
        System.out.println("Birth day -- " + worker.getBirth());
        if ( ! (worker.getBirth() == null)  ) { 
            frmMan.getTxtWorkerBirthYear().setText(worker.getBirth().split("-")[0]);
            frmMan.getTxtWorkerBirthMonth().setText(worker.getBirth().split("-")[1]);
            frmMan.getTxtWorkerBirthDay().setText(worker.getBirth().split("-")[2]);
        } else {
            frmMan.getTxtWorkerBirthYear().setText("0000");
            frmMan.getTxtWorkerBirthMonth().setText("00");
            frmMan.getTxtWorkerBirthDay().setText("00");
        }
// 
        frmMan.getTxtWorkerComments().setText(worker.getComments());
        frmMan.getTxtWorkerCretedDate().setText(worker.getTimestamp());
        
        frmMan.getCbWorkerStatus().setSelectedItem(worker.getStatus().name()); 
        
        frmMan.getTxtWorkerLastModified().setText(worker.getLastchange());
        
    }
    
    
    
     
    /* ###############################################  */
    /*                                                  */ 
    /*             Fill Worker dosimeter  info          */
    /*                                                  */ 
    /* ###############################################  */ 
     
      public List fillDosimeterInfo (String worker_id, String newORupdate) {
  
         dosimeter_info =  dosimeterdao.getDosimetersInfo("", worker_id);
            
         int nResults = dosimeter_info.size();
        
         if ( ! (nResults > 0) ) {
             dosimeter_info = null;
             return dosimeter_info;
         } 
        
         System.out.println(" Fill dosimeter com Update  : " + nResults);
         
        if (newORupdate.equalsIgnoreCase("update")) {
            System.out.println(" Fill dosimeter com Update  : " + nResults);
            tableModel.setDefaultDsmtTable("updatedsmt");
        } else {
            tableModel.setDefaultDsmtTable("readonly");
        }
        
        model = tableModel.getDsmtTable();
        System.out.println(" Fill dosimeter com Update model  : " + model);
        
        ArrayList2D dsmtInfo = new ArrayList2D();
        for (int i = 0; i < nResults; i++) {
            
             Dosimeter dosimeter = dosimeter_info.get(i);
             Object newRow[] = new Object [] {dosimeter.getId(), dosimeter.getLabel(), dosimeter.getType(), dosimeter.getPeriodicity()
                     , dosimeter.getSupplier(), dosimeter.getTimestamp(), dosimeter.getComments(), dosimeter.getStatus(), dosimeter.getLastchange()};
             model.addRow(newRow);
             
             
             // info dos dosimetros para a dose
             dsmtInfo.Add(dosimeter.getPk_dsmt(),i);
             dsmtInfo.Add(dosimeter.getId(),i);
             dsmtInfo.Add(dosimeter.getStatus(),i);
             dsmtInfo.Add(dosimeter.getPeriodicity(),i);
          
         }
        
         dbPkIDs.setDsmt_id(dsmtInfo);
        
         return dosimeter_info;
         
    }
    
     
     
     public void fillDosimeterNotesCBIndex () {
         
         clearDosimeterNotesInfo();
        
         int row = frmMan.tableDosimeterInfo.getSelectedRow();
         String pk_dsmt = dosimeter_info.get(row).getPk_dsmt();
         
         dosimeterNotes =  dosimeterNotesDao.getDosimetry_notes(pk_dsmt);
         
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
       
       
       
       
      /* ###############################################  */
    /*                                                  */ 
    /*             Fill Worker dose info                */
    /*                                                  */ 
    /* ###############################################  */ 
     
    
     public List fillDoseInfo (String worker_id, String newORupdate) {
        
         ArrayList doseIds = new ArrayList();
           
         dose_info =  doseinfodao.getDoseInfo("", worker_id);
            
         int nResults = dose_info.size();
        
         if ( ! (nResults > 0) ) {
             dose_info = null;
             return dose_info;
         } 
         
           
        if (newORupdate.equalsIgnoreCase("update")) {
            System.out.println(" Fill dose com Update  : " + nResults);
            tableModel.setDefaultDoseTable("updatedose");
        } else {
            tableModel.setDefaultDoseTable("readonly");
        }
        
        model = tableModel.getDoseTable();
        System.out.println(" Fill dose com Update model  : " + model);
         
 
         for (int i = 0; i < nResults; i++) {
            
             Dose_info worker_dose = dose_info.get(i);
             String dsmtID = dosimeterdao.getDosimetersInfo(worker_dose.getPk_dsmt(), worker_id).get(0).getId();
             
             System.out.println(" Fill dose  label dosimeter   : " + dsmtID);
             
             
             Object newRow[] = new Object [] {dsmtID , worker_dose.getYear(), worker_dose.getTrimester(), worker_dose.getMonth()
                     , worker_dose.getHp007(), worker_dose.getHp10(), worker_dose.getTimestamp(), worker_dose.getComments(), worker_dose.getLastchange()};
             model.addRow(newRow);
             
                  
             System.out.println(" Fill dose : " + i + "   " + worker_dose.getPk_dose());
             doseIds.add(i, worker_dose.getPk_dose());
          
         }
        
         dbPkIDs.setDose_id(doseIds);
  
         return dose_info;
         
    }
    
  
     
     public void fillDoseNotesCBIndex () {
         
         clearDoseNotesInfo ();
          
         int row = frmMan.tableDoseInfo.getSelectedRow();
         String pk_dose = dose_info.get(row).getPk_dose();
         
         dosenotes =  doseNotesDao.getDose_notesInfo(pk_dose);
         
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
     
      
     
}
