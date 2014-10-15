/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Tbl_doses {

    /*
     * columns names
     */
    static public String pk_dose = "pk_dose";
    static public String pk_dsmt = "pk_dsmt";
    static public String pk_id = "pk_id";
    static public String year = "year";
    static public String trimester = "trimester";
    static public String month = "month";
    static public String hp007 = "hp007";
    static public String hp10 = "hp10";
    static public String comments = "comments";
    static public String timestamp = "timestamp";
    static public String lastchange = "lastchange";
    
    /*
     +------------+------------------+------+-----+---------+-----------------------------+
     | Field      | Type             | Null | Key | Default | Extra                       |
     +------------+------------------+------+-----+---------+-----------------------------+
     | pk_dose    | int(11) unsigned | NO   | PRI | NULL    | auto_increment              |
     | pk_dsmt    | int(11) unsigned | NO   | PRI | NULL    |                             |
     | pk_id      | int(10) unsigned | NO   | PRI | NULL    |                             |
     | year       | varchar(4)       | NO   |     | NULL    |                             |
     | trimester  | varchar(10)      | NO   |     | NULL    |                             |
     | month      | varchar(10)      | NO   |     | NULL    |                             |
     | hp007      | decimal(5,2)     | YES  |     | NULL    |                             |
     | hp10       | decimal(5,2)     | YES  |     | NULL    |                             |
     | comments   | varchar(255)     | YES  |     | NULL    |                             |
     | timestamp  | varchar(20)      | YES  |     | NULL    |                             |
     | lastchange | timestamp        | YES  |     | NULL    | on update CURRENT_TIMESTAMP |
     +------------+------------------+------+-----+---------+-----------------------------+

     */
    
    
    
}
