/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_dose_notes;
import dosindchuc.globals.Tbl_doses;
import dosindchuc.globals.Tbl_dosimeters;
import dosindchuc.globals.Tbl_workers;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.DIVOldInfo;
import dosindchuc.model.entities.DIVinfo;
import dosindchuc.model.entities.DIVnotes;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class DIVDao {

    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    private DateAndTime dateAndTime = new DateAndTime();
    private DIVinfo divINFO;
    String table_workers = Conn_db.tbl_workers;
    String table_dmst = Conn_db.tbl_dsmt;
    String table_doses = Conn_db.tbl_doses;
    String table_doseNotes = Conn_db.tbl_doseNotes;

    public DIVDao() {

        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();

    }

    public List<DIVinfo> getDIVInfo(String name, String department, String category, String dsmt_id) {

        final List<DIVinfo> divInfo = new ArrayList<>();

        String query = "SELECT p1." + Tbl_workers.pk_id + ",  p2." + Tbl_dosimeters.pk_dsmt + ", p1." + Tbl_workers.name
                + ", p1." + Tbl_workers.id_mec + ", p1." + Tbl_workers.department + ", p1." + Tbl_workers.category
                + ", p2." + Tbl_dosimeters.id + ", p2." + Tbl_dosimeters.periodicity;

        String from = " FROM " + table_workers + " as p1, " + table_dmst + " as p2 ";

        String defaulWhere = " p1." + Tbl_workers.status + " = 'Activo' and p2." + Tbl_dosimeters.status + "= 'Activo' and p1."
                + Tbl_workers.pk_id + " = p2." + Tbl_dosimeters.pk_id;

        String[][][] searchWhere = {{{Tbl_workers.name, "LIKE", name}},
            {{Tbl_workers.department, "null", department}},
            {{Tbl_workers.category, "null", category}},
            {{Tbl_dosimeters.id, "LIKE", dsmt_id}}};


        String where = daoConnection.buildQueryWhere(searchWhere);

        if (where.isEmpty()) {

            where = " WHERE " + defaulWhere;

        } else {

            where = " WHERE " + where + "and " + defaulWhere;

        }

        String sort = " ORDER BY p1." + Tbl_workers.name + ", p1." + Tbl_workers.department + " DESC, p2."
                + Tbl_dosimeters.pk_dsmt + " DESC ";

        query = query + from + where + sort;
        
        
        System.out.println("getDIVInfo  query --->  " + query );

        daoConnection.executePreparedQuery(query, new QueryMapper<DIVinfo>() {
            @Override
            public List<DIVinfo> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        DIVinfo divinfo = new DIVinfo();

                        divinfo.setPk_id(rset.getString(Tbl_workers.pk_id));
                        divinfo.setPk_dsmt(rset.getString(Tbl_dosimeters.pk_dsmt));
                        divinfo.setName(rset.getString(Tbl_workers.name));
                        divinfo.setId_mec(rset.getString(Tbl_workers.id_mec));
                        divinfo.setCategory(SetEnums.worker_category.valueOf(rset.getString(Tbl_workers.category)));
                        divinfo.setDepartment(SetEnums.worker_department.valueOf(rset.getString(Tbl_workers.department)));
                        divinfo.setId_dsmt(rset.getString(Tbl_dosimeters.id));
                        divinfo.setPeriodicity(SetEnums.dsmt_periodicity.valueOf(rset.getString(Tbl_dosimeters.periodicity)));

                        divinfo.setHp007("0.00");
                        divinfo.setHp10("0.00");

                        divInfo.add(divinfo);
                    }
                    return divInfo;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDIVInfo): ",
                            DaoConnections.class, ex);
                }
            }
        });


        return divInfo;

    }

    public boolean getDIVAlreadyInserted(Object testeDIVAlready[]) {

        final List<Boolean> divAlready = new ArrayList<>();


        String query = "SELECT " + Tbl_doses.pk_dose
                + " FROM " + table_doses
                + " WHERE " + Tbl_doses.pk_dsmt + " = '" + testeDIVAlready[0] + "' and (trimester = '"
                + testeDIVAlready[1] + "' or month = '"
                + testeDIVAlready[1] + "') and year = '"
                + testeDIVAlready[2] + "'";


        System.out.println(" --- > query getDIVAlreadyInserted -- > " + query);

        daoConnection.executePreparedQuery(query, new QueryMapper<Boolean>() {
            @Override
            public List<Boolean> mapping(ResultSet rset) {

                try {
                    divAlready.add(rset.first());
                    return divAlready;

                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDIVAlreadyInserted): ",
                            DaoConnections.class, ex);
                }
            }
        });

        return divAlready.get(0);

    }

    public List<DIVOldInfo> getOldDIVInfo(String pk_id) {

        final List<DIVOldInfo> oldDIVInfo = new ArrayList<>();

        String query = "SELECT p1." + Tbl_doses.pk_dose + ", p2." + Tbl_dosimeters.periodicity
                + ", p2." + Tbl_dosimeters.id + ", p1." + Tbl_doses.trimester + ", p1." + Tbl_doses.month
                + ", p1." + Tbl_doses.year + ", p1." + Tbl_doses.hp007 + ", p1." + Tbl_doses.hp10 + ", p1." + Tbl_doses.timestamp
                + ", p1." + Tbl_doses.comments + ", p1." + Tbl_doses.lastchange;

        String from = " FROM " + table_doses + " as p1, " + table_dmst + " as p2 ";

        String where = " WHERE p1." + Tbl_doses.pk_id + "= " + pk_id + " and p1." + Tbl_doses.pk_dsmt + "= p2." + Tbl_dosimeters.pk_dsmt;

        String sort = " ORDER BY p1." + Tbl_doses.timestamp + " DESC ";

        String limit = " LIMIT 0, 10 ";

        query = query + from + where + sort + limit;

        daoConnection.executePreparedQuery(query, new QueryMapper<DIVOldInfo>() {
            @Override
            public List<DIVOldInfo> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        DIVOldInfo olddivinfo = new DIVOldInfo();
                        olddivinfo.setPk_dose(rset.getString(Tbl_doses.pk_dose));
                        olddivinfo.setPeriodicity(rset.getString(Tbl_dosimeters.periodicity));
                        olddivinfo.setId_dsmt(rset.getString(Tbl_dosimeters.id));
                        olddivinfo.setTrimester(rset.getString(Tbl_doses.trimester));
                        olddivinfo.setMonth(rset.getString(Tbl_doses.month));
                        olddivinfo.setYear(rset.getString(Tbl_doses.year));
                        olddivinfo.setHp007(rset.getString(Tbl_doses.hp007));
                        olddivinfo.setHp10(rset.getString(Tbl_doses.hp10));
                        olddivinfo.setTimestamp(rset.getString(Tbl_doses.timestamp));
                        olddivinfo.setComments(rset.getString(Tbl_doses.comments));
                        olddivinfo.setLastchange(rset.getString(Tbl_doses.lastchange));

                        oldDIVInfo.add(olddivinfo);
                    }
                    return oldDIVInfo;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getOldDIVInfo): ",
                            DaoConnections.class, ex);
                }
            }
        });

        return oldDIVInfo;

    }

    public List<DIVnotes> getDIVNotes(String pk_dose) {

        final List<DIVnotes> notesDIV = new ArrayList<>();

        String query = "SELECT " + Tbl_dose_notes.note + ", " + Tbl_dose_notes.status
                + ", " + Tbl_dose_notes.alert_level + ", " + Tbl_dose_notes.lastchange;

        String from = " FROM " + table_doseNotes;

        String where = " WHERE " + Tbl_dose_notes.pk_dose + " = " + pk_dose;

        String sort = " ORDER BY " + Tbl_dose_notes.lastchange + " DESC ";

        String limit = " LIMIT 0, 5 ";

        query = query + from + where + sort + limit;

        daoConnection.executePreparedQuery(query, new QueryMapper<DIVnotes>() {
            @Override
            public List<DIVnotes> mapping(ResultSet rset) {

                try {
                    while (rset.next()) {
                        DIVnotes notesdiv = new DIVnotes();
                        notesdiv.setNote(rset.getString(Tbl_dose_notes.note));
                        notesdiv.setStatus(rset.getString(Tbl_dose_notes.status));
                        notesdiv.setAlert_level(rset.getString(Tbl_dose_notes.alert_level));
                        notesdiv.setLastchange(rset.getString(Tbl_dose_notes.lastchange));

                        notesDIV.add(notesdiv);
                    }
                    return notesDIV;
                } catch (SQLException ex) {
                    throw new DaoExceptions("Error on ResulSet of query (getDIVNotes): ",
                            DaoConnections.class, ex);
                }
            }
        });

        return notesDIV;

    }

    public String saveDIV_doseInfo(List<DIVinfo> divInfo) {

        prepareQueryDoseInfo(divInfo);

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_doses + " (";
        String valuesInt = " VALUES (";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0);
            valuesInt += queryList.get(i, 1);
            param[i] = queryList.get(i, 2);
            System.out.println(" saveDIV_doseInfo ---param  > " + param[i]);
            
        }

        queryList.remove();

        query += ")";
        valuesInt += ")";
        query += valuesInt;
        
        System.out.println(" saveDIV_doseInfo ---query  > " + query);

        return daoConnection.insert(query, param);

    }

    private void prepareQueryDoseInfo(List<DIVinfo> divInfo) {

        String newDate = dateAndTime.currDateTime();

        divINFO = divInfo.get(0);

        int i = 0;

        queryList.Add(Tbl_doses.pk_dsmt, i);
        queryList.Add(" ? ", i);
        queryList.Add(divINFO.getPk_dsmt(), i);

        i += 1;
        queryList.Add(", " + Tbl_doses.pk_id, i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getPk_id(), i);

        i += 1;
        queryList.Add(", " + Tbl_doses.year, i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getYear(), i);

        i += 1;
        if (divINFO.getPeriodicity().toString().equalsIgnoreCase("Mensal")) {
            queryList.Add(", " + Tbl_doses.month, i);
        } else {
            if (divINFO.getPeriodicity().toString().equalsIgnoreCase("Trimestral")) {
                queryList.Add(", " + Tbl_doses.trimester, i);
            }
        }
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getPerd(), i);


        i += 1;
        queryList.Add(", " + Tbl_doses.hp007, i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getHp007(), i);

        i += 1;
        queryList.Add(", " + Tbl_doses.hp10, i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getHp10(), i);

        i += 1;
        queryList.Add(", " + Tbl_doses.comments, i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getComments(), i);

        i += 1;
        queryList.Add(", " + Tbl_doses.timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);

        i += 1;
        queryList.Add(", " + Tbl_doses.lastchange, i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);

    }

    public void saveDIV_doseNote(List<DIVinfo> divInfo, String pk_dose) {

        prepareQueryDoseNote(divInfo, pk_dose);

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + table_doseNotes + " (";
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

        daoConnection.insert(query, param);

    }

    private void prepareQueryDoseNote(List<DIVinfo> divInfo, String pk_dose) {

        String newDate = dateAndTime.currDateTime();

        divINFO = divInfo.get(0);

        int i = 0;

        queryList.Add(Tbl_dose_notes.pk_dose, i);
        queryList.Add(" ? ", i);
        queryList.Add(pk_dose, i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.note, i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getDose_note(), i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.status, i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getNoteStatus().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.status_timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);


        i += 1;
        queryList.Add(", " + Tbl_dose_notes.alert_level, i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getNoteAlertlevel().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.alert_level_timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);

        i += 1;
        queryList.Add(", " + Tbl_dose_notes.lastchange, i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);

    }
}
