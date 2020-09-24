package com.firstems.erp.ui.shared.national;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.firstems.erp.adapter.NationalAdapter;
import com.firstems.erp.api.model.response.national.National;
import com.firstems.erp.callback.ItemCheckCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.NationalFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class NationalFragment extends CommonFragment implements ItemCheckCallback {

    private NationalViewModel mViewModel;
    private NationalFragmentBinding binding;
    private List<National> nationalList;
    private List<National> nationalListCurent;
    private RecyclerView recyclerView;
    private NationalAdapter nationalAdapter;
    private National nationalSelected;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.national_fragment, container, false);
        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.include22.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                for (National national : nationalListCurent){
                    if (national.isCheck()){
                        intent.putExtra(Constant.NAME_PUT_NATIONAL,national);
                        break;
                    }
                }
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        });
        binding.radAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nationalList.clear();
                nationalList.addAll(nationalListCurent);
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        nationalAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        binding.radSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nationalList.clear();
                for (National national : nationalListCurent){
                    if (national.isCheck()){
                        nationalList.add(national);
                    }
                }
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        nationalAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        binding.radUnSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nationalList.clear();
                for (National national : nationalListCurent){
                    if (!national.isCheck()){
                        nationalList.add(national);
                    }
                }
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        nationalAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        binding.include22.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void setCheck(String itemCode, boolean isCheck) {
        for (int i = 0;i < nationalListCurent.size();i++){
            if (nationalListCurent.get(i).getItemCode().equals(itemCode)){
                nationalListCurent.get(i).setCheck(isCheck);
            }
            else {
                nationalListCurent.get(i).setCheck(false);
            }
        }
    }

    private void addControls() {
        Intent intent= getActivity().getIntent();
        String title = intent.getStringExtra(Constant.NAME_PUT_TITLE_SELECT_NATIONAL);
        if (title!=null){
            TextView txtTile  = binding.include22.findViewById(R.id.txtTitle);
            txtTile.setText(title);
        }
        nationalSelected = (National) intent.getSerializableExtra(Constant.NAME_PUT_NATIONAL);

        nationalListCurent= new ArrayList<>();
        nationalList= new ArrayList<>();
        recyclerView = binding.recycle;
        nationalAdapter= new NationalAdapter(nationalList,this::onCheckChange);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(nationalAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NationalViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.getLiveDataNational().observe(getViewLifecycleOwner(), new Observer<List<National>>() {
            @Override
            public void onChanged(List<National> nationals) {
                nationalList.clear();
                nationalListCurent.clear();
                nationalListCurent.addAll(nationals);
                if (nationalSelected!=null){
                    for (int i =0;i<nationalListCurent.size();i++){
                        if (nationalListCurent.get(i).getItemCode().equals(nationalSelected.getItemCode())){
                            nationalListCurent.get(i).setCheck(true);
                        }
                    }
                }
                nationalList.addAll(nationalListCurent);
                nationalAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCheckChange(String itemCode, boolean isChecked) {
        setCheck(itemCode,isChecked);
    }
}