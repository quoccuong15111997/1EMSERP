package com.firstems.erp.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new DashboardFragment()).commit();
    }
}