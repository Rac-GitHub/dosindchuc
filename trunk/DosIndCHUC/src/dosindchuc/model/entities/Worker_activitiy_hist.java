/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

/**
 *
 * @author ir
 */
public class Worker_activitiy_hist {
    
    private String pk_act;
    private String pk_id;
    private String job_activity;
    private String start_date;
    private String end_date;
    private String comments;
    private String timestamp;
    private String lastchange;

    
    // constructors
    public Worker_activitiy_hist() {
    }
    
    
    // getters and setters  

    public String getPk_act() {
        return pk_act;
    }

    public void setPk_act(String pk_act) {
        this.pk_act = pk_act;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getJob_activity() {
        return job_activity;
    }

    public void setJob_activity(String job_activity) {
        this.job_activity = job_activity;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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
