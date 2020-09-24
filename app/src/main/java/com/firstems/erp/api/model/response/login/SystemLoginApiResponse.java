package com.firstems.erp.api.model.response.login;

import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.company.CompanyResponse;
import com.firstems.erp.api.model.response.userlogin.UserLogin;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public class SystemLoginApiResponse implements Serializable {
    @SerializedName("TOKEN")
    private String token;
    @SerializedName("EXPIREDTIME")
    private String exTime;
    @SerializedName("LOADLGIN")
    private int loadLogin;
    @SerializedName("COMPLIST")
    private List<CompanyResponse> companyList;
    @SerializedName("APP_RGHT")
    private int appRight;
    @SerializedName("USERLGIN")
    private UserLogin userLogin;

    public SystemLoginApiResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExTime() {
        return exTime;
    }

    public void setExTime(String exTime) {
        this.exTime = exTime;
    }

    public int getLoadLogin() {
        return loadLogin;
    }

    public void setLoadLogin(int loadLogin) {
        this.loadLogin = loadLogin;
    }

    public List<CompanyResponse> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyResponse> companyList) {
        this.companyList = companyList;
    }

    public int getAppRight() {
        return appRight;
    }

    public void setAppRight(int appRight) {
        this.appRight = appRight;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}