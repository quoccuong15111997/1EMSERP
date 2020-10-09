package com.firstems.erp.api.model.response.product;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgressProductDetailApiResponse extends ApiResponse {
    @SerializedName("RETNDATA")
    private List<ProgressProductDetailItem> progressProductDetailItems;

    public ProgressProductDetailApiResponse() {
    }

    public List<ProgressProductDetailItem> getProgressProductDetailItems() {
        return progressProductDetailItems;
    }

    public void setProgressProductDetailItems(List<ProgressProductDetailItem> progressProductDetailItems) {
        this.progressProductDetailItems = progressProductDetailItems;
    }
}
