/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.CreateDaoException;
import dosindchuc.model.dao.Help.DaoHelper;
import dosindchuc.model.dao.Help.DeleteDaoException;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.dao.Help.UpdateDaoException;
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
    
    
    
    private DaoHelper daoHelper;
    
	
    public WorkerDao () {
	daoHelper = new DaoHelper();
    }
    
    
    
    public List<Worker> getWorkersInfo(String worker_id) {
		
		final List<Worker> workers = new ArrayList<>();
		
		try {   
                        String query = null;
                        if (worker_id.isEmpty()) {
                            query = "SELECT * from worker";
                        } else {
                            query = "SELECT * FROM worker WHERE pk_id = " + worker_id;
                        }
  
			daoHelper.executePreparedQuery(query, new QueryMapper<Worker>() {

				@Override
				public List<Worker> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Worker worker = new Worker();
						worker.setPk_id( rset.getInt("pk_id") );
						worker.setId_mec( rset.getString("id_mec") );
						worker.setName( rset.getString("name") );
						worker.setNick( rset.getString("nick") );
						worker.setBI( rset.getString("BI") );
						worker.setNationality( rset.getString("nationality") );
						worker.setNif( rset.getString("nif") );
						worker.setBirth( rset.getString("birth") );
						worker.setSex( SetEnums.worker_sex.valueOf( rset.getString("sex") ) );
						worker.setCategory( SetEnums.worker_category.valueOf( rset.getString("category") ) );
                                                worker.setDepartment( SetEnums.worker_department.valueOf( rset.getString("department") ) );
                                                worker.setSector( rset.getString("sector") );
						worker.setComments( rset.getString("comments") );
						worker.setTimestamp( rset.getString("timestamp") );
                                                worker.setStatus( SetEnums.status.valueOf( rset.getString("status") ) );
                                                worker.setStatus_timestamp( rset.getString("status_timestamp") );
                                                worker.setLastchange( rset.getString("lastchange") );
                                                workers.add(worker);
					}
					return workers;
				}
				
			});
		} catch (SQLException e) {
                    e.printStackTrace();
			//ignore exception
		}
		
		return workers;
		
	}

    
         
    /**
     *
     * @param params
     * @return
     * @throws CreateDaoException
     */
    public int insert(String query, Object... param  ) throws CreateDaoException {

        int id = 0;
        
        
        System.out.println("Worker+++ ... 8 " + 7);
        System.out.println("Worker+++ ... 8 " + query);
        System.out.println("Worker+++ ... 8 " + param);
        
        try {
     
            daoHelper.beginTransaction();
            
    
            id = daoHelper.executePreparedUpdateAndReturnGeneratedKeys(daoHelper.getConnectionFromContext(), query, param);
       
            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new CreateDaoException("Not possible to make the transaction ", e);

        }

        return id;

    }
    
    
    
    
  
    
    public Worker update(Worker worker, int worker_id) throws UpdateDaoException {

        try {

            daoHelper.beginTransaction();
            
            final String query = "UPDATE worker SET id_mec = ? "
                    + ", name = ? , nick = ?, BI = ? , nationality = ? , nif = ?, birth = ? , sex = ? , category = ? "
                    + ", department = ? , sector = ? , comments = ? , timestamp = ? , status = ? "
                    + ", status_timestamp = ?  WHERE pk_id = " + worker_id;

            daoHelper.executePreparedUpdate(daoHelper.getConnectionFromContext(), query
                    , worker.getId_mec()
                    , worker.getName()
                    , worker.getNick()
                    , worker.getBI()
                    , worker.getNationality()
                    , worker.getNif()
                    , worker.getBirth()
                    , worker.getSex().toString()
                    , worker.getCategory().toString()
                    , worker.getDepartment().toString()
                    , worker.getSector()
                    , worker.getComments()
                    , worker.getTimestamp()
                    , worker.getStatus().toString()
                    , worker.getStatus_timestamp());

            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new UpdateDaoException("Not possible to make the transaction ", e);

        }

        return worker;
 

    }
    

    public void delete(int worker_id) throws DeleteDaoException {

        try {

            daoHelper.beginTransaction();
            
            final String query = "DELETE FROM worker WHERE pk_id = " + worker_id;

            daoHelper.executePreparedUpdate(daoHelper.getConnectionFromContext(), query);
              

            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new DeleteDaoException("Not possible to make the transaction: ", e);

        }

       
    }
    
    
      
    public int  prepareToInsertWorker (Worker worker) {
       
        
        List<Object []> params = new ArrayList<>();
        Object values [] = new Object[3];
          
        List teste = new ArrayList<>();
        List teste1 = new ArrayList<>();
        
  //      Object mmm [] = new Object[2];
        
        teste1.add("claro");
        teste1.add("pois");
        teste1.add("ultimo");
        teste.add(teste1);
        
        teste1.clear();
        teste1.add("claro1");
        teste1.add("pois1");
        teste1.add("ultimo1");
        teste.add(teste1);
        
        
        
        System.out.println("ttttt + " + teste.get(1));
//        System.out.println("ttttt + " + teste.get(1)[1]);
//        System.out.println("ttttt + " + teste.get(2)[0]);
        
        
        
        params.clear();
        int i=0;
        if (worker.getName().isEmpty()) {
        }
        values [0] = "name";
        values [1] = " ? ";
        values [2] = worker.getName();
        
        params.add (i,values);
        
        System.out.println("Worker ... 1 " + values[0]);
        System.out.println("Worker ... 1 " + values[1]);
        System.out.println("Worker ... 1 " + values[2]);
        System.out.println("Worker ... 1 " + values.length);
        
   //     va = 
        
   //     params.add(i, {values[0], values[1], values [3]});
        
    //    params.get(i)[0];
 /*       
         
        values.set(1," ? ");
        values.set(2,worker.getName()); */

   //     params.get(0).toString()
        System.out.println("Worker ... 1 " + i);
        System.out.println("Worker ... 1 " + params.get(i)[0]);
        System.out.println("Worker ... 1 " + params.get(i)[1]);
        System.out.println("Worker ... 1 " + params.get(i)[2]);
        
  //      values[0] = params.get(i);
        
  
        i+=1;
        if (worker.getId_mec().isEmpty()) {
        }
        values [0] = ", id_mec";
        values [1] = ", ? "; 
        values [2] = worker.getId_mec();
        params.add(i,values);
        
        System.out.println("Worker ... 1 " + i);
        System.out.println("Worker ... 1 " + params.get(i)[0]);
        System.out.println("Worker ... 1 " + params.get(i)[1]);
        System.out.println("Worker ... 1 " + params.get(i)[2]);
 
        System.out.println("Worker ... NNNN " + params.get(1)[0]);
        System.out.println("Worker ... NNNN " + params.size());
        
     //   System.out.println("size ... "+ params.size());
          
//        if (! worker.getNick().isEmpty()) {
//            i+=1;
//            values.add(0, ", nick");
//            values.add(1, ", ? ");
//            values.add(2, worker.getNick());
//            params.add(values);
//        }
//        System.out.println("Worker ... 1 " + i);
//        System.out.println("Worker ... 1 " + params.get(i).get(0));
//        System.out.println("Worker ... 1 " + params.get(i).get(1));
//        System.out.println("Worker ... 1 " + params.get(i).get(2));
//        values.removeAll(values);
//        
//        if (! worker.getBI().isEmpty()) {
//            i+=1;
//            values.add(0, ", BI");
//            values.add(1, ", ? ");
//            values.add(2, worker.getBI());
//            params.add(values);
//        }
//        
//        if (! worker.getNationality().isEmpty()) {
//            i+=1;
//            values.add(0, ", nationality");
//            values.add(1, ", ? ");
//            values.add(2, worker.getNationality());
//            params.add(values);
//        }
//        
//        if (! worker.getNif().isEmpty()) {
//            i+=1;
//            values.add(0, ", nif");
//            values.add(1, ", ? ");
//            values.add(2, worker.getNif());
//            params.add(values);
//        }
//        
//        if (! worker.getBirth().isEmpty()) {
//            i+=1;
//            values.add(0, ", birth");
//            values.add(1, ", ? ");
//            values.add(2, worker.getBirth());
//            params.add(i, values);
//        }
//        
//        i+=1;
//        values.add(0, ", sex");
//        values.add(1, ", ? ");
//        values.add(2, worker.getSex().toString());
//        params.add(values);
//        System.out.println("Worker ... 1 " + i);
//        System.out.println("Worker ... 1 " + params.get(i).get(0));
//        System.out.println("Worker ... 1 " + params.get(i).get(1));
//        System.out.println("Worker ... 1 " + params.get(i).get(2));
//        values.removeAll(values);
//        i+=1;
//        values.add(0, ", category");
//        values.add(1, ", ? ");
//        values.add(2, worker.getCategory().toString());
//        params.add(values);
//        System.out.println("Worker ... 1 " + i);
//        System.out.println("Worker ... 1 " + params.get(i).get(0));
//        System.out.println("Worker ... 1 " + params.get(i).get(1));
//        System.out.println("Worker ... 1 " + params.get(i).get(2));
//        System.out.println("Worker ... AGORA " + params.get(0).get(0));
// values.removeAll(values);
//        i+=1;
//        values.add(0, ", department");
//        values.add(1, ", ? ");
//        values.add(2, worker.getDepartment().toString());
//        params.add(i, values);
//        System.out.println("Worker ... 1 " + i);
//        System.out.println("Worker ... 1 " + params.get(i).get(0));
//        System.out.println("Worker ... 1 " + params.get(i).get(1));
//        System.out.println("Worker ... 1 " + params.get(i).get(2));
//        
//        if (! worker.getSector().isEmpty()) {
//            i+=1;
//            values.add(0, ", sector");
//            values.add(1, ", ? ");
//            values.add(2, worker.getSector());
//            params.add(i, values);
//        }
//        
//        if (! worker.getComments().isEmpty()) {
//            i+=1;
//            values.add(0, ", comments");
//            values.add(1, ", ? ");
//            values.add(2, worker.getComments());
//            params.add(i, values);
//        }
// 
//        i+=1;
//        values.add(0, ", timestamp");
//        values.add(1, ", ? ");
//        values.add(2, worker.getTimestamp());
//        params.add(i, values);
//        System.out.println("Worker ... 1 " + i);
//        System.out.println("Worker ... 1 " + params.get(i).get(0));
//        System.out.println("Worker ... 1 " + params.get(i).get(1));
//        System.out.println("Worker ... 1 " + params.get(i).get(2));
//        
//        i+=1;
//        values.add(0, ", status");
//        values.add(1, ", ? ");
//        values.add(2, worker.getStatus().toString());
//        params.add(i, values);
//        
//        System.out.println("Worker ... 1 " + i);
//        System.out.println("Worker ... 1 " + params.get(i).get(0));
//        System.out.println("Worker ... 1 " + params.get(i).get(1));
//        System.out.println("Worker ... 1 " + params.get(i).get(2));
//        i+=1;
//        values.add(0, ", status_timestamp");
//        values.add(1, ", ? ");
//        values.add(2, worker.getStatus_timestamp());
//        params.add(i, values);
//    
//        System.out.println("Worker ... 1 " + i);
//        System.out.println("Worker ... 1 " + params.get(i).get(0));
//        System.out.println("Worker ... 1 " + params.get(i).get(1));
//        System.out.println("Worker ... 1 " + params.get(i).get(2));
//  //      System.out.println("Worker ... size " + params.size());
//        System.out.println("Worker ... 9 rep " + params.get(0).get(2));
//        
////  prepare query
//        int sizeparam = params.size();
//   //     String query = "INSERT INTO worker (";
//   //     String valuesInt = " VALUES (";
//    //    Object param [] = new Object[params.size()];
//                       
//            for ( i=0; i < sizeparam; i++ ) {
//       //         params.get(i).get(0);
//       //         query += params.get(i).get(0);
//       //         System.out.println("Worker ... 9 " + query);
//                System.out.println("Worker ... ciclo " + params.get(i).get(0)); 
//         //       System.out.println("Worker ... 9 " + params.size());
//         //       valuesInt += params.get(i).get(1);
//         //       param[i] = params.get(i).get(2);
//            } 
//            
//     //       query += ")";
//     //       valuesInt += ")";
//            
//     //       query += valuesInt;
//        
////   insert worker        
//     //  return insert(query, param);    
        return 4;
   
        
    }
    
    
    
    public Worker oldInsert(Worker worker) throws CreateDaoException {

        try {

            
            
            daoHelper.beginTransaction();
            
           final String query = "INSERT INTO worker "
                    + "(id_mec, name, nick, BI, nationality, nif, birth, sex, category, department, sector"
                    + ", comments, timestamp, status, status_timestamp) VALUES "
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )"; 
            
     
            

           int id = daoHelper.executePreparedUpdateAndReturnGeneratedKeys(daoHelper.getConnectionFromContext(), query
                    , worker.getId_mec()
                    , worker.getName()
                    , worker.getNick()
                    , worker.getBI()
                    , worker.getNationality()
                    , worker.getNif()
                    , worker.getBirth()
                    , worker.getSex().toString()
                    , worker.getCategory().toString()
                    , worker.getDepartment().toString()
                    , worker.getSector()
                    , worker.getComments()
                    , worker.getTimestamp()
                    , worker.getStatus().toString()
                    , worker.getStatus_timestamp()); 
            
    
            worker.setPk_id(id);
            daoHelper.endTransaction();

        } catch (SQLException e) {

            daoHelper.rollbackTransaction();
            throw new CreateDaoException("Not possible to make the transaction ", e);

        }

        return worker;

    }
    
    
    
    
}
