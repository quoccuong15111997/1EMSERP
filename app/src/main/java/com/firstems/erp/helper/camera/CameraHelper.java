package com.firstems.erp.helper.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

/**
 * Created by Nguyen Quoc Cuong on 7/17/2020.
 */
public class CameraHelper {
    private static CameraHelper instance;
    private CameraHelper(){

    }
    public static CameraHelper getInstance(){
        if (instance==null){
            instance= new CameraHelper();
        }
        return instance;
    }
    public boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        } else {
            return false;
        }
    }
    public Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        }
        catch (Exception e){
        }
        return c;
    }
}
