package com.firstems.erp.api.model.response.province;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class ProvinceApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<Province> provinceList;

    public ProvinceApiResponse() {
    }

    public List<Province> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }
}
