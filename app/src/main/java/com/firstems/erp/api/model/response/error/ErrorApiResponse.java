package com.firstems.erp.api.model.response.error;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorApiResponse extends ApiResponse {
    @SerializedName("RETNDATA")
    private List<ErrorItem> errorItemList;

    public ErrorApiResponse() {
    }

    public List<ErrorItem> getErrorItemList() {
        return errorItemList;
    }

    public void setErrorItemList(List<ErrorItem> errorItemList) {
        this.errorItemList = errorItemList;
    }
}
