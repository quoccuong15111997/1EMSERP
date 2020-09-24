package com.firstems.erp.api.model.request;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/7/2020.
 */
public class RunCodeUpDateBody implements Serializable, ApiBody {
    private String comCode;

    public RunCodeUpDateBody(String comCode) {
        this.comCode = comCode;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public RunCodeUpDateBody() {
    }

    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("COMPCODE",comCode);

        return jsonObject;
    }
}
