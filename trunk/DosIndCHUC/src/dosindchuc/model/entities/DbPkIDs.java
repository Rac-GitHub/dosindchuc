/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;


import dosindchuc.model.dao.Help.ArrayList2D;
import java.util.ArrayList;

/**
 *
 * @author ir
 */
public class DbPkIDs {
    
    private static String worker_id;  
    private static ArrayList2D dsmt_id;
    private static String dsmtNotes_id;
    private static ArrayList dose_id;
    private static String doseNotes_id;

    
    public DbPkIDs() {
    
    }

// getter and setters    
    
    
    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        DbPkIDs.worker_id = worker_id;
    }

    public ArrayList2D getDsmt_id() {
        return dsmt_id;
    }

    public void setDsmt_id(ArrayList2D dsmt_id) {
        DbPkIDs.dsmt_id = dsmt_id;
    }

    public String getDsmtNotes_id() {
        return dsmtNotes_id;
    }

    public void setDsmtNotes_id(String dsmtNotes_id) {
        DbPkIDs.dsmtNotes_id = dsmtNotes_id;
    }

    public ArrayList getDose_id() {
        return dose_id;
    }

    public void setDose_id(ArrayList dose_id) {
        DbPkIDs.dose_id = dose_id;
    }

    public String getDoseNotes_id() {
        return doseNotes_id;
    }

    public void setDoseNotes_id(String doseNotes_id) {
        DbPkIDs.doseNotes_id = doseNotes_id;
    }
    
    
    
    
    
}
