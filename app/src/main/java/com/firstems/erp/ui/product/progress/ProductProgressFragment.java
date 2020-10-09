package com.firstems.erp.ui.product.progress;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.progress.ProgressProductAdapter;
import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.ProductProgressFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductProgressFragment extends CommonFragment {

    private ProductProgressViewModel mViewModel;
    private List<ProgressItem> progressItems;
    private ProgressProductAdapter progressProductAdapter;
    private ProductProgressFragmentBinding binding;
    private TextView txtTite;
    private ImageView imgBack;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_progress_fragment, container, false);
        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        progressProductAdapter.setProgressProductOnlickListener(new ProgressProductAdapter.ProgressProductOnlickListener() {
            @Override
            public void onItemClick(ProgressItem progressItem) {
                Intent intent = new Intent();
                intent.putExtra(Constant.NAME_PUT_PROGRESS_PRODUCT, progressItem);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void addControls() {
        progressItems = new ArrayList<>();
        progressProductAdapter = new ProgressProductAdapter(progressItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleProgress.setLayoutManager(linearLayoutManager);
        binding.recycleProgress.setAdapter(progressProductAdapter);

        txtTite = binding.toolbar.findViewById(R.id.txtTitle);
        imgBack = binding.toolbar.findViewById(R.id.imgBack);

        txtTite.setText("Chọn lệnh sản xuất");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductProgressViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.getMutableLiveDataProgressItem().observe(getViewLifecycleOwner(), new Observer<List<ProgressItem>>() {
            @Override
            public void onChanged(List<ProgressItem> list) {
                progressItems.addAll(list);
                binding.recycleProgress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressProductAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}