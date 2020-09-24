package com.firstems.erp.api.model.response.approved;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class ApprovedItemDetail_1 implements Serializable {
    @SerializedName("DCMNCODE")
    private String dCMNCODE;
    @SerializedName("SCTNCODE")
    private String sCTNCODE;
    @SerializedName("SCTNNAME")
    private String sCTNNAME;
    @SerializedName("DETAIL_2")
    private List<ApprovedItemDetail_2> approvedItemDetail_2List;

    public ApprovedItemDetail_1() {
    }

    public String getdCMNCODE() {
        return dCMNCODE;
    }

    public void setdCMNCODE(String dCMNCODE) {
        this.dCMNCODE = dCMNCODE;
    }

    public String getsCTNCODE() {
        return sCTNCODE;
    }

    public void setsCTNCODE(String sCTNCODE) {
        this.sCTNCODE = sCTNCODE;
    }

    public String getsCTNNAME() {
        return sCTNNAME;
    }

    public void setsCTNNAME(String sCTNNAME) {
        this.sCTNNAME = sCTNNAME;
    }

    public List<ApprovedItemDetail_2> getApprovedItemDetail_2List() {
        return approvedItemDetail_2List;
    }

    public void setApprovedItemDetail_2List(List<ApprovedItemDetail_2> approvedItemDetail_2List) {
        this.approvedItemDetail_2List = approvedItemDetail_2List;
    }
}
