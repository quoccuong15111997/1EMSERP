package com.firstems.erp.ui.shared.loaidoituongnhan;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.DoiTuongNhanAdapter;
import com.firstems.erp.api.model.response.doi_tuong_nhan.DoiTuongNhanItem;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.LoaiDoiTuongNhanFragmentBinding;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoiTuongNhanFragment extends CommonFragment {
    
    private DoiTuongNhanViewModel mViewModel;
    private LoaiDoiTuongNhanFragmentBinding binding;
    private List<DoiTuongNhanItem> doiTuongNhanItemList;
    private List<DoiTuongNhanItem> listCurrent;
    private DoiTuongNhanAdapter doiTuongNhanAdapter;
    private String key= "";
    private TextView txtTitle;
    private DoiTuongNhanItem doiTuongNhanItemSelected;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.loai_doi_tuong_nhan_fragment, container, false);
        
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void addEvents() {
        doiTuongNhanAdapter.setDoiTuongOnItemCheckListener(new DoiTuongNhanAdapter.DoiTuongOnItemCheckListener() {
            @Override
            public void onItemClick(int newPosition, DoiTuongNhanItem item) {
                doiTuongNhanItemList.get(newPosition).setCheck(true);
                binding.recycleEmployee.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0 ;i < doiTuongNhanItemList.size();i++){
                            DoiTuongNhanItem nhanItem = doiTuongNhanItemList.get(i);
                            if (nhanItem.getiTEMCODE().equals(item.getiTEMCODE())){
                                doiTuongNhanItemList.get(i).setCheck(true);
                            }
                            else {
                                doiTuongNhanItemList.get(i).setCheck(false);
                            }
                        }
                        for (int i =0; i<listCurrent.size();i++){
                            DoiTuongNhanItem nhanItem = listCurrent.get(i);
                            if (nhanItem.getiTEMCODE().equals(item.getiTEMCODE())){
                                listCurrent.get(i).setCheck(true);
                            }
                            else {
                                listCurrent.get(i).setCheck(false);
                            }
                        }
                        doiTuongNhanAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        binding.radAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doiTuongNhanItemList.clear();
                doiTuongNhanItemList.addAll(listCurrent);
                doiTuongNhanAdapter.notifyDataSetChanged();
            }
        });
        binding.radSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doiTuongNhanItemList.clear();
                for (DoiTuongNhanItem item : listCurrent){
                    if (item.isCheck()){
                        doiTuongNhanItemList.add(item);
                        break;
                    }
                }
                doiTuongNhanAdapter.notifyDataSetChanged();
            }
        });
        binding.radUnSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doiTuongNhanItemList.clear();
                for (DoiTuongNhanItem item : listCurrent){
                    if (!item.isCheck()){
                        doiTuongNhanItemList.add(item);
                    }
                }
                doiTuongNhanAdapter.notifyDataSetChanged();
            }
        });
        binding.editTextTextPersonName2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        
            }
    
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        
            }
    
            @Override
            public void afterTextChanged(Editable editable) {
                doFilter(editable.toString().toLowerCase().trim());
            }
        });
        binding.include22.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        binding.include22.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoiTuongNhanItem tuongNhanItem = null;
                for (DoiTuongNhanItem item : listCurrent){
                    if (item.isCheck()){
                        tuongNhanItem = item;
                    }
                }
                if (tuongNhanItem!=null){
                    Intent intent = new Intent();
                    intent.putExtra(Constant.NAME_PUT_DOI_TUONG_NHAN,tuongNhanItem);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                }
                else {
                    showToastError("Vui lòng chọn đối tượng");
                }
            }
        });
    }
    
    private void doFilter(String s) {
        doiTuongNhanItemList.clear();
        if (binding.radAll.isChecked()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                doiTuongNhanItemList.addAll(listCurrent
                        .stream()
                        .filter(x -> x.getiTEMNAME().toLowerCase().contains(s))
                        .collect(Collectors.toList()));
            }
        }
        if (binding.radSelected.isChecked()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                doiTuongNhanItemList.addAll(listCurrent
                        .stream()
                        .filter(x -> x.getiTEMNAME().toLowerCase().contains(s) && x.isCheck())
                        .collect(Collectors.toList()));
            }
        }
        if (binding.radUnSelected.isChecked()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                doiTuongNhanItemList.addAll(listCurrent
                        .stream()
                        .filter(x -> x.getiTEMNAME().toLowerCase().contains(s) && !x.isCheck())
                        .collect(Collectors.toList()));
            }
        }
        binding.recycleEmployee.post(new Runnable() {
            @Override
            public void run() {
                doiTuongNhanAdapter.notifyDataSetChanged();
            }
        });
    }
    
    private void addControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(83), SharedPreferencesManager.getSystemLabel(63));
        progressdialog.show();
        
        Intent intent = getActivity().getIntent();
        key = intent.getStringExtra(Constant.NAME_PUT_KEY_DOI_TUONG_NHAN);
        doiTuongNhanItemSelected = (DoiTuongNhanItem) intent.getSerializableExtra(Constant.NAME_PUT_DOI_TUONG_NHAN);
    
        listCurrent = new ArrayList<>();
        doiTuongNhanItemList = new ArrayList<>();
        doiTuongNhanAdapter= new DoiTuongNhanAdapter(doiTuongNhanItemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleEmployee.setAdapter(doiTuongNhanAdapter);
        binding.recycleEmployee.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        binding.recycleEmployee.addItemDecoration(dividerItemDecoration);
        binding.radAll.setChecked(true);
        
        txtTitle = binding.include22.findViewById(R.id.txtTitle);
        txtTitle.setText("Chọn đối tượng");
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DoiTuongNhanViewModel.class);
        mViewModel.loadListDoiTuongNhan(key);
        mViewModel.getMutableLiveDataDoiTuongNhanList().observe(getViewLifecycleOwner(), new Observer<List<DoiTuongNhanItem>>() {
            @Override
            public void onChanged(List<DoiTuongNhanItem> doiTuongNhanItems) {
                doiTuongNhanItemList.clear();
                listCurrent.clear();
                listCurrent.addAll(doiTuongNhanItems);
                if (doiTuongNhanItemSelected!=null){
                    for (int i=0;i<listCurrent.size();i++){
                        if (listCurrent.get(i).getiTEMCODE().equals(doiTuongNhanItemSelected.getiTEMCODE())){
                            listCurrent.get(i).setCheck(true);
                            break;
                        }
                    }
                }
                doiTuongNhanItemList.addAll(listCurrent);
                doiTuongNhanAdapter.notifyDataSetChanged();
                progressdialog.dismiss();
            }
        });
    }
    
}