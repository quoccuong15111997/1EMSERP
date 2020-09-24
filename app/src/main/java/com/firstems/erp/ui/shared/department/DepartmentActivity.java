package com.firstems.erp.ui.shared.department;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class DepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_department, new DepartmentFragment()).commit();
    }
}