package com.firstems.erp.ui.shared.province;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class ProvinceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_province, new ProvinceFragment())
                .commit();
    }
}