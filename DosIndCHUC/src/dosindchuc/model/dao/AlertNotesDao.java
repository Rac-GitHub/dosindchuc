/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.AlertNotes;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class AlertNotesDao {
    
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
    
    public AlertNotesDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }
    
 
    
     public List<AlertNotes> getDoseAlerNotes() {

        final List<AlertNotes> alertNotes = new ArrayList<>();

        System.out.println("I am in alert dose_ notes ");

        try {

            String query = "SELECT DISTINCT p1.pk_notes_dose, p1.alert_level, p3.id_mec,"
                    + "p3.name, p3.department, p1.note, p1.status, p1.lastchange";
            String from = " FROM dose_notes as p1, dose_info as p2, worker as p3";
            String where = " WHERE p1.status = 'O' and p1.pk_dose = p2.pk_dose and p3.pk_id = p2.pk_id";
            String sort = " ORDER BY p1.alert_level DESC , p1.lastchange DESC";
 
            query = query + from + where + sort;

            daoConnection.executePreparedQuery(query, new QueryMapper<AlertNotes>() {
                @Override
                public List<AlertNotes> mapping(ResultSet rset) throws SQLException {
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
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            //ignore exception
        }

        return alertNotes;

    }
   
     
      public List<AlertNotes> getDsmtAlerNotes() {

        final List<AlertNotes> alertNotes = new ArrayList<>();

        System.out.println("I am in alert dsmt_ notes ");

        try {

            String query = "SELECT DISTINCT p1.pk_notes_dsmt, p1.alert_level, p3.id_mec,"
                    + "p3.name, p3.department, p1.note, p1.status, p1.lastchange";
            String from = " FROM dosimeter_notes as p1, dosimeter as p2, worker as p3";
            String where = " WHERE p1.status = 'O' and p1.pk_dsmt = p2.pk_dsmt and p3.pk_id = p2.pk_id";
            String sort = " ORDER BY p1.alert_level DESC , p1.lastchange DESC";
 
            query = query + from + where + sort;

            daoConnection.executePreparedQuery(query, new QueryMapper<AlertNotes>() {
                @Override
                public List<AlertNotes> mapping(ResultSet rset) throws SQLException {
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
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            //ignore exception
        }

        return alertNotes;

    }
    
}
