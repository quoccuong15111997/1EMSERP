package com.firstems.erp.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;

public class HomeActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //  getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, new HomeFragment()).commit();
    }
}