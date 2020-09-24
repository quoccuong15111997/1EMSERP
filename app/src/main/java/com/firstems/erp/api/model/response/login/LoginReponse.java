package com.firstems.erp.api.model.response.login;

import com.firstems.erp.api.model.response.ApiResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class LoginReponse extends ApiResponse {
    @SerializedName("RETNDATA")
    private SystemLoginApiResponse systemLoginApiResponse;

    public LoginReponse() {
    }

    public SystemLoginApiResponse getSystemLoginApiResponse() {
        return systemLoginApiResponse;
    }

    public void setSystemLoginApiResponse(SystemLoginApiResponse systemLoginApiResponse) {
        this.systemLoginApiResponse = systemLoginApiResponse;
    }
}
