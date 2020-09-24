package com.firstems.erp.api.model.response.signature.bussiness;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/19/2020.
 */
public class BussinessRegstItem implements Serializable {
    @SerializedName("COMPCODE")
    private String comCode;
    @SerializedName("LCTNCODE")
    private String locateCode;
    @SerializedName("MAINCODE")
    private String mainCode;
    @SerializedName("MAINDATE")
    private String mainDate;
    @SerializedName("EMPLCODE")
    private String employCode;
    @SerializedName("SRVCRQST")
    private String servRequest;
    @SerializedName("BEG_DATE")
    private String beginDate;
    @SerializedName("END_DATE")
    private String endDate;
    @SerializedName("ACCERGHT")
    private int accessRight;
    @SerializedName("MCNTNTEXT")
    private String noteText;
    @SerializedName("WORK_DAY")
    private int wordkDay;
    @SerializedName("STTESIGN")
    private int stteSign;
    @SerializedName("KKKK0000")
    private String keyCode;
    @SerializedName("DETAIL")
    private List<BussinessRegstDetail> detailList;

    public BussinessRegstItem() {
    }

    public int getStteSign() {
        return stteSign;
    }

    public void setStteSign(int stteSign) {
        this.stteSign = stteSign;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getComCode() {
        return comCode;
    }

    public int getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(int accessRight) {
        this.accessRight = accessRight;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getLocateCode() {
        return locateCode;
    }

    public void setLocateCode(String locateCode) {
        this.locateCode = locateCode;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getMainDate() {
        return mainDate;
    }

    public void setMainDate(String mainDate) {
        this.mainDate = mainDate;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

    public String getServRequest() {
        return servRequest;
    }

    public void setServRequest(String servRequest) {
        this.servRequest = servRequest;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getWordkDay() {
        return wordkDay;
    }

    public void setWordkDay(int wordkDay) {
        this.wordkDay = wordkDay;
    }

    public List<BussinessRegstDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<BussinessRegstDetail> detailList) {
        this.detailList = detailList;
    }
}
