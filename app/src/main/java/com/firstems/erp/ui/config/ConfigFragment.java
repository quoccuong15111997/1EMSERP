package com.firstems.erp.ui.config;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.databinding.ConfigFragmentBinding;
import com.firstems.erp.loading.LoadingActivity;
import com.firstems.erp.model.Language;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.viewmodel.config.ConfigUIBindingModel;
import com.google.gson.JsonObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.security.auth.callback.Callback;

public class ConfigFragment extends CommonFragment {

    private ConfigViewModel mViewModel;
    private ConfigFragmentBinding binding;
    private TextView txtTitle;
    private List<Language> languageList;
    private Spinner spinnerLang;
    private ArrayAdapter adapterLang;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.config_fragment, container, false);
        setAminHeader();
        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtDomain.setText("http://api-dev.firstems.com/");
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.include11.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.switchInfoSignature.isChecked();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.getUi().getEdtDomain().equals("")){
                    checkDomain(binding.edtDomain.getText().toString().trim(), new CheckDomainCallback() {
                        @Override
                        public void onDomainIsTrust() {
                            if (Integer.parseInt(binding.edtNumberDay.getText().toString()) == 0){
                                binding.edtNumberDay.setText("7");
                            }
    
                            SharedPreferencesManager.getInstance().clearLoginData();
    
                            Language language = languageList.get(spinnerLang.getSelectedItemPosition());
                            SharedPreferencesManager.getInstance().setDomain(binding.getUi().getEdtDomain());
                            SharedPreferencesManager.getInstance().setLanguage(language.getLangCode());
                            SharedPreferencesManager.getInstance().setNumberDaySignature(Integer.parseInt(binding.getUi().getEdtNumberDay()));
                            SharedPreferencesManager.getInstance().setInfoSignature(binding.switchInfoSignature.isChecked());
                            SharedPreferencesManager.getInstance().setWaitAppreoved(binding.switchWaitApproved.isChecked());
                            SharedPreferencesManager.getInstance().setWaitSignature(binding.switchWaitSignature.isChecked());
                            SharedPreferencesManager.getInstance().setCompleteSignature(binding.switchCompleteSignature.isChecked());
                            SharedPreferencesManager.getInstance().setInfoApproved(binding.switchInfoApproved.isChecked());
                            SharedPreferencesManager.getInstance().setDefaulRequestFormPart(binding.switchResquestFormPart.isChecked());
                            SharedPreferencesManager.getInstance().setDefaulRequestFormCongKhoan(binding.switchResquestFormCongKhoan.isChecked());
    
                            ApiServices.reset();
                            ApiServices.init(SharedPreferencesManager.getInstance().getDomain());
    
                            SharedPreferencesManager.getInstance().setFirstSetup(false);
                            Intent intent= new Intent(getActivity(), LoadingActivity.class);
                            startActivity(intent);
                            setOveridePendingTransisi(getActivity());
                            getActivity().finish();
                        }
    
                        @Override
                        public void onDomainError() {
                            showErrorDialog("Thông báo","Domain không hợp lệ");
                        }
                    });
                }
            }
        });

    }
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(binding.lParentContent);
                        binding.txtTitleSystemSetting.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            binding.txtTitleSystemSetting.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void checkDomain(String domain, CheckDomainCallback checkDomainCallback) {
        if (domain.startsWith("http://") || domain.startsWith("https://")){
            CheckDomainTask checkDomainTask = new CheckDomainTask(domain,checkDomainCallback);
            checkDomainTask.execute();
        }
        else {
            checkDomainCallback.onDomainError();
        }
    }
    
    private void addControls() {
        txtTitle=binding.include11.findViewById(R.id.txtTitle);

        languageList= new ArrayList<>();
        spinnerLang=binding.spinerLang;
        adapterLang= new ArrayAdapter(getContext(),R.layout.custom_spinner_item,languageList);
        adapterLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLang.setAdapter(adapterLang);

        if (SharedPreferencesManager.getInstance().isFirstSetup()){
            binding.btnCancel.setEnabled(false);
            binding.include11.findViewById(R.id.imgBack).setVisibility(View.INVISIBLE);
        }
        else {
            binding.btnCancel.setEnabled(true);
            binding.include11.findViewById(R.id.imgBack).setVisibility(View.VISIBLE);
            binding.edtDomain.setText(SharedPreferencesManager.getInstance().getDomain());
            binding.edtNumberDay.setText(SharedPreferencesManager.getInstance().getNumberDaySignature()+"");
            binding.switchInfoSignature.setChecked(SharedPreferencesManager.getInstance().getInfoSignature());
            binding.switchWaitSignature.setChecked(SharedPreferencesManager.getInstance().getWaitSignature());
            binding.switchWaitApproved.setChecked(SharedPreferencesManager.getInstance().getWaitAppreoved());
            binding.switchCompleteSignature.setChecked(SharedPreferencesManager.getInstance().getCompleteSignature());
            binding.switchInfoApproved.setChecked(SharedPreferencesManager.getInstance().getInfoApproved());
            binding.switchResquestFormPart.setChecked(SharedPreferencesManager.getInstance().getDefaulRequestFormPart());
            binding.switchResquestFormCongKhoan.setChecked(SharedPreferencesManager.getInstance().getDefaulRequestFormCongKhoan());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConfigViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
        mViewModel.getUiModel().observe(getViewLifecycleOwner(), new Observer<ConfigUIBindingModel>() {
            @Override
            public void onChanged(ConfigUIBindingModel configUIBindingModel) {
                binding.setUi(configUIBindingModel);
            }
        });
        mViewModel.getLangList().observe(getViewLifecycleOwner(), new Observer<List<Language>>() {
            @Override
            public void onChanged(List<Language> languages) {
                languageList.addAll(languages);
                adapterLang.notifyDataSetChanged();
            }
        });
        mViewModel.getNumberDateValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println(s);
            }
        });
    }
    public interface CheckDomainCallback{
        void onDomainIsTrust();
        void onDomainError();
    }
    class CheckDomainTask extends AsyncTask<Void,Void,Boolean>{
       
        private String domain;
        private CheckDomainCallback checkDomainCallback;
    
        public CheckDomainTask(String domain, CheckDomainCallback checkDomainCallback) {
            this.domain = domain;
            this.checkDomainCallback = checkDomainCallback;
        }
    
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                checkDomainCallback.onDomainIsTrust();
            }
            else {
                checkDomainCallback.onDomainError();
            }
        }
    
        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean res = false;
            try {
                if (domain.startsWith("http")){
                    URL httpbinEndpoint = new URL(domain);
                    HttpURLConnection myConnection
                            = (HttpURLConnection) httpbinEndpoint.openConnection();
                    myConnection.setRequestMethod("GET");
                    myConnection.setDoOutput(true);
    
                    if (myConnection.getResponseCode() == 200) {
                        res = true;
                    } else {
                        res = false;
                    }
                }
                else if (domain.startsWith("https")){
                    URL httpbinEndpoint = new URL(domain);
                    HttpsURLConnection myConnection
                            = (HttpsURLConnection) httpbinEndpoint.openConnection();
                    myConnection.setRequestMethod("GET");
                    myConnection.setDoOutput(true);
    
                    if (myConnection.getResponseCode() == 200) {
                        res = true;
                    } else {
                        res = false;
                    }
                }
                else {
                    res = false;
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
                res = false;
            }
            return res;
        }
    }
}