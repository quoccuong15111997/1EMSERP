package com.firstems.erp.api.model.response.approved;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class ApprovedItemApiResponse implements Serializable {
    @SerializedName("DCMNCODE")
    private String dCMNCODE;
    @SerializedName("DCMNNAME")
    private String dCMNNAME;
    @SerializedName("NUMBVCHR")
    private int nUMBVCHR;
    @SerializedName("ACCERGHT")
    private int aCCERGHT;
    @SerializedName("STTESIGN")
    private int sTTESIGN;
    @SerializedName("STTENAME")
    private String sTTENAME;
    @SerializedName("DETAIL_1")
    private List<ApprovedItemDetail_1> approvedItemDetail_1List;

    public ApprovedItemApiResponse() {
    }

    public String getdCMNCODE() {
        return dCMNCODE;
    }

    public void setdCMNCODE(String dCMNCODE) {
        this.dCMNCODE = dCMNCODE;
    }

    public String getdCMNNAME() {
        return dCMNNAME;
    }

    public void setdCMNNAME(String dCMNNAME) {
        this.dCMNNAME = dCMNNAME;
    }

    public int getnUMBVCHR() {
        return nUMBVCHR;
    }

    public void setnUMBVCHR(int nUMBVCHR) {
        this.nUMBVCHR = nUMBVCHR;
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

    public List<ApprovedItemDetail_1> getApprovedItemDetail_1List() {
        return approvedItemDetail_1List;
    }

    public void setApprovedItemDetail_1List(List<ApprovedItemDetail_1> approvedItemDetail_1List) {
        this.approvedItemDetail_1List = approvedItemDetail_1List;
    }
    public int getItemCount(){
        int res = 0;
        if (approvedItemDetail_1List!=null){
            for (ApprovedItemDetail_1 approvedItemDetail_1 : approvedItemDetail_1List){
                res += approvedItemDetail_1.getApprovedItemDetail_2List().size();
            }
        }
        return res;
    }
}