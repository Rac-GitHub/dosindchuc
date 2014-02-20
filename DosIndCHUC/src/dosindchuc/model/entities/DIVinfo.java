/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.entities.Help.SetEnums.Trimester;
import dosindchuc.model.entities.Help.SetEnums.dsmt_periodicity;
import dosindchuc.model.entities.Help.SetEnums.worker_category;
import dosindchuc.model.entities.Help.SetEnums.worker_department;

/**
 *
 * @author ir
 */
public class DIVinfo {
 
    
    private String pk_id;
    private String pk_dsmt;
    private String name;
    private String id_mec;
    private SetEnums.worker_category category;
    private SetEnums.worker_department department;
    private String id_dsmt;
    private SetEnums.dsmt_periodicity periodicity;
    private SetEnums.Trimester trimester;
    private SetEnums.month month;
    private String year;
    private String hp007;
    private String hp10;
    private String comments;
    private String dose_note;
    
    
    
    public DIVinfo() {
    
    }

   
        
    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getPk_dsmt() {
        return pk_dsmt;
    }

    public void setPk_dsmt(String pk_dsmt) {
        this.pk_dsmt = pk_dsmt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_mec() {
        return id_mec;
    }

    public void setId_mec(String id_mec) {
        this.id_mec = id_mec;
    }

    public worker_category getCategory() {
        return category;
    }

    public void setCategory(worker_category category) {
        this.category = category;
    }

    
    
    public worker_department getDepartment() {
        return department;
    }

    public void setDepartment(worker_department department) {
        this.department = department;
    }

    

    public String getId_dsmt() {
        return id_dsmt;
    }

    public void setId_dsmt(String id_dsmt) {
        this.id_dsmt = id_dsmt;
    }

    public dsmt_periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(dsmt_periodicity periodicity) {
        this.periodicity = periodicity;
    }

    
    
    public Trimester getTrimester() {
        return trimester;
    }

    public void setTrimester(Trimester trimester) {
        this.trimester = trimester;
    }

    public SetEnums.month getMonth() {
        return month;
    }

    public void setMonth(SetEnums.month month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
    
    

    public String getHp007() {
        return hp007;
    }

    public void setHp007(String hp007) {
        this.hp007 = hp007;
    }

    public String getHp10() {
        return hp10;
    }

    public void setHp10(String hp10) {
        this.hp10 = hp10;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDose_note() {
        return dose_note;
    }

    public void setDose_note(String dose_note) {
        this.dose_note = dose_note;
    }

    
    
//     
    
 
  
    
}
