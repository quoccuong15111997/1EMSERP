package com.firstems.erp.model;

/**
 * Created by Nguyen Quoc Cuong on 7/15/2020.
 */
public class ProgressApproveModel {
    private String username;
    private String date;
    private String content;
    private String info;
    private boolean isApproved;

    public ProgressApproveModel() {
    }

    public ProgressApproveModel(String username, String date, String content, String info, boolean isApproved) {
        this.username = username;
        this.date = date;
        this.content = content;
        this.info = info;
        this.isApproved = isApproved;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
