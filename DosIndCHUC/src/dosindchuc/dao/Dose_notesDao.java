/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.entities.Dose_notes;
import dosindchuc.entities.create_enums;
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
   
    
      
    public List<Dose_notes> listDosimeters(int dsmt_id) {
		
		final List<Dose_notes> dose_notes = new ArrayList<>();
		
		try {
                    
                        String query = null;
                        if (dsmt_id == 0) {
                            query = "SELECT * from dose_notes";
                        } 
                            query = "SELECT * FROM dose_notes WHERE pk_dsmt = " + dsmt_id;
                        }
                            
                            
			daoHelper.executePreparedQuery(query, new QueryMapper<Dose_notes>() {

				@Override
				public List<Dose_notes> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dose_notes dose_note = new Dose_notes();
						dose_note.setPk_dose( rset.getInt("pk_dose") );
						dose_note.setNote( rset.getString("note") );
						dose_note.setTimestamp( rset.getString("timestamp") );
                                                dose_note.setStatus(create_enums.note_status.valueOf( rset.getString("status") ) );
                                                dose_note.setStatus_timestamp( rset.getString("status_timestamp") );
                                                dose_note.setAlert_level(create_enums.note_alertlevel.valueOf( rset.getString("alert_level") ) );
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
    
    
    
    
    public Dosimeter insert(Dosimeter dosimeter) throws CreateDaoException {

        try {

            daoHelper.beginTransaction();

            int id = daoHelper.executePreparedUpdateAndReturnGeneratedKeys(daoHelper.getConnectionFromContext(), "INSERT INTO dosimeter "
                    + "(pk_id, id, label, type, periodicity, supplier, comments, timestamp, status, status_timestamp) VALUES "
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )"
                    , dosimeter.getPk_id()
                    , dosimeter.getId()
                    , dosimeter.getLabel()
                    , dosimeter.getType().toString()
                    , dosimeter.getPeriodicity().toString()
                    , dosimeter.getSupplier().toString()
                    , dosimeter.getComments()
                    , dosimeter.getTimestamp()
                    , dosimeter.getStatus().toString()
                    , dosimeter.getStatus_timestamp() );
                    
            dosimeter.setPk_dsmt(id);
            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new CreateDaoException("Not possible to make the transaction ", e);

        }

        return dosimeter;

    }
    
  
    
    
    public Dosimeter update(Dosimeter dosimeter, int dsmt_id) throws CreateDaoException {

        try {

            daoHelper.beginTransaction();

            daoHelper.executePreparedUpdate(daoHelper.getConnectionFromContext(), "UPDATE dosimeter SET pk_id = ? "
                    + ", id = ? , label = ?, type = ? , periodicity = ? , supplier = ? , comments = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ?  WHERE pk_dsmt = " + dsmt_id
                    , dosimeter.getPk_id()
                    , dosimeter.getId()
                    , dosimeter.getLabel()
                    , dosimeter.getType().toString()
                    , dosimeter.getPeriodicity().toString()
                    , dosimeter.getSupplier().toString()
                    , dosimeter.getComments()
                    , dosimeter.getTimestamp()
                    , dosimeter.getStatus().toString()
                    , dosimeter.getStatus_timestamp() );

            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new CreateDaoException("Not possible to make the transaction ", e);

        }

        return dosimeter;

    }
    
 
    
}
