/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;


import dosindchuc.model.dao.Help.DaoConnections;

/**
 *
 * @author ir
 */
public class UsersDao {
    
    
    private DaoConnections daoConnection;
    
	
    public UsersDao () {
	daoConnection = new DaoConnections();
    }
    
    
    public String loginUsers (String username, String password) {
        
  
        String [][][] where = { {{"username", "null", username}}, {{"password", "null", password}} };
        Object [][] loginUsers = daoConnection.executeSelectivePreparedQuery("users", "name, username, password ", where);
        
        if (loginUsers.length > 0) {
            return loginUsers[0][0].toString();
        } else {
            return null;
        }
    }
    
    
}
