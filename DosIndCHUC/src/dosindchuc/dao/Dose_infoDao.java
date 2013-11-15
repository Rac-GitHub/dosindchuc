/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.dao.Help.CreateDaoException;
import dosindchuc.dao.Help.DaoHelper;
import dosindchuc.dao.Help.QueryMapper;
import dosindchuc.dao.Help.UpdateDaoException;
import dosindchuc.entities.Dose_info;
import dosindchuc.entities.Help.create_enums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
class Dose_infoDao {
    
    
    
    private DaoHelper daoHelper;
	
    public Dose_infoDao () {
        daoHelper = new DaoHelper();
    }
    
    
    
    public List<Dose_info> listDose(int dsmt_id, int worker_id) {
		
		final List<Dose_info> doses = new ArrayList<>();
		
		try {
                        
                        String query = null;
                        if (worker_id == 0 && dsmt_id == 0) {
                            query = "SELECT * from dose_info";
                        } else if (worker_id != 0 && dsmt_id == 0) {
                            query = "SELECT * FROM dose_info WHERE pk_id = " + worker_id;
                        } else if (dsmt_id != 0 && worker_id == 0) {
                            query = "SELECT * FROM dose_info WHERE pk_dsmt = " + dsmt_id;
                        } else {
                            query = "SELECT * FROM dose_info WHERE pk_dsmt = " + dsmt_id + " AND pk_id = " + worker_id;
                        }
                    
                    
			daoHelper.executePreparedQuery(query, new QueryMapper<Dose_info>() {

				@Override
				public List<Dose_info> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dose_info dose = new Dose_info();
						dose.setPk_dose( rset.getShort("pk_dose") );
						dose.setPk_dsmt( rset.getShort("pk_dsmt") );
						dose.setPk_id( rset.getShort("pk_id") );
						dose.setYear( rset.getString("year") );
						dose.setTrimester( create_enums.Trimester.valueOf( rset.getString("trimester")) );
						dose.setMonth( create_enums.month.valueOf( rset.getString("month")) );
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
    
 
      
    public Dose_info insert(Dose_info dose) throws CreateDaoException {

        try {

            daoHelper.beginTransaction();

            final String query =  "INSERT INTO dose_info "
                    + "(pk_dsmt, pk_id, year, trimester, month, hp007, hp10, comments, timestamp) VALUES "
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            
            int id = daoHelper.executePreparedUpdateAndReturnGeneratedKeys(daoHelper.getConnectionFromContext(), query
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
            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new CreateDaoException("Not possible to make the transaction ", e);

        }

        return dose;

    }
    
  
    
    
    public Dose_info update(Dose_info dose, int dsmt_id) throws UpdateDaoException {

        try {

            daoHelper.beginTransaction();
            
            final String query = "UPDATE dosimeter SET pk_id = ? "
                    + ", id = ? , label = ?, type = ? , periodicity = ? , supplier = ? , comments = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ?  WHERE pk_dsmt = " + dsmt_id;

            daoHelper.executePreparedUpdate(daoHelper.getConnectionFromContext(), query
                    , dose.getPk_dsmt()
                    , dose.getPk_id()
                    , dose.getYear()
                    , dose.getTrimester()
                    , dose.getMonth()
                    , dose.getHp007()
                    , dose.getHp10()
                    , dose.getComments()
                    , dose.getTimestamp() );

            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new UpdateDaoException("Not possible to make the transaction ", e);

        }

        return dose;

    }
    

    
}
