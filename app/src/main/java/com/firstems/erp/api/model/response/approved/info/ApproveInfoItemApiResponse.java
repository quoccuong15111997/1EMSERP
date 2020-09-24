package com.firstems.erp.api.model.response.approved.info;

import com.firstems.erp.api.model.response.approved.info.file.ApproveIncludeFileApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/31/2020.
 */
public class ApproveInfoItemApiResponse {
    @SerializedName("DCMNCODE")
    private String dcmnCode;
    @SerializedName("DCMNNAME")
    private String dcmnName;
    @SerializedName("SCTNCODE")
    private String sctnCode;
    @SerializedName("KEY_CODE")
    private String keyCode;
    @SerializedName("MAINDATE")
    private String mainDate;
    @SerializedName("MAINCODE")
    private String mainCode;
    @SerializedName("LABLNAME")
    private String labelName;
    @SerializedName("CURRVLUE")
    private double currentValue;
    @SerializedName("DPTMSGST")
    private String dptmsgst;
    @SerializedName("DPSGNAME")
    private String dpsgName;
    @SerializedName("EMPLSGST")
    private String emplSgst;
    @SerializedName("EMSGNAME")
    private String empSgName;
    @SerializedName("NOTESGST")
    private String noteSgst;
    @SerializedName("APRVALOW")
    private int aprvalow;
    @SerializedName("SCTNNAME")
    private String sctnName;
    @SerializedName("UOM_NAME")
    private String uomName;
    @SerializedName("SENDOPTN")
    private int sendOptn;
    @SerializedName("LABL_ADD")
    private String labelAdd;
    @SerializedName("PRCSLIST")
    private String pRCSLIST;
    @SerializedName("DETAIL")
    private List<ApproveInfoDetailApiResponse> details;
    @SerializedName("DCMNFILE")
    private List<ApproveIncludeFileApiResponse> fileList;

    public ApproveInfoItemApiResponse() {
    }

    public List<ApproveIncludeFileApiResponse> getFileList() {
        return fileList;
    }

    public void setFileList(List<ApproveIncludeFileApiResponse> fileList) {
        this.fileList = fileList;
    }

    public String getpRCSLIST() {
        return pRCSLIST;
    }

    public void setpRCSLIST(String pRCSLIST) {
        this.pRCSLIST = pRCSLIST;
    }

    public String getDcmnCode() {
        return dcmnCode;
    }

    public void setDcmnCode(String dcmnCode) {
        this.dcmnCode = dcmnCode;
    }

    public String getDcmnName() {
        return dcmnName;
    }

    public void setDcmnName(String dcmnName) {
        this.dcmnName = dcmnName;
    }

    public String getSctnCode() {
        return sctnCode;
    }

    public void setSctnCode(String sctnCode) {
        this.sctnCode = sctnCode;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getMainDate() {
        return mainDate;
    }

    public void setMainDate(String mainDate) {
        this.mainDate = mainDate;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public String getDptmsgst() {
        return dptmsgst;
    }

    public void setDptmsgst(String dptmsgst) {
        this.dptmsgst = dptmsgst;
    }

    public String getDpsgName() {
        return dpsgName;
    }

    public void setDpsgName(String dpsgName) {
        this.dpsgName = dpsgName;
    }

    public String getEmplSgst() {
        return emplSgst;
    }

    public void setEmplSgst(String emplSgst) {
        this.emplSgst = emplSgst;
    }

    public String getEmpSgName() {
        return empSgName;
    }

    public void setEmpSgName(String empSgName) {
        this.empSgName = empSgName;
    }

    public String getNoteSgst() {
        return noteSgst;
    }

    public void setNoteSgst(String noteSgst) {
        this.noteSgst = noteSgst;
    }

    public int getAprvalow() {
        return aprvalow;
    }

    public void setAprvalow(int aprvalow) {
        this.aprvalow = aprvalow;
    }

    public String getSctnName() {
        return sctnName;
    }

    public void setSctnName(String sctnName) {
        this.sctnName = sctnName;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public int getSendOptn() {
        return sendOptn;
    }

    public void setSendOptn(int sendOptn) {
        this.sendOptn = sendOptn;
    }

    public String getLabelAdd() {
        return labelAdd;
    }

    public void setLabelAdd(String labelAdd) {
        this.labelAdd = labelAdd;
    }

    public List<ApproveInfoDetailApiResponse> getDetails() {
        return details;
    }

    public void setDetails(List<ApproveInfoDetailApiResponse> details) {
        this.details = details;
    }
}