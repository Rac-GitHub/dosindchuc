/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ir
 */
public class DaoHelper {

    
    /**
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
    
    
    public void realease(Statement stmt) {
        
        if (stmt == null ) {
            return;
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
     public void realease(Connection conn) {
        
        if (conn == null ) {
             return;
         }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
     public void realease(ResultSet rset) {
        
        if (rset == null ) {
             return;
         }
        try {
            rset.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     
     public void realeaseAll(Connection conn, Statement stmt) {
         realease(stmt);
         realease(conn);
       
    }

     public void realeaseAll(Connection conn, Statement stmt, ResultSet rset) {
         realease(rset);
         realeaseAll(conn, stmt);
       
     }
     
     
}
