package com.firstems.erp.ui.shared.relatedfields;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class RelatedFieldsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_related_fields);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_related_fields, new RelatedFieldsFragment())
                .commit();
    }
}