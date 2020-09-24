package com.firstems.erp.ui.more.help;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_help, new HelpFragment()).commit();
    }
}