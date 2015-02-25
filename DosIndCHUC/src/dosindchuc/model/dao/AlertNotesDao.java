/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;


import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dose_notes;
import dosindchuc.globals.Tbl_doses;
import dosindchuc.globals.Tbl_dosimeters;
import dosindchuc.globals.Tbl_dsmt_hist;
import dosindchuc.globals.Tbl_dsmt_notes;
import dosindchuc.globals.Tbl_workers;
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

    private String table_workers = Conn_db.tbl_workers;
    private String table_dmst = Conn_db.tbl_dsmt;
    private String table_dmstNotes = Conn_db.tbl_dsmtNotes;
    private String table_doses = Conn_db.tbl_doses;
    private String table_doseNotes = Conn_db.tbl_doseNotes;
    
    
    public AlertNotesDao() {

        dbPkIDs = new DbPkIDs();
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();


    }

    public List<AlertNotes> getDoseAlerNotes() {

        final List<AlertNotes> alertNotes = new ArrayList<>();

        String query = "SELECT DISTINCT p1." + Tbl_dose_notes.pk_dose_notes + ", p1." + Tbl_dose_notes.alert_level 
                + ", p3." + Tbl_workers.id_mec + ", p3." + Tbl_workers.name + ", p3." + Tbl_workers.department 
                + ", p1." + Tbl_dose_notes.note + ", p1." + Tbl_dose_notes.status + ", p1." + Tbl_dose_notes.lastchange;
        
        String from = " FROM " + table_doseNotes + " as p1, " + table_doses + " as p2, " 
                + table_workers + " as p3";
   
        String where = " WHERE p1." + Tbl_dose_notes.status + " = 'O' and p1." + Tbl_dose_notes.pk_dose + " = p2." + Tbl_doses.pk_dose 
                + " and p3." + Tbl_workers.pk_id + " = p2." + Tbl_doses.pk_id;
        
        String sort = " ORDER BY p1." + Tbl_dose_notes.alert_level + " DESC, p1." + Tbl_dose_notes.lastchange + " DESC";

        query = query + from + where + sort;
        
        
        System.out.println("   query ->  getDoseAlerNotes : " + query);
 
        daoConnection.executePreparedQuery(query, new QueryMapper<AlertNotes>() {
            @Override
            public List<AlertNotes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        AlertNotes alertNote = new AlertNotes();

                        alertNote.setPk_notes(rset.getString(Tbl_dose_notes.pk_dose_notes));
                        alertNote.setNotesLevel(SetEnums.note_alertlevel.valueOf(rset.getString(Tbl_dose_notes.alert_level)));
                        alertNote.setNotesType("dose");
                        alertNote.setNotesMec(rset.getString(Tbl_workers.id_mec));
                        alertNote.setNotesName(rset.getString(Tbl_workers.name));
                        alertNote.setNotesDept(rset.getString(Tbl_workers.department));
                        alertNote.setNotesNote(rset.getString(Tbl_dose_notes.note));
                        alertNote.setNotesStatus(SetEnums.note_status.valueOf(rset.getString(Tbl_dose_notes.status)));
                        alertNote.setNotesDate(rset.getString(Tbl_dose_notes.lastchange).split("\\s+")[0]);
                        alertNotes.add(alertNote);
                    }
                    return alertNotes;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDoseAlerNotes): ",
                            DaoConnections.class, ex);
                }

            }
        });

        System.out.println("   size ->  getDoseAlerNotes : " + alertNotes.size());
        
        return alertNotes;

    }

    public List<AlertNotes> getDsmtAlerNotes() {

        final List<AlertNotes> alertNotes = new ArrayList<>();

        String query = "SELECT DISTINCT p1." + Tbl_dsmt_notes.pk_dsmt_notes + ", p1." + Tbl_dsmt_notes.alert_level 
                + ", p3." + Tbl_workers.id_mec + ", p3." + Tbl_workers.name + ", p3." + Tbl_workers.department 
                + ", p1." + Tbl_dsmt_notes.note + ", p1." + Tbl_dsmt_notes.status + ", p1." + Tbl_dsmt_notes.lastchange;
        
        String from = " FROM " + table_dmstNotes + " as p1, " + table_dmst + " as p2, " 
                + table_workers + " as p3";
        
        String where = " WHERE p1." + Tbl_dsmt_notes.status + " = 'O' and p1." + Tbl_dsmt_notes.pk_dsmt + " = p2." 
                + Tbl_dosimeters.pk_dsmt + " and p3." + Tbl_workers.pk_id + " = p2." + Tbl_dosimeters.pk_id;
        
        String sort = " ORDER BY p1." + Tbl_dsmt_notes.alert_level + " DESC, p2." + Tbl_dsmt_notes.lastchange + " DESC";

        query = query + from + where + sort;

        
        System.out.println("   query ->  getDsmtAlerNotes : " + query);
        
        daoConnection.executePreparedQuery(query, new QueryMapper<AlertNotes>() {
            @Override
            public List<AlertNotes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        AlertNotes alertNote = new AlertNotes();

                        //alertNote.setPk_notes(rset.getString(Tbl_dsmt_status.pk_dsmt_status));
                        alertNote.setPk_notes(rset.getString(Tbl_dsmt_notes.pk_dsmt_notes));
                        alertNote.setNotesLevel(SetEnums.note_alertlevel.valueOf(rset.getString(Tbl_dsmt_notes.alert_level)));
                        alertNote.setNotesType("dsmt");
                        alertNote.setNotesMec(rset.getString(Tbl_workers.id_mec));
                        alertNote.setNotesName(rset.getString(Tbl_workers.name));
                        alertNote.setNotesDept(rset.getString(Tbl_workers.department));
                        alertNote.setNotesNote(rset.getString(Tbl_dsmt_notes.note));
                        alertNote.setNotesStatus(SetEnums.note_status.valueOf(rset.getString(Tbl_dsmt_notes.status)));
                        alertNote.setNotesDate(rset.getString(Tbl_dsmt_hist.lastchange).split("\\s+")[0]);
                        alertNotes.add(alertNote);
                    }
                    return alertNotes;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDsmtAlerNotes): ",
                            DaoConnections.class, ex);
                }
            }
        });

         System.out.println("   size ->  getDsmtAlerNotes   : " + alertNotes.size());
        
        return alertNotes;

    }

    private void prepareQuery(Object[] alertNote, int iRow, JTable table) {

        String newDate = dateAndTime.currDateTime();

        int i = 0;

        queryList.Add(Tbl_dsmt_notes.note, i);
        queryList.Add(" ? ", i);

        queryList.Add(table.getValueAt(iRow, 5).toString(), i);

        i += 1;
        String status = table.getValueAt(iRow, 6).toString();

        queryList.Add(", " + Tbl_dosimeters.status, i);
        queryList.Add(", ? ", i);
        queryList.Add(status, i);

        if (!status.matches(alertNote[7].toString())) {
            i += 1;
            //queryList.Add(", " + Tbl_dsmt_status.status_timestamp, i);
            queryList.Add(", " + Tbl_dosimeters.timestamp, i);
            queryList.Add(", ? ", i);
            queryList.Add(newDate, i);
            alertNote[7] = status;

        }

        i += 1;
        String alertLevel = table.getValueAt(iRow, 1).toString();

        queryList.Add(", " + Tbl_dsmt_notes.alert_level, i);
        queryList.Add(", ? ", i);
        queryList.Add(alertLevel, i);

        if (!alertLevel.matches(alertNote[2].toString())) {
            i += 1;
            queryList.Add(", " + Tbl_dsmt_notes.alert_level_timestamp, i);
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
            query = "UPDATE " + table_doseNotes + " SET ";
            where = " WHERE " + Tbl_dose_notes.pk_dose_notes + " = ";
        }

        if (noteType.matches("dsmt")) {
            query = "UPDATE " + table_dmstNotes + " SET ";
            where = " WHERE " + Tbl_dsmt_notes.pk_dsmt_notes + " = ";
        }

        
        int sizeNparam = queryList.getNumRows();
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += where + alertNote[0];

        
        System.out.println(  " query updateAlertNote --> " + query);
        
        daoConnection.update(query, param);

        queryList.remove();

    }
}
