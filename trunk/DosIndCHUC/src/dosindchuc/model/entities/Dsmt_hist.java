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
    private String pk_dsmt;
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

    public String getPk_dsmt() {
        return pk_dsmt;
    }

    public void setPk_dsmt(String pk_dsmt) {
        this.pk_dsmt = pk_dsmt;
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
