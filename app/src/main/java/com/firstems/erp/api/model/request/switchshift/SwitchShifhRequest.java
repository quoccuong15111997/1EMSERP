package com.firstems.erp.api.model.request.switchshift;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/24/2020.
 */
public class SwitchShifhRequest {
        @SerializedName("HEADER")
    private List<SwithshiftHeaderRequest> swithshiftHeaderRequests;

    public SwitchShifhRequest() {
    }

    public List<SwithshiftHeaderRequest> getSwithshiftHeaderRequests() {
        return swithshiftHeaderRequests;
    }

    public void setSwithshiftHeaderRequests(List<SwithshiftHeaderRequest> swithshiftHeaderRequests) {
        this.swithshiftHeaderRequests = swithshiftHeaderRequests;
    }
}
