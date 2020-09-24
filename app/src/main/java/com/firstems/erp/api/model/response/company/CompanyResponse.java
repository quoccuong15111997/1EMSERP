package com.firstems.erp.api.model.response.company;

import androidx.annotation.StyleableRes;

import com.firstems.erp.api.model.response.location.LocationResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public class CompanyResponse implements Serializable {
    @SerializedName("COMPCODE")
    private String companyCode;
    @SerializedName("COMPNAME")
    private String companyName;
    @SerializedName("LCTNLIST")
    private List<LocationResponse> locationList;

    public List<LocationResponse> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationResponse> locationList) {
        this.locationList = locationList;
    }

    public CompanyResponse() {
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
