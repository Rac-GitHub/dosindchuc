/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

//
import dosindchuc.model.entities.Help.SetEnums.worker_category;
import dosindchuc.model.entities.Help.SetEnums.worker_department;
import dosindchuc.model.entities.Help.SetEnums.worker_sex;
import dosindchuc.model.entities.Help.SetEnums.worker_status;

/**
 *
 * @author ir
 */
public class Worker {

    private String pk_id;
    private String id_mec;
    private String name;
    private String nick;
    private String BI;
    private String nationality;
    private String nif;
    private String birth;
    private worker_sex sex;
    private worker_category category;
    private worker_department department;
    private String sector;
    private String job_activity;
    private String comments;
    private String timestamp;
    private worker_status status;
    private String status_timestamp;
    private String lastchange;

    // constructors
    public Worker() {

        this.nationality = "Portuguese";

    }

    // getters and setters  
    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getId_mec() {
        return id_mec;
    }

    public void setId_mec(String id_mec) {
        this.id_mec = id_mec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getBI() {
        return BI;
    }

    public void setBI(String BI) {
        this.BI = BI;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public worker_sex getSex() {
        return sex;
    }

    public void setSex(worker_sex sex) {
        this.sex = sex;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getJob_activity() {
        return job_activity;
    }

    public void setJob_activity(String job_activity) {
        this.job_activity = job_activity;
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

    public worker_status getStatus() {
        return status;
    }

    public void setStatus(worker_status status) {
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
}
