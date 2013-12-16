/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.CreateDaoException;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.UpdateDaoException;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class DosimeterDao {
 
    
    
    private DaoConnections daoConnection;
	
    public DosimeterDao () {
        daoConnection = new DaoConnections();
    }
    
    
    public List<Dosimeter> getDosimetersInfo(int dsmt_id, String worker_id) {
		
		final List<Dosimeter> dosimeters = new ArrayList<>();
		
		try {
                    
                        String query = null;
                        if (worker_id.isEmpty() && dsmt_id == 0) {
                            query = "SELECT * from dosimeter";
                        } else if ( ! (worker_id.isEmpty()) && dsmt_id == 0) {
                            query = "SELECT * FROM dosimeter WHERE pk_id = " + worker_id + " ORDER BY pk_dsmt DESC";
                        } else if (dsmt_id != 0 && worker_id.isEmpty()) {
                            query = "SELECT * FROM dosimeter WHERE pk_dsmt = " + dsmt_id;
                        } else {
                            query = "SELECT * FROM dosimeter WHERE pk_dsmt = " + dsmt_id + " AND pk_id = " + worker_id;
                        }
                           
			daoConnection.executePreparedQuery(query, new QueryMapper<Dosimeter>() {

				@Override
				public List<Dosimeter> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dosimeter dosimeter = new Dosimeter();
						dosimeter.setPk_dsmt( rset.getShort("pk_dsmt") );
						dosimeter.setPk_id( rset.getShort("pk_id") );
						dosimeter.setId( rset.getString("id") );
						dosimeter.setLabel( rset.getString("label") );
						dosimeter.setType( SetEnums.dsmt_type.valueOf( rset.getString("type")) );
						dosimeter.setPeriodicity( SetEnums.dsmt_periodicity.valueOf( rset.getString("periodicity")) );
						dosimeter.setSupplier( SetEnums.dsmt_supplier.valueOf( rset.getString("supplier")) );
						dosimeter.setComments( rset.getString("comments") );
						dosimeter.setTimestamp( rset.getString("timestamp") );
                                                dosimeter.setStatus( SetEnums.status.valueOf( rset.getString("status") ) );
                                                dosimeter.setStatus_timestamp( rset.getString("status_timestamp") );
                                                dosimeter.setLastchange( rset.getString("lastchange") );
                                                dosimeters.add(dosimeter);
					}
 					return dosimeters;
				}
				
			});
		} catch (SQLException e) {
                    e.printStackTrace();
			//ignore exception
		}
		
		return dosimeters;
		
	}
    
    
    
    
    public Dosimeter insert(Dosimeter dosimeter) throws CreateDaoException {

        try {

            final String query = "INSERT INTO dosimeter "
                    + "(pk_id, id, label, type, periodicity, supplier, comments, timestamp, status, status_timestamp) VALUES "
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            
            int id = daoConnection.executePreparedUpdateAndReturnGeneratedKeys(query
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

        } catch (SQLException e) {
            throw new CreateDaoException("Not possible to make the transaction ", e);
        }

        return dosimeter;

    }
    
  
    
    
    public Dosimeter update(Dosimeter dosimeter, int dsmt_id) throws UpdateDaoException {

        try {
            
            final String query = "UPDATE dosimeter SET pk_id = ? "
                    + ", id = ? , label = ?, type = ? , periodicity = ? , supplier = ? , comments = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ?  WHERE pk_dsmt = " + dsmt_id;

            daoConnection.executePreparedUpdate(query
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

        } catch (SQLException e) {
            throw new UpdateDaoException("Not possible to make the transaction ", e);
        }

        return dosimeter;

    }
    
    
    
}
