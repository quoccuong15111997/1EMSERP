package com.firstems.erp.api.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/28/2020.
 */
public class ApproveDocumentRequest implements Serializable {
    @SerializedName("DCMNCODE")
    private String dCMNCODE;
    @SerializedName("LCTNCODE")
    private String lCTNCODE;
    @SerializedName("KEY_CODE")
    private String kEYCODE;
    @SerializedName("PRCSCODE")
    private String pRCSCODE;
    @SerializedName("NOTETEXT")
    private String nOTETEXT;
    @SerializedName("ADD_EMPL")
    private String aDDEMPL;

    public ApproveDocumentRequest() {
    }

    public String getdCMNCODE() {
        return dCMNCODE;
    }

    public void setdCMNCODE(String dCMNCODE) {
        this.dCMNCODE = dCMNCODE;
    }

    public String getlCTNCODE() {
        return lCTNCODE;
    }

    public void setlCTNCODE(String lCTNCODE) {
        this.lCTNCODE = lCTNCODE;
    }

    public String getkEYCODE() {
        return kEYCODE;
    }

    public void setkEYCODE(String kEYCODE) {
        this.kEYCODE = kEYCODE;
    }

    public String getpRCSCODE() {
        return pRCSCODE;
    }

    public void setpRCSCODE(String pRCSCODE) {
        this.pRCSCODE = pRCSCODE;
    }

    public String getnOTETEXT() {
        return nOTETEXT;
    }

    public void setnOTETEXT(String nOTETEXT) {
        this.nOTETEXT = nOTETEXT;
    }

    public String getaDDEMPL() {
        return aDDEMPL;
    }

    public void setaDDEMPL(String aDDEMPL) {
        this.aDDEMPL = aDDEMPL;
    }
}
