/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Glbs_ActiveDsmt;
import dosindchuc.globals.Tbl_dosimeters;
import dosindchuc.globals.Tbl_dsmt_hist;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.RandomNumbers;
import dosindchuc.model.entities.ActiveDsmt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class ActiveDsmtDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    private RandomNumbers random;
    String table_dsmt = Conn_db.tbl_dsmt;
    String table_dsmtHist = Conn_db.tbl_dsmtHist;

    public ActiveDsmtDao() {
        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
        random = new RandomNumbers();
    }

    /* ############################################### */
    /*                                                  */
    /*              dosimeter  info                       */
    /*                                                  */
    /* ###############################################  */
    public List<ActiveDsmt> getActiveDsmtInfo(String worker_id) {

        final List<ActiveDsmt> activeDsmt = new ArrayList<>();

        String query = "SELECT * "
                + " FROM (SELECT  p1." + Tbl_dosimeters.pk_dsmt + ", p1." + Tbl_dosimeters.id + ", p1." + Tbl_dosimeters.type
                + ", p1." + Tbl_dosimeters.periodicity + ", p1." + Tbl_dosimeters.supplier + ", p2." + Tbl_dsmt_hist.value
                + ", p2." + Tbl_dsmt_hist.lastchange
                + " FROM  " + table_dsmt + " as p1"
                + " INNER JOIN " + table_dsmtHist + " as p2"
                + " ON p1." + Tbl_dosimeters.pk_id + " = " + worker_id + " AND p2." + Tbl_dsmt_hist.pk_dsmt + " = p1." + Tbl_dosimeters.pk_dsmt
                + " AND p2." + Tbl_dsmt_hist.id_change + " = 'status' AND p2." + Tbl_dsmt_hist.value + " != 'Inactivo'"
                + " ORDER BY p2." + Tbl_dsmt_hist.lastchange + " DESC) temp"
                + " GROUP by temp." + Tbl_dsmt_hist.pk_dsmt;
        

        System.out.println("query --getActiveDsmtInfo--- > " + query);

        daoConnection.executePreparedQuery(query, new QueryMapper<ActiveDsmt>() {
            @Override
            public List<ActiveDsmt> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        ActiveDsmt activedsmt = new ActiveDsmt();
                        activedsmt.setId(rset.getString(Glbs_ActiveDsmt.id));
 //                       activedsmt.setLabel(rset.getString(Glbs_ActiveDsmt.label));
                        activedsmt.setPeriodicity(rset.getString(Glbs_ActiveDsmt.periodicity));
                        activedsmt.setSupplier(rset.getString(Glbs_ActiveDsmt.supplier));
                        activedsmt.setType(rset.getString(Glbs_ActiveDsmt.type));
                        activedsmt.setStatus(rset.getString(Glbs_ActiveDsmt.value));
                        activedsmt.setLastchange(rset.getString(Glbs_ActiveDsmt.lastchange));
                        activeDsmt.add(activedsmt);
                    }
                    return activeDsmt;

                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getActiveDsmtInfo): ",
                            DaoConnections.class, ex);
                }
            }
        });


        return activeDsmt;

    }
}
