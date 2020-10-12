package com.firstems.erp.api.model.request.progress;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProgressProductRequest implements Serializable {
    @SerializedName("HEADER")
    private List<ProgressProductHeader> progressProductHeaderList;

    public ProgressProductRequest(List<ProgressProductHeader> progressProductHeaderList) {
        this.progressProductHeaderList = progressProductHeaderList;
    }

    public List<ProgressProductHeader> getProgressProductHeaderList() {
        return progressProductHeaderList;
    }

    public ProgressProductRequest() {
    }

    public void setProgressProductHeaderList(List<ProgressProductHeader> progressProductHeaderList) {
        this.progressProductHeaderList = progressProductHeaderList;
    }
}
