/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

//


import dosindchuc.entities.Help.create_enums.status;
import dosindchuc.entities.Help.create_enums.worker_category;
import dosindchuc.entities.Help.create_enums.worker_department;
import dosindchuc.entities.Help.create_enums.worker_sex;
import java.util.Objects;



/**
 *
 * @author ir
 */
public class Worker {
  
    
  private int pk_id;
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

  private String comments;
  private String timestamp;
  private status status;
  private String status_timestamp;
  private String lastchange;

  
  
  // constructors
    public Worker() {

    }

    public Worker(int pk_id, String id_mec, String name, String nick, String nationality, String nif, 
            String birth, worker_sex sex, worker_category category, worker_department department, String sector, 
            String comments, String timestamp, status status, String status_timestamp, String lastchange) {
        this.pk_id = pk_id;
        this.id_mec = id_mec;
        this.name = name;
        this.nick = nick;
        this.nationality = nationality;
        this.nif = nif;
        this.birth = birth;
        this.sex = sex;
        this.category = category;
        this.department = department;
        this.sector = sector;
        this.comments = comments;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
        this.lastchange = lastchange;
    }

    public Worker(String id_mec, String name, String nick, String BI, String nationality, String nif,
            String birth, worker_sex sex, worker_category category, worker_department department, 
            String sector, String comments, String timestamp, status status, String status_timestamp) {
        this.id_mec = id_mec;
        this.name = name;
        this.nick = nick;
        this.BI = BI;
        this.nationality = nationality;
        this.nif = nif;
        this.birth = birth;
        this.sex = sex;
        this.category = category;
        this.department = department;
        this.sector = sector;
        this.comments = comments;
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
    }

    
    
 
  // getters and setters  
    
    public int getPk_id() {
        return pk_id;
    }

    public void setPk_id(int pk_id) {
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

    
    
        
    
 // hashCode and equals   
       
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.pk_id;
        hash = 89 * hash + Objects.hashCode(this.id_mec);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.nick);
        hash = 89 * hash + Objects.hashCode(this.BI);
        hash = 89 * hash + Objects.hashCode(this.nationality);
        hash = 89 * hash + Objects.hashCode(this.nif);
        hash = 89 * hash + Objects.hashCode(this.birth);
        hash = 89 * hash + (this.sex != null ? this.sex.hashCode() : 0);
        hash = 89 * hash + (this.category != null ? this.category.hashCode() : 0);
        hash = 89 * hash + (this.department != null ? this.department.hashCode() : 0);
        hash = 89 * hash + Objects.hashCode(this.sector);
        hash = 89 * hash + Objects.hashCode(this.comments);
        hash = 89 * hash + Objects.hashCode(this.timestamp);
        hash = 89 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 89 * hash + Objects.hashCode(this.status_timestamp);
        hash = 89 * hash + Objects.hashCode(this.lastchange);
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
        final Worker other = (Worker) obj;
        if (this.pk_id != other.pk_id) {
            return false;
        }
        if (!Objects.equals(this.id_mec, other.id_mec)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.nick, other.nick)) {
            return false;
        }
        if (!Objects.equals(this.BI, other.BI)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        if (!Objects.equals(this.birth, other.birth)) {
            return false;
        }
        if (this.sex != other.sex) {
            return false;
        }
        if (this.category != other.category) {
            return false;
        }
        if (this.department != other.department) {
            return false;
        }
        if (!Objects.equals(this.sector, other.sector)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.status_timestamp, other.status_timestamp)) {
            return false;
        }
        if (!Objects.equals(this.lastchange, other.lastchange)) {
            return false;
        }
        return true;
    }

    
    

   

    
    // to string

    @Override
    public String toString() {
        return "Worker{" + "pk_id=" + pk_id + ", id_mec=" + id_mec + ", name=" + name + ", nick=" 
                + nick + ", BI=" + BI + ", nationality=" + nationality + ", nif=" + nif + ", birth=" + birth + ", sex=" 
                + sex + ", category=" + category + ", department=" + department + ", sector=" + sector + ", comments=" 
                + comments + ", timestamp=" + timestamp + ", status=" + status + ", status_timestamp=" 
                + status_timestamp + ", lastchange=" + lastchange + '}';
    }
    
    
  
    
    
}
