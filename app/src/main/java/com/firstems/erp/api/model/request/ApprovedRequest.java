package com.firstems.erp.api.model.request;

import com.google.gson.JsonObject;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class ApprovedRequest implements ApiBody {
    private String beginDate;
    private String endDate;

    public ApprovedRequest(String beginDate, String endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public ApprovedRequest() {
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("BEG_DATE",beginDate);
        jsonObject.addProperty("END_DATE", endDate);
        return jsonObject;
    }
}
