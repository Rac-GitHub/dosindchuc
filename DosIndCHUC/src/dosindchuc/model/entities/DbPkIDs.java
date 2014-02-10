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
    private static ArrayList<Object []> dsmt_id;
    private static ArrayList2D dsmtNotes_id;
    private static ArrayList dose_id;
    private static ArrayList2D doseNotes_id;
    private static int dsmtRowSelected;
    private static int doseRowSelected;
    private static ArrayList<Object []> alertNote;


    
    public DbPkIDs() {
    
    }

// getter and setters    
    
    
    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        DbPkIDs.worker_id = worker_id;
    }

    public ArrayList<Object []> getDsmt_id() {
        return dsmt_id;
    }

    public void setDsmt_id(ArrayList<Object []> dsmt_id) {
        DbPkIDs.dsmt_id = dsmt_id;
    }

    public ArrayList2D getDsmtNotes_id() {
        return dsmtNotes_id;
    }

    public void setDsmtNotes_id(ArrayList2D dsmtNotes_id) {
        DbPkIDs.dsmtNotes_id = dsmtNotes_id;
    }

    public ArrayList getDose_id() {
        return dose_id;
    }

    public void setDose_id(ArrayList dose_id) {
        DbPkIDs.dose_id = dose_id;
    }

    public ArrayList2D getDoseNotes_id() {
        return doseNotes_id;
    }

    public void setDoseNotes_id(ArrayList2D doseNotes_id) {
        DbPkIDs.doseNotes_id = doseNotes_id;
    }

    public int getDsmtRowSelected() {
        return dsmtRowSelected;
    }

    public void setDsmtRowSelected(int dsmtRowSelected) {
        DbPkIDs.dsmtRowSelected = dsmtRowSelected;
    }

    public int getDoseRowSelected() {
        return doseRowSelected;
    }

    public void setDoseRowSelected(int doseRowSelected) {
        DbPkIDs.doseRowSelected = doseRowSelected;
    }

    public ArrayList<Object[]> getAlertNote() {
        return alertNote;
    }

    public void setAlertNote(ArrayList<Object[]> alertNote) {
        DbPkIDs.alertNote = alertNote;
    }
    
    
    
    
    
}
