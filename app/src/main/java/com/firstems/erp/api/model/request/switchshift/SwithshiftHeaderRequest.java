package com.firstems.erp.api.model.request.switchshift;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/24/2020.
 */
public class SwithshiftHeaderRequest {
    @SerializedName("LCTNCODE")
    private String lCTNCODE;
    @SerializedName("MAINCODE")
    private String mAINCODE;
    @SerializedName("MAINDATE")
    private String mAINDATE;
    @SerializedName("CHGESLRY")
    private int cHGESLRY;
    @SerializedName("NOTETEXT")
    private String nOTETEXT;
    @SerializedName("ACCERGHT")
    private int aCCERGHT;
    @SerializedName("STTESIGN")
    private int sTTESIGN;
    @SerializedName("STTENAME")
    private String sTTENAME;
    @SerializedName("KKKK0000")
    private String keyCode;
    @SerializedName("DETAIL")
    private List<SwithshiftDetailRequest> detailRequests;

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public SwithshiftHeaderRequest() {
    }

    public String getlCTNCODE() {
        return lCTNCODE;
    }

    public void setlCTNCODE(String lCTNCODE) {
        this.lCTNCODE = lCTNCODE;
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

    public int getcHGESLRY() {
        return cHGESLRY;
    }

    public void setcHGESLRY(int cHGESLRY) {
        this.cHGESLRY = cHGESLRY;
    }

    public String getnOTETEXT() {
        return nOTETEXT;
    }

    public void setnOTETEXT(String nOTETEXT) {
        this.nOTETEXT = nOTETEXT;
    }

    public int getaCCERGHT() {
        return aCCERGHT;
    }

    public void setaCCERGHT(int aCCERGHT) {
        this.aCCERGHT = aCCERGHT;
    }

    public int getsTTESIGN() {
        return sTTESIGN;
    }

    public void setsTTESIGN(int sTTESIGN) {
        this.sTTESIGN = sTTESIGN;
    }

    public String getsTTENAME() {
        return sTTENAME;
    }

    public void setsTTENAME(String sTTENAME) {
        this.sTTENAME = sTTENAME;
    }

    public List<SwithshiftDetailRequest> getDetailRequests() {
        return detailRequests;
    }

    public void setDetailRequests(List<SwithshiftDetailRequest> detailRequests) {
        this.detailRequests = detailRequests;
    }
}
