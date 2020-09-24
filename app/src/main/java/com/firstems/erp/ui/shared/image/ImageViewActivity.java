package com.firstems.erp.ui.shared.image;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_image_view,new ViewImageFragment())
                .commit();
    }
}