/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
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

    public DIVDao() {

        daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
        divINFO = new DIVinfo();


    }

    public List<DIVinfo> getDIVInfo(String name, String department, String category, String dsmt_id) {

        final List<DIVinfo> divInfo = new ArrayList<>();

        System.out.println("I am in DIV info ");

        try {

            String query = "SELECT p1.pk_id, p2.pk_dsmt, p1.name,"
                    + " p1.id_mec, p1.department, p1.category, p2.id, p2.periodicity ";
            String from = " FROM worker as p1, dosimeter as p2 ";

            String defaulWhere = " p1.status = 'Activo' and p2.status = 'Activo' and p1.pk_id = p2.pk_id";

            String[][][] searchWhere = {{{"name", "LIKE", name}},
                {{"department", "null", department}},
                {{"category", "null", category}},
                {{"id", "LIKE", dsmt_id}}};


            String where = daoConnection.buildQueryWhere(searchWhere);

            if (where.isEmpty()) {

                where = " WHERE " + defaulWhere;

            } else {

                where = " WHERE " + where + "and " + defaulWhere;

            }


            System.out.println(" DIV Where ---- > " + where);

            String sort = " ORDER BY p1.name , p1.department DESC, p2.pk_dsmt DESC ";

            query = query + from + where + sort;

            System.out.println(" DIV Where ---- >  " + query);

            daoConnection.executePreparedQuery(query, new QueryMapper<DIVinfo>() {
                @Override
                public List<DIVinfo> mapping(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        DIVinfo divinfo = new DIVinfo();

                        divinfo.setPk_id(rset.getString("pk_id"));
                        divinfo.setPk_dsmt(rset.getString("pk_dsmt"));
                        divinfo.setName(rset.getString("name"));
                        divinfo.setId_mec(rset.getString("id_mec"));
                        divinfo.setCategory(SetEnums.worker_category.valueOf(rset.getString("category")));
                        divinfo.setDepartment(SetEnums.worker_department.valueOf(rset.getString("department")));
                        divinfo.setId_dsmt(rset.getString("id"));
                        divinfo.setPeriodicity(SetEnums.dsmt_periodicity.valueOf(rset.getString("periodicity")));

                        divinfo.setHp007("0.00");
                        divinfo.setHp10("0.00");

                        divInfo.add(divinfo);
                    }
                    return divInfo;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            //ignore exception
        }

        return divInfo;

    }
   
    
    public boolean getDIVAlreadyInserted(Object testeDIVAlready[]) {
 
        final List<Boolean> divAlready = new ArrayList<>();
        
        try {

            String query = "SELECT pk_dose FROM dose_info WHERE"
                    + " pk_dsmt='" + testeDIVAlready[0] + "' and (trimester = '" 
                    + testeDIVAlready[1] + "' or month = '"
                    + testeDIVAlready[1] + "') and year = '"
                    + testeDIVAlready[2] + "'";
            
            
            daoConnection.executePreparedQuery(query, new QueryMapper<Boolean>() {
                @Override
                public List<Boolean> mapping(ResultSet rset) throws SQLException {
                    
                        System.out.println("result do rset ---> ... " + rset.first());
                        divAlready.add(rset.first());
                        
                        return divAlready;
     
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            //ignore exception
        }
        
        String query = "SELECT pk_dose FROM dose_info WHERE"
                    + " pk_dsmt='" + testeDIVAlready[0] + "' and (trimester = '" 
                    + testeDIVAlready[1] + "' or month = '"
                    + testeDIVAlready[1] + "') and year = '"
                    + testeDIVAlready[2] + "'";
        
        System.out.println(" divAlready ... -> " +  divAlready.get(0) + "  query --->  " + query);
        
        return divAlready.get(0);
            
    }

    
    
    public List<DIVOldInfo> getOldDIVInfo(String pk_id) {

        final List<DIVOldInfo> oldDIVInfo = new ArrayList<>();

        System.out.println("I am in DIV info ");

        try {

            String query = "SELECT p1.pk_dose, p2.periodicity, p2.id, p1.trimester,"
                    + " p1.month, p1.year, p1.hp007, p1.hp10, p1.timestamp, p1.comments, p1.lastchange";
            String from = " FROM dose_info as p1, dosimeter as p2 ";

            String where = " WHERE p1.pk_id = " + pk_id + " and p1.pk_dsmt = p2.pk_dsmt ";



            System.out.println(" DIV Where ---- > " + where);

            String sort = " ORDER BY p1.timestamp DESC ";

            String limit = " LIMIT 0, 10 ";

            query = query + from + where + sort + limit;

            System.out.println(" DIV Where ---- >  " + query);

            daoConnection.executePreparedQuery(query, new QueryMapper<DIVOldInfo>() {
                @Override
                public List<DIVOldInfo> mapping(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        DIVOldInfo olddivinfo = new DIVOldInfo();
                        olddivinfo.setPk_dose(rset.getString("pk_dose"));
                        olddivinfo.setPeriodicity(rset.getString("periodicity"));
                        olddivinfo.setId_dsmt(rset.getString("id"));
                        olddivinfo.setTrimester(rset.getString("trimester"));
                        olddivinfo.setMonth(rset.getString("month"));
                        olddivinfo.setYear(rset.getString("year"));
                        olddivinfo.setHp007(rset.getString("hp007"));
                        olddivinfo.setHp10(rset.getString("hp10"));
                        olddivinfo.setTimestamp(rset.getString("timestamp"));
                        olddivinfo.setComments(rset.getString("comments"));
                        olddivinfo.setLastchange(rset.getString("lastchange"));

                        oldDIVInfo.add(olddivinfo);
                    }
                    return oldDIVInfo;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            //ignore exception
        }

        return oldDIVInfo;

    }

    
    
    
    public List<DIVnotes> getDIVNotes(String pk_dose) {

        final List<DIVnotes> notesDIV = new ArrayList<>();

        System.out.println("I am in DIV info ");

        try {

            String query = "SELECT note, status, alert_level, lastchange";
            String from = " FROM dose_notes  ";

            String where = " WHERE pk_dose = " + pk_dose;

            System.out.println(" DIV Where ---- > " + where);

            String sort = " ORDER BY lastchange DESC ";

            String limit = " LIMIT 0, 5 ";

            query = query + from + where + sort + limit;

            System.out.println(" DIV Where ---- >  " + query);

            daoConnection.executePreparedQuery(query, new QueryMapper<DIVnotes>() {
                @Override
                public List<DIVnotes> mapping(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        DIVnotes notesdiv = new DIVnotes();
                        notesdiv.setNote(rset.getString("note"));
                        notesdiv.setStatus(rset.getString("status"));
                        notesdiv.setAlert_level(rset.getString("alert_level"));
                        notesdiv.setLastchange(rset.getString("lastchange"));


                        notesDIV.add(notesdiv);
                    }
                    return notesDIV;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            //ignore exception
        }

        return notesDIV;

    }

    
    
    public String saveDIV_doseInfo(List<DIVinfo> divInfo) {

        prepareQueryDoseInfo(divInfo);

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

    
    
    private void prepareQueryDoseInfo(List<DIVinfo> divInfo) {

        String newDate = dateAndTime.currDateTime();

        divINFO = divInfo.get(0);

        int i = 0;

        queryList.Add("pk_dsmt", i);
        queryList.Add(" ? ", i);
        queryList.Add(divINFO.getPk_dsmt(), i);

        System.out.println(" query DIV pk_dsmt ----> " + divINFO.getPk_dsmt());

        i += 1;
        queryList.Add(", pk_id", i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getPk_id(), i);

        i += 1;
        queryList.Add(", year", i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getYear(), i);

        i += 1;
        System.out.println("   querylist peri --- > " + divINFO.getPeriodicity());
        if (divINFO.getPeriodicity().toString().equalsIgnoreCase("Mensal")) {
            queryList.Add(", month", i);
        } else {
            if (divINFO.getPeriodicity().toString().equalsIgnoreCase("Trimestral")) {
                queryList.Add(", trimester", i);
            }
        }
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getPerd(), i);


        i += 1;
        queryList.Add(", hp007", i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getHp007(), i);

        i += 1;
        queryList.Add(", hp10", i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getHp10(), i);

        i += 1;
        queryList.Add(", comments", i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getComments(), i);

        i += 1;
        queryList.Add(", timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);

        i += 1;
        queryList.Add(", lastchange", i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);


    }

    public void saveDIV_doseNote(List<DIVinfo> divInfo, String pk_dose) {

        prepareQueryDoseNote(divInfo, pk_dose);

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO dose_notes (";
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

        queryList.Add("pk_dose", i);
        queryList.Add(" ? ", i);
        queryList.Add(pk_dose, i);

        System.out.println(" query DIV pk_dose ----> " + pk_dose);

        i += 1;
        queryList.Add(", note", i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getDose_note(), i);

        i += 1;
        queryList.Add(", timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);

        i += 1;
        queryList.Add(", status", i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getNoteStatus().toString(), i);

        i += 1;
        queryList.Add(", status_timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);


        i += 1;
        queryList.Add(", alert_level", i);
        queryList.Add(", ? ", i);
        queryList.Add(divINFO.getNoteAlertlevel().toString(), i);

        i += 1;
        queryList.Add(", alert_level_timestamp", i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);

        i += 1;
        queryList.Add(", lastchange", i);
        queryList.Add(", ? ", i);
        queryList.Add(newDate, i);



    }
}
