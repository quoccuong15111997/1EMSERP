package com.firstems.erp.ui.approved;

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

import com.firstems.erp.R;
import com.firstems.erp.callback.BackToHomeCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.databinding.ApprovedFragmentBinding;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.approved.tab.approved.TabApprovedFragment;
import com.firstems.erp.ui.approved.tab.waitapprove.TabWaitApproveFragment;
import com.google.android.material.tabs.TabLayout;

public class ApprovedFragment extends CommonFragment {

    private ApprovedViewModel mViewModel;

    private BackToHomeCallback backToHomeCallback;
    private ApprovedFragmentBinding approvedFragmentBinding;
    private TextView txtTitle;

    public ApprovedFragment(BackToHomeCallback backToHomeCallback) {
        this.backToHomeCallback = backToHomeCallback;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        approvedFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.approved_fragment, container, false);

        addControls();
        addEvents();
        return approvedFragmentBinding.getRoot();
    }

    private void addEvents() {
        approvedFragmentBinding.tabLayout3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:{
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_tab_approved, new TabWaitApproveFragment()).commit();
                        break;
                    }
                    case 1:{
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_tab_approved, new TabApprovedFragment()).commit();
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        approvedFragmentBinding.include.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomeCallback.onBackPress();
            }
        });
    }

    private void addControls() {
        txtTitle=approvedFragmentBinding.include.findViewById(R.id.txtTitle);
        approvedFragmentBinding.tabLayout3.getTabAt(0).setText(SharedPreferencesManager.getSystemLabel(145));
        approvedFragmentBinding.tabLayout3.getTabAt(1).setText(SharedPreferencesManager.getSystemLabel(149));
        approvedFragmentBinding.tabLayout3.getTabAt(0).select();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_tab_approved, new TabWaitApproveFragment()).commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ApprovedViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
    }
}