/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.ManagementTablesModel;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.DsmtHistDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dsmt_hist;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ir
 */
public class ManagementDsmtHist {

    private MainFrm frmMain;
    private ManagementFrm frmMan;
    private DsmtHistDao dsmtHistdao;
//    private DbPkIDs dbPkIDs;
    private ManagementTablesModel tableModel;
    private List<Dsmt_hist> dsmtHist;
    private DefaultTableModel model;

    public ManagementDsmtHist(ManagementFrm frmMan) {

        this.frmMan = frmMan;
//        dbPkIDs = new DbPkIDs();
        dsmtHistdao = new DsmtHistDao();
        tableModel = new ManagementTablesModel(this.frmMain, this.frmMan);
 
    }

    /* ############################################### */
    /*                                                  */
    /*              dosimeter  info                       */
    /*                                                  */
    /* ###############################################  */
 
       
    public void dsmtHist(String dsmt_id) {

        dsmtHist = dsmtHistdao.getDsmtHist(dsmt_id);

        int nResults = dsmtHist.size();

        System.out.println("   nResults dsmtHist -> " + nResults);
        
        if (!(nResults > 0)) {
            dsmtHist = null;
   //         return dsmtHist;
        }


            tableModel.setDefaultDsmtHistTable();

        model = tableModel.getDsmtHistTable();

        ArrayList<Object[]> dsmtHistAllInfo = new ArrayList();

        for (int i = 0; i < nResults; i++) {

            Dsmt_hist dsmthist = dsmtHist.get(i);
            Object newRow[] = new Object[]{dsmthist.getId_change(), dsmthist.getValue(), dsmthist.getLastchange()};
            model.addRow(newRow);

            // info dos dosimetros para a dose and history
//            Object dsmtInfo[] = new Object[]{dosimeter.getPk_dsmt(), dosimeter.getId(), dosimeter.getStatus(), dosimeter.getPeriodicity()};
/*            Object dsmtHistInfo[] = new Object[]{i, dsmthist.getPk_dsmt(), dsmthist.getId_change(), dsmthist.getValue(), 
                dsmthist.getLastchange()};
   
            
            dsmtHistAllInfo.add(i, dsmtHistInfo);
    */        
           
        }

   //     dbPkIDs.setDsmt_hist(dsmtHistAllInfo);

    //    return dsmtHist;

    }
        
 
}

    
  