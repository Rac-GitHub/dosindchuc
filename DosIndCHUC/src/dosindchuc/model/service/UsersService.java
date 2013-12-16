/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.model.entities.Users;

/**
 *
 * @author ir
 */
public class UsersService {
    
    private Users user = new Users();
            
    public boolean login(String username, String password) {
         Object[][] res = null;
        if( res.length > 0) {
            user.setUsername( res[0][0].toString() );
            user.setPassword( res[0][1].toString() );
            return true;
        } else {
            return false;
        }
    
    }
    
}
