/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.globals;

/**
 *
 * @author ir
 */
public class Conn_db {

    /**
     * DATOS PARA LA CONEXION
     */
    static public String driver = "com.mysql.jdbc.Driver";
    static public String host = "localhost";
    static public String port = "3306";
    static public String bd = "dosim_indiv_CHUC";
    static public String login = "dichuc";
    static public String password = "dichuc";
    /**
     * tables names
     */
    static public String tbl_users = "users";
    static public String tbl_workers = "workers";
    static public String tbl_workerActHist = "worker_activity_history";
    static public String tbl_cumDose = "cumulative_dose";
    static public String tbl_doses = "doses";
    static public String tbl_doseNotes = "dose_notes";
    static public String tbl_doseNotesHist = "dose_notes_hist";
    static public String tbl_dsmt = "dosimeters";
    static public String tbl_dsmtStatus = "dsmt_status";
    static public String tbl_dsmtNotes = "dsmt_notes";
    static public String tbl_dsmtHist = "dsmt_hist";
    static public String tbl_dsmtNotesHist = "dsmt_notes_hist";
    
    
    /*
     +----------------------------+
     | Tables_in_dosim_indiv_CHUC |
     +----------------------------+
     | cumulative_dose            |
     | dose_notes                 |
     | dose_notes_hist            |
     | doses                      |
     | dosimeters                 |
     | dsmt_hist                  |
     | dsmt_notes                 |
     | dsmt_notes_hist            |
     | users                      |
     | worker_activity_history    |
     | workers                    |
     +----------------------------+

     */
}
