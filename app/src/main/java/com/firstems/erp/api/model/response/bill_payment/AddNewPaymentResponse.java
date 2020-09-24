package com.firstems.erp.api.model.response.bill_payment;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddNewPaymentResponse extends ApiResponse implements Serializable {
    @SerializedName("RETNDATA")
    private List<AddNewPaymentItem> addNewPaymentItems;
    
    public AddNewPaymentResponse() {
    }
    
    public List<AddNewPaymentItem> getAddNewPaymentItems() {
        return addNewPaymentItems;
    }
    
    public void setAddNewPaymentItems(List<AddNewPaymentItem> addNewPaymentItems) {
        this.addNewPaymentItems = addNewPaymentItems;
    }
}
