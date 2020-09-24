package com.firstems.erp.ui.more.password;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextView txtTitle;
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        addControls();
        addEvents();
    }
    
    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    
    private void addControls() {
        txtTitle = findViewById(R.id.txtTitle);
        imgBack = findViewById(R.id.imgBack);
        txtTitle.setText("Đổi mật khẩu");
    }
}