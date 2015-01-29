/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_doses;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Dose;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class DoseDao {
    
    
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
    String table_dose = Conn_db.tbl_doses;
	
    
    public DoseDao () {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }
    
    
    
    public List<Dose> getDoseInfo(String dsmt_pkid, String worker_id) {

        final List<Dose> doses = new ArrayList<>();

        
        
        String select = "SELECT * ";
        String from = " FROM " + table_dose;
        String sort = " ORDER BY " + Tbl_doses.pk_dose + " DESC";
        String limit = " LIMIT 10";

        String query = select + from;

        if (worker_id.isEmpty() && dsmt_pkid.isEmpty()) {
            query = query + sort;
        } else if (!worker_id.isEmpty() && dsmt_pkid.isEmpty()) {
            query = query + " WHERE " + Tbl_doses.pk_id + " = " + worker_id + sort;
        } else if (!(dsmt_pkid.isEmpty()) && worker_id.isEmpty()) {
            query = query + " WHERE " + Tbl_doses.pk_dsmt + " = " + dsmt_pkid + sort;
        } else {
            query = query + " WHERE " + Tbl_doses.pk_dsmt + " = " + dsmt_pkid + " AND " + Tbl_doses.pk_id + " = " + worker_id + sort;
        }

        System.out.println( "  query getDoseInfo + DoseDao " +  query);
        
        daoConnection.executePreparedQuery(query, new QueryMapper<Dose>() {
            @Override
            public List<Dose> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        Dose dose = new Dose();
                        dose.setPk_dose(rset.getString(Tbl_doses.pk_dose));
                        dose.setPk_dsmt(rset.getString(Tbl_doses.pk_dsmt));
                        dose.setPk_id(rset.getString(Tbl_doses.pk_id));
                        dose.setYear(rset.getString(Tbl_doses.year));
                        dose.setTrimester(SetEnums.Trimester.valueOf(rset.getString(Tbl_doses.trimester)));
                        dose.setMonth(SetEnums.month.valueOf(rset.getString(Tbl_doses.month)));
                        dose.setHp007(rset.getString(Tbl_doses.hp007));
                        dose.setHp10(rset.getString(Tbl_doses.hp10));
                        dose.setComments(rset.getString(Tbl_doses.comments));
                        dose.setTimestamp(rset.getString(Tbl_doses.timestamp));
                        dose.setLastchange(rset.getString(Tbl_doses.lastchange));
                        doses.add(dose);
                    }
                    return doses;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDoseInfo): ",
                            DaoConnections.class, ex);
                }
            }
            
        });


        return doses;

    }

    private void prepareQuery(Dose dose, String newOrUpdate) {

        int i = 0;

        queryList.Add(Tbl_doses.year, i);
        queryList.Add(" ? ", i);
        queryList.Add(dose.getYear(), i);

        if (!dose.getPk_dsmt().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_doses.pk_dsmt, i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getPk_dsmt(), i);
        }


        if (!dose.getPk_id().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_doses.pk_id, i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getPk_id(), i);
        }

        i += 1;
        queryList.Add(", " + Tbl_doses.trimester, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose.getTrimester().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_doses.month, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose.getMonth().toString(), i);


        if (!dose.getHp007().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_doses.hp007, i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getHp007(), i);
        }

        if (!dose.getHp10().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_doses.hp10, i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getHp10(), i);
        }


        if (!dose.getComments().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_doses.comments, i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getComments(), i);
        }

        i += 1;
        queryList.Add(", " + Tbl_doses.timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(dose.getTimestamp(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", " + Tbl_doses.lastchange, i);
            queryList.Add(", ? ", i);
            queryList.Add(dose.getLastchange(), i);
        }

    }

       public String insertDose(Dose dose) {

        prepareQuery(dose, "new");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_dose + " (";
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

      
    public void updateDose(Dose dose, String dose_id) {

        prepareQuery(dose, "update");

        int sizeNparam = queryList.getNumRows();
        String query = "UPDATE " + table_dose + " SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
        }

        query += " WHERE " + Tbl_doses.pk_dose + " = " + dose_id;

        daoConnection.update(query, param);

        queryList.remove();

    }
  
 
    
}
