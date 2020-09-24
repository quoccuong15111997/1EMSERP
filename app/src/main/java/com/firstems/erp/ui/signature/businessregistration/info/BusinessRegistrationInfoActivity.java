package com.firstems.erp.ui.signature.businessregistration.info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class BusinessRegistrationInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_registration_info);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new BusinessRegistrationInfoFragment()).commit();
    }
}