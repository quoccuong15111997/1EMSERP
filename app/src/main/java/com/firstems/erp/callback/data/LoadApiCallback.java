package com.firstems.erp.callback.data;

/**
 * Created by Nguyen Quoc Cuong on 8/11/2020.
 */
public interface LoadApiCallback {
    void onApiLoadSuccess(DataApiCallback dataApiCallback);
    void onApiLoadFail();
}
