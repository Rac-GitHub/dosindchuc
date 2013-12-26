/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

import dosindchuc.model.entities.Help.SetEnums.dsmt_periodicity;
import dosindchuc.model.entities.Help.SetEnums.dsmt_supplier;
import dosindchuc.model.entities.Help.SetEnums.dsmt_type;
import dosindchuc.model.entities.Help.SetEnums.status;
import java.util.Objects;



/**
 *
 * @author ir
 */
public class Dosimeter {
    
    private String pk_dsmt;
    private String pk_id;
    private String id;
    private String label;
    private dsmt_type type;
    private dsmt_periodicity periodicity;
    private dsmt_supplier supplier;
    private String comments;
    private String timestamp;
    private status status;
    private String status_timestamp;
    private String lastchange;

    
    
    // constructors
    
    public Dosimeter() {
        
    }

    public Dosimeter(String pk_dsmt, String pk_id, String id, String label, dsmt_type type,
            dsmt_periodicity periodicity, dsmt_supplier supplier, String comments, String timestamp,
            status status, String status_timestamp, String lastchange) {
        this.pk_dsmt = pk_dsmt;
        this.pk_id = pk_id;
        this.id = id;
        this.label = label;
        this.type = type;
        this.periodicity = periodicity;
        this.supplier = supplier;
        this.comments = comments;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
        this.lastchange = lastchange;
    }

    public Dosimeter(String pk_id, String id, String label, dsmt_type type, dsmt_periodicity periodicity, dsmt_supplier supplier
            , String comments, String timestamp, status status, String status_timestamp) {
        this.pk_id = pk_id;
        this.id = id;
        this.label = label;
        this.type = type;
        this.periodicity = periodicity;
        this.supplier = supplier;
        this.comments = comments;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
    }

    
    
    
    
    
    // set and getters

    public String getPk_dsmt() {
        return pk_dsmt;
    }

    public void setPk_dsmt(String pk_dsmt) {
        this.pk_dsmt = pk_dsmt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public dsmt_type getType() {
        return type;
    }

    public void setType(dsmt_type type) {
        this.type = type;
    }

    public dsmt_periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(dsmt_periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public dsmt_supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(dsmt_supplier supplier) {
        this.supplier = supplier;
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
        this.timestamp = timestamp;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public String getStatus_timestamp() {
        return status_timestamp;
    }

    public void setStatus_timestamp(String status_timestamp) {
        this.status_timestamp = status_timestamp;
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
        return "Dosimeter{" + "pk_dsmt=" + pk_dsmt + ", pk_id=" + pk_id + ", id=" 
                + id + ", label=" + label + ", type=" + type + ", periodicity=" + periodicity + ", supplier=" 
                + supplier + ", comments=" + comments + ", timestamp=" + timestamp + ", status=" + status 
                + ", status_timestamp=" + status_timestamp + ", lastchange=" + lastchange + '}';
    }
    
    
    
}