package com.firstems.erp.api.model.request.document;

import com.firstems.erp.api.model.request.ApiBody;
import com.google.gson.JsonObject;

import java.io.Serializable;

public class DocumentRequest implements Serializable, ApiBody {
    private String queryString;
    private String dcmnCode;
    
    public String getDcmnCode() {
        return dcmnCode;
    }
    
    public void setDcmnCode(String dcmnCode) {
        this.dcmnCode = dcmnCode;
    }
    
    public DocumentRequest(String queryString) {
        this.queryString = queryString;
    }
    
    public DocumentRequest() {
    }
    
    public String getQueryString() {
        return queryString;
    }
    
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    
    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("DCMNCODE", dcmnCode);
        jsonObject.addProperty("CONDFLTR",queryString);
        return jsonObject;
    }
}
