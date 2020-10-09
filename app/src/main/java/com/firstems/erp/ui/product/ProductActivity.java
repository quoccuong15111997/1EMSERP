package com.firstems.erp.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_product, new ProductFragment()).commit();
    }
}