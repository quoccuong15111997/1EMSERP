package com.firstems.erp.api.model.request.switchshift;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen Quoc Cuong on 8/24/2020.
 */
public class SwithshiftDetailRequest {
    @SerializedName("EMPLCDDT")
    private String eMPLCDDT;
    @SerializedName("EMPLCDNM")
    private String eMPLCDNM;
    @SerializedName("EMPLRLNM")
    private String eMPLRLNM;
    @SerializedName("EMPLRLTN")
    private String eMPLRLTN;
    @SerializedName("FRLVDATE")
    private String fRLVDATE;
    @SerializedName("TOLVDATE")
    private String tOLVDATE;
    @SerializedName("TIMEMORN")
    private String tIMEMORN;
    @SerializedName("TIMEAFTR")
    private String tIMEAFTR;
    @SerializedName("TIMEEVEN")
    private String tIMEEVEN;
    @SerializedName("MAINCODE")
    private String mAINCODE;

    public SwithshiftDetailRequest() {
    }

    public String getmAINCODE() {
        return mAINCODE;
    }

    public void setmAINCODE(String mAINCODE) {
        this.mAINCODE = mAINCODE;
    }

    public String geteMPLCDDT() {
        return eMPLCDDT;
    }

    public void seteMPLCDDT(String eMPLCDDT) {
        this.eMPLCDDT = eMPLCDDT;
    }

    public String geteMPLCDNM() {
        return eMPLCDNM;
    }

    public void seteMPLCDNM(String eMPLCDNM) {
        this.eMPLCDNM = eMPLCDNM;
    }

    public String geteMPLRLNM() {
        return eMPLRLNM;
    }

    public void seteMPLRLNM(String eMPLRLNM) {
        this.eMPLRLNM = eMPLRLNM;
    }

    public String geteMPLRLTN() {
        return eMPLRLTN;
    }

    public void seteMPLRLTN(String eMPLRLTN) {
        this.eMPLRLTN = eMPLRLTN;
    }

    public String getfRLVDATE() {
        return fRLVDATE;
    }

    public void setfRLVDATE(String fRLVDATE) {
        this.fRLVDATE = fRLVDATE;
    }

    public String gettOLVDATE() {
        return tOLVDATE;
    }

    public void settOLVDATE(String tOLVDATE) {
        this.tOLVDATE = tOLVDATE;
    }

    public String gettIMEMORN() {
        return tIMEMORN;
    }

    public void settIMEMORN(String tIMEMORN) {
        this.tIMEMORN = tIMEMORN;
    }

    public String gettIMEAFTR() {
        return tIMEAFTR;
    }

    public void settIMEAFTR(String tIMEAFTR) {
        this.tIMEAFTR = tIMEAFTR;
    }

    public String gettIMEEVEN() {
        return tIMEEVEN;
    }

    public void settIMEEVEN(String tIMEEVEN) {
        this.tIMEEVEN = tIMEEVEN;
    }
}
