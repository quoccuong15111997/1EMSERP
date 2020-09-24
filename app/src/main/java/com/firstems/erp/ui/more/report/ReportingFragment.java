package com.firstems.erp.ui.more.report;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firstems.erp.R;
import com.firstems.erp.databinding.ReportingFragmentBinding;
import com.firstems.erp.ui.more.report.finance.FinanceFragment;
import com.firstems.erp.ui.more.report.sell.SellFragment;
import com.firstems.erp.ui.more.report.warehouse.WarehouseFragment;
import com.google.android.material.tabs.TabLayout;

public class ReportingFragment extends Fragment {

    private ReportingViewModel mViewModel;
    private ReportingFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.reporting_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.include14.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0: {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_reportting,new SellFragment()).commit();
                        break;
                    }
                    case 1: {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_reportting, new WarehouseFragment()).commit();
                        break;
                    }
                    case 2 : {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_reportting,new FinanceFragment()).commit();
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
    }

    private void addControls() {
        TabLayout.Tab tab = binding.tabLayout.getTabAt(0);
        tab.select();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_reportting,new SellFragment()).commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReportingViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView txtTitle = binding.include14.findViewById(R.id.txtTitle);
                txtTitle.setText(s);
            }
        });
    }

}