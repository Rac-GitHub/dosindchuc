/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

/**
 *
 * @author ir
 */
public class Dsmt_hist {

    private String pk_dsmt_hist;
    private String pk_dsmt_status;
    private String pk_dsmt_notes;
    private String id_trigger;
    private String id_change;
    private String value;
    private String lastchange;

    // constructors
    public Dsmt_hist() {
    }

    public String getPk_dsmt_hist() {
        return pk_dsmt_hist;
    }

    public void setPk_dsmt_hist(String pk_dsmt_hist) {
        this.pk_dsmt_hist = pk_dsmt_hist;
    }

    public String getPk_dsmt_status() {
        return pk_dsmt_status;
    }

    public void setPk_dsmt_status(String pk_dsmt_status) {
        this.pk_dsmt_status = pk_dsmt_status;
    }

    public String getPk_dsmt_notes() {
        return pk_dsmt_notes;
    }

    public void setPk_dsmt_notes(String pk_dsmt_notes) {
        this.pk_dsmt_notes = pk_dsmt_notes;
    }

    public String getId_trigger() {
        return id_trigger;
    }

    public void setId_trigger(String id_trigger) {
        this.id_trigger = id_trigger;
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
