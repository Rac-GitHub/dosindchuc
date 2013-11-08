/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

//


import dosindchuc.entities.create_enums.staff_category;
import dosindchuc.entities.create_enums.staff_department;
import dosindchuc.entities.create_enums.staff_sex;
import dosindchuc.entities.create_enums.status;
import java.util.Objects;



/**
 *
 * @author ir
 */
public class Staff {
  
    
  private short pk_id;
  private short id_mec;
  private String name;
  private String nick;
  private String BI;
  private String nationality;
  private String nif;
  private String birth;
  
  private staff_sex sex; 
  private staff_category category;
  private staff_department department;
  private String sector;

  private String comments;
  private String timestamp;
  private status status;
  private String status_timestamp;

  // constructors
    public Staff() {

    }

    public Staff(short pk_id, short id_mec, String name, String nick, String BI, String nationality, String nif, 
            String birth, staff_sex sex, staff_category category, staff_department department, String sector, 
            String timestamp, status status, String status_timestamp) {
        this.pk_id = pk_id;
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
        this.timestamp = timestamp;
        this.status = status;
        this.status_timestamp = status_timestamp;
    }
 
  // getters and setters  
    
    public short getPk_id() {
        return pk_id;
    }

    public void setPk_id(short pk_id) {
        this.pk_id = pk_id;
    }

    public short getId_mec() {
        return id_mec;
    }

    public void setId_mec(short id_mec) {
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

    public staff_sex getSex() {
        return sex;
    }

    public void setSex(staff_sex sex) {
        this.sex = sex;
    }

    public staff_category getCategory() {
        return category;
    }

    public void setCategory(staff_category category) {
        this.category = category;
    }

    public staff_department getDepartment() {
        return department;
    }

    public void setDepartment(staff_department department) {
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
        this.timestamp = new help_entities().currDateTime();
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
        this.status_timestamp = new help_entities().currDateTime();
    }

 // hashCode and equals   
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.pk_id;
        hash = 67 * hash + this.id_mec;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.nick);
        hash = 67 * hash + Objects.hashCode(this.BI);
        hash = 67 * hash + Objects.hashCode(this.nationality);
        hash = 67 * hash + Objects.hashCode(this.nif);
        hash = 67 * hash + Objects.hashCode(this.birth);
        hash = 67 * hash + (this.sex != null ? this.sex.hashCode() : 0);
        hash = 67 * hash + (this.category != null ? this.category.hashCode() : 0);
        hash = 67 * hash + (this.department != null ? this.department.hashCode() : 0);
        hash = 67 * hash + Objects.hashCode(this.sector);
        hash = 67 * hash + Objects.hashCode(this.comments);
        hash = 67 * hash + Objects.hashCode(this.timestamp);
        hash = 67 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 67 * hash + Objects.hashCode(this.status_timestamp);
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
        final Staff other = (Staff) obj;
        if (this.pk_id != other.pk_id) {
            return false;
        }
        if (this.id_mec != other.id_mec) {
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
        if (!Objects.equals(this.comments, other.comments)) {
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
        return true;
    }

    
    // to string
    
    @Override
    public String toString() {
        return "staff{" + "pk_id=" + pk_id + 
                ", id_mec=" + id_mec + ", name=" + name + "," +
                " nick=" + nick + ", BI=" + BI + ", nationality=" + nationality +
                ", nif=" + nif + ", birth=" + birth + ", sex=" + sex + 
                ", category=" + category + ", department=" + department + 
                ", sector=" + sector + ", comments=" + comments + 
                ", timestamp=" + timestamp + ", status=" + status + ", status_timestamp=" + status_timestamp + '}';
    }
  
  
    
    
}
