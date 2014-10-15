/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

/**
 *
 * @author ir
 */
public class Dose_notes_hist {

    private String pk_dose_notes_hist;
    private String pk_dose_notes;
    private String id_change;
    private String value;
    private String lastchange;
 

    // constructors
    public Dose_notes_hist() {
    }

    // getters and setters

    public String getPk_dose_notes_hist() {
        return pk_dose_notes_hist;
    }

    public void setPk_dose_notes_hist(String pk_dose_notes_hist) {
        this.pk_dose_notes_hist = pk_dose_notes_hist;
    }

    public String getPk_dose_notes() {
        return pk_dose_notes;
    }

    public void setPk_dose_notes(String pk_dose_notes) {
        this.pk_dose_notes = pk_dose_notes;
    }

    public String getId_change() {
        return id_change;
    }

    public void setId_change(String id_change) {
        this.id_change = id_change;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLastchange() {
        return lastchange;
    }

    public void setLastchange(String lastchange) {
        this.lastchange = lastchange;
    }
    
    
    
    
}
