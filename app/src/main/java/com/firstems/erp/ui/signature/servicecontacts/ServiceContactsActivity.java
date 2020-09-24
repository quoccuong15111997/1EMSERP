package com.firstems.erp.ui.signature.servicecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;
import com.firstems.erp.helper.animation.AnimationHelper;

public class ServiceContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_contacts);
        AnimationHelper.getInstance().setAnimationRightToLeft(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ServiceContactsFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimationHelper.getInstance().setAnimationLeftToRight(this);
    }
}