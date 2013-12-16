/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.CreateDaoException;
import dosindchuc.model.dao.Help.DaoConnections;
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
    
    
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
	
    public WorkerDao () {
	daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
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
  
			daoConnection.executePreparedQuery(query, new QueryMapper<Worker>() {

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

    
         
    /**
     *
     * @param params
     * @return
     * @throws CreateDaoException
     */
    public int insert(String query, Object [] param  ) throws CreateDaoException {

        int id = 0;
 
        try {
  
            id = daoConnection.executePreparedUpdateAndReturnGeneratedKeys(query, param);
  
        } catch (SQLException e) {
          throw new CreateDaoException("Not possible to make the transaction ", e);
        }

        return id;

    }
    
    
    
    
  
    
    public void update(String query, Object [] param) throws UpdateDaoException {

        try {
            System.out.println("Query: " + query);
            System.out.println("Param: " + param[0] + " " 
                    + param[1] + " "
                    + param[2] + " "
                    + param[3] + " "
                    + param[4] + " "
                    + param[5] + " "
                    + param[6] + " "
                    + param[7] + " ");
            daoConnection.executePreparedUpdate(query, param);
 
        } catch (SQLException e) {
            throw new UpdateDaoException("Not possible to make the transaction ", e);
        }

    }
    
    
    
    

    public void delete(int worker_id) throws DeleteDaoException {

        try {

            final String query = "DELETE FROM worker WHERE pk_id = " + worker_id;

            daoConnection.executePreparedUpdate(query);

        } catch (SQLException e) {
            throw new DeleteDaoException("Not possible to make the transaction: ", e);
        }

       
    }
    
    
      
    public void prepareQuery (Worker worker, String newOrUpdate) {
       
        int i=0;
        if (worker.getName().isEmpty()) {
        }
        queryList.Add("name",i);
        queryList.Add(" ? ",i);
        queryList.Add(worker.getName(),i);
  
        i+=1;
        if (worker.getId_mec().isEmpty()) {
        }
        queryList.Add(", id_mec",i);
        queryList.Add(", ? ",i);
        System.out.println("prepare query .... " + worker.getId_mec());
        
        queryList.Add(worker.getId_mec(),i);
        System.out.println("prepare query .... " + queryList.get(1, 2));
  
          
        if (! worker.getNick().isEmpty()) {
            i+=1;
            queryList.Add(", nick",i);
            queryList.Add(", ? ",i);
            queryList.Add(worker.getNick(),i);
        }
 
        if (! worker.getBI().isEmpty()) {
            i+=1;
            queryList.Add(", BI",i);
            queryList.Add(", ? ",i);
            queryList.Add(worker.getBI(),i);
        }
        
        if (! worker.getNationality().isEmpty()) {
            i+=1;
            queryList.Add(", nationality",i);
            queryList.Add( ", ? ", i);
            queryList.Add(worker.getNationality(), i);
       }
        
        if (! worker.getNif().isEmpty()) {
            i+=1;
            queryList.Add(", nif", i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getNif(), i);
       }
        
        if (! worker.getBirth().isEmpty()) {
            i+=1;
            queryList.Add(", birth", i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getBirth(), i);
        }
        
        i+=1;
        queryList.Add(", sex", i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getSex().toString(), i);

        i+=1;
        queryList.Add(", category", i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getCategory().toString(), i);
 
  
        i+=1;
        queryList.Add(", department", i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getDepartment().toString(), i);
        
        if (! worker.getSector().isEmpty()) {
            i+=1;
            queryList.Add(", sector", i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getSector(), i);
        }
        
        if (! worker.getComments().isEmpty()) {
            i+=1;
            queryList.Add(", comments", i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getComments(), i);
        }
 
        i+=1;
        queryList.Add(", timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getTimestamp(), i);
      
        i+=1;
        queryList.Add(", status", i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getStatus().toString(), i);
  
        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", status_timestamp", i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getStatus_timestamp(), i);

            i += 1;
            queryList.Add(", lastchange", i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getLastchange(), i);
        }
 
    }
    
    
         public int insertWorker (Worker worker) {
      
            prepareQuery(worker,"new");

            int sizeNparam = queryList.getNumRows();

            String query = "INSERT INTO worker (";
            String valuesInt = " VALUES (";
            Object param[] = new Object[sizeNparam];

            for (int i = 0; i < sizeNparam; i++) {
                query += queryList.get(i, 0);
                valuesInt += queryList.get(i, 1);
                param[i] = queryList.get(i, 2);
            }

            queryList.remove();
            
            query += ")";
            valuesInt += ")";
            query += valuesInt;
            
            return insert(query, param);
 
    }

        
        
     public void updateWorker (Worker worker, String worker_id) {
      
            System.out.println("Worker info no update Worker id mec" + worker.getId_mec());
            prepareQuery(worker,"update");
            
            int sizeNparam = queryList.getNumRows();
            String query = "UPDATE worker SET ";
            Object param[] = new Object[sizeNparam];

            for (int i = 0; i < sizeNparam; i++) {
                query += queryList.get(i, 0) + " = ? " ;
                param[i] = queryList.get(i, 2);
            }

            query += " WHERE pk_id = " + worker_id;

            update(query, param);
            
            queryList.remove();

    }
        
    
    
}
