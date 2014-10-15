/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

/**
 *
 * @author ir
 */
public class Dsmt_status {

    private String pk_dsmt_status;
    private String pk_dsmt;
    private String pk_id;
    private String status;
    private String status_timestamp;
    private String note;
    private String timestamp;
    private String alert_level;
    private String alert_level_timestamp;
    private String lastchange;

    // constructors
    public Dsmt_status() {
    }

    // getters and setters
    
    public String getPk_dsmt_status() {
        return pk_dsmt_status;
    }

    public void setPk_dsmt_status(String pk_dsmt_status) {
        this.pk_dsmt_status = pk_dsmt_status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_timestamp() {
        return status_timestamp;
    }

    public void setStatus_timestamp(String status_timestamp) {
        this.status_timestamp = status_timestamp;
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

    public String getAlert_level() {
        return alert_level;
    }

    public void setAlert_level(String alert_level) {
        this.alert_level = alert_level;
    }

    public String getAlert_level_timestamp() {
        return alert_level_timestamp;
    }

    public void setAlert_level_timestamp(String alert_level_timestamp) {
        this.alert_level_timestamp = alert_level_timestamp;
    }

    public String getLastchange() {
        return lastchange;
    }

    public void setLastchange(String lastchange) {
        this.lastchange = lastchange;
    }

  
    
    
}
