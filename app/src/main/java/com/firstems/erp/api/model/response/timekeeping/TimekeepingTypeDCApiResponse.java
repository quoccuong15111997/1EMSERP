package com.firstems.erp.api.model.response.timekeeping;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class TimekeepingTypeDCApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<TimekeepingTypeDC> timekeepingTypeDCList;

    public TimekeepingTypeDCApiResponse() {

    }

    public List<TimekeepingTypeDC> getTimekeepingTypeDCList() {
        return timekeepingTypeDCList;
    }

    public void setTimekeepingTypeDCList(List<TimekeepingTypeDC> timekeepingTypeDCList) {
        this.timekeepingTypeDCList = timekeepingTypeDCList;
    }
}
