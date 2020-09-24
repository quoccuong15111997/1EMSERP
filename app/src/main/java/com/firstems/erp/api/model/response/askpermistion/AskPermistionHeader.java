package com.firstems.erp.api.model.response.askpermistion;

import androidx.annotation.StyleableRes;

import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.common.Util;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class AskPermistionHeader {
    @SerializedName("COMPCODE")
    public String cOMPCODE;
    @SerializedName("LCTNCODE")
    public String lCTNCODE;
    @SerializedName("MAINCODE")
    public String mAINCODE;
    @SerializedName("MAINDATE")
    public String mAINDATE;
    @SerializedName("MEXLNNTE")
    public String mEXLNNTE;
    @SerializedName("SUMLEAV")
    public double sUMLEAV;
    @SerializedName("ACCERGHT")
    public int aCCERGHT;
    @SerializedName("STTESIGN")
    public int sTTESIGN;
    @SerializedName("STTENAME")
    public String sTTENAME;
    @SerializedName("KKKK0000")
    private String keyCode;
    @SerializedName("DETAIL")
    public List<AskPermistionDetail> askPermistionDetails;

    public AskPermistionHeader() {
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getcOMPCODE() {
        return cOMPCODE;
    }

    public void setcOMPCODE(String cOMPCODE) {
        this.cOMPCODE = cOMPCODE;
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

    public String getmEXLNNTE() {
        return mEXLNNTE;
    }

    public void setmEXLNNTE(String mEXLNNTE) {
        this.mEXLNNTE = mEXLNNTE;
    }

    public double getsUMLEAV() {
        return sUMLEAV;
    }

    public void setsUMLEAV(double sUMLEAV) {
        this.sUMLEAV = sUMLEAV;
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

    public List<AskPermistionDetail> getAskPermistionDetails() {
        return askPermistionDetails;
    }

    public void setAskPermistionDetails(List<AskPermistionDetail> askPermistionDetails) {
        this.askPermistionDetails = askPermistionDetails;
    }
    public String getMainDate(){
        String date = mAINDATE;
        try{
            date = Util.formatDate(mAINDATE);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return date;
    }
}
