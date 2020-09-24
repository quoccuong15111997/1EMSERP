package com.firstems.erp.helper.validation;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Nguyen Quoc Cuong on 8/11/2020.
 */
public class ValidationData {
    public static boolean isBlank(TextView textView){
        if (textView.getText().toString().matches("")){
            return true;
        }
        return false;
    }
}
