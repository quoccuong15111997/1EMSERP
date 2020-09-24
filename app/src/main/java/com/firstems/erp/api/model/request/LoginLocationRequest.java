package com.firstems.erp.api.model.request;

import com.google.gson.JsonObject;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class LoginLocationRequest implements ApiBody{
    private String comCode;
    private String loctCode;

    public LoginLocationRequest() {
    }

    public LoginLocationRequest(String comCode, String loctCode) {
        this.comCode = comCode;
        this.loctCode = loctCode;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getLoctCode() {
        return loctCode;
    }

    public void setLoctCode(String loctCode) {
        this.loctCode = loctCode;
    }

    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("COMPCODE",comCode);
        jsonObject.addProperty("LCTNCODE",loctCode);
        return jsonObject;
    }
}
