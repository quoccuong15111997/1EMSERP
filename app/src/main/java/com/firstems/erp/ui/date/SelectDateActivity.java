package com.firstems.erp.ui.date;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;
import com.firstems.erp.ui.date.SelectDateFragment;

public class SelectDateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new SelectDateFragment()).commit();
    }
}