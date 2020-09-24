package com.firstems.erp.api.model.response.bill_payment;

import com.google.gson.annotations.SerializedName;

public class AddNewPaymentItem {
    @SerializedName("KKKK0000")
    private String keyCode;
    
    public AddNewPaymentItem() {
    }
    
    public String getKeyCode() {
        return keyCode;
    }
    
    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
}
