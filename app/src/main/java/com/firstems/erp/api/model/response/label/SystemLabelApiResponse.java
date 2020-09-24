package com.firstems.erp.api.model.response.label;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SystemLabelApiResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<SystemLabel> systemLabelList;
    
    public SystemLabelApiResponse() {
    }
    
    public List<SystemLabel> getSystemLabelList() {
        return systemLabelList;
    }
    
    public void setSystemLabelList(List<SystemLabel> systemLabelList) {
        this.systemLabelList = systemLabelList;
    }
}
