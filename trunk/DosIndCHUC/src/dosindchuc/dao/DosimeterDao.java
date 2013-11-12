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
    
    
}
