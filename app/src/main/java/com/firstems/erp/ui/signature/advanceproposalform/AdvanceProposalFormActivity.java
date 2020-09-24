package com.firstems.erp.ui.signature.advanceproposalform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstems.erp.R;
import com.firstems.erp.helper.animation.AnimationHelper;

public class AdvanceProposalFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_proposal_form);
        AnimationHelper.getInstance().setAnimationRightToLeft(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new AdvanceProposalFormFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimationHelper.getInstance().setAnimationLeftToRight(this);
    }
}