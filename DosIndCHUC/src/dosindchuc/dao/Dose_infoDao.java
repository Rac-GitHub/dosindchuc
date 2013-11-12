/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.entities.Dose_info;
import dosindchuc.entities.create_enums;
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
    
    
    public List<Dose_info> listAllFromAllDose() {
		
		final List<Dose_info> doses = new ArrayList<>();
		
		try {
		
			daoHelper.executePreparedQuery("select * from dose_info where pk_dsmt = 10", new QueryMapper<Dose_info>() {

				@Override
				public List<Dose_info> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dose_info dose = new Dose_info();
						dose.setPk_dose( rset.getShort("pk_dose") );
						dose.setPk_dsmt( rset.getShort("pk_dsmt") );
						dose.setPk_id( rset.getShort("pk_id") );
						dose.setYear( rset.getString("year") );
						dose.setTrimester( create_enums.Trimester.valueOf( rset.getString("trimester")) );
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
    
    
    
}
