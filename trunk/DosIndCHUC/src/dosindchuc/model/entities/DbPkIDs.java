/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

/**
 *
 * @author ir
 */
public class DbPkIDs {
    
    public String worker_id;  
    public String dsmt_id;
    public String dsmtNotes_id;
    public String dose_id;
    public String doseNotes_id;

    
    public DbPkIDs() {
    
    }

// getter and setters    
    
    
    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public String getDsmt_id() {
        return dsmt_id;
    }

    public void setDsmt_id(String dsmt_id) {
        this.dsmt_id = dsmt_id;
    }

    public String getDsmtNotes_id() {
        return dsmtNotes_id;
    }

    public void setDsmtNotes_id(String dsmtNotes_id) {
        this.dsmtNotes_id = dsmtNotes_id;
    }

    public String getDose_id() {
        return dose_id;
    }

    public void setDose_id(String dose_id) {
        this.dose_id = dose_id;
    }

    public String getDoseNotes_id() {
        return doseNotes_id;
    }

    public void setDoseNotes_id(String doseNotes_id) {
        this.doseNotes_id = doseNotes_id;
    }
    
    
    
    
    
}
