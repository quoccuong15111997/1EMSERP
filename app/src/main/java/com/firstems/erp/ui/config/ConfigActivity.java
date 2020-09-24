package com.firstems.erp.ui.config;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ConfigFragment()).commit();
    }
}