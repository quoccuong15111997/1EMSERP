package com.firstems.erp.api.model.request;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/28/2020.
 */
public class EmployeeRequest implements Serializable,ApiBody {
    private String dptmCode;

    public EmployeeRequest(String dptmCode) {
        this.dptmCode = dptmCode;
    }

    public EmployeeRequest() {
    }

    public String getDptmCode() {
        return dptmCode;
    }

    public void setDptmCode(String dptmCode) {
        this.dptmCode = dptmCode;
    }

    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("DPTMCODE",dptmCode);
        return jsonObject;
    }
}
