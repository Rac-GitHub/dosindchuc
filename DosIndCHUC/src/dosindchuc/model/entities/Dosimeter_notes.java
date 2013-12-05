/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums.note_alertlevel;
import dosindchuc.model.entities.Help.SetEnums.note_status;
import java.util.Objects;

/**
 *
 * @author ir
 */
public class Dosimeter_notes {
    
   private int pk_notes_dsmt;
   private int pk_dsmt;
   private String note;
   private String timestamp;
   private note_status status;
   private String status_timestamp;
   private note_alertlevel alert_level;
   private String lastchange;

   
   
      // constructors

    public Dosimeter_notes() {
        
    }

    public Dosimeter_notes(int pk_notes_dsmt, int pk_dsmt, String note, String timestamp, note_status status, String status_timestamp, note_alertlevel alert_level, String lastchange) {
        this.pk_notes_dsmt = pk_notes_dsmt;
        this.pk_dsmt = pk_dsmt;
        this.note = note;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
        this.alert_level = alert_level;
        this.lastchange = lastchange;
    }

    public Dosimeter_notes(int pk_dsmt, String note, String timestamp, note_status status, String status_timestamp, note_alertlevel alert_level) {
        this.pk_dsmt = pk_dsmt;
        this.note = note;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
        this.alert_level = alert_level;
    }

    
    
    
   
    // getter and setters

    public int getPk_notes_dsmt() {
        return pk_notes_dsmt;
    }

    public void setPk_notes_dsmt(int pk_notes_dsmt) {
        this.pk_notes_dsmt = pk_notes_dsmt;
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
        this.timestamp = new DateAndTime().currDateTime();
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
        this.status_timestamp = new DateAndTime().currDateTime();
    }

    public int getPk_dsmt() {
        return pk_dsmt;
    }

    public void setPk_dsmt(int pk_dsmt) {
        this.pk_dsmt = pk_dsmt;
    }
    
     public note_alertlevel getAlert_level() {
        return alert_level;
    }

    public void setAlert_level(note_alertlevel alert_level) {
        this.alert_level = alert_level;
    }

    public String getLastchange() {
        return lastchange;
    }

    public void setLastchange(String lastchange) {
        this.lastchange = lastchange;
    }
   
    
       
    
    // hash and equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.pk_notes_dsmt;
        hash = 13 * hash + this.pk_dsmt;
        hash = 13 * hash + Objects.hashCode(this.note);
        hash = 13 * hash + Objects.hashCode(this.timestamp);
        hash = 13 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 13 * hash + Objects.hashCode(this.status_timestamp);
        hash = 13 * hash + (this.alert_level != null ? this.alert_level.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dosimeter_notes other = (Dosimeter_notes) obj;
        if (this.pk_notes_dsmt != other.pk_notes_dsmt) {
            return false;
        }
        if (this.pk_dsmt != other.pk_dsmt) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.status_timestamp, other.status_timestamp)) {
            return false;
        }
        if (this.alert_level != other.alert_level) {
            return false;
        }
        return true;
    }

    // to String

    @Override
    public String toString() {
        return "Dosimeter_notes{" + "pk_notes_dsmt=" + pk_notes_dsmt + ", pk_dsmt=" + pk_dsmt + ", note=" + note + ", timestamp=" 
                + timestamp + ", status=" + status + ", status_timestamp=" + status_timestamp + ", alert_level=" 
                + alert_level + ", lastchange=" + lastchange + '}';
    }

        
    
}
