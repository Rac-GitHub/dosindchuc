/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Tbl_dsmt_hist {

     /**
     * columns names
     */
    static public String pk_dsmt_hist = "pk_dsmt_hist";
    static public String pk_dsmt_status = "pk_dsmt_status";
    static public String pk_dsmt_notes = "pk_dsmt_notes";
    static public String id_trigger = "id_trigger";
    static public String id_change = "id_change";
    static public String value = "value";
    static public String lastchange = "lastchange";

    /*
     * 

     +----------------+------------------+------+-----+---------+-----------------------------+
     | Field          | Type             | Null | Key | Default | Extra                       |
     +----------------+------------------+------+-----+---------+-----------------------------+
     | pk_dsmt_hist   | int(11) unsigned | NO   | PRI | NULL    | auto_increment              |
     | pk_dsmt_status | int(11) unsigned | NO   | PRI | NULL    |                             |
     | pk_dsmt_notes  | int(11) unsigned | NO   | PRI | NULL    |                             |
     | id_trigger     | varchar(10)      | NO   |     | NULL    |                             |
     | id_change      | smallint(6)      | NO   |     | NULL    |                             |
     | value          | varchar(20)      | NO   |     | NULL    |                             |
     | lastchange     | timestamp        | YES  |     | NULL    | on update CURRENT_TIMESTAMP |
     +----------------+------------------+------+-----+---------+-----------------------------+

     */
}
