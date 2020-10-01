package com.firstems.erp.loading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firstems.erp.MainActivity;
import com.firstems.erp.R;
import com.firstems.erp.api.model.request.LoginLocationRequest;
import com.firstems.erp.api.model.request.SystemLoginRequest;
import com.firstems.erp.api.model.response.login.LoginReponse;
import com.firstems.erp.api.model.response.login.SystemLoginApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.LoadSystemLabelCallback;
import com.firstems.erp.callback.LoginCallback;
import com.firstems.erp.callback.SaveDataCallback;
import com.firstems.erp.common.CommonActivity;
import com.firstems.erp.common.Constant;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.system.SystemLabelProvider;
import com.firstems.erp.ui.company.SelectCompanyActivity;
import com.firstems.erp.ui.config.ConfigActivity;
import com.firstems.erp.ui.login.LoginActivity;
import com.firstems.erp.viewmodel.LoadingViewmodel;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tuyenmonkey.mkloader.MKLoader;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends CommonActivity {
    private ImageView imgLogo;
    private LoadingViewmodel loadingViewmodel;
    private MKLoader progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        addControls();
        addEvents();
        start();
    }
    private void start() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        initControl();
                    }
                },
                3000);
    }
    
    private void initControl() {
        if (SharedPreferencesManager.getInstance().isFirstSetup()){
            progressBar.setVisibility(View.INVISIBLE);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent intent= new Intent(LoadingActivity.this, ConfigActivity.class);
                            startActivity(intent);
                            setOveridePendingTransisi(LoadingActivity.this);
                            finish();
                        }
                    },
                    100);
        }
        else {
            if (SharedPreferencesManager.getInstance().isSyslabelBaseLoad()){
                System.out.println("Label is emty - Begin download label");
                SystemLabelProvider.getInstance().getDataSystemLabel(new LoadSystemLabelCallback() {
                    @Override
                    public void onLoaded() {
                        startLogin();
                    }
            
                    @Override
                    public void onLoadFail() {
                        progressBar.setVisibility(View.INVISIBLE);
                        showServerIsDead();
                    }
                });
            }
            else {
                startLogin();
            }
        }
    }
    
    private void startLogin() {
        if (SharedPreferencesManager.getInstance().getPrefrRemember()){
            runLogin(SharedPreferencesManager.getInstance().getPrefUsername(), SharedPreferencesManager.getInstance().getPrefPassword(), new LoginCallback() {
                @Override
                public void onLoginSuccess(SystemLoginApiResponse loginApiResponse) {
                    SharedPreferencesManager.getInstance().setPrefToken(loginApiResponse.getToken());
                    if (!SharedPreferencesManager.getInstance().getPrefCompcode().equals("") && !SharedPreferencesManager.getInstance().getPrefLctcode().equals("")){
                        doLoginByLocation(SharedPreferencesManager.getInstance().getPrefCompcode(),SharedPreferencesManager.getInstance().getPrefLctcode());
                    }
                    else {
                        progressBar.setVisibility(View.INVISIBLE);
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        Intent intent= new Intent(LoadingActivity.this, SelectCompanyActivity.class);
                                        intent.putExtra(Constant.NAME_PUT_LIST_COMPANY, (Serializable) loginApiResponse.getCompanyList());
                                        startActivity(intent);
                                        setOveridePendingTransisi(LoadingActivity.this);
                                        finish();
                                    }
                                },
                                100);
                    }
                }
            
                @Override
                public void onLoginFail(LoginReponse loginReponse) {
                
                }
            
                @Override
                public void onServerFail() {
                    showOutTOKEN();
                }
            });
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent intent= new Intent(LoadingActivity.this, LoginActivity.class);
                            startActivity(intent);
                            setOveridePendingTransisi(LoadingActivity.this);
                            finish();
                        }
                    },
                    100);
        }
    }
    
    private void addEvents() {
    }
    
    private void addControls() {
        imgLogo=findViewById(R.id.imgLogo);
        progressBar = findViewById(R.id.progressBar);
        registerNotificationToken();
        initUI();
    }
    
    private void initUI() {
        Glide.with(LoadingActivity.this)
                .load("http://api-dev.firstems.com/Api/data/runApi_File?run_Code=COM001.01.003&Key_Code=EMS001")
                .into(imgLogo);
    }
    private void runLogin(String username, String password, LoginCallback loginCallback) {
        SystemLoginRequest systemLoginRequest= new SystemLoginRequest();
        systemLoginRequest.setUserLogin(username);
        systemLoginRequest.setPassword(password);
        systemLoginRequest.setTokenDevice(SharedPreferencesManager.getFCMToken());
        
        ApiServices.getInstance().doSystemLogin(systemLoginRequest.convertToJson(), new Callback<LoginReponse>() {
            @Override
            public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                if (response.isSuccessful()){
                    LoginReponse loginReponse = response.body();
                    if (loginReponse.isRETNCODE()){
                        System.out.println("Fisrt TOKEN: "+loginReponse.getSystemLoginApiResponse().getToken());
                        loginCallback.onLoginSuccess(loginReponse.getSystemLoginApiResponse());
                    }
                    else
                        loginCallback.onLoginFail(loginReponse);
                }
                else {
                    showOutTOKEN();
                }
            }
            
            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                showOutTOKEN();
            }
        });
    }
    private void saveData(SystemLoginApiResponse loginApiResponse, SaveDataCallback saveDataCallback) {
        if (loginApiResponse.getUserLogin()!=null){
            SharedPreferencesManager.getInstance().setPrefToken(loginApiResponse.getToken());
            SharedPreferencesManager.getInstance().setPrefUsercode(loginApiResponse.getUserLogin().getUserCode());
            SharedPreferencesManager.getInstance().setPrefAccountName(loginApiResponse.getUserLogin().getUserName());
            SharedPreferencesManager.getInstance().setPrefEmpCode(loginApiResponse.getUserLogin().getEmpCode());
            SharedPreferencesManager.getInstance().setPrefPartCode(loginApiResponse.getUserLogin().getPartCode());
            SharedPreferencesManager.getInstance().setPrefPartName(loginApiResponse.getUserLogin().getPartName());
            SharedPreferencesManager.getInstance().setPrefPostCode(loginApiResponse.getUserLogin().getPositionCode());
            SharedPreferencesManager.getInstance().setPrefPostName(loginApiResponse.getUserLogin().getPositionName());
            SharedPreferencesManager.getInstance().setPrefJobCode(loginApiResponse.getUserLogin().getJobCode());
            SharedPreferencesManager.getInstance().setPrefJobName(loginApiResponse.getUserLogin().getJobName());
            SharedPreferencesManager.getInstance().setPrefLogoComp(loginApiResponse.getUserLogin().getLogoCompany());
            saveDataCallback.onSaveComplete();
        }
        else {
            saveDataCallback.onSaveFail();
        }
    }
    private void doLoginByLocation(String compCode, String locationCode) {
        LoginLocationRequest loginLocationRequest= new LoginLocationRequest();
        loginLocationRequest.setComCode(compCode);
        loginLocationRequest.setLoctCode(locationCode);
        
        ApiServices.getInstance().doLocateLogin(SharedPreferencesManager.getInstance().getPrefToken(), loginLocationRequest.convertToJson(), new Callback<LoginReponse>() {
            @Override
            public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                if (response.isSuccessful()){
                    LoginReponse loginReponse= response.body();
                    saveData(loginReponse.getSystemLoginApiResponse(), new SaveDataCallback() {
                        @Override
                        public void onSaveComplete() {
                            progressBar.setVisibility(View.INVISIBLE);
                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            Intent intent= new Intent(LoadingActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            setOveridePendingTransisi(LoadingActivity.this);
                                            finish();
                                        }
                                    },
                                    100);
                        }
                        
                        @Override
                        public void onSaveFail() {
                        
                        }
                    });
                }
                else {
                    showOutTOKEN();
                }
            }
            
            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                showOutTOKEN();
            }
        });
    }
    private void registerNotificationToken() {
        final String[] token = {SharedPreferencesManager.getFCMToken()};
        if (token[0].equals("")) {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            
                            return;
                        }
                        
                        // Get new Instance ID token
                        if (task.getResult() != null) {
                            token[0] = task.getResult().getToken();
                            SharedPreferencesManager.setFCMToken(token[0]);
                        }
                    });
        }
        System.out.println("FCM: "+SharedPreferencesManager.getFCMToken());
    }
}