/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Tbl_workers {

    /**
     * columns names
     */
    static public String pk_id = "pk_id";
    static public String id_mec = "id_mec";
    static public String name = "name";
    static public String nick = "nick";
    static public String BI = "BI";
    static public String nationality = "nationality";
    static public String nif = "nif";
    static public String birth = "birth";
    static public String sex = "sex";
    static public String category = "category";
    static public String department = "department";
    static public String sector = "sector";
    static public String job_activity = "job_activity";
    static public String comments = "comments";
    static public String timestamp = "timestamp";
    static public String status = "status";
    static public String status_timestamp = "status_timestamp";
    static public String lastchange = "lastchange";

    /*    
    
     +------------------+------------------+------+-----+----------+-----------------------------+
     | Field            | Type             | Null | Key | Default  | Extra                       |
     +------------------+------------------+------+-----+----------+-----------------------------+
     | pk_id            | int(10) unsigned | NO   | PRI | NULL     | auto_increment              |
     | id_mec           | varchar(10)      | YES  | UNI | NULL     |                             |
     | name             | varchar(80)      | NO   |     | NULL     |                             |
     | nick             | varchar(15)      | YES  |     | NULL     |                             |
     | BI               | varchar(15)      | YES  | UNI | NULL     |                             |
     | nationality      | varchar(15)      | YES  |     | Portugal |                             |
     | nif              | varchar(15)      | YES  | UNI | NULL     |                             |
     | birth            | date             | YES  |     | NULL     |                             |
     | sex              | enum('M','F')    | NO   |     | F        |                             |
     | category         | varchar(20)      | YES  |     | NULL     |                             |
     | department       | varchar(20)      | YES  |     | NULL     |                             |
     | sector           | varchar(20)      | YES  |     | NULL     |                             |
     | job_activity     | varchar(20)      | YES  |     | NULL     |                             |
     | comments         | varchar(255)     | YES  |     | NULL     |                             |
     | timestamp        | varchar(20)      | YES  |     | NULL     |                             |
     | status           | varchar(20)      | NO   |     | NULL     |                             |
     | status_timestamp | varchar(20)      | YES  |     | NULL     |                             |
     | lastchange       | timestamp        | YES  |     | NULL     | on update CURRENT_TIMESTAMP |
     +------------------+------------------+------+-----+----------+-----------------------------+

     */
}
