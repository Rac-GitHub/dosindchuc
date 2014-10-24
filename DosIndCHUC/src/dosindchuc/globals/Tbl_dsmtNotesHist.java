/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Tbl_dsmtNotesHist {

    /**
     * columns names
     */
    static public String pk_dsmtNote_hist = "pk_dsmtNote_hist";
    static public String pk_dsmt_notes = "pk_dsmt_notes";
    static public String id_change = "id_change";
    static public String value = "value";
    static public String lastchange = "lastchange";
   
    /*
     * 
     +------------------+------------------+------+-----+-------------------+----------------+
     | Field            | Type             | Null | Key | Default           | Extra          |
     +------------------+------------------+------+-----+-------------------+----------------+
     | pk_dsmtNote_hist | int(11) unsigned | NO   | PRI | NULL              | auto_increment |
     | pk_dsmt_notes    | int(11) unsigned | NO   | PRI | NULL              |                |
     | id_change        | smallint(6)      | NO   |     | NULL              |                |
     | value            | varchar(255)     | NO   |     | NULL              |                |
     | lastchange       | timestamp        | YES  |     | CURRENT_TIMESTAMP |                |
     +------------------+------------------+------+-----+-------------------+----------------+

     */
}
