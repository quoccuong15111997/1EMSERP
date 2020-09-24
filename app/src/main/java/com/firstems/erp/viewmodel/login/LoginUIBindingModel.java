package com.firstems.erp.viewmodel.login;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class LoginUIBindingModel extends BaseObservable {
    private String username;
    private String password;
    private boolean isRemember;

    public LoginUIBindingModel() {
    }
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Bindable
    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }
}
