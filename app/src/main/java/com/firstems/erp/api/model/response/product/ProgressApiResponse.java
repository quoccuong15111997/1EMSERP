package com.firstems.erp.api.model.response.product;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgressApiResponse extends ApiResponse {
    @SerializedName("RETNDATA")
    private List<ProgressItem> progressItems;

    public ProgressApiResponse() {
    }

    public List<ProgressItem> getProgressItems() {
        return progressItems;
    }

    public void setProgressItems(List<ProgressItem> progressItems) {
        this.progressItems = progressItems;
    }
}
