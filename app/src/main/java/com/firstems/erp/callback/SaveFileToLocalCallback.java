package com.firstems.erp.callback;

public interface SaveFileToLocalCallback {
    void onSaveSuccess(String path);
    void onSaveFail(String mess);
}
