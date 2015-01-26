/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

/**
 *
 * @author ir
 */
public class Cumulative_dose {

    private String pk_cumulative_dose;
    private String pk_id;
    private String hp007_1year;
    private String hp10_1year;
    private String ndsmt_1year;
    private String hp007_5year;
    private String hp10_5year;
    private String ndsmt_5year;
    private String lastchange;

    private String cumulDose;
    private String numDsmtCumulDose;

    
    // constructors
    public Cumulative_dose() {
    }

    public String getPk_cumulative_dose() {
        return pk_cumulative_dose;
    }

    public void setPk_cumulative_dose(String pk_cumulative_dose) {
        this.pk_cumulative_dose = pk_cumulative_dose;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getHp007_1year() {
        return hp007_1year;
    }

    public void setHp007_1year(String hp007_1year) {
        this.hp007_1year = hp007_1year;
    }

    public String getHp10_1year() {
        return hp10_1year;
    }

    public void setHp10_1year(String hp10_1year) {
        this.hp10_1year = hp10_1year;
    }

    public String getNdsmt_1year() {
        return ndsmt_1year;
    }

    public void setNdsmt_1year(String ndsmt_1year) {
        this.ndsmt_1year = ndsmt_1year;
    }

    public String getHp007_5year() {
        return hp007_5year;
    }

    public void setHp007_5year(String hp007_5year) {
        this.hp007_5year = hp007_5year;
    }

    public String getHp10_5year() {
        return hp10_5year;
    }

    public void setHp10_5year(String hp10_5year) {
        this.hp10_5year = hp10_5year;
    }

    public String getNdsmt_5year() {
        return ndsmt_5year;
    }

    public void setNdsmt_5year(String ndsmt_5year) {
        this.ndsmt_5year = ndsmt_5year;
    }

    public String getLastchange() {
        return lastchange;
    }

    public void setLastchange(String lastchange) {
        this.lastchange = lastchange;
    }

    public String getCumulDose() {
        return cumulDose;
    }

    public void setCumulDose(String cumulDose) {
        this.cumulDose = cumulDose;
    }

    public String getNumDsmtCumulDose() {
        return numDsmtCumulDose;
    }

    public void setNumDsmtCumulDose(String numDsmtCumulDose) {
        this.numDsmtCumulDose = numDsmtCumulDose;
    }




}
