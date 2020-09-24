package com.firstems.erp.ui.company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class SelectCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_select_company, new SelectCompanyFragment()).commit();
    }
}