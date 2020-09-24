package com.firstems.erp.api.model.response.runcode;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/7/2020.
 */
public class RunCodeUpDateApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<RunCodeUpDateItemApiResponse> runCodeUpDateItemApiResponses;

    public RunCodeUpDateApiResponse() {
    }

    public List<RunCodeUpDateItemApiResponse> getRunCodeUpDateItemApiResponses() {
        return runCodeUpDateItemApiResponses;
    }

    public void setRunCodeUpDateItemApiResponses(List<RunCodeUpDateItemApiResponse> runCodeUpDateItemApiResponses) {
        this.runCodeUpDateItemApiResponses = runCodeUpDateItemApiResponses;
    }
}
