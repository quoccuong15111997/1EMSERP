package com.firstems.erp.api.model.response.askpermistion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class AskPermistionDetail implements Serializable {
    @SerializedName("MAINCODE")
    private String mAINCODE;
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
    @SerializedName("EMPLRLNM")
    private String eMPLRLNM;
    @SerializedName("EMPLRLTN")
    private String eMPLRLTN;

    public AskPermistionDetail() {
    }

    public String getmAINCODE() {
        return mAINCODE;
    }

    public void setmAINCODE(String mAINCODE) {
        this.mAINCODE = mAINCODE;
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
}
