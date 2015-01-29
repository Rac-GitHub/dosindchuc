/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_cumulDose;
import dosindchuc.globals.Tbl_doses;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Cumulative_dose;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class CumulDoseDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    String table_cumulDose = Conn_db.tbl_cumDose;
    String table_dose = Conn_db.tbl_doses;

    public CumulDoseDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }

    
    public List<Cumulative_dose> getCumulDoseInfo(String worker_id) {

        final List<Cumulative_dose> cumulDoses = new ArrayList<>();

        String select = "SELECT * ";
        String from = " FROM " + table_cumulDose;

        String where = " WHERE " + Tbl_cumulDose.pk_id + " = " + worker_id;

        String query = select + from + where;


        daoConnection.executePreparedQuery(query, new QueryMapper<Cumulative_dose>() {
            @Override
            public List<Cumulative_dose> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Cumulative_dose cumuldose = new Cumulative_dose();
                        cumuldose.setPk_cumulative_dose(rset.getString(Tbl_cumulDose.pk_cumulative_dose));
                        cumuldose.setPk_id(rset.getString(Tbl_cumulDose.pk_id));
                        cumuldose.setHp007_1year(rset.getString(Tbl_cumulDose.hp007_1year));
                        cumuldose.setHp10_1year(rset.getString(Tbl_cumulDose.hp10_1year));
                        cumuldose.setNdsmt_1year(rset.getString(Tbl_cumulDose.ndsmt_1year));
                        cumuldose.setHp007_5year(rset.getString(Tbl_cumulDose.hp007_5year));
                        cumuldose.setHp10_5year(rset.getString(Tbl_cumulDose.hp10_5year));
                        cumuldose.setNdsmt_5year(rset.getString(Tbl_cumulDose.ndsmt_5year));
                        cumuldose.setLastchange(rset.getString(Tbl_cumulDose.lastchange));
                        cumulDoses.add(cumuldose);
                    }
                    return cumulDoses;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDoseInfo): ",
                            DaoConnections.class, ex);
                }
            }
        });


        return cumulDoses;

    }

    private void prepareQuery(Cumulative_dose cumuldose, String newOrUpdate) {


        int i = 0;

        queryList.Add(Tbl_cumulDose.pk_id, i);
        queryList.Add(" ? ", i);
        queryList.Add(cumuldose.getPk_id(), i);

        i += 1;

        queryList.Add(", " + Tbl_cumulDose.hp007_1year, i);
        queryList.Add(", ? ", i);
        queryList.Add(cumuldose.getHp007_1year(), i);

        i += 1;
        queryList.Add(", " + Tbl_cumulDose.hp10_1year, i);
        queryList.Add(", ? ", i);
        queryList.Add(cumuldose.getHp10_1year(), i);

        i += 1;
        queryList.Add(", " + Tbl_cumulDose.ndsmt_1year, i);
        queryList.Add(", ? ", i);
        queryList.Add(cumuldose.getNdsmt_1year(), i);

        i += 1;
        queryList.Add(", " + Tbl_cumulDose.hp007_5year, i);
        queryList.Add(", ? ", i);
        queryList.Add(cumuldose.getHp007_1year(), i);

        i += 1;
        queryList.Add(", " + Tbl_cumulDose.hp10_5year, i);
        queryList.Add(", ? ", i);
        queryList.Add(cumuldose.getHp10_1year(), i);

        i += 1;
        queryList.Add(", " + Tbl_cumulDose.ndsmt_5year, i);
        queryList.Add(", ? ", i);
        queryList.Add(cumuldose.getNdsmt_1year(), i);


        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", " + Tbl_cumulDose.lastchange, i);
            queryList.Add(", ? ", i);
            queryList.Add(cumuldose.getLastchange(), i);
        }

    }

    
    
    public String insertCumulDose(Cumulative_dose cumuldose) {

        prepareQuery(cumuldose, "New");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_cumulDose + " (";
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
        
        
        System.out.println( " introduzir --- cumul dose -- : " + query + " param " + param );
        System.out.println( " introduzir --- param 0 : " + param[0] );
        System.out.println( " introduzir --- param 0 : " + param[1] );
        System.out.println( " introduzir --- param 0 : " + param[2] );
        System.out.println( " introduzir --- param 0 : " + param[3] );
        System.out.println( " introduzir --- param 0 : " + param[4] );
        System.out.println( " introduzir --- param 0 : " + param[5] );
        System.out.println( " introduzir --- param 0 : " + param[6] );
        System.out.println( " introduzir --- param 0 : " + param[7] );
        System.out.println( " introduzir --- param 0 : " + sizeNparam );
         

        return daoConnection.insert(query, param);

    }

    public void updateCumulDose(Cumulative_dose cumuldose, String worker_id) {

        prepareQuery(cumuldose, "Update");

        int sizeNparam = queryList.getNumRows();
        String query = "UPDATE " + table_cumulDose + " SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE " + Tbl_cumulDose.pk_id + " = " + worker_id;
        
        
        System.out.println( " UPDATE --- cumul dose -- : " );
        System.out.println( " introduzir --- cumul dose -- : " + query + " param " + param );
        System.out.println( " introduzir --- param 0 : " + param[0] );
        System.out.println( " introduzir --- param 0 : " + param[1] );
        System.out.println( " introduzir --- param 0 : " + param[2] );
        System.out.println( " introduzir --- param 0 : " + param[3] );
        System.out.println( " introduzir --- param 0 : " + param[4] );
        System.out.println( " introduzir --- param 0 : " + param[5] );
        System.out.println( " introduzir --- param 0 : " + param[6] );
        System.out.println( " introduzir --- param 0 : " + sizeNparam );
        System.out.println( " worker id : " +  worker_id);
        

        daoConnection.update(query, param);

        queryList.remove();

    }
    
     
    /*
     * 
     * Cal cumul dose directly from dose table
     * 
     */
    
    public String calcCumulDose(String doseq, String doseTime, String worker_id) {
    
     
       final List<Cumulative_dose> cumulDoses = new ArrayList<>();

        
        String select = "SELECT SUM(" + doseq + ") AS sumDose";
        String from = " FROM " + table_dose;
        String where = " WHERE " + Tbl_doses.pk_id + " = " + worker_id + " AND " + Tbl_doses.timestamp + " > now() - INTERVAL "
                + doseTime + " YEAR";

        String query = select + from + where;
               
        daoConnection.executePreparedQuery(query, new QueryMapper<Cumulative_dose>() {
            @Override
            public List<Cumulative_dose> mapping(ResultSet rset) {

                
                try {
                    while (rset.next()) {
                       Cumulative_dose cumuldose = new Cumulative_dose();
                       cumuldose.setCumulDose(rset.getString("sumDose"));
                       if ( rset.wasNull() ) {
                           cumuldose.setCumulDose("0");
                        }
                       cumulDoses.add(cumuldose);
                    }
                    return cumulDoses;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (calcCumulDose): ",
                            DaoConnections.class, ex);
                }
            }
        });

        
        return cumulDoses.get(0).getCumulDose().toString();
        
        
    }
    
    
    /*
     * 
     * Cal numero de dosimetros para cumul dose directly from dose table
     * 
     */
    
    
    public String calcNumDsmtCumulDose(String doseTime, String worker_id) {
    
     
       final List<Cumulative_dose> cumulDoses = new ArrayList<>();


        String select = "SELECT COUNT(DISTINCT " + Tbl_doses.pk_dsmt + ") AS numDsmtsumDose";
        String from = " FROM " + table_dose;
        String where = " WHERE " + Tbl_doses.pk_id + " = " + worker_id + " AND " + Tbl_doses.timestamp + " > now() - INTERVAL "
                + doseTime + " YEAR";

        String query = select + from + where;
               
        daoConnection.executePreparedQuery(query, new QueryMapper<Cumulative_dose>() {
            @Override
            public List<Cumulative_dose> mapping(ResultSet rset) {

                
                try {
                    while (rset.next()) {
                       Cumulative_dose cumuldose = new Cumulative_dose();
                       cumuldose.setNumDsmtCumulDose(rset.getString("numDsmtsumDose"));
                       if ( rset.wasNull() ) {
                           cumuldose.setNumDsmtCumulDose("0");
                        }
                       cumulDoses.add(cumuldose);
                    }
                    return cumulDoses;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (calcNumDsmtCumulDose): ",
                            DaoConnections.class, ex);
                }
            }
        });

        
        return cumulDoses.get(0).getNumDsmtCumulDose().toString();
        
        
    }
    
    
    
    
}

