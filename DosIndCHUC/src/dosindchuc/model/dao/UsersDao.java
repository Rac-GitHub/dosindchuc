/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.dao.Help.DaoHelper;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Users;
import dosindchuc.model.entities.Worker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class UsersDao {
    
    
    
     private DaoHelper daoHelper;
    
	
    public UsersDao () {
	daoHelper = new DaoHelper();
    }
    
    
    public String loginUsers (String username, String password) {
        
        
        System.out.println(username);
        
        String where = "username = '" + username + "' AND password = '" + password + "'";
       
        Object [][] loginUsers = daoHelper.executeSelectivePreparedQuery("users", "name, username, password ", where);
        
        System.out.println(loginUsers.length);
        if (loginUsers.length > 0) {
            return loginUsers[0][0].toString();
        } else {
            return null;
        }
    }
    
    
}
