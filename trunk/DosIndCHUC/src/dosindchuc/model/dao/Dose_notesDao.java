/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

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

    public Dose_notesDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }

    public List<Dose_notes> getDose_notesInfo(String dose_id) {

        final List<Dose_notes> dose_notes = new ArrayList<>();

        String sort = " ORDER BY status, alert_level DESC";
        String query = null;
        if (dose_id.isEmpty()) {
            query = "SELECT * from dose_notes" + sort;
        } else {
            query = "SELECT * FROM dose_notes WHERE pk_dose= " + dose_id + sort;
        }

        daoConnection.executePreparedQuery(query, new QueryMapper<Dose_notes>() {
            @Override
            public List<Dose_notes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dose_notes dose_note = new Dose_notes();

                        dose_note.setPk_notes_dose(rset.getString("pk_notes_dose"));
                        dose_note.setPk_dose(rset.getString("pk_dose"));
                        dose_note.setNote(rset.getString("note"));
                        dose_note.setTimestamp(rset.getString("timestamp"));
                        dose_note.setStatus(SetEnums.note_status.valueOf(rset.getString("status")));
                        dose_note.setStatus_timestamp(rset.getString("status_timestamp"));
                        dose_note.setAlert_level(SetEnums.note_alertlevel.valueOf(rset.getString("alert_level")));
                        dose_note.setAlert_level_timestamp(rset.getString("alert_level_timestamp"));
                        dose_note.setLastchange(rset.getString("lastchange"));
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

        queryList.Add("pk_dose", i);
        queryList.Add(" ? ", i);
        queryList.Add(dose_note.getPk_dose(), i);


        i += 1;
        queryList.Add(", note", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getNote(), i);

        i += 1;
        queryList.Add(", status", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getStatus().toString(), i);

        i += 1;
        queryList.Add(", alert_level", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getAlert_level().toString(), i);

        i += 1;
        queryList.Add(", timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getTimestamp(), i);

        i += 1;
        queryList.Add(", status_timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getStatus_timestamp(), i);

        i += 1;
        queryList.Add(", alert_level_timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose_note.getAlert_level_timestamp(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", lastchange", i);
            queryList.Add(", ? ", i);
            queryList.Add(dose_note.getLastchange(), i);
        }

    }

    public String insertDoseNote(Dose_notes dose_note) {

        prepareQuery(dose_note, "new");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO dose_notes (";
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
        String query = "UPDATE dose_notes SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE pk_notes_dose = " + dose_note_id;

        daoConnection.update(query, param);

        queryList.remove();

    }
}
