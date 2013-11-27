/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Help.DaoHelper;
import dosindchuc.model.dao.WorkerDao;
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
    
    
    
    public ManagementSearch(ManagementFrm frmMan) {

        daoHelper = new DaoHelper();
        this.frmMan = frmMan;
        workerdao = new WorkerDao();
        
    }

    
    
    public Object [][] searchWorkers() {
        
 //       Object [][] workerList = null;
        int nFields = 6; // numeros de campos retirados da base de dados

        String[][][] searchWhere = {{{"name", "LIKE", frmMan.getSearchWorkerName().getText()}},
            {{"department", "null", frmMan.getSearchCBdepartment().getSelectedItem().toString()}},
            {{"category", "null", frmMan.getSearchCBCategory().getSelectedItem().toString()}},
            {{"id_mec", "LIKE", frmMan.getSearchTxtMec().getText()}}};
//           

        workerList = daoHelper.executeSelectivePreparedQuery("worker", "pk_id, id_mec, name, category, department, status", searchWhere);
       
   
        int nResults = workerList.length;
        
        System.out.println(" nresu = " + nResults);

          
        model = frmMan.setSettingsSearchTable();

        if (nResults > 0) {
           
           Object newRow[] = new Object[nFields - 1 ];   //  por causa do  pk_id
       
            for (int i = 0; i < nResults; i++) {
                for (int j = 1; j < nFields; j++) {    // start at 1 because pk_id
                    System.out.println("asas = " + workerList[i][j]);
                    newRow[j - 1] = workerList[i][j];  // por causa do pk_id
                }
                model.addRow(newRow);
            }
     
        }
        
        return workerList;
        
    }
    
    /*                     */
    
    public void cleanWorkers() {
        
        frmMan.getSearchWorkerName().setText("");
        frmMan.getSearchCBdepartment().setSelectedItem("NoDef");
        frmMan.getSearchCBCategory().setSelectedItem("NoDef");
        frmMan.getSearchTxtMec().setText("");
//        
        model = frmMan.setSettingsSearchTable();
        
        
    }
    
    
    
    /*     */
    
    
    public void fillAllManagement() { 
        
       
        String worker_id =  this.workerList[frmMan.searchTable.getSelectedRow()][0].toString();
    
        fillWorkerInfo(worker_id);
        
         System.out.println(" preenchimento - all " + worker_id );
         
         
      
    
    }
    
    
    /*             */
    public void fillWorkerInfo (String worker_id) {
        
        List<Worker> workerinfo =  workerdao.getWorkersInfo(worker_id);
        
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
    
    
     public void fillDoseInfo (String worker_id) {
        
   
        
    }
    
    
    
}
