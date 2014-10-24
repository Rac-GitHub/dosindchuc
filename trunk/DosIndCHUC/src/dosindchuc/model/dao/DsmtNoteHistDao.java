/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dsmtNotesHist;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.entities.Dsmt_notes_hist;

/**
 *
 * @author ir
 */
public class DsmtNoteHistDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
    private String table_dsmtNotesHist = Conn_db.tbl_dsmtNotesHist;
    

    public DsmtNoteHistDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }


    private void prepareQuery(Dsmt_notes_hist dsmtNoteHist) {

        int i = 0;

        queryList.Add(Tbl_dsmtNotesHist.pk_dsmt_notes, i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmtNoteHist.getPk_dsmt_notes(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmtNotesHist.id_change, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtNoteHist.getId_change(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmtNotesHist.value, i);
        queryList.Add(", ? ", i);
        System.out.println(" --- preparre .. : " + dsmtNoteHist.getValue());
        queryList.Add(dsmtNoteHist.getValue(), i);

    }

    public String insertDsmtNoteHist(Dsmt_notes_hist dsmtNoteHist) {

        prepareQuery(dsmtNoteHist);

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_dsmtNotesHist + " (";
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
