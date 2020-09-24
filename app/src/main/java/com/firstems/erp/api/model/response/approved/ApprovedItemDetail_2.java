package com.firstems.erp.api.model.response.approved;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class ApprovedItemDetail_2 implements Serializable, Comparable {
    @SerializedName("SCTNCODE")
    private String sCTNCODE;
    @SerializedName("KEY_CODE")
    private String kEYCODE;
    @SerializedName("MAINCODE")
    private String mAINCODE;
    @SerializedName("MAINDATE")
    private String mAINDATE;
    @SerializedName("SENTDATE")
    private String sENTDATE;
    @SerializedName("SENDEMPL")
    private String sENDEMPL;
    @SerializedName("EMPLNAME")
    private String eMPLNAME;
    @SerializedName("JOB_CODE")
    private String jOBCODE;
    @SerializedName("JOB_NAME")
    private String jOBNAME;
    @SerializedName("DPTMCODE")
    private String dPTMCODE;
    @SerializedName("DPTMNAME")
    private String dPTMNAME;

    public ApprovedItemDetail_2() {
    }

    public String getsCTNCODE() {
        return sCTNCODE;
    }

    public void setsCTNCODE(String sCTNCODE) {
        this.sCTNCODE = sCTNCODE;
    }

    public String getkEYCODE() {
        return kEYCODE;
    }

    public void setkEYCODE(String kEYCODE) {
        this.kEYCODE = kEYCODE;
    }

    public String getmAINCODE() {
        return mAINCODE;
    }

    public void setmAINCODE(String mAINCODE) {
        this.mAINCODE = mAINCODE;
    }

    public String getmAINDATE() {
        return mAINDATE;
    }

    public void setmAINDATE(String mAINDATE) {
        this.mAINDATE = mAINDATE;
    }

    public String getsENTDATE() {
        return sENTDATE;
    }

    public void setsENTDATE(String sENTDATE) {
        this.sENTDATE = sENTDATE;
    }

    public String getsENDEMPL() {
        return sENDEMPL;
    }

    public void setsENDEMPL(String sENDEMPL) {
        this.sENDEMPL = sENDEMPL;
    }

    public String geteMPLNAME() {
        return eMPLNAME;
    }

    public void seteMPLNAME(String eMPLNAME) {
        this.eMPLNAME = eMPLNAME;
    }

    public String getjOBCODE() {
        return jOBCODE;
    }

    public void setjOBCODE(String jOBCODE) {
        this.jOBCODE = jOBCODE;
    }

    public String getjOBNAME() {
        return jOBNAME;
    }

    public void setjOBNAME(String jOBNAME) {
        this.jOBNAME = jOBNAME;
    }

    public String getdPTMCODE() {
        return dPTMCODE;
    }

    public void setdPTMCODE(String dPTMCODE) {
        this.dPTMCODE = dPTMCODE;
    }

    public String getdPTMNAME() {
        return dPTMNAME;
    }

    public void setdPTMNAME(String dPTMNAME) {
        this.dPTMNAME = dPTMNAME;
    }
    
    @Override
    public int compareTo(Object o) {
        ApprovedItemDetail_2 compare  = (ApprovedItemDetail_2) o;
        if (compare.getkEYCODE().equals(this.kEYCODE) && compare.getmAINCODE().equals(this.kEYCODE)){
            return 0;
        }
        return 1;
    }
}
