package com.firstems.erp.ui.date.day;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.firstems.erp.R;
import com.firstems.erp.databinding.SelectDayFragmentBinding;

public class SelectDayFragment extends Fragment {

    private SelectDayViewModel mViewModel;
    private SelectDayFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.select_day_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.llCurrentDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.radCurrentDay.setChecked(true);
                binding.radYesterday.setChecked(false);
            }
        });
        binding.llYesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.radYesterday.setChecked(true);
                binding.radCurrentDay.setChecked(false);
            }
        });
    }

    private void addControls() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SelectDayViewModel.class);
        // TODO: Use the ViewModel
    }

}