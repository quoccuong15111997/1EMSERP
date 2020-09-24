package com.firstems.erp.callback;

import com.firstems.erp.api.model.response.login.LoginReponse;
import com.firstems.erp.api.model.response.login.SystemLoginApiResponse;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public interface LoginCallback {
    void onLoginSuccess(SystemLoginApiResponse loginApiResponse);
    void onLoginFail(LoginReponse loginReponse);
    void onServerFail();
}
