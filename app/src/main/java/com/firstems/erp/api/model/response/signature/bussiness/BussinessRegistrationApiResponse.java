package com.firstems.erp.api.model.response.signature.bussiness;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/19/2020.
 */
public class BussinessRegistrationApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<BussinessRegstItem> bussinessRegstItems;

    public BussinessRegistrationApiResponse() {
    }

    public List<BussinessRegstItem> getBussinessRegstItems() {
        return bussinessRegstItems;
    }

    public void setBussinessRegstItems(List<BussinessRegstItem> bussinessRegstItems) {
        this.bussinessRegstItems = bussinessRegstItems;
    }
}
