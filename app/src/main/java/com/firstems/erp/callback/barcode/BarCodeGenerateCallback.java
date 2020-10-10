package com.firstems.erp.callback.barcode;

import android.graphics.Bitmap;

public interface BarCodeGenerateCallback {
    void onSuccess(Bitmap bitmap);
    void onFail(String mess);
}
