package com.firstems.erp.api.model.response.userlogin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class UserLogin {
    @SerializedName("LCTNCODE")
    private String locateCode;
    @SerializedName("LCTNNAME")
    private String locateName;
    @SerializedName("USERCODE")
    private String userCode;
    @SerializedName("USERNAME")
    private String userName;
    @SerializedName("APP_RGHT")
    private int appRight;
    @SerializedName("EMPLCODE")
    private String empCode;
    @SerializedName("DPTMCODE")
    private String partCode;
    @SerializedName("DPTMNAME")
    private String partName;
    @SerializedName("PSTNCODE")
    private String positionCode;
    @SerializedName("PSTNNAME")
    private String positionName;
    @SerializedName("JOB_CODE")
    private String jobCode;
    @SerializedName("JOB_NAME")
    private String jobName;
    @SerializedName("LOGOCOMP")
    private String logoCompany;

    public UserLogin() {
    }

    public String getLocateCode() {
        return locateCode;
    }

    public void setLocateCode(String locateCode) {
        this.locateCode = locateCode;
    }

    public String getLocateName() {
        return locateName;
    }

    public void setLocateName(String locateName) {
        this.locateName = locateName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAppRight() {
        return appRight;
    }

    public void setAppRight(int appRight) {
        this.appRight = appRight;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLogoCompany() {
        return logoCompany;
    }

    public void setLogoCompany(String logoCompany) {
        this.logoCompany = logoCompany;
    }
}
