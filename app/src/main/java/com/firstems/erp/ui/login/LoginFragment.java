package com.firstems.erp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.transition.TransitionManager;

import com.firstems.erp.MainActivity;
import com.firstems.erp.R;
import com.firstems.erp.api.model.request.LoginLocationRequest;
import com.firstems.erp.api.model.response.login.LoginReponse;
import com.firstems.erp.api.model.response.login.SystemLoginApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.LoginCallback;
import com.firstems.erp.callback.SaveDataCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.databinding.LoginLayoutTypeFlatBinding;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.company.SelectCompanyActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends CommonFragment {

    private LoginViewModel mViewModel;
    //private LoginFragmentBinding binding;
    private LoginLayoutTypeFlatBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       // binding= DataBindingUtil.inflate(inflater,R.layout.login_fragment, container, false);
        binding= DataBindingUtil.inflate(inflater,R.layout.login_layout_type_flat, container, false);
        initProgressDialog(SharedPreferencesManager.getSystemLabel(83),SharedPreferencesManager.getSystemLabel(63));
        //progressdialog.show();
        setAminHeader();
        initLabel();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initLabel() {
        /*binding.textInputEditText.setHint(SharedPreferencesManager.getSystemLabel(1));
        binding.textInputEditText2.setHint(SharedPreferencesManager.getSystemLabel(2));*/
        binding.chkRemember.setText(SharedPreferencesManager.getSystemLabel(3));
        binding.button2.setText(SharedPreferencesManager.getSystemLabel(4));
        binding.textView56.setText(SharedPreferencesManager.getSystemLabel(5));
    }
    
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(binding.lParentContent);
                        binding.button2.setVisibility(View.VISIBLE);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }, 750);
        }
        catch (Exception ex){
            binding.button2.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    
    private void checkSavePass() {
        Intent intent= getActivity().getIntent();
        int flag = intent.getIntExtra(Constant.NAME_PUT_FLAG_LOGOUT,0);

            if (SharedPreferencesManager.getInstance().getPrefrRemember()){
                binding.textInputEditText.setText(SharedPreferencesManager.getInstance().getPrefUsername());
                binding.textInputEditText2.setText(SharedPreferencesManager.getInstance().getPrefPassword());
                binding.chkRemember.setChecked(true);
                if (flag!=1){
                    runLogin(SharedPreferencesManager.getInstance().getPrefUsername(),SharedPreferencesManager.getInstance().getPrefPassword());
                }
                else
                    progressdialog.dismiss();
            }
            else {
                progressdialog.dismiss();
        }
    }

    private void addEvents() {
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        if (!Util.isBlank(binding.textInputEditText.getText().toString())){
            if (!Util.isBlank(binding.textInputEditText2.getText().toString())){
                SharedPreferencesManager.getInstance().clearLoginData();
                runLogin(binding.textInputEditText.getText().toString(), binding.textInputEditText2.getText().toString());
            }
            else {
                //Password is blank
                showToastError("Chưa nhập mật khẩu");
            }
        }
        else {
            //Username is blank
            showToastError("Chưa nhập tên đăng nhập");
        }
    }

    private void runLogin(String username, String password) {
        showLoadingDialog(SharedPreferencesManager.getSystemLabel(83));
        mViewModel.runLogin(username, password, new LoginCallback() {
            @Override
            public void onLoginSuccess(SystemLoginApiResponse loginApiResponse) {
                SharedPreferencesManager.getInstance().setPrefUsername(binding.textInputEditText.getText().toString());
                SharedPreferencesManager.getInstance().setPrefPassword(binding.textInputEditText2.getText().toString());
                SharedPreferencesManager.getInstance().setPrefRemember(binding.chkRemember.isChecked());
                if (loginApiResponse.getCompanyList()!=null){
                    if (loginApiResponse.getCompanyList().size()==1){
                        if (loginApiResponse.getCompanyList().get(0).getLocationList().size()==1){
                            // 1 comp 1 locate
                            saveData(loginApiResponse, new SaveDataCallback() {
                                @Override
                                public void onSaveComplete() {
                                    Intent intent= new Intent(getContext(),MainActivity.class);
                                    startActivity(intent);
                                    loadingDialog.dismiss();
                                    getActivity().finish();
                                    setOveridePendingTransisi(getActivity());
                                }

                                @Override
                                public void onSaveFail() {
                                    loadingDialog.dismiss();
                                }
                            });
                        }
                    }
                    else {
                        SharedPreferencesManager.getInstance().setPrefToken(loginApiResponse.getToken());
                        checkLoginLocation(loginApiResponse);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onLoginFail(LoginReponse loginReponse) {
                loadingDialog.dismiss();
                showErrorDialog("Không thành công!",loginReponse.getRETNMSSG());
            }
    
            @Override
            public void onServerFail() {
                showOutTOKEN();
            }
        });
    }
    private void checkLoginLocation(SystemLoginApiResponse loginApiResponse) {
        if (!SharedPreferencesManager.getInstance().getPrefCompcode().equals("") && !SharedPreferencesManager.getInstance().getPrefLctcode().equals("")){
            doLoginByLocation(SharedPreferencesManager.getInstance().getPrefCompcode(),SharedPreferencesManager.getInstance().getPrefLctcode());
        }
        else {
            Intent intent= new Intent(getActivity(), SelectCompanyActivity.class);
            intent.putExtra(Constant.NAME_PUT_LIST_COMPANY, (Serializable) loginApiResponse.getCompanyList());
            startActivity(intent);
            getActivity().finish();
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
                    System.out.println("Second TOKEN: "+loginReponse.getSystemLoginApiResponse().getToken());
                    saveData(loginReponse.getSystemLoginApiResponse(), new SaveDataCallback() {
                        @Override
                        public void onSaveComplete() {
                            Intent intent= new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                            progressdialog.dismiss();
                            getActivity().finish();
                        }

                        @Override
                        public void onSaveFail() {
                            progressdialog.dismiss();
                        }
                    });
                }
                else {
                    progressdialog.dismiss();
                    showOutTOKEN();
                }
            }

            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                progressdialog.dismiss();
                showOutTOKEN();
            }
        });
    }
    private void saveData(SystemLoginApiResponse loginApiResponse, SaveDataCallback saveDataCallback) {
        if (loginApiResponse.getCompanyList().size()>0){
          if (loginApiResponse.getCompanyList().get(0).getLocationList().size()>0){
              SharedPreferencesManager.getInstance().setPrefToken(loginApiResponse.getToken());
              SharedPreferencesManager.getInstance().setPrefAppRight(loginApiResponse.getAppRight());
              SharedPreferencesManager.getInstance().setPrefCompcode(loginApiResponse.getCompanyList().get(0).getCompanyCode());
              SharedPreferencesManager.getInstance().setPrefCompname(loginApiResponse.getCompanyList().get(0).getCompanyName());
              SharedPreferencesManager.getInstance().setPrefLctcode(loginApiResponse.getCompanyList().get(0).getLocationList().get(0).getLocationCode());
              SharedPreferencesManager.getInstance().setPrefLctname(loginApiResponse.getCompanyList().get(0).getLocationList().get(0).getLocationName());
              if (loginApiResponse.getUserLogin()!=null){
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
          else
              saveDataCallback.onSaveFail();
        }
        saveDataCallback.onSaveFail();
    }

    private void addControls() {
        registerNotificationToken();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        checkSavePass();
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
    }
}