/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_doseNotesHist;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.entities.Dose_notes_hist;

/**
 *
 * @author ir
 */
public class DoseNotesHistDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
    private String table_doseNotesHist = Conn_db.tbl_doseNotesHist;
    

    public DoseNotesHistDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }


    private void prepareQuery(Dose_notes_hist doseNoteHist) {

        int i = 0;

        queryList.Add(Tbl_doseNotesHist.pk_dose_notes, i);
        queryList.Add(" ? ", i);
        queryList.Add(doseNoteHist.getPk_dose_notes(), i);


        i += 1;
        queryList.Add(", " + Tbl_doseNotesHist.id_change, i);
        queryList.Add(", ? ", i);
        queryList.Add(doseNoteHist.getId_change(), i);

        i += 1;
        queryList.Add(", " + Tbl_doseNotesHist.value, i);
        queryList.Add(", ? ", i);
        System.out.println(" --- preparre .. : " + doseNoteHist.getValue());
        queryList.Add(doseNoteHist.getValue(), i);

    }

    public String insertDoseNoteHist(Dose_notes_hist doseNoteHist) {

        prepareQuery(doseNoteHist);

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_doseNotesHist + " (";
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
