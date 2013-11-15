/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

import dosindchuc.entities.Help.create_enums.worker_category;
import java.util.Objects;

/**
 *
 * @author ir
 */
public class Users {
    
    private int pk_users;
    private String name;
    private String username;
    private String password;
    private worker_category job;
    private String department;
    private String comments;
    private String timestamp;
    private String lastchange;
    
    // constructors

    public Users() {
        
    }

    public Users(int pk_users, String name, String username, String password, worker_category job, String department, String comments, String timestamp, String lastchange) {
        this.pk_users = pk_users;
        this.name = name;
        this.username = username;
        this.password = password;
        this.job = job;
        this.department = department;
        this.comments = comments;
        this.timestamp = timestamp;
        this.lastchange = lastchange;
    }

       
    
    // getter and setters

    public int getPk_users() {
        return pk_users;
    }

    public void setPk_users(int pk_users) {
        this.pk_users = pk_users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public worker_category getJob() {
        return job;
    }

    public void setJob(worker_category job) {
        this.job = job;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getLastchange() {
        return lastchange;
    }

    public void setLastchange(String lastchange) {
        this.lastchange = lastchange;
    }
    
    
    
    
    //  hashcode and equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.pk_users;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.username);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + (this.job != null ? this.job.hashCode() : 0);
        hash = 67 * hash + Objects.hashCode(this.department);
        hash = 67 * hash + Objects.hashCode(this.comments);
        hash = 67 * hash + Objects.hashCode(this.timestamp);
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
        final Users other = (Users) obj;
        if (this.pk_users != other.pk_users) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.job != other.job) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }
    
    
    // toString

    @Override
    public String toString() {
        return "Users{" + "pk_users=" + pk_users + ", name=" + name + ", username=" + username + ", password=" 
                + password + ", job=" + job + ", department=" + department + ", comments=" + comments + ", timestamp=" 
                + timestamp + ", lastchange=" + lastchange + '}';
    }

    
    
}
