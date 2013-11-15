/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.entities.Worker;
import dosindchuc.entities.create_enums;
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
    
    
    
    public List<Worker> listWorkers(int worker_id) {
		
		final List<Worker> workers = new ArrayList<>();
		
		try {   
                    
                        String query = "";
                        if (worker_id == 0) {
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
						worker.setSex( create_enums.worker_sex.valueOf( rset.getString("sex") ) );
						worker.setCategory( create_enums.worker_category.valueOf( rset.getString("category") ) );
                                                worker.setDepartment( create_enums.worker_department.valueOf( rset.getString("department") ) );
                                                worker.setSector( rset.getString("sector") );
						worker.setComments( rset.getString("comments") );
						worker.setTimestamp( rset.getString("timestamp") );
                                                worker.setStatus( create_enums.status.valueOf( rset.getString("status") ) );
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

            int id = daoHelper.executePreparedUpdateAndReturnGeneratedKeys(daoHelper.getConnectionFromContext(), "INSERT INTO worker "
                    + "(id_mec, name, nick, BI, nationality, nif, birth, sex, category, department, sector"
                    + ", comments, timestamp, status, status_timestamp) VALUES "
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )"
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
    
  
    
    public Worker update(Worker worker, int worker_id) throws CreateDaoException {

        try {

            daoHelper.beginTransaction();

            daoHelper.executePreparedUpdate(daoHelper.getConnectionFromContext(), "UPDATE worker SET id_mec = ? "
                    + ", name = ? , nick = ?, BI = ? , nationality = ? , nif = ?, birth = ? , sex = ? , category = ? "
                    + ", department = ? , sector = ? , comments = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ?  WHERE pk_id = " + worker_id
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
            throw new CreateDaoException("Not possible to make the transaction ", e);

        }

        return worker;

    }
    
    
//    
//    
//    public void select () {
//        
//                
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rset = null;
//        
//        try {
//            conn = daoHelper.getConnection();
//            
//            pstmt = conn.prepareStatement("SELECT pk_id, name, category FROM worker WHERE pk_id = 20");
//       //     pstmt = conn.prepareStatement("SELECT name FROM worker WHERE pk_id = 20");
//            rset = pstmt.executeQuery();
//            
//            System.out.println("aqui");
//            System.out.println("Moving cursor to the first row...");
//          
//           // rset.first();
//            rset.next();
//          //  int index = 0;
//              rset.getString("name");  
//                      System.out.println("aqui");
//           // while (rset.next()) {
//                
//                System.out.println(rset.getString("name"));
//                System.out.println(rset.getLong("pk_id"));
//                //System.out
//           // }
//            
//            
//        } catch (SQLException ex) {
//            throw new CreateDaoException("lllll", ex);
//        } finally { 
//           daoHelper.releaseAll(conn, pstmt); 
//        }        
//        
//        
//    }
//    
//    
    
    
    
}
