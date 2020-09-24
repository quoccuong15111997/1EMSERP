package com.firstems.erp.api.model.response.employee;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/4/2020.
 */
public class EmployeeApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<Employee> employeeItemApiResponses;

    public EmployeeApiResponse() {
    }

    public List<Employee> getEmployeeItemApiResponses() {
        return employeeItemApiResponses;
    }

    public void setEmployeeItemApiResponses(List<Employee> employeeItemApiResponses) {
        this.employeeItemApiResponses = employeeItemApiResponses;
    }
}
