package com.firstems.erp.helper.barcode;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.firstems.erp.R;
import com.firstems.erp.callback.barcode.BarCodeGenerateCallback;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BarCodeHelper {
    private static BarCodeHelper instance;
    private BarCodeHelper(){

    }
    public static BarCodeHelper getInstance(){
        if (instance== null){
            instance = new BarCodeHelper();
        }
        return instance;
    }
    public void generate(String data, BarCodeGenerateCallback barCodeGenerateCallback){
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, 800, 800);
            barCodeGenerateCallback.onSuccess(bitmap);
        } catch(Exception e) {
            barCodeGenerateCallback.onFail(e.getMessage());
        }
    }
    public Bitmap generateBarCode(String data){
        Bitmap bitmap =null;
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, 800, 800);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
