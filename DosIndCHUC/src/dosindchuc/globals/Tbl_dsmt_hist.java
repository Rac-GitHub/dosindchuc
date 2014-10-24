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
    static public String pk_dsmt = "pk_dsmt";
    static public String id_change = "id_change";
    static public String value = "value";
    static public String lastchange = "lastchange";
    

    /*
     * 
     +--------------+------------------+------+-----+-------------------+----------------+
     | Field        | Type             | Null | Key | Default           | Extra          |
     +--------------+------------------+------+-----+-------------------+----------------+
     | pk_dsmt_hist | int(11) unsigned | NO   | PRI | NULL              | auto_increment |
     | pk_dsmt      | int(11) unsigned | NO   | PRI | NULL              |                |
     | id_change    | smallint(6)      | NO   |     | NULL              |                |
     | value        | varchar(255)     | NO   |     | NULL              |                |
     | lastchange   | timestamp        | YES  |     | CURRENT_TIMESTAMP |                |
     +--------------+------------------+------+-----+-------------------+----------------+

     */
}
