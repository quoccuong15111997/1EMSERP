package com.firstems.erp.ui.more.password;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextView txtTitle;
    private ImageView imgBack;
    private EditText edtPassOld, edtPassNew, edtPassConfirm;
    private TextView txtPassOld, txtPassNew, txtPassConfirm;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        addControls();
        addEvents();
        setTexts();
    }
    
    private void setTexts() {
        txtPassOld.setText(SharedPreferencesManager.getSystemLabel(208) /*Mật khẩu cũ*/);
        txtPassNew.setText(SharedPreferencesManager.getSystemLabel(209) /*Mật khẩu mới*/);
        txtPassConfirm.setText(SharedPreferencesManager.getSystemLabel(210) /*Xác nhận mật khẩu*/);
        btnSave.setText(SharedPreferencesManager.getSystemLabel(211) /*Cập nhật*/);
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
    
        txtPassOld = findViewById(R.id.txtPassOld);
        txtPassNew = findViewById(R.id.txtPassNew);
        txtPassConfirm = findViewById(R.id.txtPassConfirm);
        
    
        edtPassOld = findViewById(R.id.etPasswordNow);
        edtPassNew = findViewById(R.id.etPasswordNew);
        edtPassConfirm = findViewById(R.id.etPasswordRetype);
        
        btnSave = findViewById(R.id.btnSave);
    }
}