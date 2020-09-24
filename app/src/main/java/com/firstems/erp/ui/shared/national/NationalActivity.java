package com.firstems.erp.ui.shared.national;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class NationalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_national);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_national, new NationalFragment())
                .commit();
    }
}