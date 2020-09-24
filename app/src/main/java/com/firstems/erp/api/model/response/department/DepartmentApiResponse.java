package com.firstems.erp.api.model.response.department;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/4/2020.
 */
public class DepartmentApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<DepartmentItemApiResponse> departmentItemApiResponses;

    public DepartmentApiResponse() {
    }

    public List<DepartmentItemApiResponse> getDepartmentItemApiResponses() {
        return departmentItemApiResponses;
    }

    public void setDepartmentItemApiResponses(List<DepartmentItemApiResponse> departmentItemApiResponses) {
        this.departmentItemApiResponses = departmentItemApiResponses;
    }
}
