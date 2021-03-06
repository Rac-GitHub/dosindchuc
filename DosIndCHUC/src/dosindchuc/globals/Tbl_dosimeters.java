/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Tbl_dosimeters {

    /**
     * columns names
     */
    static public String pk_dsmt = "pk_dsmt";
    static public String pk_id = "pk_id";
    static public String id = "id";
    static public String label = "label";
    static public String dsmt_link = "dsmt_link";
    static public String type = "type";
    static public String periodicity = "periodicity";
    static public String supplier = "supplier";
    static public String comments = "comments";
    static public String status = "status";
    static public String timestamp = "timestamp";
    static public String lastchange = "lastchange";
    
     /*
     * number and variables to save in history
     */
    static public int nrHist = 7;
    static public String parmHist[] = new String []{id, label, type, periodicity, supplier, comments, status};

    /*  
     
     +-------------+------------------+------+-----+---------+-----------------------------+
     | Field       | Type             | Null | Key | Default | Extra                       |
     +-------------+------------------+------+-----+---------+-----------------------------+
     | pk_dsmt     | int(11) unsigned | NO   | PRI | NULL    | auto_increment              |
     | pk_id       | int(10) unsigned | NO   | PRI | NULL    |                             |
     | id          | varchar(15)      | YES  | UNI | NULL    |                             |
     | label       | varchar(45)      | YES  |     | NULL    |                             |
     | dsmt_link   | int(11)          | YES  |     | NULL    |                             |
     | type        | varchar(8)       | NO   |     | NULL    |                             |
     | periodicity | varchar(15)      | NO   |     | NULL    |                             |
     | supplier    | varchar(15)      | NO   |     | NULL    |                             |
     | comments    | varchar(255)     | YES  |     | NULL    |                             |
     | status      | varchar(20)      | NO   |     | NULL    |                             |
     | timestamp   | varchar(20)      | YES  |     | NULL    |                             |
     | lastchange  | timestamp        | YES  |     | NULL    | on update CURRENT_TIMESTAMP |
     +-------------+------------------+------+-----+---------+-----------------------------+

     */
}
