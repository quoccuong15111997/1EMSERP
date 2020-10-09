package com.firstems.erp.ui.product.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class ProductProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_progress);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_product_progress, new ProductProgressFragment()).commit();
    }
}