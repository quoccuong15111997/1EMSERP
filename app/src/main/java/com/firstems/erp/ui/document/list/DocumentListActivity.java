package com.firstems.erp.ui.document.list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;

public class DocumentListActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_list);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_document_list, new DocumnetListFragment()).commit();
    }
}