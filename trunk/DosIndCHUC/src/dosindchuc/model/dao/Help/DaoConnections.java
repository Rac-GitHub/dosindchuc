/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao.Help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ir
 */
public class DaoConnections {

    
    // only one connection to database at a time. The connection is used during the wait_timout (120 s)
    // New one is then created.
    private static final ThreadLocal<Connection> context = new ThreadLocal<>();

    
    
    /**
     * Connection to the database --- 
     * 
     * @return conn
     * @throws SQLException 
     */
    private Connection getConnection() throws SQLException {
        
        
        
      /* DATOS PARA LA CONEXION */
        String driver = "com.mysql.jdbc.Driver";
        String host = "localhost";
        String port = "3306";
        String bd = "dosim_indiv_CHUC";
        String login = "dichuc";
        String password = "dichuc";
      
       
        String url = "jdbc:mysql://" + host + ":" + port + "/" + bd;
        
        Connection conn = null;
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection (url,login,password);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
      
    }
 
    
    
    private void verifyConnectionThread () throws SQLException {
      
        if (isValidConnection()) {
            return; // << 13 -- provide support to transaction propagation
        }
    
        Connection conn = getConnection();
        context.set(conn);
    
    }
    
   
    
    private boolean isValidConnection() {
        
        Connection conn = context.get();
                    
        if (conn != null) {
            try {
                if (( ! conn.isClosed()) && ( conn.isValid(1))) {
                    System.out.println("conn1 " + conn.isValid(1));
                    return true;
                }
            } catch (SQLException ex) {
               Logger.getLogger(DaoConnections.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
    
         return false;
         
    }
    
   
    
    private Connection getConnectionFromThread() throws SQLException {
        
        
        verifyConnectionThread ();

        Connection conn = context.get();
  
        if (conn == null) {
            throw new SQLException("Invalid transaction.");
        }
        if (conn.isClosed()) {
            throw new SQLException("Invalid transition, connection is closed.");
        }
        
        return conn;
        
    }
   
    
     
     /**
      * Close ResultSet
      * 
      * @param rset 
      */
     public void release(ResultSet rset) {
        
        if (rset == null ) {
             return;
         }
        try {
            rset.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     
     
    /**
     * Close Statement
     * 
     * @param stmt 
     */
    public void release(Statement stmt) {
        
        if (stmt == null ) {
            return;
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
   
     
     private void releaseAll(ResultSet rset, Statement stmt) {
         release(rset);
         release(stmt);
       
    }
     

 
     
     
 /**
   *
   *  Create methods  for actions on the database
   * 
   * */
     

    
     public String executePreparedUpdateAndReturnGeneratedKeys(String query, Object... params) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        int result = 0;
        
        try {
            
            conn = getConnectionFromThread();
            
            pstmt = conn.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 0;
            for (Object param : params) {
                pstmt.setObject(++i, param);
            }

            pstmt.executeUpdate();

            rset = pstmt.getGeneratedKeys();

            if (rset.next()) {
                result = rset.getInt(1);
            }

        } finally {
            releaseAll(rset,pstmt);
        }
        
        return Integer.toString(result);
    }

     
     
    public void executePreparedUpdate(String query, Object... params) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = getConnectionFromThread();
            
            pstmt = conn.prepareStatement(query);
            int i = 0;
            for (Object param : params) {
                pstmt.setObject(++i, param);
            }
            pstmt.executeUpdate();
        } finally {
            release(pstmt);
        }
    }

    
     
    
    public <T> List<T> executePreparedQuery(String query, QueryMapper<T> mapper) throws SQLException {

        Connection conn = null;
        Statement pstmt = null;
        ResultSet rset = null;

        List<T> list = new ArrayList<>();

        try {
            conn = getConnectionFromThread();
            
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery(query);
            list = mapper.mapping(rset);
     
        } finally {
            releaseAll(rset,pstmt);
        }

        return list;

    }
    
 
   /**
    * 
    * @param table
    * @param fields
    * @param where
    * @return
    */
    public Object[][] executeSelectivePreparedQuery(String table, String fields, String [][][] searchWhere) {

        String colname[] = fields.split(",");
        String where = buildQueryWhere(searchWhere);
        
        Object[][] data = null;

        //Consultas SQL
        String q = "SELECT " + fields + " FROM " + table;
        String q2 = "SELECT count(*) as total FROM " + table;

        if (where != null) {
            q += " WHERE " + where;
            q2 += " WHERE " + where;
        }

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Connection conn = null;
        
        try {
                    
            conn = getConnectionFromThread();
            
            System.out.print("Connection: " + conn);

            pstmt = conn.prepareStatement(q2);
            rset = pstmt.executeQuery();
            rset.next();
            int regts = rset.getInt("total");

            releaseAll(rset,pstmt);
            
            data = new String[regts][fields.split(",").length];
 
            pstmt = conn.prepareStatement(q);
            rset = pstmt.executeQuery();

            int i = 0;
            while (rset.next()) {
                for (int j = 0; j <= fields.split(",").length - 1; j++) {
                    data[i][j] = rset.getString(colname[j].trim());
                }
                i++;
            }
           
        } catch (SQLException e) {
            throw new CreateDaoException("Not possible to make the transaction ", e);
        } finally {
            releaseAll(rset,pstmt);
        }

        return data;
        
    }


    public String buildQueryWhere(String[][][] searchWhere) {

 
        String  where = "";
         
        int iand = 0;
        String newWhere = null;
        for (int i = 0; i <= searchWhere. length - 1; i++) {
              if ( ( ! searchWhere[i][0][2].isEmpty() ) && ( ! searchWhere[i][0][2].equalsIgnoreCase("NoDef") ) ) {
                  
                  switch (searchWhere[i][0][1]) {
                      case "LIKE": 
                          newWhere = " " + searchWhere[i][0][0] + " LIKE " + "'%" + searchWhere[i][0][2] + "%'";
                          break;
                      default: newWhere = " " + searchWhere[i][0][0] + " = " + "'" + searchWhere[i][0][2] + "'";
                          
                  }
                   
                  if (  iand > 0 ) {
                    where = where + " AND" + newWhere;
                  } else {
                    where = where + newWhere;
                  }
                  iand++;
              }
        }
         
        
        if (where.isEmpty()) {
            where = null;
        }
        
        return where;
        
    }

    
    

    // InTo the DATABASE

    
     /**
     *
     * @param params
     * @return
     * @throws CreateDaoException
     */
    public String insert(String query, Object [] param  ) throws CreateDaoException {

        String id = null;
 
        System.out.println("Query: " + query);
        
        try {
  
            id = executePreparedUpdateAndReturnGeneratedKeys(query, param);
  
        } catch (SQLException e) {
          throw new CreateDaoException("Not possible to make the transaction ", e);
        }

        return id;

    }
    
    
    
     public void update(String query, Object [] param) throws UpdateDaoException {

        try {
            System.out.println("Query: " + query);

            
            
            System.out.println("query ... " +
                    param[0] + " "
                    + param[1] + " "
                    + param[2] + " "
                    + param[3] + " "
                    + param[4] + " "
                    + param[5] + " "
                    + param[6] + " "
                    );
 
            executePreparedUpdate(query, param);           
            
            
        } catch (SQLException e) {
            throw new UpdateDaoException("Not possible to make the transaction ", e);
        }

    }
    
    
    
    public void delete(String worker_id) throws DeleteDaoException {

        try {

            final String query = "DELETE FROM worker WHERE pk_id = " + worker_id;

            executePreparedUpdate(query);

        } catch (SQLException e) {
            throw new DeleteDaoException("Not possible to make the transaction: ", e);
        }

       
    }
    
    
    
     
}
