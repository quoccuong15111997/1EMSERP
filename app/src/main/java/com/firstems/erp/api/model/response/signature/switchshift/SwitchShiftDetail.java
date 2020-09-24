package com.firstems.erp.api.model.response.signature.switchshift;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/21/2020.
 */
public class SwitchShiftDetail implements Serializable {
    @SerializedName("EMPLCDDT")
    private String emplCDDT;
    @SerializedName("EMPLCDNM")
    private String emplCDNM;
    @SerializedName("EMPLRLNM")
    private String emplRLNM;
    @SerializedName("EMPLRLTN")
    private String emplRLTN;
    @SerializedName("FRLVDATE")
    private String frLVDATE;
    @SerializedName("TOLVDATE")
    private String toLVDATE;
    @SerializedName("TIMEMORN")
    private String timeMORN;
    @SerializedName("TIMEAFTR")
    private String timeAFTR;
    @SerializedName("TIMEEVEN")
    private String timeEEVEN;

    public SwitchShiftDetail() {
    }

    public String getEmplCDDT() {
        return emplCDDT;
    }

    public void setEmplCDDT(String emplCDDT) {
        this.emplCDDT = emplCDDT;
    }

    public String getEmplCDNM() {
        return emplCDNM;
    }

    public void setEmplCDNM(String emplCDNM) {
        this.emplCDNM = emplCDNM;
    }

    public String getEmplRLNM() {
        return emplRLNM;
    }

    public void setEmplRLNM(String emplRLNM) {
        this.emplRLNM = emplRLNM;
    }

    public String getEmplRLTN() {
        return emplRLTN;
    }

    public void setEmplRLTN(String emplRLTN) {
        this.emplRLTN = emplRLTN;
    }

    public String getFrLVDATE() {
        return frLVDATE;
    }

    public void setFrLVDATE(String frLVDATE) {
        this.frLVDATE = frLVDATE;
    }

    public String getToLVDATE() {
        return toLVDATE;
    }

    public void setToLVDATE(String toLVDATE) {
        this.toLVDATE = toLVDATE;
    }

    public String getTimeMORN() {
        return timeMORN;
    }

    public void setTimeMORN(String timeMORN) {
        this.timeMORN = timeMORN;
    }

    public String getTimeAFTR() {
        return timeAFTR;
    }

    public void setTimeAFTR(String timeAFTR) {
        this.timeAFTR = timeAFTR;
    }

    public String getTimeEEVEN() {
        return timeEEVEN;
    }

    public void setTimeEEVEN(String timeEEVEN) {
        this.timeEEVEN = timeEEVEN;
    }
}
