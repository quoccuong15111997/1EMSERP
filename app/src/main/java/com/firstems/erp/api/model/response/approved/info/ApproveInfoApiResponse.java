package com.firstems.erp.api.model.response.approved.info;

import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/31/2020.
 */
public class ApproveInfoApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<ApproveInfoItemApiResponse> responseList;

    public ApproveInfoApiResponse() {
    }

    public List<ApproveInfoItemApiResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<ApproveInfoItemApiResponse> responseList) {
        this.responseList = responseList;
    }
}
