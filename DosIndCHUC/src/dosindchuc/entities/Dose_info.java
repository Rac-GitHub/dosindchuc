/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

import dosindchuc.entities.Help.help_entities;
import dosindchuc.entities.Help.create_enums.Trimester;
import dosindchuc.entities.Help.create_enums.month;
import java.util.Objects;

/**
 *
 * @author ir
 */
public class Dose_info {
    
    private int pk_dose;
    private int pk_dsmt;
    private int pk_id;
    private String year;
    private Trimester trimester;
    private month month;
    private float hp007;
    private float hp10;
    private String comments;
    private String timestamp;
    private String lastchange;

    
    
    // constructors

    public Dose_info() {
        
    }

    public Dose_info(int pk_dose, int pk_dsmt, int pk_id, String year, Trimester trimester, month month, float hp007, float hp10, String comments, String timestamp, String lastchange) {
        this.pk_dose = pk_dose;
        this.pk_dsmt = pk_dsmt;
        this.pk_id = pk_id;
        this.year = year;
        this.trimester = trimester;
        this.month = month;
        this.hp007 = hp007;
        this.hp10 = hp10;
        this.comments = comments;
        this.timestamp = timestamp;
        this.lastchange = lastchange;
    }

    public Dose_info(int pk_dsmt, int pk_id, String year, Trimester trimester, month month, float hp007, float hp10, String comments, String timestamp) {
        this.pk_dsmt = pk_dsmt;
        this.pk_id = pk_id;
        this.year = year;
        this.trimester = trimester;
        this.month = month;
        this.hp007 = hp007;
        this.hp10 = hp10;
        this.comments = comments;
        this.timestamp = timestamp;
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

    public month getMonth() {
        return month;
    }

    public void setMonth(month month) {
        this.month = month;
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

    public int getPk_dsmt() {
        return pk_dsmt;
    }

    public void setPk_dsmt(short pk_dsmt) {
        this.pk_dsmt = pk_dsmt;
    }

    public int getPk_id() {
        return pk_id;
    }

    public void setPk_id(short pk_id) {
        this.pk_id = pk_id;
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
        hash = 89 * hash + this.pk_dose;
        hash = 89 * hash + this.pk_dsmt;
        hash = 89 * hash + this.pk_id;
        hash = 89 * hash + Objects.hashCode(this.year);
        hash = 89 * hash + (this.trimester != null ? this.trimester.hashCode() : 0);
        hash = 89 * hash + Float.floatToIntBits(this.hp007);
        hash = 89 * hash + Float.floatToIntBits(this.hp10);
        hash = 89 * hash + Objects.hashCode(this.comments);
        hash = 89 * hash + Objects.hashCode(this.timestamp);
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
        if (this.pk_dsmt != other.pk_dsmt) {
            return false;
        }
        if (this.pk_id != other.pk_id) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (this.trimester != other.trimester) {
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
        return true;
    }

      
    
    // toString

    @Override
    public String toString() {
        return "Dose_info{" + "pk_dose=" + pk_dose + ", pk_dsmt=" + pk_dsmt + ", pk_id=" + pk_id + ", year=" + year 
                + ", trimester=" + trimester + ", month=" + month + ", hp007=" + hp007 + ", hp10=" + hp10 
                + ", comments=" + comments + ", timestamp=" + timestamp + ", lastchange=" + lastchange + '}';
    }

    
    
    
}
