/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.CreateDaoException;
import dosindchuc.model.dao.Help.DaoHelper;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.UpdateDaoException;
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
    
    
    
    private DaoHelper daoHelper;
	
    public Dose_notesDao () {
        daoHelper = new DaoHelper();
    }
   
    
      
    public List<Dose_notes> getDose_notesInfo(String dose_id) {
		
		final List<Dose_notes> dose_notes = new ArrayList<>();
		
                
                System.out.println("dose_id??? " + dose_id);
                
		try {
                        System.out.println("dose_id??? " + dose_id);
                        String query = null;
                        if ( dose_id.isEmpty() ) {
                            query = "SELECT * from dose_notes ORDER BY pk_notes_dose DESC";
                  //          query = "SELECT * from dose_notes";
                        } else {
                            query = "SELECT * FROM dose_notes WHERE pk_dose= " + dose_id + " ORDER BY pk_notes_dose DESC";
                  //          query = "SELECT * FROM dose_notes WHERE pk_dose= " + dose_id;
                        }
                           
                        System.out.println("dose_id??? " + query);
                            
			daoHelper.executePreparedQuery(query, new QueryMapper<Dose_notes>() {

				@Override
				public List<Dose_notes> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dose_notes dose_note = new Dose_notes();
                                                dose_note.setPk_notes_dose( rset.getInt("pk_notes_dose") );
						dose_note.setPk_dose( rset.getInt("pk_dose") );
						dose_note.setNote( rset.getString("note") );
						dose_note.setTimestamp( rset.getString("timestamp") );
                                                dose_note.setStatus(SetEnums.note_status.valueOf( rset.getString("status") ) );
                                                dose_note.setStatus_timestamp( rset.getString("status_timestamp") );
                                                dose_note.setAlert_level(SetEnums.note_alertlevel.valueOf( rset.getString("alert_level") ) );
                                                dose_note.setLastchange( rset.getString("lastchange") );
                                                dose_notes.add(dose_note);
					}
					return dose_notes;
				}
				
			});
		} catch (SQLException e) {
                    e.printStackTrace();
			//ignore exception
		}
		
		return dose_notes;
		
	}
    

    
    
    public Dose_notes insert(Dose_notes dose_note) throws CreateDaoException {

        try {

            daoHelper.beginTransaction();

            final String query = "INSERT INTO dose_notes "
                    + "(pk_dose, note, timestamp, status, status_timestamp, alert_level) VALUES "
                    + "( ? , ? , ? , ? , ? , ? )";
            
            int id = daoHelper.executePreparedUpdateAndReturnGeneratedKeys(daoHelper.getConnectionFromContext(), query
                    , dose_note.getPk_dose()
                    , dose_note.getNote()
                    , dose_note.getTimestamp()
                    , dose_note.getStatus().toString()
                    , dose_note.getStatus_timestamp()
                    , dose_note.getAlert_level().toString() );
                  
                    
            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new CreateDaoException("Not possible to make the transaction: ", e);

        }

        return dose_note;

    }
    
  
    
    
    public Dose_notes update(Dose_notes dose_note, int dose_note_id) throws UpdateDaoException {

        try {

            daoHelper.beginTransaction();
            
            final String query = "UPDATE dose_notes SET pk_dose = ? "
                    + ", note = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ? , alert_level = ?  WHERE pk_notes_dose = " + dose_note_id;

            daoHelper.executePreparedUpdate(daoHelper.getConnectionFromContext(), query
                    , dose_note.getPk_dose()
                    , dose_note.getNote()
                    , dose_note.getTimestamp()
                    , dose_note.getStatus().toString()
                    , dose_note.getStatus_timestamp()
                    , dose_note.getAlert_level().toString() );

            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new UpdateDaoException("Not possible to make the transaction: ", e);

        }

        return dose_note;

    }
    
 
    
}
