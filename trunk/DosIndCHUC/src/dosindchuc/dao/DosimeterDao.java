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
    
    
    public List<Dosimeter> listAllFromAllDosimeters() {
		
		final List<Dosimeter> dosimeters = new ArrayList<>();
		
		try {
		
			daoHelper.executePreparedQuery("select * from dosimeter", new QueryMapper<Dosimeter>() {

				@Override
				public List<Dosimeter> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dosimeter dosimeter = new Dosimeter();
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
                                                workers.add(worker);
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
    
    
}
