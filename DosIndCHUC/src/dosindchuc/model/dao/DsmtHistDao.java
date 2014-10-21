/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dsmt_hist;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.entities.Dsmt_hist;

/**
 *
 * @author ir
 */
public class DsmtHistDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
    private String table_dsmtHist = Conn_db.tbl_dsmtHist;
    

    public DsmtHistDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }


    private void prepareQuery(Dsmt_hist dsmtHist, String trigger) {

        int i = 0;
        
        switch (trigger) {
            case "note":
                queryList.Add(Tbl_dsmt_hist.pk_dsmt_notes, i);
                queryList.Add(" ? ", i);
                queryList.Add(dsmtHist.getPk_dsmt_notes(), i);
                break;
            case "status":
                queryList.Add(Tbl_dsmt_hist.pk_dsmt_status, i);
                queryList.Add(" ? ", i);
                queryList.Add(dsmtHist.getPk_dsmt_status(), i);
                break;
            default:
                break;
        }
        
        i += 1;
        queryList.Add(", " + Tbl_dsmt_hist.id_change, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtHist.getId_change(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_hist.value, i);
        queryList.Add(", ? ", i);
        System.out.println(" --- preparre .. : " + dsmtHist.getValue());
        queryList.Add(dsmtHist.getValue(), i);

    }

    public String insertDsmtHist(Dsmt_hist dsmtHist, String trigger) {

        prepareQuery(dsmtHist, trigger);

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_dsmtHist + " (";
        String valuesInt = " VALUES (";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0);
            valuesInt += queryList.get(i, 1);
            param[i] = queryList.get(i, 2);
        }

        queryList.remove();

        query += ")";
        valuesInt += ")";
        query += valuesInt;

        
        System.out.println(" query : .... : " + query );
        System.out.println(" param : .... : " + query );
        
        
        return daoConnection.insert(query, param);

    }

   
}
