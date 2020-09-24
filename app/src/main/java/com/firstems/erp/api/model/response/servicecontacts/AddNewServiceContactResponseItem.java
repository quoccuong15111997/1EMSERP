package com.firstems.erp.api.model.response.servicecontacts;

import com.google.gson.annotations.SerializedName;

public class AddNewServiceContactResponseItem {
    @SerializedName("KKKK0000")
    private String keyCode;
    
    public AddNewServiceContactResponseItem(String keyCode) {
        this.keyCode = keyCode;
    }
    
    public AddNewServiceContactResponseItem() {
    }
    
    public String getKeyCode() {
        return keyCode;
    }
    
    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
}
