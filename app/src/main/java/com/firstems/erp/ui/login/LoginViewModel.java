package com.firstems.erp.ui.login;

import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.request.SystemLoginRequest;
import com.firstems.erp.api.model.response.login.LoginReponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.LoginCallback;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public LoginViewModel(){
    }

    public void runLogin(String username, String password, LoginCallback loginCallback) {
        SystemLoginRequest systemLoginRequest= new SystemLoginRequest();
        systemLoginRequest.setUserLogin(username);
        systemLoginRequest.setPassword(password);
        systemLoginRequest.setTokenDevice(SharedPreferencesManager.getFCMToken());

        ApiServices.getInstance().doSystemLogin(systemLoginRequest.convertToJson(), new Callback<LoginReponse>() {
            @Override
            public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                if (response.isSuccessful()){
                    LoginReponse loginReponse = response.body();
                    if (loginReponse.isRETNCODE()){
                        System.out.println("Fisrt TOKEN: "+loginReponse.getSystemLoginApiResponse().getToken());
                        loginCallback.onLoginSuccess(loginReponse.getSystemLoginApiResponse());
                    }
                    else
                        loginCallback.onLoginFail(loginReponse);
                }
                else {
                
                }
            }

            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                loginCallback.onServerFail();
            }
        });
    }
}