/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.UI.swing.Help.DIVButtons;
import dosindchuc.UI.swing.Help.DIVTablesModel;
import dosindchuc.model.dao.DIVDao;
import dosindchuc.model.entities.DIVinfo;
import dosindchuc.model.entities.DIVnotes;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.entities.DIVOldInfo;
import dosindchuc.model.service.Help.YearMonthAndTrimester;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ir
 */
public class DIVService {
    
    private int colOK = 16;
  
    private DIVFrm frmDIV;
    private DIVDao divdao;
    private DbPkIDs dbPkIDs;
    private List<DIVinfo> divInfo;
    private List<DIVOldInfo> oldDivInfo;
    private List<DIVnotes> notesDiv;
    private DIVinfo divINFO;
    
    private YearMonthAndTrimester yearMonthTrimester;

    private DIVTablesModel divTableModel;
    private DefaultTableModel model;
    private DateAndTime dateAndTime = new DateAndTime();
    
    private ArrayList<ArrayList<String>> allDsmtIDs = new ArrayList<>();
    private ArrayList<ArrayList<String>> allDsmtPeriod = new ArrayList<>();
    private DIVButtons setButtonsState;
    
    
    

    public DIVService(final DIVFrm frmDIV) {
        
        this.frmDIV = frmDIV;
        divTableModel = new DIVTablesModel(this.frmDIV);
        divdao = new DIVDao();
        dbPkIDs = new DbPkIDs();
        setButtonsState = new DIVButtons(this.frmDIV);
        divINFO = new DIVinfo();
  
        
    }
    
        
      
     public void saveDIVs () {
   
        divInfo.clear();

        List<Integer> rowToBeRemove = new ArrayList<>();


        DefaultTableModel tmodel = divTableModel.getModelTableNewDIVinfo();

        System.out.println(" Numero de rows " + tmodel.getRowCount());

        int newDivs = tmodel.getRowCount();

        for (int i = 0; i < newDivs; i++) {

            boolean isToSave = tmodel.getValueAt(i, colOK).equals(true);

            System.out.println(" Is to save ? __> " + isToSave + " iiii  ->  " + i);

            String note = null;

            if (isToSave) {

                divINFO.setPk_id(tmodel.getValueAt(i, 0).toString());
                divINFO.setPk_dsmt(tmodel.getValueAt(i, 1).toString());
                divINFO.setPeriodicity(SetEnums.dsmt_periodicity.valueOf(tmodel.getValueAt(i, 2).toString()));
                divINFO.setYear(tmodel.getValueAt(i, 9).toString());
                divINFO.setPerd(tmodel.getValueAt(i, 8).toString());
                divINFO.setHp007(tmodel.getValueAt(i, 10).toString());
                divINFO.setHp10(tmodel.getValueAt(i, 11).toString());

                System.out.println(" Value period --- > " + divINFO.getPeriodicity());

                if (!(tmodel.getValueAt(i, 12) == null)) {
                    divINFO.setComments(tmodel.getValueAt(i, 12).toString());
                }

                if (!(tmodel.getValueAt(i, 13) == null)) {
                    divINFO.setDose_note(tmodel.getValueAt(i, 13).toString());
                }

                divINFO.setTimestamp(dateAndTime.currDateTime());

                divINFO.setNoteAlertlevel(SetEnums.note_alertlevel.valueOf(tmodel.getValueAt(i, 14).toString()));
                divINFO.setNoteStatus(SetEnums.note_status.valueOf(tmodel.getValueAt(i, 15).toString()));


                divInfo.add(divINFO);

                String pk_dose = divdao.saveDIV_doseInfo(divInfo);

                System.out.println(" pk dose --->  " + pk_dose);

                if (!(divINFO.getDose_note() == null)) {

                    String pk_dose_note = divdao.saveDIV_doseNote(divInfo, pk_dose);
                    System.out.println(" pk dose note --->  " + pk_dose_note);

                }


                rowToBeRemove.add(i);


            }

        }

        int nRow = 0;
        for (int i = 0; i < rowToBeRemove.size(); i++) {

            int row = rowToBeRemove.get(i) - nRow;
            tmodel.removeRow(row);

            nRow = nRow + 1;

        }

        
    }
   
    
    
    public void searchDIVInfo() {
       
        frmDIV.txtWorkerNameDIV.setText("");
        clearNewDIVTables();
        clearOldDIVTables();
        clearNotesDIVTables();
               
        String department = frmDIV.getCbDIV_Department().getSelectedItem().toString();
        String dsmt_id = frmDIV.txtDIV_dsmtID.getText();
        String name = frmDIV.getTxtDIV_Name().getText();
        String category = frmDIV.getCbDIV_Category().getSelectedItem().toString();

        System.out.println(" Output de search to DIV --->  depart: " + department + "   dsmt_id:  " + dsmt_id + " name  :   " + name + " category:   " + category);


        divInfo = divdao.getDIVInfo(name, department, category, dsmt_id);

        System.out.println(" Output de search to DIV 1--->  " + divInfo.size());

        removeDuplicates(divInfo);

//        System.out.println(" Output de search to DIV --->  " + divInfo.get(0).getPk_id());



        if (!(divInfo.size() > 0)) {
            return;
        }
    

   
        
    
        System.out.println(" Output de search to DIV ---> 2 " + divInfo.size());
        int iN =0;
        for (DIVinfo div : divInfo) {
  
            Object newDIVInfo[] = new Object[]{div.getPk_id(), div.getName()};
            
            String[] splitedName = newDIVInfo[1].toString().split("\\s+");
            div.setName(splitedName[0] + " " + splitedName[splitedName.length - 1] );

            addCBwithAllDsmtIDs(newDIVInfo[0], iN);

            iN++;

        }
        
        
     //   System.out.println("  Por aqui --- >  " + allDsmtIDs.get(4).get(1));
            
        DbPkIDs.setAllDsmtIDs(allDsmtIDs);
        DbPkIDs.setAllDsmtPeriod(allDsmtPeriod);
       
        
        
        model = setNewDIVTableModel("newTable");   
  
        for (DIVinfo div : divInfo) {
            
            div.setNoteAlertlevel(SetEnums.note_alertlevel.N);
            div.setNoteStatus(SetEnums.note_status.O);
            
            Object newDIVInfo[] = new Object[]{div.getPk_id(), div.getPk_dsmt(), div.getPeriodicity(),
                div.getName(), div.getId_mec(), div.getCategory(), div.getDepartment(), div.getId_dsmt(), div.getMonth(),
                div.getYear(), div.getHp007(), div.getHp10(), div.getComments(), div.getDose_note(), div.getNoteAlertlevel(),
                div.getNoteStatus(), Boolean.FALSE};
            
            
            newDIVInfo = selectPeriod(newDIVInfo);
  
            model.addRow(newDIVInfo);
            
            
        }

        
        System.out.println(" Output de search to DIV --->  " + divInfo.size() + " nR  ---> " + iN);
        //     return workerList; */

     
        setButtonsState.setAllDIVBtsSaveCancel(true);
        
        DbPkIDs.setRowSelectedNewDIV(-1);
        addNewDIVInfoTableListeners(divTableModel.getTableNewDIVinfo());
         
        
    }
    
    
    
    
    
    private void addCBwithAllDsmtIDs (Object divPk_id, int nRow) {
        
        String pk_id = divPk_id.toString();

        ArrayList<Object[]> dsmtIds = new ArrayList();

        ArrayList<String> allDsmtIds = new ArrayList();
        ArrayList<String> allDsmtPerd = new ArrayList();

        dsmtIds = dbPkIDs.getDiv_dsmtID();


        System.out.println("  --- > dsmtIds ---> size :  :  " + dsmtIds.size()); // + "   year ---> " + dateAndTime.currYear());



        for (int i = 0; i < dsmtIds.size(); i++) {

            if (dsmtIds.get(i)[0].toString().matches(pk_id)) {

                allDsmtIds.add(dsmtIds.get(i)[1].toString());
                allDsmtPerd.add(dsmtIds.get(i)[2].toString());
                System.out.println("  --- > dsmtIds ---> row :  :  " + nRow);
                System.out.println("  --- > dsmtIds ---> pk_id :  :  " + dsmtIds.get(i)[0].toString());
                System.out.println("  --- > dsmtIds ---> pk_dsmt :  :  " + dsmtIds.get(i)[1].toString());


            }

        }


        allDsmtIDs.add(nRow, allDsmtIds);
        allDsmtPeriod.add(nRow, allDsmtPerd);



    }
 
    
    
    
    private List<DIVinfo> removeDuplicates(List<DIVinfo> divInfo) {
  

        /* Set of all attributes seen so far */
        Set<String> attributes = new HashSet<>();
        /* All confirmed duplicates go in here */
        List duplicates = new ArrayList<>();

        ArrayList<Object[]> dsmtIds = new ArrayList();
        int ndsmtId = 0;
        for (DIVinfo x : divInfo) {

            if (attributes.contains(x.getPk_id())) {

                duplicates.add(x);

            }

            Object dsmtID[] = new Object[]{x.getPk_id(), x.getId_dsmt(), x.getPeriodicity()};
            dsmtIds.add(ndsmtId, dsmtID);

            ndsmtId++;

            attributes.add(x.getPk_id());

            System.out.println(" Output de search to DIV remove duplicate  --->  " + dsmtIds.size());


        }

        dbPkIDs.setDiv_dsmtID(dsmtIds);

        /* Clean list without any dups */

        divInfo.removeAll(duplicates);

        return divInfo;

    }
    
    
    
    
    private Object[] selectPeriod (Object newDIVInfo[]) {
  
  
       yearMonthTrimester = new YearMonthAndTrimester();
        
        int colPeriod = 2;
        int colMonthTrim = 8;
        int colYear = 9;
        int trimester = 0;
        int year = yearMonthTrimester.Year();


        SetEnums.month Month[] = SetEnums.month.values();
        SetEnums.Trimester Trimester[] = SetEnums.Trimester.values();

        String period = newDIVInfo[colPeriod].toString();

        switch (period) {
            case "Mensal":
              
                int month = yearMonthTrimester.Month();
                year = yearMonthTrimester.Year();
   
                newDIVInfo[colMonthTrim] = Month[month];
                newDIVInfo[colYear] = year;

                return newDIVInfo;

            case "Trimestral":
                
                trimester = yearMonthTrimester.Trimester();
                year = yearMonthTrimester.Year();
                
                newDIVInfo[colMonthTrim] = Trimester[trimester];
                newDIVInfo[colYear] = year;
                return newDIVInfo;

            default:
                newDIVInfo[colMonthTrim] = Trimester[trimester];
                newDIVInfo[colYear] = year;
                return newDIVInfo;

        }



    }

    
     
     
    private void addNewDIVInfoTableListeners(final JTable table) {

        System.out.println(" Estou a entrar no listener _176__>  ... ");

        final TableModel tableModel = table.getModel();
 
     
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int selectedColumn = table.getSelectedColumn();
                int selectedRow = table.getSelectedRow();

                System.out.println(" Estou a entrar no listener --- selected column___>  ... " + selectedColumn);
                System.out.println(" Estou a entrar no listener ---Ncolumns ___>  ... " + table.getColumnCount());
               
             

                //if (!(selectedColumn == (table.getColumnCount() - 1))) {
                if ( selectedColumn == 0  ) {

                    System.out.println(" Selected rows antes if ... " + e.getFirstIndex() + " last index antes if " + e.getLastIndex());


                    if (e.getSource() == table.getSelectionModel() && e.getFirstIndex() >= 0) {


                        System.out.println(" Selected rows ... " + e.getFirstIndex() + " last index " + e.getLastIndex());
                        int rowSelected = table.getSelectedRow();
                        String pk_id = (String) tableModel.getValueAt(table.getSelectedRow(), 0);

                        if (rowSelected != DbPkIDs.getRowSelectedNewDIV()) {
                            // Display the selected item
                            System.out.println("Value selected KKKKK  = " + pk_id);
                    
                            selectDIVtable(pk_id);
                            DbPkIDs.setRowSelectedNewDIV(rowSelected);
                            
                            frmDIV.txtWorkerNameDIV.setText(table.getValueAt(selectedRow, selectedColumn).toString());
                            
                            
                        }

                    }
                } else {
                    
                    frmDIV.txtWorkerNameDIV.setText("");
                    clearOldDIVTables();
                    clearNotesDIVTables();
                }
            }
        });


    }

     
   
    
   
     private void selectDIVtable (String pk_id) {
        
         clearOldDIVTables ();
         
         
         oldDivInfo = divdao.getOldDIVInfo(pk_id);
        
         
        if (!(oldDivInfo.size() > 0)) {
            return;
        }
       
        model = setOldDIVTableModel("newTable");   
  
        for (DIVOldInfo div : oldDivInfo) {
            
            if ( div.getPeriodicity().equalsIgnoreCase("Trimestral") ) {
                div.setChoosePerd(div.getTrimester());
            } 
            
            if ( div.getPeriodicity().equalsIgnoreCase("Mensal") ) {
                div.setChoosePerd(div.getMonth());
            }
       
            div.setTimestamp(div.getTimestamp().toString().split("\\s+")[0]);
       
            
            System.out.println("   LastChange --- > " + div.getLastchange() );
            
          //  if ( testeNullandEmpty.TesteNull(div.getLastchange()) ) {
            if ( div.getLastchange() != null ) {
                div.setLastchange(div.getLastchange().toString().split("\\s+")[0]);
             }
            
            
            Object oldDIVInfo[] = new Object[]{div.getPk_dose(), div.getId_dsmt(), div.getChoosePerd(),
                div.getYear(), div.getHp007(), div.getHp10(), div.getTimestamp(), div.getComments(), div.getLastchange()};
  
            model.addRow(oldDIVInfo);
            
            
        }

    
        
        clearNotesDIVTables();
        DbPkIDs.setRowSelectedOldDIV(-1);
        
        System.out.println(" divTableModel.getTableNotesDIVinfo() +++++ " + divTableModel.getTableNotesDIVinfo());
        
        addOldDIVInfoTableListeners(divTableModel.getTableOldDIVinfo());
         
         
     }
     
     
      
      private void addOldDIVInfoTableListeners(final JTable table) {

        System.out.println(" AQUI ------ Add listener .. " + table.getModel());
          
          
        final TableModel tableModel = table.getModel();
         
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
        
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
            //    int modelColumn = convertColumnIndexToModel(table.getSelectedColumn());
                
                if ( e.getSource() == table.getSelectionModel() && e.getFirstIndex() >= 0 ) {
                
              //  if ( e.getFirstIndex() > -1 ) {
                    
                    int rowSelected = table.getSelectedRow();
                
                String pk_dose = (String)tableModel.getValueAt(table.getSelectedRow(), 0);
                
                if ( rowSelected != DbPkIDs.getRowSelectedOldDIV() ) {
			// Display the selected item
			System.out.println( "Value selected on OLD table = " + pk_dose );
                        
                        selectOldDIVtable (pk_dose);
                      DbPkIDs.setRowSelectedOldDIV(rowSelected);
                }
                
                    
                System.out.println( " Selected rows ... " + e.getFirstIndex() + " last index " + e.getLastIndex());
                

			
                
                }
            }
        });
  
 }
     
     
   
    
   
    private void selectOldDIVtable(String pk_dose) {

        System.out.println("   selectOldDIVtable --- > ");

        notesDiv = divdao.getDIVNotes(pk_dose);

        if (!(notesDiv.size() > 0)) {
            removeNotesDIVTables();
            return;
        }

        model = setNotesDIVTableModel("newTable");

        for (DIVnotes div : notesDiv) {


            System.out.println("   LastChange --- > " + div.getLastchange());

            if (div.getLastchange() != null) {
                div.setLastchange(div.getLastchange().toString().split("\\s+")[0]);
            }

            Object notesDIV[] = new Object[]{div.getNote(), div.getStatus(), div.getAlert_level(), div.getLastchange()};

            model.addRow(notesDIV);

        }


    }
     
     
    
    
    
    
    
    /*
     * 
     *  Chamadas
     *  
     */
      
       
     private DefaultTableModel setNewDIVTableModel ( String type ) {
         
         
         System.out.println("  --- Set MOdel SET TABLE ---- > " + type);
         
             divTableModel.setSettingsNewDIVinfoTable(type);
             model= divTableModel.getModelTableNewDIVinfo();
 
             return model;
         
     }
     
    
     
     
     
    private void clearNewDIVTables () {
         
         System.out.println("  --- Clear TABLE ---- > " );
         
         divTableModel.setSettingsNewDIVinfoTable("newTable");
             
     }
     
     
     /*
      * 
      * 
      */ 
     
     
        private DefaultTableModel setOldDIVTableModel ( String type ) {
         
             divTableModel.setSettingsOldDIVinfoTable(type);
             model = divTableModel.getModelTableOldDIVinfo();
             
             System.out.println("  --- Get MOdel SET TABLE ---- > " + model);
             return model;
         
     }
     
     
     
     private void clearOldDIVTables () {
         
         divTableModel.setSettingsOldDIVinfoTable("newTable");
         divTableModel.setSettingsOldDIVinfoTable("removeTable");
             
     }
     
  
      private DefaultTableModel setNotesDIVTableModel ( String type ) {
         
             divTableModel.setSettingsNotesDIVinfoTable(type);
             model = divTableModel.getModelTableNotesDIVinfo();
             
             System.out.println("  --- Get MOdel SET TABLE Notes ---- > " + model);
             return model;
         
     }
     
     
     
     private void clearNotesDIVTables () {
         
         divTableModel.setSettingsNotesDIVinfoTable("newTable");
         divTableModel.setSettingsNotesDIVinfoTable("removeTable");
   
         
 //        setButtonsState.setAllDIVBtsSearchClean(false);
  
             
     }
     
     
     private void removeNewDIVTables () {
         
         divTableModel.setSettingsNewDIVinfoTable("removeTable");
         DbPkIDs.setRowSelectedNewDIV(-1);
         
     }
     
     
     private void removeOldDIVTables () {
         
         divTableModel.setSettingsOldDIVinfoTable("removeTable");
         DbPkIDs.setRowSelectedOldDIV(-1);
         
     }
     
     
     private void removeNotesDIVTables () {
         
         divTableModel.setSettingsNotesDIVinfoTable("removeTable");
         
     }
     
     
     public void removeDIVTables () {
         
         removeNewDIVTables ();
         removeOldDIVTables ();
         removeNotesDIVTables ();
         
     }
  
     
     
    
}
