/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums.Trimester;
import dosindchuc.model.entities.Help.SetEnums.month;
import java.util.Objects;

/**
 *
 * @author ir
 */
public class Dose_info {
    
    private String pk_dose;
    private String pk_dsmt;
    private String pk_id;
    private String year;
    private Trimester trimester;
    private month month;
    private String hp007;
    private String hp10;
    private String comments;
    private String timestamp;
    private String lastchange;

    
    
    // constructors

    public Dose_info() {
        
    }

    public Dose_info(String pk_dose, String pk_dsmt, String pk_id, String year, Trimester trimester, month month
            , String hp007, String hp10, String comments, String timestamp, String lastchange) {
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

    public Dose_info(String pk_dsmt, String pk_id, String year, Trimester trimester, month month, String hp007, String hp10
            , String comments, String timestamp) {
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

    public String getPk_dose() {
        return pk_dose;
    }

    public void setPk_dose(String pk_dose) {
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
    
    public String getHp007() {
        return hp007;
    }

    public void setHp007(String hp007) {
        this.hp007 = hp007;
    }

    public String getHp10() {
        return hp10;
    }

    public void setHp10(String hp10) {
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
        this.timestamp = new DateAndTime().currDateTime();
    }

    public String getPk_dsmt() {
        return pk_dsmt;
    }

    public void setPk_dsmt(String pk_dsmt) {
        this.pk_dsmt = pk_dsmt;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
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
        return "Dose_info{" + "pk_dose=" + pk_dose + ", pk_dsmt=" + pk_dsmt + ", pk_id=" + pk_id + ", year=" + year 
                + ", trimester=" + trimester + ", month=" + month + ", hp007=" + hp007 + ", hp10=" + hp10 
                + ", comments=" + comments + ", timestamp=" + timestamp + ", lastchange=" + lastchange + '}';
    }

    
    
    
}