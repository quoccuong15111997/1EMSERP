package com.firstems.erp.ui.signature.billpaymentrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;
import com.firstems.erp.helper.animation.AnimationHelper;

public class BillPaymentRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment_request);
        AnimationHelper.getInstance().setAnimationRightToLeft(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new BillPaymentRequestFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimationHelper.getInstance().setAnimationLeftToRight(this);
    }
}