package com.firstems.erp.api.model.response.runcode;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/7/2020.
 */
public class RunCodeUpDateItemApiResponse implements Serializable {
    @SerializedName("COMPCODE")
    private String comCode;
    @SerializedName("ITEMCODE")
    private String itemCode;
    @SerializedName("ITEMUPDT")
    private int statusUpdate;

    public RunCodeUpDateItemApiResponse() {
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(int statusUpdate) {
        this.statusUpdate = statusUpdate;
    }
}