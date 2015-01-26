/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dsmt_hist;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Dsmt_hist;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class DsmtHistDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
    private String table_dsmtHist = Conn_db.tbl_dsmtHist;
    

    public DsmtHistDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }


    
      public List<Dsmt_hist> getDsmtHist(String dsmt_id) {
	
        final List<Dsmt_hist> dsmtHist = new ArrayList<>();
    
        String select = "SELECT p1." + Tbl_dsmt_hist.pk_dsmt_hist + ", p1." + Tbl_dsmt_hist.pk_dsmt  + ", p1." +
                Tbl_dsmt_hist.id_change + ", p1." + Tbl_dsmt_hist.value + ", p1." + Tbl_dsmt_hist.lastchange; 
        String from = " FROM " + table_dsmtHist + " as p1";

        String query = select + from;
        
        String sort = " ORDER BY p1." + Tbl_dsmt_hist.lastchange  + " DESC ";
        String limit = " LIMIT 15";
    
        query = query + " WHERE p1." + Tbl_dsmt_hist.pk_dsmt + " = " + dsmt_id + sort + limit;
        
        
        System.out.println("query ----- > " + query);
        
        daoConnection.executePreparedQuery(query, new QueryMapper<Dsmt_hist>() {
            @Override
            public List<Dsmt_hist> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dsmt_hist dsmthist = new Dsmt_hist();
                        dsmthist.setPk_dsmt_hist(rset.getString(Tbl_dsmt_hist.pk_dsmt_hist));
                        dsmthist.setPk_dsmt(rset.getString(Tbl_dsmt_hist.pk_dsmt));
                        dsmthist.setId_change(rset.getString(Tbl_dsmt_hist.id_change));
                        dsmthist.setValue(rset.getString(Tbl_dsmt_hist.value));
                        dsmthist.setLastchange(rset.getString(Tbl_dsmt_hist.lastchange));
                        dsmtHist.add(dsmthist);
                    }
                    
                    return dsmtHist;

                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDsmtHist): ",
                            DaoConnections.class, ex);
                }
            }
        });

        return dsmtHist;

    }
    
    
    
    private void prepareQuery(Dsmt_hist dsmtHist) {

        int i = 0;

        queryList.Add(Tbl_dsmt_hist.pk_dsmt, i);
        queryList.Add(" ? ", i);
        queryList.Add(dsmtHist.getPk_dsmt(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_hist.id_change, i);
        queryList.Add(", ? ", i);
        queryList.Add(dsmtHist.getId_change(), i);

        i += 1;
        queryList.Add(", " + Tbl_dsmt_hist.value, i);
        queryList.Add(", ? ", i);
        System.out.println(" --- preparre .. : " + dsmtHist.getValue());
        queryList.Add(dsmtHist.getValue(), i);

    }

    public String insertDsmtHist(Dsmt_hist dsmtHist) {

        prepareQuery(dsmtHist);

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_dsmtHist + " (";
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

        
        System.out.println(" query : .... : " + query );
        System.out.println(" param : .... : " + query );
        
        
        return daoConnection.insert(query, param);

    }

   
}
