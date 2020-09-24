package com.firstems.erp.ui.shared.loaidoituongnhan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;

public class DoiTuongNhanActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_doi_tuong_nhan);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_loai_doi_tuong_nhan, new DoiTuongNhanFragment())
                .commit();
    }
}