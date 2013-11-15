/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.entities.Dosimeter;
import dosindchuc.entities.create_enums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class DosimeterDao {
 
    
    
    private DaoHelper daoHelper;
	
    public DosimeterDao () {
        daoHelper = new DaoHelper();
    }
    
    
    public List<Dosimeter> listDosimeters(int dsmt_id, int worker_id) {
		
		final List<Dosimeter> dosimeters = new ArrayList<>();
		
		try {
                    
                        String query = "";
                        if (worker_id == 0 && dsmt_id == 0) {
                            query = "SELECT * from dosimeter";
                        } else if (worker_id != 0 && dsmt_id == 0) {
                            query = "SELECT * FROM dosimeter WHERE pk_id = " + worker_id;
                        } else if (dsmt_id != 0 && worker_id == 0) {
                            query = "SELECT * FROM dosimeter WHERE pk_dsmt = " + dsmt_id;
                        } else {
                            query = "SELECT * FROM dosimeter WHERE pk_dsmt = " + dsmt_id + " AND pk_id = " + worker_id;
                        }
                            
                            
			daoHelper.executePreparedQuery(query, new QueryMapper<Dosimeter>() {

				@Override
				public List<Dosimeter> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dosimeter dosimeter = new Dosimeter();
						dosimeter.setPk_dsmt( rset.getShort("pk_dsmt") );
						dosimeter.setPk_id( rset.getShort("pk_id") );
						dosimeter.setId( rset.getString("id") );
						dosimeter.setLabel( rset.getString("label") );
						dosimeter.setType( create_enums.dsmt_type.valueOf( rset.getString("type")) );
						dosimeter.setPeriodicity( create_enums.dsmt_periodicity.valueOf( rset.getString("periodicity")) );
						dosimeter.setSupplier( create_enums.dsmt_supplier.valueOf( rset.getString("supplier")) );
						dosimeter.setComments( rset.getString("comments") );
						dosimeter.setTimestamp( rset.getString("timestamp") );
                                                dosimeter.setStatus( create_enums.status.valueOf( rset.getString("status") ) );
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
