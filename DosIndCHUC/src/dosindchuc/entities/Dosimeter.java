/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

import dosindchuc.entities.create_enums.dsmt_periodicity;
import dosindchuc.entities.create_enums.dsmt_supplier;
import dosindchuc.entities.create_enums.dsmt_type;
import dosindchuc.entities.create_enums.status;
import java.util.Objects;



/**
 *
 * @author ir
 */
public class Dosimeter {
    
    private short pk_dsmt;
    private short pk_id;
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

    public Dosimeter(short pk_dsmt, short pk_id, String id, String label, dsmt_type type,
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

        
    
    // set and getters

    public short getPk_dsmt() {
        return pk_dsmt;
    }

    public void setPk_dsmt(short pk_dsmt) {
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

    public short getPk_id() {
        return pk_id;
    }

    public void setPk_id(short pk_id) {
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
    
    
    
    
    
    // hash and equals

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.pk_dsmt;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.label);
        hash = 61 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 61 * hash + (this.periodicity != null ? this.periodicity.hashCode() : 0);
        hash = 61 * hash + (this.supplier != null ? this.supplier.hashCode() : 0);
        hash = 61 * hash + Objects.hashCode(this.comments);
        hash = 61 * hash + Objects.hashCode(this.timestamp);
        hash = 61 * hash + this.pk_id;
        hash = 61 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 61 * hash + Objects.hashCode(this.status_timestamp);
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
        final Dosimeter other = (Dosimeter) obj;
        if (this.pk_dsmt != other.pk_dsmt) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.periodicity != other.periodicity) {
            return false;
        }
        if (this.supplier != other.supplier) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (this.pk_id != other.pk_id) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.status_timestamp, other.status_timestamp)) {
            return false;
        }
        return true;
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
