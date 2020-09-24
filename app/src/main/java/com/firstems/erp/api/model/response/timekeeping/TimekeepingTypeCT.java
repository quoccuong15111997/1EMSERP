package com.firstems.erp.api.model.response.timekeeping;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class TimekeepingTypeCT implements Serializable {
    @SerializedName("ITEMCODE")
    private String itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;

    public TimekeepingTypeCT() {
    }

    public TimekeepingTypeCT(String itemCode) {
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
