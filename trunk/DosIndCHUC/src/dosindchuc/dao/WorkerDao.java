/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.entities.Worker;
import dosindchuc.entities.create_enums;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ir
 */
public class WorkerDao {
    
    
    private DaoHelper daoHelper;
    
	
    public WorkerDao () {
	daoHelper = new DaoHelper();
    }
    
    public List<Worker> listAllFromAllWorkers() {
		
		final List<Worker> workers = new ArrayList<>();
		
		try {
		
			daoHelper.executePreparedQuery("select * from worker", new QueryMapper<Worker>() {

				@Override
				public List<Worker> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Worker worker = new Worker();
						worker.setPk_id( rset.getShort("pk_id") );
						worker.setId_mec( rset.getShort("id_mec") );
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
    
    
    
    public void insert (Worker worker) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        try {
            conn = daoHelper.getConnection();
            
            pstmt = conn.prepareStatement("insert into staff (nome, sexo) values ( ? , ? )"
                    , PreparedStatement.RETURN_GENERATED_KEYS );
            
            int index = 0;
            pstmt.setString(++index, worker.getName());
            pstmt.setString(++index, worker.getSex().toString());
            pstmt.executeUpdate();
            
            rset = pstmt.getGeneratedKeys();
            if (rset.next()) {
                worker.setPk_id(rset.getShort(1));
            }
            
        } catch (SQLException ex) {
            throw new SQLException("dadadad", ex);
        }
        
        daoHelper.releaseAll(conn, pstmt);
        
    }
    
    
    
    public void select () {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        try {
            conn = daoHelper.getConnection();
            
            pstmt = conn.prepareStatement("SELECT pk_id, name, category FROM worker WHERE pk_id = 20");
       //     pstmt = conn.prepareStatement("SELECT name FROM worker WHERE pk_id = 20");
            rset = pstmt.executeQuery();
            
            System.out.println("aqui");
            System.out.println("Moving cursor to the first row...");
          
           // rset.first();
            rset.next();
          //  int index = 0;
              rset.getString("name");  
                      System.out.println("aqui");
           // while (rset.next()) {
                
                System.out.println(rset.getString("name"));
                System.out.println(rset.getLong("pk_id"));
                //System.out
           // }
            
            
        } catch (SQLException ex) {
            throw new CreateDaoException("lllll", ex);
        } finally { 
           daoHelper.releaseAll(conn, pstmt); 
        }        
        
        
    }
    
    
    
    
    
}
