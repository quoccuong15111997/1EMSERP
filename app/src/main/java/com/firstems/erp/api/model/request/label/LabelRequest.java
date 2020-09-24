package com.firstems.erp.api.model.request.label;

import com.firstems.erp.api.model.request.ApiBody;
import com.google.gson.JsonObject;

import java.io.Serializable;

public class LabelRequest implements Serializable, ApiBody {
    private String appCode;
    private String langCode;
    
    public LabelRequest(String appCode, String langCode) {
        this.appCode = appCode;
        this.langCode = langCode;
    }
    
    public LabelRequest() {
    }
    
    public String getAppCode() {
        return appCode;
    }
    
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
    
    public String getLangCode() {
        return langCode;
    }
    
    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }
    
    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("APP_CODE",appCode);
        jsonObject.addProperty("LGGECODE", langCode);
        return jsonObject;
    }
}
