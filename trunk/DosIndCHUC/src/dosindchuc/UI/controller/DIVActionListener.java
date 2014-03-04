/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.UI.swing.Help.DIVTablesModel;
import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.service.DIVService;
import dosindchuc.model.service.Help.YearMonthAndTrimester;
import dosindchuc.model.service.ManagementDose;
import dosindchuc.model.service.ManagementDose_Notes;
import dosindchuc.model.service.ManagementDosimeter;
import dosindchuc.model.service.ManagementDosimeter_Notes;
import dosindchuc.model.service.ManagementSearch;
import dosindchuc.model.service.ManagementWorker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;



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
    private YearMonthAndTrimester yearMonthTrimester;
    
    public Object [][] workerList = null;
    public ActionListener Listeners;
    private DIVTablesModel infoDIVTables;
 
    
    
    public DIVActionListener(DIVFrm frmDIV) {
     
        this.frmDIV = frmDIV;
        
        System.out.println("Anqui no DIV action list " + this.frmDIV);
        
        Listeners = this;
  
   //     serviceClean = new ManagementClean(this.frmMan);
   //     serviceBtns = new ManagementButtons(this.frmMan);
        dbPkIDs = new DbPkIDs();
        serviceDIV = new DIVService(this.frmDIV);
        
        infoDIVTables = new DIVTablesModel(this.frmDIV);
   
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
            case "btDIV_Clean":  serviceDIV.clearNewDIVTables();
                     break;
  /*
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


        System.out.println("noa add listenesrs --- " + this);

        frmDIV.btDIV_Save.addActionListener(this);
        frmDIV.btDIV_Cancel.addActionListener(this);

     //   int indexItem = 0;

       
        
        
        frmDIV.comboDsmtID.addItemListener(new ItemListener() {
          @Override
            public void itemStateChanged(ItemEvent e) {
     
                
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    
                   // System.out.println((state == ItemEvent.SELECTED) ? "Selected" : "Deselected");

                    int selectedRow = infoDIVTables.getTableNewDIVinfo().getSelectedRow();

                    if (!(selectedRow == -1)) {

                        int colSetPerd = 8;
                        int colPerd = 2;

                        yearMonthTrimester = new YearMonthAndTrimester();

                        SetEnums.month Month[] = SetEnums.month.values();
                        SetEnums.Trimester Trimester[] = SetEnums.Trimester.values();

                        int indexSelectedItem = frmDIV.comboDsmtID.getSelectedIndex();

            //            indexItem = indexSelectedItem;
                        
                        System.out.println(e.getItem() + "     dadadada1 --> " + indexSelectedItem + "  selected row -->  " + selectedRow);

                        String newPerd = DbPkIDs.getAllDsmtPeriod().get(selectedRow).get(indexSelectedItem);

                        infoDIVTables.getModelTableNewDIVinfo().setValueAt(newPerd, selectedRow, colPerd);
          

                        switch (newPerd) {
                            case "Mensal":
                                infoDIVTables.getModelTableNewDIVinfo().setValueAt(Month[yearMonthTrimester.Month()], selectedRow, colSetPerd);
                                infoDIVTables.getModelTableNewDIVinfo().setValueAt(yearMonthTrimester.Year(), selectedRow, colSetPerd + 1);
                                break;
                            case "Trimestral":
                                infoDIVTables.getModelTableNewDIVinfo().setValueAt(Trimester[yearMonthTrimester.Trimester()], selectedRow, colSetPerd);
                                infoDIVTables.getModelTableNewDIVinfo().setValueAt(yearMonthTrimester.Year(), selectedRow, colSetPerd + 1);
                                break;

                        }


                        System.out.println(e.getItem() + "     dadadada2 --> " + indexSelectedItem + "  selected row -->  " + selectedRow);

                        System.out.println(e.getItem() + "   dadadada3 --->  " + newPerd);

                    }

                }
            }
        });


        
       System.out.println ( " EStou aqui no tableModel --- >  ") ;
    //    frmDIV.tableNewDIVinfo.
  
 
    


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
