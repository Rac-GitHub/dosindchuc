/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.UI.swing.Help.DIVTablesModel;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.model.dao.DIVDao;
import dosindchuc.model.entities.DIVinfo;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

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

    private DIVTablesModel divTableModel;
    private DefaultTableModel model;
    private DateAndTime dateAndTime = new DateAndTime();
/*    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private DIVTablesModel_old tableModel;
    private ManagementSearch setDoseInfo;
    private JTable table;
    
    */

    public DIVService(DIVFrm frmDIV) {
        
        this.frmDIV = frmDIV;
        divTableModel = new DIVTablesModel(this.frmDIV);
        divdao = new DIVDao();
        
    }
    
        
    
    // insert into the database 
    
    public void saveNewDIV () {
        
  
        
        divTableModel.setDefaultNewDIVTable("readonly");
        
        
        
    }
    
       
    
    public void searchDIVInfo() {
       
        String department = frmDIV.getCbDIV_Department().getSelectedItem().toString();
        String dsmt_id = frmDIV.txtDIV_dsmtID.getText();
        String name = frmDIV.getTxtDIV_Name().getText();
        String category = frmDIV.getCbDIV_Category().getSelectedItem().toString();

        System.out.println(" Output de search to DIV --->  depart: " + department + "   dsmt_id:  " + dsmt_id + " name  :   " + name + " category:   " + category);


        divInfo = divdao.getDIVInfo(name, department, category, dsmt_id);

        System.out.println(" Output de search to DIV 1--->  " + divInfo.size());

   //     removeDuplicates(divInfo);

        System.out.println(" Output de search to DIV --->  " + divInfo.get(0).getPk_id());



        if (!(divInfo.size() > 0)) {
            return;
        }
    

        model = setTableModel("toedit");
        
    
        System.out.println(" Output de search to DIV ---> 2 " + divInfo.size());
        int iN =0;
        for (DIVinfo div : divInfo) {
            Object newDIVInfo[] = new Object[]{div.getPk_id(), div.getPk_dsmt(), div.getPeriodicity(),
                div.getName(), div.getId_mec(), div.getCategory(), div.getDepartment(), div.getId_dsmt(), div.getMonth(),
                div.getYear(), div.getHp007(), div.getHp10(), div.getComments(), div.getDose_note(), Boolean.TRUE};
            
            
            System.out.println("  --- > mes:  " + dateAndTime.currMonth() + "   year ---> " + dateAndTime.currYear());
            
    //        newDIVInfo = selectPeriod(newDIVInfo);
            
            iN++;
            
            model.addRow(newDIVInfo);
            
        }

        System.out.println(" Output de search to DIV --->  " + divInfo.size() + " nR  ---> " + iN);
        //     return workerList; */

    }
    
    
    
    private List<DIVinfo> removeDuplicates(List<DIVinfo> divInfo) {
        
        /* Set of all attributes seen so far */
        Set<String> attributes = new HashSet<>();
        /* All confirmed duplicates go in here */
        List duplicates = new ArrayList<>();
        
        ArrayList<Object []> dsmtIds = new ArrayList();
        int ndsmtId = 0;
        for (DIVinfo x : divInfo) {
            if (attributes.contains(x.getPk_id())) {
                duplicates.add(x);
                
            }
            attributes.add(x.getPk_id());
            
            Object dsmtID[] = new Object [] {x.getPk_id(),x.getId_dsmt(),x.getPeriodicity()};
            dsmtIds.add(ndsmtId, dsmtID);
            
            ndsmtId++;
        }
        
        /* Clean list without any dups */
        
        divInfo.removeAll(duplicates);
          
        return divInfo;
        
    }
    
    
    
    
    private Object[] selectPeriod (Object newDIVInfo[]) {
  
        Calendar c = Calendar.getInstance();

        int colPeriod = 2;
        int colMonthTrim = 8;
        int colYear = 9;
        int month = 0;
        int trimester = 0;
        int year = c.get(Calendar.YEAR);


        SetEnums.month Month[] = SetEnums.month.values();
        SetEnums.Trimester Trimester[] = SetEnums.Trimester.values();

        String period = newDIVInfo[colPeriod].toString();

        switch (period) {
            case "Mensal":
                c.add(Calendar.MONTH, -1);

                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                newDIVInfo[colMonthTrim] = Month[month];
                newDIVInfo[colYear] = year;

                return newDIVInfo;

            case "Trimestral":
                c.add(Calendar.MONTH, -4);

                trimester = (c.get(Calendar.MONTH)) / 3;
                year = c.get(Calendar.YEAR);

                newDIVInfo[colMonthTrim] = Trimester[trimester];
                newDIVInfo[colYear] = year;
                return newDIVInfo;

            default:
                newDIVInfo[colMonthTrim] = Trimester[trimester];
                newDIVInfo[colYear] = year;
                return newDIVInfo;

        }



    }

    
       
     private DefaultTableModel setTableModel ( String type ) {
         
             divTableModel.setSettingsNewDIVinfoTable(type);  // setSettingsAlertTable(type);
             model = divTableModel.getModelTableNewDIVinfo();
             
             System.out.println("  --- Get MOdel SET TABLE ---- > " + model);
             return model;
         
     }
     
    
    
}
