/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.RandomNumbers;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class DosimeterDao {
 
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    private RandomNumbers random;

    
    
    public DosimeterDao () {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
        random = new RandomNumbers();
    }
    
    
    
    public List<Dosimeter> getDosimetersInfo(String dsmt_id, String worker_id) {
	
        final List<Dosimeter> dosimeters = new ArrayList<>();

        String sort = " ORDER BY status, pk_dsmt DESC ";
        String limit = " LIMIT 10";

        String query = null;

        if (worker_id.isEmpty() && dsmt_id.isEmpty()) {
            query = "SELECT * from dosimeter" + sort;
        } else if (!(worker_id.isEmpty()) && dsmt_id.isEmpty()) {
            query = "SELECT * FROM dosimeter WHERE pk_id = " + worker_id + sort;
        } else if (dsmt_id.isEmpty() && worker_id.isEmpty()) {
            query = "SELECT * FROM dosimeter WHERE pk_dsmt = " + dsmt_id + sort;
        } else {
            query = "SELECT * FROM dosimeter WHERE pk_dsmt = " + dsmt_id + " AND pk_id = " + worker_id + sort;
        }

        daoConnection.executePreparedQuery(query, new QueryMapper<Dosimeter>() {
            @Override
            public List<Dosimeter> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dosimeter dosimeter = new Dosimeter();
                        dosimeter.setPk_dsmt(rset.getString("pk_dsmt"));
                        dosimeter.setPk_id(rset.getString("pk_id"));
                        dosimeter.setId(rset.getString("id"));
                        dosimeter.setLabel(rset.getString("label"));
                        dosimeter.setType(SetEnums.dsmt_type.valueOf(rset.getString("type")));
                        dosimeter.setPeriodicity(SetEnums.dsmt_periodicity.valueOf(rset.getString("periodicity")));
                        dosimeter.setSupplier(SetEnums.dsmt_supplier.valueOf(rset.getString("supplier")));
                        dosimeter.setComments(rset.getString("comments"));
                        dosimeter.setTimestamp(rset.getString("timestamp"));
                        dosimeter.setStatus(SetEnums.status.valueOf(rset.getString("status")));
                        dosimeter.setStatus_timestamp(rset.getString("status_timestamp"));
                        dosimeter.setLastchange(rset.getString("lastchange"));
                        dosimeters.add(dosimeter);
                    }
                    return dosimeters;

                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDosimetersInfo): ",
                            DaoConnections.class, ex);
                }
            }
        });



        return dosimeters;

    }
    
     private void prepareQuery(Dosimeter dsmt, String newOrUpdate) {

        int i = 0;

        if (dsmt.getPk_id().isEmpty()) {
        }
        queryList.Add("pk_id", i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmt.getPk_id(), i);

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

       public String insertDosimeter(Dosimeter dsmt) {

        prepareQuery(dsmt, "new");

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

        
    public void updateDosimeter(Dosimeter dsmt, String dsmt_id) {

        prepareQuery(dsmt, "update");

        int sizeNparam = queryList.getNumRows();
        String query = "UPDATE dosimeter SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE pk_dsmt = " + dsmt_id;

        daoConnection.update(query, param);

        queryList.remove();

    }
      
    
}
