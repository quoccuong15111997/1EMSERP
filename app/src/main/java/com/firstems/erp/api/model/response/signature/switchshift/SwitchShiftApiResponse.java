package com.firstems.erp.api.model.response.signature.switchshift;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/21/2020.
 */
public class SwitchShiftApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<SwitchShiftItem> switchShiftItems;

    public SwitchShiftApiResponse() {
    }

    public List<SwitchShiftItem> getSwitchShiftItems() {
        return switchShiftItems;
    }

    public void setSwitchShiftItems(List<SwitchShiftItem> switchShiftItems) {
        this.switchShiftItems = switchShiftItems;
    }
}
