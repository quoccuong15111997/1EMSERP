package com.firstems.erp.ui.shared.department;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.DepartmentAdapter;
import com.firstems.erp.api.model.response.department.DepartmentItemApiResponse;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.DepartmentFragmentBinding;
import com.firstems.erp.enums.TypeSelect;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepartmentFragment extends Fragment {

    private DepartmentViewModel mViewModel;
    private DepartmentFragmentBinding binding;
    private List<DepartmentItemApiResponse> list;
    private List<DepartmentItemApiResponse> listCurrent;
    private DepartmentAdapter departmentAdapter;
    private int oldPosition = 0;
    private TypeSelect typeSelect;
    private List<DepartmentItemApiResponse> listSelect;
    private TextView txtTitle;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.department_fragment, container, false);

        initText();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.radAll.setText(SharedPreferencesManager.getSystemLabel(138) /*Tất cả*/);
        binding.radSelected.setText(SharedPreferencesManager.getSystemLabel(139) /*Đã chọn*/);
        binding.radUnSelected.setText(SharedPreferencesManager.getSystemLabel(140) /*Chưa chọn*/);
    }
    
    private void addEvents() {
        binding.editTextTextPersonName2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                doFilter(s.toString().toLowerCase());
            }
        });
        binding.include22.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSelect= new ArrayList<>();
                listSelect.clear();
                for (DepartmentItemApiResponse item : listCurrent){
                    if (item.getCheck()==1){
                        listSelect.add(item);
                    }
                }
                Intent intent = new Intent();
                intent.putExtra(Constant.NANE_PUT_DEPARTMENT_LIST, (Serializable) listSelect);
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        });
        binding.include22.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        departmentAdapter.setOnDepartmentClickListener(new DepartmentAdapter.OnDepartmentClickListener() {
            @Override
            public void onItemLick(DepartmentItemApiResponse item) {
                System.out.println("TYPE" + typeSelect);
                if (typeSelect==TypeSelect.SINGEL){
                    int index = 0;
                    for (int i = 0;i<listCurrent.size();i++){
                        if (listCurrent.get(i).getItemCode().equals(item.getItemCode())){
                            index = i;
                            break;
                        }
                    }
                    if (listCurrent.get(oldPosition).getCheck() == 0) {
                        listCurrent.get(oldPosition).setCheck(1);
                    } else {
                        listCurrent.get(oldPosition).setCheck(0);
                    }
                    if (listCurrent.get(index).getCheck() == 0) {
                        listCurrent.get(index).setCheck(1);
                    } else {
                        listCurrent.get(index).setCheck(0);
                    }
                    binding.recycledepartment.post(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    oldPosition = index;
                }
                if (typeSelect == TypeSelect.MULTI){
                    int index = 0;
                    for (int i = 0;i<listCurrent.size();i++){
                        if (listCurrent.get(i).getItemCode().equals(item.getItemCode())){
                            index = i;
                            break;
                        }
                    }
                    if (listCurrent.get(index).getCheck() == 0) {
                        listCurrent.get(index).setCheck(1);
                    } else {
                        listCurrent.get(index).setCheck(0);
                    }
                    binding.recycledepartment.post(new Runnable() {
                        @Override
                        public void run() {
                            /*for (DepartmentItemApiResponse departmentItemApiResponse : listCurrent){
                                System.out.println(departmentItemApiResponse.getItemName() + " = "+departmentItemApiResponse.getCheck());
                            }*/
                        }
                    });
                }
            }
        });
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radAll : {
                        if (binding.radAll.isChecked()){
                            list.clear();
                            list.addAll(listCurrent);
                            departmentAdapter.notifyDataSetChanged();
                        }
                        break;
                    }
                    case R.id.radSelected : {
                        if (binding.radSelected.isChecked()){
                            list.clear();
                            for (DepartmentItemApiResponse item : listCurrent){
                                if (item.getCheck() == 1){
                                    list.add(item);
                                }
                            }
                            departmentAdapter.notifyDataSetChanged();
                        }
                        break;
                    }
                    case R.id.radUnSelected : {
                        if (binding.radUnSelected.isChecked()){
                            list.clear();
                            for (DepartmentItemApiResponse item : listCurrent){
                                if (item.getCheck()==0){
                                    list.add(item);
                                }
                            }
                            departmentAdapter.notifyDataSetChanged();
                        }
                        break;
                    }
                }
            }
        });
        binding.radAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.radSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.radUnSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void doFilter(String s) {
        list.clear();
        for (DepartmentItemApiResponse item : listCurrent){
            if (binding.radAll.isChecked()){
                if (item.getItemName().toLowerCase().contains(s)){
                    list.add(item);
                }
            }
            if (binding.radSelected.isChecked()){
                if (item.getCheck()==1 && item.getItemName().toLowerCase().contains(s)){
                    list.add(item);
                }
            }
            if (binding.radUnSelected.isChecked()){
                if (item.getCheck()==0 && item.getItemName().toLowerCase().contains(s)){
                    list.add(item);
                }
            }
        }
        departmentAdapter.notifyDataSetChanged();
    }

    private void addControls() {
        listSelect = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        typeSelect = (TypeSelect) intent.getSerializableExtra(Constant.NANE_PUT_TYPE_SELECT);
        if (typeSelect!=null){
            listSelect = (List<DepartmentItemApiResponse>) intent.getSerializableExtra(Constant.NANE_PUT_DEPARTMENT_LIST);
        }
        list= new ArrayList<>();
        listCurrent= new ArrayList<>();
        departmentAdapter = new DepartmentAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation());
        binding.recycledepartment.setAdapter(departmentAdapter);
        binding.recycledepartment.setLayoutManager(linearLayoutManager);
        binding.recycledepartment.addItemDecoration(dividerItemDecoration);

        txtTitle=binding.include22.findViewById(R.id.txtTitle);
        txtTitle.setText(SharedPreferencesManager.getSystemLabel(165) /*Chọn phòng ban*/);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DepartmentViewModel.class);
        mViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<DepartmentItemApiResponse>>() {
            @Override
            public void onChanged(List<DepartmentItemApiResponse> departmentItemApiResponses) {
                list.clear();
                listCurrent.clear();
                listCurrent.addAll(departmentItemApiResponses);
                if (listSelect != null) {
                   if (listSelect.size()>0){
                       for (DepartmentItemApiResponse item : listSelect){
                           for (int i =0;i<listCurrent.size();i++){
                               DepartmentItemApiResponse itemApiResponse = listCurrent.get(i);
                               if (item.getItemCode().equals(itemApiResponse.getItemCode())){
                                   listCurrent.get(i).setCheck(1);
                                   break;
                               }
                           }
                       }
                   }
                }
                list.addAll(listCurrent);
                departmentAdapter.notifyDataSetChanged();
            }
        });
    }
}