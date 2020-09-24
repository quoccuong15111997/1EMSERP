package com.firstems.erp.ui.signature.defaultsignature;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;

public class DefaultSignatureActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_signature);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_default_sign, new DefaultSignatureFragment()).commit();
    }
}