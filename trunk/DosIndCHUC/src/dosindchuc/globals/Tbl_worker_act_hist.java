/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Tbl_worker_act_hist {

     /**
     * columns names
     */
    static public String pk_act = "pk_act";
    static public String pk_id = "pk_id";
    static public String job_activity = "job_activity";
    static public String start_date = "start_date";
    static public String end_date = "end_date";
    static public String comments = "comments";
    static public String timestamp = "timestamp";
    static public String lastchange = "lastchange";

    /*

     +--------------+------------------+------+-----+---------+-----------------------------+
     | Field        | Type             | Null | Key | Default | Extra                       |
     +--------------+------------------+------+-----+---------+-----------------------------+
     | pk_act       | int(11) unsigned | NO   | PRI | NULL    | auto_increment              |
     | pk_id        | int(10) unsigned | NO   | PRI | NULL    |                             |
     | job_activity | varchar(20)      | YES  |     | NULL    |                             |
     | start_date   | varchar(20)      | YES  |     | NULL    |                             |
     | end_date     | varchar(20)      | YES  |     | NULL    |                             |
     | comments     | varchar(255)     | YES  |     | NULL    |                             |
     | timestamp    | varchar(20)      | YES  |     | NULL    |                             |
     | lastchange   | timestamp        | YES  |     | NULL    | on update CURRENT_TIMESTAMP |
     +--------------+------------------+------+-----+---------+-----------------------------+
    
     */
}
