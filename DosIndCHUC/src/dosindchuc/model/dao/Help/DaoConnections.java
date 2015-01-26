/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao.Help;

import dosindchuc.globals.Conn_db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    private Connection getConnection() {

 

        String url = "jdbc:mysql://" + Conn_db.host + ":" + Conn_db.port + "/" + Conn_db.bd;

        Connection conn = null;

        try {
            Class.forName(Conn_db.driver);
        } catch (ClassNotFoundException ex) {
            throw new DaoExceptions("Error: No Driver Class Found (ClassNotFoundException) ", DaoConnections.class);
        }

        try {
            conn = DriverManager.getConnection(url, Conn_db.login, Conn_db.password);

        } catch (SQLException ex) {
            throw new DaoExceptions("Error connecting to the Database ", DaoConnections.class);
        }

        return conn;

    }

    private void verifyConnectionThread() {

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
                if ((!conn.isClosed()) && (conn.isValid(1))) {
                    return true;
                }
            } catch (SQLException ex) {
                throw new DaoExceptions("Error: connection CLOSED or not Valid  ", DaoConnections.class);

            }

        }

        return false;

    }

    private Connection getConnectionFromThread() throws SQLException {

        verifyConnectionThread();

        Connection conn = context.get();

        if (conn == null) {
            throw new DaoExceptions("Error: NULL connection ", DaoConnections.class);
        }
        if (conn.isClosed()) {
            throw new DaoExceptions("Error: connection CLOSED ", DaoConnections.class);
        }

        return conn;

    }

    /**
     * Close ResultSet
     *
     * @param rset
     */
    public void release(ResultSet rset) {

        if (rset == null) {
            return;
        }
        try {
            rset.close();
        } catch (SQLException ex) {
            throw new DaoExceptions("Error on ResultSet close ", DaoConnections.class);
        }

    }

    /**
     * Close Statement
     *
     * @param stmt
     */
    public void release(Statement stmt) {

        if (stmt == null) {
            return;
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            throw new DaoExceptions("Error on statement close ", DaoConnections.class);
        }

    }

    private void releaseAll(ResultSet rset, Statement stmt) {
        release(rset);
        release(stmt);

    }

    /**
     *
     * Create methods for actions on the database
     *
     *
     */
    public String executePreparedUpdateAndReturnGeneratedKeys(String query, Object... params) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        int result = 0;

        try {

            Connection conn = getConnectionFromThread();
            
            System.out.println(" connnn --- : " + conn);

            pstmt = conn.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            
            System.out.println(" query --- : " + query);
            System.out.println(" pstmt --- : " + pstmt);
            
            int i = 0;
            for (Object param : params) {
                            System.out.println(" parammmdmad --- : " + param);

                pstmt.setObject(++i, param);
            }

            pstmt.executeUpdate();

            rset = pstmt.getGeneratedKeys();

            if (rset.next()) {
                result = rset.getInt(1);
            }
        } catch (SQLException ex) {
            throw new DaoExceptions("Invalid Insert transition: ", DaoConnections.class, ex);
        } finally {
            releaseAll(rset, pstmt);
        }

        return Integer.toString(result);


    }

    public void executePreparedUpdate(String query, Object... params) {

        PreparedStatement pstmt = null;

        try {
            Connection conn = getConnectionFromThread();

            pstmt = conn.prepareStatement(query);
            int i = 0;
            for (Object param : params) {
                pstmt.setObject(++i, param);
            }
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoExceptions("Invalid Update transition: ", DaoConnections.class, ex);
        } finally {
            release(pstmt);
        }
    }
    
       public <T> List<T> executePreparedQuery(String query, QueryMapper<T> mapper) {

        Statement pstmt = null;
        ResultSet rset = null;

        List<T> list = new ArrayList<>();

        try {
            Connection conn = getConnectionFromThread();

            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery(query);
            list = mapper.mapping(rset);

        } catch (SQLException ex) {
            throw new DaoExceptions("Invalid Query transition: ", DaoConnections.class, ex);

        } finally {
            releaseAll(rset, pstmt);
        }

        return list;

    }
    
    
    public String buildQueryWhere(String[][][] searchWhere) {

        String where = "";

        int iand = 0;
        String newWhere = null;
        for (int i = 0; i <= searchWhere.length - 1; i++) {
            if ((!searchWhere[i][0][2].isEmpty()) && (!searchWhere[i][0][2].equalsIgnoreCase("NoDef"))) {

                switch (searchWhere[i][0][1]) {
                    case "LIKE":
                        newWhere = " " + searchWhere[i][0][0] + " LIKE " + "'%" + searchWhere[i][0][2] + "%'";
                        break;
                    default:
                        newWhere = " " + searchWhere[i][0][0] + " = " + "'" + searchWhere[i][0][2] + "'";

                }

                if (iand > 0) {
                    where = where + " AND" + newWhere;
                } else {
                    where = where + newWhere;
                }
                iand++;
            }
        }

        return where;

    }

    // InTo the DATABASE
    /**
     *
     * @param params
     * @return
     * @throws DaoExceptions
     */
    public String insert(String query, Object[] param) {

        String id = executePreparedUpdateAndReturnGeneratedKeys(query, param);
        return id;

    }

    
    public void update(String query, Object[] param) {
        executePreparedUpdate(query, param);

    }

    /*  
    
     public void delete(String worker_id) throws DeleteDaoException {

     try {

     final String query = "DELETE FROM worker WHERE pk_id = " + worker_id;

     executePreparedUpdate(query);

     } catch (SQLException e) {
     throw new DeleteDaoException("Not possible to make the transaction: ", e);
     }

       
     }
    
     */
}
