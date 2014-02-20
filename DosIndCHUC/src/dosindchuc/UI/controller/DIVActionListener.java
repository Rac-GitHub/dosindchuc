/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.UI.swing.Help.DIVTablesModel;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.service.ManagementDose;
import dosindchuc.model.service.ManagementDose_Notes;
import dosindchuc.model.service.ManagementDosimeter;
import dosindchuc.model.service.ManagementSearch;
import dosindchuc.model.service.ManagementWorker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.service.DIVService;
import dosindchuc.model.service.ManagementDosimeter_Notes;
import javax.swing.JTable;



/**
 *
 * @author ir
 */
public final class DIVActionListener implements ActionListener, MouseListener {

    
    private DIVFrm frmDIV;
    private DIVService serviceDIV;
    private ManagementClean serviceClean;
    private ManagementButtons serviceBtns;
    private ManagementSearch serviceSearch;
    private ManagementWorker serviceWorker;
    private ManagementDosimeter serviceDosimeter;
    private ManagementDose serviceDose;
    private ManagementDose_Notes serviceDoseNotes;
    private ManagementDosimeter_Notes serviceDsmtNotes;
    public DbPkIDs dbPkIDs;
    private JTable table;
    
    public Object [][] workerList = null;
    public ActionListener Listeners;
    private DIVTablesModel newInfoDIVTable;
    
    
    public DIVActionListener(DIVFrm frmDIV) {
     
        this.frmDIV = frmDIV;
        
        System.out.println("Anqui no DIV action list " + this.frmDIV);
        
        Listeners = this;
  
   //     serviceClean = new ManagementClean(this.frmMan);
   //     serviceBtns = new ManagementButtons(this.frmMan);
        dbPkIDs = new DbPkIDs();
        serviceDIV = new DIVService(this.frmDIV);
        
        newInfoDIVTable = new DIVTablesModel(this.frmDIV);
   /*     serviceSearch = new ManagementSearch(this.frmMan, this);
        serviceDIV = new DIVService(this.frmMan, this);
        serviceDosimeter = new ManagementDosimeter(this.frmMan);
        serviceDsmtNotes = new ManagementDosimeter_Notes(this.frmMan,this);
        serviceDose = new ManagementDose(this.frmMan);
        serviceDoseNotes = new ManagementDose_Notes(this.frmMan,this); */
        
        addListeners();

    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent aevent) {
        
        
        System.out.println(aevent);
        
        String command = aevent.getActionCommand();
        
        System.out.println(command);
        
        switch (command) {

            case "btDIV_Search":  
                System.out.println(" AQUI no btDIV_Search --- > ");
                serviceDIV.searchDIVInfo();
                     break;
  /*          case "btDIV_Clean":  serviceDIV.searchDIVInfo();
                     break;
           case "cbDIV_Department":  serviceWorker.updateWorker();
                break;
            case "cbDIV_Category":  serviceWorker.updateWorker();
                     break;
            case "txtDIV_ID":  serviceWorker.saveUpdateWorker();
                     break;
            case "txtDIV_Name":  serviceWorker.btWorkerCancel();
                     break;    
            case "btDIV_Save":  serviceDIV.searchDIVInfo();
                     break;
            case "btDIV_cancel":  serviceDIV.searchDIVInfo();
                     break; */
                
        }
        
    }
    
    
    
      /**
     *
     */
    public void addListeners() {
        
        System.out.println("noa add listenesrs --- " + this);
        
        frmDIV.btDIV_Search.addActionListener(this);
        frmDIV.btDIV_Clean.addActionListener(this);
        
        frmDIV.tableOldDosimetry.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("evento tabela::::doseInfo  " + evt);
       //         tableNewDIVInfoMouseClicked(evt);
            }
        });
        
      
       System.out.println("noa add listenesrs --- " + this);
       
   //     frmDIV.cbDIV_Category.addActionListener(this);
   //     frmDIV.cbDIV_Department.addActionListener(this);
       
   /*     frmDIV.txtDIV_dsmtID.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("evento dsmt ID  " + evt);
           /*     frmDIV.cbDIV_Category.setEnabled(false);
                frmDIV.cbDIV_Department.setEnabled(false);
                frmDIV.txtDIV_Name.setEnabled(false); 
            } 
        }); */
        
    //    frmDIV.txtDIV_Name.addActionListener(this);
        
        frmDIV.btDIV_Save.addActionListener(this);
        frmDIV.btDIV_Cancel.addActionListener(this);
        
        
  
        
     
    }

    
    public void addNewDIVInfoTableListeners() {

        newInfoDIVTable.setSettingsNewDIVinfoTable("newToActionListeners");
  
        System.out.println("  Que caracas -TAble- Addd NEw- >>>> " + newInfoDIVTable.getTableNewDIVinfo());
        System.out.println("  Que caracas -MODEL -- ADD NEW  >>>> " + newInfoDIVTable.getModelTableNewDIVinfo());
     /*   
        newInfoDIVTable.getTableNewDIVinfo().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                tableNewDIVInfoMouseClicked(evt);
            }
        });
*/

    }
    
    
    
    public void tableNewDIVInfoMouseClicked(MouseEvent mevent) {
  /*   
        if ( ! this.frmMan.btDoseInfoUpdate.isEnabled() ) {
            return;
        }
        
        
       if ( ! this.frmMan.tableDoseInfo.isEnabled() ) {
            System.out.println("  Aqui dentro dose ---- ");
            return;
        }  */
        
        ArrayList2D doseNoteInfo = new ArrayList2D();

        // info dose-note selected for update
        for (int i = 0; i < 4; i++) {
            doseNoteInfo.Add(null, 0);
        }

        dbPkIDs.setDoseNotes_id(doseNoteInfo);

        System.out.println(" No tableDoseInfoMouseClicked pois " + dbPkIDs.getDoseNotes_id().get(0, 0));

        serviceSearch.fillDoseNotesCBIndex();
 
    }    
 
    
    
    
    
    // generic listeners
    
    @Override
    public void mouseClicked(MouseEvent me) {
      
       
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
        
   
    
}
