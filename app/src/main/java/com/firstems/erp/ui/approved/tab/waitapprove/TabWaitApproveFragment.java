package com.firstems.erp.ui.approved.tab.waitapprove;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.ApprovedListAdapter;
import com.firstems.erp.api.model.response.approved.ApprovedItemApiResponse;
import com.firstems.erp.api.model.response.approved.ApprovedItemDetail_1;
import com.firstems.erp.api.model.response.approved.ApprovedItemDetail_2;
import com.firstems.erp.callback.AprrovedDetail_1_ClickListener;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.TabWaitApproveFragmentBinding;
import com.firstems.erp.enums.TypeApproved;
import com.firstems.erp.ui.approved.detail.ApproveDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class TabWaitApproveFragment extends CommonFragment implements AprrovedDetail_1_ClickListener {

    private TabWaitApproveViewModel mViewModel;
    private TabWaitApproveFragmentBinding binding;
    private ApprovedListAdapter approveAdapter;
    private List<ApprovedItemApiResponse> list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_wait_approve_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }
    private void addEvents() {
    }

    private void addControls() {
        list =  new ArrayList<>();
        approveAdapter= new ApprovedListAdapter(list,this::onItemClick);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleApproved.setAdapter(approveAdapter);
        binding.recycleApproved.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TabWaitApproveViewModel.class);
        mViewModel.getDataListApprove().observe(getViewLifecycleOwner(), new Observer<List<ApprovedItemApiResponse>>() {
            @Override
            public void onChanged(List<ApprovedItemApiResponse> approveModels) {
                showLoadingNonMessDialog();
                approveAdapter.setDate(approveModels);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingNonMessDialog.dismiss();
                    }
                },1000);
            }
        });
    }

    @Override
    public void onItemClick(ApprovedItemDetail_2 itemDetail_2, ApprovedItemDetail_1 approvedItemDetail_1) {
        Intent intent= new Intent(getContext(), ApproveDetailActivity.class);
        intent.putExtra(Constant.NAME_PUT_APPROVED_INFO_DCMNCODE, approvedItemDetail_1.getdCMNCODE());
        intent.putExtra(Constant.NAME_PUT_APPROVED_INFO_KEY_CODE, itemDetail_2.getkEYCODE());
        intent.putExtra(Constant.NANE_PUT_TYPE_APPROVED, TypeApproved.WAIT);
        startActivity(intent);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if (mViewModel!=null){
            mViewModel.loadDataApproved();
        }
    }
}