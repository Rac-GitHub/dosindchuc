/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.RandomNumbers;
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
    private RandomNumbers random;
    
	
    public Dose_infoDao () {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
        random = new RandomNumbers();
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

        System.out.print(" dadasd adasd a : " + dsmt.getPk_id());
        if (dsmt.getPk_id().isEmpty()) {
        }
        queryList.Add("pk_id", i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmt.getPk_id(), i);

        System.out.print(" dadasd adasd a : " + dsmt.getPk_id());
        i += 1;
        if (dsmt.getId().isEmpty()) {
            dsmt.setId(Integer.toString(random.RandomNumbers(999999, 123399933)));
        }
        queryList.Add(", id", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getId(), i);

        if (!dsmt.getLabel().isEmpty()) {
            i += 1;
            queryList.Add(", label", i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt.getLabel(), i);
        }

        i += 1;
        queryList.Add(", type", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getType().toString(), i);

        i += 1;
        queryList.Add(", periodicity", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getPeriodicity().toString(), i);

        i += 1;
        queryList.Add(", supplier", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getSupplier().toString(), i);

        if (!dsmt.getComments().isEmpty()) {
            i += 1;
            queryList.Add(", comments", i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt.getComments(), i);
        }

        i += 1;
        queryList.Add(", timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getTimestamp(), i);

        i += 1;
        queryList.Add(", status", i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getStatus().toString(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", status_timestamp", i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt.getStatus_timestamp(), i);

            i += 1;
            queryList.Add(", lastchange", i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt.getLastchange(), i);
        }

    }

    
         public String insertDosimeter (Dosimeter dsmt) {
      
            prepareQuery(dsmt,"new");

            int sizeNparam = queryList.getNumRows();

            String query = "INSERT INTO dosimeter (";
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

        
        
     public void updateDosimeter (Dosimeter dsmt, String dsmt_id) {
      
            System.out.println("Dosimeter info no update Dosimeter id mec" + dsmt.getId());
            prepareQuery(dsmt,"update");
            
            int sizeNparam = queryList.getNumRows();
            String query = "UPDATE dosimeter SET ";
            Object param[] = new Object[sizeNparam];

            for (int i = 0; i < sizeNparam; i++) {
                query += queryList.get(i, 0) + " = ? " ;
                param[i] = queryList.get(i, 2);
            }

            query += " WHERE pk_dsmt = " + dsmt_id;

            daoConnection.update(query, param);
            
            queryList.remove();

    }
        
    
 
    
}
