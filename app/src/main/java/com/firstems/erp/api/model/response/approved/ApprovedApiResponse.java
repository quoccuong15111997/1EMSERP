package com.firstems.erp.api.model.response.approved;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class ApprovedApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<ApprovedItemApiResponse> approvedItemApiResponses;

    public ApprovedApiResponse() {
    }

    public List<ApprovedItemApiResponse> getApprovedItemApiResponses() {
        return approvedItemApiResponses;
    }

    public void setApprovedItemApiResponses(List<ApprovedItemApiResponse> approvedItemApiResponses) {
        this.approvedItemApiResponses = approvedItemApiResponses;
    }
}
