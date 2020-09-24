package com.firstems.erp.ui.document.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;

public class DocumnetDetailActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documnet_detail);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_document_detail, new DocumentDetailFragment()).commit();
    }
}