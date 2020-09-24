package com.firstems.erp.ui.config;

import android.content.Intent;
import android.os.Bundle;
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

import com.firstems.erp.R;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.databinding.ConfigFragmentBinding;
import com.firstems.erp.loading.LoadingActivity;
import com.firstems.erp.model.Language;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.viewmodel.config.ConfigUIBindingModel;

import java.util.ArrayList;
import java.util.List;

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
        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
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

                    SharedPreferencesManager.getInstance().setFirstSetup(false);
                    Intent intent= new Intent(getActivity(), LoadingActivity.class);
                    startActivity(intent);
                    setOveridePendingTransisi(getActivity());
                    getActivity().finish();
                }
            }
        });

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
}