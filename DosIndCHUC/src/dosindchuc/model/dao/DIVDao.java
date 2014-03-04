/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.DIVinfo;
import dosindchuc.model.entities.OldDIVinfo;
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
public class DIVDao {
    
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    private DateAndTime dateAndTime = new DateAndTime();
    private DbPkIDs dbPkIDs;
//    private 
 //   private MainFrm frmMain;
 
    
    
    public DIVDao() {
   //     this.frmMain = frmMain;
        dbPkIDs = new DbPkIDs();
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
 
  
    }
    
 
    
     public List<DIVinfo> getDIVInfo(String name, String department, String category, String dsmt_id) {

        final List<DIVinfo> divInfo = new ArrayList<>();

        System.out.println("I am in DIV info ");

        try {
         
            String query = "SELECT DISTINCT p1.pk_id, p2.pk_dsmt, p1.name,"
                    + " p1.id_mec, p1.department, p1.category, p2.id, p2.periodicity ";
            String from = " FROM worker as p1, dosimeter as p2 ";
    
            String defaulWhere = " p1.status = 'Activo' and p2.status = 'Activo' and p1.pk_id = p2.pk_id";
        
            String[][][] searchWhere = {{{"name", "LIKE", name}},
            {{"department", "null", department}},
            {{"category", "null", category}},
            {{"id", "LIKE", dsmt_id}}};
            
            
             String where = daoConnection.buildQueryWhere(searchWhere);
            
            if (where.isEmpty()) {
           
                where = " WHERE "+ defaulWhere;
            
            } else {
                
                where = " WHERE " + where + "and " + defaulWhere;
                
            }
            
           
            
            
             
             System.out.println(" DIV Where ---- > " + where);
            
            String sort = " ORDER BY p1.name , p1.department DESC, p2.pk_dsmt DESC ";
 
            query = query + from + where + sort;
            
            System.out.println(" DIV Where ---- >  " + query);

            daoConnection.executePreparedQuery(query, new QueryMapper<DIVinfo>() {
                @Override
                public List<DIVinfo> mapping(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        DIVinfo divinfo = new DIVinfo();
            
                        divinfo.setPk_id( rset.getString("pk_id") );
                        divinfo.setPk_dsmt( rset.getString("pk_dsmt") );
                        divinfo.setName( rset.getString("name") );
                        divinfo.setId_mec( rset.getString("id_mec") );
                        divinfo.setCategory( SetEnums.worker_category.valueOf(rset.getString("category")) );
                        divinfo.setDepartment( SetEnums.worker_department.valueOf(rset.getString("department")) );
                        divinfo.setId_dsmt( rset.getString("id") );
                        divinfo.setPeriodicity( SetEnums.dsmt_periodicity.valueOf(rset.getString("periodicity")) );
                        
                        divinfo.setHp007("0.00");
                        divinfo.setHp10("0.00");
                        
                        divInfo.add(divinfo);
                    }
                    return divInfo;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            //ignore exception
        }

        return divInfo;

    }
   
     
     
     
     public List<OldDIVinfo> getOldDIVInfo(String pk_id) {

        final List<OldDIVinfo> oldDIVInfo = new ArrayList<>();

        System.out.println("I am in DIV info ");

        try {
         
            String query = "SELECT DISTINCT p1.pk_dose, p2.periodicity, p2.id, p1.trimester,"
                    + " p1.month, p1.year, p1.hp007, p1.hp10, p1.timestamp, p1.comments, p1.lastchange";
            String from = " FROM dose_info as p1, dosimeter as p2 ";
    
            String where = " WHERE p1.pk_id = " + pk_id + " and p1.pk_dsmt = p2.pk_dsmt ";
  
    
             
             System.out.println(" DIV Where ---- > " + where);
            
            String sort = " ORDER BY p1.timestamp DESC ";
            
            String limit = " LIMIT 0, 10 ";
 
            query = query + from + where + sort + limit;
            
            System.out.println(" DIV Where ---- >  " + query);

            daoConnection.executePreparedQuery(query, new QueryMapper<OldDIVinfo>() {
                @Override
                public List<OldDIVinfo> mapping(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        OldDIVinfo olddivinfo = new OldDIVinfo();
                       olddivinfo.setPk_dose( rset.getString("pk_dose") );
                        olddivinfo.setPeriodicity( rset.getString("periodicity") );
                        olddivinfo.setId_dsmt( rset.getString("id") );
                        olddivinfo.setTrimester( rset.getString("trimester") );
                        olddivinfo.setMonth( rset.getString("month") );
                        olddivinfo.setYear( rset.getString("year") );
                        olddivinfo.setHp007( rset.getString("hp007") );
                        olddivinfo.setHp10( rset.getString("hp10") );
                        olddivinfo.setTimestamp( rset.getString("timestamp") );
                        olddivinfo.setComments( rset.getString("comments") );
                        olddivinfo.setLastchange( rset.getString("lastchange") );
                        
                        oldDIVInfo.add(olddivinfo);
                    }
                    return oldDIVInfo;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            //ignore exception
        }

        return oldDIVInfo;

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
    
      
          
    private void prepareQuery(Object[] alertNote,  int iRow, JTable table) {

      
        
        String newDate = dateAndTime.currDateTime();
        
        int i = 0;

        queryList.Add("note", i);
        queryList.Add(" ? ", i);
        
   //     System.out.println("  note -- no update get value -- >  " + table.getValueAt(iRow, 5) + " -> row  " + iRow + " ---- > type " + noteType);
        queryList.Add(table.getValueAt(iRow, 5).toString(), i);

        i += 1;
        String status = table.getValueAt(iRow, 6).toString();
        
        System.out.print( " Status da note alert --- > " + status + "  old status" + alertNote[7].toString());
        queryList.Add(", status", i);
        queryList.Add(", ? ", i);
        queryList.Add(status, i);

        if ( ! status.matches(alertNote[7].toString()) ) {
             i += 1;
             System.out.print( " Status da note alert, dentro para o time  --- > " + dateAndTime.currDateTime());
             queryList.Add(", status_timestamp", i);
             queryList.Add(", ? ", i);
             queryList.Add(newDate, i);
             alertNote[7] = status;
            
        }
        
        i += 1;
        String alertLevel = table.getValueAt(iRow, 1).toString();
        System.out.print( " Alert Level da note alert --- >  " + alertLevel + "old alert level  " + alertNote[2].toString());
        
        queryList.Add(", alert_level", i);
        queryList.Add(", ? ", i);
        queryList.Add(alertLevel, i);

        if ( ! alertLevel.matches(alertNote[2].toString()) ) {
            i += 1;
            System.out.print( " LEVEL da note alert, dentro para o time  --- > " + dateAndTime.currDateTime());
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
            where = " WHERE pk_notes_dose = " ;
        }
        
        if (noteType.matches("dsmt")) {
            query = "UPDATE dosimeter_notes SET ";
            where = " WHERE pk_notes_dsmt = " ;
        }
        
        System.out.println("query 1 --->   " + queryList );
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
