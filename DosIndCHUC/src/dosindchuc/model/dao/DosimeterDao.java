/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dosimeters;
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

    String table_dsmt = Conn_db.tbl_dsmt;
    
    
    public DosimeterDao () {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
        random = new RandomNumbers();
    }
    
    
    
    public List<Dosimeter> getDosimetersInfo(String dsmt_id, String worker_id) {
	
        final List<Dosimeter> dosimeters = new ArrayList<>();
    
        String select = "SELECT p1." + Tbl_dosimeters.pk_dsmt + ", p1." + Tbl_dosimeters.pk_id  + ", p1." + Tbl_dosimeters.id 
                + ", p1." + Tbl_dosimeters.label + ", p1." + Tbl_dosimeters.type + ", p1." + Tbl_dosimeters.periodicity 
                + ", p1." + Tbl_dosimeters.supplier + ", p1." + Tbl_dosimeters.comments + ", p1." + Tbl_dosimeters.timestamp 
                + ", p1." + Tbl_dosimeters.status + ", p1." + Tbl_dosimeters.lastchange; 
        String from = " FROM " + table_dsmt + " as p1";

        String query = select + from;
        
        String sort = " ORDER BY p1." + Tbl_dosimeters.status + ", p1." + Tbl_dosimeters.pk_dsmt + " DESC ";
        String limit = " LIMIT 10";
        
        if (worker_id.isEmpty() && dsmt_id.isEmpty()) {
            query = query + sort;
        } else if (!(worker_id.isEmpty()) && dsmt_id.isEmpty()) {
            query = query + " WHERE p1." + Tbl_dosimeters.pk_id + " = " + worker_id + sort;
        } else if (dsmt_id.isEmpty() && worker_id.isEmpty()) {
            query = query  + " WHERE p1." + Tbl_dosimeters.pk_dsmt + " = " + dsmt_id + sort;
        } else {
            query = query + " WHERE p1." + Tbl_dosimeters.pk_dsmt + " = " + dsmt_id 
                    + " AND p1." + Tbl_dosimeters.pk_id + " = " + worker_id + sort;
        }

        
        System.out.println("query ----- > " + query);
        
        daoConnection.executePreparedQuery(query, new QueryMapper<Dosimeter>() {
            @Override
            public List<Dosimeter> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dosimeter dosimeter = new Dosimeter();
                        dosimeter.setPk_dsmt(rset.getString(Tbl_dosimeters.pk_dsmt));
                        dosimeter.setPk_id(rset.getString(Tbl_dosimeters.pk_id));
                        dosimeter.setId(rset.getString(Tbl_dosimeters.id));
                        dosimeter.setLabel(rset.getString(Tbl_dosimeters.label));
                        dosimeter.setType(SetEnums.dsmt_type.valueOf(rset.getString(Tbl_dosimeters.type)));
                        dosimeter.setPeriodicity(SetEnums.dsmt_periodicity.valueOf(rset.getString(Tbl_dosimeters.periodicity)));
                        dosimeter.setSupplier(SetEnums.dsmt_supplier.valueOf(rset.getString(Tbl_dosimeters.supplier)));
                        dosimeter.setComments(rset.getString(Tbl_dosimeters.comments));
                        dosimeter.setTimestamp(rset.getString(Tbl_dosimeters.timestamp));
                        dosimeter.setStatus(SetEnums.dsmt_status.valueOf(rset.getString(Tbl_dosimeters.status)));
                        dosimeter.setLastchange(rset.getString(Tbl_dosimeters.lastchange));
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
        queryList.Add(Tbl_dosimeters.pk_id, i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmt.getPk_id(), i);

  
        if (!dsmt.getId().isEmpty()) {
            i += 1;
        queryList.Add(", " + Tbl_dosimeters.id, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getId(), i);
        }

        if (!dsmt.getLabel().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_dosimeters.label, i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt.getLabel(), i);
        }

        i += 1;
        queryList.Add(", " + Tbl_dosimeters.type, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getType().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_dosimeters.periodicity, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getPeriodicity().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_dosimeters.supplier, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getSupplier().toString(), i);

        if (!dsmt.getComments().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_dosimeters.comments, i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt.getComments(), i);
        }

        i += 1;
        queryList.Add(", " + Tbl_dosimeters.status, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getStatus().toString(), i);
        
        i += 1;
        queryList.Add(", " + Tbl_dosimeters.timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmt.getTimestamp(), i);

        
        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", " + Tbl_dosimeters.lastchange, i);
            queryList.Add(", ? ", i);
            queryList.Add(dsmt.getLastchange(), i);
        }

    }

     
     
     
     
       public String insertDosimeter(Dosimeter dsmt) {

        prepareQuery(dsmt, "new");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_dsmt + " (";
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
        String query = "UPDATE " + table_dsmt + " SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE " + Tbl_dosimeters.pk_dsmt + " = " + dsmt_id;

        daoConnection.update(query, param);

        queryList.remove();

    }
      
    
}
