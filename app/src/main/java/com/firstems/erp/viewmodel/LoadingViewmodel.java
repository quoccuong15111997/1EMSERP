package com.firstems.erp.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class LoadingViewmodel extends BaseObservable {
    private String urlLogo;

    public LoadingViewmodel(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public LoadingViewmodel() {
    }

    @Bindable
    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}