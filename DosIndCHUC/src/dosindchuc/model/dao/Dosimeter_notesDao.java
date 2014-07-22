/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Dosimeter_notes;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class Dosimeter_notesDao {
    
    
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
     public Dosimeter_notesDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }

    public List<Dosimeter_notes> getDosimetry_notes(String dsmt_id) {

        final List<Dosimeter_notes> dsmt_notes = new ArrayList<>();

        String query = null;
        String sort = " ORDER BY status, alert_level DESC, lastchange DESC";
        if (dsmt_id.isEmpty()) {
            query = "SELECT * from dosimeter_notes" + sort;
        } else {
            query = "SELECT * FROM dosimeter_notes WHERE pk_dsmt= " + dsmt_id + sort;
        }


        daoConnection.executePreparedQuery(query, new QueryMapper<Dosimeter_notes>() {
            @Override
            public List<Dosimeter_notes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dosimeter_notes dsmt_note = new Dosimeter_notes();

                        dsmt_note.setPk_notes_dsmt(rset.getString("pk_notes_dsmt"));
                        dsmt_note.setPk_dsmt(rset.getString("pk_dsmt"));
                        dsmt_note.setNote(rset.getString("note"));
                        dsmt_note.setTimestamp(rset.getString("timestamp"));
                        dsmt_note.setStatus(SetEnums.note_status.valueOf(rset.getString("status")));
                        dsmt_note.setStatus_timestamp(rset.getString("status_timestamp"));
                        dsmt_note.setAlert_level_timestamp(rset.getString("alert_level_timestamp"));
                        dsmt_note.setAlert_level(SetEnums.note_alertlevel.valueOf(rset.getString("alert_level")));
                        dsmt_note.setLastchange(rset.getString("lastchange"));
                        dsmt_notes.add(dsmt_note);

                    }
                    return dsmt_notes;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDosimetry_notes): ",
                            DaoConnections.class, ex);
                }
            }
        });

        return dsmt_notes;

    }

    private void prepareQuery(Dosimeter_notes dsmt_note, String newOrUpdate) {

        int i = 0;

        queryList.Add("pk_dsmt", i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmt_note.getPk_dsmt(), i);


        i += 1;
        queryList.Add(", note", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getNote(), i);

        i += 1;
        queryList.Add(", status", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getStatus().toString(), i);

        i += 1;
        queryList.Add(", alert_level", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getAlert_level().toString(), i);

        i += 1;
        queryList.Add(", timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getTimestamp(), i);

        i += 1;
        queryList.Add(", status_timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getStatus_timestamp(), i);

        i += 1;
        queryList.Add(", alert_level_timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getAlert_level_timestamp(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", lastchange", i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt_note.getLastchange(), i);
        }

    }

    public String insertDsmtNote(Dosimeter_notes dsmt_note) {

        prepareQuery(dsmt_note, "new");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO dosimeter_notes (";
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

    public void updateDsmtNote(Dosimeter_notes dstm_note, String dsmt_note_id) {

        prepareQuery(dstm_note, "update");

        int sizeNparam = queryList.getNumRows();
        String query = "UPDATE dosimeter_notes SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE pk_notes_dsmt = " + dsmt_note_id;

        daoConnection.update(query, param);

        queryList.remove();

    }
}
