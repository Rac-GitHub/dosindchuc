/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.CreateDaoException;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.UpdateDaoException;
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
	
    public Dosimeter_notesDao () {
        daoConnection = new DaoConnections();
    }
   
    
      
    public List<Dosimeter_notes> getDosimetry_notes(String dsmt_id) {
		
		final List<Dosimeter_notes> dsmt_notes = new ArrayList<>();
		
		try {
                    
                        String query = null;
                        if (dsmt_id.isEmpty()) {
                            query = "SELECT * from dosimeter_notes";
                        } else {
                            query = "SELECT * FROM dosimeter_notes WHERE pk_dsmt= " + dsmt_id + " ORDER BY pk_notes_dsmt DESC";
                        }
                            
                            
			daoConnection.executePreparedQuery(query, new QueryMapper<Dosimeter_notes>() {

				@Override
				public List<Dosimeter_notes> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dosimeter_notes dsmt_note = new Dosimeter_notes();
                                                dsmt_note.setPk_notes_dsmt( rset.getInt("pk_notes_dsmt"));
						dsmt_note.setPk_dsmt( rset.getInt("pk_dsmt") );
						dsmt_note.setNote( rset.getString("note") );
						dsmt_note.setTimestamp( rset.getString("timestamp") );
                                                dsmt_note.setStatus(SetEnums.note_status.valueOf( rset.getString("status") ) );
                                                dsmt_note.setStatus_timestamp( rset.getString("status_timestamp") );
                                                dsmt_note.setAlert_level(SetEnums.note_alertlevel.valueOf( rset.getString("alert_level") ) );
                                                dsmt_note.setLastchange( rset.getString("lastchange") );
                                                dsmt_notes.add(dsmt_note);
					}
					return dsmt_notes;
				}
				
			});
		} catch (SQLException e) {
                    e.printStackTrace();
			//ignore exception
		}
		
		return dsmt_notes;
		
	}
    

    
    
    public Dosimeter_notes insert(Dosimeter_notes dsmt_note) throws CreateDaoException {

        try {

            final String query = "INSERT INTO dosimeter_notes "
                    + "(pk_dsmt, note, timestamp, status, status_timestamp, alert_level) VALUES "
                    + "( ? , ? , ? , ? , ? , ? )";
            
            int id = daoConnection.executePreparedUpdateAndReturnGeneratedKeys(query
                    , dsmt_note.getPk_dsmt()
                    , dsmt_note.getNote()
                    , dsmt_note.getTimestamp()
                    , dsmt_note.getStatus().toString()
                    , dsmt_note.getStatus_timestamp()
                    , dsmt_note.getAlert_level().toString() );
 
        } catch (SQLException e) {
            throw new CreateDaoException("Not possible to make the transaction: ", e);
        }

        return dsmt_note;

    }
    
  
    
    
    public Dosimeter_notes update(Dosimeter_notes dsmt_note, int dsmt_note_id) throws UpdateDaoException {

        try {
           
            final String query = "UPDATE dosimeter_notes SET pk_dsmt = ? "
                    + ", note = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ? , alert_level = ?  WHERE pk_notes_dsmt = " + dsmt_note_id;

            daoConnection.executePreparedUpdate(query
                    , dsmt_note.getPk_dsmt()
                    , dsmt_note.getNote()
                    , dsmt_note.getTimestamp()
                    , dsmt_note.getStatus().toString()
                    , dsmt_note.getStatus_timestamp()
                    , dsmt_note.getAlert_level().toString() );

        } catch (SQLException e) {
            throw new UpdateDaoException("Not possible to make the transaction: ", e);
        }

        return dsmt_note;

    }
    
 
    
}
