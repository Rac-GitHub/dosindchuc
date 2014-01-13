/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.QueryMapper;
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
    private ArrayList2D queryList;
    
	
    public Dose_infoDao () {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }
    
    
    
    public List<Dose_info> getDoseInfo(String dsmt_pkid, String worker_id) {
		
		final List<Dose_info> doses = new ArrayList<>();
		
		try {
                        System.out.println("get INfo "+ worker_id.isEmpty() + "  " + dsmt_pkid.isEmpty());
                        String sort = " ORDER BY pk_dose DESC";
                        String limit = " LIMIT 10";
                        
                        String query = null;
                        
                        if ( worker_id.isEmpty() && dsmt_pkid.isEmpty() ) {
                            query = "SELECT * from dose_info" + sort;
                        } else if ( ! worker_id.isEmpty() && dsmt_pkid.isEmpty() ) {
                            query = "SELECT * FROM dose_info WHERE pk_id = " + worker_id + sort;
                        } else if (! (dsmt_pkid.isEmpty()) && worker_id.isEmpty()) {
                            query = "SELECT * FROM dose_info WHERE pk_dsmt = " + dsmt_pkid + sort;
                        } else {
                            query = "SELECT * FROM dose_info WHERE pk_dsmt = " + dsmt_pkid + " AND pk_id = " + worker_id + sort;
                        }
                        
                        System.out.println("get dose Info  " + query);
                    
			daoConnection.executePreparedQuery(query, new QueryMapper<Dose_info>() {

				@Override
				public List<Dose_info> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Dose_info dose = new Dose_info();
						dose.setPk_dose( rset.getString("pk_dose") );
						dose.setPk_dsmt( rset.getString("pk_dsmt") );
						dose.setPk_id( rset.getString("pk_id") );
						dose.setYear( rset.getString("year") );
						dose.setTrimester( SetEnums.Trimester.valueOf( rset.getString("trimester")) );
						dose.setMonth( SetEnums.month.valueOf( rset.getString("month")) );
                                                dose.setHp007(rset.getString("hp007") );
                                                dose.setHp10(rset.getString("hp10") );
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
    
 
    
     private void prepareQuery (Dose_info dose, String newOrUpdate) {
  
        int i = 0;
     
        queryList.Add("year", i);
        queryList.Add(" ? ", i);
        queryList.Add(dose.getYear(), i);
        
        if ( ! dose.getPk_dsmt().isEmpty() ) {
            i += 1;
            queryList.Add(", pk_dsmt", i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getPk_dsmt(), i);
        }
        

        if ( ! dose.getPk_id().isEmpty()) {
            i += 1;
            queryList.Add(", pk_id", i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getPk_id(), i);
        }

        i += 1;
        queryList.Add(", trimester", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose.getTrimester().toString(), i);

        i += 1;
        queryList.Add(", month", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose.getMonth().toString(), i);

        
        if (! dose.getHp007().isEmpty()) {
            i += 1;
            queryList.Add(", hp007", i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getHp007(), i);
        }

        if (! dose.getHp10().isEmpty()) {
            i += 1;
            queryList.Add(", hp10", i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getHp10(), i);
        }

        
        if (! dose.getComments().isEmpty()) {
            i += 1;
            queryList.Add(", comments", i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getComments(), i);
        }

        i += 1;
        queryList.Add(", timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(dose.getTimestamp(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", lastchange", i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getLastchange(), i);
        }

    }

    
         public String insertDose (Dose_info dose) {
      
            prepareQuery(dose,"new");

            int sizeNparam = queryList.getNumRows();

            String query = "INSERT INTO dose_info (";
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
            
            return daoConnection.insert(query, param);
 
    }

        
        
     public void updateDose (Dose_info dose, String dose_id) {
      
            System.out.println("Dose info no update Dose id mec" + dose.getPk_dose());
            prepareQuery(dose,"update");
            
            int sizeNparam = queryList.getNumRows();
            String query = "UPDATE dose_info SET ";
            Object param[] = new Object[sizeNparam];

            for (int i = 0; i < sizeNparam; i++) {
                query += queryList.get(i, 0) + " = ? " ;
                param[i] = queryList.get(i, 2);
            }

            query += " WHERE pk_dose = " + dose_id;

            daoConnection.update(query, param);
            
            queryList.remove();

    }
        
    
 
    
}
