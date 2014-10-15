/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Tbl_users {

    /**
     * columns names
     */
    static public String pk_users = "pk_users";
    static public String name = "name";
    static public String username = "username";
    static public String password = "password";
    static public String category = "category";
    static public String department = "department";
    static public String comments = "comments";
    static public String timestamp = "timestamp";
    static public String lastchange = "lastchange";
    
    /*
   
     +------------+------------------+------+-----+---------+-----------------------------+
     | Field      | Type             | Null | Key | Default | Extra                       |
     +------------+------------------+------+-----+---------+-----------------------------+
     | pk_users   | int(11) unsigned | NO   | PRI | NULL    | auto_increment              |
     | name       | varchar(45)      | NO   |     | NULL    |                             |
     | username   | varchar(15)      | NO   | UNI | NULL    |                             |
     | password   | varchar(20)      | NO   |     | NULL    |                             |
     | category   | varchar(20)      | YES  |     | NULL    |                             |
     | department | varchar(45)      | YES  |     | NULL    |                             |
     | comments   | varchar(255)     | YES  |     | NULL    |                             |
     | timestamp  | varchar(20)      | YES  |     | NULL    |                             |
     | lastchange | timestamp        | YES  |     | NULL    | on update CURRENT_TIMESTAMP |
     +------------+------------------+------+-----+---------+-----------------------------+
    
     */
}
