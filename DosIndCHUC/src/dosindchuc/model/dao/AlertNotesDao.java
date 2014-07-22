/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.AlertNotes;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class AlertNotesDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    private DateAndTime dateAndTime = new DateAndTime();
    private DbPkIDs dbPkIDs;

    public AlertNotesDao() {

        dbPkIDs = new DbPkIDs();
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();


    }

    public List<AlertNotes> getDoseAlerNotes() {

        final List<AlertNotes> alertNotes = new ArrayList<>();

        String query = "SELECT DISTINCT p1.pk_notes_dose, p1.alert_level, p3.id_mec,"
                + "p3.name, p3.department, p1.note, p1.status, p1.lastchange";
        String from = " FROM dose_notes as p1, dose_info as p2, worker as p3";
        String where = " WHERE p1.status = 'O' and p1.pk_dose = p2.pk_dose and p3.pk_id = p2.pk_id";
        String sort = " ORDER BY p1.alert_level DESC , p1.lastchange DESC";

        query = query + from + where + sort;

        daoConnection.executePreparedQuery(query, new QueryMapper<AlertNotes>() {
            @Override
            public List<AlertNotes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        AlertNotes alertNote = new AlertNotes();

                        alertNote.setPk_notes(rset.getString("pk_notes_dose"));
                        alertNote.setNotesLevel(SetEnums.note_alertlevel.valueOf(rset.getString("alert_level")));
                        alertNote.setNotesType("dose");
                        alertNote.setNotesMec(rset.getString("id_mec"));
                        alertNote.setNotesName(rset.getString("name"));
                        alertNote.setNotesDept(rset.getString("department"));
                        alertNote.setNotesNote(rset.getString("note"));
                        alertNote.setNotesStatus(SetEnums.note_status.valueOf(rset.getString("status")));
                        alertNote.setNotesDate(rset.getString("lastchange").split("\\s+")[0]);
                        alertNotes.add(alertNote);
                    }
                    return alertNotes;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDoseAlerNotes): ",
                            DaoConnections.class, ex);
                }

            }
        });

        return alertNotes;

    }

    public List<AlertNotes> getDsmtAlerNotes() {

        final List<AlertNotes> alertNotes = new ArrayList<>();

        String query = "SELECT DISTINCT p1.pk_notes_dsmt, p1.alert_level, p3.id_mec,"
                + "p3.name, p3.department, p1.note, p1.status, p1.lastchange";
        String from = " FROM dosimeter_notes as p1, dosimeter as p2, worker as p3";
        String where = " WHERE p1.status = 'O' and p1.pk_dsmt = p2.pk_dsmt and p3.pk_id = p2.pk_id";
        String sort = " ORDER BY p1.alert_level DESC , p1.lastchange DESC";

        query = query + from + where + sort;

        daoConnection.executePreparedQuery(query, new QueryMapper<AlertNotes>() {
            @Override
            public List<AlertNotes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        AlertNotes alertNote = new AlertNotes();

                        alertNote.setPk_notes(rset.getString("pk_notes_dsmt"));
                        alertNote.setNotesLevel(SetEnums.note_alertlevel.valueOf(rset.getString("alert_level")));
                        alertNote.setNotesType("dsmt");
                        alertNote.setNotesMec(rset.getString("id_mec"));
                        alertNote.setNotesName(rset.getString("name"));
                        alertNote.setNotesDept(rset.getString("department"));
                        alertNote.setNotesNote(rset.getString("note"));
                        alertNote.setNotesStatus(SetEnums.note_status.valueOf(rset.getString("status")));
                        alertNote.setNotesDate(rset.getString("lastchange").split("\\s+")[0]);
                        alertNotes.add(alertNote);
                    }
                    return alertNotes;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDsmtAlerNotes): ",
                            DaoConnections.class, ex);
                }
            }
        });

        return alertNotes;

    }

    private void prepareQuery(Object[] alertNote, int iRow, JTable table) {

        String newDate = dateAndTime.currDateTime();

        int i = 0;

        queryList.Add("note", i);
        queryList.Add(" ? ", i);

        queryList.Add(table.getValueAt(iRow, 5).toString(), i);

        i += 1;
        String status = table.getValueAt(iRow, 6).toString();

        queryList.Add(", status", i);
        queryList.Add(", ? ", i);
        queryList.Add(status, i);

        if (!status.matches(alertNote[7].toString())) {
            i += 1;
            queryList.Add(", status_timestamp", i);
            queryList.Add(", ? ", i);
            queryList.Add(newDate, i);
            alertNote[7] = status;

        }

        i += 1;
        String alertLevel = table.getValueAt(iRow, 1).toString();

        queryList.Add(", alert_level", i);
        queryList.Add(", ? ", i);
        queryList.Add(alertLevel, i);

        if (!alertLevel.matches(alertNote[2].toString())) {
            i += 1;
            queryList.Add(", alert_level_timestamp", i);
            queryList.Add(", ? ", i);
            queryList.Add(newDate, i);
            alertNote[2] = alertLevel;

        }

    }

    public void updateAlertNote(int iRow, JTable table) {

        ArrayList<Object[]> alertNoteInfo;
        alertNoteInfo = dbPkIDs.getAlertNote();

        Object[] alertNote;

        alertNote = alertNoteInfo.get(iRow);

        String noteType = alertNote[1].toString();
        prepareQuery(alertNote, iRow, table);

        alertNoteInfo.set(iRow, alertNote);

        String query = null;
        String where = null;
        if (noteType.matches("dose")) {
            query = "UPDATE dose_notes SET ";
            where = " WHERE pk_notes_dose = ";
        }

        if (noteType.matches("dsmt")) {
            query = "UPDATE dosimeter_notes SET ";
            where = " WHERE pk_notes_dsmt = ";
        }

        int sizeNparam = queryList.getNumRows();
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += where + alertNote[0];

        daoConnection.update(query, param);

        queryList.remove();

    }
}
