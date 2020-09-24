package com.firstems.erp.ui.login;

import android.os.Bundle;

import com.firstems.erp.R;
import com.firstems.erp.common.CommonActivity;

public class LoginActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new LoginFragment()).commit();
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}