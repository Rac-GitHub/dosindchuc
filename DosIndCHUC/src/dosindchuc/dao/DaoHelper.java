/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

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
public class DaoHelper {

    
    private static final ThreadLocal<Connection> context = new ThreadLocal<>();

    
//    public DaoHelper() {
//    
//    }
    
    
    
    /**
     * Connection to the database
     * 
     * @return conn
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        
      /* DATOS PARA LA CONEXION */
        String bd = "dosim_indiv_CHUC";
        String login = "dichuc";
        String password = "dichuc";
        String url = "jdbc:mysql://localhost/"+bd;
        
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,login,password);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
      
    }
 
    
    
    public void beginTransaction () throws SQLException {
        if (isTransactionStarted()) {
            return; // << 13 -- provide support to transaction propagation
        }
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        context.set(conn);
    
    }
    
      private boolean isTransactionStarted() {
        return (context.get() != null);
    }
    
    
    public void endTransaction () throws SQLException {
        commit(getConnectionFromContext());
        releaseTransaction();
    }
    
    
    
    public void releaseTransaction () throws SQLException {
        Connection conn = context.get();
        release(conn);
        context.remove();
    }
    
    
    
     public void rollbackTransaction() {
        Connection conn;
        try {
            conn = getConnectionFromContext();
            rollback(conn);
            release(conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        context.remove();
    }
    
    
    public void commit (Connection conn) throws SQLException {
        conn.commit();
    }
    
    
    
    public void rollback(Connection conn) throws SQLException {
        if (conn != null) {
            conn.rollback();
        }
    }
    
    
    public Connection getConnectionFromContext() throws SQLException {
        
        Connection conn = context.get();
        
        if (conn == null) {
            throw new SQLException("Invalid transaction.");
        }
        if (conn.isClosed()) {
            throw new SQLException("Invalid transition, connection is closed.");
        }
        
        return conn;
        
    }
   
    
    
    
     public long executePreparedUpdateAndReturnGeneratedKeys(Connection conn,
            String query, Object... params)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        long result = 0l;
        try {
            pstmt = conn.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 0;
            for (Object param : params) {
                pstmt.setObject(++i, param);
            }

            pstmt.executeUpdate();

            rset = pstmt.getGeneratedKeys();

            if (rset.next()) {
                result = rset.getLong(1);
            }

        } finally {
            release(pstmt);
            release(rset);
        }
        return result;
    }

     
     
    public void executePreparedUpdate(Connection conn, String query,
            Object... params) throws SQLException {
        PreparedStatement pstmt = null;
        try {
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

    
    
    public void executePreparedUpdateIntoSingleTransaction(String query,
            Object... params) throws SQLException {
        
        executePreparedUpdate(getConnection(), query, params);
    }

    
    
    public void executePreparedUpdate(String query,
            Object... params) throws SQLException {

        executePreparedUpdate(getConnectionFromContext(), query, params);
    }

    
    
    public <T> List<T> executePreparedQuery(String query, QueryMapper<T> mapper) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        List<T> list = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
            list = mapper.mapping(rset);
        } finally {
            releaseAll(conn, stmt, rset);
        }

        return list;

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
            Logger.getLogger(DaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    
    /**
     * Close Connection
     * 
     * @param conn 
     */
     public void release(Connection conn) {
        
        if (conn == null ) {
             return;
         }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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
            Logger.getLogger(DaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     
     
     public void releaseAll(Connection conn, Statement stmt) {
         release(stmt);
         release(conn);
       
    }

     
     public void releaseAll(Connection conn, Statement stmt, ResultSet rset) {
         release(rset);
         releaseAll(conn, stmt);
       
     }
     
     
}
