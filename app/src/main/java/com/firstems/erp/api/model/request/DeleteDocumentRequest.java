package com.firstems.erp.api.model.request;

import com.google.gson.JsonObject;

/**
 * Created by Nguyen Quoc Cuong on 8/20/2020.
 */
public class DeleteDocumentRequest implements ApiBody{
    private String dcmnCode;
    private String keyCode;

    public DeleteDocumentRequest(String dcmnCode, String keyCode) {
        this.dcmnCode = dcmnCode;
        this.keyCode = keyCode;
    }

    public DeleteDocumentRequest() {
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
