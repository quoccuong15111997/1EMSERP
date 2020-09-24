package com.firstems.erp.api.model.response.timekeeping;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class TimekeepingTypeCTApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<TimekeepingTypeCT> timekeepingTypeCTList;

    public TimekeepingTypeCTApiResponse() {
    }

    public List<TimekeepingTypeCT> getTimekeepingTypeCTList() {
        return timekeepingTypeCTList;
    }

    public void setTimekeepingTypeCTList(List<TimekeepingTypeCT> timekeepingTypeCTList) {
        this.timekeepingTypeCTList = timekeepingTypeCTList;
    }
}
