package com.firstems.erp.model;

import java.io.Serializable;
import java.util.Date;

public class FilterModel implements Serializable {
    private Date beginDate;
    private Date endDate;
    private boolean isWaitsignature = true;
    private boolean isWaitApproved =true;
    private boolean isDone =true;
    
    
    public FilterModel() {
    }
    
    public FilterModel(Date beginDate, Date endDate, boolean isWaitsignature, boolean isWaitApproved, boolean isDone) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.isWaitsignature = isWaitsignature;
        this.isWaitApproved = isWaitApproved;
        this.isDone = isDone;
    }
    
    public Date getBeginDate() {
        return beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public boolean isWaitsignature() {
        return isWaitsignature;
    }
    
    public void setWaitsignature(boolean waitsignature) {
        isWaitsignature = waitsignature;
    }
    
    public boolean isWaitApproved() {
        return isWaitApproved;
    }
    
    public void setWaitApproved(boolean waitApproved) {
        isWaitApproved = waitApproved;
    }
    
    public boolean isDone() {
        return isDone;
    }
    
    public void setDone(boolean done) {
        isDone = done;
    }
}
