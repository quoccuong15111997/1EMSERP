package com.firstems.erp.ui.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class ChartViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ChartViewFragment()).commit();
    }
}