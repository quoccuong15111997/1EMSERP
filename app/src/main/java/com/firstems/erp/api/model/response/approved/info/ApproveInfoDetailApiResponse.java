package com.firstems.erp.api.model.response.approved.info;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Nguyen Quoc Cuong on 7/31/2020.
 */
public class ApproveInfoDetailApiResponse {
    @SerializedName("DCMNCODE")
    private String dcmnCode;
    @SerializedName("PRCSDATE")
    private String prcsDate;
    @SerializedName("PRCSEMPL")
    private String prcsEmp;
    @SerializedName("EMPCNAME")
    private String empName;
    @SerializedName("PRCSCODE")
    private String prcsCode;
    @SerializedName("PRCSNAME")
    private String prcsName;
    @SerializedName("SGSTNAME")
    private String sgstName;
    @SerializedName("PRCSNOTE")
    private String prcsNote;
    @SerializedName("PRCSAPRV")
    private int prcsApprove;

    public ApproveInfoDetailApiResponse() {
    }

    public String getDcmnCode() {
        return dcmnCode;
    }

    public void setDcmnCode(String dcmnCode) {
        this.dcmnCode = dcmnCode;
    }

    public String getPrcsDate() {
        return prcsDate;
    }

    public void setPrcsDate(String prcsDate) {
        this.prcsDate = prcsDate;
    }

    public String getPrcsEmp() {
        return prcsEmp;
    }

    public void setPrcsEmp(String prcsEmp) {
        this.prcsEmp = prcsEmp;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPrcsCode() {
        return prcsCode;
    }

    public void setPrcsCode(String prcsCode) {
        this.prcsCode = prcsCode;
    }

    public String getPrcsName() {
        return prcsName;
    }

    public void setPrcsName(String prcsName) {
        this.prcsName = prcsName;
    }

    public String getSgstName() {
        return sgstName;
    }

    public void setSgstName(String sgstName) {
        this.sgstName = sgstName;
    }

    public String getPrcsNote() {
        return prcsNote;
    }

    public void setPrcsNote(String prcsNote) {
        this.prcsNote = prcsNote;
    }

    public int getPrcsApprove() {
        return prcsApprove;
    }

    public void setPrcsApprove(int prcsApprove) {
        this.prcsApprove = prcsApprove;
    }
}