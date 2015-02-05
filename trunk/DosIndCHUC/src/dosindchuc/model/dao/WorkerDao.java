/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_workers;
import dosindchuc.model.dao.Help.ArrayList2D;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.entities.Worker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ir
 */
public class WorkerDao {
    
    
    
    private DaoConnections daoConnection;
    private ArrayList2D queryList;
    
	
    public WorkerDao () {
	daoConnection = new DaoConnections();
        queryList = new ArrayList2D();
    }
    
    
      public List<Worker> getSearchWorker(String name, String department, String category, String id_mec) {

        final List<Worker> workerInfo = new ArrayList<>();

        String query = "SELECT " + Tbl_workers.pk_id + ", " + Tbl_workers.id_mec + ", " + Tbl_workers.name + ", " + 
                Tbl_workers.category + ", " + Tbl_workers.department + ", " + Tbl_workers.status;

        String from = " FROM " + Conn_db.tbl_workers;

        String[][][] searchWhere = {{{Tbl_workers.name, "LIKE", name}},
            {{Tbl_workers.department, "null", department}},
            {{Tbl_workers.category, "null", category}},
            {{Tbl_workers.id_mec, "LIKE", id_mec}}};

        
        String where = daoConnection.buildQueryWhere(searchWhere);

        if (where.isEmpty()) {

            where = "";

        } else {

            where = " WHERE " + where;

        }
        
   
//       String sort = " ORDER BY p1.name , p1.department DESC, p2.pk_dsmt DESC ";
//        query = query + from + where + sort;
        query = query + from + where;
        
        
        System.out.println("getSearchWorker  query --->  " + query );

          daoConnection.executePreparedQuery(query, new QueryMapper<Worker>() {
              @Override
              public List<Worker> mapping(ResultSet rset) {

                  try {
                      while (rset.next()) {
                          Worker workerinfo = new Worker();

                          workerinfo.setPk_id(rset.getString(Tbl_workers.pk_id));
                          workerinfo.setName(rset.getString(Tbl_workers.name));
                          workerinfo.setId_mec(rset.getString(Tbl_workers.id_mec));
                          workerinfo.setCategory(SetEnums.worker_category.valueOf(rset.getString(Tbl_workers.category)));
                          workerinfo.setDepartment(SetEnums.worker_department.valueOf(rset.getString(Tbl_workers.department)));
                          workerinfo.setStatus(SetEnums.worker_status.valueOf(rset.getString(Tbl_workers.status)));

                          workerInfo.add(workerinfo);
                      }
                      return workerInfo;
                  } catch (SQLException ex) {
                      throw new DaoExceptions("Error on ResulSet of query (getSearchWorker): ",
                              DaoConnections.class, ex);
                  }
              }
              
          });


        return workerInfo;

    }

    
    
       public List<Worker> getWorkersInfo(String worker_id) {

        final List<Worker> workers = new ArrayList<>();

        String query = null;
        if (worker_id.isEmpty()) {
            query = "SELECT * FROM " + Conn_db.tbl_workers;
        } else {
            query = "SELECT * FROM " + Conn_db.tbl_workers + " WHERE " + Tbl_workers.pk_id + " = " + worker_id;
        }

           daoConnection.executePreparedQuery(query, new QueryMapper<Worker>() {
               @Override
               public List<Worker> mapping(ResultSet rset) {

                   try {
                       while (rset.next()) {
                           Worker worker = new Worker();
                           worker.setPk_id(rset.getString(Tbl_workers.pk_id));
                           worker.setId_mec(rset.getString(Tbl_workers.id_mec));
                           worker.setName(rset.getString(Tbl_workers.name));
                           worker.setNick(rset.getString(Tbl_workers.nick));
                           worker.setBI(rset.getString(Tbl_workers.BI));
                           worker.setNationality(rset.getString(Tbl_workers.nationality));
                           worker.setNif(rset.getString(Tbl_workers.nif));
                           worker.setBirth(rset.getString(Tbl_workers.birth));
                           worker.setSex(SetEnums.worker_sex.valueOf(rset.getString(Tbl_workers.sex)));
                           worker.setCategory(SetEnums.worker_category.valueOf(rset.getString(Tbl_workers.category)));
                           worker.setDepartment(SetEnums.worker_department.valueOf(rset.getString(Tbl_workers.department)));
                           worker.setSector(rset.getString(Tbl_workers.sector));
                           worker.setComments(rset.getString(Tbl_workers.comments));
                           worker.setTimestamp(rset.getString(Tbl_workers.timestamp));
                           worker.setStatus(SetEnums.worker_status.valueOf(rset.getString(Tbl_workers.status)));
                           worker.setStatus_timestamp(rset.getString(Tbl_workers.status_timestamp));
                           worker.setLastchange(rset.getString(Tbl_workers.lastchange));
                           workers.add(worker);
                       }
                       return workers;
                   } catch (SQLException ex) {
                       throw new DaoExceptions("Error on ResulSet of query (getWorkersInfo): ",
                               DaoConnections.class, ex);
                   }
               }
           });

        return workers;

    }

 
        
      public void prepareQuery(Worker worker, String newOrUpdate) {

        int i = 0;
        if (worker.getName().isEmpty()) {
        }
        queryList.Add(Tbl_workers.name, i);
        queryList.Add(" ? ", i);
        queryList.Add(worker.getName(), i);

        i += 1;
        if (worker.getId_mec().isEmpty()) {
        }
        queryList.Add(", " + Tbl_workers.id_mec, i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getId_mec(), i);


        if (!worker.getNick().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_workers.nick, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getNick(), i);
        }

        if (!worker.getBI().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_workers.BI, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getBI(), i);
        }

        if (!worker.getNationality().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_workers.nationality, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getNationality(), i);
        }

        if (!worker.getNif().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_workers.nif, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getNif(), i);
        }

        if (!worker.getBirth().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_workers.birth, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getBirth(), i);
        }

        i += 1;
        queryList.Add(", " + Tbl_workers.sex, i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getSex().toString(), i);

        i += 1;
        queryList.Add(", " + Tbl_workers.category, i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getCategory().toString(), i);


        i += 1;
        queryList.Add(", " + Tbl_workers.department, i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getDepartment().toString(), i);

        if (!worker.getSector().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_workers.sector, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getSector(), i);
        }

        if (!worker.getComments().isEmpty()) {
            i += 1;
            queryList.Add(", " + Tbl_workers.comments, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getComments(), i);
        }

        i += 1;
        queryList.Add(", " + Tbl_workers.timestamp, i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getTimestamp(), i);

        i += 1;
        queryList.Add(", " + Tbl_workers.status, i);
        queryList.Add(", ? ", i);
        queryList.Add(worker.getStatus().toString(), i);

        if (newOrUpdate.equalsIgnoreCase("new")) {
            i += 1;
            queryList.Add(", " + Tbl_workers.status_timestamp, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getStatus_timestamp(), i);

            i += 1;
            queryList.Add(", " + Tbl_workers.lastchange, i);
            queryList.Add(", ? ", i);
            queryList.Add(worker.getLastchange(), i);
        }

    }

      
      
      public String insertWorker(Worker worker) {

        prepareQuery(worker, "new");

        int sizeNparam = queryList.getNumRows();

        String query = "INSERT INTO " + Conn_db.tbl_workers + "(";
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

        
       public void updateWorker(Worker worker, String worker_id) {

        prepareQuery(worker, "update");

        int sizeNparam = queryList.getNumRows();
        String query = "UPDATE " + Conn_db.tbl_workers + " SET ";
        Object param[] = new Object[sizeNparam];

        for (int i = 0; i < sizeNparam; i++) {
            query += queryList.get(i, 0) + " = ? ";
            param[i] = queryList.get(i, 2);
            System.out.println(" Param --->  " + param[i]);
        }

        query += " WHERE pk_id = " + worker_id;

        
        System.out.println(" Query --->  " + query);
        
        
        daoConnection.update(query, param);

        queryList.remove();

    }
        
    
    
}
