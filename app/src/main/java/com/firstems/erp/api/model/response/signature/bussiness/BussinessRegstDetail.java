package com.firstems.erp.api.model.response.signature.bussiness;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/19/2020.
 */
public class BussinessRegstDetail implements Serializable {
    @SerializedName("MAINCODE")
    private String mainCode;
    @SerializedName("EMPLCODE")
    private String employCode;
    @SerializedName("FRLVDATE")
    private String fromDate;
    @SerializedName("TOLVDATE")
    private String toDate;
    @SerializedName("TIMEMORN")
    private String contentMor;
    @SerializedName("TIMEAFTR")
    private String contentAfft;
    @SerializedName("TIMEEVEN")
    private String contentEvr;
    @SerializedName("WORKTYPE")
    private String workType;
    @SerializedName("WORKPLAC")
    private String workPlace;

    public BussinessRegstDetail() {
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getContentMor() {
        return contentMor;
    }

    public void setContentMor(String contentMor) {
        this.contentMor = contentMor;
    }

    public String getContentAfft() {
        return contentAfft;
    }

    public void setContentAfft(String contentAfft) {
        this.contentAfft = contentAfft;
    }

    public String getContentEvr() {
        return contentEvr;
    }

    public void setContentEvr(String contentEvr) {
        this.contentEvr = contentEvr;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
}