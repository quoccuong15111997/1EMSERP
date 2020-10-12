package com.firstems.erp.api.model.request.progress;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgressProductHeader {
    @SerializedName("COMPCODE")
    private String compcode;

    @SerializedName("LCTNCODE")
    private String lctncode;

    @SerializedName("DETAIL")
    private List<ProgressProductDetail> progressProductDetailList;

    public void setCompcode(String compcode) {
        this.compcode = compcode;
    }


    public List<ProgressProductDetail> getProgressProductDetailList() {
        return progressProductDetailList;
    }

    public void setProgressProductDetailList(List<ProgressProductDetail> progressProductDetailList) {
        this.progressProductDetailList = progressProductDetailList;
    }

    public ProgressProductHeader() {
    }

    public void setLctncode(String lctncode) {
        this.lctncode = lctncode;
    }

    public String getCompcode() {
        return compcode;
    }

    public String getLctncode() {
        return lctncode;
    }

}
