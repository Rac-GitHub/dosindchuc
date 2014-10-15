/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Tbl_dsmt_status {

    /**
     * columns names
     */
    static public String pk_dsmt_status = "pk_dsmt_status";
    static public String pk_dsmt = "pk_dsmt";
    static public String pk_id = "pk_id";
    static public String status = "status";
    static public String status_timestamp = "status_timestamp";
    static public String note = "note";
    static public String timestamp = "timestamp";
    static public String alert_level = "alert_level";
    static public String alert_level_timestamp = "alert_level_timestamp";
    static public String lastchange = "lastchange";

    /*
     +-----------------------+------------------+------+-----+---------+-----------------------------+
     | Field                 | Type             | Null | Key | Default | Extra                       |
     +-----------------------+------------------+------+-----+---------+-----------------------------+
     | pk_dsmt_status        | int(11) unsigned | NO   | PRI | NULL    | auto_increment              |
     | pk_dsmt               | int(11) unsigned | NO   | PRI | NULL    |                             |
     | pk_id                 | int(10) unsigned | NO   | PRI | NULL    |                             |
     | status                | varchar(20)      | NO   |     | NULL    |                             |
     | status_timestamp      | varchar(20)      | YES  |     | NULL    |                             |
     | note                  | varchar(255)     | YES  |     | NULL    |                             |
     | timestamp             | varchar(20)      | YES  |     | NULL    |                             |
     | alert_level           | varchar(8)       | NO   |     | NULL    |                             |
     | alert_level_timestamp | varchar(20)      | YES  |     | NULL    |                             |
     | lastchange            | timestamp        | YES  |     | NULL    | on update CURRENT_TIMESTAMP |
     +-----------------------+------------------+------+-----+---------+-----------------------------+
    
     */
}
