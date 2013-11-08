/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

import dosindchuc.entities.create_enums.Trimester;
import java.util.Objects;

/**
 *
 * @author ir
 */
public class Dose_info {
    
    private int pk_dose;
    private String year;
    private Trimester trimester;
    private float hp007;
    private float hp10;
    private String comments;
    private String timestamp;
    private short pk_dsmt;
    
    
    // constructors

    public Dose_info() {
    }

    public Dose_info(int pk_dose, String year, Trimester trimester, float hp007, 
            float hp10, String comments, String timestamp, short pk_dsmt) {
        this.pk_dose = pk_dose;
        this.year = year;
        this.trimester = trimester;
        this.hp007 = hp007;
        this.hp10 = hp10;
        this.comments = comments;
        this.timestamp = timestamp;
        this.pk_dsmt = pk_dsmt;
    }
    
    
    // getter and setters

    public int getPk_dose() {
        return pk_dose;
    }

    public void setPk_dose(int pk_dose) {
        this.pk_dose = pk_dose;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Trimester getTrimester() {
        return trimester;
    }

    public void setTrimester(Trimester trimester) {
        this.trimester = trimester;
    }

    public float getHp007() {
        return hp007;
    }

    public void setHp007(float hp007) {
        this.hp007 = hp007;
    }

    public float getHp10() {
        return hp10;
    }

    public void setHp10(float hp10) {
        this.hp10 = hp10;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = new help_entities().currDateTime();
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
        hash = 19 * hash + this.pk_dose;
        hash = 19 * hash + Objects.hashCode(this.year);
        hash = 19 * hash + Objects.hashCode(this.trimester);
        hash = 19 * hash + Float.floatToIntBits(this.hp007);
        hash = 19 * hash + Float.floatToIntBits(this.hp10);
        hash = 19 * hash + Objects.hashCode(this.comments);
        hash = 19 * hash + Objects.hashCode(this.timestamp);
        hash = 19 * hash + this.pk_dsmt;
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
        final Dose_info other = (Dose_info) obj;
        if (this.pk_dose != other.pk_dose) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.trimester, other.trimester)) {
            return false;
        }
        if (Float.floatToIntBits(this.hp007) != Float.floatToIntBits(other.hp007)) {
            return false;
        }
        if (Float.floatToIntBits(this.hp10) != Float.floatToIntBits(other.hp10)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
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
        return "dose_info{" + "pk_dose=" + pk_dose + ", year=" + year + ", trimester=" + trimester + ", hp007=" + hp007 + ","
                + " hp10=" + hp10 + ", comments=" + comments + ", timestamp=" + timestamp + ", pk_dsmt=" + pk_dsmt + '}';
    }
    
    
    
    
}
