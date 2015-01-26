/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

/**
 *
 * @author ir
 */
public class ActiveDsmt {

    private String pk_dsmt;
    private String pk_id;
    private String id;
    private String label;
    private String type;
    private String periodicity;
    private String supplier;
    private String status;
    private String lastchange;

    // constructors
    public ActiveDsmt() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastchange() {
        return lastchange;
    }

    public void setLastchange(String lastchange) {
        this.lastchange = lastchange;
    }





}
