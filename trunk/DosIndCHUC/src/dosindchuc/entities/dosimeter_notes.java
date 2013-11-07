/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

import dosindchuc.entities.create_enums.status;
import java.util.Objects;

/**
 *
 * @author ir
 */
public class dosimeter_notes {
    
   private short pk_notes_dsmt;
   private String note;
   private String timestamp;
   private status status;
   private String status_timestamp;
   private short pk_dsmt;
    
   
   // constructors

    public dosimeter_notes() {
    }

    public dosimeter_notes(short pk_notes_dsmt, String note, String timestamp, status status, String status_timestamp, short pk_dsmt) {
        this.pk_notes_dsmt = pk_notes_dsmt;
        this.note = note;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
        this.pk_dsmt = pk_dsmt;
    }
   
    // getter and setters

    public short getPk_notes_dsmt() {
        return pk_notes_dsmt;
    }

    public void setPk_notes_dsmt(short pk_notes_dsmt) {
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
        this.timestamp = timestamp;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public String getStatus_timestamp() {
        return status_timestamp;
    }

    public void setStatus_timestamp(String status_timestamp) {
        this.status_timestamp = status_timestamp;
    }

    public short getPk_dsmt() {
        return pk_dsmt;
    }

    public void setPk_dsmt(short pk_dsmt) {
        this.pk_dsmt = pk_dsmt;
    }
    
    
    // hash and equals

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.pk_notes_dsmt;
        hash = 11 * hash + Objects.hashCode(this.note);
        hash = 11 * hash + Objects.hashCode(this.timestamp);
        hash = 11 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 11 * hash + Objects.hashCode(this.status_timestamp);
        hash = 11 * hash + this.pk_dsmt;
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
        final dosimeter_notes other = (dosimeter_notes) obj;
        if (this.pk_notes_dsmt != other.pk_notes_dsmt) {
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
        if (this.pk_dsmt != other.pk_dsmt) {
            return false;
        }
        return true;
    }
    
    
    // toString

    @Override
    public String toString() {
        return "dosimeter_notes{" + "pk_notes_dsmt=" + pk_notes_dsmt + ", note=" + note + ", timestamp=" + timestamp + 
                ", status=" + status + ", status_timestamp=" + status_timestamp + ", pk_dsmt=" + pk_dsmt + '}';
    }
    
        
    
}
