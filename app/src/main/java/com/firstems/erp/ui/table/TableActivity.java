package com.firstems.erp.ui.table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_table, new DataTableFragmentFragment()).commit();
    }
}