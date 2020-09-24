package com.firstems.erp.ui.shared.province;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.ProvinceAdapter;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.province.Province;
import com.firstems.erp.callback.ItemCheckCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.ProvinceFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProvinceFragment extends Fragment implements ItemCheckCallback {

    private ProvinceViewModel mViewModel;
    private ProvinceFragmentBinding binding;
    private List<Province> provinceList;
    private List<Province> provinceListCurrent;
    private ProvinceAdapter provinceAdapter;
    private RecyclerView recyclerView;
    private Province provinceSelected;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.province_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.radAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provinceList.clear();;
                provinceList.addAll(provinceListCurrent);
                provinceAdapter.notifyDataSetChanged();
            }
        });
        binding.radSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provinceList.clear();
                for(Province province : provinceListCurrent){
                    if (province.isCheck()){
                        provinceList.add(province);
                        break;
                    }
                }
                provinceAdapter.notifyDataSetChanged();
            }
        });
        binding.radUnSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provinceList.clear();
                for (Province province : provinceListCurrent){
                    if (!province.isCheck()){
                        provinceList.add(province);
                    }
                }
                provinceAdapter.notifyDataSetChanged();
            }
        });
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
        binding.include22.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.include22.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                for (Province province : provinceListCurrent){
                    if (province.isCheck()){
                        intent.putExtra(Constant.NAME_PUT_PROVINCE,province);
                        break;
                    }
                }
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        });
    }

    private void doFilter(String s) {
        if (binding.radAll.isChecked()){
            List<Province> listTemp= new ArrayList<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listTemp.addAll(provinceListCurrent
                        .stream()
                        .filter(x -> x.getItemName().toLowerCase().contains(s))
                        .collect(Collectors.toList()));
            }
            provinceList.clear();
            provinceList.addAll(listTemp);
            provinceAdapter.notifyDataSetChanged();
        }
        else if (binding.radSelected.isChecked()){
            List<Province> listTemp= new ArrayList<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listTemp.addAll(provinceListCurrent
                        .stream()
                        .filter(x -> x.getItemName().toLowerCase().contains(s) && x.isCheck() )
                        .collect(Collectors.toList()));
            }
            provinceList.clear();
            provinceList.addAll(listTemp);
            provinceAdapter.notifyDataSetChanged();
        }
        else if (binding.radUnSelected.isChecked()){
            List<Province> listTemp= new ArrayList<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listTemp.addAll(provinceListCurrent
                        .stream()
                        .filter(x -> x.getItemName().toLowerCase().contains(s) && !x.isCheck())
                        .collect(Collectors.toList()));
            }
            provinceList.clear();
            provinceList.addAll(listTemp);
            provinceAdapter.notifyDataSetChanged();
        }
    }

    private void addControls() {
        Intent intent = getActivity().getIntent();
        String title = intent.getStringExtra(Constant.NAME_PUT_TITLE_SELECT_PROVINCE);
        if (title!=null){
            TextView txtTitle = binding.include22.findViewById(R.id.txtTitle);
            txtTitle.setText(title);
        }
        provinceSelected = (Province) intent.getSerializableExtra(Constant.NAME_PUT_PROVINCE);

        recyclerView = binding.recycle;
        provinceList= new ArrayList<>();
        provinceListCurrent= new ArrayList<>();
        provinceAdapter= new ProvinceAdapter(provinceList,this::onCheckChange);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(provinceAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProvinceViewModel.class);
        mViewModel.getLiveDataListProvince().observe(getViewLifecycleOwner(), new Observer<List<Province>>() {
            @Override
            public void onChanged(List<Province> provinces) {
                provinceList.clear();
                provinceListCurrent.clear();
                provinceListCurrent.addAll(provinces);
                if (provinceSelected!=null){
                    for (Province province : provinceListCurrent){
                        if (province.getItemCode().equals(provinceSelected.getItemCode())){
                            province.setCheck(true);
                            break;
                        }
                    }
                }

                provinceList.addAll(provinceListCurrent);
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        provinceAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onCheckChange(String itemCode, boolean isChecked) {
        for (int i=0;i<provinceListCurrent.size();i++){
            if (provinceListCurrent.get(i).getItemCode().equals(itemCode)){
                provinceListCurrent.get(i).setCheck(isChecked);
            }
            else {
                provinceListCurrent.get(i).setCheck(false);
            }
        }
    }
}