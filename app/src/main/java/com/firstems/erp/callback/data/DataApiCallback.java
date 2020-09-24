package com.firstems.erp.callback.data;

/**
 * Created by Nguyen Quoc Cuong on 8/11/2020.
 */
public interface DataApiCallback {
    void onDataApi(String jsonAPI);
    void onApiLoadFail(String mess);
}
