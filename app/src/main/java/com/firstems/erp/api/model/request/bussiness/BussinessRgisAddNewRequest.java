package com.firstems.erp.api.model.request.bussiness;

import com.firstems.erp.api.model.request.ApiBody;
import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegstItem;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import retrofit2.http.Header;

/**
 * Created by Nguyen Quoc Cuong on 8/20/2020.
 */
public class BussinessRgisAddNewRequest implements Serializable, ApiBody {
    @SerializedName("HEADER")
    private List<BussinessRegstItem> bussinessRegstItems;

    public BussinessRgisAddNewRequest() {
    }

    public List<BussinessRegstItem> getBussinessRegstItems() {
        return bussinessRegstItems;
    }

    public void setBussinessRegstItems(List<BussinessRegstItem> bussinessRegstItems) {
        this.bussinessRegstItems = bussinessRegstItems;
    }

    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("HEADER",new Gson().toJson(bussinessRegstItems));
        return jsonObject;
    }

}
