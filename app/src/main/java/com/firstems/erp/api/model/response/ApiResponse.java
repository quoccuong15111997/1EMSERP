package com.firstems.erp.api.model.response;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public class ApiResponse extends BaseObservable implements Serializable {
    @SerializedName("RETNCODE")
    private boolean RETNCODE;
    @SerializedName("RETNDATE")
    private String RETNDATE;
    @SerializedName("RETNMSSG")
    private String RETNMSSG;

    public ApiResponse() {
    }

    public ApiResponse(boolean RETNCODE, String RETNDATE, String RETNMSSG) {
        this.RETNCODE = RETNCODE;
        this.RETNDATE = RETNDATE;
        this.RETNMSSG = RETNMSSG;
    }

    public boolean isRETNCODE() {
        return RETNCODE;
    }

    public void setRETNCODE(boolean RETNCODE) {
        this.RETNCODE = RETNCODE;
    }

    public String getRETNDATE() {
        return RETNDATE;
    }

    public void setRETNDATE(String RETNDATE) {
        this.RETNDATE = RETNDATE;
    }

    public String getRETNMSSG() {
        return RETNMSSG;
    }

    public void setRETNMSSG(String RETNMSSG) {
        this.RETNMSSG = RETNMSSG;
    }
}
