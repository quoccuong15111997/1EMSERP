package com.firstems.erp.api.model.request;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 7/31/2020.
 */
public class ApproveInfoRequest implements Serializable, ApiBody {
    private String dcmnCode;
    private String keyCode;

    public ApproveInfoRequest() {
    }

    public ApproveInfoRequest(String dcmnCode, String keyCode) {
        this.dcmnCode = dcmnCode;
        this.keyCode = keyCode;
    }

    public String getDcmnCode() {
        return dcmnCode;
    }

    public void setDcmnCode(String dcmnCode) {
        this.dcmnCode = dcmnCode;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("DCMNCODE",dcmnCode);
        jsonObject.addProperty("KEY_CODE",keyCode);
        return jsonObject;
    }
}
