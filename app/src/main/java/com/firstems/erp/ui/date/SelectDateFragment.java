package com.firstems.erp.ui.date;

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
import com.firstems.erp.databinding.SelectDateFragmentBinding;
import com.firstems.erp.ui.date.custom.SelectCustomFragment;
import com.firstems.erp.ui.date.day.SelectDayFragment;
import com.firstems.erp.ui.date.month.SelectMonthFragment;
import com.firstems.erp.ui.date.week.SelectWeekFragment;
import com.google.android.material.tabs.TabLayout;

public class SelectDateFragment extends Fragment {

    private SelectDateViewModel mViewModel;
    private SelectDateFragmentBinding binding;
    private TextView txtTitle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.select_date_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:{
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_select_date, new SelectDayFragment()).commit();
                        System.out.println("Day");
                        break;
                    }
                    case 1 : {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_select_date,new SelectWeekFragment()).commit();
                        System.out.println("Week");
                        break;
                    }
                    case 2 : {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_select_date,new SelectMonthFragment()).commit();
                        System.out.println("Month");
                        break;
                    }
                    case 3 : {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_select_date, new SelectCustomFragment()).commit();
                        System.out.println("Custom");
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
        txtTitle=binding.include16.findViewById(R.id.txtTitle);

        TabLayout.Tab tab = binding.tabLayout2.getTabAt(0);
        tab.select();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_select_date, new SelectDayFragment()).commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SelectDateViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
    }

}