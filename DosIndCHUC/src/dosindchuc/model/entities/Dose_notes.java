/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;


import dosindchuc.model.entities.Help.SetEnums.note_alertlevel;
import dosindchuc.model.entities.Help.SetEnums.note_status;

/**
 *
 * @author ir
 */
public class Dose_notes {
    
    
    private String pk_notes_dose;
    private String pk_dose;
    private String note;
    private String timestamp;
    private note_status status;
    private String status_timestamp;
    private note_alertlevel alert_level;
    private String alert_level_timestamp;
    private String lastchange;

    
    
    // constructors

    public Dose_notes() {
    }

    public Dose_notes(String pk_notes_dose, String pk_dose, String note, String timestamp, note_status status, String status_timestamp, note_alertlevel alert_level, String lastchange) {
        this.pk_notes_dose = pk_notes_dose;
        this.pk_dose = pk_dose;
        this.note = note;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
        this.alert_level = alert_level;
        this.lastchange = lastchange;
    }

    public Dose_notes(String pk_dose, String note, String timestamp, note_status status, String status_timestamp, note_alertlevel alert_level) {
        this.pk_dose = pk_dose;
        this.note = note;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
        this.alert_level = alert_level;
    }

    
     
    
    
    // getter and setters

    public String getPk_notes_dose() {
        return pk_notes_dose;
    }

    public void setPk_notes_dose(String pk_notes_dose) {
        this.pk_notes_dose = pk_notes_dose;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
     }

    public note_status getStatus() {
        return status;
    }

    public void setStatus(note_status status) {
        this.status = status;
    }

    public String getStatus_timestamp() {
        return status_timestamp;
    }

    public void setStatus_timestamp(String status_timestamp) {
        this.status_timestamp = status_timestamp;
    }

    public String getPk_dose() {
        return pk_dose;
    }

    public void setPk_dose(String pk_dose) {
        this.pk_dose = pk_dose;
    }

    public note_alertlevel getAlert_level() {
        return alert_level;
    }

    public void setAlert_level(note_alertlevel alert_level) {
        this.alert_level = alert_level;
    }

    public String getAlert_level_timestamp() {
        return alert_level_timestamp;
    }

    public void setAlert_level_timestamp(String alert_level_timestamp) {
        this.alert_level_timestamp = alert_level_timestamp;
    }

    
    public String getLastchange() {
        return lastchange;
    }

    public void setLastchange(String lastchange) {
        this.lastchange = lastchange;
    }

    
   

    
    
    // toString

    @Override
    public String toString() {
        return "Dose_notes{" + "pk_notes_dose=" + pk_notes_dose + ", pk_dose=" + pk_dose + ", note=" + note + ", timestamp=" 
                + timestamp + ", status=" + status + ", status_timestamp=" + status_timestamp + ", alert_level=" 
                + alert_level + ", lastchange=" + lastchange + '}';
    }

  
    
}
