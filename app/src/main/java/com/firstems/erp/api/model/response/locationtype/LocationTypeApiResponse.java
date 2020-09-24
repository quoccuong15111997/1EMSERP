package com.firstems.erp.api.model.response.locationtype;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class LocationTypeApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<LocationType> locationTypeList;

    public LocationTypeApiResponse() {
    }

    public List<LocationType> getLocationTypeList() {
        return locationTypeList;
    }

    public void setLocationTypeList(List<LocationType> locationTypeList) {
        this.locationTypeList = locationTypeList;
    }
}
