package com.firstems.erp.api.model.response.location;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LocationApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<LocationItem> locationResponses;
    
    public LocationApiResponse() {
    }
    
    public List<LocationItem> getLocationResponses() {
        return locationResponses;
    }
    
    public void setLocationResponses(List<LocationItem> locationResponses) {
        this.locationResponses = locationResponses;
    }
}
