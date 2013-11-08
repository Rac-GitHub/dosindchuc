/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;


import dosindchuc.entities.create_enums.note_status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ir
 */
public class dose_notes {
    
    
    private short pk_notes_dose;
    private String note;
    private Date timestamp;
    private note_status status;
    private Date status_timestamp;
    private int pk_dose;
    
    
    // constructors

    public dose_notes() {
    }

    public dose_notes(short pk_notes_dose, String note, Date timestamp, note_status status, Date status_timestamp, int pk_dose) {
        this.pk_notes_dose = pk_notes_dose;
        this.note = note;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
        this.pk_dose = pk_dose;
    }
    
    
    // getter and setters

    public short getPk_notes_dose() {
        return pk_notes_dose;
    }

    public void setPk_notes_dose(short pk_notes_dose) {
        this.pk_notes_dose = pk_notes_dose;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public note_status getStatus() {
        return status;
    }

    public void setStatus(note_status status) {
        this.status = status;
    }

    public Date getStatus_timestamp() {
        return status_timestamp;
    }

    public void setStatus_timestamp(Date status_timestamp) {
        this.status_timestamp = status_timestamp;
    }

    public int getPk_dose() {
        return pk_dose;
    }

    public void setPk_dose(int pk_dose) {
        this.pk_dose = pk_dose;
    }
    
    
    
    // hash and equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.pk_notes_dose;
        hash = 53 * hash + Objects.hashCode(this.note);
        hash = 53 * hash + Objects.hashCode(this.timestamp);
        hash = 53 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 53 * hash + Objects.hashCode(this.status_timestamp);
        hash = 53 * hash + this.pk_dose;
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
        final dose_notes other = (dose_notes) obj;
        if (this.pk_notes_dose != other.pk_notes_dose) {
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
        if (this.pk_dose != other.pk_dose) {
            return false;
        }
        return true;
    }
    
    // toString

    @Override
    public String toString() {
        return "dose_notes{" + "pk_notes_dose=" + pk_notes_dose + ", note=" + note + ", timestamp=" + timestamp + 
                ", status=" + status + ", status_timestamp=" + status_timestamp + ", pk_dose=" + pk_dose + '}';
    }
    
    
    
    
}
