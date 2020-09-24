package com.firstems.erp.helper.permision;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by Nguyen Quoc Cuong on 7/17/2020.
 */
public class PermissionProvider {
    private static PermissionProvider instance;
    private PermissionProvider(){
    }
    public static PermissionProvider getInstance(){
        if (instance==null){
            instance= new PermissionProvider();
        }
        return instance;
    }
    public boolean requestPermissionW(String permission, int requestCode, Activity context){
        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(context,
                    new String[] { permission },
                    requestCode);
            return false;
        }
        else {
            return true;
        }
    }
}
