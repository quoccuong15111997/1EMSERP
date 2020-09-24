package com.firstems.erp.api.model.response.national;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class National extends BaseObservable implements Serializable {
    @SerializedName("ITEMCODE")
    private String itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public National() {
    }

    public National(String itemCode) {
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
}
