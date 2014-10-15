/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;


import dosindchuc.globals.Conn_db;
import dosindchuc.globals.Tbl_users;
import dosindchuc.model.dao.Help.DaoConnections;
import dosindchuc.model.dao.Help.DaoExceptions;
import dosindchuc.model.dao.Help.QueryMapper;
import dosindchuc.model.entities.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ir
 */
public class UsersDao {
    
    
    private DaoConnections daoConnection;
	
    public UsersDao () {
	daoConnection = new DaoConnections();
    }
    
      public String loginUsers(String username, String password) {
          
        final List<Users> users = new ArrayList<>();
     
        String query = "SELECT " + Tbl_users.name + ", " + Tbl_users.username + ", " + Tbl_users.password;
        String from = " FROM " + Conn_db.tbl_users;
        String[][][] searchWhere = {{{Tbl_users.username, "null", username}}, {{Tbl_users.password, "null", password}}};

        String where = " WHERE " + daoConnection.buildQueryWhere(searchWhere);

        query = query + from + where;

          daoConnection.executePreparedQuery(query, new QueryMapper<Users>() {
              @Override
              public List<Users> mapping(ResultSet rset) {

                  try {
                      while (rset.next()) {
                          Users usersInfo = new Users();
                          usersInfo.setName(rset.getString(Tbl_users.name));
                          users.add(usersInfo);
                      }
                      return users;
                  } catch (SQLException ex) {
                      throw new DaoExceptions("Error on ResulSet of query (loginUsers): ",
                              DaoConnections.class, ex);
                  }
              }
              
          });

        if (users.size() > 0) {
            return users.get(0).getName().toString();
        } else {
            return null;
        }
    }

    
}
