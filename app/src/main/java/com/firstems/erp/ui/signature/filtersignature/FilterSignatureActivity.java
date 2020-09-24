package com.firstems.erp.ui.signature.filtersignature;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;
import com.firstems.erp.helper.animation.AnimationHelper;

public class FilterSignatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_signature);
        AnimationHelper.getInstance().setAnimationBottomToTop(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new FilterSignatureFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimationHelper.getInstance().setAnimationTopToBottom(FilterSignatureActivity.this);
    }
}