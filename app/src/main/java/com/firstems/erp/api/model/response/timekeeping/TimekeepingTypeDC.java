package com.firstems.erp.api.model.response.timekeeping;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class TimekeepingTypeDC implements Serializable {
    @SerializedName("ITEMCODE")
    private String itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;

    public TimekeepingTypeDC() {
    }

    public TimekeepingTypeDC(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public TimekeepingTypeDC(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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
