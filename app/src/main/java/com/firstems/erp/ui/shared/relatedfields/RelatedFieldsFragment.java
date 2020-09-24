package com.firstems.erp.ui.shared.relatedfields;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.firstems.erp.R;
import com.firstems.erp.adapter.RelatedFieldAdapter;
import com.firstems.erp.api.model.response.lanh_vuc_lien_quan.LanhVucLienQuan;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.RelatedFieldsFragmentBinding;
import com.firstems.erp.enums.TypeSelect;
import com.wdullaer.materialdatetimepicker.time.Timepoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RelatedFieldsFragment extends Fragment {

    private RelatedFieldsViewModel mViewModel;
    private RelatedFieldsFragmentBinding binding;
    private List<LanhVucLienQuan> list;
    private List<LanhVucLienQuan> listCurrent;
    private RelatedFieldAdapter relatedFieldAdapter;
    private TypeSelect typeSelect;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.related_fields_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        relatedFieldAdapter.setRelatedFieldOnclickListener(new RelatedFieldAdapter.RelatedFieldOnclickListener() {
            @Override
            public void onItemClick(int position, int oldPosition, LanhVucLienQuan item) {
               if (typeSelect==TypeSelect.SINGEL){
                   list.get(oldPosition).setChecked(false);
                   list.get(position).setChecked(item.isChecked());
               }
               else if (typeSelect ==TypeSelect.MULTI){
                   list.get(position).setChecked(item.isChecked());
               }
            }
        });
        binding.radAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relatedFieldAdapter.getList().clear();
                relatedFieldAdapter.setList(listCurrent);
                binding.recycle.post(new Runnable() {
                    @Override
                    public void run() {
                        relatedFieldAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        binding.radSelected.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                relatedFieldAdapter.getList().clear();
                relatedFieldAdapter.setList(listCurrent.stream().filter(x -> x.isChecked()).collect(Collectors.toList()));
                binding.recycle.post(new Runnable() {
                    @Override
                    public void run() {
                        relatedFieldAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        binding.radUnSelected.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                relatedFieldAdapter.getList().clear();
                relatedFieldAdapter.setList(listCurrent.stream().filter(x -> !x.isChecked()).collect(Collectors.toList()));
                binding.recycle.post(new Runnable() {
                    @Override
                    public void run() {
                        relatedFieldAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void addControls() {
        Intent intent = getActivity().getIntent();
        typeSelect = (TypeSelect) intent.getSerializableExtra(Constant.NANE_PUT_TYPE_SELECT);

        listCurrent = new ArrayList<>();
        list = new ArrayList<>();
        relatedFieldAdapter= new RelatedFieldAdapter();
        relatedFieldAdapter.setTypeSelect(typeSelect!=null ? typeSelect : TypeSelect.SINGEL);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        binding.recycle.setAdapter(relatedFieldAdapter);
        binding.recycle.setLayoutManager(linearLayoutManager);
        binding.recycle.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RelatedFieldsViewModel.class);
        mViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<LanhVucLienQuan>>() {
            @Override
            public void onChanged(List<LanhVucLienQuan> lanhVucLienQuans) {
                listCurrent.clear();
                listCurrent.addAll(lanhVucLienQuans);
                relatedFieldAdapter.setList(lanhVucLienQuans);
                binding.recycle.post(new Runnable() {
                    @Override
                    public void run() {
                        relatedFieldAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}