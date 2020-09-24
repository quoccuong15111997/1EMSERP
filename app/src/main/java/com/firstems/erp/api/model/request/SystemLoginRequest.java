package com.firstems.erp.api.model.request;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public class SystemLoginRequest implements Serializable, ApiBody {
    private String userLogin;
    private String password;
    private String loginType;
    private String phoneNumber;
    private String tokenDevice;

    public SystemLoginRequest() {
    }

    public SystemLoginRequest(String userLogin, String password, String loginType, String phoneNumber, String tokenDevice) {
        this.userLogin = userLogin;
        this.password = password;
        this.loginType = loginType;
        this.phoneNumber = phoneNumber;
        this.tokenDevice = tokenDevice;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTokenDevice() {
        return tokenDevice;
    }

    public void setTokenDevice(String tokenDevice) {
        this.tokenDevice = tokenDevice;
    }

    @Override
    public JsonObject convertToJson() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("USERLGIN",userLogin);
        jsonObject.addProperty("PASSWORD",password);
        jsonObject.addProperty("LGINTYPE",loginType);
        jsonObject.addProperty("PHONNUMB",phoneNumber);
        jsonObject.addProperty("TKENDEVC",tokenDevice);
        return jsonObject;
    }
}
