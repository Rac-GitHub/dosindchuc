/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dsmt_status;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.RandomNumbers;
import dosindchuc.model.entities.Dsmt_status;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class DsmtStatusDao {
 
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;

    String table_dsmtStatus = Conn_db.tbl_dsmtStatus;
    
    
    public DsmtStatusDao () {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }
    
    
    
    public List<Dsmt_status> getDsmtStatus(String dsmt_id) {
	
        final List<Dsmt_status> dsmtStatus = new ArrayList<>();
    
        String query = "";
        String select = "SELECT * ";
        String from = " FROM " + table_dsmtStatus;
        String where = " WHERE " + Tbl_dsmt_status.pk_dsmt + " = ";
        String sort = " ORDER BY " + Tbl_dsmt_status.status + ", " + Tbl_dsmt_status.alert_level + " DESC, " +
                Tbl_dsmt_status.lastchange + " DESC";

        if (dsmt_id.isEmpty()) {
            query = query + select + from + sort;
        } else {
            query = query + select + from + where + dsmt_id + sort;
        }


        
        System.out.println("query ----- > " + query);
        
        daoConnection.executePreparedQuery(query, new QueryMapper<Dsmt_status>() {
            @Override
            public List<Dsmt_status> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dsmt_status dsmtstatus = new Dsmt_status();
                        
                        dsmtstatus.setPk_dsmt(rset.getString(Tbl_dsmt_status.pk_dsmt));
                        dsmtstatus.setPk_dsmt(rset.getString(Tbl_dsmt_status.pk_dsmt));
                        dsmtstatus.setPk_id(rset.getString(Tbl_dsmt_status.pk_id));
                        dsmtstatus.setStatus(SetEnums.dsmt_status.valueOf(rset.getString(Tbl_dsmt_status.status)));
                        dsmtstatus.setStatus_timestamp(rset.getString(Tbl_dsmt_status.status_timestamp));
                        dsmtstatus.setNote(rset.getString(Tbl_dsmt_status.note));
                        dsmtstatus.setTimestamp(rset.getString(Tbl_dsmt_status.timestamp));
                        dsmtstatus. setAlert_level(SetEnums.note_alertlevel.valueOf(rset.getString(Tbl_dsmt_status.alert_level)));
                        dsmtstatus.setAlert_level_timestamp(rset.getString(Tbl_dsmt_status.alert_level_timestamp));
                        dsmtstatus.setLastchange(rset.getString(Tbl_dsmt_status.lastchange));
                        dsmtStatus.add(dsmtstatus);
                    }
                    return dsmtStatus;

                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDosimetersInfo): ",
                            DaoConnections.class, ex);
                }
            }
        });



        return dsmtStatus;

    }
    
    
     private void prepareQuery(Dsmt_status dsmtStatus, String newOrUpdate) {

        int i = 0;

        queryList.Add(Tbl_dsmt_status.pk_dsmt, i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmtStatus.getPk_dsmt(), i);

        queryList.Add(", " + Tbl_dsmt_status.pk_id, i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmtStatus.getPk_id(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_status.status, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtStatus.getStatus().toString(), i);  
        
        i += 1;
        queryList.Add(", " + Tbl_dsmt_status.status_timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtStatus.getStatus_timestamp(), i);
        
         i += 1;
        queryList.Add(", " + Tbl_dsmt_status.note, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtStatus.getNote(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_status.timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtStatus.getTimestamp(), i);
        
        i += 1;
        queryList.Add(", " + Tbl_dsmt_status.alert_level, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtStatus.getAlert_level().toString(), i);
       
        i += 1;
        queryList.Add(", " + Tbl_dsmt_status.alert_level_timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtStatus.getAlert_level_timestamp(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", "+ Tbl_dsmt_status.lastchange, i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmtStatus.getLastchange(), i);
        }

    }

   
     
       public String insertDsmtStatus(Dsmt_status dsmtStatus) {

        prepareQuery(dsmtStatus, "new");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_dsmtStatus + " (";
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

        
    public void updateDsmtStatus(Dsmt_status dsmtStatus, String dsmt_id) {

        prepareQuery(dsmtStatus, "update");

        int sizeNparam = queryList.getNumRows();
        String query = "UPDATE " + table_dsmtStatus + " SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE " + Tbl_dsmt_status.pk_dsmt + " = " + dsmt_id;

        daoConnection.update(query, param);

        queryList.remove();

    }
      
    
}
