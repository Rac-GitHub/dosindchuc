/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dose_notes;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Dose_notes;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class Dose_notesDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
    private String table_doseNotes = Conn_db.tbl_doseNotes;
//    private String table_doseNotes = Conn_db.tbl_doseNotes;
    

    public Dose_notesDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }

    public List<Dose_notes> getDose_notesInfo(String dose_id) {

        final List<Dose_notes> dose_notes = new ArrayList<>();
        
        String select = "SELECT * ";
        String from = " FROM " + table_doseNotes;
        String where = " WHERE " + Tbl_dose_notes.pk_dose + " = " + dose_id;
        String sort = " ORDER BY " + Tbl_dose_notes.status + ", " + Tbl_dose_notes.alert_level + " DESC";
        
        String query = null;
        if (dose_id.isEmpty()) {
            query = query + select + from + sort;
        } else {
            query = query + select + from + where + sort;
        }

        daoConnection.executePreparedQuery(query, new QueryMapper<Dose_notes>() {
            @Override
            public List<Dose_notes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dose_notes dose_note = new Dose_notes();

                        dose_note.setPk_dose_notes(rset.getString(Tbl_dose_notes.pk_dose_notes));
                        dose_note.setPk_dose(rset.getString(Tbl_dose_notes.pk_dose));
                        dose_note.setNote(rset.getString(Tbl_dose_notes.note));
                        dose_note.setTimestamp(rset.getString(Tbl_dose_notes.timestamp));
                        dose_note.setStatus(SetEnums.note_status.valueOf(rset.getString(Tbl_dose_notes.status)));
                        dose_note.setStatus_timestamp(rset.getString(Tbl_dose_notes.status_timestamp));
                        dose_note.setAlert_level(SetEnums.note_alertlevel.valueOf(rset.getString(Tbl_dose_notes.alert_level)));
                        dose_note.setAlert_level_timestamp(rset.getString(Tbl_dose_notes.alert_level_timestamp));
                        dose_note.setLastchange(rset.getString(Tbl_dose_notes.lastchange));
                        dose_notes.add(dose_note);
                    }
                    return dose_notes;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDose_notesInfo): ",
                            DaoConnections.class, ex);
                }
            }
        });

        return dose_notes;

    }

    private void prepareQuery(Dose_notes dose_note, String newOrUpdate) {

        int i = 0;

        queryList.Add(Tbl_dose_notes.pk_dose, i);
        queryList.Add(" ? ", i);
        queryList.Add(dose_note.getPk_dose(), i);


        i += 1;
        queryList.Add(", " + Tbl_dose_notes.note, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getNote(), i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.status, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getStatus().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.alert_level, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getAlert_level().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getTimestamp(), i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.status_timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getStatus_timestamp(), i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.alert_level_timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getAlert_level_timestamp(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", " + Tbl_dose_notes.lastchange, i);
            queryList.Add(", ? ", i);
            queryList.Add(dose_note.getLastchange(), i);
        }

    }

    public String insertDoseNote(Dose_notes dose_note) {

        prepareQuery(dose_note, "new");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_doseNotes + " (";
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

        return daoConnection.insert(query, param);

    }

    public void updateDoseNote(Dose_notes dose_note, String dose_note_id) {

        prepareQuery(dose_note, "update");

        int sizeNparam = queryList.getNumRows();
        String query = "UPDATE " + table_doseNotes + " SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE " + Tbl_dose_notes.pk_dose_notes + " = " + dose_note_id;

        daoConnection.update(query, param);

        queryList.remove();

    }
}
