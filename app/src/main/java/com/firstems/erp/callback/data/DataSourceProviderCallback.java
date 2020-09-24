package com.firstems.erp.callback.data;

/**
 * Created by Nguyen Quoc Cuong on 8/11/2020.
 */
public interface DataSourceProviderCallback {
    void onDataSource(String data);
    void onUpdateImage(int status);
}
