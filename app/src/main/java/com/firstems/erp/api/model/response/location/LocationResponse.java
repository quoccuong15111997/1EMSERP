package com.firstems.erp.api.model.response.location;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public class LocationResponse implements Serializable {
    @SerializedName("LCTNCODE")
    private String locationCode;
    @SerializedName("LCTNNAME")
    private String locationName;
    
    public LocationResponse() {
    }
    
    public String getLocationCode() {
        return locationCode;
    }
    
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    @Override
    public String toString() {
        return locationName;
    }
}
