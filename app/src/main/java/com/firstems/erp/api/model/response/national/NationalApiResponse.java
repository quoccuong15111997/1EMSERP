package com.firstems.erp.api.model.response.national;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class NationalApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<National> nationalList;

    public NationalApiResponse() {
    }

    public List<National> getNationalList() {
        return nationalList;
    }

    public void setNationalList(List<National> nationalList) {
        this.nationalList = nationalList;
    }
}
