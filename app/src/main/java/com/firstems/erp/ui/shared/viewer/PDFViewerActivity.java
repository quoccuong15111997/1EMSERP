package com.firstems.erp.ui.shared.viewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class PDFViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_viewer);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_pdf_view,new PDFViewerFragment())
                .commit();
    }
}