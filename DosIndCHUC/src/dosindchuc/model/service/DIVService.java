/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.UI.swing.Help.DIVButtons;
import dosindchuc.UI.swing.Help.DIVTablesModel;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.model.dao.DIVDao;
import dosindchuc.model.entities.DIVinfo;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.OldDIVinfo;
import dosindchuc.model.service.Help.TesteNullandEmpty;
import dosindchuc.model.service.Help.YearMonthAndTrimester;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;
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
    
    
   
    private MainFrm frmMain;
    private DIVFrm frmDIV;
    private DIVDao divdao;
    private DbPkIDs dbPkIDs;
    private List<DIVinfo> divInfo;
    private List<OldDIVinfo> oldDivInfo;
    
    private YearMonthAndTrimester yearMonthTrimester;

    private DIVTablesModel divTableModel;
    private DefaultTableModel model;
    private DateAndTime dateAndTime = new DateAndTime();
    
    private ArrayList<ArrayList<String>> allDsmtIDs = new ArrayList<>();
    private ArrayList<ArrayList<String>> allDsmtPeriod = new ArrayList<>();
    private DIVButtons setButtonsState;
    
    private TesteNullandEmpty testeNullandEmpty;
/*    private ManagementClean setCleanState;
    private DIVTablesModel_old tableModel;
    private ManagementSearch setDoseInfo;
    private JTable table;
    */
    
    

    public DIVService(DIVFrm frmDIV) {
        
        this.frmDIV = frmDIV;
        divTableModel = new DIVTablesModel(this.frmDIV);
        divdao = new DIVDao();
        dbPkIDs = new DbPkIDs();
        setButtonsState = new DIVButtons(this.frmDIV);
  
        
    }
    
        
    
    // insert into the database 
    
    public void saveNewDIV () {
        
  
        
        setNewDIVTableModel("readonly");
        
        
        
    }
    
       
    
    public void searchDIVInfo() {
       
        clearNewDIVTables();
               
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
            Object newDIVInfo[] = new Object[]{div.getPk_id(), div.getPk_dsmt(), div.getPeriodicity(),
                div.getName(), div.getId_mec(), div.getCategory(), div.getDepartment(), div.getId_dsmt(), div.getMonth(),
                div.getYear(), div.getHp007(), div.getHp10(), div.getComments(), div.getDose_note(), Boolean.TRUE};
            
            
            newDIVInfo = selectPeriod(newDIVInfo);
  
            model.addRow(newDIVInfo);
            
            
        }

        
        System.out.println(" Output de search to DIV --->  " + divInfo.size() + " nR  ---> " + iN);
        //     return workerList; */

     
        setButtonsState.setAllDIVBtsSearchClean(true);
        
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
        int month = 0;
        int trimester = 0;
        int year = yearMonthTrimester.Year();


        SetEnums.month Month[] = SetEnums.month.values();
        SetEnums.Trimester Trimester[] = SetEnums.Trimester.values();

        String period = newDIVInfo[colPeriod].toString();

        switch (period) {
            case "Mensal":
              
                month = yearMonthTrimester.Month();
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

     
    
    /*
     * 
     *  Chamadas
     *  
     */
      
       
     private DefaultTableModel setNewDIVTableModel ( String type ) {
         
             divTableModel.setSettingsNewDIVinfoTable(type);
             model= divTableModel.getModelTableNewDIVinfo();
 
             return model;
         
     }
     
    
     
     
     
     public void clearNewDIVTables () {
         
         divTableModel.setSettingsNewDIVinfoTable("newTable");
         divTableModel.setSettingsNewDIVinfoTable("removeTable");
         
         setButtonsState.setAllDIVBtsSearchClean(false);
  
             
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
   
         
 //        setButtonsState.setAllDIVBtsSearchClean(false);
  
             
     }
     
  
      private void addNewDIVInfoTableListeners(final JTable table) {

        final TableModel tableModel = table.getModel();
         
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
        
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
            //    int modelColumn = convertColumnIndexToModel(table.getSelectedColumn());
                
                if ( e.getSource() == table.getSelectionModel() && e.getFirstIndex() >= 0 ) {
                
              //  if ( e.getFirstIndex() > -1 ) {
                System.out.println( " Selected rows ... " + e.getFirstIndex() + " last index " + e.getLastIndex());
                String pk_id = (String)tableModel.getValueAt(table.getSelectedRow(), 0);

			// Display the selected item
			System.out.println( "Value selected = " + pk_id );
                        
                        selectDIVtable (pk_id);
                
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
  
        for (OldDIVinfo div : oldDivInfo) {
            
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
            
            
            Object newDIVInfo[] = new Object[]{div.getPk_dose(), div.getId_dsmt(), div.getChoosePerd(),
                div.getYear(), div.getHp007(), div.getHp10(), div.getTimestamp(), div.getComments(), div.getLastchange()};
  
            model.addRow(newDIVInfo);
            
            
        }


         
         
         
     }
     
     
     
    
}
