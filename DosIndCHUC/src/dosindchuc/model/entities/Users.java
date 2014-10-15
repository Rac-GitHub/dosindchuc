/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

import dosindchuc.model.entities.Help.SetEnums.worker_category;

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
}
