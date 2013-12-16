/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.CreateDaoException;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.UpdateDaoException;
import dosindchuc.model.entities.Dose_info;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class Dose_infoDao {
    
    
    
    private DaoConnections daoConnection;
	
    public Dose_infoDao () {
        daoConnection = new DaoConnections();
    }
    
    
    
    public List<Dose_info> getDoseInfo(int dsmt_id, String worker_id) {
		
		final List<Dose_info> doses = new ArrayList<>();
		
		try {
                        
                        String query = null;
                        
                        if (worker_id.isEmpty() && dsmt_id == 0) {
                            query = "SELECT * from dose_info";
                        } else if ( ! worker_id.isEmpty() && dsmt_id == 0) {
                            query = "SELECT * FROM dose_info WHERE pk_id = " + worker_id;
                        } else if (dsmt_id != 0 && worker_id.isEmpty()) {
                            query = "SELECT * FROM dose_info WHERE pk_dsmt = " + dsmt_id;
                        } else {
                            query = "SELECT * FROM dose_info WHERE pk_dsmt = " + dsmt_id + " AND pk_id = " + worker_id;
                        }
                        
                        System.out.println("get dose Info  " + query);
                    
			daoConnection.executePreparedQuery(query, new QueryMapper<Dose_info>() {

				@Override
				public List<Dose_info> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dose_info dose = new Dose_info();
						dose.setPk_dose( rset.getShort("pk_dose") );
						dose.setPk_dsmt( rset.getShort("pk_dsmt") );
						dose.setPk_id( rset.getShort("pk_id") );
						dose.setYear( rset.getString("year") );
						dose.setTrimester( SetEnums.Trimester.valueOf( rset.getString("trimester")) );
						dose.setMonth( SetEnums.month.valueOf( rset.getString("month")) );
                                                dose.setHp007(rset.getFloat("hp007") );
                                                dose.setHp10(rset.getFloat("hp10") );
						dose.setComments( rset.getString("comments") );
						dose.setTimestamp( rset.getString("timestamp") );
                                                dose.setLastchange( rset.getString("lastchange") );
                                                doses.add(dose);
					}
					return doses;
				}
				
			});
		} catch (SQLException e) {
                    e.printStackTrace();
			//ignore exception
		}
		
		return doses;
		
	}
    
 
      
    public Dose_info insert(Dose_info dose) throws SQLException  {

        try {

            final String query =  "INSERT INTO dose_info "
                    + "(pk_dsmt, pk_id, year, trimester, month, hp007, hp10, comments, timestamp) VALUES "
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            
            int id = daoConnection.executePreparedUpdateAndReturnGeneratedKeys(query
                    , dose.getPk_dsmt()
                    , dose.getPk_id()
                    , dose.getYear()
                    , dose.getTrimester().toString()
                    , dose.getMonth().toString()
                    , dose.getHp007()
                    , dose.getHp10()
                    , dose.getComments()
                    , dose.getTimestamp() );
                    
            dose.setPk_dose(id);
            
        } catch (SQLException e) {
            throw new CreateDaoException("Not possible to make the transaction ", e);
        }
 
        return dose;

    }
    
  
    
    
    public Dose_info update(Dose_info dose, int dsmt_id) throws UpdateDaoException {

        try {
            
            final String query = "UPDATE dosimeter SET pk_id = ? "
                    + ", id = ? , label = ?, type = ? , periodicity = ? , supplier = ? , comments = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ?  WHERE pk_dsmt = " + dsmt_id;

            daoConnection.executePreparedUpdate(query
                    , dose.getPk_dsmt()
                    , dose.getPk_id()
                    , dose.getYear()
                    , dose.getTrimester()
                    , dose.getMonth()
                    , dose.getHp007()
                    , dose.getHp10()
                    , dose.getComments()
                    , dose.getTimestamp() );

        } catch (SQLException e) {
            throw new UpdateDaoException("Not possible to make the transaction ", e);
        }

        return dose;

    }
    

    
}
