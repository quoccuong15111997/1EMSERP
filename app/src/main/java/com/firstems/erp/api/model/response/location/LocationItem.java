package com.firstems.erp.api.model.response.location;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationItem implements Serializable {
    @SerializedName("ITEMCODE")
    private String itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;
    
    public LocationItem() {
    }
    
    public String getItemCode() {
        return itemCode;
    }
    
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
    public LocationItem(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    @Override
    public String toString() {
        return itemName;
    }
}
