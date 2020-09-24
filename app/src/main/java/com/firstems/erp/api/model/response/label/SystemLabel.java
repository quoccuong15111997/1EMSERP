package com.firstems.erp.api.model.response.label;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SystemLabel implements Serializable {
    @SerializedName("ITEMCODE")
    private int iteCode;
    @SerializedName("ITEMNAME")
    private String itemName;
    
    public SystemLabel(int iteCode, String itemName) {
        this.iteCode = iteCode;
        this.itemName = itemName;
    }
    
    public SystemLabel() {
    }
    
    public int getIteCode() {
        return iteCode;
    }
    
    public void setIteCode(int iteCode) {
        this.iteCode = iteCode;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
