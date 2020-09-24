package com.firstems.erp.ui.date.week;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firstems.erp.R;
import com.firstems.erp.databinding.SelectWeekFragmentBinding;

public class SelectWeekFragment extends Fragment {
    private SelectWeekViewModel mViewModel;
    private SelectWeekFragmentBinding binding;

    public static SelectWeekFragment newInstance() {
        return new SelectWeekFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.select_week_fragment, container, false);
        
        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
    }

    private void addControls() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SelectWeekViewModel.class);
        // TODO: Use the ViewModel
    }

}