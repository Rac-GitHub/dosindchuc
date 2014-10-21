/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

import dosindchuc.model.entities.Help.SetEnums.dsmt_status;
import dosindchuc.model.entities.Help.SetEnums.note_alertlevel;

/**
 *
 * @author ir
 */
public class Dsmt_status {

    private String pk_dsmt_status;
    private String pk_dsmt;
    private String pk_id;
    private dsmt_status status;
    private String status_timestamp;
    private String note;
    private String timestamp;
    private note_alertlevel alert_level;
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

    public dsmt_status getStatus() {
        return status;
    }

    public void setStatus(dsmt_status status) {
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

    public note_alertlevel getAlert_level() {
        return alert_level;
    }

    public void setAlert_level(note_alertlevel alert_level) {
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
