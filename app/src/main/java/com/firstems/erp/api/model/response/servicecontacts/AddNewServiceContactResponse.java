package com.firstems.erp.api.model.response.servicecontacts;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddNewServiceContactResponse extends ApiResponse  implements Serializable {
    @SerializedName("RETNDATA")
    private List<AddNewServiceContactResponseItem> retuenValue;
    
    public AddNewServiceContactResponse() {
    }
    
    public List<AddNewServiceContactResponseItem> getRetuenValue() {
        return retuenValue;
    }
    
    public void setRetuenValue(List<AddNewServiceContactResponseItem> retuenValue) {
        this.retuenValue = retuenValue;
    }
}
