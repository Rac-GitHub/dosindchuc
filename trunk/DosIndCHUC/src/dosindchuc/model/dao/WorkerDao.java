/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.CreateDaoException;
import dosindchuc.model.dao.Help.DaoHelper;
import dosindchuc.model.dao.Help.DeleteDaoException;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.UpdateDaoException;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.entities.Worker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ir
 */
public class WorkerDao {
    
    
    
    private DaoHelper daoHelper;
    
	
    public WorkerDao () {
	daoHelper = new DaoHelper();
    }
    
    
    
    public List<Worker> getWorkersInfo(String worker_id) {
		
		final List<Worker> workers = new ArrayList<>();
		
		try {   
                        String query = null;
                        if (worker_id.isEmpty()) {
                            query = "SELECT * from worker";
                        } else {
                            query = "SELECT * FROM worker WHERE pk_id = " + worker_id;
                        }
  
			daoHelper.executePreparedQuery(query, new QueryMapper<Worker>() {

				@Override
				public List<Worker> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Worker worker = new Worker();
						worker.setPk_id( rset.getInt("pk_id") );
						worker.setId_mec( rset.getString("id_mec") );
						worker.setName( rset.getString("name") );
						worker.setNick( rset.getString("nick") );
						worker.setBI( rset.getString("BI") );
						worker.setNationality( rset.getString("nationality") );
						worker.setNif( rset.getString("nif") );
						worker.setBirth( rset.getString("birth") );
						worker.setSex( SetEnums.worker_sex.valueOf( rset.getString("sex") ) );
						worker.setCategory( SetEnums.worker_category.valueOf( rset.getString("category") ) );
                                                worker.setDepartment( SetEnums.worker_department.valueOf( rset.getString("department") ) );
                                                worker.setSector( rset.getString("sector") );
						worker.setComments( rset.getString("comments") );
						worker.setTimestamp( rset.getString("timestamp") );
                                                worker.setStatus( SetEnums.status.valueOf( rset.getString("status") ) );
                                                worker.setStatus_timestamp( rset.getString("status_timestamp") );
                                                worker.setLastchange( rset.getString("lastchange") );
                                                workers.add(worker);
					}
					return workers;
				}
				
			});
		} catch (SQLException e) {
                    e.printStackTrace();
			//ignore exception
		}
		
		return workers;
		
	}

    
         
    public Worker insert(Worker worker) throws CreateDaoException {

        try {

            daoHelper.beginTransaction();
            
            final String query = "INSERT INTO worker "
                    + "(id_mec, name, nick, BI, nationality, nif, birth, sex, category, department, sector"
                    + ", comments, timestamp, status, status_timestamp) VALUES "
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";

            int id = daoHelper.executePreparedUpdateAndReturnGeneratedKeys(daoHelper.getConnectionFromContext(), query
                    , worker.getId_mec()
                    , worker.getName()
                    , worker.getNick()
                    , worker.getBI()
                    , worker.getNationality()
                    , worker.getNif()
                    , worker.getBirth()
                    , worker.getSex().toString()
                    , worker.getCategory().toString()
                    , worker.getDepartment().toString()
                    , worker.getSector()
                    , worker.getComments()
                    , worker.getTimestamp()
                    , worker.getStatus().toString()
                    , worker.getStatus_timestamp());

            worker.setPk_id(id);
            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new CreateDaoException("Not possible to make the transaction ", e);

        }

        return worker;

    }
    
  
    
    public Worker update(Worker worker, int worker_id) throws UpdateDaoException {

        try {

            daoHelper.beginTransaction();
            
            final String query = "UPDATE worker SET id_mec = ? "
                    + ", name = ? , nick = ?, BI = ? , nationality = ? , nif = ?, birth = ? , sex = ? , category = ? "
                    + ", department = ? , sector = ? , comments = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ?  WHERE pk_id = " + worker_id;

            daoHelper.executePreparedUpdate(daoHelper.getConnectionFromContext(), query
                    , worker.getId_mec()
                    , worker.getName()
                    , worker.getNick()
                    , worker.getBI()
                    , worker.getNationality()
                    , worker.getNif()
                    , worker.getBirth()
                    , worker.getSex().toString()
                    , worker.getCategory().toString()
                    , worker.getDepartment().toString()
                    , worker.getSector()
                    , worker.getComments()
                    , worker.getTimestamp()
                    , worker.getStatus().toString()
                    , worker.getStatus_timestamp());

            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new UpdateDaoException("Not possible to make the transaction ", e);

        }

        return worker;

    }
    

    public void delete(int worker_id) throws DeleteDaoException {

        try {

            daoHelper.beginTransaction();
            
            final String query = "DELETE FROM worker WHERE pk_id = " + worker_id;

            daoHelper.executePreparedUpdate(daoHelper.getConnectionFromContext(), query);
              

            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new DeleteDaoException("Not possible to make the transaction: ", e);

        }

       
    }
    
    
    
}
