package com.firstems.erp.ui.more.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.databinding.HelpFragmentBinding;
import com.firstems.erp.ui.config.ConfigActivity;

public class HelpFragment extends Fragment {

    private HelpViewModel mViewModel;
    private HelpFragmentBinding binding;
    private TextView txtTitle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.help_fragment, container, false);


        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.include18.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.cardSystemSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ConfigActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        txtTitle=binding.include18.findViewById(R.id.txtTitle);
        txtTitle.setText("Cài đặt");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HelpViewModel.class);

    }

}