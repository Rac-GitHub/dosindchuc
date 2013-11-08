/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.entities.Staff;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ir
 */
public class StaffDao {
    
    private DaoHelper daoHelper;
    
    
    public void insert (Staff staff) {
        
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = daoHelper.getConnection();
            
            stmt = conn.createStatement();
            
            stmt = 
            
        } catch (SQLException ex) {
            Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
