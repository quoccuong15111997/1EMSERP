package com.firstems.erp.ui.shared.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_employee, new EmployeeFragment()).commit();
    }
}