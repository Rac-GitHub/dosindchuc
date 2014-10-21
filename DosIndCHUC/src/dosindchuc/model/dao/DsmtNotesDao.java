/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dsmt_notes;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Dsmt_notes;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class DsmtNotesDao {
    
    
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
    
    String table_dsmtNotes = Conn_db.tbl_dsmtNotes;
    
     public DsmtNotesDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }

    public List<Dsmt_notes> getDsmt_notes(String dsmt_id) {

        final List<Dsmt_notes> dsmt_notes = new ArrayList<>();

        String query = "";
        String select = "SELECT * ";
        String from = " FROM " + table_dsmtNotes;
        String where = " WHERE " + Tbl_dsmt_notes.pk_dsmt + " = ";
        String sort = " ORDER BY " + Tbl_dsmt_notes.status + ", " + Tbl_dsmt_notes.alert_level + " DESC, " +
                Tbl_dsmt_notes.lastchange + " DESC";

        if (dsmt_id.isEmpty()) {
            query = query + select + from + sort;
        } else {
            query = query + select + from + where + dsmt_id + sort;
        }


        daoConnection.executePreparedQuery(query, new QueryMapper<Dsmt_notes>() {
            @Override
            public List<Dsmt_notes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dsmt_notes dsmt_note = new Dsmt_notes();

                        dsmt_note.setPk_dsmt_notes(rset.getString(Tbl_dsmt_notes.pk_dsmt_notes));
                        dsmt_note.setPk_dsmt(rset.getString(Tbl_dsmt_notes.pk_dsmt));
                        dsmt_note.setNote(rset.getString(Tbl_dsmt_notes.note));
                        dsmt_note.setTimestamp(rset.getString(Tbl_dsmt_notes.timestamp));
                        dsmt_note.setStatus(SetEnums.note_status.valueOf(rset.getString(Tbl_dsmt_notes.status)));
                        dsmt_note.setStatus_timestamp(rset.getString(Tbl_dsmt_notes.status_timestamp));
                        dsmt_note.setAlert_level(SetEnums.note_alertlevel.valueOf(rset.getString(Tbl_dsmt_notes.alert_level)));
                        dsmt_note.setAlert_level_timestamp(rset.getString(Tbl_dsmt_notes.alert_level_timestamp));
                        dsmt_note.setLastchange(rset.getString(Tbl_dsmt_notes.lastchange));
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

    private void prepareQuery(Dsmt_notes dsmt_note, String newOrUpdate) {

        int i = 0;

        queryList.Add(Tbl_dsmt_notes.pk_dsmt, i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmt_note.getPk_dsmt(), i);


        i += 1;
        queryList.Add(", "+ Tbl_dsmt_notes.note, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getNote(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_notes.status, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getStatus().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_notes.alert_level, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getAlert_level().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_notes.timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getTimestamp(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_notes.status_timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getStatus_timestamp(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_notes.alert_level_timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt_note.getAlert_level_timestamp(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", "+ Tbl_dsmt_notes.lastchange, i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt_note.getLastchange(), i);
        }

    }

    public String insertDsmtNote(Dsmt_notes dsmt_note) {

        prepareQuery(dsmt_note, "new");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_dsmtNotes + " (";
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

    public void updateDsmtNote(Dsmt_notes dstm_note, String dsmt_note_id) {

        prepareQuery(dstm_note, "update");

        int sizeNparam = queryList.getNumRows();
        String query = "UPDATE " + table_dsmtNotes + " SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE " + Tbl_dsmt_notes.pk_dsmt_notes + " = " + dsmt_note_id;

        daoConnection.update(query, param);

        queryList.remove();

    }
}
