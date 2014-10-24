/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

/**
 *
 * @author ir
 */
public class Dsmt_notes_hist {

    private String pk_dsmtNote_hist;
    private String pk_dsmt_notes;
    private String id_change;
    private String value;
    private String lastchange;

    
    // constructors
    public Dsmt_notes_hist() {
    }

    // getter and setters

    public String getPk_dsmtNote_hist() {
        return pk_dsmtNote_hist;
    }

    public void setPk_dsmtNote_hist(String pk_dsmtNote_hist) {
        this.pk_dsmtNote_hist = pk_dsmtNote_hist;
    }

    public String getPk_dsmt_notes() {
        return pk_dsmt_notes;
    }

    public void setPk_dsmt_notes(String pk_dsmt_notes) {
        this.pk_dsmt_notes = pk_dsmt_notes;
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
