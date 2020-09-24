package com.firstems.erp.ui.more.report;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class ReporttingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportting);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ReportingFragment()).commit();
    }
}