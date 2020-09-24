package com.firstems.erp.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.callback.BackToHomeCallback;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.database.helper.DatabaseHelper;
import com.firstems.erp.databinding.MoreFragmentBinding;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.login.LoginActivity;
import com.firstems.erp.ui.more.help.HelpActivity;
import com.firstems.erp.ui.more.password.ChangePasswordActivity;
import com.firstems.erp.ui.more.report.ReporttingActivity;

public class MoreFragment extends CommonFragment {

    private MoreViewModel mViewModel;
    private BackToHomeCallback backToHomeCallback;
    private MoreFragmentBinding binding;

    public MoreFragment(BackToHomeCallback backToHomeCallback) {
        this.backToHomeCallback = backToHomeCallback;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.more_fragment, container, false);
        
        initText();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.txtTitleSetting.setText(SharedPreferencesManager.getSystemLabel(196) /*Cài đặt*/);
        binding.txtTilleHelp.setText(SharedPreferencesManager.getSystemLabel(197) /*Hỗ trợ*/);
        binding.textView25.setText(SharedPreferencesManager.getSystemLabel(198) /*Đổi mật khẩu*/);
        binding.textView26.setText(SharedPreferencesManager.getSystemLabel(199) /*Đăng xuất*/);
    }
    
    private void addEvents() {
        binding.layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(200) /*Bạn chắc chắn muốn đăng xuất*/,SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                    @Override
                    public void onAccept() {
                        SharedPreferencesManager.getInstance().clearLoginData();
                        DatabaseHelper.getInstance().deleteAllLocation();
                        Intent intent= new Intent(getContext(), LoginActivity.class);
                        intent.putExtra(Constant.NAME_PUT_FLAG_LOGOUT,1);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
        binding.layoutReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ReporttingActivity.class);
                startActivity(intent);
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomeCallback.onBackPress();
            }
        });
        binding.layoutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
        binding.layoutChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        binding.txtCompName.setText(SharedPreferencesManager.getInstance().getPrefCompname());
        binding.txtLocateName.setText(SharedPreferencesManager.getInstance().getPrefLctname());
        binding.txtUserName.setText(SharedPreferencesManager.getInstance().getPrefAccountName());
        binding.txtPositioName.setText(SharedPreferencesManager.getInstance().getPrefPostName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MoreViewModel.class);

    }

}